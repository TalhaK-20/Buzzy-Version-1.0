package com.example.buzzy;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class StopAlarmReceiver extends BroadcastReceiver {
    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    @Override
    public void onReceive(Context context, Intent intent) {
        // Stop the alarm
        AlarmReceiver.stopAlarm(context);

        Log.d("StopAlarmReceiver", "Alarm stopped by user.");
    }
}

