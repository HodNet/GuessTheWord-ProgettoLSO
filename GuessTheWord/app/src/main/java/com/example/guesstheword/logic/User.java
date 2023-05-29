package com.example.guesstheword.logic;

import android.widget.ImageView;

import androidx.annotation.NonNull;

public class User {
    private final String email;
    private String password;
    private String username;
    private ImageView avatar;

    /**
     * Constructor called to create the profile of the main User.
     * @param email must respect the RFC 5322 email format
     * @param password at least 5 characters
     * @param username (unique attribute)
     * @param avatar chosen between n images
     */
    public User(@NonNull String email, @NonNull String password, @NonNull String username, @NonNull ImageView avatar) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.avatar = avatar;
    }

    /**
     * Constructor called to create an incomplete profile of the other Users of a room
     * @param username
     * @param avatar
     */
    public User(@NonNull String username, @NonNull ImageView avatar) {
        this.username = username;
        this.avatar = avatar;
        email = null;
        password = null;
    }

    /*
     * Getters
     */
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public ImageView getAvatar() {
        return avatar;
    }

    /*
     * Setters
     */
    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public void setAvatar(@NonNull ImageView avatar) {
        this.avatar = avatar;
    }
}
