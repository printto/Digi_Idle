package com.printto.printmov.digi_idle.farmmap;

import com.printto.printmov.digi_idle.R;
import com.printto.printmov.digi_idle.item.OrangeJuice;
import com.printto.printmov.digi_idle.item.Drug;
import com.printto.printmov.digi_idle.item.Item;
import com.printto.printmov.digi_idle.item.Meat;

public class MapAmidaForestDay extends FarmMap{

    @Override
    public String getMapName() {
        return "Amida Forest";
    }

    @Override
    public int getBackgroundResource(){
        return R.drawable.map_amida_forest_day;
    }

    @Override
    public Item[] getItemArray() {
        return new Item[]{new Meat(),new OrangeJuice(),new Drug()};
    }

    @Override
    public String getDescription() {
        return "Basic map to start with.";
    }

}
