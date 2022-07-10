package com.example.workouttimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private ImageButton setTimeButton;
    private ImageButton settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTimeButton = findViewById(R.id.setTimeOptButton);
        settingsButton = findViewById(R.id.settingsButton);

        setTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTimeSettings();
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSettings();
            }
        });
    }

    private void openSettings() {
        finish();
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    private void openTimeSettings() {
        finish();
        Intent intent = new Intent(this, SetTime.class);
        startActivity(intent);
    }
}