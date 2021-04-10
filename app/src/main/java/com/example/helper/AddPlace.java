package com.example.helper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddPlace extends AppCompatActivity implements ValueEventListener {
    Button place, photo, gallery, makePhoto, myPoint, drPoint;
    FloatingActionButton save;
    EditText news;
    Uri mUri;
    ImageView image;
    private static final int CODE = 100;
    private final int Pick = 1;

    DatabaseReference dbRef;
    String PLAYS = "plays";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plays_add);

        place = findViewById(R.id.place);
        photo = findViewById(R.id.photo);
        news = findViewById(R.id.news);
        image = findViewById(R.id.image);
        save = findViewById(R.id.save);
        Dialog dialog = new Dialog(AddPlace.this);
        Dialog dialogPoint = new Dialog(AddPlace.this);

        dialogPoint.setContentView(R.layout.choose_location);
        dialog.setContentView(R.layout.camera);

        myPoint = dialogPoint.findViewById(R.id.myPoint);
        drPoint = dialogPoint.findViewById(R.id.drPoint);

        makePhoto = dialog.findViewById(R.id.camera);
        gallery = dialog.findViewById(R.id.gallery);

        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogPoint.show();
            }
        });

        //TODO добавить геолокацию моего местоположения
        myPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogPoint.dismiss();
            }
        });

        //TODO добавить геолокация через точку на карте
        drPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogPoint.dismiss();
            }
        });

        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
        makePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
                startActivityForResult(intent, CODE);
                dialog.dismiss();
            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, Pick);
                dialog.dismiss();
            }
        });


        dbRef = FirebaseDatabase.getInstance().getReference(PLAYS);
    }

    public void addBaseData(){
        String id = dbRef.getKey();

    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }

    //TODO добавление фото через галлерею и камеру
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            image.setImageBitmap(imageBitmap);
            switch (requestCode) {
                case Pick:
                    try {
                        final Uri imageUri = data.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        image.setImageBitmap(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
        switch (requestCode) {
            case Pick:
                if (resultCode == RESULT_OK) {
                    try {
                        final Uri imageUri = data.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        image.setImageBitmap(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
        }
    }
}

