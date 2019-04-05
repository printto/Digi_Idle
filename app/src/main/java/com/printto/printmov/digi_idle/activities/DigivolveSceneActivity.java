package com.printto.printmov.digi_idle.activities;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.printto.printmov.digi_idle.Player;
import com.printto.printmov.digi_idle.R;
import com.printto.printmov.digi_idle.digimon.Digimon;
import com.printto.printmov.digi_idle.digimon.DigimonFactory;
import com.printto.printmov.digi_idle.utils.DigimonArrayAdapterKotlin;
import com.printto.printmov.digi_idle.utils.SaveManager;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class DigivolveSceneActivity extends AppCompatActivity {

    ImageView profilePic;
    Digimon digimon;
    Player player;
    SaveManager saveManager;

    boolean slidingup = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digivolve);

        ImageView bgOverlay = findViewById(R.id.bgOverlay);
        AnimationDrawable overlayAnimation = (AnimationDrawable) bgOverlay.getDrawable();
        overlayAnimation.start();

        saveManager = new SaveManager();
        saveManager.loadState();
        digimon = saveManager.getDigimon();

        profilePic = findViewById(R.id.profilePic);
        profilePic.setImageResource(digimon.getProfilePic());

        Timer timer = new Timer();
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

    }

    @Override
    public void onBackPressed () {

    }

}
