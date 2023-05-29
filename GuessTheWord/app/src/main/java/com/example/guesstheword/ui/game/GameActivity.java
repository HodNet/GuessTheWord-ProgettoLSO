package com.example.guesstheword.ui.game;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.guesstheword.databinding.ActivityGameBinding;

public class GameActivity extends AppCompatActivity {
    private ActivityGameBinding binding;

    private GameChatView gameChat;

    private EditText editGchatMessage;
    private Button buttonGchatSend;
    private String messageToSend;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = com.example.guesstheword.databinding.ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        gameChat = new GameChatView();

        editGchatMessage = binding.editGchatMessage;
        buttonGchatSend = binding.buttonGchatSend;

        buttonGchatSend.setEnabled(false);

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                messageChanged();
            }
        };
        editGchatMessage.addTextChangedListener(afterTextChangedListener);

        buttonGchatSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
    }

    private void messageChanged() {
        messageToSend = editGchatMessage.getText().toString();
        buttonGchatSend.setEnabled(!messageToSend.isEmpty());
    }
}


















