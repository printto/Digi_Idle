package com.printto.printmov.digi_idle.utils;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.printto.printmov.digi_idle.MainActivity;
import com.printto.printmov.digi_idle.Player;
import com.printto.printmov.digi_idle.R;
import com.printto.printmov.digi_idle.digimon.Digimon;
import com.printto.printmov.digi_idle.digimon.Terriermon;

public class CreateDigimonActivity extends AppCompatActivity {

    SaveManager saveManager;
    EditText nameInput;
    Digimon digimon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_digimon);
        setTitle("Create new save");

        saveManager = new SaveManager();
        nameInput = findViewById(R.id.editText);
    }

    private void createSave(Digimon digimon){
        Player player = new Player(nameInput.getText().toString());
        saveManager.saveState(digimon, player);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void terriermonSelected(View view){
        if(!nameInput.getText().toString().equals("")){
            digimon = new Terriermon();
            createSave(digimon);
        }
        else{
            Toast.makeText(getApplicationContext(),"Invalid player name.", Toast.LENGTH_SHORT).show();
        }
    }
}
