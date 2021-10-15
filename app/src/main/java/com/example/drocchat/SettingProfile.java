package com.example.drocchat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.drocchat.databinding.ActivitySettingProfileBinding;

public class SettingProfile extends AppCompatActivity {
    private ActivitySettingProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();


    }
}