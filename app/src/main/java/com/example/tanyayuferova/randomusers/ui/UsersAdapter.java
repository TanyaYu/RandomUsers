package com.example.tanyayuferova.randomusers.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tanyayuferova.randomusers.databinding.UserItemBinding;
import com.example.tanyayuferova.randomusers.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter for browsing users
 * Created by Tanya Yuferova on 5/23/2017.
 */

public class UsersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface OnClickUserHandler {
        void onClickUser(View view, User user);
    }

    private List<User> data = new ArrayList<>();
    private OnClickUserHandler clickHandler;

    public UsersAdapter() {
    }

    public UsersAdapter(OnClickUserHandler clickHandler) {
        this.clickHandler = clickHandler;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        UserItemBinding binding;

        public UserViewHolder(UserItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(User item) {
            binding.setUser(item);
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickHandler.onClickUser(binding.getRoot(), binding.getUser());
        }
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        UserItemBinding itemBinding = UserItemBinding.inflate(layoutInflater, parent, false);
        return new UserViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        User item = data.get(position);
        ((UserViewHolder)holder).bind(item);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public User getItem(int position) {
        return data == null ? null : data.get(position);
    }

    public void setData(List<User> data) {
        this.data = data;
    }
}
