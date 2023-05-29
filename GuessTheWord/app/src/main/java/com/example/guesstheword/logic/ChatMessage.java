package com.example.guesstheword.logic;

import android.graphics.Color;

import androidx.annotation.NonNull;

/**
 * Message that will appear in chat
 */
public abstract class ChatMessage {
    private final String message;

    /**
     * The message "message" written by the player "sender";
     */
    public ChatMessage(@NonNull String message) {
        this.message = message;
    }

    /*
     * Getters
     */
    protected String getMessage() {
        return message;
    }
}