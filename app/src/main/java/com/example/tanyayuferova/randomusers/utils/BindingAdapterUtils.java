package com.example.tanyayuferova.randomusers.utils;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Tanya Yuferova on 1/18/2018.
 */

public final class BindingAdapterUtils {

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView, String url) {
        Picasso.with(imageView.getContext()).load(url).into(imageView);
    }

    @BindingAdapter({"bind:imageUrl", "bind:error"})
    public static void loadImageError(ImageView view, String url, Drawable error) {
        Picasso.with(view.getContext())
                .load(url)
                .placeholder(error)
                .error(error)
                .into(view);
    }
}
