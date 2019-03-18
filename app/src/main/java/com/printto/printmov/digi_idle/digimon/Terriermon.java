package com.printto.printmov.digi_idle.digimon;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;

import com.printto.printmov.digi_idle.R;
import com.printto.printmov.digi_idle.utils.DigimonForms;
import com.printto.printmov.digi_idle.utils.WalkingModes;

import java.util.Date;

public class Terriermon extends Digimon {

    @Override
    protected void initializeInformationsOnCreate() {
        name = "Terriermon";
        form = DigimonForms.ROOKIE;
    }

    @Override
    public void renderAnimation(int mode) {
        switch(mode){
            case WalkingModes.DOWN_LEFT_WALK:
                activity.setWalkerPic(R.drawable.ani_downleft);
                break;
            case WalkingModes.DOWN_RIGHT_WALK:
                activity.setWalkerPic(R.drawable.ani_downright);
                break;
            case WalkingModes.UP_LEFT_WALK:
                activity.setWalkerPic(R.drawable.ani_upleft);
                break;
            case WalkingModes.UP_RIGHT_WALK:
                activity.setWalkerPic(R.drawable.ani_upright);
                break;
            case WalkingModes.DOWN_LEFT_IDLE:
                activity.setWalkerPic(R.drawable.ani_downleft_idle);
                break;
            case WalkingModes.DOWN_RIGHT_IDLE:
                activity.setWalkerPic(R.drawable.ani_downright_idle);
                break;
            case WalkingModes.UP_LEFT_IDLE:
                activity.setWalkerPic(R.drawable.ani_upleft_idle);
                break;
            case WalkingModes.UP_RIGHT_IDLE:
                activity.setWalkerPic(R.drawable.ani_upright_idle);
                break;
            case WalkingModes.SIT:
                activity.setWalkerPic(R.drawable.ani_sit);
                break;
            case WalkingModes.JUMP:
                activity.setWalkerPic(R.drawable.ani_jump);
                break;
            default:
                activity.setWalkerPic(R.drawable.ani_sit);
        }
    }

    @Override
    public int getProfilePic() {
        return R.drawable.terriermon;
    }
}
