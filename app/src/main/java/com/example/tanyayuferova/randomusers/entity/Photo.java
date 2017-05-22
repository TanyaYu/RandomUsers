package com.example.tanyayuferova.randomusers.entity;

import android.graphics.Bitmap;

/**
 * User photo entity
 * Created by Tanya Yuferova on 5/22/2017.
 */

public class Photo {

    private Bitmap thumbnail;
    private Bitmap medium;
    private Bitmap large;

    public Photo() {
    }

    public Bitmap getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Bitmap thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Bitmap getMedium() {
        return medium;
    }

    public void setMedium(Bitmap medium) {
        this.medium = medium;
    }

    public Bitmap getLarge() {
        return large;
    }

    public void setLarge(Bitmap large) {
        this.large = large;
    }
}
