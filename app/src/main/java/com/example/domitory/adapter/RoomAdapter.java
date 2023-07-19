package com.example.domitory.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import com.example.domitory.Model.Room;
import com.example.domitory.R;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder >{
    List<Room> ListRoom;

    private Context context;
    public RoomAdapter(Context context, List<Room> listRoom) {
        this.context =context;
        this.ListRoom = listRoom;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcv_listroom,parent,false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        final Room room = ListRoom.get(position);
        if(room == null){
            return;
        }

        holder.tv_RoomName.setText("   Room Name: " +  room.getRoom_name() );
        holder.tv_RoomPrice.setText("   Room Price: " + room.getRoom_price());
        holder.tv_SizeRoom.setText("   SizeRoom: "+room.getCurrent_slot()+"/"+room.getMax_slot());
        holder.tv_RoomStatus.setText("   Status: "+room.getStatus());
        holder.rcv_listroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGotoDetail(room);
            }
        });
    }

    private void onClickGotoDetail(Room room) {
      //  Intent intent = new Intent(context, AdminDetailActivityRoom.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Object_Room",room);
//        intent.putExtras(bundle);
//        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        if(ListRoom != null){
            return ListRoom.size();
        }
        return 0;
    }

    public class RoomViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_RoomName,tv_RoomPrice,tv_SizeRoom,tv_RoomStatus;
        private ConstraintLayout rcv_listroom;
        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
//            tv_RoomName = itemView.findViewById(R.id.tv_RoomName);
//            tv_RoomPrice = itemView.findViewById(R.id.tv_CCCD);
//            tv_SizeRoom = itemView.findViewById(R.id.tv_SizeRoom);
//            tv_RoomStatus = itemView.findViewById(R.id.tv_RoomStatus);
//            rcv_listroom = itemView.findViewById(R.id.admin_listroom);
        }
    }
}
