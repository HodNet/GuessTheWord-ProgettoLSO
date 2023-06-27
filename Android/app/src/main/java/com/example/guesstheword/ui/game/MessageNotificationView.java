package com.example.guesstheword.ui.game;

import android.graphics.Color;

import androidx.annotation.NonNull;

import com.example.guesstheword.logic.ChatMessage;
import com.example.guesstheword.logic.ServerNotification;

public class MessageNotificationView extends ChatMessage {
    private final int color;

    public MessageNotificationView(@NonNull String message, int color) {
        super(message);
        this.color = color;
    }

    public MessageNotificationView(@NonNull ServerNotification serverNotification) {
        super(serverNotification.showWhatHappened());
        switch (serverNotification.getWhatHappened()) {
            case JOINED:
                color = Color.GREEN;
                break;
            case LEFT:
                color = Color.RED;
                break;
            default:
                color = Color.WHITE;
        }
    }

    public int getColor() {
        return color;
    }
}
