package com.example.drocchat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.drocchat.databinding.ActivitySelfProfileBinding;

public class SelfProfileActivity extends AppCompatActivity {
private ActivitySelfProfileBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySelfProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();


    }
}