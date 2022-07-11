package com.example.workouttimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private long prepareTimeVar;
    private long workTimeVar;
    private long restTimeVar;
    private long roundsVar;
    private long cyclesVar;
    private long rbcVar;

    private long totalTimeVar;

    private ImageButton setTimeButton;
    private ImageButton settingsButton;
    private Button startButton;

    private TextView totalTimeDisplay;
    private TextView currentTimeType;
    private TextView currentTimeDisplay;
    private TextView nextTimeType;
    private TextView nextTimeDisplay;
    private TextView roundsDisplay;
    private TextView cyclesDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepareTimeVar = SetTime.getPrepareTimeVar();
        workTimeVar = SetTime.getWorkTimeVar();
        restTimeVar = SetTime.getRestTimeVar();
        roundsVar = SetTime.getRoundsVar();
        cyclesVar = SetTime.getCyclesVar();
        rbcVar = SetTime.getRbcVar();

        totalTimeVar = prepareTimeVar + (((workTimeVar + restTimeVar) * roundsVar) * cyclesVar);

        setTimeButton = findViewById(R.id.setTimeOptButton);
        settingsButton = findViewById(R.id.settingsButton);
        startButton = findViewById(R.id.startButton);

        totalTimeDisplay = findViewById(R.id.totalTimeDisplay);
        currentTimeType = findViewById(R.id.currentTimeLabel);
        currentTimeDisplay = findViewById(R.id.currentTimeDisplay);
        nextTimeType = findViewById(R.id.nextTimeLabel);
        nextTimeDisplay = findViewById(R.id.nextTimeDisplay);
        roundsDisplay = findViewById(R.id.roundsLeftDisplay);
        cyclesDisplay = findViewById(R.id.cyclesLeftDisplay);

        setLabels();

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

    private void setRCLabel(long num, TextView txt) {
        txt.setText("" + num);
    }

    private void setTimeLabel(long time, TextView txt) {

        int mins = (int) (time / 60);
        int secs = (int) (time % 60);

        String t = String.format(Locale.getDefault(), "%02d:%02d", mins, secs);

        txt.setText(t);
    }

    private void setLabels() {
        setTimeLabel(totalTimeVar, totalTimeDisplay);
        setTimeLabel(prepareTimeVar, currentTimeDisplay);
        setTimeLabel(workTimeVar, nextTimeDisplay);
        setRCLabel(roundsVar, roundsDisplay);
        setRCLabel(cyclesVar, cyclesDisplay);
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