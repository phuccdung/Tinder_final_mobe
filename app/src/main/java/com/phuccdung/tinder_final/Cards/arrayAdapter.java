package com.phuccdung.tinder_final.Cards;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.phuccdung.tinder_final.Cards.cards;
import com.phuccdung.tinder_final.R;

import java.util.List;

public class arrayAdapter extends ArrayAdapter<cards> {
    Context context;
    public arrayAdapter(Context context, int resourceId, List<cards> items){
        super(context,resourceId,items);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        cards card_item=getItem(position);
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.item,parent,false);
        }
        TextView name =(TextView) convertView.findViewById(R.id.tv_name);
        ImageView image=(ImageView) convertView.findViewById(R.id.image);
        TextView about=(TextView) convertView.findViewById(R.id.tv_about);
        name.setText(card_item.getName());
        about.setText(card_item.getAbout());
        Glide.with(getContext()).load(card_item.getProfileImageUrl()).error(R.mipmap.ic_launcher).into(image);
        return  convertView;
    }
}
