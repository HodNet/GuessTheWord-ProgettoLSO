package com.example.guesstheword.ui.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.guesstheword.databinding.ActivityMenuBinding;

public class MenuActivity extends AppCompatActivity {
    private ActivityMenuBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = com.example.guesstheword.databinding.ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void goToFindGameActivity(View v) {
        Intent switchActivities = new Intent(this, FindGameActivity.class);
        startActivity(switchActivities);
    }

    public void goToCreateGameActivity(View v) {
        Intent switchActivities = new Intent(this, CreateGameActivity.class);
        startActivity(switchActivities);
    }
}
