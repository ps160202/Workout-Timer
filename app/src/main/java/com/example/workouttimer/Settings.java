package com.example.workouttimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.slider.Slider;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class Settings extends AppCompatActivity {

    private MaterialButton backButton;
    private SwitchMaterial soundOnSwitch;
    private SwitchMaterial vibrationOnSwitch;
    private RelativeLayout soundScheme;
    private Slider volumeSlider;

    private static boolean soundOn = true;
    private static boolean vibrationOn = false;
    private static float volume = 70.0F;

    SharedPreferences sp;

    public static boolean isSoundOn() {
        return soundOn;
    }

    public static boolean isVibrationOn() {
        return vibrationOn;
    }

    public static float getVolume() {
        return volume;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        backButton = findViewById(R.id.backButton);
        soundOnSwitch = findViewById(R.id.soundOnSwitch);
        vibrationOnSwitch = findViewById(R.id.vibrationOnSwitch);
        soundScheme = findViewById(R.id.soundOptionsLayout);
        volumeSlider = findViewById(R.id.volumeSlider);

        sp = getSharedPreferences("WorkoutTimerSettings", Context.MODE_PRIVATE);
        SharedPreferences setPreferences = getApplicationContext().getSharedPreferences("WorkoutTimerSettings", Context.MODE_PRIVATE);


        soundOnSwitch.setChecked(setPreferences.getBoolean("SoundOn", true));
        vibrationOnSwitch.setChecked(setPreferences.getBoolean("VibrationOn", true));
        volumeSlider.setValue(setPreferences.getFloat("Volume", 0.0f));


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });
    }

    private void back() {
        SharedPreferences.Editor editor = sp.edit();

        editor.putBoolean("SoundOn", soundOnSwitch.isChecked());
        editor.putBoolean("VibrationOn", vibrationOnSwitch.isChecked());
        editor.putFloat("Volume", volumeSlider.getValue());
        editor.commit();

        /*
        volume = volumeSlider.getValue();
        soundOn = soundOnSwitch.isChecked();
        vibrationOn = vibrationOnSwitch.isChecked();
        */

        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
