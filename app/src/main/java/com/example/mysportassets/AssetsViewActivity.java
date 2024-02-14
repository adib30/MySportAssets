package com.example.mysportassets;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AssetsViewActivity extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;
    EditText updateAsset;
    EditText updateQuantity;
    private String udAsset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assets_view);



        //alt+enter
        updateAsset = findViewById(R.id.etEditAsset);
        updateQuantity = findViewById(R.id.etEditQuantity);
        Button btnUpdate = findViewById(R.id.btnUpdate);
        Button btnDelete = findViewById(R.id.btnDelete);

        mDatabaseHelper = new DatabaseHelper(this);
        final Cursor data = mDatabaseHelper.getDataForUpdate(udAsset);


        if(data != null){

            while (data.moveToNext())
            {

                updateAsset.setText(data.getString(1));
                updateQuantity.setText(data.getString(2));

            }
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String udAsset = updateAsset.getText().toString();
                String udQuantity = updateQuantity.getText().toString();

                int ud_quantity = Integer.parseInt(udQuantity);
                if (udAsset.length() != 0 | udQuantity.length() != 0) {
                    mDatabaseHelper.addData(udAsset,ud_quantity);
                } else {
                    Toast.makeText(AssetsViewActivity.this, "fill the field", Toast.LENGTH_SHORT).show();
                }

                mDatabaseHelper = new DatabaseHelper(AssetsViewActivity.this);
                if(data != null && data.moveToFirst()){


                    updateAsset.setText(data.getString(1));
                    String udId = data.getString(data.getColumnIndex("id"));

                    Cursor updateData = mDatabaseHelper.updateData(udAsset,udId,ud_quantity);

                    Toast.makeText(AssetsViewActivity.this,"Updated", Toast.LENGTH_SHORT).show(); //mengeluarkan toast updated

                }data.close();

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(AssetsViewActivity.this,"Delete Success", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showDialog(){
        AlertDialog.Builder builder= new AlertDialog.Builder(AssetsViewActivity.this);

        builder.setTitle("Confirm");
        builder.setMessage("Are you sure you want to delete?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String udAsset = updateAsset.getText().toString();
                mDatabaseHelper = new DatabaseHelper(AssetsViewActivity.this);
                boolean deleteData = mDatabaseHelper.deleteData(udAsset);
                Intent deleteIntent = new Intent(AssetsViewActivity.this,MainActivity.class);
                startActivity(deleteIntent);
                Toast.makeText(getApplicationContext(),"Delete Success",Toast.LENGTH_SHORT).show();

                dialog.dismiss();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}