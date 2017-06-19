package com.example.tanyayuferova.randomusers.dataAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tanyayuferova.randomusers.R;
import com.example.tanyayuferova.randomusers.entity.User;
import com.squareup.picasso.Picasso;

/**
 * Adapter for browsing users
 * Created by Tanya Yuferova on 5/23/2017.
 */

public class UsersDataAdapter<T extends User> extends ArrayAdapter<T> {
    public static String LOG_TAG = UsersDataAdapter.class.getName();

    Context mContext;

    public UsersDataAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        mContext=context;
    }

    static class ViewHolder {
        TextView fullName;
        ImageView photo;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        User item = getItem(position);

        ViewHolder viewHolder;

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.fullName = (TextView) convertView.findViewById(R.id.fullName);
            viewHolder.photo = (ImageView) convertView.findViewById(R.id.photo);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.fullName.setText(item.getFullName());
        Picasso.with(mContext).load(item.getPhotoThumbnail().getUrlString()).into(viewHolder.photo);

        return convertView;
    }
}
