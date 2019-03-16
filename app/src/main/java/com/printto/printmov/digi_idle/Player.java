package com.printto.printmov.digi_idle;

import com.printto.printmov.digi_idle.digimon.Digimon;

import java.io.Serializable;

public class Player implements Serializable {

    String name = "";
    int level = 1;
    int exp = 0;
    int balance = 0;

    public Player(String name){
        this.name = name;
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

    int points = 0;

}
