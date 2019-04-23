package com.printto.printmov.digi_idle.item;

import com.printto.printmov.digi_idle.R;

public class DebugFood1 extends Food{

    public DebugFood1(int fullness, int energy, int hp){
        this.fullness = fullness;
        this.energy = energy;
        this.hp = hp;
    }

    @Override
    public int getPicture() {
        return R.drawable.ch_9999;
    }
}
