package com.printto.printmov.digi_idle.utils;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.printto.printmov.digi_idle.activities.MainActivity;
import com.printto.printmov.digi_idle.Player;
import com.printto.printmov.digi_idle.R;
import com.printto.printmov.digi_idle.digimon.Digimon;
import com.printto.printmov.digi_idle.factory.DigimonFactory;
import com.printto.printmov.digi_idle.item.DebugAttackFood;
import com.printto.printmov.digi_idle.item.DebugFood1;

import java.util.Date;

public class SaveEditorDebug extends AppCompatActivity {

    SaveManager saveManager = new SaveManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_editor_debug);
    }

    public void getTestFood(View view){
        saveManager.loadState();
        Player temp = saveManager.getPlayer();
        temp.addItem(new DebugAttackFood());
        saveManager.saveState(saveManager.getDigimon(), temp);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void onBackButtonClicked(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void onConfirmButtonClicked(View view){
        EditText name = findViewById(R.id.name);
        EditText digimonName = findViewById(R.id.digimonName);
        EditText atk = findViewById(R.id.atk);
        EditText def = findViewById(R.id.def);
        EditText fullness = findViewById(R.id.fullness);
        EditText maxFullness = findViewById(R.id.maxFullness);
        EditText hp = findViewById(R.id.hp);
        EditText maxHp = findViewById(R.id.maxHp);
        EditText energy = findViewById(R.id.energy);
        EditText maxEnergy = findViewById(R.id.maxEnergy);
        EditText level = findViewById(R.id.level);
        EditText exp = findViewById(R.id.exp);
        EditText points = findViewById(R.id.points);

        int atkInt = Integer.parseInt(atk.getText().toString());
        int defInt = Integer.parseInt(def.getText().toString());
        int fullnessInt = Integer.parseInt(fullness.getText().toString());
        int maxFullnessInt = Integer.parseInt(maxFullness.getText().toString());
        int energyInt = Integer.parseInt(energy.getText().toString());
        int maxEnergyInt = Integer.parseInt(maxEnergy.getText().toString());
        int hpInt = Integer.parseInt(hp.getText().toString());
        int maxHpInt = Integer.parseInt(maxHp.getText().toString());
        int levelInt = Integer.parseInt(level.getText().toString());
        int expInt = Integer.parseInt(exp.getText().toString());
        int pointsInt = Integer.parseInt(points.getText().toString());

        Player player = new Player(name.getText().toString());
        player.setExp(levelInt);
        player.setLevel(expInt);
        player.setPoints(pointsInt);

        Digimon digimon = DigimonFactory.findDigimonByName(digimonName.getText().toString());
        digimon.setStatus(atkInt, defInt, maxHpInt, maxEnergyInt, maxFullnessInt, new Date(), new Date(), new Date());
        digimon.feed(new DebugFood1(fullnessInt, energyInt, hpInt));
        digimon.maxAge();

        saveManager.saveState(digimon, player);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed () {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

}
