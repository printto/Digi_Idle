package com.printto.printmov.digi_idle.farmmap;

import com.printto.printmov.digi_idle.R;
import com.printto.printmov.digi_idle.item.Banana;
import com.printto.printmov.digi_idle.item.GreenCandy;
import com.printto.printmov.digi_idle.item.OrangeJuice;
import com.printto.printmov.digi_idle.item.Drug;
import com.printto.printmov.digi_idle.item.Item;
import com.printto.printmov.digi_idle.item.Meat;
import com.printto.printmov.digi_idle.item.Vaccine;

public class MapAmidaForestEvening extends FarmMap{

    @Override
    public String getMapName() {
        return "Amida Forest";
    }

    @Override
    public int getBackgroundResource(){
        return R.drawable.map_amida_forest_evening;
    }

    @Override
    public Item[] getItemArray() {
        return new Item[]{new Banana(),new Vaccine(),new GreenCandy()};
    }

    @Override
    public String getDescription() {
        return "Basic map to start with.";
    }

}
