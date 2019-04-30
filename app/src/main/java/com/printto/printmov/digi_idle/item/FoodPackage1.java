package com.printto.printmov.digi_idle.item;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.printto.printmov.digi_idle.Player;
import com.printto.printmov.digi_idle.R;
import com.printto.printmov.digi_idle.utils.SaveManager;

public class FoodPackage1 extends Usable {

    private Item[] obtainable = {new Meat(), new Drug(), new OrangeJuice(), new Banana(), new Vaccine(), new GreenCandy(), new MidnightRamen(), new HealSand(), new WhiteLeaf()};

    public FoodPackage1(){
        id = "0010";
        name = "Food Package";
        desc = "I wonder what is inside? Seems like someone droped this food package. Let's open it, I am very hungry right now.\n\nGet 3 Amida Forest's food randomly";
    }

    @Override
    public int getPicture() {
        return R.drawable.item0010;
    }

    @Override
    public void use() {
        SaveManager saveManager = new SaveManager();
        saveManager.loadState();
        Player player = saveManager.getPlayer();
        int randomed = (int) (Math.random() * (obtainable.length));
        player.addItem(obtainable[randomed]);
        successText += "Get 1 " + obtainable[randomed];
        randomed = (int) (Math.random() * (obtainable.length));
        player.addItem(obtainable[randomed]);
        successText += "\nGet 1 " + obtainable[randomed];
        randomed = (int) (Math.random() * (obtainable.length));
        player.addItem(obtainable[randomed]);
        successText += "\nGet 1 " + obtainable[randomed];
        saveManager.saveState(player);
    }

}
