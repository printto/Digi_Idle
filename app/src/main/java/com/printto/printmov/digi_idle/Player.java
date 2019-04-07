package com.printto.printmov.digi_idle;

import com.printto.printmov.digi_idle.digimon.Digimon;
import com.printto.printmov.digi_idle.item.Cola;
import com.printto.printmov.digi_idle.item.Drug;
import com.printto.printmov.digi_idle.item.Food;
import com.printto.printmov.digi_idle.item.Item;
import com.printto.printmov.digi_idle.item.Meat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Player implements Serializable {

    String name = "";
    int level = 1;
    int exp = 0;
    int balance = 0;
    Map<Item, Integer> items = new HashMap<Item, Integer>();
    int points = 0;

    public Player(String name){
        this.name = name;
        items.put(new Drug(), 5);
        items.put(new Cola(), 5);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addItem(Item item){
        if(!items.containsKey(item)){
            items.put(item, 1);
        }
        else{
            items.put(item, items.get(item) + 1);
        }
    }

    public void removeItem(Item item){
        if(items.get(item) <= 1){
            items.remove(item);
        }
        else{
            items.put(item, items.get(item) - 1);
        }
    }

}
