package com.printto.printmov.digi_idle.item;

import com.printto.printmov.digi_idle.R;

public class MidnightRamen extends Food {

    public MidnightRamen(){
        id = "0007";
        name = "Midnight Ramen";
        fullness = 5;
        desc = "Instant noodle imported from real-world's Thailand.\nPerfect for midnight meal either raw or cooked.\n\nFill up by 5";
    }

    @Override
    public int getPicture() {
        return R.drawable.item0007;
    }
}
