package com.example.workouttimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

        soundOnSwitch.setChecked(soundOn);
        vibrationOnSwitch.setChecked(vibrationOn);
        volumeSlider.setValue(volume);


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });
    }

    private void back() {
        volume = volumeSlider.getValue();
        soundOn = soundOnSwitch.isChecked();
        vibrationOn = vibrationOnSwitch.isChecked();
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
