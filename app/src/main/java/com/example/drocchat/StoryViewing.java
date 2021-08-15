package com.example.drocchat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.drocchat.databinding.ActivityStoryViewingBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class StoryViewing extends AppCompatActivity {
ActivityStoryViewingBinding binding;
FirebaseAuth auth;
FirebaseDatabase database;
FirebaseStorage storage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStoryViewingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        auth = FirebaseAuth.getInstance();

        


    }
}