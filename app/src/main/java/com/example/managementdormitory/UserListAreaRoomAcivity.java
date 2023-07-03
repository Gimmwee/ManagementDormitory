package com.example.managementdormitory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.managementdormitory.Model.User;
import com.example.managementdormitory.adapter.RoomAreaAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserListAreaRoomAcivity extends AppCompatActivity {

    private RecyclerView rcv;
    private RoomAreaAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list_area_room);
    }

    private void bindingView(){
        rcv = findViewById(R.id.user_listArea);
    }

    private void rcvListArea(List<User> listArea){
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        rcv.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL);
        rcv.addItemDecoration(dividerItemDecoration);
        adapter = new RoomAreaAdapter(this, listArea);
        rcv.setAdapter(adapter);
    }

    private void getListAreaRoom(){
        List<User> users = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("User");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    User user = ds.getValue(User.class);
                    if(user.getRole() == User.Role.ADMIN) {
                        users.add(user);
                    }
                }
                rcvListArea(users);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}