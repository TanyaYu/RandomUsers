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
    protected TextView email;
    protected TextView phone;
    protected TextView nationality;
    protected TextView location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_browse);
        fullName = (TextView) findViewById(R.id.fullName);
        photo = (ImageView) findViewById(R.id.photo);
        email = (TextView) findViewById(R.id.email);
        phone = (TextView) findViewById(R.id.phone);
        nationality = (TextView) findViewById(R.id.nationality);
        location = (TextView) findViewById(R.id.location);

        Intent intent = getIntent();

        fullName.setText(intent.getStringExtra("fullName"));
        photo.setImageBitmap((Bitmap) intent.getParcelableExtra("photo"));
        email.setText(intent.getStringExtra("email"));
        phone.setText(intent.getStringExtra("phone"));
        nationality.setText(intent.getStringExtra("nationality"));
        location.setText(intent.getStringExtra("location"));
    }
}
