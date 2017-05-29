package com.example.tanyayuferova.randomusers.entity;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * User photo entity
 * Created by Tanya Yuferova on 5/22/2017.
 */

public class Photo implements Parcelable {

    private Bitmap thumbnail;
    private Bitmap medium;
    private Bitmap large;

    public Photo() {
    }

    public Photo(Parcel in) {
        Object[] data = new Object[3];
        data = in.readArray(Bitmap.class.getClassLoader());
        thumbnail = (Bitmap) data[0];
        medium = (Bitmap) data[1];
        large = (Bitmap) data[2];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeArray(new Object[] { thumbnail, medium, large });
    }

    public static final Parcelable.Creator<Photo> CREATOR = new Parcelable.Creator<Photo>() {

        @Override
        public Photo createFromParcel(Parcel source) {
            return new Photo(source);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };

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
