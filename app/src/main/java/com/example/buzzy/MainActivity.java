package com.example.buzzy;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TimePicker;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TimePicker timePicker;
    private Button buttonSetAlarm;
    private RecyclerView recyclerViewAlarms;
    private AlarmAdapter alarmAdapter;
    private List<Alarm> alarmList;
    private AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timePicker = findViewById(R.id.timePicker);
        buttonSetAlarm = findViewById(R.id.buttonSetAlarm);
        recyclerViewAlarms = findViewById(R.id.recyclerViewAlarms);
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        alarmList = new ArrayList<>();
        alarmAdapter = new AlarmAdapter(alarmList);
        recyclerViewAlarms.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAlarms.setAdapter(alarmAdapter);

        buttonSetAlarm.setOnClickListener(v -> setAlarm());
    }

    private void setAlarm() {
        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();

        Alarm alarm = new Alarm(hour, minute);
        alarmList.add(alarm);
        alarmAdapter.notifyDataSetChanged();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, alarmList.size(), intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }
}

