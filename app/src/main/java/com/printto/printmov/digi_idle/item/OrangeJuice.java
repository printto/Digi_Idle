package com.printto.printmov.digi_idle.item;

import com.printto.printmov.digi_idle.R;

public class OrangeJuice extends Food {

    public OrangeJuice(){
        id = "0003";
        name = "Orange Juice";
        energy = 5;
        desc = "Liquid extract of the Digi-Orange tree fruit, produced by squeezing Digi-Oranges.\n\nRefill energy by 5";
    }

    @Override
    public int getPicture() {
        return R.drawable.item0003;
    }
}
