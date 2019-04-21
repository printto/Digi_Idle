package com.printto.printmov.digi_idle.utils;

import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.*;
import android.widget.Button;
import android.widget.TextView;

import com.printto.printmov.digi_idle.R;
import com.printto.printmov.digi_idle.stepscounter.StepDetector;
import com.printto.printmov.digi_idle.stepscounter.StepListener;

/**
 * This class is used for testing step count
 */
public class StepTestActivity extends AppCompatActivity implements SensorEventListener, StepListener {
    private TextView TvSteps;
    private StepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;
    private static final String TEXT_NUM_STEPS = "Number of Steps: ";
    private int numSteps;

    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_test);


        // Get an instance of the SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener(this);

        TvSteps = (TextView) findViewById(R.id.tv_steps);
        numSteps = 0;
        sensorManager.registerListener(StepTestActivity.this, accel, SensorManager.SENSOR_DELAY_FASTEST);

        mp = MediaPlayer.create(this, R.raw.digitama01);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            simpleStepDetector.updateAccel(
                    event.timestamp, event.values[0], event.values[1], event.values[2]);
        }
    }

    @Override
    public void step(long timeNs) {
        numSteps++;
        mp.start();
        TvSteps.setText(TEXT_NUM_STEPS + numSteps);
    }

    @Override
    public void finish(){
        sensorManager.unregisterListener(StepTestActivity.this, accel);
        super.finish();
    }

}