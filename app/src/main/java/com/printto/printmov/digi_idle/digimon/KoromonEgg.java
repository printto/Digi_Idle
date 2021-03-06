package com.printto.printmov.digi_idle.digimon;

import com.printto.printmov.digi_idle.R;
import com.printto.printmov.digi_idle.values.DigimonForms;

public class KoromonEgg extends Digimon {

    @Override
    protected void initializeInformationsOnCreate() {
        name = "Koromon Egg";
        modeCount = 1;
        form = DigimonForms.EGG;
    }

    @Override
    public void renderAnimation(int mode) {
        switch(mode){
            default:
                activity.setWalkerPic(R.drawable.redegg_ani);
        }
    }

    @Override
    protected void walkWalker(float toWalkX, float toWalkY){ }

    @Override
    public int getProfilePic() {
        return R.drawable.eggprofile;
    }

}
