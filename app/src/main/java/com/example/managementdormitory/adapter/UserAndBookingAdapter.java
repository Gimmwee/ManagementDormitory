package com.example.managementdormitory.adapter;

import static com.facebook.FacebookSdk.getApplicationContext;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.managementdormitory.AdminListRoomActivity;
import com.example.managementdormitory.Dao.DaoBooking;
import com.example.managementdormitory.Dao.DaoRoom;
import com.example.managementdormitory.Model.Booking;
import com.example.managementdormitory.Model.Room;
import com.example.managementdormitory.Model.User;
import com.example.managementdormitory.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserAndBookingAdapter extends RecyclerView.Adapter<UserAndBookingAdapter.UserAndBookingViewHolder>{
    private List<User> mListUser;
    private Context context;

    public UserAndBookingAdapter(List<User> mListUser, Context context) {
        this.mListUser = mListUser;
        this.context = context;
    }

    @NonNull
    @Override
    public UserAndBookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcv_listroomdetail,parent,false);
        return new UserAndBookingAdapter.UserAndBookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAndBookingViewHolder holder, int position) {
        User user = mListUser.get(position);
        if(user == null){
            return;
        }

        holder.tv_username.setText("Name : "+ user.getName());
        holder.tv_userphone.setText("Phone : "+ user.getPhone());
        holder.tv_userCCCD.setText("CCCD : "+ user.getIdentification_number());
        holder.tv_userAddress.setText("Address :" + user.getAddress());
        holder.btn_RejectUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Booking> LBooking = new ArrayList<>();
                List<Room> LRoom = new ArrayList<>();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Booking");
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        LBooking.clear();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            Booking b1 = ds.getValue(Booking.class);
                            LBooking.add(b1);
                        }
                        System.out.println(LBooking.size()+"ssssssssssssss");
                        for (int i = 0;i<LBooking.size();i++){
                            if(LBooking.get(i).getType().equals(Booking.Type.REGISTER) &&
                                    user.getUser_id().equalsIgnoreCase(LBooking.get(i).getTenant_id())){
                                Booking rb= new Booking();
                                rb.setBooking_id(LBooking.get(i).getBooking_id());
                                rb.setExpire_date(LBooking.get(i).getExpire_date());
                                rb.setHost_id(LBooking.get(i).getHost_id());
                                rb.setNote("ADMIN REJECT!!!!!");
                                rb.setRoom_area(LBooking.get(i).getRoom_area());
                                rb.setRoom_id(LBooking.get(i).getRoom_id());
                                rb.setRoom_name(LBooking.get(i).getRoom_name());
                                rb.setRoom_price(LBooking.get(i).getRoom_price());
                                rb.setTenant_id(user.getUser_id());
                                rb.setType(Booking.Type.ADMIN_REJECT);
                                //  rb.setCreate_date(LBooking.get(i).getCreate_date());
                                DaoBooking Dbooking = new DaoBooking();
                                Dbooking.add(rb).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(getApplicationContext(), "Change Successfull", Toast.LENGTH_SHORT).show();

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getApplicationContext(), "Change Fail", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        }
                        DatabaseReference myRef1 = database.getReference("Room");
                        myRef1.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                LRoom.clear();
                                for (DataSnapshot ds : snapshot.getChildren()) {
                                    Room r1 = ds.getValue(Room.class);
                                    LRoom.add(r1);
                                }
                                for (int i = 0; i < LRoom.size(); i++) {
                                    for (int j = 0; j < LBooking.size(); j++) {
                                        if(LRoom.get(i).getRoom_id().equalsIgnoreCase(LBooking.get(j).getRoom_id())){
                                            if(user.getUser_id().equalsIgnoreCase(LBooking.get(j).getTenant_id())){
                                                Room rroom = new Room();
                                                rroom.setCurrent_slot(String.valueOf(Integer.parseInt(LRoom.get(i).getCurrent_slot())-1));
                                                rroom.setMax_slot(LRoom.get(i).getMax_slot());
                                                rroom.setNote(LRoom.get(i).getNote());
                                                rroom.setRoom_area(LRoom.get(i).getRoom_area());
                                                rroom.setRoom_floor(LRoom.get(i).getRoom_floor());
                                                rroom.setRoom_id(LRoom.get(i).getRoom_id());
                                                rroom.setRoom_price(LRoom.get(i).getRoom_price());
                                                rroom.setRoom_name(LRoom.get(i).getRoom_name());
                                                rroom.setStatus(LRoom.get(i).getStatus());
                                                rroom.setUser_id(LRoom.get(i).getUser_id());
                                                DaoRoom daorom = new DaoRoom();
                                                daorom.add(rroom);
                                            }

                                        }
                                    }
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                Intent intent = new Intent(context, AdminListRoomActivity.class);
                context.startActivity(intent);
            }

        });
    }

    @Override
    public int getItemCount() {
        if(mListUser != null ){
            return mListUser.size();
        }
        return 0;
    }

    public class UserAndBookingViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_username,tv_userphone,tv_userCCCD,tv_userAddress;
        private Button btn_RejectUser;

        public UserAndBookingViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_username = itemView.findViewById(R.id.tv_name);
            tv_userphone = itemView.findViewById(R.id.tv_Phone);
            tv_userCCCD = itemView.findViewById(R.id.tv_CCCD);
            tv_userAddress  = itemView.findViewById(R.id.tv_Address);
            btn_RejectUser = itemView.findViewById(R.id.btn_RejectUser);
        }
    }
}
