package com.phuccdung.tinder_final;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.phuccdung.tinder_final.Chat.Chat;
import com.phuccdung.tinder_final.Chat.ChatAdapter;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView rcv;
    private ChatAdapter chatAdapter;
    private List<Chat> list =new ArrayList<Chat>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        rcv=findViewById(R.id.rcv_chat);
        chatAdapter=new ChatAdapter(list,this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        rcv.setLayoutManager(linearLayoutManager);
        rcv.setAdapter(chatAdapter);
    }
}