package com.example.tanyayuferova.randomusers.entity;

import com.example.tanyayuferova.randomusers.StringUtils;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * User location entity
 * Created by Tanya Yuferova on 5/22/2017.
 */

public class Location implements Parcelable {

    private String street;
    private String city;
    private String state;
    private String postCode;

    public Location() {
    }

    public Location(String street, String city, String state, String postCode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.postCode = postCode;
    }

    public Location(Parcel in) {
        String[] data = new String[4];
        in.readStringArray(data);
        street = data[0];
        city = data[1];
        state = data[2];
        postCode = data[3];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] { street, city, state, postCode });
    }

    public static final Parcelable.Creator<Location> CREATOR = new Parcelable.Creator<Location>() {

        @Override
        public Location createFromParcel(Parcel source) {
            return new Location(source);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

    public String getStreet() {
        return StringUtils.allWordsFirstSymbolsToUpperCase(street);
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return StringUtils.firstSymbolToUpperCase(city);
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return StringUtils.firstSymbolToUpperCase(state);
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getFullDescription(){
        return getStreet() + " " + getCity() + ", " + getState() + " " + getPostCode();
    }

}
