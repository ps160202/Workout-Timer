package com.example.workouttimer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.Locale;

public class SetTime extends AppCompatActivity {

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private TextView typeOfChange, typeOfChangeRC, typeOfChangeRCLabel;
    private EditText changeMins, changeSecs, changerc;
    private MaterialButton savePopChanges, cancelPopChanges, savePopChangesRC, cancelPopChangesRC;

    private static long prepareTimeVar = 10000;
    private static long workTimeVar = 45000;
    private static long restTimeVar = 15000;
    private static int roundsVar = 3;
    private static int cyclesVar = 3;
    private static long rbcVar = 20000;

    private long prepareTimeTmp;
    private long workTimeTmp;
    private long restTimeTmp;
    private int roundsTmp;
    private int cyclesTmp;
    private long rbcTmp;

    public static long getPrepareTimeVar() {
        return prepareTimeVar;
    }

    public static long getWorkTimeVar() {
        return workTimeVar;
    }

    public static long getRestTimeVar() {
        return restTimeVar;
    }

    public static int getRoundsVar() {
        return roundsVar;
    }

    public static int getCyclesVar() {
        return cyclesVar;
    }

    public static long getRbcVar() {
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

    private int newMins, newSecs;

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
        roundsDisplay = findViewById(R.id.roundsDisplay);
        cyclesDisplay = findViewById(R.id.cyclesDisplay);
        rBCDisplay = findViewById(R.id.rBCTimeDisplay);

        prepareTimeTmp = prepareTimeVar;
        workTimeTmp = workTimeVar;
        restTimeTmp = restTimeVar;
        cyclesTmp = cyclesVar;
        roundsTmp = roundsVar;
        rbcTmp = rbcVar;

        setLabels();

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prepareTimeVar = prepareTimeTmp;
                workTimeVar = workTimeTmp;
                restTimeVar = restTimeTmp;
                rbcVar = rbcTmp;
                roundsVar = roundsTmp;
                cyclesTmp = cyclesVar;

                back();
            }
        });

        prepareTimeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTime();
                int l = (int) (prepareTimeTmp / 1000);
                int mins = (l / 60);
                int secs = (l % 60);

                changeMins.setText("" + mins);
                changeSecs.setText("" + secs);
                typeOfChange.setText("Prepare");

                savePopChanges.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();


                        if(changeMins.getText().toString() == "")
                            newMins = 0;
                        else
                            newMins = Integer.parseInt(changeMins.getText().toString());

                        if(changeSecs.getText().toString() == "")
                            newSecs = 0;
                        else
                            newSecs = Integer.parseInt(changeSecs.getText().toString());

                        prepareTimeTmp = ((newMins*60) + (newSecs)) * 1000;
                        setTimeLabel(prepareTimeTmp, prepareTimeDisplay);
                    }
                });
            }
        });

        workTimeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTime();
                int l = (int) (workTimeTmp / 1000);
                int mins = (l / 60);
                int secs = (l % 60);

                changeMins.setText("" + mins);
                changeSecs.setText("" + secs);
                typeOfChange.setText("Work");

                savePopChanges.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();

                        if(changeMins.getText().toString() == "")
                            newMins = 0;
                        else
                            newMins = Integer.parseInt(changeMins.getText().toString());

                        if(changeSecs.getText().toString() == "")
                            newSecs = 0;
                        else
                            newSecs = Integer.parseInt(changeSecs.getText().toString());

                        workTimeTmp = ((newMins*60) + (newSecs)) * 1000;
                        setTimeLabel(workTimeTmp, workTimeDisplay);
                    }
                });
            }
        });

        restTimeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTime();
                int l = (int) (restTimeTmp / 1000);
                int mins = (l / 60);
                int secs = (l % 60);

                changeMins.setText("" + mins);
                changeSecs.setText("" + secs);
                typeOfChange.setText("Rest");

                savePopChanges.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();


                        if(changeMins.getText().toString() == "")
                            newMins = 0;
                        else
                            newMins = Integer.parseInt(changeMins.getText().toString());

                        if(changeSecs.getText().toString() == "")
                            newSecs = 0;
                        else
                            newSecs = Integer.parseInt(changeSecs.getText().toString());

                        restTimeTmp = ((newMins*60) + (newSecs)) * 1000;
                        setTimeLabel(restTimeTmp, restTimeDisplay);
                    }
                });
            }
        });

        rbcLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTime();
                int l = (int) (rbcTmp / 1000);
                int mins = (l / 60);
                int secs = (l % 60);

                changeMins.setText("" + mins);
                changeSecs.setText("" + secs);
                typeOfChange.setText("Rest Between Cycles");

                savePopChanges.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();


                        if(changeMins.getText().toString() == "")
                            newMins = 0;
                        else
                            newMins = Integer.parseInt(changeMins.getText().toString());

                        if(changeSecs.getText().toString() == "")
                            newSecs = 0;
                        else
                            newSecs = Integer.parseInt(changeSecs.getText().toString());

                        rbcTmp = ((newMins*60) + (newSecs)) * 1000;
                        setTimeLabel(rbcTmp, rBCDisplay);
                    }
                });
            }
        });

        roundsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeRC();
                typeOfChangeRC.setText("ROUNDS");
                typeOfChangeRCLabel.setText("Number of Rounds");

                changerc.setText("" + roundsTmp);
                changerc.setHint("Rounds");

                savePopChangesRC.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();

                        if(changerc.getText().toString() == "")
                            roundsTmp = 1;
                        else
                            roundsTmp = Integer.parseInt(changerc.getText().toString());

                        setRCLabel(roundsTmp, roundsDisplay);
                    }
                });
            }
        });

        cyclesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeRC();
                typeOfChangeRC.setText("CYCLES");
                typeOfChangeRCLabel.setText("Number of Cycles");

                changerc.setText("" + cyclesTmp);
                changerc.setHint("Cycles");

                savePopChangesRC.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();

                        if(changerc.getText().toString() == "")
                            cyclesTmp = 1;
                        else
                            cyclesTmp = Integer.parseInt(changerc.getText().toString());

                        setRCLabel(cyclesTmp, cyclesDisplay);
                    }
                });
            }
        });
    }

    private void setLabels() {
        setTimeLabel(prepareTimeVar, prepareTimeDisplay);
        setTimeLabel(workTimeVar, workTimeDisplay);
        setTimeLabel(restTimeVar, restTimeDisplay);
        setRCLabel(roundsVar, roundsDisplay);
        setRCLabel(cyclesVar, cyclesDisplay);
        setTimeLabel(rbcVar, rBCDisplay);
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

    private void back() {
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void changeTime() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View changeTimePopUp = getLayoutInflater().inflate(R.layout.activity_changetime, null);

        typeOfChange = changeTimePopUp.findViewById(R.id.typeTextLabel);

        changeMins = changeTimePopUp.findViewById(R.id.minsEditText);
        changeSecs = changeTimePopUp.findViewById(R.id.secsEditText);

        savePopChanges = changeTimePopUp.findViewById(R.id.popUpSaveButton);
        cancelPopChanges = changeTimePopUp.findViewById(R.id.popUpCancelButton);

        dialogBuilder.setView(changeTimePopUp);
        dialog = dialogBuilder.create();

        dialog.show();

        cancelPopChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    public void changeRC() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View changeRCPopUp = getLayoutInflater().inflate(R.layout.activity_changerc, null);

        typeOfChangeRC = changeRCPopUp.findViewById(R.id.typeTextLabelRC);
        typeOfChangeRCLabel = changeRCPopUp.findViewById(R.id.rcTextLabel);

        changerc = changeRCPopUp.findViewById(R.id.rcEditText);

        savePopChangesRC = changeRCPopUp.findViewById(R.id.popUpSaveButtonRC);
        cancelPopChangesRC = changeRCPopUp.findViewById(R.id.popUpCancelButtonRC);

        dialogBuilder.setView(changeRCPopUp);
        dialog = dialogBuilder.create();

        dialog.show();

        cancelPopChangesRC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}