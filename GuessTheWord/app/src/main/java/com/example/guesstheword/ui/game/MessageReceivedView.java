package com.example.guesstheword.ui.game;

import androidx.annotation.NonNull;

import com.example.guesstheword.logic.ChatMessage;
import com.example.guesstheword.logic.Player;
import com.example.guesstheword.logic.ServerMessage;

/**
 * Message received by the other players of the room
 */
public class MessageReceivedView extends ChatMessage {
    private final Player sender;

    public MessageReceivedView(@NonNull String message, @NonNull Player sender) {
        super(message);
        this.sender = sender;
    }

    /**
     * This constructor create a message from the information received by the server
     *
     * @param serverMessage message by the server
     */
    public MessageReceivedView(@NonNull ServerMessage serverMessage) {
        super(serverMessage.getMessage());
        sender = serverMessage.getSender();
    }

    /*
     * Getters
     */
    public Player getSender() {
        return sender;
    }
}
