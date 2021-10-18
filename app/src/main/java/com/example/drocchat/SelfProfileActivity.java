package com.example.drocchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.drocchat.Models.Users;
import com.example.drocchat.databinding.ActivitySelfProfileBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class SelfProfileActivity extends AppCompatActivity {
private ActivitySelfProfileBinding binding;
private FirebaseDatabase database;
private String receiverId;
private String userName;
private String status;
private String profilePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySelfProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        database = FirebaseDatabase.getInstance();

        receiverId = getIntent().getStringExtra("id");

        // Setting all the elements from database.
        database.getReference().child("Users").child(receiverId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users user = snapshot.getValue(Users.class);
                assert user != null;
                userName = user.getUsername();
                status = user.getStatus();
                profilePhoto = user.getProfilePic();
                binding.usersname.setText(userName + "'s");
                binding.userSetting.setText(userName);
                binding.statusSetting.setText(status);
                Picasso.get().load(profilePhoto).placeholder(R.drawable.login).into(binding.profilePhoto);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // All click listeners for buttons.
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelfProfileActivity.this, ChatDeatailActivity.class);
                intent.putExtra("id", receiverId);
                intent.putExtra("profilePicture", profilePhoto);
                intent.putExtra("username", userName);
                startActivity(intent);
            }
        });

    }
}