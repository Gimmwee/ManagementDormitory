package com.example.managementdormitory.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.managementdormitory.Model.User;
import com.example.managementdormitory.R;

import java.util.List;

public class RoomAreaAdapter extends RecyclerView.Adapter<RoomAreaAdapter.AreaRoomViewHolder> {

    List<User> areas;
    private Context context;

    public RoomAreaAdapter(List<User> areas, Context context) {
        this.areas = areas;
        this.context = context;
    }

    @NonNull
    @Override
    public RoomAreaAdapter.AreaRoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcv_list_area_room,parent,false);
        return new AreaRoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomAreaAdapter.AreaRoomViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class AreaRoomViewHolder extends RecyclerView.ViewHolder{
        private ConstraintLayout rcv_listArea;
        private TextView tv_AreaName, tv_Address, tv_Manage, tv_Phone;
        public AreaRoomViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_AreaName = itemView.findViewById(R.id.tv_AreaName);
            tv_Address = itemView.findViewById(R.id.tv_AreaAddress);
            tv_Manage = itemView.findViewById(R.id.tv_AreaManage);
            tv_Phone = itemView.findViewById(R.id.tv_AreaPhone);
            rcv_listArea = itemView.findViewById(R.id.user_listArea);
        }
    }
}
