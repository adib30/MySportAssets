package com.example.mysportassets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListAssetsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_assets);

        final ListView lvAssetList = findViewById(R.id.lvAssets);
        final DatabaseHelper mDatabaseHelper = new DatabaseHelper(this);

        final Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();

        while (data.moveToNext()){
            listData.add(data.getString(1));
            listData.add(data.getString(2));
        }

        ListAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listData);
        lvAssetList.setAdapter(adapter);

        lvAssetList.setClickable(true);
        lvAssetList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = lvAssetList.getItemAtPosition(position);
                Cursor data = mDatabaseHelper.getData(o);
                while (data.moveToNext()){
                    o = data.getInt(0);
                }


            }
        });
    }
}