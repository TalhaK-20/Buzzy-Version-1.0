package com.example.buzzy;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Vibrator;
import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {

    public static final String CHANNEL_ID = "alarm_channel";
    public static final int NOTIFICATION_ID = 1;
    private static Ringtone ringtone;
    private static Vibrator vibrator;

    @Override
    public void onReceive(Context context, Intent intent) {
        // Play the ringtone
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        }

        ringtone = RingtoneManager.getRingtone(context, alarmUri);
        ringtone.play();

        // Vibrate the phone
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) {
            long[] pattern = {0, 1000, 1000}; // Wait 0ms, vibrate 1000ms, wait 1000ms
            vibrator.vibrate(pattern, 0); // Repeat the pattern from the start (index 0)
        }

        // Create a stop intent
        Intent stopIntent = new Intent(context, StopAlarmReceiver.class);
        PendingIntent stopPendingIntent = PendingIntent.getBroadcast(context, 0, stopIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Create a silent notification with a stop button
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_logo)
                .setContentTitle("Alarm")
                .setContentText("Your alarm is ringing!")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(stopPendingIntent)
                .addAction(R.drawable.ic_stop, "Stop Alarm", stopPendingIntent)
                .setSound(null) // Ensure the notification is silent
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        createNotificationChannel(context, notificationManager);
        notificationManager.notify(NOTIFICATION_ID, builder.build());

        // Start the AlarmRingingActivity
        Intent alarmIntent = new Intent(context, AlarmRingingActivity.class);
        alarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(alarmIntent);

        // Stop the alarm after 30 seconds
        new Handler().postDelayed(() -> stopAlarm(context), 30000);
    }

    private void createNotificationChannel(Context context, NotificationManager notificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Alarm Channel";
            String description = "Channel for alarm notifications";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            channel.setSound(null, null); // Ensure the notification channel is silent
            notificationManager.createNotificationChannel(channel);
        }
    }

    public static void stopAlarm(Context context) {
        if (ringtone != null && ringtone.isPlaying()) {
            ringtone.stop();
        }

        if (vibrator != null) {
            vibrator.cancel();
        }

        // Cancel the notification
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(NOTIFICATION_ID);
    }
}
