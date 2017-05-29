package com.example.tanyayuferova.randomusers;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.tanyayuferova.randomusers.databinding.ActivityUserBrowseBinding;
import com.example.tanyayuferova.randomusers.entity.User;

public class UserBrowse extends AppCompatActivity {

    protected User user;
    protected ActivityUserBrowseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_browse);

        user = getIntent().getParcelableExtra("user");
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_browse);
        binding.setUser(user);

        ((ImageView) findViewById(R.id.photo)).setImageBitmap(user.getPhoto().getLarge());
    }

    public void backBtnOnClick(View view){
        finish();
    }

    public void openDialer(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+user.getPhone()));
        startActivity(intent);
    }

    public void emailOnClick(View view) {
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.email_dialog, null);
        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(this);
        mDialogBuilder.setView(promptsView);
        final EditText email = (EditText) promptsView.findViewById(R.id.email);

        mDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {

                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        AlertDialog alertDialog = mDialogBuilder.create();
        alertDialog.show();
    }
}
