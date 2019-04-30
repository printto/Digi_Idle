package com.printto.printmov.digi_idle.factory;

import com.printto.printmov.digi_idle.Player;
import com.printto.printmov.digi_idle.digimon.Agumon;
import com.printto.printmov.digi_idle.digimon.Digimon;
import com.printto.printmov.digi_idle.digimon.Geogreymon;
import com.printto.printmov.digi_idle.digimon.Greymon;
import com.printto.printmov.digi_idle.digimon.Koromon;
import com.printto.printmov.digi_idle.digimon.KoromonEgg;
import com.printto.printmov.digi_idle.digimon.Metalgreymon;
import com.printto.printmov.digi_idle.digimon.NullDigimon;
import com.printto.printmov.digi_idle.digimon.Terriermon;
import com.printto.printmov.digi_idle.values.DigimonForms;
import com.printto.printmov.digi_idle.utils.SaveManager;

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
        Digimon[] tempArray = temp.toArray(new Digimon[temp.size()]);
        return tempArray;
    }

    public static String getRequirementString(String digimonName) {

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
                break;

            case "Koromon":
                break;

            case "Agumon":
                requirement += "Attack > 10\nDefense > 10";
                break;

            case "Greymon":
                requirement += "Attack > 20\nDefense > 10";
                break;

            case "Geogreymon":
                requirement += "Level > 10";
                break;

            case "Metalgreymon":
                requirement += "Defense > 1000\nor special item";
                break;

            case "Terriermon":
                break;

            default:

        }
        return requirement;
    }

    public static boolean checkDigivolve(Player player, Digimon digimon, String digimonName) {

        Digimon temp = findDigimonByName(digimonName);

        switch (temp.getForm()) {
            case DigimonForms.INTRAINING:
                if (digimon.getAge() < 300000) {
                    return false;
                }
                break;
            case DigimonForms.ROOKIE:
                if (digimon.getAge() < 86400000) {
                    return false;
                }
                break;
            case DigimonForms.CHAMPION:
                if (digimon.getAge() < 86400000 * 3) {
                    return false;
                }
                break;
            case DigimonForms.ULTIMATE:
                if (digimon.getAge() < 86400000 * 5) {
                    return false;
                }
                break;
            case DigimonForms.MEGA:
                if (digimon.getAge() < 86400000 * 10) {
                    return false;
                }
                break;
        }

        switch (digimonName) {

            case "Koromon Egg":
                return true;

            case "Koromon":
                break;

            case "Agumon":
                return digimon.getAttack() >= 10 && digimon.getDefense() >= 10;

            case "Greymon":
                return digimon.getAttack() >= 20 && digimon.getDefense() >= 10;

            case "Geogreymon":
                return player.getLevel() >= 10;

            case "Metalgreymon":
                return digimon.getDefense() >= 1000;

            case "Terriermon":
                break;
        }
        return true;
    }

    public static void digivolve(Digimon digimon, String nextDigimon) {
        int attack = (int) 1.5 * digimon.getAttack();
        int defense = (int) 1.5 * digimon.getDefense();
        int maxHp = (int) 1.5 * digimon.getMaxHp();
        Digimon temp = findDigimonByName(nextDigimon);
        int maxEnergy = digimon.getMaxEnergy();
        Date lastFeed = new Date();
        Date lastEnergized = new Date();
        Date birth = digimon.getBirth();
        int maxFullness = digimon.getMaxFullness();
        temp.setStatus(attack, defense, maxHp, maxEnergy, maxFullness, lastFeed, lastEnergized, birth);
        SaveManager saveManager = new SaveManager();
        saveManager.loadState();
        saveManager.saveState(temp);
    }

}
