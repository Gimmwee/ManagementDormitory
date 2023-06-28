package com.example.managementdormitory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.managementdormitory.Model.Room;
import com.example.managementdormitory.Model.User;
import com.example.managementdormitory.adapter.UserListRoomAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserViewRoomActivity extends AppCompatActivity {

    public RecyclerView rcv;
    public UserListRoomAdapter adapter;
    public TextView tv_Header;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_room);
    }

    public void bindingView(){
        rcv = findViewById(R.id.user_listRoom);
        tv_Header = findViewById(R.id.textHeaderUserRoom);
    }

    public void bindingAction(){
        getListRoom();
    }


    private void rcvListRoom(List<Room> listRoom){
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        rcv.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL);
        rcv.addItemDecoration(dividerItemDecoration);
        adapter = new UserListRoomAdapter(this, listRoom);
        rcv.setAdapter(adapter);
    }

    private  void getListRoom(){
        Bundle bundle = getIntent().getExtras();
        User user = (User) bundle.get("Object_User");
        tv_Header.setText(user.getHouse_name());
        List<Room> listRooms = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Room");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listRooms.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Room room = ds.getValue(Room.class);
                    if(user.getUser_id().equals(room.getUser_id()) &&  !room.getCurrent_slot().equals(room.getMax_slot()) ) {

                        listRooms.add(room);
                    }
                }
                rcvListRoom(listRooms);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}