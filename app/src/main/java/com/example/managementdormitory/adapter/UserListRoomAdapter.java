package com.example.managementdormitory.adapter;


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

import com.example.managementdormitory.BookingRoomAtivity;
import com.example.managementdormitory.Model.Room;
import com.example.managementdormitory.R;

import java.util.List;

public class UserListRoomAdapter extends RecyclerView.Adapter<UserListRoomAdapter.UserListRoomViewHolder>{
    List<Room> listRoom;
    private Context context;

    public UserListRoomAdapter(Context context,List<Room> listRoom) {
        this.listRoom = listRoom;
        this.context = context;
    }

    @NonNull
    @Override
    public UserListRoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcv_user_list_room,parent,false);
        return new UserListRoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserListRoomViewHolder holder, int position) {
        final Room room = listRoom.get(position);
        if(room == null){
            return;
        }
        holder.tv_name.setText("   Room Name: " + room.getRoom_name());
        holder.tv_area.setText("   Acreage: " + room.getRoom_area() +" m2");
        holder.tv_price.setText("   Address: " + room.getRoom_price() + " đ");
        holder.tv_slotUse.setText("   Number of beds in use: "+ room.getCurrent_slot() + "/" + room.getMax_slot());
        holder.tv_maxSlot.setText("   Number of beds: "+ room.getMax_slot());
        holder.rcv_UserListRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGotoBooking(room);
            }
        });
    }
    private void onClickGotoBooking(Room room) {
        Intent intent = new Intent(context, BookingRoomAtivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Object_UserRoom", room);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        if (listRoom != null){
            return listRoom.size();
        }
        return 0;
    }

    public class UserListRoomViewHolder extends RecyclerView.ViewHolder{

        private ConstraintLayout rcv_UserListRoom;
        private TextView tv_name, tv_price, tv_maxSlot, tv_slotUse, tv_area;
        public UserListRoomViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_area = itemView.findViewById(R.id.tv_Acreage);
            tv_price = itemView.findViewById(R.id.tv_UserRoomPrice);
            tv_name = itemView.findViewById(R.id.tv_UserRoomName);
            tv_maxSlot = itemView.findViewById(R.id.tv_TotalBed);
            tv_slotUse = itemView.findViewById(R.id.tv_BedUse);
            rcv_UserListRoom = itemView.findViewById(R.id.user_listRoom);
        }
    }
}
