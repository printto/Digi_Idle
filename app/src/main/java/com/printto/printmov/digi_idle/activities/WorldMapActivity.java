package com.printto.printmov.digi_idle.activities;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.printto.printmov.digi_idle.R;

public class WorldMapActivity extends AppCompatActivity {

    Button cityMapButton;
    Button trainMapButton;
    Button battleMapButton;
    Button huntMapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world_map);

        setTitle("World-map");

        cityMapButton = findViewById(R.id.cityMapButton);
        trainMapButton = findViewById(R.id.trainMapButton);
        battleMapButton = findViewById(R.id.battleMapButton);
        huntMapButton = findViewById(R.id.huntMapButton);

        AnimationDrawable[] buttonDrawables = {
                (AnimationDrawable) cityMapButton.getBackground()
                ,(AnimationDrawable) trainMapButton.getBackground()
                ,(AnimationDrawable) battleMapButton.getBackground()
                ,(AnimationDrawable) huntMapButton.getBackground()};

        for(final AnimationDrawable background : buttonDrawables){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    background.start();
                }
            });
        }

    }
}
