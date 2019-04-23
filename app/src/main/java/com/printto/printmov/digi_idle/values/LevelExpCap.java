package com.printto.printmov.digi_idle.values;

public class LevelExpCap {

    public static LevelExpCap instance = new LevelExpCap();

    public static int getCap(int level){
        if(level <= 10){
            return 10;
        }
        else if(level <= 20){
            return 20;
        }
        else if(level <= 30){
            return 30;
        }
        else if(level <= 40){
            return 40;
        }
        else {
            return 50;
        }
    }

}
