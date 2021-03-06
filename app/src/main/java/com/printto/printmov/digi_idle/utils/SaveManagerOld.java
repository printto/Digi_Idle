package com.printto.printmov.digi_idle.utils;

import android.os.Environment;
import android.util.Log;

import com.printto.printmov.digi_idle.Player;
import com.printto.printmov.digi_idle.digimon.Digimon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.Serializable;
import java.io.StreamCorruptedException;

public class SaveManagerOld implements Serializable {

    Digimon digimon = null;
    Player player = null;

    boolean isLoaded = false;

    public void saveState(Digimon digimon, Player player) {
        FileOutputStream outStream = null;
        try {
            File digimonSaveFile = new File(Environment.getExternalStorageDirectory(), "/idle_digimon.dat");
            outStream = new FileOutputStream(digimonSaveFile);
            ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);
            objectOutStream.writeObject(digimon);
            objectOutStream.close();
            File playerSaveFile = new File(Environment.getExternalStorageDirectory(), "/idle_player.dat");
            outStream = new FileOutputStream(playerSaveFile);
            objectOutStream = new ObjectOutputStream(outStream);
            objectOutStream.writeObject(player);
            objectOutStream.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void saveState(Digimon digimon) {
        FileOutputStream outStream = null;
        try {
            File digimonSaveFile = new File(Environment.getExternalStorageDirectory(), "/idle_digimon.dat");
            outStream = new FileOutputStream(digimonSaveFile);
            ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);
            objectOutStream.writeObject(digimon);
            objectOutStream.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public boolean loadState()
    {
        Digimon loadDigimon = null;
        Player loadPlayer = null;
        FileInputStream inStream = null;
        try {
            File digimonLoadFile = new File(Environment.getExternalStorageDirectory(), "/idle_digimon.dat");
            inStream = new FileInputStream(digimonLoadFile);
            ObjectInputStream objectInStream = new ObjectInputStream(inStream);
            loadDigimon = ((Digimon) objectInStream.readObject());
            objectInStream.close();
            File playerLoadFile = new File(Environment.getExternalStorageDirectory(), "/idle_player.dat");
            inStream = new FileInputStream(playerLoadFile);
            objectInStream = new ObjectInputStream(inStream);
            loadPlayer = ((Player) objectInStream.readObject());
            objectInStream.close();
        } catch (FileNotFoundException e1) {
            Log.e("Load state", "No save found");
            e1.printStackTrace();
            return false;
        } catch (ClassNotFoundException e1) {
            Log.e("Load state", "Error casting class. Save state is too old?");
            return false;
        } catch (OptionalDataException e1) {
            Log.e("Load state", "Loading save failed");
            e1.printStackTrace();
            return false;
        } catch (StreamCorruptedException e1) {
            Log.e("Load state", "Loading save failed");
            e1.printStackTrace();
            return false;
        } catch (IOException e1) {
            Log.e("Load state", "Loading save failed");
            e1.printStackTrace();
            return false;
        }
        if(loadDigimon != null || loadPlayer != null){
            digimon = loadDigimon;
            player = loadPlayer;
            return true;
        }
        else
        {
            Log.e("Load save", "Loading save failed");
            return false;
        }
    }

    public Digimon getDigimon(){
        return digimon;
    }

    public Player getPlayer(){
        return player;
    }

}
