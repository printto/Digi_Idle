package com.printto.printmov.digi_idle.farmmap;

import com.printto.printmov.digi_idle.R;
import com.printto.printmov.digi_idle.item.Drug;
import com.printto.printmov.digi_idle.item.FoodPackage1;
import com.printto.printmov.digi_idle.item.Item;
import com.printto.printmov.digi_idle.item.Meat;
import com.printto.printmov.digi_idle.item.OrangeJuice;

public class MapCapCoalaDay extends FarmMap{

    @Override
    public String getMapName() {
        return "Cap Coala";
    }

    @Override
    public int getBackgroundResource(){
        return R.drawable.map_cap_coala_day;
    }

    @Override
    public Item[] getItemArray() {
        return new Item[]{new FoodPackage1()};
    }

    @Override
    public String getDescription() {
        return "Nice beach. Good place to relax.";
    }

}
