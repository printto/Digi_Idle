package com.printto.printmov.digi_idle.item;

import com.printto.printmov.digi_idle.R;

public class WhiteLeaf extends Food {

    public WhiteLeaf(){
        id = "0009";
        name = "White Leaf";
        energy = 5;
        desc = "Huh? Where did I get this leaf from? I can't remember.\n\nRefill energy by 5";
        successText = "Refilled energy by 5";
    }

    @Override
    public int getPicture() {
        return R.drawable.item0009;
    }
}
