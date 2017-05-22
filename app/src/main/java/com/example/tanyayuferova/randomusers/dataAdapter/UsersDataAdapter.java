package com.example.tanyayuferova.randomusers.dataAdapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tanyayuferova.randomusers.entity.User;

/**
 * Adapter for browsing users
 * Created by Tanya Yuferova on 5/23/2017.
 */

public class UsersDataAdapter<T extends User> extends ArrayAdapter<T> {
    public static String LOG_TAG = UsersDataAdapter.class.getName();


    public UsersDataAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        User item = getItem(position);

        LinearLayout.LayoutParams linearLParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams viewParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout linearLayout = (LinearLayout) convertView;

        TextView textView = null;
        ImageView imageView = null;

        if(convertView == null){
            convertView = new LinearLayout(getContext());
            linearLayout = (LinearLayout) convertView;
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setLayoutParams(linearLParams);
        }

        //Image photo
        imageView = (ImageView) linearLayout.getChildAt(0);
        if(imageView == null){
            imageView = new ImageView(getContext());
            linearLayout.addView(imageView, 0, viewParams);
        }
        imageView.setImageBitmap(item.getPhoto().getThumbnail() );

        //Full Name
        textView = (TextView) linearLayout.getChildAt(1);
        if(textView == null){
            textView = new TextView(getContext());
            linearLayout.addView(textView, 1, viewParams);
        }
        textView.setText(item.getFullName());

        return (convertView);
    }
}
