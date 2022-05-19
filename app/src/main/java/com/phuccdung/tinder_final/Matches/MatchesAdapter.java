package com.phuccdung.tinder_final.Matches;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.phuccdung.tinder_final.R;

import java.util.List;

public class MatchesAdapter  extends  RecyclerView.Adapter<MatchesAdapter.MatchesViewHolder>{
   private List<Matches> matchesList;
   public  void setData(List<Matches> list){
       this.matchesList=list;
       notifyDataSetChanged();
   }

    @NonNull
    @Override
    public MatchesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.matches_item,parent,false);
       return new MatchesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchesViewHolder holder, int position) {
        Matches matches=matchesList.get(position);
        holder.matches_id.setText(matches.getUserId());
     }

    @Override
    public int getItemCount() {
       if(matchesList!=null){
           return matchesList.size();
       }
        return 0;
    }

    public  class  MatchesViewHolder extends RecyclerView.ViewHolder{
        private TextView matches_id;
        public MatchesViewHolder(@NonNull View itemView) {
            super(itemView);
            matches_id=itemView.findViewById(R.id.match_id);


        }
    }


}
