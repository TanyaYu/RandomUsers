package com.example.tanyayuferova.randomusers;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class UserBrowse extends AppCompatActivity {
    protected TextView fullName;
    protected ImageView photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_browse);
        fullName = (TextView) findViewById(R.id.fullName);
        photo = (ImageView) findViewById(R.id.photo);

        Intent intent = getIntent();

        fullName.setText(intent.getStringExtra("fullName"));
        photo.setImageBitmap((Bitmap) intent.getParcelableExtra("photo"));
    }
}
