package com.printto.printmov.digi_idle.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.printto.printmov.digi_idle.Player;
import com.printto.printmov.digi_idle.R;
import com.printto.printmov.digi_idle.digimon.Digimon;
import com.printto.printmov.digi_idle.utils.SaveManager;

public class UpStatActivity extends AppCompatActivity {

    int points = 0;
    int defense = 0;
    int attack = 0;
    int maxEnergy = 0;
    Player player;
    Digimon digimon;

    TextView pointsText;
    TextView attackText;
    TextView defenseText;
    TextView energyText;

    Button plusAttack;
    Button minusAttack;
    Button plusDefense;
    Button minusDefense;
    Button plusEnergy;
    Button minusEnergy;

    SaveManager saveManager = new SaveManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_stat);

        saveManager.loadState();
        player = saveManager.getPlayer();
        digimon = saveManager.getDigimon();
        points = player.getPoints();
        attack = digimon.getAttack();
        defense = digimon.getDefense();
        maxEnergy = digimon.getMaxEnergy();

        pointsText = findViewById(R.id.pointsText);
        attackText = findViewById(R.id.attackText);
        defenseText = findViewById(R.id.defenseText);
        energyText = findViewById(R.id.energyText);
        plusAttack = findViewById(R.id.plusAttack);
        minusAttack = findViewById(R.id.minusAttack);
        plusDefense = findViewById(R.id.plusDefense);
        minusDefense = findViewById(R.id.minusDefense);
        plusEnergy = findViewById(R.id.plusEnergy);
        minusEnergy = findViewById(R.id.minusEnergy);

        updateStatus();
    }

    private void updateStatus(){
        pointsText.setText(points + "");
        attackText.setText(attack + "/" + digimon.getAttack());
        defenseText.setText(defense + "/" + digimon.getDefense());
        energyText.setText(maxEnergy + "/" + digimon.getMaxEnergy());
        plusAttack.setBackgroundResource(R.drawable.btn2);
        plusDefense.setBackgroundResource(R.drawable.btn2);
        minusAttack.setBackgroundResource(R.drawable.btn2);
        minusDefense.setBackgroundResource(R.drawable.btn2);
        plusEnergy.setBackgroundResource(R.drawable.btn2);
        minusEnergy.setBackgroundResource(R.drawable.btn2);
        if(points <= 0){
            plusAttack.setBackgroundResource(R.drawable.btn4);
            plusDefense.setBackgroundResource(R.drawable.btn4);
            plusEnergy.setBackgroundResource(R.drawable.btn4);
        }
        if(attack <= digimon.getAttack()){
            minusAttack.setBackgroundResource(R.drawable.btn4);
        }
        if(defense <= digimon.getDefense()){
            minusDefense.setBackgroundResource(R.drawable.btn4);
        }
        if(maxEnergy <= digimon.getMaxEnergy()){
            minusEnergy.setBackgroundResource(R.drawable.btn4);
        }
    }

    public void upAttack(View view){
        if(points > 0) {
            attack++;
            points--;
            updateStatus();
        }
    }

    public void upDefense(View view){
        if(points > 0) {
            defense++;
            points--;
            updateStatus();
        }
    }

    public void downAttack(View view){
        if(attack > digimon.getAttack()) {
            attack--;
            points++;
            updateStatus();
        }
    }

    public void downDefense(View view){
        if(defense > digimon.getDefense()) {
            defense--;
            points++;
            updateStatus();
        }
    }

    public void upEnergy(View view){
        if(points > 0) {
            maxEnergy += 100;
            points--;
            updateStatus();
        }
    }

    public void downEnergy(View view){
        if(maxEnergy > digimon.getMaxEnergy()) {
            maxEnergy -= 100;
            points++;
            updateStatus();
        }
    }

    public void onResetBtnClicked(View view){
        points = player.getPoints();
        attack = digimon.getAttack();
        defense = digimon.getDefense();
        maxEnergy = digimon.getMaxEnergy();
        updateStatus();
    }

    public void onConfirmBtnClicked(View view){
        digimon.setAttack(attack);
        digimon.setDefense(defense);
        digimon.setMaxEnergy(maxEnergy);
        player.setPoints(points);
        saveManager.saveState(digimon, player);
        saveManager.loadState();
        updateStatus();
    }

    public void onBackBtnPressed(View view){
        finish();
    }

    @Override
    public void onBackPressed () {
        finish();
    }

    @Override
    public void finish(){
        Intent intent = new Intent(this, StatusActivity.class);
        startActivity(intent);
        super.finish();
        overridePendingTransition(R.anim.zoominfadein, R.anim.zoomoutfadeout);
    }

}
