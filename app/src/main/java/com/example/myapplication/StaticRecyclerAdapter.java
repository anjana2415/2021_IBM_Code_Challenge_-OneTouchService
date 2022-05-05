package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StaticRecyclerAdapter extends RecyclerView.Adapter<StaticRecyclerAdapter.StaticRVViewholder> {
    private ArrayList<StaticRecyclerViewModel> item;
    int row_index = 1;
    public StaticRecyclerAdapter(ArrayList<StaticRecyclerViewModel> item) {
        this.item = item;
    }


    @NonNull
    @Override
    public StaticRVViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.static_rv_layout,parent,false);
        StaticRVViewholder staticRVViewholder = new StaticRVViewholder(view);
        return staticRVViewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull StaticRVViewholder holder, int position) {
        StaticRecyclerViewModel currentItems = item.get(position);
        holder.imageView.setImageResource(currentItems.getImage());
        holder.textView.setText(currentItems.getText());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              row_index = position;
              notifyDataSetChanged();
            }
        });
        if (row_index == position){
            holder.linearLayout.setBackgroundResource(R.drawable.static_recyclerview_selected);
        }
        else {
            holder.linearLayout.setBackgroundResource(R.drawable.static_rv_bg);
        }

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public  static class StaticRVViewholder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        LinearLayout linearLayout;

        public StaticRVViewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview);
            textView = itemView.findViewById(R.id.label);
           linearLayout = itemView.findViewById(R.id.linerlayout);

        }
    }

}
