package com.example.cc.clock;

import android.content.Intent;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Toast;

public class Timer extends AppCompatActivity {

    private String time;
    private int timeToSecond(String s) {
        int sumSecond = 0;
        String time[] = s.split(":");
        if (time.length == 3) {
            sumSecond = Integer.parseInt(time[0]) * 3600
                    + Integer.parseInt(time[1]) * 60
                    + Integer.parseInt(time[2]);
        } else if (time.length == 2) {
            sumSecond = Integer.parseInt(time[0]) * 60
                    + Integer.parseInt(time[1]);
        } else {
            sumSecond = Integer.parseInt(time[0]);
        }
        return sumSecond;
    }

    public void startTimer(String message, int seconds) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_LENGTH, seconds)
                .putExtra(AlarmClock.EXTRA_SKIP_UI, true);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        Button ok = (Button) findViewById(R.id.set_button);
        final EditText editText = (EditText) findViewById(R.id.edit_timer);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputText = editText.getText().toString();
                time = inputText;
                Toast.makeText(Timer.this,
                        "success!",
                        Toast.LENGTH_LONG).show();
                int second;
                second = timeToSecond(time);
                startTimer("You set a Alarm "+ second+"s"+" ago. : )", second);
            }
        });
    }


}
