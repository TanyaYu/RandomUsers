package com.example.tanyayuferova.randomusers.ui;

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

import com.example.tanyayuferova.randomusers.R;
import com.example.tanyayuferova.randomusers.databinding.ActivityDetailsBinding;
import com.example.tanyayuferova.randomusers.emailer.UserInfoEmailSender;
import com.example.tanyayuferova.randomusers.entity.User;

public class DetailsActivity extends AppCompatActivity {

    protected ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        User user = getIntent().getParcelableExtra(MainActivity.EXTRA_USER);
        binding.setUser(user);
    }

    public void backBtnOnClick(View view) {
        finish();
    }

    public void openDialer(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + binding.getUser().getPhone()));
        startActivity(intent);
    }

    public void emailOnClick(View view) {
        String email = binding.getUser().getEmail();
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
                                UserInfoEmailSender sender = new UserInfoEmailSender(DetailsActivity.this, binding.getUser(), email);
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
