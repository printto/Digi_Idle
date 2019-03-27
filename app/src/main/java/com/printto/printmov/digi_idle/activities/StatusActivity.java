package com.printto.printmov.digi_idle.activities;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.printto.printmov.digi_idle.Player;
import com.printto.printmov.digi_idle.R;
import com.printto.printmov.digi_idle.digimon.Digimon;
import com.printto.printmov.digi_idle.utils.DigimonForms;
import com.printto.printmov.digi_idle.utils.SaveManager;
import com.printto.printmov.digi_idle.utils.WalkEngine;

public class StatusActivity extends AppCompatActivity implements DigimonViewController {

    Digimon digimon;
    Player player;

    TextView textView;
    ImageView walker;

    AnimationDrawable walkerAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        SaveManager save = new SaveManager();
        save.loadState();
        digimon = save.getDigimon();
        player = save.getPlayer();
        float sizeX = 50;
        digimon.initializeWalkingArea(this, sizeX,10);
        setTitle(player.getName() + "'s Status");

        textView = findViewById(R.id.textView);
        walker = findViewById(R.id.walker);

        textView.setMovementMethod(new ScrollingMovementMethod());

        WalkEngine walkEngine = new WalkEngine(digimon);
        walkEngine.walk();

        textView.setText("Partner: "+digimon.getName()
                +"\n\n Fullness: "+digimon.getFullness() + "/" + digimon.getMaxFullness()
                +"\n\n HP: "+digimon.getHp() + "/" + digimon.getMaxHp()
                +"\n Attack: "+digimon.getAttack()
                +"\n Defense: "+digimon.getDefense()
//                +"\n\n Birth: "+digimon.getBirth()
                +"\n\n Age: "+digimon.getAgeString()
                +"\n\n Energy: "+digimon.getEnergy() + "/" + digimon.getMaxEnergy()
                +"\n\n Level: "+player.getLevel()
                +"\n Exp: "+player.getExp());

        if(digimon.getForm() == DigimonForms.INTRAINING){
            Button digivolveBtn = findViewById(R.id.digivolveBtn);
            digivolveBtn.setText("Hatch");
        }

    }

    public void onBackBtnClicked(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void onDigivolveClicked(View view){
        Intent intent = new Intent(this, DigivolveActivity.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void walkWalker(float toWalkX, float toWalkY) {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(walker, "translationX", toWalkX);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(walker, "translationY", toWalkY);
        final AnimatorSet animSet = new AnimatorSet();
        animSet.playTogether(animatorX, animatorY);
        animSet.setDuration(2950);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override public void run() {
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
    public void onBackPressed () {

    }

}
