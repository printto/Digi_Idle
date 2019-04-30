package com.printto.printmov.digi_idle.farmmap;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.printto.printmov.digi_idle.Player;
import com.printto.printmov.digi_idle.R;
import com.printto.printmov.digi_idle.activities.CreateDigimonActivity;
import com.printto.printmov.digi_idle.activities.DigimonViewController;
import com.printto.printmov.digi_idle.activities.TrainingActivity;
import com.printto.printmov.digi_idle.digimon.Digimon;
import com.printto.printmov.digi_idle.item.Item;
import com.printto.printmov.digi_idle.stepscounter.StepDetector;
import com.printto.printmov.digi_idle.stepscounter.StepListener;
import com.printto.printmov.digi_idle.utils.SaveManager;
import com.printto.printmov.digi_idle.utils.WalkEngine;

import java.util.Date;

import static java.lang.System.lineSeparator;

public abstract class FarmMap extends AppCompatActivity implements DigimonViewController, SensorEventListener, StepListener {

    final int MIN_RANDOM_STEP = 10;
    final int MAX_RANDOM_STEP = 70;

    SaveManager saveManager;

    Digimon digimon;
    Player player;

    ImageView walker;
    WalkEngine walkEngine;
    AnimationDrawable walkerAnimation;

    ImageView bg_image;
    TextView countText;
    TextView statusText;
    StepDetector simpleStepDetector;
    SensorManager sensorManager;
    Sensor accel;
    int numSteps = 0;
    int newSteps = 0;
    int nextSteps = 0;

    String mapName;
    int mapBackground;
    String desc;

    String gotItems = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_map);

        walker = findViewById(R.id.walker);
        saveManager = new SaveManager();

        if (saveManager.loadState()) {
            digimon = saveManager.getDigimon();
            player = saveManager.getPlayer();
            float sizeX = (getApplicationContext().getResources().getDisplayMetrics().widthPixels / 2) - 40;
            float sizeY = (getApplicationContext().getResources().getDisplayMetrics().heightPixels / 2) - 500;
            Log.d("Initialize Size", sizeX + "," + sizeY);
            digimon.initializeWalkingArea(this, sizeX, sizeY);
            walkEngine = new WalkEngine(digimon);
            walkEngine.walk();
        } else {
            Intent intent = new Intent(this, CreateDigimonActivity.class);
            startActivity(intent);
            finish();
        }

        nextSteps = (int) (Math.random() * ((MAX_RANDOM_STEP - MIN_RANDOM_STEP) + 1)) + MIN_RANDOM_STEP;

        countText = findViewById(R.id.stepCountView);
        statusText = findViewById(R.id.statusText);
        bg_image = findViewById(R.id.bg);

        AnimationDrawable walkerAnimation = (AnimationDrawable) countText.getBackground();
        walkerAnimation.start();

        statusText.setMovementMethod(new ScrollingMovementMethod());

        initSensor();

        mapName = "Loading...";
        mapBackground = R.drawable.ch_9999;

        Log.d(getMapName(), getBackgroundResource() + "");

        mapName = getMapName();
        mapBackground = getBackgroundResource();
        desc = getDescription();

        setTitle( mapName );
        bg_image.setImageResource( mapBackground );

        initStatus();

    }

    private void initSensor() {
        // Get an instance of the SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener(this);
        numSteps = 0;
        sensorManager.registerListener(FarmMap.this, accel, SensorManager.SENSOR_DELAY_FASTEST);
    }

    /**
     * Init map name.
     */
    abstract public String getMapName();

    /**
     * Init map background resource.
     */
    abstract public int getBackgroundResource();

    /**
     * Get obtainable items from the map.
     * @return Array of obtainable items.
     */
    abstract public Item[] getItemArray();

    /**
     * Get description of the map.
     */
    public abstract String getDescription();

    @Override
    public void walkWalker(float toWalkX, float toWalkY) {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(walker, "translationX", toWalkX);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(walker, "translationY", toWalkY);
        final AnimatorSet animSet = new AnimatorSet();
        animSet.playTogether(animatorX, animatorY);
        animSet.setDuration(walkEngine.getDuration() - 10);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                animSet.start();
            }
        });
    }

    @Override
    public void setWalkerPic(int resID) {
        runOnUiThread(new AnimateWalker(resID));
    }

    @Override
    public float getTranslationX() {
        return walker.getTranslationX();
    }

    @Override
    public float getTranslationY() {
        return walker.getTranslationY();
    }

    class AnimateWalker implements Runnable {
        int resID;
        public AnimateWalker(int resID) {
            this.resID = resID;
        }
        @Override
        public void run() {
            walker.setImageResource(resID);
            walkerAnimation = (AnimationDrawable) walker.getDrawable();
            walkerAnimation.start();
        }
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

    private void initStatus() {
        saveManager.loadState();
        player = saveManager.getPlayer();
        digimon = saveManager.getDigimon();
        statusText.setText("Energy: "+digimon.getEnergy()+"/"+digimon.getMaxEnergy() + "\nBalance: " + player.getBalance() + lineSeparator() + gotItems);
    }

    @Override
    public void step(long timeNs) {
        if(digimon.getEnergy() > 0) {
            countText.setText(++numSteps + "\nStep");
            newSteps++;
            if (numSteps > 1) {
                countText.setText(numSteps + "\nSteps");
            }
            if (newSteps >= nextSteps) {
                nextSteps = (int) (Math.random() * ((MAX_RANDOM_STEP - MIN_RANDOM_STEP) + 1)) + MIN_RANDOM_STEP;
                int randomed = (int) (Math.random() * (getItemArray().length + 1));
                if(randomed >= getItemArray().length) {
                    int randomedBalance = (int) (Math.random() * 1000);
                    player.setBalance(player.getBalance() + randomedBalance);
                    gotItems = "["+numSteps+"] Got "+randomedBalance+" Bits\n" + gotItems;
                    Toast.makeText(this,"Got "+randomedBalance+" Bits",Toast.LENGTH_SHORT).show();
                }
                else {
                    player.addItem(getItemArray()[randomed]);
                    gotItems = "["+numSteps+"] Got 1 "+getItemArray()[randomed]+"\n" + gotItems;
                    Toast.makeText(this,"Got 1 "+getItemArray()[randomed],Toast.LENGTH_SHORT).show();
                }
                saveManager.saveState(digimon, player);
                newSteps = 0;
            }
            if(numSteps % 2 == 0){
                digimon.setEnergy(digimon.getEnergy() - 1);
                digimon.setLastEnergized(new Date());
            }
            saveManager.saveState(digimon);
            initStatus();
        }
        if(digimon.getEnergy() <= 0){
            String next = "<br/><font color='#EE0000'>Energy: "+digimon.getEnergy()+" / "+digimon.getMaxEnergy()+"</font>" + "\nBalance: " + player.getBalance() + lineSeparator() + gotItems;
            initStatus();
            statusText.setText(Html.fromHtml(next));
        }
    }

    @Override
    public void finish(){
        sensorManager.unregisterListener(FarmMap.this, accel);
        super.finish();
    }

}
