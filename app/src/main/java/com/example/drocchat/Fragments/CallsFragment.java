package com.example.drocchat.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.drocchat.Models.Users;
import com.example.drocchat.R;
import com.example.drocchat.SettingActivity;
import com.example.drocchat.databinding.FragmentCallsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class CallsFragment extends Fragment {
    FragmentCallsBinding binding;
    FirebaseDatabase database;

    public CallsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         binding = FragmentCallsBinding.inflate(inflater, container, false);
         database = FirebaseDatabase.getInstance();

        database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users user = snapshot.getValue(Users.class);
                binding.userSetting.setText(user.getUsername());
                Picasso.get().load(user.getProfilePic()).placeholder(R.drawable.login).into(binding.profilePhoto);
                binding.statusSetting.setText(user.getStatus());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(requireActivity(), "Error while getting users info.", Toast.LENGTH_SHORT).show();
            }
        });

        binding.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), SettingActivity.class);
                startActivity(intent);
            }
        });

         return binding.getRoot();
    }
}