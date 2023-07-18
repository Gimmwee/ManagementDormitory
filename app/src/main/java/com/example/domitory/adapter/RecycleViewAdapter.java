package com.example.domitory.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.domitory.R;
import com.example.domitory.bean.HistoryBEAN;

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

        holder.tv_house_name.setText(his.getHouse_name());
        holder.tv_room_name.setText("Phòng : " +his.getRoom_name());
        holder.tv_room_area.setText("Diện tích : " +his.getRoom_area());
        holder.tv_room_floor.setText("Tầng : " +his.getRoom_floor());
        holder.tv_room_address.setText(his.getHost_address());
        holder.tv_host_name.setText(his.getHost_name());
        holder.tv_host_phone.setText("LH : "+his.getHost_phone());
        holder.tv_create_at.setText("Ngày thuê : "+his.getCreate_date());
        holder.tv_expire_date.setText("Ngày hết hạn :" +his.getExpire_date());
        holder.tv_room_price.setText("Giá :" +his.getRoom_price());
        holder.tv_day_left.setText(his.getDayLeft());

    }

    @Override
    public int getItemCount() {
        return listHistory.size();
    }



    public class HistoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tv_house_name,tv_room_name,tv_room_area,tv_room_floor,tv_room_address,tv_host_name,tv_host_phone,tv_create_at,tv_expire_date,tv_room_price,tv_day_left;
        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
//            tv_room_floor = itemView.findViewById(R.id.tv_room_floor);
//            tv_house_name = itemView.findViewById(R.id.tv_house_name);
//            tv_room_name = itemView.findViewById(R.id.tv_room_name);
//            tv_room_area = itemView.findViewById(R.id.tv_room_area);
//            tv_room_address = itemView.findViewById(R.id.tv_room_address);
//            tv_host_name = itemView.findViewById(R.id.tv_host_name);
//            tv_host_phone = itemView.findViewById(R.id.tv_host_phone);
//            tv_create_at = itemView.findViewById(R.id.tv_create_at);
//            tv_expire_date = itemView.findViewById(R.id.tv_expire_date);
//            tv_room_price = itemView.findViewById(R.id.tv_room_price);
//            tv_day_left = itemView.findViewById(R.id.tv_day_left);

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
