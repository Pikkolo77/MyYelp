package com.example.myyelp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Yelp_DB extends SQLiteOpenHelper {


    private static final String DB_NAME = "YELP_DATABASE";
    private static final int DB_VERSION = 1;

    public Yelp_DB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE FAVORITES ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "PRICE TEXT,"
                + "PHONE TEXT,"
                + "STREET TEXT,"
                + "CITY TEXT,"
                + "STATE TEXT,"
                + "IMAGE TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addData(String name, String price, String phone, String street, String city, String state, String image){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME",name);
        contentValues.put("PRICE", price);
        contentValues.put("PHONE",phone);
        contentValues.put("STREET", street);
        contentValues.put("CITY",city);
        contentValues.put("STATE",state);
        contentValues.put("IMAGE", image);
        db.insert("FAVORITES", null, contentValues);
        db.close();
    }



    Cursor readAllData (){
        String select_query= "SELECT *FROM " +"FAVORITES";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(select_query,null);
        }
        return cursor;
    }

}
