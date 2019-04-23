package com.printto.printmov.digi_idle.item;

import com.printto.printmov.digi_idle.R;

public class Drug extends Food {

    public Drug(){
        id = "0002";
        name = "Drug";
        desc = "A medicine which has a physiological effect when ingested or otherwise introduced into the body. Can cure your partner's illness.\n\nRestores HP by 5 when used.";
        hp = 5;
    }

    @Override
    public int getPicture() {
        return R.drawable.item0002;
    }
}
