package com.example.buzzy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            // Reset alarms here if necessary
            Log.d("BootReceiver", "Device rebooted - reset alarms here.");
        }
    }
}

