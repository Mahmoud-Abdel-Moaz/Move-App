package com.mahmoud.movieapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mahmoud on 16/10/2017.
 */

public class revadapter extends RecyclerView.Adapter<revadapter.holder> {
    ArrayList<rev> revu = new ArrayList<>();
    Context con;

    public revadapter(ArrayList<rev> revu, Context con) {
        this.revu = revu;
        this.con = con;
    }

    @Override
    public revadapter.holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.revitem, parent, false);
        revadapter.holder holder = new revadapter.holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(revadapter.holder holder, int position) {
        holder.txt_name.setText(revu.get(position).getName());
        holder.txt_rev.setText(revu.get(position).getRev());
    }

    @Override
    public int getItemCount() {
        return revu.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        TextView txt_name, txt_rev,txt_ditail;
        ImageView img_post;
        public holder(View itemView) {
            super(itemView);
            txt_name = (TextView) itemView.findViewById(R.id.rev_name);
            txt_rev = (TextView) itemView.findViewById(R.id.rev);
            img_post=(ImageView) itemView.findViewById(R.id.revimg);
            txt_ditail = (TextView) itemView.findViewById(R.id.revdetile);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int postion = getAdapterPosition();
                    commication commication = (com.mahmoud.movieapp.commication) con;
                }
            });
        }
    }
}