package com.example.drocchat.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drocchat.ChatDeatailActivity;
import com.example.drocchat.Models.Users;
import com.example.drocchat.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {
    ArrayList<Users>  list;
    Context context;

    public UsersAdapter(ArrayList<Users> list, Context context) {
        this.list = list;
        this.context = context;
    }

    // so this is viewholder class for our user adapter. :D
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, lastMessage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            lastMessage = itemView.findViewById(R.id.lastMessage);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_show_user , parent , false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Users users = list.get(position);
        Picasso.get().load(users.getProfilePic()).placeholder(R.drawable.ic_user).into(holder.image);
        holder.name.setText(users.getUsername());

//            Here we are giving our view holder the last message text we want it to show. :D
        FirebaseDatabase.getInstance().getReference().child("chats")
                .child(FirebaseAuth.getInstance().getUid() + users.getId())
                .orderByChild("timestamp")
                .limitToLast(1)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChildren()) {
                            for (DataSnapshot snapshot1: snapshot.getChildren() ) {
                                holder.lastMessage.setText(snapshot1.child("message").getValue().toString());
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Image preview feature not available.", Toast.LENGTH_SHORT).show();
            }
        });
        holder.itemView.setOnClickListener((new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                Intent intent = new Intent(context, ChatDeatailActivity.class);
                intent.putExtra("id", users.getId());
                intent.putExtra("profilePicture", users.getProfilePic());
                intent.putExtra("username", users.getUsername());
                context.startActivity(intent);
            }
        }));

    }






}
