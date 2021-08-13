package com.chamodex.whatsapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chamodex.whatsapp.Models.MessageModel;
import com.chamodex.whatsapp.R;
import com.chamodex.whatsapp.databinding.SampleReciverBinding;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter{

    ArrayList<MessageModel> messageModels;
    Context context;

    int SENDER_VIEW_TYPE = 1;
    int RECIEVER_VIEW_TYPE = 2;

    public ChatAdapter(ArrayList<MessageModel> messageModels, Context context) {
        this.messageModels = messageModels;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        if (viewType == SENDER_VIEW_TYPE) {
            View view = LayoutInflater.from(context).inflate(R.layout.sample_sender, parent, false);
            return new SenderViewHolder(view);
        }
        else {
            View view = LayoutInflater.from(context).inflate(R.layout.sample_reciver, parent, false);
            return new RecieverViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {

        if (messageModels.get(position).getuId().equals(FirebaseAuth.getInstance().getUid())) {
            return SENDER_VIEW_TYPE;
        } else {
            return RECIEVER_VIEW_TYPE;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {

        MessageModel messageModel = messageModels.get(position);

        if (holder.getClass() == SenderViewHolder.class) {
            ((SenderViewHolder)holder).senderMsg.setText(messageModel.getMessage());
        } else {
            ((RecieverViewHolder)holder).recieverMsg.setText(messageModel.getMessage());
        }

    }

    @Override
    public int getItemCount() {
        return messageModels.size();
    }

    public class RecieverViewHolder extends RecyclerView.ViewHolder {

        TextView recieverMsg, recieverTime;

        public RecieverViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            recieverMsg = itemView.findViewById(R.id.recieverText);
            recieverTime = itemView.findViewById(R.id.recieverTime);


        }
    }

    public class SenderViewHolder extends RecyclerView.ViewHolder {

        TextView senderMsg, receiverTime;

        public SenderViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            senderMsg = itemView.findViewById(R.id.senderText);
            receiverTime = itemView.findViewById(R.id.senderTime);

        }
    }

}
