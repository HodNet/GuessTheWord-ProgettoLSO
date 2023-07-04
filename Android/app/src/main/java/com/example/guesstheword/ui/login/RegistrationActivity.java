package com.example.guesstheword.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import com.example.guesstheword.databinding.ActivityRegistrationBinding;

public class RegistrationActivity extends AppCompatActivity {
    private ActivityRegistrationBinding binding;

    @Override
    public void onCreate(Bundle savedIstanceState) {
        super.onCreate(savedIstanceState);

        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final ImageView mainAvatarImageView = binding.imageViewAvatar;
        final EditText usernameEditText = binding.registrationUsername;
        final GridLayout avatarsGrid = binding.avatarsGrid;
        final EditText emailEditText = binding.registrationEmail;
        final EditText passwordEditText = binding.registrationPassword;
        final EditText repeatPasswordEditText = binding.registrationRepeatPassword;
        final Button signUpButton = binding.signUpButton;
        final ProgressBar loadingProgressBar = binding.registrationLoading;

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    private void updateUiWithUser() {
        //TODO
    }

    public void goToLoginActivity(View view) {
        Intent switchActivities = new Intent(this, LoginActivity.class);
        startActivity(switchActivities);
    }
}
