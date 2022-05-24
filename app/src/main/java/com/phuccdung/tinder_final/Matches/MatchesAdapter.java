package com.phuccdung.tinder_final.Matches;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.phuccdung.tinder_final.R;

import java.util.List;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.MatchesViewHolder> {
    private List<Matches> matchesList;
    private Context context;
    private IClickItemListener mIClickItemListener;

    public MatchesAdapter(List<Matches> matchesList, Context context,IClickItemListener listener) {
        this.matchesList = matchesList;
        this.context = context;
        this.mIClickItemListener=listener;
    }
    public interface IClickItemListener{
        void onClickItem(String matchesId);
    }

    public void setData(List<Matches> list) {
        this.matchesList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MatchesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.matches_item, parent, false);
        return new MatchesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchesViewHolder holder, int position) {
        Matches matches = matchesList.get(position);

        holder.matches_name.setText(matches.getName());
        Glide.with(context).load(matches.getProfileImageUrl()).error(R.mipmap.ic_launcher).into(holder.matches_image);
        holder.matches_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIClickItemListener.onClickItem(matches.getUserId());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (matchesList != null) {
            return matchesList.size();
        }
        return 0;
    }

    public class MatchesViewHolder extends RecyclerView.ViewHolder {
        private TextView matches_id, matches_name;
        private ImageView matches_image;

        public MatchesViewHolder(@NonNull View itemView) {
            super(itemView);

            matches_name = itemView.findViewById(R.id.match_name);
            matches_image = itemView.findViewById(R.id.matchesImage);
        }
        
    }


}
