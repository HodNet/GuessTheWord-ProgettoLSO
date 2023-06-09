package com.example.guesstheword.logic;

import android.graphics.Color;

import androidx.annotation.NonNull;

import com.example.guesstheword.ui.game.MessageNotificationView;
import com.example.guesstheword.ui.game.MessageReceivedView;
import com.example.guesstheword.ui.game.MessageSentView;

import java.util.LinkedList;
import java.util.List;

public class GameChat {
    private final Player mainPlayer;
    private final Room room;
    private Game currentGame;
    private LinkedList<ChatMessage> chat;
    private long initialTime;

    /**
     * Constructor called when the room is first created
     *
     * @param host Player which created the room
     * @param room the room just created
     */
    public GameChat(@NonNull Player host, @NonNull Room room) {
        this.mainPlayer = host;
        this.room = room;
        currentGame = null;
        chat = new LinkedList<ChatMessage>();
    }

    /**
     * Constructor called when a player enter in a pre-existing room
     *
     * @param mainPlayer  Player entered in the room
     * @param room        the pre-existing room
     * @param currentGame current game played, if exists (can be null)
     */
    public GameChat(@NonNull Player mainPlayer, @NonNull Room room, Game currentGame) {
        this.mainPlayer = mainPlayer;
        this.room = room;
        this.currentGame = currentGame;
        chat = new LinkedList<ChatMessage>();
    }

    /*
     * Getters
     */
    public List<ChatMessage> getChat() {
        return chat;
    }

    /**
     * This function must be called every time the main player (the user) send a message
     * in chat.
     * It does mainly 3 things:
     * 1. check if the main player in the message didn't guess the word.
     * 2. if he didn't, it adds the message to the chat UI.
     * 3. otherwise write in chat that the main player won and finishes the round.
     *
     * @param message sent by the main player, the user
     * @return the message to send to the server
     */
    public ServerMessage SendMessage(@NonNull String message) {
        if (mainPlayer.getState() == PlayerState.CHOOSER)
            throw new IllegalStateException("Chooser can't send messages");
        if (mainPlayer.getState() == PlayerState.SPECTATOR)
            throw new IllegalStateException("Spectator can't send messages");

        ServerMessage serverMessage;
        if (room.isGaming())
            serverMessage = new ServerMessage(message, currentGame.getWord(), mainPlayer);
        else
            serverMessage = new ServerMessage(message, null, mainPlayer);
        if (serverMessage.isGuessed()) {
            String notification = mainPlayer.getUsername() + " guessed the word! (+ " +
                    currentGame.getPointsForGuesser() + " points)";
            chat.add(new MessageNotificationView(notification, Color.GREEN));
            finishGame(mainPlayer);
        } else {
            chat.add(new MessageSentView(serverMessage));
        }
        return serverMessage;
    }

    /**
     * This function must be called every time is received a message from the server by other players.
     * It does mainly 3 things:
     * 1. check if the player in the message didn't guess the word.
     * 2. if he didn't, it adds the message to the chat UI.
     * 3. otherwise write in chat that this player won and finishes the round.
     *
     * @param serverMessage sent by the server
     */
    public void ReceiveMessage(@NonNull ServerMessage serverMessage) {
        if (serverMessage.isGuessed()) {
            Player winner = serverMessage.getSender();
            String notification = winner.getUsername() + " guessed the word! (+ " +
                    currentGame.getPointsForGuesser() + " points)";
            chat.add(new MessageNotificationView(notification, Color.GREEN));
            finishGame(winner);
        } else {
            chat.add(new MessageReceivedView(serverMessage));
        }
    }

    /**
     * This function is used to terminate a game and is called or when a player guess the word
     * (the guesser wins) or when nobody guesses the word but the time finishes (the chooser wins)
     */
    private void finishGame(Player winner) {
        switch (winner.getState()) {
            case GUESSER:
                winner.addPoints(currentGame.getPointsForGuesser());
                break;
            case CHOOSER:
                winner.addPoints(currentGame.getPointsForChooser());
                break;
        }
        room.setIsInGame(false);
        room.resetStateOfAllPlayers();
        currentGame = null;
        if (room.getNumberOfPlayers() > 1) {
            startChoosingPeriod();
        }
    }

    private void startChoosingPeriod() {
        initialTime = System.currentTimeMillis();
        //TODO: do a request to the server for the next
        if (mainPlayer.equals(room.getChooser()))
    }

    /**
     * Function called when a there is a notification from the server about a player which joined
     * or left the room.
     * it adds or remove the player from the room and adds in the chat a message of notification
     * of that.
     * Furthermore, if is there only 1 player the game can't start, the count down for the
     * start of the game start when there are at least 2 players. So it also checks if the
     * players become 2 and if they are then it also start the timer. The game will start in
     * "WAIT_TIME" seconds (unless the players are less than 2 again)
     *
     * @param serverNotification
     */
    public void manageServerNotification(ServerNotification serverNotification) {
        switch (serverNotification.getWhatHappened()) {
            case JOINED:
                if (room.getNumberOfPlayers() == 2)
                    initialTime = System.currentTimeMillis();
                if (room.isGaming())
                    serverNotification.getPlayer().setState(PlayerState.SPECTATOR);
                room.addPlayer(serverNotification.getPlayer());
                chat.add(new MessageNotificationView(serverNotification));
                break;
            case LEFT:
                room.removePlayer(serverNotification.getPlayer());
                chat.add(new MessageNotificationView(serverNotification));
                break;
        }
    }

    public void updateGame() {
        if (room.isGaming()) {
            if (currentGame.isRoundFinished(Room.TIME_PER_ROUND_IN_MILLISECONDS)) {
                char revealedLetter = currentGame.revealOneMoreLetter();
                String notification1 = "letter " + revealedLetter +
                        " revealed. Now the known word is " + currentGame.getIncompleteWord();
                chat.add(new MessageNotificationView(notification1, Color.YELLOW));
                if (currentGame.isWordFullRevealed()) {
                    String notification2 = "Nobody guessed the word. " + room.getChooser().getUsername() +
                            " wins the game! (+ " + currentGame.getPointsForChooser() + " points)";
                    chat.add(new MessageNotificationView(notification2, Color.GREEN));
                    finishGame(room.getChooser());
                }
            }
        } else {
            if (isChoosingTimeFinished(Room.CHOOSING_TIME_IN_MILLISECONDS)) {
                //TODO: send the random word to the server
            } else {

            }
        }
    }

    /**
     * @param choosingTime in milliseconds
     * @return true if the time for the chooser is finished (then you have to choose a random word for him), otherwise false
     */
    public boolean isChoosingTimeFinished(long choosingTime) {
        long currentTime = System.currentTimeMillis();
        long timePassed = currentTime - initialTime;
        return timePassed >= choosingTime;
    }

    private void startGame() {

    }

    /**
     * TODO: This function is called when a player exit the game. It must send a ServerNotification
     * to all the other players
     */
}