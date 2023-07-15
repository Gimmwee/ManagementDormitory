package com.example.managementdormitory.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.managementdormitory.R;
import com.example.managementdormitory.bean.HistoryBEAN;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.HistoryViewHolder>{
    private List<HistoryBEAN> listHistory;
    private ItemListener itemListener;
    public RecycleViewAdapter(){
        listHistory = new ArrayList<>();
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public void setList(List<HistoryBEAN> list) {
         this.listHistory = list;
        notifyDataSetChanged();
    }

    public HistoryBEAN getItem(int position){
        return listHistory.get(position);
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history,parent,false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        HistoryBEAN his = listHistory.get(position);



    }

    @Override
    public int getItemCount() {
        return listHistory.size();
    }



    public class HistoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tv_house_name,tv_room_name,tv_room_area,tv_room_floor,tv_room_address,tv_host_name,tv_host_phone,tv_create_at,tv_expire_date,tv_room_price,tv_day_left;
        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_room_floor = itemView.findViewById(R.id.tv_room_floor);
            tv_house_name = itemView.findViewById(R.id.tv_house_name);
            tv_room_name = itemView.findViewById(R.id.tv_room_name);
            tv_room_area = itemView.findViewById(R.id.tv_room_area);
            tv_room_address = itemView.findViewById(R.id.tv_room_address);
            tv_host_name = itemView.findViewById(R.id.tv_host_name);
            tv_host_phone = itemView.findViewById(R.id.tv_host_phone);
            tv_create_at = itemView.findViewById(R.id.tv_create_at);
            tv_expire_date = itemView.findViewById(R.id.tv_expire_date);
            tv_room_price = itemView.findViewById(R.id.tv_room_price);
            tv_day_left = itemView.findViewById(R.id.tv_day_left);

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
