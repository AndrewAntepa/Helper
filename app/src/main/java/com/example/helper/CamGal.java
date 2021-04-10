package com.example.helper;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class CamGal extends Fragment{
    private final int Pick = 1;
    private static final int CODE = 100;
    Uri mUri;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //создаем элемент интерфейса для активности на базе фрагмента
        View view = inflater.inflate(R.layout.activity_cam_gal, null);
        Button fromGallery = view.findViewById(R.id.fromGallery);
        Button makePhoto = view.findViewById(R.id.makePhoto);


        fromGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                startActivityForResult(intent, Pick);
                intent.setType("image/*");
                startActivityForResult(intent, Pick);
                intent.putExtra("image", Pick);
            }
        });
        makePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
                startActivityForResult(intent, CODE);
            }
        });
        return view;



    }
}