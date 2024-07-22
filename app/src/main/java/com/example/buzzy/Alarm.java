package com.example.buzzy;

public class Alarm {
    private int hour;
    private int minute;

    public Alarm(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }
}
