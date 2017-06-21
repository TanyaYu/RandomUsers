package com.example.tanyayuferova.randomusers.dataAdapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.tanyayuferova.randomusers.R;
import com.example.tanyayuferova.randomusers.databinding.ListItemBinding;
import com.example.tanyayuferova.randomusers.entity.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Adapter for browsing users
 * Created by Tanya Yuferova on 5/23/2017.
 */

public class UsersDataAdapter<T extends User> extends RecyclerView.Adapter<UsersDataAdapter.ViewHolder> {
    public static String LOG_TAG = UsersDataAdapter.class.getName();

    List<T> data = new ArrayList<>();
    Context mContext;
    View.OnClickListener onItemClickListener;

    public UsersDataAdapter(Context context) {
        super();
        mContext=context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ListItemBinding binding;
        ImageView photo;

        public ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            photo = (ImageView)itemView.findViewById(R.id.photo);
        }

        public void setUser(User user) {
            binding.setUser(user);
        }

        public User getUser() {
            return binding.getUser();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(onItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        T item = getItem(position);
        holder.setUser(item);
        Picasso.with(mContext).load(item.getPhotoThumbnail().getUrlString()).into(holder.photo);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public T getItem(int position) {
        return data.get(position);
    }

    public void addAll(Collection<T> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void add(T item) {
        data.add(item);
        notifyDataSetChanged();
    }

    public void remove(T item) {
        int position = data.indexOf(item);
        data.remove(item);
        notifyItemRemoved(position);
    }

    public View.OnClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
