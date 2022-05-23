package com.phuccdung.tinder_final;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.phuccdung.tinder_final.Chat.Chat;
import com.phuccdung.tinder_final.Chat.ChatAdapter;
import com.phuccdung.tinder_final.Matches.Matches;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView rcv;
    private ChatAdapter chatAdapter;
    private List<Chat> list = new ArrayList<Chat>();
    private String currentUserId, matchId, chatId;
    private Button btn_send;
    private EditText tv_mess;
    DatabaseReference mDatabaseReference, mDatabaseChat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        matchId = getIntent().getExtras().getString("matchId");
        currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserId).child("connections").child("matches").child(matchId).child("chatId");
        mDatabaseChat = FirebaseDatabase.getInstance().getReference().child("chat");

        rcv = findViewById(R.id.rcv_chat);



        chatAdapter = new ChatAdapter(list, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcv.setLayoutManager(linearLayoutManager);
        getChatId();

        rcv.setAdapter(chatAdapter);

        btn_send = findViewById(R.id.send);
        tv_mess = findViewById(R.id.message);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });
    }

    private void sendMessage() {
        String sendMess=tv_mess.getText().toString();
        if(!sendMess.isEmpty()){
            DatabaseReference newMessDb=mDatabaseChat.push();
            Map newMess=new HashMap();
            newMess.put("createdByUser",currentUserId);
            newMess.put("text",sendMess);
            newMessDb.setValue(newMess);
        }
        tv_mess.setText(null);
    }

    private void getChatId() {
        mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    chatId = snapshot.getValue().toString();
                    mDatabaseChat = mDatabaseChat.child(chatId);
                    getChatMessage();


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getChatMessage() {
        mDatabaseChat.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(snapshot.exists()){
                    String message=null;
                    String UserCreate=null;
                    if(snapshot.child("text").getValue()!=null){
                        message=snapshot.child("text").getValue().toString();
                    }
                    if(snapshot.child("createdByUser").getValue()!=null){
                        UserCreate=snapshot.child("createdByUser").getValue().toString();
                    }
                    if(message!=null&& UserCreate!=null){
                        Boolean is=false;
                        if(UserCreate.equals(currentUserId)){
                            is=true;
                        }
                        Chat newMessage=new Chat(message,is);
                        list.add(newMessage);
                        chatAdapter.setData(list);
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}