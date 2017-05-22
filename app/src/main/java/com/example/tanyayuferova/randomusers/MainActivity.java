package com.example.tanyayuferova.randomusers;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.tanyayuferova.randomusers.dataAdapter.UsersDataAdapter;
import com.example.tanyayuferova.randomusers.entity.Location;
import com.example.tanyayuferova.randomusers.entity.Photo;
import com.example.tanyayuferova.randomusers.entity.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    public static String LOG_TAG = MainActivity.class.getName();
    public static final String urlString = "https://randomuser.me/api/?format=json";
    protected static final int USERS_AMOUNT = 100;

    protected GridView gridView;
    protected UsersDataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.gridView);

        initDataAdapter();
        initClickListener();
    }

    protected  void startUserBrowseActivity(User user, int position){
        Intent intent = new Intent(this, UserBrowse.class);
        intent.putExtra("fullName", user.getFullName());
        intent.putExtra("photo", user.getPhoto().getLarge());
        startActivity(intent);
    }

    protected void initClickListener(){
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User user = (User) parent.getItemAtPosition(position);
                startUserBrowseActivity(user, position);
            }
        });
    }

    private class ParseJsonUser extends AsyncTask<Void, Void, User> {

        @Override
        protected User doInBackground(Void... params) {
            String resultJson = "";
            try {
                URL url = new URL(urlString);

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                resultJson = buffer.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }

            JSONObject dataJsonObj = null;
            User user = null;

            try {
                dataJsonObj = new JSONObject(resultJson);
                JSONArray results = dataJsonObj.getJSONArray("results");

                JSONObject result = results.getJSONObject(0);
                JSONObject name = result.getJSONObject("name");
                String firstName = name.getString("first");
                String lastName = name.getString("last");
                String email = result.getString("email");
                String gender = result.getString("gender");
                String phone = result.getString("phone"); //todo or cell
                JSONObject photo = result.getJSONObject("picture");

                JSONObject jLocation = result.getJSONObject("location");
                Location location = new Location(jLocation.getString("street"), jLocation.getString("city"),
                        jLocation.getString("city"), jLocation.getString("city"));

                user = new User(firstName, lastName);
                user.setEmail(email);
                user.setGender(gender);
                user.setLocation(location);
                user.setPhone(phone);
                user.setPhoto(new Photo());
                user.getPhoto().setThumbnail(loadBitmap(photo.getString("thumbnail")));
                user.getPhoto().setMedium(loadBitmap(photo.getString("medium")));
                user.getPhoto().setLarge(loadBitmap(photo.getString("large")));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return user;
        }

        Bitmap loadBitmap(String url){
            Bitmap bitmap = null;
            try {
                InputStream in = new java.net.URL(url).openStream();
                bitmap = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e(LOG_TAG, e.getMessage());
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);
            adapter.add(user);
        }
    }

    protected void initDataAdapter(){
        adapter = new UsersDataAdapter(getApplicationContext(),
                android.R.layout.simple_list_item_1);
        gridView.setAdapter(adapter);

        int c = 0;
        while (c++ < USERS_AMOUNT){
            new ParseJsonUser().execute();
        }
    }
}
