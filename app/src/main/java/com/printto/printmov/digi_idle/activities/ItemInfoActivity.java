package com.printto.printmov.digi_idle.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.printto.printmov.digi_idle.R;

public class ItemInfoActivity extends AppCompatActivity {

    String id = "";
    String name = "";
    String desc = "";
    int picture = 0;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);

        picture = R.drawable.ch_9999;

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        name = intent.getStringExtra("name");
        desc = intent.getStringExtra("desc");
        picture = (int) intent.getExtras().get("picture");
        count = (int) intent.getExtras().get("count");

        setTitle("Item ID: "+id);

        ImageView pictureView = findViewById(R.id.imageView);
        TextView nameText = findViewById(R.id.nameText);
        TextView descText = findViewById(R.id.descText);
        TextView countText = findViewById(R.id.countText);

        pictureView.setImageResource(picture);
        nameText.setText(name);
        descText.setText(desc);

        if(count > 0){
            if(count > 1){
                countText.setText(count+" "+name+"s available in your storage.");
            }
            else {
                countText.setText(count+" "+name+" available in your storage.");
            }
        }
        else{
            countText.setVisibility(View.INVISIBLE);
        }

        Button back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
