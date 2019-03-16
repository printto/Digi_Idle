package com.printto.printmov.digi_idle.digimon;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DigimonFactory {

    public static Digimon findDigimonByName(String name){
        switch (name){

            case "Koromon Egg":
                return new KoromonEgg();

            case "Koromon":
                return new Koromon();

            case "Agumon":
                return new Agumon();

            case "Greymon":
                return new Greymon();

            case "Metalgreymon":
                return new Metalgreymon();

            case "Geogreymon":
                return new Geogreymon();

            case "Terriermon":
                return new Terriermon();

            default:
                return new NullDigimon();
        }
    }

    public static Digimon[] getNextDigivolvableArray(String name){
        List<Digimon> temp = new ArrayList<Digimon>();
        switch (name){

            case "Koromon Egg":
                temp.add(new Koromon());
                break;

            case "Koromon":
                temp.add(new Agumon());
                break;

            case "Agumon":
                temp.add(new Greymon());
                temp.add(new Geogreymon());
                break;

            case "Greymon":
                temp.add(new Metalgreymon());
                break;

            case "Terriermon":

            default:

        }
        return (Digimon[]) temp.toArray();
    }

}
