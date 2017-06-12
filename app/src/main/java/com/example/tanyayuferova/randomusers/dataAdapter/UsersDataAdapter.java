package com.example.tanyayuferova.randomusers.dataAdapter;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
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
        LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        textViewParams.gravity = Gravity.CENTER_VERTICAL;
        LinearLayout.LayoutParams imageViewParams = new LinearLayout.LayoutParams(150, 150);
        imageViewParams.setMargins(0, 5, 10, 5);


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
            linearLayout.addView(imageView, 0, imageViewParams);
        }
        imageView.setImageBitmap(item.getPhotoThumbnail().getBitmap());

        //Full Name
        textView = (TextView) linearLayout.getChildAt(1);
        if(textView == null){
            textView = new TextView(getContext());
            linearLayout.addView(textView, 1, textViewParams);
            textView.setTextSize(20);
        }
        textView.setText(item.getFullName());

        return (convertView);
    }
}
