package com.printto.printmov.digi_idle.farmmap;

import com.printto.printmov.digi_idle.R;
import com.printto.printmov.digi_idle.item.HealSand;
import com.printto.printmov.digi_idle.item.MidnightRamen;
import com.printto.printmov.digi_idle.item.OrangeJuice;
import com.printto.printmov.digi_idle.item.Drug;
import com.printto.printmov.digi_idle.item.Item;
import com.printto.printmov.digi_idle.item.Meat;
import com.printto.printmov.digi_idle.item.WhiteLeaf;

public class MapAmidaForestNight extends FarmMap{

    @Override
    public String getMapName() {
        return "Amida Forest";
    }

    @Override
    public int getBackgroundResource(){
        return R.drawable.map_amida_forest_night;
    }

    @Override
    public Item[] getItemArray() {
        return new Item[]{new MidnightRamen(),new HealSand(),new WhiteLeaf()};
    }

    @Override
    public String getDescription() {
        return "Basic map to start with.";
    }

}
