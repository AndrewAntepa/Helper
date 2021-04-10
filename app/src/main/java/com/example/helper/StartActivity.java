package com.example.helper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.LinkedList;

public class StartActivity extends AppCompatActivity {
    ListView list;
    ExtendedFloatingActionButton mapButton, balls;
    FloatingActionButton addButton;
    Intent intentNew;
    LinkedList<Place> plays;
    MyOpenHelper myOpenHelper;
    SQLiteDatabase sdb;
    SimpleAdapter simpleAdapter;
    SharedPreferences sharedPreferences;
    private static final String STATUS = "status";
    int stat, count;

    LinkedList<HashMap<String, String>> linkedList = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mapButton = findViewById(R.id.mapButton);
        addButton = findViewById(R.id.addButton);
        list = findViewById(R.id.list);
        balls = findViewById(R.id.balls);
        intentNew = new Intent(StartActivity.this, AddPlace.class);

        //TODO sharedPreferences
        sharedPreferences = getSharedPreferences("pref", 0);

        //TODO работа с листвью
        String[] keyFrom = {"name", "description", "imageView"};
        int[] idTo = {R.id.name, R.id.description, R.id.imageView};
        simpleAdapter = new SimpleAdapter(this, linkedList, R.layout.list_itemn, keyFrom, idTo);
        list.setAdapter(simpleAdapter);

        LinkedList<String> element = new LinkedList();
        for (int i = 0; i < element.size(); i++) {
            HashMap<String, String> map = new HashMap<>();
            map.put("name", element.get(i));
            map.put("description", String.valueOf(R.id.description));
            map.put("imageView", String.valueOf(R.id.imageView));
            //map.put("imageView", )
            linkedList.add(map);
        }
        /*LinkedList<String> numbers = new LinkedList();
        if (!number.equals("")) {
            numbers.add(number.getText().toString());
            bulls = 1;
            cows = 1;
            for (int i = 0; i < numbers.size(); i++) {
                HashMap<String, String> map = new HashMap<>();
                map.put("numberbc", numbers.get(i));
                map.put("bulls", String.valueOf(bulls));
                map.put("cows", String.valueOf(cows));
                mapNumber.add(map);
            }
            simpleAdapter.notifyDataSetChanged();
            number.setText("");*/

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

//        plays = new LinkedList<>();
//        plays.add(new Place("Снести здание", 52.2562, 104.2650));
//        plays.add(new Place("Убрать мусор", 52.2258, 104.3226));
//        plays.add(new Place("Убрать мусор", 52.2507, 104.3458));
//        plays.add(new Place("Убрать мусор", 52.2808, 104.2732));
//        for (int i = 0; i < plays.size(); i++) {
//            ContentValues contentValues = new ContentValues();
//            contentValues.put(MyOpenHelper.COLUMN_NAME, plays.get(i).name);
//            contentValues.put(MyOpenHelper.COLUMN_LAT, plays.get(i).lat);
//            contentValues.put(MyOpenHelper.COLUMN_LON, plays.get(i).lon);
//            sdb.insert(MyOpenHelper.TABLE_NAME, null, contentValues);
//        }
    }
}