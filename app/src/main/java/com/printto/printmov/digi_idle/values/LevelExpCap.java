package com.printto.printmov.digi_idle.values;

public class LevelExpCap {

    public static LevelExpCap instance = new LevelExpCap();

    public int getCap(int level){
        if(level <= 10){
            return 30;
        }
        else if(level <= 20){
            return 50;
        }
        else if(level <= 30){
            return 100;
        }
        else {
            return 200;
        }
    }

}
