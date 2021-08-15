package com.example.drocchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.hardware.camera2.params.MandatoryStreamCombination;
import android.os.Bundle;
import android.view.View;

import com.example.drocchat.Adapters.ChatAdapter;
import com.example.drocchat.Models.MessageModel;
import com.example.drocchat.databinding.ActivityGroupChatBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class GroupChatActivity extends AppCompatActivity {
ActivityGroupChatBinding binding;
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGroupChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        getSupportActionBar().hide();

//        Go back button binding.
        binding.goBackG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroupChatActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

//        Getting database instance and setting up recyclerView and layoutManager for our chats in group chat. :D
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final ArrayList<MessageModel> messageModels = new ArrayList<>();

        final String senderId = auth.getUid();
        binding.userNameG.setText("Group Chat");

        final ChatAdapter chatAdapter = new ChatAdapter(messageModels, this);
        binding.groupChatRecyclerView.setAdapter(chatAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.groupChatRecyclerView.setLayoutManager(layoutManager);



//        After this we will be done with getting layout manager and setting adapter for recyclerView. :D

//        Now trying to get message from the database and displaying in our recycler View. :D
        database.getReference().child("Group Chat")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        messageModels.clear();
                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                            MessageModel model = dataSnapshot.getValue(MessageModel.class);
                            messageModels.add(model);
                        }
                        chatAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

//        now putting things on send button. :D
        binding.sendG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String message = binding.typeAMessageG.getText().toString();
                final MessageModel model = new MessageModel(senderId, message);
                model.setTimestamp(new Date().getTime());
                binding.typeAMessageG.setText("");

                database.getReference()
                        .child("Group Chat")
                        .push()
                        .setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });


            }
        });
//        chatAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
//            @Override
//            public void onItemRangeInserted(int positionStart, int itemCount) {
//                layoutManager.smoothScrollToPosition(messageModels,
//                        null,
//                        chatAdapter.getItemCount());
//            }
//        });


    }
}