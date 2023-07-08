package com.example.managementdormitory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.managementdormitory.Dao.DaoRoom;
import com.example.managementdormitory.Model.Room;
import com.example.managementdormitory.Model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminEditRoom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_room);
        initUi();
        Logout1();
    }
    private TextView room_name,room_area,room_size,room_price,room_floor;
    private Button btn_Edit;
    private void initUi(){
        room_name = findViewById(R.id.tv_CRoomName);
        room_area = findViewById(R.id.tv_CRoomArea);
        room_size = findViewById(R.id.tv_CRoomSlot);
        room_price = findViewById(R.id.tv_CRoomPrice);
        room_floor = findViewById(R.id.tv_RRoom);
        btn_Edit = findViewById(R.id.btn_EditRoom);
        btn_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
                User u1 = sessionManagement.getUser();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Room");
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        DatabaseReference myRef1 = database.getReference("User");
                        myRef1.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot ds : snapshot.getChildren()) {
                                    User u = ds.getValue(User.class);
                                    if(u.getUser_id().equals(u1.getUser_id())){
                                        DatabaseReference myRef1 = database.getReference("Room");
                                        myRef1.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                for (DataSnapshot ds : snapshot.getChildren()) {
                                                    Room r = ds.getValue(Room.class);
                                                    if(r.getRoom_name().equals(room_name.getText().toString())){
                                                        if(u.getUser_id().equals(u1.getUser_id())){
                                                            Room room = new Room();
                                                            DaoRoom Droom = new DaoRoom();
                                                            room.setRoom_id(r.getRoom_id());
                                                            room.setUser_id(u.getUser_id());
                                                            room.setRoom_name(room_name.getText().toString());
                                                            room.setRoom_area(room_area.getText().toString());
                                                            room.setMax_slot(room_size.getText().toString());
                                                            room.setRoom_price(room_price.getText().toString());
                                                            room.setRoom_floor(room_floor.getText().toString());
                                                            room.setCurrent_slot("0");
                                                            room.setStatus(r.getStatus());
                                                            room.setNote(r.getNote());
                                                            Droom.add(room).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void unused) {
                                                                    Toast.makeText(AdminEditRoom.this, "Edit successful", Toast.LENGTH_SHORT).show();
                                                                    Intent i = new Intent(getApplicationContext(), AdminListRoomActivity.class);
                                                                    startActivity(i);
                                                                    return;
                                                                }
                                                            }).addOnFailureListener(new OnFailureListener() {
                                                                @Override
                                                                public void onFailure(@NonNull Exception e) {
                                                                    Toast.makeText(AdminEditRoom.this, "Edit fail", Toast.LENGTH_SHORT).show();
                                                                }
                                                            });
                                                        }
                                                    }
                                                }
                                            }
                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }


                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    private ImageView signOut_button7;
    private void Logout1(){
        signOut_button7 = findViewById(R.id.logout_a7);
        signOut_button7.setOnClickListener(new View.OnClickListener() {
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