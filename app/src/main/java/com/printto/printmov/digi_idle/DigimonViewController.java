package com.printto.printmov.digi_idle;

public interface DigimonViewController {

    public void walkWalker(float toWalkX, float toWalkY);
    public void setWalkerPic(int resID);

    public float getTranslationX();
    public float getTranslationY();

}
