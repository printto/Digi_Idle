package com.printto.printmov.digi_idle.activities;

import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.printto.printmov.digi_idle.Player;
import com.printto.printmov.digi_idle.R;
import com.printto.printmov.digi_idle.adapters.FeedRecyclerViewAdapter;
import com.printto.printmov.digi_idle.adapters.FeedRecyclerViewAdapterBU;
import com.printto.printmov.digi_idle.digimon.Digimon;
import com.printto.printmov.digi_idle.item.Food;
import com.printto.printmov.digi_idle.item.Item;
import com.printto.printmov.digi_idle.utils.SaveManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class FeedActivity extends AppCompatActivity {

    ImageView profilePic;
    Digimon digimon;
    Player player;
    SaveManager saveManager;
    RecyclerView listView;

    boolean slidingup = true;

    FeedRecyclerViewAdapter adapter;
    Map<Item, Integer> foodItems = new HashMap<Item, Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        saveManager = new SaveManager();
        saveManager.loadState();
        digimon = saveManager.getDigimon();
        player = saveManager.getPlayer();

        profilePic = findViewById(R.id.profilePic);
        profilePic.setImageResource(digimon.getProfilePic());

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                final ObjectAnimator animatorY;
                if(slidingup){
                    animatorY = ObjectAnimator.ofFloat(profilePic, "translationY", 20);
                }
                else {
                    animatorY = ObjectAnimator.ofFloat(profilePic, "translationY", -20);
                }
                animatorY.setDuration(1990);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        animatorY.start();
                    }
                });
                slidingup = !slidingup;
            }
        },0,2000);



        for(Map.Entry<Item, Integer> item : player.getItems().entrySet() ){
            if(item.getKey().getClass() == Food.class) foodItems.put(item.getKey(), item.getValue());
        }

        adapter = new FeedRecyclerViewAdapter(this ,player.getItems());
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),3);
        listView.setLayoutManager(gridLayoutManager);

    }
}
