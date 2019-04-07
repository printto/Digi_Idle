package com.printto.printmov.digi_idle.item;

import com.printto.printmov.digi_idle.R;

public abstract class Item {

    static String id;
    static String name;
    static int picture;
    static String desc;

    public void Item(){
        id = "0";
        name = "Untitled Item";
        picture = R.drawable.ch_9999;
        desc = "Mystery effects may apply when using this item.";
    }

    @Override
    public boolean equals(Object object){
        if(object.getClass() == Item.class){
            Item item = (Item) object;
            if(this.id.equals(item.id)) return true;
        }
        return false;
    }

    public static String getID(){
        return id;
    }

    public static String getName() {
        return name;
    }

    public static int getPicture() {
        return picture;
    }

    public static String getDesc() {
        return desc;
    }

}
