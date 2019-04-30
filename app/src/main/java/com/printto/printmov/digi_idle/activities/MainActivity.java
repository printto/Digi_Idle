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
import android.widget.Toast;

import com.printto.printmov.digi_idle.Player;
import com.printto.printmov.digi_idle.R;
import com.printto.printmov.digi_idle.digimon.Digimon;
import com.printto.printmov.digi_idle.utils.SaveEditorDebug;
import com.printto.printmov.digi_idle.utils.SaveManager;
import com.printto.printmov.digi_idle.utils.StepTestActivity;
import com.printto.printmov.digi_idle.utils.WalkEngine;
import com.printto.printmov.digi_idle.values.DigimonForms;

public class MainActivity extends AppCompatActivity implements DigimonViewController {

    SaveManager saveManager;

    Digimon digimon;
    Player player;

    ImageView walker;
    ImageView profilePic;
    TextView nameText;

    ImageView sleepDark;
    ImageView sleepOverlay;

    WalkEngine walkEngine;
    AnimationDrawable walkerAnimation;

    boolean isSleep = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        walker = findViewById(R.id.walker);
        profilePic = findViewById(R.id.profileImage);
        nameText = findViewById(R.id.nameText);

        sleepDark = findViewById(R.id.sleepDark);
        sleepOverlay = findViewById(R.id.sleepOverlay);

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
        }else {
            Intent intent = new Intent(this, CreateDigimonActivity.class);
            intent.putExtra("alert_title","Welcome to Digi-idle!");
            intent.putExtra("alert_text","Please select your first egg!");
            startActivity(intent);
            finish();
        }
        if(digimon.isDied()) {
            Intent intent = new Intent(this, CreateDigimonActivity.class);
            intent.putExtra("alert_title","Your partner died :(");
            intent.putExtra("alert_text","Please take care of your partner next time.");
            startActivity(intent);
            finish();
        }

    }

    public void statusBtnClicked(View view) {
        Intent intent = new Intent(this, StatusActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.zoomoutfadein, R.anim.zoominfadeout);
    }

    public void feedBtnClicked(View view) {
        Intent intent = new Intent(this, FeedActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.zoomoutfadein, R.anim.zoominfadeout);
    }

    public void fightBtnClicked(View view) {
        if(digimon.getForm() != DigimonForms.EGG){
            Intent intent = new Intent(this, WorldMapActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Your partner is too weak to go out at the moment.",Toast.LENGTH_SHORT).show();
        }
    }

    //This button were changed from sleeping to Digimon Dex
    public void sleepBtnClicked(View view) {
//        isSleep = !isSleep;
//        if(isSleep){
//            sleepDark.setVisibility(View.VISIBLE);
//            sleepOverlay.setVisibility(View.VISIBLE);
//            Toast.makeText(this, "Sleeping mode currently not working.\nNo effect applied.",Toast.LENGTH_SHORT).show();
//        }
//        else{
//            sleepDark.setVisibility(View.INVISIBLE);
//            sleepOverlay.setVisibility(View.INVISIBLE);
//        }
        Intent intent = new Intent(this, DexActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.zoomoutfadein, R.anim.zoominfadeout);
    }

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

    public void onDebugButtonClicked(View view) {
        Intent intent = new Intent(this, SaveEditorDebug.class);
        startActivity(intent);
        finish();
//        Intent intent = new Intent(this, StepTestActivity.class);
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

    @Override
    public void finish(){
        if(walkEngine != null) {
            walkEngine.stop();
        }
        super.finish();
    }

}
