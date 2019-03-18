package com.printto.printmov.digi_idle.digimon;

import com.printto.printmov.digi_idle.R;
import com.printto.printmov.digi_idle.utils.DigimonForms;
import com.printto.printmov.digi_idle.utils.WalkingModes;

public class Greymon extends Digimon {

    @Override
    protected void initializeInformationsOnCreate() {
        name = "Greymon";
        form = DigimonForms.CHAMPION;
    }

    @Override
    public void renderAnimation(int mode) {
        switch(mode){
            case WalkingModes.DOWN_LEFT_WALK:
                activity.setWalkerPic(R.drawable.greymon_downleft);
                break;
            case WalkingModes.DOWN_RIGHT_WALK:
                activity.setWalkerPic(R.drawable.greymon_downright);
                break;
            case WalkingModes.UP_LEFT_WALK:
                activity.setWalkerPic(R.drawable.greymon_upleft);
                break;
            case WalkingModes.UP_RIGHT_WALK:
                activity.setWalkerPic(R.drawable.greymon_upright);
                break;
            case WalkingModes.DOWN_LEFT_IDLE:
                activity.setWalkerPic(R.drawable.greymon_downleft_idle);
                break;
            case WalkingModes.DOWN_RIGHT_IDLE:
                activity.setWalkerPic(R.drawable.greymon_downright_idle);
                break;
            case WalkingModes.UP_LEFT_IDLE:
                activity.setWalkerPic(R.drawable.greymon_upleft_idle);
                break;
            case WalkingModes.UP_RIGHT_IDLE:
                activity.setWalkerPic(R.drawable.greymon_upright_idle);
                break;
            case WalkingModes.SIT:
                activity.setWalkerPic(R.drawable.greymon_sit);
                break;
            case WalkingModes.JUMP:
                activity.setWalkerPic(R.drawable.greymon_jump);
                break;
            default:
                activity.setWalkerPic(R.drawable.greymon_sit);
        }
    }

    @Override
    public int getProfilePic() {
        return R.drawable.greymonprofile;
    }
}
