package com.printto.printmov.digi_idle.digimon;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DigimonFactory {

    public Digimon findDigimonByName(String name){
        switch (name){
            case "Koromon Egg":
                return new KoromonEgg();
            case "Koromon":
                return new Koromon();
            case "Agumon":
                return new Agumon();
            case "Terriermon":
                return new Terriermon();
            default:
                return new NullDigimon();
        }
    }

    public Digimon[] getNextDigivolvableArray(String name){
        List<Digimon> temp = new ArrayList<Digimon>();
        switch (name){
            case "Koromon Egg":
                temp.add(new Koromon());
                break;
            case "Koromon":
                temp.add(new Agumon());
                break;
            case "Agumon":
                break;
            case "Terriermon":

            default:

        }
        return (Digimon[]) temp.toArray();
    }

}
