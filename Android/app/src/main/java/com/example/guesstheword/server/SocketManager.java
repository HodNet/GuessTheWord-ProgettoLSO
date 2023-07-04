package com.example.guesstheword.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.net.Socket;

public class SocketManager extends Service {
    // Create a new socket and connect to the server
    private final Socket socket;

    SocketManager() throws IOException {
        socket = new Socket("192.168.121.243", 3000);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
