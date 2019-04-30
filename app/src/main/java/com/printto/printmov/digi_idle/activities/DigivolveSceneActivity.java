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
import android.widget.TextView;

import com.printto.printmov.digi_idle.Player;
import com.printto.printmov.digi_idle.R;
import com.printto.printmov.digi_idle.digimon.Digimon;
import com.printto.printmov.digi_idle.factory.DigimonFactory;
import com.printto.printmov.digi_idle.utils.SaveManager;

import java.util.Timer;
import java.util.TimerTask;

public class DigivolveSceneActivity extends AppCompatActivity {

    ImageView profilePic;
    Digimon digimon;
    Digimon nextDigimon;
    Player player;
    SaveManager saveManager;
    ImageView overlay;
    TextView digimonName;

    boolean slidingup = true;

    Timer timer = new Timer();
    int timerCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digivolve_scene);

        setTitle("Digivolve");

        ImageView bgOverlay = findViewById(R.id.bgOverlay);

        saveManager = new SaveManager();
        saveManager.loadState();
        digimon = saveManager.getDigimon();
        player = saveManager.getPlayer();

        profilePic = findViewById(R.id.profilePic);
        profilePic.setImageResource(digimon.getProfilePic());
        overlay = findViewById(R.id.overlayCutscene);
        digimonName = findViewById(R.id.digimonName);

        digimonName.setText(digimon.getName() + " digivolve to...");

        final Timer timer = new Timer();
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

        runOnUiThread(new Runnable(){
            @Override
            public void run() {
                AnimationDrawable overlayAnimater = (AnimationDrawable) overlay.getDrawable();
                overlayAnimater.start();
            }
        });

        nextDigimon = (Digimon) getIntent().getExtras().get("nextDigimon");
        player.addDigimonToDex(nextDigimon);
        saveManager.saveState(player);

        final DigivolveSceneActivity thisActivity = this;

        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                timerCount++;
                if(timerCount == 300){
                    runOnUiThread(new Runnable(){
                        @Override
                        public void run() {
                            digimonName.setVisibility(View.VISIBLE);
                        }
                    });
                }
                else if(timerCount == 2500){
                    runOnUiThread(new Runnable(){
                        @Override
                        public void run() {
                            profilePic.setImageResource(nextDigimon.getProfilePic());
                            overlay.setImageResource(R.drawable.digivolvesr);
                            AnimationDrawable overlayAnimater = (AnimationDrawable) overlay.getDrawable();
                            overlayAnimater.start();
                        }
                    });
                }
                else if(timerCount == 4000){
                    runOnUiThread(new Runnable(){
                        @Override
                        public void run() {
                            overlay.setVisibility(View.INVISIBLE);
                            digimonName.setText(nextDigimon.getName());
                            DigimonFactory.digivolve(digimon, nextDigimon.getName());
                        }
                    });
                }
                else if(timerCount >= 5000){
                    timer.cancel();
                    Intent intent = new Intent(thisActivity, DigivolveActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 0, 1);

    }

    @Override
    public void onBackPressed () {

    }

}
