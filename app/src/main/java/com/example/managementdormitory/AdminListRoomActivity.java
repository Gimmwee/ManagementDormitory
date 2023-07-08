package com.example.managementdormitory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdminListRoomActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminlistrom);
        initUi();
        getListRoom();
        Logout1();
    }
    private RecyclerView rcv_listroom;
    private ImageView signOut_button3,btn_user1;
    private RoomAdapter mRoomAdapter;
    private Button btn_CreateRoom,btn_RejectRoom,btn_EditRoom;
    private void initUi() {
        btn_EditRoom = findViewById(R.id.btn_EditRoom);
        rcv_listroom = findViewById(R.id.admin_listroom);
        btn_CreateRoom = findViewById(R.id.btn_AddRoom);
        btn_CreateRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AdminCreateRoom.class);
                startActivity(i);
            }
        });
        btn_EditRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AdminEditRoom.class);
                startActivity(i);
            }
        });
        btn_RejectRoom = findViewById(R.id.btn_RejectRoom);
        btn_RejectRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AdminRejectRoom.class);
                startActivity(i);
            }
        });
    }

    private void rcvListRoom(List<Room> ListRoom){
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        rcv_listroom.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL);
        rcv_listroom.addItemDecoration(dividerItemDecoration);
        mRoomAdapter = new RoomAdapter(this,ListRoom);
        rcv_listroom.setAdapter(mRoomAdapter);
    }
    private void Logout1(){
        signOut_button3 = findViewById(R.id.logout_a7);
        signOut_button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
    private  void getListRoom(){
        List<Room> LRoom = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Room");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                LRoom.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Room room = ds.getValue(Room.class);
                    LRoom.add(room);
                }
                rcvListRoom(LRoom);
                mRoomAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
