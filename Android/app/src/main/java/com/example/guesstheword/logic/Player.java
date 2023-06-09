package com.example.guesstheword.logic;

import android.widget.ImageView;

import androidx.annotation.NonNull;

public class Player {
    private PlayerState state;
    private int points;
    private final User user;

    /**
     * Constructor called to create the main player who enter a new room
     */
    public Player(@NonNull User user, boolean isRoomGaming) {
        this.user = user;
        state = isRoomGaming ? PlayerState.SPECTATOR : null;
        points = 0;
    }

    /**
     * Constructor called to create the other players of the room
     * @param avatar chosen between 16 images (must be a number between 1-16, you can use the MACROS of the User class)
     */
    public Player(PlayerState state, int points, @NonNull String username, int avatar) {
        this.state = state;
        this.points = points;
        user = new User(username, avatar);
    }

    /**
     * Constructor called when a new player joins the room
     * @param avatar chosen between 16 images (must be a number between 1-16, you can use the MACROS of the User class)
     */
    public Player(@NonNull String username, int avatar) {
        user = new User(username, avatar);
        state = null;
        points = 0;
    }

    /*
     * Setters
     */
    public void setState(PlayerState state) {
        this.state = state;
    }

    public void addPoints(int points) {
        this.points = this.points + points;
    }

    public void resetPoints() {
        points = 0;
    }

    /*
     * Getters
     */
    public PlayerState getState() {
        return state;
    }

    public int getPoints() {
        return points;
    }

    public String getUsername() {
        return user.getUsername();
    }

    /**
     * @return avatar chosen between 16 images (a number between 1-16)
     */
    public int getAvatar() {
        return user.getAvatar();
    }

    /*
     * Override methods
     */
    public boolean equals(Player other) {
        return getUsername().equals(other.getUsername());
    }
}