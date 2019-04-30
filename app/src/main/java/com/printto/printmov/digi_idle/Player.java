package com.printto.printmov.digi_idle;

import com.printto.printmov.digi_idle.digimon.Digimon;
import com.printto.printmov.digi_idle.item.OrangeJuice;
import com.printto.printmov.digi_idle.item.Drug;
import com.printto.printmov.digi_idle.item.Item;
import com.printto.printmov.digi_idle.item.Meat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player implements Serializable {

    String name = "";
    int level = 1;
    int exp = 0;
    int balance = 0;
    Map<Item, Integer> items = new HashMap<Item, Integer>();
    int points = 0;
    Digimon digimon;
    List<Digimon> digimonDex = new ArrayList<Digimon>();

    public Player(String name){
        this.name = name;
        items.put(new Meat(), 3);
        items.put(new Drug(), 3);
        items.put(new OrangeJuice(), 3);
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

    public void addItem(Item item, int amount){
        if(!items.containsKey(item)){
            items.put(item, amount);
        }
        else{
            items.put(item, items.get(item) + amount);
        }
    }

    public void addUnlimitedItem(Item item){
        items.put(item, -1);
    }

    public void useItem(Item item){
        if(items.get(item) == 1){
            items.remove(item);
        }
        else{
            items.put(item, items.get(item) - 1);
        }
    }

    public Map<Item, Integer> getItems(){
        return items;
    }

    public Digimon getDigimon() {
        return digimon;
    }

    public void setDigimon(Digimon digimon) {
        this.digimon = digimon;
    }

    public void addDigimonToDex(Digimon addDigimon){
        digimonDex.add(addDigimon);
    }

    public List<Digimon> getDigimonDex() {
        return digimonDex;
    }
}
