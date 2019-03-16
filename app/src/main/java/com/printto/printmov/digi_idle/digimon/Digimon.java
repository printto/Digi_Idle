package com.printto.printmov.digi_idle.digimon;

import android.util.Log;

import com.printto.printmov.digi_idle.DigimonViewController;
import com.printto.printmov.digi_idle.food.Food;
import com.printto.printmov.digi_idle.utils.WalkingModes;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

public abstract class Digimon implements Serializable {

    final int MIN_WALK_DISTANCE = 10;
    final int MAX_WALK_DISTANCE = 150;

    String name = "";
    int modeCount = 10;

    int attack = 0;
    int defense = 0;
    int hp = 5;
    int maxHp = 5;
    int maxFullness = 60;
    int maxEnergy = 10;

    int fullness = 60;
    int energy = 10;
    int mood = 10;

    boolean isSick = false;
    boolean isSleeping = false;
    boolean isDied = false;

    Date lastFeed;
    Date lastEnergized;
    Date birth;

    Random random = new Random();

    float screenSizeX = 0;
    float screenSizeY = 0;

    DigimonViewController activity;

    public void setStatus(int attack, int defense, int maxHp, int maxEnergy, int maxFullness, Date lastFeed, Date lastEnergized, Date birth){
        this.attack = attack;
        this.defense = defense;
        this.maxHp = maxHp;
        this.maxEnergy = maxEnergy;
        this.lastFeed = lastFeed;
        this.lastEnergized = lastEnergized;
        this.birth = birth;
        this.maxFullness = maxFullness;
        initializeInformationsOnCreate();
    }

    protected Digimon(){
        lastFeed = new Date();
        lastEnergized = new Date();
        birth = new Date();
        initializeInformationsOnCreate();
    }

    protected abstract void initializeInformationsOnCreate();

    /**
     * Initialize the screen size and walker view.
     * @param activity UI activity that Digimon is on
     */
    public void initializeWalkingArea(DigimonViewController activity, float screenSizeX, float screenSizeY){
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
        randomXY = MIN_WALK_DISTANCE + random.nextFloat() * (MAX_WALK_DISTANCE - MIN_WALK_DISTANCE);
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

    protected void walkWalker(float toWalkX, float toWalkY){
        activity.walkWalker(toWalkX, toWalkY);
    }

    /**
     * Increase Digimon stats through the item or food.
     * @param food Item to give to digimon
     */
    public void feed(Food food){
        if(food.getFullness() != 0){
            if(this.fullness + food.getFullness() > maxFullness){
                this.fullness = maxFullness;
            }
            else {
                this.fullness += food.getFullness();
            }
            lastFeed = new Date();
        }
        this.energy += food.getEnergy();
        this.attack += food.getAttack();
        this.defense += food.getDefense();
        if(this.hp + food.getHp() > maxHp + food.getMaxHp()){
            this.hp = maxHp + food.getMaxHp();
        }
        else{
            this.hp += food.getHp();
        }
        this.maxHp += food.getMaxHp();
        this.maxFullness += food.getMaxFullness();
    }

    /**
     * Check if Digimon is full.
     * @return Digimon is full
     */
    public boolean isFull(){
        return fullness >= maxFullness;
    }

//    public boolean isEgg(){
//        if( new Date().getTime() - birth.getTime() <= 100000 ) return true;
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

    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getMaxFullness() {
        return maxFullness;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public int getFullness() {
        return fullness;
    }

    public int getEnergy() {
        return energy;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public boolean isSick() {
        return isSick;
    }

    public boolean isSleeping() {
        return isSleeping;
    }

    public Date getLastFeed() {
        return lastFeed;
    }

    public Date getLastEnergized() {
        return lastEnergized;
    }

    public Date getBirth() {
        return birth;
    }

}
