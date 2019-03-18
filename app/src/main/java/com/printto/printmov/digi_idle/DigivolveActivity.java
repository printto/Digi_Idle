package com.printto.printmov.digi_idle;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class DigivolveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digivolve);

        ImageView bgOverlay = findViewById(R.id.bgOverlay);
        AnimationDrawable walkerAnimation = (AnimationDrawable) bgOverlay.getDrawable();
        walkerAnimation.start();
    }
}
