package com.example.buzzy;

import android.app.Activity;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class AlarmRingingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_ringing);

        TextView textViewWakeUp = findViewById(R.id.textViewWakeUp);
        ImageView imageViewBell = findViewById(R.id.imageViewBell);

        textViewWakeUp.setText("Wake Up :)");

        Animation vibrateAnimation = AnimationUtils.loadAnimation(this, R.anim.vibrate_animation);
        imageViewBell.startAnimation(vibrateAnimation);
    }
}

