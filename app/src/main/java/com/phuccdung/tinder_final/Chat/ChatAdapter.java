package com.phuccdung.tinder_final.Chat;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.phuccdung.tinder_final.Matches.Matches;
import com.phuccdung.tinder_final.Matches.MatchesAdapter;
import com.phuccdung.tinder_final.R;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    private List<Chat> chatList;
    private Context context;

    public ChatAdapter(List<Chat> chatList, Context context) {
        this.chatList = chatList;
        this.context = context;
    }

    public void setData(List<Chat> list) {
        this.chatList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false);
        return new ChatAdapter.ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        holder.mMess.setText(chatList.get(position).getMessage());
        if (chatList.get(position).getCurrentUser()) {
            holder.mMess.setGravity(Gravity.END);
            holder.mMess.setTextColor(Color.parseColor("#404040"));
            holder.mContainer.setBackgroundColor(Color.parseColor("#F4F4F4"));
        } else {
            holder.mMess.setGravity(Gravity.START);
            holder.mMess.setTextColor(Color.parseColor("#FFFFFF"));
            holder.mContainer.setBackgroundColor(Color.parseColor("#2DB4C8"));
        }
    }

    @Override
    public int getItemCount() {
        if (chatList != null) {
            return chatList.size();
        }
        return 0;
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {

        public TextView mMess;
        public LinearLayout mContainer;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            mMess = itemView.findViewById(R.id.tv_mess);
            mContainer = itemView.findViewById(R.id.ll_container);
        }
    }
}
