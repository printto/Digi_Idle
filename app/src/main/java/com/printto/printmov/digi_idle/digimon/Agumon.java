package com.printto.printmov.digi_idle.digimon;

import com.printto.printmov.digi_idle.R;
import com.printto.printmov.digi_idle.values.DigimonForms;
import com.printto.printmov.digi_idle.values.WalkingModes;

public class Agumon extends Digimon {

    @Override
    protected void initializeInformationsOnCreate() {
        name = "Agumon";
        form = DigimonForms.ROOKIE;
    }

    @Override
    public void renderAnimation(int mode) {
        switch(mode){
            case WalkingModes.DOWN_LEFT_WALK:
                activity.setWalkerPic(R.drawable.agumon_downleft);
                break;
            case WalkingModes.DOWN_RIGHT_WALK:
                activity.setWalkerPic(R.drawable.agumon_downright);
                break;
            case WalkingModes.UP_LEFT_WALK:
                activity.setWalkerPic(R.drawable.agumon_upleft);
                break;
            case WalkingModes.UP_RIGHT_WALK:
                activity.setWalkerPic(R.drawable.agumon_upright);
                break;
            case WalkingModes.DOWN_LEFT_IDLE:
                activity.setWalkerPic(R.drawable.agumon_downleft_idle);
                break;
            case WalkingModes.DOWN_RIGHT_IDLE:
                activity.setWalkerPic(R.drawable.agumon_downright_idle);
                break;
            case WalkingModes.UP_LEFT_IDLE:
                activity.setWalkerPic(R.drawable.agumon_upleft_idle);
                break;
            case WalkingModes.UP_RIGHT_IDLE:
                activity.setWalkerPic(R.drawable.agumon_upright_idle);
                break;
            case WalkingModes.SIT:
                activity.setWalkerPic(R.drawable.agumon_sit);
                break;
            case WalkingModes.JUMP:
                activity.setWalkerPic(R.drawable.agumon_jump);
                break;
            default:
                activity.setWalkerPic(R.drawable.agumon_sit);
        }
    }

    @Override
    public int getProfilePic() {
        return R.drawable.agumonprofile;
    }
}
