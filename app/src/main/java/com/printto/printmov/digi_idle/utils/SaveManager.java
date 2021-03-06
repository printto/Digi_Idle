package com.printto.printmov.digi_idle.utils;

import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.printto.printmov.digi_idle.Player;
import com.printto.printmov.digi_idle.digimon.Digimon;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class SaveManager implements Serializable {

    Digimon digimon = null;
    Player player = null;

    public void saveState(Digimon digimon, Player player) {
        try {
            File playerSaveFile = new File(Environment.getExternalStorageDirectory(), "/digi-idle-save.dat");
            FileOutputStream outStream = new FileOutputStream(playerSaveFile);
            ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);
            player.setDigimon(digimon);
            objectOutStream.writeObject(player);
            objectOutStream.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void saveState(Digimon digimon) {
        try {
            File digimonSaveFile = new File(Environment.getExternalStorageDirectory(), "/digi-idle-save.dat");
            FileOutputStream outStream = new FileOutputStream(digimonSaveFile);
            ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);
            player.setDigimon(digimon);
            objectOutStream.writeObject(player);
            objectOutStream.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void saveState(Player player) {
        try {
            File digimonSaveFile = new File(Environment.getExternalStorageDirectory(), "/digi-idle-save.dat");
            FileOutputStream outStream = new FileOutputStream(digimonSaveFile);
            ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);
            objectOutStream.writeObject(player);
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
            File playerLoadFile = new File(Environment.getExternalStorageDirectory(), "/digi-idle-save.dat");
            inStream = new FileInputStream(playerLoadFile);
            ObjectInputStream objectInStream = new ObjectInputStream(inStream);
            loadPlayer = ((Player) objectInStream.readObject());
            loadDigimon = loadPlayer.getDigimon();
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
        if(loadDigimon != null && loadPlayer != null){
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
