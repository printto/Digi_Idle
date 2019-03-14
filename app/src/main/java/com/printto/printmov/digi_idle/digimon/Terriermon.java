package com.printto.printmov.digi_idle.digimon;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;

import com.printto.printmov.digi_idle.R;

public class Terriermon extends Digimon {

    public Terriermon(){
        super();
        name = "Terriermon";
        modeCount = 10;
    }

    @Override
    public void renderAnimation(int mode) {
        switch(mode){
            case 0:
                activity.setWalkerPic(R.drawable.ani_downleft);
                break;
            case 1:
                activity.setWalkerPic(R.drawable.ani_downright);
                break;
            case 2:
                activity.setWalkerPic(R.drawable.ani_upleft);
                break;
            case 3:
                activity.setWalkerPic(R.drawable.ani_upright);
                break;
            case 4:
                activity.setWalkerPic(R.drawable.ani_downleft_idle);
                break;
            case 5:
                activity.setWalkerPic(R.drawable.ani_downright_idle);
                break;
            case 6:
                activity.setWalkerPic(R.drawable.ani_upleft_idle);
                break;
            case 7:
                activity.setWalkerPic(R.drawable.ani_upright_idle);
                break;
            case 8:
                activity.setWalkerPic(R.drawable.ani_sit);
                break;
            case 9:
                activity.setWalkerPic(R.drawable.ani_jump);
                break;
        }
    }

    @Override
    public int getProfilePic() {
        return R.drawable.terriermon;
    }
}
