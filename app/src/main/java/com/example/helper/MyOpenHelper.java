package com.example.helper;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class MyOpenHelper extends SQLiteOpenHelper {
    private static final String NAME = "help";
    private static final int VERSION = 1;

    public static final String TABLE_NAME = "hepers";
    public static final String COLUMN_NAME = "spots";
    public static final String COLUMN_LAT = "lat";
    public static final String COLUMN_LON = "lon";


    @RequiresApi(api = Build.VERSION_CODES.P)
    public MyOpenHelper(@Nullable Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME
                + "( _id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT,"
                + COLUMN_LAT + " REAL, "
                + COLUMN_LON + " REAL);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}