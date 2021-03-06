package com.printto.printmov.digi_idle.utils;

import android.util.Log;

import com.printto.printmov.digi_idle.digimon.Digimon;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class WalkEngine {

    int DURATION = 3290;

    Digimon digimon;
    Timer timer;
    Random random = new Random();

    public WalkEngine(Digimon digimon){
        this.digimon = digimon;
        timer = new Timer();
    }

    public void walk(){
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                int randomedMode = random.nextInt(digimon.getModeCount());
                digimon.renderAnimation(randomedMode);
                digimon.generateWalk(randomedMode);
            }
        },0,DURATION);
    }

    public void randomPoses(){
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                int randomedMode = random.nextInt(digimon.getModeCount());
                digimon.renderAnimation(randomedMode);
            }
        },0,DURATION);
    }

    public void stop(){
        timer.cancel();
    }

    public int getDuration() {
        return DURATION;
    }
}
