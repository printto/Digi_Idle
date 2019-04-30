package com.printto.printmov.digi_idle.item;

import com.printto.printmov.digi_idle.R;

public class GreenCandy extends Food {

    public GreenCandy(){
        id = "0006";
        name = "Green Pop";
        energy = 5;
        desc = "Sweet candy made from Digi-SugarCane with Digi-Pandan as an ingredient.\n\nRefill energy by 5";
        successText = "Refilled energy by 5";
    }

    @Override
    public int getPicture() {
        return R.drawable.item0006;
    }
}
