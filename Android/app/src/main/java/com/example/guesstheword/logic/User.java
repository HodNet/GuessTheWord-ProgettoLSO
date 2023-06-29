package com.example.guesstheword.logic;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;

public class User implements Parcelable {

    public static final int AVATAR_1 = 1;
    public static final int AVATAR_2 = 2;
    public static final int AVATAR_3 = 3;
    public static final int AVATAR_4 = 4;
    public static final int AVATAR_5 = 5;
    public static final int AVATAR_6 = 6;
    public static final int AVATAR_7 = 7;
    public static final int AVATAR_8 = 8;
    public static final int AVATAR_9 = 9;
    public static final int AVATAR_10 = 10;
    public static final int AVATAR_11 = 11;
    public static final int AVATAR_12 = 12;
    public static final int AVATAR_13 = 13;
    public static final int AVATAR_14 = 14;
    public static final int AVATAR_15 = 15;
    public static final int AVATAR_16 = 16;
    @Nullable
    private final String email;
    @Nullable
    private String password;
    private String username;
    private int avatar;

    /**
     * Constructor called to create the profile of the main User.
     *
     * @param email    must respect the RFC 5322 email format (unique attribute)
     * @param password at least 5 characters
     * @param username (this will be used as the User ID, as Primary key)
     * @param avatar   chosen between 16 images (must be a number between 1-16, you can use the MACROS of this class)
     */
    public User(@NonNull String email, @NonNull String password, @NonNull String username, int avatar) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.avatar = avatar;
    }

    /**
     * Constructor called to create an incomplete profile of the other Users of a room
     *
     * @param username (this will be used as the User ID, as Primary key)
     * @param avatar chosen between 16 images (must be a number between 1-16, you can use the MACROS of this class)
     */
    public User(@NonNull String username, int avatar) {
        this.username = username;
        this.avatar = avatar;
        email = null;
        password = null;
    }

    /**
     * Constructor to pass this object between Activities
     */
    public User(Parcel source) {
        email = source.readString();
        password = source.readString();
        username = source.readString();
        avatar = source.readInt();
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

    /**
     * @return avatar chosen between 16 images (a number between 1-16)
     */
    public int getAvatar() {
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

    /**
     * @param avatar chosen between 16 images (must be a number between 1-16, you can use the MACROS of this class)
     */
    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(username);
        dest.writeInt(avatar);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
