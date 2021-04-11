package com.example.helper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PartList extends AppCompatActivity {
    TextView name, description;
    Button discoverGeoBut, execute;
    ImageView imageView;
    int stat = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part_list);

        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        discoverGeoBut = findViewById(R.id.discoverGeoBut);
        execute = findViewById(R.id.execute);
        imageView = findViewById(R.id.imageView);

        execute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                stat = 1;
                intent = new Intent(PartList.this, StartActivity.class);
                intent.putExtra("status", stat);
                startActivity(intent);
            }
        });
    }
}