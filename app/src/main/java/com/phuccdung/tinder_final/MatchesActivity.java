package com.phuccdung.tinder_final;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.phuccdung.tinder_final.Matches.Matches;
import com.phuccdung.tinder_final.Matches.MatchesAdapter;

import java.util.ArrayList;
import java.util.List;

public class MatchesActivity extends AppCompatActivity {
    private  RecyclerView rcv;
    private MatchesAdapter matchesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);

            rcv=findViewById(R.id.rcv);
            matchesAdapter=new MatchesAdapter();
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
            rcv.setLayoutManager(linearLayoutManager);
            matchesAdapter.setData(getListMatches());
            rcv.setAdapter(matchesAdapter);
    }

    private List<Matches> getListMatches() {
        List<Matches> list=new ArrayList<Matches>();
        for (int i=0;i<100;i++){
            Matches obj=new Matches("jsd"+i);
            list.add(obj);

        }
        return  list;
    }

}