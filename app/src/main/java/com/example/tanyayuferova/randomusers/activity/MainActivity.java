package com.example.tanyayuferova.randomusers.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.example.tanyayuferova.randomusers.R;
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
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String urlString = "https://randomuser.me/api/?format=json&nat=us,fr,gb&results=100";
    protected static final int USERS_AMOUNT = 100;

    protected GridView gridView;
    protected ProgressBar progressBar;
    protected UsersDataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.gridView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        initDataAdapter();
        initClickListener();
    }

    protected void startUserBrowseActivity(User user){
        Intent intent = new Intent(this, UserDetailsBrowse.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    protected void initClickListener(){
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User user = (User) parent.getItemAtPosition(position);
                startUserBrowseActivity(user);
            }
        });
    }

    private class ParseJsonUsers extends AsyncTask<Void, Void, List<User>> {

        @Override
        protected List<User> doInBackground(Void... params) {
            List<User> result = new ArrayList<>();
            String json = getJson();
            if (json != null){
                result = readUsers(json);
            }
            return result;
        }

        private String getJson() {
            String resultJson = null;
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
                Log.e(LOG_TAG, e.getMessage());
                e.printStackTrace();
            }
            return resultJson;
        }

        private User readUser(JSONObject jsonObject) {
            User user = null;
            try {
                JSONObject name = jsonObject.getJSONObject("name");
                String firstName = name.getString("first");
                String lastName = name.getString("last");
                String email = jsonObject.getString("email");
                String gender = jsonObject.getString("gender");
                String phone = jsonObject.getString("phone");
                String nat = jsonObject.getString("nat");
                JSONObject photo = jsonObject.getJSONObject("picture");

                JSONObject jLocation = jsonObject.getJSONObject("location");
                Location location = new Location(jLocation.getString("street"), jLocation.getString("city"),
                        jLocation.getString("city"), jLocation.getString("city"));

                user = new User(firstName, lastName);
                user.setEmail(email);
                user.setGender(gender);
                user.setLocation(location);
                user.setPhone(phone);
                user.setNationality(nat);
                String large = photo.getString("large"),
                        medium = photo.getString("medium"),
                        thumbnail = photo.getString("thumbnail");
                user.setPhotoLarge(new Photo(large));
                user.setPhotoMedium(new Photo(medium));
                user.setPhotoThumbnail(new Photo(thumbnail));
            } catch (JSONException e) {
                Log.e(LOG_TAG, e.getMessage());
                e.printStackTrace();
            }
            return user;
        }

        private List<User> readUsers(String jsonString) {
            List<User> users = new ArrayList<>();
            try {
                JSONObject dataJsonObj = new JSONObject(jsonString);
                JSONArray results = dataJsonObj.getJSONArray("results");

                for(int i = 0; i < USERS_AMOUNT; i++) {
                    JSONObject result = results.getJSONObject(i);
                    users.add(readUser(result));
                }

            } catch (JSONException e) {
                Log.e(LOG_TAG, e.getMessage());
                e.printStackTrace();
            }
            return users;
        }

        @Override
        protected void onPostExecute(List<User> users) {
            super.onPostExecute(users);
            adapter.addAll(users);
            stopProgressBar();
        }
    }

    protected void initDataAdapter(){
        adapter = new UsersDataAdapter(getApplicationContext(),
                android.R.layout.simple_list_item_1);
        gridView.setAdapter(adapter);

        progressBar.setVisibility(ProgressBar.VISIBLE);
        new ParseJsonUsers().execute();
    }

    protected void stopProgressBar() {
        progressBar.setVisibility(ProgressBar.INVISIBLE);
    }
}
