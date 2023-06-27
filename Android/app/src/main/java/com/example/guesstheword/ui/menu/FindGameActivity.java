package com.example.guesstheword.ui.menu;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.guesstheword.databinding.ActivityFindGameBinding;

public class FindGameActivity extends AppCompatActivity {
    private ActivityFindGameBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = com.example.guesstheword.databinding.ActivityFindGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
