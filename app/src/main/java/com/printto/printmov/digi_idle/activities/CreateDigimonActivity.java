package com.printto.printmov.digi_idle.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.printto.printmov.digi_idle.Player;
import com.printto.printmov.digi_idle.R;
import com.printto.printmov.digi_idle.digimon.Digimon;
import com.printto.printmov.digi_idle.factory.DigimonFactory;
import com.printto.printmov.digi_idle.utils.SaveManager;

public class CreateDigimonActivity extends AppCompatActivity {

    public static final int READ_EXTERNAL_STORAGE = 0;
    public static final int WRITE_EXTERNAL_STORAGE = 1;
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

        Intent intent = getIntent();
        if (intent.hasExtra("alert_title") && intent.hasExtra("alert_text")) {
            String title = intent.getStringExtra("alert_title");
            String message = intent.getStringExtra("alert_text");
            new AlertDialog.Builder(CreateDigimonActivity.this)
                    .setTitle(title)
                    .setMessage(message)
                    .setNegativeButton("Momentai~", null)
                    .setIcon(R.drawable.ch_9999)
                    .show();
        }

    }

    private void confirmCreate(Digimon digimon) {
        Player player = new Player(nameInput.getText().toString());
        saveManager.saveState(digimon, player);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void createDigimon(String digimonName) {
        if (!nameInput.getText().toString().equals("")) {
            digimon = DigimonFactory.findDigimonByName(digimonName);
            confirmCreate(digimon);
        } else {
            Toast.makeText(getApplicationContext(), "Invalid player name.", Toast.LENGTH_SHORT).show();
        }
    }

    public void eggSelected(View view) {
        boolean readPermissionGranted = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        boolean writePermissionGranted = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        //Check permission
        if (!readPermissionGranted) {
            Toast.makeText(getApplicationContext(), "Call for permission", Toast.LENGTH_SHORT).show();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXTERNAL_STORAGE);
            }
        }
        if (!writePermissionGranted) {
            Toast.makeText(getApplicationContext(), "Call for permission", Toast.LENGTH_SHORT).show();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORAGE);
            }
        }
        if (readPermissionGranted && writePermissionGranted) {
            switch (view.getId()) {

                case R.id.egg1:
                    createDigimon("Koromon Egg");
                    break;
                case R.id.egg2:

                    break;
                case R.id.egg3:

                    break;
                case R.id.egg4:

                    break;
                case R.id.egg5:

                    break;
                case R.id.egg6:

                    break;
                case R.id.egg7:

                    break;
                case R.id.egg8:

                    break;
                case R.id.egg9:

                    break;

                default:
            }
        }
    }

}
