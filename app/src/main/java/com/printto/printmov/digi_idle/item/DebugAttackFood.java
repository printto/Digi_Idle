package com.printto.printmov.digi_idle.item;

import com.printto.printmov.digi_idle.R;

public class DebugAttackFood extends Food{

    public DebugAttackFood(){
        id = "999A";
        name = "DebugA";
        desc = "Debug food for testing that increases your partner's strength. Duplicate this item's class source code to test other status.\n\nIncrease attack by 5";
        attack = 5;
    }

    @Override
    public int getPicture() {
        return R.drawable.ch_9999;
    }
}
