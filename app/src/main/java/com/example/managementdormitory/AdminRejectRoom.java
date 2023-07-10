package com.example.managementdormitory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.managementdormitory.Model.Room;
import com.example.managementdormitory.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminRejectRoom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_reject_room);
        initUi();
        Logout1();
    }
    private Button btn_Reject,btn_Active;
    private TextView tv_RoomName;
    private void initUi(){
        tv_RoomName = findViewById(R.id.tv_RRoom);
        btn_Reject = findViewById(R.id.btn_RRejecRoom);
        btn_Reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Room> LlRoom = new ArrayList<>();
                SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
                User u1 = sessionManagement.getUser();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Room");
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        LlRoom.clear();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            Room room = ds.getValue(Room.class);
                            LlRoom.add(room);
                        }
                        for(int i = 0;i< LlRoom.size();i++){
                            if(Integer.parseInt(LlRoom.get(i).getCurrent_slot()) ==0 &&
                                    LlRoom.get(i).getRoom_name().equalsIgnoreCase(tv_RoomName.getText().toString())
                                    && LlRoom.get(i).getStatus().equals(Room.Status.ACTIVE)
                            ){
                                Room room = new Room();
                                room.setRoom_id(LlRoom.get(i).getRoom_id());
                                room.setRoom_price(LlRoom.get(i).getRoom_price());
                                room.setRoom_name(tv_RoomName.getText().toString());
                                room.setRoom_area(LlRoom.get(i).getRoom_area());
                                room.setCurrent_slot(LlRoom.get(i).getCurrent_slot());
                                room.setMax_slot(LlRoom.get(i).getMax_slot());
                                room.setRoom_floor(LlRoom.get(i).getRoom_floor());
                                room.setUser_id(LlRoom.get(i).getUser_id());
                                room.setNote(LlRoom.get(i).getNote());
                                room.setStatus(Room.Status.INACTIVE);
                            }else{
                                Toast.makeText(getApplicationContext(), " Change Fail", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        btn_Active = findViewById(R.id.btn_RActive);
        btn_Active.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Room> LlRoom = new ArrayList<>();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Room");
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        LlRoom.clear();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            Room room = ds.getValue(Room.class);
                            LlRoom.add(room);
                        }
                        for(int i = 0;i< LlRoom.size();i++){
                            if(Integer.parseInt(LlRoom.get(i).getCurrent_slot()) ==0 &&
                                    LlRoom.get(i).getRoom_name().equalsIgnoreCase(tv_RoomName.getText().toString())
                                    && LlRoom.get(i).getStatus().equals(Room.Status.INACTIVE)){
                                Room room = new Room();
                                room.setRoom_id(LlRoom.get(i).getRoom_id());
                                room.setRoom_price(LlRoom.get(i).getRoom_price());
                                room.setRoom_name(LlRoom.get(i).getRoom_name());
                                room.setRoom_area(LlRoom.get(i).getRoom_area());
                                room.setCurrent_slot(LlRoom.get(i).getCurrent_slot());
                                room.setMax_slot(LlRoom.get(i).getMax_slot());
                                room.setRoom_floor(LlRoom.get(i).getRoom_floor());
                                room.setUser_id(LlRoom.get(i).getUser_id());
                                room.setNote(LlRoom.get(i).getNote());
                                room.setStatus(Room.Status.ACTIVE);
                            }else{
                                Toast.makeText(getApplicationContext(), "Change Fail", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
    private ImageView signOut_button6;
    private void Logout1(){
        signOut_button6 = findViewById(R.id.logout_a6);
        signOut_button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}