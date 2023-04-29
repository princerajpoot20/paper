package com.example.paper.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paper.Listeners.OnRecyclerClickListener;
import com.example.paper.Models.Photo;
import com.example.paper.R;
import com.squareup.picasso.Picasso;

import java.util.List;

//CuratedAdapter for image as list view

public class CuratedAdapter extends RecyclerView.Adapter<CuratedViewHolder>{

    Context context;
    List<Photo> list;
    OnRecyclerClickListener listener;

    public CuratedAdapter(Context context, List<Photo> list, OnRecyclerClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CuratedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CuratedViewHolder(LayoutInflater.from(context).inflate(R.layout.home_list,parent,false));

    }





    


// To get Item counts
    @Override
    public int getItemCount() {
        return list.size();
    }
}

class CuratedViewHolder extends RecyclerView.ViewHolder{

    CardView home_list_container;
    ImageView imageView_list;
    public CuratedViewHolder(@NonNull View itemView) {
        super(itemView);
        home_list_container = itemView.findViewById(R.id.home_list_container);
        imageView_list = itemView.findViewById(R.id.imageView_list);
    }
}


