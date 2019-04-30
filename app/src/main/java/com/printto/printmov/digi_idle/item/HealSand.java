package com.printto.printmov.digi_idle.item;

import com.printto.printmov.digi_idle.R;

public class HealSand extends Food {

    public HealSand(){
        id = "0008";
        name = "Heal Sand";
        desc = "Magical sand from Amida Forest. The sand shines very bright at night.\n\nRestores HP by 1 when used.";
        hp = 1;
        successText = "Restored HP by 1";
    }

    @Override
    public int getPicture() {
        return R.drawable.item0008;
    }
}
