package com.printto.printmov.digi_idle.item;

import com.printto.printmov.digi_idle.R;

public class Meat extends Food {

    public Meat(){
        id = "0001";
        name = "Digi-Meat";
        fullness = 5;
        desc = "A piece of meat harvested from Digi-meat plant.\n\nFill up by 5";
    }

    @Override
    public int getPicture() {
        return R.drawable.item0001;
    }
}
