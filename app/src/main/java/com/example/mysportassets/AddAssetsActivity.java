package com.example.mysportassets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddAssetsActivity extends AppCompatActivity {

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assets);

        db = new DatabaseHelper(AddAssetsActivity.this);
        Button saveNote = findViewById(R.id.btnAddAssets);
        Button cancelSaveNote = findViewById(R.id.btnCancel);
        EditText assets = findViewById(R.id.assets);
        EditText assetsQuantity = findViewById(R.id.assetsQuantity);

        cancelSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assets.setText("");
                assetsQuantity.setText("");
            }
        });
        saveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addAsset = assets.getText().toString();
                String addQuantity = assetsQuantity.getText().toString();
                int quantity = Integer.parseInt(addQuantity);

                if (addAsset.length() != 0 | addQuantity.length() != 0) {
                    addData(addAsset, quantity);
                } else {
                    Toast.makeText(AddAssetsActivity.this, "fill the field", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

            private void addData(String addAsset, int quantity) { //untuk menambah data
                boolean addData = db.addData(addAsset, quantity);
                Intent intent = new Intent(AddAssetsActivity.this, MainActivity.class);
                startActivity(intent);
                if (addData) {
                    Toast.makeText(AddAssetsActivity.this, "Assets Added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddAssetsActivity.this, "FAILED", Toast.LENGTH_SHORT).show();
                }
            }
        }