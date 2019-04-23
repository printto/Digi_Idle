package com.printto.printmov.digi_idle.activities;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.printto.printmov.digi_idle.Player;
import com.printto.printmov.digi_idle.R;
import com.printto.printmov.digi_idle.adapters.FeedRecyclerViewAdapter;
import com.printto.printmov.digi_idle.digimon.Digimon;
import com.printto.printmov.digi_idle.item.Food;
import com.printto.printmov.digi_idle.item.Item;
import com.printto.printmov.digi_idle.utils.SaveManager;
import com.printto.printmov.digi_idle.utils.StepTestActivity;
import com.printto.printmov.digi_idle.values.DigimonForms;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class FeedActivity extends AppCompatActivity {

    ImageView profilePic;
    Digimon digimon;
    Player player;
    SaveManager saveManager;
    RecyclerView recyclerView;
    Timer timer;

    boolean slidingup = true;

    FeedRecyclerViewAdapter adapter;
    Map<Item, Integer> foodItems = new HashMap<Item, Integer>();

    AppCompatActivity thisActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        setTitle("Inventory");

        saveManager = new SaveManager();
        saveManager.loadState();
        digimon = saveManager.getDigimon();
        player = saveManager.getPlayer();

        profilePic = findViewById(R.id.profilePic);
        profilePic.setImageResource(digimon.getProfilePic());

        thisActivity = this;

        timer = new Timer();
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

        adapter = new FeedRecyclerViewAdapter(thisActivity, player.getItems(), new FeedListener(), new ItemDescListener());

        recyclerView = findViewById(R.id.listView);
        recyclerView.setAdapter(adapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),3);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    public void makeToast(String text){
        Toast.makeText(thisActivity,text,Toast.LENGTH_SHORT).show();
    }

    class FeedListener implements FeedRecyclerViewAdapter.ClickListener {
        @Override
        public void onItemClick(int position, @NotNull View v) {
            Item item =  adapter.getItemFromPosition(position);
            Log.d("Feed listener",item.getName() + " selected");
            if(item instanceof Food && digimon.getForm() != DigimonForms.EGG){
                Food food = (Food) item;
                digimon.feed(food);
                player.removeItem(food);
                saveManager.saveState(digimon, player);
            }
            else if (digimon.getForm() == DigimonForms.EGG){
                makeToast("Please hatch the egg first.");
            }
            else {
                makeToast("This item is not edible.");
            }
            saveManager.loadState();
            player = saveManager.getPlayer();
            adapter = new FeedRecyclerViewAdapter(thisActivity, player.getItems(), new FeedListener(), new ItemDescListener());
            recyclerView.setAdapter(adapter);
        }
    }
    class ItemDescListener implements FeedRecyclerViewAdapter.LongClickListener {
        @Override
        public void onItemLongClick(int position, @NotNull View v) {
            Item item =  adapter.getItemFromPosition(position);
            Intent intent = new Intent(thisActivity, ItemInfoActivity.class);
            intent.putExtra("id", item.getID());
            intent.putExtra("name", item.getName());
            intent.putExtra("desc", item.getDesc());
            intent.putExtra("picture", item.getPicture());
            saveManager.loadState();
            player = saveManager.getPlayer();
            int itemCount = player.getItems().get(item);
            intent.putExtra("count", itemCount);
            startActivity(intent);
        }
    }

    @Override
    public void finish() {
        timer.cancel();
        super.finish();
    }

}
