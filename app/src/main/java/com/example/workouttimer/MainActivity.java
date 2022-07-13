//COPY
package com.example.workouttimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private long prepareTimeVar;
    private long workTimeVar;
    private long restTimeVar;
    private int roundsVar;
    private int cyclesVar;
    private long rbcVar;

    private long totalTimeVar;

    private boolean soundOnVar;
    private boolean vibrationOnVar;

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

        soundOnVar = Settings.isSoundOn();
        vibrationOnVar = Settings.isVibrationOn();

        totalTimeVar = prepareTimeVar + (workTimeVar * roundsVar * cyclesVar) + (restTimeVar * cyclesVar * (roundsVar - 1)) + (rbcVar * (cyclesVar-1));

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

    private void setLabels() {
        setTimeLabel(totalTimeVar, totalTimeDisplay);
        setTimeLabel(prepareTimeVar, currentTimeDisplay);
        setTimeLabel(workTimeVar, nextTimeDisplay);
        setRCLabel(roundsVar, roundsDisplay);
        setRCLabel(cyclesVar, cyclesDisplay);
    }

    private void setTimeLabel(long time, TextView txt) {
        int l = (int) (time / 1000);
        int mins = (l / 60);
        int secs = (l % 60);

        String t = String.format(Locale.getDefault(), "%02d:%02d", mins, secs);

        txt.setText(t);
    }

    private void setRCLabel(int num, TextView txt) {
        txt.setText("" + num);
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