package com.example.tanyayuferova.randomusers.activity;

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

import com.example.tanyayuferova.randomusers.R;
import com.example.tanyayuferova.randomusers.databinding.ActivityUserDetailsBrowseBinding;
import com.example.tanyayuferova.randomusers.emailer.UserInfoEmailSender;
import com.example.tanyayuferova.randomusers.entity.User;
import com.squareup.picasso.Picasso;

public class UserDetailsBrowse extends AppCompatActivity {

    protected User user;
    protected ActivityUserDetailsBrowseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details_browse);

        user = getIntent().getParcelableExtra("user");
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_details_browse);
        binding.setUser(user);
        Picasso.with(this).load(user.getPhotoLarge().getUrlString())
                .into((ImageView) findViewById(R.id.photo));
    }

    public void backBtnOnClick(View view) {
        finish();
    }

    public void openDialer(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + user.getPhone()));
        startActivity(intent);
    }

    public void emailOnClick(View view) {
        String email = user.getEmail();
        String subject = "Subject";
        String content = "Content";

        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("plain/text");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] { email });
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, content);
        startActivity(Intent.createChooser(emailIntent, "Send email"));
    }

    public void sendUserInfoBtnOnClick(View view) {
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.email_dialog, null);
        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(this);
        mDialogBuilder.setView(promptsView);
        final EditText emailView = (EditText) promptsView.findViewById(R.id.email);

        mDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String email = emailView.getText().toString();
                                UserInfoEmailSender sender = new UserInfoEmailSender(UserDetailsBrowse.this, user, email);
                                sender.execute();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        AlertDialog alertDialog = mDialogBuilder.create();
        alertDialog.show();
    }
}
