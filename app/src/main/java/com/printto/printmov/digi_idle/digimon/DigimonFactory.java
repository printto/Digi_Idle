package com.printto.printmov.digi_idle.digimon;

import com.printto.printmov.digi_idle.utils.DigimonForms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DigimonFactory {

    public static Digimon findDigimonByName(String name) {
        switch (name) {

            case "Koromon Egg":
                return new KoromonEgg();

            case "Koromon":
                return new Koromon();

            case "Agumon":
                return new Agumon();

            case "Greymon":
                return new Greymon();

            case "Geogreymon":
                return new Geogreymon();

            case "Metalgreymon":
                return new Metalgreymon();

            case "Terriermon":
                return new Terriermon();

            default:
                return new NullDigimon();
        }
    }

    public static Digimon[] getNextDigivolvableArray(String digimonName) {
        List<Digimon> temp = new ArrayList<Digimon>();
        switch (digimonName) {

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

            case "Geogreymon":

            case "Metalgreymon":

            case "Terriermon":

            default:

        }
        return (Digimon[]) temp.toArray();
    }

    public String getRequirementString(String digimonName) {

        String requirement = "";
        Digimon digimon = findDigimonByName(digimonName);

        switch (digimon.getForm()) {
            case DigimonForms.INTRAINING:
                requirement = "Age > 5 minutes\n";
                break;
            case DigimonForms.ROOKIE:
                requirement = "Age > 1 day\n";
                break;
            case DigimonForms.CHAMPION:
                requirement = "Age > 3 day\n";
                break;
            case DigimonForms.ULTIMATE:
                requirement = "Age > 5 day\n";
                break;
            case DigimonForms.MEGA:
                requirement = "Age > 10 day\n";
                break;
        }

        switch (digimonName) {

            case "Koromon Egg":

            case "Koromon":

            case "Agumon":
                requirement += "Attack > 10\nDefense > 10";

            case "Greymon":
                requirement += "Attack > 20\nDefense > 10";

            case "Geogreymon":
                requirement += "Level > 10";

            case "Metalgreymon":
                requirement += "HP > 300";

            case "Terriermon":

            default:

        }
        return requirement;
    }

}
