package com.printto.printmov.digi_idle.item;

public abstract class Food extends Item {

    int fullness;
    int energy = 0;

    int attack = 0;
    int defense = 0;
    int maxFullness = 0;
    int exp = 0;
    int hp = 0;
    int maxHp = 0;

    public int getFullness() {
        return fullness;
    }

    public int getEnergy() {
        return energy;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getMaxFullness() {
        return maxFullness;
    }

    public int getExp() {
        return exp;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

}
