package com.example.cc.clock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button timer = (Button) findViewById(R.id.timer);
        Button chronograph = (Button) findViewById(R.id.chronograph);

        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent timerIntent = new Intent(MainActivity.this,
                        TimerActivity.class);
                startActivity(timerIntent);
            }
        });

        chronograph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chronIntent = new Intent(MainActivity.this,
                        ChronographActivity.class);
                startActivity(chronIntent);
            }
        });
    }
}
