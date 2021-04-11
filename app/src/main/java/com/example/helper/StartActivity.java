package com.example.helper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.LinkedList;

public class StartActivity extends AppCompatActivity {
    ListView list;
    ExtendedFloatingActionButton mapButton, balls;
    FloatingActionButton addButton;
    Intent intentNew;
    SimpleAdapter simpleAdapter;
    SharedPreferences sharedPreferences;
    private static final String STATUS = "status";
    int stat, count = 0;
    private static final String DESCRIPTION = "descrition";
    private static final String IMAGE = "imageView";


    LinkedList<HashMap<String, Object>> linkedList = new LinkedList<HashMap<String, Object>>();

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
        balls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getSharedPreferences("pref", 0);
                SharedPreferences.Editor editor =sharedPreferences.edit();
                editor.putInt("Status", count);
                editor.commit();
            }
        });


        //TODO работа с листвью


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

        //String str = getIntent().getStringExtra(DESCRIPTION);
        String str = "ПОМОГИТЕ УБРАТЬ МУСОР, ЭТО КАКОЙ ТО ТРЭШ";
        String sss = getIntent().getStringExtra(IMAGE);
        Bitmap bitmap = (Bitmap) getIntent().getParcelableExtra("BitmapImage");

        String[] keyFrom = {"name", "description", "imageView"};
        int[] idTo = {R.id.name, R.id.description, R.id.imageView};
        simpleAdapter = new SimpleAdapter(this, linkedList, R.layout.list_itemn, keyFrom, idTo);
        list.setAdapter(simpleAdapter);

        LinkedList<String> element = new LinkedList();

        for (int i = 0; i < element.size(); i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("name", "Иванов Иван");
            map.put("description", str);
            map.put("imageView", R.drawable.trash);
            //map.put("imageView", )
            linkedList.add(map);
        }
        simpleAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        int s = getIntent().getIntExtra(STATUS, 0);
        //int c = sharedPreferences.getInt("Status", 0);
        if (s != 0){
            count++;
            balls.setText(count);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "сработала onRestart", Toast.LENGTH_SHORT).show();

    }
}