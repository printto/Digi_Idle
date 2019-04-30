package com.printto.printmov.digi_idle.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.printto.printmov.digi_idle.Player;
import com.printto.printmov.digi_idle.R;
import com.printto.printmov.digi_idle.adapters.DexRecyclerViewAdapter;
import com.printto.printmov.digi_idle.digimon.Digimon;
import com.printto.printmov.digi_idle.utils.SaveManager;
import com.printto.printmov.digi_idle.values.DigimonForms;

public class DexActivity extends AppCompatActivity {

    RecyclerView recycleView;

    SaveManager saveManager = new SaveManager();
    Player player;
    Digimon digimon;

    DexRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dex);

        recycleView = findViewById(R.id.listView);

        saveManager.loadState();
        player = saveManager.getPlayer();
        digimon = saveManager.getDigimon();

        Button reinca_btn = findViewById(R.id.reinca_btn);

        if(digimon.getForm() == DigimonForms.MEGA){
            reinca_btn.setBackgroundResource(R.drawable.btn2);
        }

        reinca_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(digimon.getForm() == DigimonForms.MEGA){
                    //TODO: Reincarnation system
                    Toast.makeText(DexActivity.this,"Reincarnation system soon...", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(DexActivity.this,"Your partner need to be in form 4 or higher to get reincarnated.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView profileNameText = findViewById(R.id.profileName);
        TextView statusText = findViewById(R.id.statusText);

        profileNameText.setText(player.getName() + "'s Profile");
        statusText.setText("Level: "+player.getLevel()+
                            "\nExp: "+player.getExp()+
                            "\nSkill points: "+player.getPoints()+
                            "\nBalance: "+player.getBalance()+" Bits"+
                            "\nDigimon collected: "+player.getDigimonDex().size());

        adapter = new DexRecyclerViewAdapter(this,player.getDigimonDex());
        recycleView.setAdapter(adapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),3);
        recycleView.setLayoutManager(gridLayoutManager);

        Button dexHintBtn = findViewById(R.id.dexHintBtn);
        dexHintBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(DexActivity.this)
                        .setTitle("Digimon Dex")
                        .setMessage("When you digivolve your partner to a new form, the form will be recorded to this Digimon dex.\n"+
                                    "\nThe more you collect, the more status multiplier in the battle.\n"+
                                    "\nYou can reincarnate your partner back to an egg once your partner reaches the 4th form or higher.\n"+
                                    "\nReincarnating helps you to collect more stamps in Digimon Dex.")
                        .setNegativeButton("Momentai~!", null)
                        .setIcon(R.drawable.btndex)
                        .show();
            }
        });

    }

    @Override
    public void onBackPressed () {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
        overridePendingTransition(R.anim.zoominfadein, R.anim.zoomoutfadeout);
    }

    @Override
    public void finish(){
        super.finish();
    }

}
