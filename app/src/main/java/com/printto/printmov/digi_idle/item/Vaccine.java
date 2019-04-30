package com.printto.printmov.digi_idle.item;

import com.printto.printmov.digi_idle.R;

public class Vaccine extends Food {

    public Vaccine(){
        id = "0005";
        name = "Vaccine";
        desc = "A program designed to detect computer viruses and inactivate them.\n\nRestores HP by 1 when used.";
        hp = 1;
        successText = "Restored HP by 1";
    }

    @Override
    public int getPicture() {
        return R.drawable.item0005;
    }
}
