package com.printto.printmov.digi_idle.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.printto.printmov.digi_idle.R;
import com.printto.printmov.digi_idle.adapters.MapArrayAdapterKotlin;
import com.printto.printmov.digi_idle.farmmap.FarmMap;
import com.printto.printmov.digi_idle.farmmap.MapAmidaForestDay;
import com.printto.printmov.digi_idle.farmmap.MapAmidaForestEvening;
import com.printto.printmov.digi_idle.farmmap.MapAmidaForestNight;

import java.util.ArrayList;
import java.util.Calendar;

public class MapSelectActivity extends AppCompatActivity {

    MapArrayAdapterKotlin adapter = null;
    ArrayList<FarmMap> farmMaps;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_select);

        farmMaps = new ArrayList<FarmMap>();
        adapter = new MapArrayAdapterKotlin(this, farmMaps);
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FarmMap clicked = (FarmMap) listView.getItemAtPosition(position);
                Intent intent = new Intent(MapSelectActivity.this, clicked.getClass());
                startActivity(intent);
            }
        });

        setTitle("Hunting areas");
        TextView textView = findViewById(R.id.textView);

        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if(timeOfDay >= 5 && timeOfDay < 7){
            addEveningMaps();
            textView.setText("Morning");
        }else if(timeOfDay >= 7 && timeOfDay < 16){
            addDayMaps();
            textView.setText("Daytime");
        }else if(timeOfDay >= 16 && timeOfDay < 19){
            addEveningMaps();
            textView.setText("Evening");
        }else {
            addNightMaps();
            textView.setText("Nighttime");
        }

    }

    private void addNightMaps() {
        farmMaps.add(new MapAmidaForestNight());
    }

    private void addDayMaps() {
        farmMaps.add(new MapAmidaForestDay());
    }

    private void addEveningMaps() {
        farmMaps.add(new MapAmidaForestEvening());
    }

}
