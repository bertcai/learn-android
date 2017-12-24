package com.example.cc.clock;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.util.TimerTask;
import java.util.Timer;

public class Chronograph extends AppCompatActivity implements
        Chronometer.OnChronometerTickListener, View.OnClickListener {

    private Chronometer chronometer;
    private TextView textView;
    private Timer timer;
    private TimerTask timerTask;
    //use chronometer to achieve the chronograph
    private Button start, stop, reset;
    //use timer and timerTask to achieve the chronograph
    private Button start2, stop2, reset2;
    private Boolean isStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chronograph);
        init();
    }

    private void init() {
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        textView = (TextView) findViewById(R.id.text);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        reset = (Button) findViewById(R.id.reset);

        start2 = (Button) findViewById(R.id.start2);
        stop2 = (Button) findViewById(R.id.stop2);
        reset2 = (Button) findViewById(R.id.reset2);

        chronometer.setOnChronometerTickListener(this);
        chronometer.setFormat("%s");
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        reset.setOnClickListener(this);

        start2.setOnClickListener(this);
        stop2.setOnClickListener(this);
        reset2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                chronometer.start();
                break;
            case R.id.stop:
                chronometer.stop();
                break;
            case R.id.reset:
                chronometer.setBase(SystemClock.elapsedRealtime());
                break;
            case R.id.start2:
                if (!isStarted) {
                    isStarted = true;
                    timer = new Timer();
                    timerTask = new TimerTask() {
                        int cnt = 0;

                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText(getStringTime(cnt++));
                                }
                            });
                        }
                    };
                    timer.schedule(timerTask, 0, 1);
                }
                break;
            case R.id.stop2:
                isStarted = false;
                if (!timerTask.cancel()) {
                    timerTask.cancel();
                    timer.cancel();
                }
                break;
            case R.id.reset2:
                textView.setText("00:00:00");
                break;
        }
    }

    private String getStringTime(int cnt) {
        int mm = cnt / 1000 / 60;
        int ss = (cnt / 1000) % 60;
        int ms = cnt % 1000 / 10;
        return String.format("%02d:%02d:%02d", mm, ss, ms);
    }

    @Override
    public void onChronometerTick(Chronometer chronometer) {
        String time = chronometer.getText().toString();
    }
}
