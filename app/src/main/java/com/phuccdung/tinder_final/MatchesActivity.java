package com.phuccdung.tinder_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.phuccdung.tinder_final.Matches.Matches;
import com.phuccdung.tinder_final.Matches.MatchesAdapter;

import java.util.ArrayList;
import java.util.List;

public class MatchesActivity extends AppCompatActivity {
    private RecyclerView rcv;
    private MatchesAdapter matchesAdapter;
    private String currentUserId;
    private List<Matches> list = new ArrayList<Matches>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);
        currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        rcv = findViewById(R.id.rcv);
        matchesAdapter = new MatchesAdapter(list,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcv.setLayoutManager(linearLayoutManager);
        getListMatches();
        rcv.setAdapter(matchesAdapter);
    }

    private void getListMatches() {

        DatabaseReference matchesDb = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserId).child("connections").child("matches");
        matchesDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot matches:snapshot.getChildren()){
                    FecthMatchInfo(matches.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void FecthMatchInfo(String key) {
        DatabaseReference userDb = FirebaseDatabase.getInstance().getReference().child("Users").child(key);
        userDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String userId=snapshot.getKey();
                    String name="";
                    String profileImageUrl="";
                    if(snapshot.child("name").getValue()!=null){
                        name=snapshot.child("name").getValue().toString();
                    }
                    if(snapshot.child("profileImageUrl").getValue()!=null){
                        profileImageUrl=snapshot.child("profileImageUrl").getValue().toString();
                    }
                    Matches obj=new Matches(userId,name,profileImageUrl);
                    list.add(obj);
                    matchesAdapter.setData(list);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

}