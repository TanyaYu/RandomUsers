package com.example.tanyayuferova.randomusers.entity;

import android.text.TextUtils;

import com.example.tanyayuferova.randomusers.StringUtils;

/**
 * User entity
 * Created by Tanya Yuferova on 5/22/2017.
 */

public class User {

    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String nationality;
    private String phone;
    private Location location;
    private Photo photo;

    public User() {
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return StringUtils.firstSymbolToUpperCase(firstName);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return StringUtils.firstSymbolToUpperCase(lastName);
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }
}
