package com.printto.printmov.digi_idle.digimon;

import com.printto.printmov.digi_idle.R;
import com.printto.printmov.digi_idle.utils.WalkingModes;

public class Koromon extends Digimon {

    @Override
    protected void initializeInformationsOnCreate() {
        name = "Koromon";
    }

    @Override
    public void renderAnimation(int mode) {
        switch(mode){
            case WalkingModes.DOWN_LEFT_WALK:
                activity.setWalkerPic(R.drawable.koromon_downleft);
                break;
            case WalkingModes.DOWN_RIGHT_WALK:
                activity.setWalkerPic(R.drawable.koromon_downright);
                break;
            case WalkingModes.UP_LEFT_WALK:
                activity.setWalkerPic(R.drawable.koromon_upleft);
                break;
            case WalkingModes.UP_RIGHT_WALK:
                activity.setWalkerPic(R.drawable.koromon_upright);
                break;
            case WalkingModes.DOWN_LEFT_IDLE:
                activity.setWalkerPic(R.drawable.koromon_downleft_idle);
                break;
            case WalkingModes.DOWN_RIGHT_IDLE:
                activity.setWalkerPic(R.drawable.koromon_downright_idle);
                break;
            case WalkingModes.UP_LEFT_IDLE:
                activity.setWalkerPic(R.drawable.koromon_upleft_idle);
                break;
            case WalkingModes.UP_RIGHT_IDLE:
                activity.setWalkerPic(R.drawable.koromon_upright_idle);
                break;
            case WalkingModes.SIT:
                activity.setWalkerPic(R.drawable.koromon_sit);
                break;
            case WalkingModes.JUMP:
                activity.setWalkerPic(R.drawable.koromon_jump);
                break;
            default:
                activity.setWalkerPic(R.drawable.koromon_sit);
        }
    }

    @Override
    public int getProfilePic() {
        return R.drawable.koromonprofile;
    }
}
