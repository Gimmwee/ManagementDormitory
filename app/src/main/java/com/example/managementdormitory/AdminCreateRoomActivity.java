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

import java.util.ArrayList;
import java.util.List;

public class AdminCreateRoomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_room);
        initUi();
        Logout1();
    }
    private TextView room_name,room_area,room_size,room_price,room_floor;
    private Button btn_Create;
    private void initUi(){
        room_name = findViewById(R.id.tv_CRoomName);
        room_area = findViewById(R.id.tv_CRoomArea);
        room_size = findViewById(R.id.tv_CRoomSlot);
        room_price = findViewById(R.id.tv_CRoomPrice);
        room_floor = findViewById(R.id.tv_RRoom);
        btn_Create = findViewById(R.id.btn_RRejecRoom);
        btn_Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Room> LRoom = new ArrayList<>();
                SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
                User u1 = sessionManagement.getUser();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Room");
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        LRoom.clear();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            Room room = ds.getValue(Room.class);
                            LRoom.add(room);
                        }
                        for(int i = 0;i< LRoom.size();i++){
                            if(LRoom.get(i).getRoom_name().equalsIgnoreCase(room_name.getText().toString())){
                                Toast.makeText(AdminCreateRoomActivity.this, "Room name exist", Toast.LENGTH_SHORT).show();
                                Intent i1 = new Intent(getApplicationContext(), AdminListRoomActivity.class);
                                startActivity(i1);
                                return;
                            }else{
                                DatabaseReference myRef1 = database.getReference("User");
                                myRef1.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        for (DataSnapshot ds : snapshot.getChildren()) {
                                            User u = ds.getValue(User.class);
                                            if(u.getUser_id().equals(u1.getUser_id())){
                                                Room room = new Room();
                                                DaoRoom Droom = new DaoRoom();
                                                room.setRoom_id(String.valueOf(LRoom.size()+1));
                                                room.setUser_id(u.getUser_id());
                                                room.setRoom_name(room_name.getText().toString());
                                                room.setRoom_area(room_area.getText().toString());
                                                room.setCurrent_slot("0");
                                                room.setRoom_floor(room_floor.getText().toString());
                                                room.setMax_slot(room_size.getText().toString());
                                                room.setRoom_price(room_price.getText().toString());
                                                room.setStatus(Room.Status.ACTIVE);
                                                room.setNote("");
                                                Droom.add(room).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        Toast.makeText(AdminCreateRoom.this, "Create successful", Toast.LENGTH_SHORT).show();
                                                        Intent i = new Intent(getApplicationContext(), AdminListRoomActivity.class);
                                                        startActivity(i);
                                                        return;
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(AdminCreateRoom.this, "Create fail", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                            }
                                        }

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
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
    private ImageView signOut_button4;
    private void Logout1(){
        signOut_button4 = findViewById(R.id.logout_a4);
        signOut_button4.setOnClickListener(new View.OnClickListener() {
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