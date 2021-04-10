package com.example.helper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Menu extends AppCompatActivity {
    ListView list;
    ExtendedFloatingActionButton mapButton, lentaButton;
    FloatingActionButton addButton;
    SimpleAdapter simpleAdapter;
    Intent intentNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mapButton = findViewById(R.id.mapButton);
        lentaButton = findViewById(R.id.lentaButton);
        addButton = findViewById(R.id.addButton);
        list = findViewById(R.id.list);
        intentNew = new Intent(Menu.this, MainActivity.class);

        lentaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Good", Toast.LENGTH_SHORT).show();

            }
        });

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, Map.class);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentNew);
            }
        });


        //simpleAdapter = new SimpleAdapter(this, )
    }
}