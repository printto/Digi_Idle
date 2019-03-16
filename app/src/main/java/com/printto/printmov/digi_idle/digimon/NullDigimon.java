package com.printto.printmov.digi_idle.digimon;

import com.printto.printmov.digi_idle.R;

import java.util.Date;

public class NullDigimon extends Digimon {

    @Override
    protected void initializeInformationsOnCreate() {
        name = "Null Digimon";
        modeCount = 1;
    }

    @Override
    public void renderAnimation(int mode) {
        switch(mode){
            default:
                activity.setWalkerPic(R.drawable.soondigimon);
        }
    }

    @Override
    protected void walkWalker(float toWalkX, float toWalkY){ }

    @Override
    public int getProfilePic() {
        return R.drawable.soondigimon;
    }
}
