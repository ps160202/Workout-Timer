//COPY
package com.example.workouttimer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Locale;
import java.util.PriorityQueue;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {

    private long prepareTimeVar;
    private long workTimeVar;
    private long restTimeVar;
    private int roundsVar;
    private int roundVarTmp;
    private int cyclesVar;
    private long rbcVar;

    private long totalTimeVar;

    private boolean soundOnVar;
    private boolean vibrationOnVar;

    private ImageButton setTimeButton;
    private ImageButton settingsButton;
    private Button startButton;
    private ImageButton resetButton;

    private TextView totalTimeDisplay;
    private TextView currentTimeType;
    private TextView currentTimeDisplay;
    private TextView nextTimeType;
    private TextView nextTimeDisplay;
    private TextView roundsDisplay;
    private TextView cyclesDisplay;

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private MaterialButton yesResetButton;
    private MaterialButton cancelResetButton;

    private CountDownTimer countDownTimer;
    private boolean timerRunning;
    private MediaPlayer mPlayer;
    private Vibrator vibrator;
    private ArrayList<TimePeriod> queue;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepareTimeVar = SetTime.getPrepareTimeVar();
        workTimeVar = SetTime.getWorkTimeVar();
        restTimeVar = SetTime.getRestTimeVar();
        roundsVar = SetTime.getRoundsVar();
        roundVarTmp = roundsVar;
        cyclesVar = SetTime.getCyclesVar();
        rbcVar = SetTime.getRbcVar();

        soundOnVar = Settings.isSoundOn();
        vibrationOnVar = Settings.isVibrationOn();
        mPlayer = MediaPlayer.create(this, R.raw.boxing_bell);
        vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);

        timerRunning = false;

        totalTimeVar = prepareTimeVar + (workTimeVar * roundsVar * cyclesVar) + (restTimeVar * cyclesVar * (roundsVar - 1)) + (rbcVar * (cyclesVar - 1));

        setTimeButton = findViewById(R.id.setTimeOptButton);
        settingsButton = findViewById(R.id.settingsButton);
        startButton = findViewById(R.id.startButton);
        resetButton = findViewById(R.id.resetButton);

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

        queue = new ArrayList<TimePeriod>();
        index = 0;
        createQueue(queue);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timerRunning) {
                    pauseTimer();

                    startButton.setText("Resume");
                } else {
                    timerRunning = true;
                    startButton.setText("Pause");
                    startTimer(queue);
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimerPopUp();
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

    public class TimePeriod {
        private String timeType;
        private long duration;

        public TimePeriod(long duration, String type) {
            this.duration = duration;
            this.timeType = type;
        }
    }

    private void createQueue(ArrayList<TimePeriod> queue) {
        TimePeriod timePeriod = new TimePeriod(prepareTimeVar, "Prepare");
        queue.add(timePeriod);

        if(cyclesVar > 1)
        {
            for(int i = 0 ; i < cyclesVar ; i++)
            {
                for (int j = 0 ; j < roundsVar-1 ; j++)
                {
                    timePeriod = new TimePeriod(workTimeVar, "Work");
                    queue.add(timePeriod);
                    timePeriod = new TimePeriod(restTimeVar, "Rest");
                    queue.add(timePeriod);
                }

                timePeriod = new TimePeriod(workTimeVar, "Work");
                queue.add(timePeriod);

                if(i != cyclesVar-1) {
                    timePeriod = new TimePeriod(rbcVar, "Cycle Rest");
                    queue.add(timePeriod);
                }

            }
        }
        else
        {
            for (int j = 0 ; j < roundsVar-1 ; j++)
            {
                timePeriod = new TimePeriod(workTimeVar, "Work");
                queue.add(timePeriod);
                timePeriod = new TimePeriod(restTimeVar, "Rest");
                queue.add(timePeriod);
            }

            timePeriod = new TimePeriod(workTimeVar, "Work");
            queue.add(timePeriod);
        }
    }

    private void startTimer(ArrayList<TimePeriod> queue) {
        currentTimeType.setText(queue.get(index).timeType);

        if(index+1 < queue.size()) {
            nextTimeType.setText(queue.get(index+1).timeType);
            setTimeLabel(queue.get(index+1).duration, nextTimeDisplay);
        }
        else {
            nextTimeDisplay.setText("");
            nextTimeDisplay.setText("");
        }

        countDownTimer = new CountDownTimer(queue.get(index).duration, 1000) {
            @Override
            public void onTick(long l) {
                queue.get(index).duration = l;
                setTimeLabel(queue.get(index).duration, currentTimeDisplay);
                totalTimeVar -= 1000;
                setTimeLabel(totalTimeVar, totalTimeDisplay);
            }

            @Override
            public void onFinish() {
                if(soundOnVar)
                    mPlayer.start();

                if(vibrationOnVar)
                    vibrator.vibrate(1000);

                if(index == queue.size()-1) {
                    setLabels();
                    startButton.setText("Start");
                    resetTimes();
                    return;
                }

                if(queue.get(index).timeType == "Work") {
                    roundVarTmp--;
                    roundsDisplay.setText("" + roundVarTmp);
                }

                if(queue.get(index).timeType == "Cycle Rest") {
                    roundVarTmp = roundsVar;
                    roundsDisplay.setText("" + roundVarTmp);
                    cyclesDisplay.setText("" + --cyclesVar);
                }

                index++;
                startTimer(queue);
            }
        }.start();
    }

    private void pauseTimer() {
        countDownTimer.cancel();
        timerRunning = false;
        startButton.setText("Resume");
    }

    private void resetTimerPopUp() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View resetTimePopUp = getLayoutInflater().inflate(R.layout.activity_reset_permission, null);

        yesResetButton = resetTimePopUp.findViewById(R.id.yesResetButton);
        cancelResetButton = resetTimePopUp.findViewById(R.id.cancelResetButton);

        dialogBuilder.setView(resetTimePopUp);
        dialog = dialogBuilder.create();

        dialog.show();

        yesResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(timerRunning)
                    pauseTimer();

                resetTimes();
                dialog.dismiss();
            }
        });

        cancelResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private void resetTimes() {
        prepareTimeVar = SetTime.getPrepareTimeVar();
        workTimeVar = SetTime.getWorkTimeVar();
        restTimeVar = SetTime.getRestTimeVar();
        roundsVar = SetTime.getRoundsVar();
        cyclesVar = SetTime.getCyclesVar();
        rbcVar = SetTime.getRbcVar();

        totalTimeVar = prepareTimeVar + (workTimeVar * roundsVar * cyclesVar) + (restTimeVar * cyclesVar * (roundsVar - 1)) + (rbcVar * (cyclesVar - 1));

        setLabels();
        queue.clear();
        createQueue(queue);

        index = 0;

        startButton.setText("Start");
    }
}