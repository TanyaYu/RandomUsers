package com.example.tanyayuferova.randomusers.emailer;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.tanyayuferova.randomusers.entity.User;

import java.net.URL;

/**
 * Sends email with user information
 * Created by Tanya Yuferova on 5/31/2017.
 */

public class UserInfoEmailSender extends AsyncTask<Object, String, Boolean> {
    public static String LOG_TAG = UserInfoEmailSender.class.getSimpleName();

    private ProgressDialog WaitingDialog;
    private Context mainContext;
    private User user;
    private String addresseeEmail;

    public UserInfoEmailSender(Context mainContext, User user, String addresseeEmail) {
        this.mainContext = mainContext;
        this.user = user;
        this.addresseeEmail = addresseeEmail;
    }

    @Override
    protected void onPreExecute() {
        WaitingDialog = ProgressDialog.show(mainContext, "Sending data", "Email sending...", true);
    }

    @Override
    protected void onPostExecute(Boolean result) {
        WaitingDialog.dismiss();
        String sendingResult = result ? "Sending completed successfully" : "Error sending email";
        Toast.makeText(mainContext, sendingResult, Toast.LENGTH_LONG).show();
    }

    @Override
    protected Boolean doInBackground(Object... params) {
        Boolean result = true;
        try {
            String from = "user.info.notification@gmail.com";
            String to = addresseeEmail;

            String subject = "Information about " + user.getFullName();
            String content = "Full name: " + user.getFullName() +
                    "\nGender: " + user.getGender() +
                    "\nNationality: " + user.getNationality() +
                    "\nEmail: " + user.getEmail() +
                    "\nPhone: " + user.getPhone() +
                    "\nAddress: " + user.getLocation().getFullDescription();

            URL photoUrl = new URL(user.getPhotoLarge().getUrlString());

            EmailSender sender = new EmailSender("user.info.notification@gmail.com", "$user_info_notification");
            result = sender.sendMail(subject, content, from, to, photoUrl, "UserPhoto.jpg");
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
            result = false;
        }
        return result;
    }
}
