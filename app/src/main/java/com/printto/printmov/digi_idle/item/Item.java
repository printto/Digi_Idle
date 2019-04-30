package com.printto.printmov.digi_idle.item;

import com.printto.printmov.digi_idle.R;

import java.io.Serializable;
import java.util.Objects;

public abstract class Item implements Serializable {

    String id = "0";
    String name = "Untitled Item";
    String desc = "No description available.";
    String successText = "";

    @Override
    public boolean equals(Object object){
        if(object instanceof Item){
            Item item = (Item) object;
            if(this.id.equals(item.id)) return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getID(){
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPicture() {
        return R.drawable.ch_9999;
    }

    public String getDesc() {
        return desc;
    }

    public String getSuccessText(){
        return successText;
    }

    @Override
    public String toString(){
        return name;
    }

}
