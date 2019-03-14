package com.printto.printmov.digi_idle;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.printto.printmov.digi_idle.digimon.Digimon;
import com.printto.printmov.digi_idle.utils.SaveManager;
import com.printto.printmov.digi_idle.utils.WalkEngine;

public class MainActivity extends AppCompatActivity implements MainActivityController {

    SaveManager saveManager;

    Digimon digimon;
    Player player;

    ImageView walker;
    ImageView profilePic;
    TextView nameText;

    WalkEngine walkEngine;
    AnimationDrawable walkerAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        walker = findViewById(R.id.walker);
        profilePic = findViewById(R.id.profileImage);
        nameText = findViewById(R.id.nameText);

        saveManager = new SaveManager();

        if(saveManager.loadState()){
            digimon = saveManager.getDigimon();
            player = saveManager.getPlayer();
            nameText.setText(player.getName());
            profilePic.setImageResource(digimon.getProfilePic());
            float sizeX = (getApplicationContext().getResources().getDisplayMetrics().widthPixels / 2) - 40;
            float sizeY = (getApplicationContext().getResources().getDisplayMetrics().heightPixels / 2) - 40;
            Log.d("Initialize Size", sizeX+","+sizeY);
            digimon.initializeScreen(this, sizeX, sizeY);
            walkEngine = new WalkEngine(digimon);
            walkEngine.walk();
        }
        else{
            Intent intent = new Intent(this, CreateDigimonActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void statusBtnClicked(View view){

    }

    public void feedBtnClicked(View view){

    }

    public void fightBtnClicked(View view){

    }

    public void sleepBtnClicked(View view){

    }

    @Override
    public void walkWalker(float toWalkX, float toWalkY) {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(walker, "translationX", toWalkX);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(walker, "translationY", toWalkY);
        final AnimatorSet animSet = new AnimatorSet();
        animSet.playTogether(animatorX, animatorY);
        animSet.setDuration(4950);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override public void run() {
                animSet.start();
            }
        });
    }

    @Override
    public void setWalkerPic(int resID) {
        walker.setBackgroundResource(resID);
        walkerAnimation = (AnimationDrawable) walker.getBackground();
        walkerAnimation.start();
    }

    @Override
    public float getTranslationX() {
        return walker.getTranslationX();
    }

    @Override
    public float getTranslationY() {
        return walker.getTranslationY();
    }
}
