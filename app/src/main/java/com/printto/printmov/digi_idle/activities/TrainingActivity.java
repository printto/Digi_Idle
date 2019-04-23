package com.printto.printmov.digi_idle.activities;

import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.printto.printmov.digi_idle.Player;
import com.printto.printmov.digi_idle.R;
import com.printto.printmov.digi_idle.digimon.Digimon;
import com.printto.printmov.digi_idle.stepscounter.StepDetector;
import com.printto.printmov.digi_idle.stepscounter.StepListener;
import com.printto.printmov.digi_idle.utils.SaveManager;
import com.printto.printmov.digi_idle.values.DigimonForms;
import com.printto.printmov.digi_idle.values.LevelExpCap;

import java.util.Timer;
import java.util.TimerTask;

public class TrainingActivity extends AppCompatActivity implements SensorEventListener, StepListener {

    private Digimon digimon;
    private Player player;
    private SaveManager saveManager;

    private TextView countText;
    private TextView statusText;
    private StepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;
    private Timer timer;
    boolean slidingup = true;

    private int numSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        countText = findViewById(R.id.stepCountView);
        statusText = findViewById(R.id.statusText);

        AnimationDrawable walkerAnimation = (AnimationDrawable) countText.getBackground();
        walkerAnimation.start();

        setTitle("Training mode");

        initSensor();

        final ImageView profilePic = findViewById(R.id.profilePic);

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                final ObjectAnimator animatorY;
                if(slidingup){
                    animatorY = ObjectAnimator.ofFloat(profilePic, "translationY", 20);
                }
                else {
                    animatorY = ObjectAnimator.ofFloat(profilePic, "translationY", -20);
                }
                animatorY.setDuration(1990);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        animatorY.start();
                    }
                });
                slidingup = !slidingup;
            }
        },0,2000);

        saveManager = new SaveManager();
        saveManager.loadState();
        player = saveManager.getPlayer();
        digimon = saveManager.getDigimon();
        profilePic.setImageResource(digimon.getProfilePic());

        initStatus();

    }

    private void initStatus() {
        saveManager.loadState();
        player = saveManager.getPlayer();
        statusText.setText("Level: "+player.getLevel()+"\nEXP: "+player.getExp()+" / "+ LevelExpCap.getCap(player.getLevel()) +"\nPoints: "+player.getPoints());
    }

    private void initSensor() {
        // Get an instance of the SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener(this);
        numSteps = 0;
        sensorManager.registerListener(TrainingActivity.this, accel, SensorManager.SENSOR_DELAY_FASTEST);
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
        countText.setText(++numSteps+"\nStep");
        if(numSteps > 1){
            countText.setText(numSteps+"\nSteps");
        }
        if(numSteps % 10 == 0){
            player.setExp(player.getExp() + 1);
            if(player.getExp() >= LevelExpCap.getCap(player.getLevel())){
                player.setLevel(player.getLevel() + 1);
                player.setExp(0);
                player.setPoints(player.getPoints() + 10);
            }
            saveManager.saveState(digimon, player);
            initStatus();
        }
        //TODO Reduce the energy somehow
    }

    @Override
    public void finish(){
        sensorManager.unregisterListener(TrainingActivity.this, accel);
        timer.cancel();
        super.finish();
    }

}
