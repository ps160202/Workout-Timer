package com.example.workouttimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class SetTime extends AppCompatActivity {

    private static int prepareTimeVar = 10000;
    private static int workTimeVar = 45000;
    private static int restTimeVar = 15000;
    private static int roundsVar = 3;
    private static int cyclesVar = 3;
    private static int rbcVar = 20000;

    public static int getPrepareTimeVar() {
        return prepareTimeVar;
    }

    public static int getWorkTimeVar() {
        return workTimeVar;
    }

    public static int getRestTimeVar() {
        return restTimeVar;
    }

    public static int getRoundsVar() {
        return roundsVar;
    }

    public static int getCyclesVar() {
        return cyclesVar;
    }

    public static int getRbcVar() {
        return rbcVar;
    }

    private MaterialButton saveButton;
    private MaterialButton cancelButton;

    private RelativeLayout prepareTimeLayout;
    private RelativeLayout workTimeLayout;
    private RelativeLayout restTimeLayout;
    private RelativeLayout roundsLayout;
    private RelativeLayout cyclesLayout;
    private RelativeLayout rbcLayout;

    private TextView prepareTimeDisplay;
    private TextView workTimeDisplay;
    private TextView restTimeDisplay;
    private TextView roundsDisplay;
    private TextView cyclesDisplay;
    private TextView rBCDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time);

        saveButton = findViewById(R.id.saveButton);
        cancelButton = findViewById(R.id.cancelButton);

        prepareTimeLayout = findViewById(R.id.prepareLayout);
        workTimeLayout = findViewById(R.id.workLayout);
        restTimeLayout = findViewById(R.id.restLayout);
        roundsLayout = findViewById(R.id.roundsLayout);
        cyclesLayout = findViewById(R.id.cyclesLayout);
        rbcLayout = findViewById(R.id.rBCLayout);

        prepareTimeDisplay = findViewById(R.id.prepareTimeDisplay);
        workTimeDisplay = findViewById(R.id.workTimeDisplay);
        restTimeDisplay = findViewById(R.id.restTimeDisplay);
        roundsDisplay = findViewById(R.id.roundsLeftDisplay);
        cyclesDisplay = findViewById(R.id.cyclesLeftDisplay);
        rBCDisplay = findViewById(R.id.rBCTimeDisplay);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });
    }

    private void back() {
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}