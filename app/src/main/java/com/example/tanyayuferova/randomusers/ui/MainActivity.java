package com.example.tanyayuferova.randomusers.ui;

import android.content.Intent;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.tanyayuferova.randomusers.R;
import com.example.tanyayuferova.randomusers.data.UsersDatabase;
import com.example.tanyayuferova.randomusers.entity.User;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<User>>,
        UsersAdapter.OnClickUserHandler {

    public static String LOG_TAG = MainActivity.class.getName();
    protected static final int USERS_AMOUNT = 100;
    public static final String EXTRA_USER = "extra.user";
    public static final int USERS_LOADER_ID = 1;

    protected RecyclerView recyclerView;
    protected ProgressBar progressBar;
    protected UsersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UsersAdapter(this);
        recyclerView.setAdapter(adapter);

        getSupportLoaderManager().initLoader(USERS_LOADER_ID, null, this);
    }

    @Override
    public Loader<List<User>> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<List<User>>(this) {
            List<User> data = null;

            @Override
            protected void onStartLoading() {
                if (data != null) {
                    deliverResult(data);
                } else {
                    progressBar.setVisibility(ProgressBar.VISIBLE);
                    forceLoad();
                }
            }

            @Override
            public List<User> loadInBackground() {
                return UsersDatabase.getUsers(USERS_AMOUNT);
            }

            public void deliverResult(List<User> data) {
                this.data = data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<List<User>> loader, List<User> data) {
        adapter.setData(data);
        progressBar.setVisibility(ProgressBar.INVISIBLE);
    }

    @Override
    public void onLoaderReset(Loader<List<User>> loader) {
        adapter.setData(null);
    }

    @Override
    public void onClickUser(View view, User user) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(EXTRA_USER, user);
        startActivity(intent);
    }
}
