package com.example.mysportassets;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper  {

    public static final String DATABASE_NAME ="sport_assets";
    public static final String TABLE_NAME ="assetsTable";
    public static final String col1 ="id";
    public static final String col2 ="assets";
    public static final String col3 ="quantity";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CATEGORIES_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + col1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + col2 + " TEXT, "+ col3 + " TEXT)";
        db.execSQL(CREATE_CATEGORIES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean addData(String addAssets, int addQuantity){ //sintaks tambah data
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("sport_assets",addAssets);
        contentValues.put("quantity",addQuantity);
        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1){
            return false;
        }else {
            return true;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME ;
        Cursor data = db.rawQuery(query,null);
        return data;
    }

    public Cursor getDataForUpdate(String udAssets){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " +TABLE_NAME, null);
        return data;
    }

    public Cursor updateData(String udAsset, String udId, int udquantity){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + col2 + " = '" + udAsset + "'";
        String query2 = "SELECT * FROM " + TABLE_NAME + " WHERE " + col3 + " = '" + udquantity + "'";
        String query3 = "SELECT * FROM " + TABLE_NAME + " WHERE " + col1 + " = '" + udId + "'";
        Cursor data1 = db.rawQuery(query, null);
        return data1;
    }

    public boolean deleteData(String udAsset){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + col2 + " = '" + udAsset + "'";
        db.execSQL(query);
        return true;
    }


    public Cursor getData(Object o) {
        return null;
    }
}
