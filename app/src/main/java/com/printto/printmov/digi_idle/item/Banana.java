package com.printto.printmov.digi_idle.item;

import com.printto.printmov.digi_idle.R;

public class Banana extends Food {

    public Banana(){
        id = "0004";
        name = "Digi-Banana";
        fullness = 5;
        desc = "Digi-Banana harvested from digi-banana pond.\n\nFill up by 5";
        successText = "Filled up by 5";
    }

    @Override
    public int getPicture() {
        return R.drawable.item0004;
    }

}
