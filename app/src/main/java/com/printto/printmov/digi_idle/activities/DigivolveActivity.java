package com.printto.printmov.digi_idle.activities;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.printto.printmov.digi_idle.Player;
import com.printto.printmov.digi_idle.R;
import com.printto.printmov.digi_idle.digimon.Digimon;
import com.printto.printmov.digi_idle.digimon.DigimonFactory;
import com.printto.printmov.digi_idle.adapters.DigimonArrayAdapterKotlin;
import com.printto.printmov.digi_idle.utils.SaveManager;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class DigivolveActivity extends AppCompatActivity {

    ImageView profilePic;
    Digimon digimon;
    Player player;
    SaveManager saveManager;
    ListView listView;

    boolean slidingup = true;

    DigimonArrayAdapterKotlin adapter = null;
    ArrayList<Digimon> nextDigimons = new ArrayList<Digimon>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digivolve);

        setTitle("Digivolve");

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

        adapter = new DigimonArrayAdapterKotlin(this, nextDigimons);
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        for(Digimon digi: DigimonFactory.getNextDigivolvableArray(digimon.getName())){
            nextDigimons.add(digi);
        }
        final AppCompatActivity digivolveActivity = this;
        listView.setOnItemClickListener( new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Digimon clicked = (Digimon) listView.getItemAtPosition(position);
                if(DigimonFactory.checkDigivolve(player, digimon, clicked.getName())){
                    Intent intent = new Intent(digivolveActivity, DigivolveSceneActivity.class);
                    intent.putExtra("currentDigimon",digimon);
                    intent.putExtra("nextDigimon", clicked);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.zoominfadein, R.anim.zoomoutfadeout);
                }
                else {
                    Toast.makeText(digivolveActivity, "Please check the requirement.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed () {
        Intent intent = new Intent(this, StatusActivity.class);
        startActivity(intent);
        this.finish();
        overridePendingTransition(R.anim.zoominfadein, R.anim.zoomoutfadeout);
    }

}
