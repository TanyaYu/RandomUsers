package com.example.tanyayuferova.randomusers.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.tanyayuferova.randomusers.R;
import com.example.tanyayuferova.randomusers.databinding.ActivityDetailsBinding;
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

    public void emailBtnOnClick(View view) {
        String email = binding.getUser().getEmail();
        String subject = getString(R.string.email_subject);
        String content = getString(R.string.email_content);

        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("plain/text");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] { email });
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, content);
        startActivity(Intent.createChooser(emailIntent, getString(R.string.email_send)));
    }

    public void shareBtnOnClick(View view) {
        startActivity(Intent.createChooser(ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText(binding.getUser().getFullName()) //TODO more information
                .getIntent(), getString(R.string.action_share)));
    }
}
