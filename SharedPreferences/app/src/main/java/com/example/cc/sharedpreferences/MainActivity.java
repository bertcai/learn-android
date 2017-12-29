package com.example.cc.sharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.save_data);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences(
                        "data", MODE_PRIVATE).edit();
                editor.putString("name", "Bert");
                editor.putInt("age", 20);
                editor.putBoolean("married", false);
                editor.apply();
            }
        });

        Button button1 = (Button) findViewById(R.id.restore_data);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences(
                        "data", MODE_PRIVATE);
                String name = preferences.getString("name", "");
                int age = preferences.getInt("age", 0);
                boolean married = preferences.getBoolean("married", false);
                Log.d(TAG, "name is "+ name);
                Log.d(TAG, "age is "+ age);
                Log.d(TAG, "married is "+ married);
            }
        });
    }
}
