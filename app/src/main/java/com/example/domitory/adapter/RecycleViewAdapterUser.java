package com.example.domitory.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.RecyclerView;


import com.example.domitory.R;
import com.example.domitory.bean.ItemUser;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapterUser extends RecyclerView.Adapter<RecycleViewAdapterUser.HistoryViewHolder>{
    private List<ItemUser> list;
    private ItemListener itemListener;
    public RecycleViewAdapterUser(){
        list = new ArrayList<>();
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public void setList(List<ItemUser> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public ItemUser getItem(int position){
        return list.get(position);
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        ItemUser item = list.get(position);
        holder.email.setText(item.getEmail());
        holder.name.setText(item.getName());
        holder.major.setText(item.getMajor());
        holder.role.setText(item.getRole());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class HistoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView email,name,major,role;
        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
//            email = itemView.findViewById(R.id.email);
//            name = itemView.findViewById(R.id.name);
//            major = itemView.findViewById(R.id.major);
//            role = itemView.findViewById(R.id.role);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(itemListener !=null){
                itemListener.onItemClick(itemView,getAdapterPosition());
            }
        }
    }
    public interface ItemListener {
        void onItemClick(View view,int position);
    }
}

