package com.printto.printmov.digi_idle.item;

import com.printto.printmov.digi_idle.R;

public class Cola extends Food {

    public Cola(){
        id = "0003";
        name = "Soda";
        energy = 5;
        desc = "A sweetened, carbonated soft drink flavored with vanilla, cinnamon, citrus oils and other flavorings.\n\nRefill energy by 5";
    }

    @Override
    public int getPicture() {
        return R.drawable.item0003;
    }
}
