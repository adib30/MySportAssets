package com.example.mysportassets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button toAddAssets = findViewById(R.id.btnAddAssets);
        Button toListAssets = findViewById(R.id.btnViewList);

        //navigate to Add Assets Activity
        toAddAssets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,AddAssetsActivity.class);
            startActivity(intent);
            }
        });

        //navigate to List Assets Activity
        toListAssets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this,ListAssetsActivity.class);
                startActivity(intent2);
            }
        });
    }
}