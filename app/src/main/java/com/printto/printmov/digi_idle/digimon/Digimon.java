package com.printto.printmov.digi_idle.digimon;

import android.util.Log;

import com.printto.printmov.digi_idle.MainActivityController;
import com.printto.printmov.digi_idle.food.Food;
import com.printto.printmov.digi_idle.utils.WalkingModes;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

public abstract class Digimon implements Serializable {

    final int MIN_WALK_DISTANCE = 0;
    final int MAX_WALK_DISTANCE = 300;

    String name = "";
    int modeCount = 8;

    int attack = 0;
    int defense = 0;
    int maxFullness = 60;
    int maxEnergy = 10;

    int fullness = 60;
    int energy = 10;
    boolean isSick = false;
    boolean isSleeping = false;

    Date lastFeed;
    Date lastEnergized;
    Date birth;

    Random random = new Random();

    float screenSizeX = 0;
    float screenSizeY = 0;

    MainActivityController activity;

    /**
     * Initialize the screen size and walker view.
     * @param activity UI activity that Digimon is on
     */
    public void initializeScreen(MainActivityController activity, float screenSizeX, float screenSizeY){
        this.activity = activity;
        this.screenSizeX = screenSizeX;
        this.screenSizeY = screenSizeY;
    }

    /**
     * Render animation of the Digimon.
     * @param mode
     */
    public abstract void renderAnimation(int mode);

    /**
     * Get profile picture of the Digimon to use with ImageView
     * @return profilePic Profile picture of the Digimon
     */
    public abstract int getProfilePic();

    public void generateWalk(int mode){
        float randomXY = MIN_WALK_DISTANCE + random.nextFloat() * (MAX_WALK_DISTANCE - MIN_WALK_DISTANCE);
        Log.d("Walk distance", "randomed: "+randomXY);
        float randomX = randomXY;
        float randomY = randomXY;
        float currentX = activity.getTranslationX();
        float currentY = activity.getTranslationY();
        Log.d("Current X", "Current X position: "+currentX);
        Log.d("Current Y", "Current Y position: "+currentY);
        float toWalkX = 0;
        float toWalkY = 0;
        switch(mode){
            case WalkingModes.DOWN_LEFT_WALK:
                //Downleft
                toWalkX = currentX - randomX;
                toWalkY = currentY + randomY;
                if(toWalkX < -screenSizeX){
                    toWalkX = -screenSizeX;
                }
                if(toWalkY > screenSizeY){
                    toWalkY = screenSizeY;
                }
                break;
            case WalkingModes.DOWN_RIGHT_WALK:
                //Downright
                toWalkX = currentX + randomX;
                toWalkY = currentY + randomY;
                if(toWalkX > screenSizeX){
                    toWalkX = screenSizeX;
                }
                if(toWalkY > screenSizeY){
                    toWalkY = screenSizeY;
                }
                break;
            case WalkingModes.UP_LEFT_WALK:
                //Upleft
                toWalkX = currentX - randomX;
                toWalkY = currentY - randomY;
                if(toWalkX < -screenSizeX){
                    toWalkX = -screenSizeX;
                }
                if(toWalkY < -screenSizeY){
                    toWalkY = -screenSizeY;
                }
                break;
            case WalkingModes.UP_RIGHT_WALK:
                //Upright
                toWalkX = currentX + randomX;
                toWalkY = currentY - randomY;
                if(toWalkX > screenSizeX){
                    toWalkX = screenSizeX;
                }
                if(toWalkY < -screenSizeY){
                    toWalkY = -screenSizeY;
                }
                break;
            default:
                toWalkX = currentX;
                toWalkY = currentY;
                break;
        }
        Log.d("Walk distance", "toWalkX: "+toWalkX);
        Log.d("Walk distance", "toWalkY: "+toWalkY);
        Log.d("Screen Size", "Screen size X: "+screenSizeX);
        Log.d("Screen Size", "Screen size Y: "+screenSizeY);
        walkWalker(toWalkX, toWalkY);
    }

    private void walkWalker(float toWalkX, float toWalkY){
        activity.walkWalker(toWalkX, toWalkY);
    }

    /**
     * Increase Digimon stats through the item or food.
     * @param food Item to give to digimon
     */
    public void feed(Food food){
        if(food.fullness != 0){
            if(this.fullness + food.fullness > maxFullness){
                this.fullness = maxFullness;
            }
            else {
                this.fullness += food.fullness;
            }
            lastFeed = new Date();
        }
        this.energy += food.energy;
        this.attack += food.attack;
        this.defense += food.defense;
        this.maxFullness += food.maxFullness;
    }

    /**
     * Check if Digimon is full.
     * @return Digimon is full
     */
    public boolean isFull(){
        return fullness >= maxFullness;
    }

//    public boolean isEgg(){
//        if( new Date().getTime() - birth.getTime() <= 300000 ) return true;
//        return false;
//    }

    /**
     * Get age of the Digimon.
     * @return Age in Milliseconds
     */
    public long getAge(){
        return new Date().getTime() - birth.getTime();
    }

    public int getModeCount() {
        return modeCount;
    }
}
