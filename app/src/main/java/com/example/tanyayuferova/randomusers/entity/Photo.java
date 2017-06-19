package com.example.tanyayuferova.randomusers.entity;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * User photo entity
 * Created by Tanya Yuferova on 5/22/2017.
 */

public class Photo implements Parcelable {

    /* fixme bitmap is not used */
    @Deprecated
    private Bitmap bitmap;
    private String urlString;

    public Photo() {
    }

    public Photo(String urlString) {
        this.urlString = urlString;
    }

    public Photo(Parcel in) {
        bitmap = in.readParcelable(Bitmap.class.getClassLoader());
        urlString = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(bitmap, flags);
        dest.writeString(urlString);
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

    @Deprecated
    public Bitmap getBitmap() {
        return bitmap;
    }
    @Deprecated
    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getUrlString() {
        return urlString;
    }

    public void setUrlString(String urlString) {
        this.urlString = urlString;
    }
}
