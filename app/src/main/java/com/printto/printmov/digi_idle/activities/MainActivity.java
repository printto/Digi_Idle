package com.printto.printmov.digi_idle.activities;

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

import com.printto.printmov.digi_idle.Player;
import com.printto.printmov.digi_idle.R;
import com.printto.printmov.digi_idle.digimon.Digimon;
import com.printto.printmov.digi_idle.utils.SaveEditorDebug;
import com.printto.printmov.digi_idle.utils.SaveManager;
import com.printto.printmov.digi_idle.utils.WalkEngine;

public class MainActivity extends AppCompatActivity implements DigimonViewController {

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

        if (saveManager.loadState()) {
            digimon = saveManager.getDigimon();
            player = saveManager.getPlayer();
            nameText.setText(player.getName());
            profilePic.setImageResource(digimon.getProfilePic());
            float sizeX = (getApplicationContext().getResources().getDisplayMetrics().widthPixels / 2) - 40;
            float sizeY = (getApplicationContext().getResources().getDisplayMetrics().heightPixels / 2) - 300;
            Log.d("Initialize Size", sizeX + "," + sizeY);
            digimon.initializeWalkingArea(this, sizeX, sizeY);
            walkEngine = new WalkEngine(digimon);
            walkEngine.walk();
        } else {
            Intent intent = new Intent(this, CreateDigimonActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void statusBtnClicked(View view) {
        Intent intent = new Intent(this, StatusActivity.class);
        startActivity(intent);
        finish();
    }

    public void feedBtnClicked(View view) {

    }

    public void fightBtnClicked(View view) {

    }

    public void sleepBtnClicked(View view) {

    }

    @Override
    public void walkWalker(float toWalkX, float toWalkY) {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(walker, "translationX", toWalkX);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(walker, "translationY", toWalkY);
        final AnimatorSet animSet = new AnimatorSet();
        animSet.playTogether(animatorX, animatorY);
        animSet.setDuration(2990);
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

    public void onDebugButtonClicked(View view) {
        Intent intent = new Intent(this, SaveEditorDebug.class);
        startActivity(intent);
        finish();

//        Intent intent = new Intent(this, DigivolveActivity.class);
//        startActivity(intent);
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

}
