package com.printto.printmov.digi_idle;

import com.printto.printmov.digi_idle.digimon.Digimon;

import java.util.Timer;
import java.util.TimerTask;

public class WalkEngine {

    Digimon digimon;
    Timer timer;

    public WalkEngine(Digimon digimon){
        this.digimon = digimon;
        timer = new Timer();
    }

    public void start(){
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                digimon.walk();
            }
        },0,5000);
    }

}
