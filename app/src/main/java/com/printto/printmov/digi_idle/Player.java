package com.printto.printmov.digi_idle;

import com.printto.printmov.digi_idle.digimon.Digimon;

import java.io.Serializable;

public class Player implements Serializable {

    String name = "";
    int level = 1;
    int exp = 0;
    int balance = 0;
    int points = 0;

    public Player(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
