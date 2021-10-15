package com.example.drocchat.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drocchat.Models.MessageModel;
import com.example.drocchat.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;


public class ChatAdapter extends  RecyclerView.Adapter {
    ArrayList<MessageModel> messageModel;
    Context context;

    int SENDER_VIEW_TYPE = 1;
    int RECIEVER_VIEW_TYPE = 2;

    public ChatAdapter(ArrayList<MessageModel> messageModel, Context context) {
        this.messageModel = messageModel;
        this.context = context;
    }
//  Knowing which viewholder to use by getting info about ViewTYpe no.
    @Override
    public int getItemViewType(int position) {
        if(messageModel.get(position).getUid().equals(FirebaseAuth.getInstance().getUid())){
            return SENDER_VIEW_TYPE;
        }
        else{
            return RECIEVER_VIEW_TYPE;
        }
    }
// inflating the required layout in oncreate ViewHolder. :D
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==SENDER_VIEW_TYPE){
            View view = LayoutInflater.from(context).inflate(R.layout.sample_sender_layout, parent,false);
            return new SenderViewHolder(view);
        }
        else{
            View view = LayoutInflater.from(context).inflate(R.layout.sample_reciever_layout, parent,false);
            return new RecieverViewHolder(view);
        }
    }
// one of the most important task. :D
//    here we will get to know what is the message of the reciever and sender. :D
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessageModel messageModels = messageModel.get(position);
        final String posi = String.valueOf(position);
        if (holder.getClass() == SenderViewHolder.class){
            ((SenderViewHolder)holder).senderMsg.setText(messageModels.getMessage());
        }
        else{
            ((RecieverViewHolder)holder).recieverMsg.setText(messageModels.getMessage());
        }

    }

    @Override
    public int getItemCount() {
        return messageModel.size();
    }


    //    since we have two layouts sender and reciever we will have two ViewHolder. :D
    public class RecieverViewHolder extends RecyclerView.ViewHolder{
        TextView recieverMsg, recieverTime;

        public RecieverViewHolder(@NonNull View itemView) {
            super(itemView);
            recieverMsg = itemView.findViewById(R.id.recieverText);

        }
    }
    public class SenderViewHolder extends RecyclerView.ViewHolder{
        TextView senderMsg, senderTime;

        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            senderMsg = itemView.findViewById(R.id.senderText);
        }
    }



}
