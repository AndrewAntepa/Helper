package com.example.helper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.LinkedList;

public class StartActivity extends AppCompatActivity {
    ListView list;
    ExtendedFloatingActionButton mapButton, balls;
    FloatingActionButton addButton;
    Intent intentNew;
    LinkedList<Place> plays;
    MyOpenHelper myOpenHelper;
    SQLiteDatabase sdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mapButton = findViewById(R.id.mapButton);
        addButton = findViewById(R.id.addButton);
        list = findViewById(R.id.list);
        balls = findViewById(R.id.balls);
        intentNew = new Intent(StartActivity.this, AddPlace.class);

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this, Map.class);
                startActivity(intent);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentNew);
            }
        });

        plays = new LinkedList<>();
        plays.add(new Place("Снести здание", 52.2562, 104.2650));
        plays.add(new Place("Убрать мусор", 52.2258, 104.3226));
        plays.add(new Place("Убрать мусор", 52.2507, 104.3458));
        plays.add(new Place("Убрать мусор", 52.2808, 104.2732));
        for (int i = 0; i < plays.size(); i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(MyOpenHelper.COLUMN_NAME, plays.get(i).name);
            contentValues.put(MyOpenHelper.COLUMN_LAT, plays.get(i).lat);
            contentValues.put(MyOpenHelper.COLUMN_LON, plays.get(i).lon);
            sdb.insert(MyOpenHelper.TABLE_NAME, null, contentValues);
        }
    }
}