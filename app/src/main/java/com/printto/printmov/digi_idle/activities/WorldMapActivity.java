package com.printto.printmov.digi_idle.activities;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.printto.printmov.digi_idle.R;
import com.printto.printmov.digi_idle.utils.SaveEditorDebug;

public class WorldMapActivity extends AppCompatActivity {

    Button cityMapButton;
    Button trainMapButton;
    Button battleMapButton;
    Button huntMapButton;

    AppCompatActivity thisActivity;

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

        thisActivity = this;

        trainMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(thisActivity,TrainingActivity.class);
                startActivity(intent);
            }
        });

    }
}
