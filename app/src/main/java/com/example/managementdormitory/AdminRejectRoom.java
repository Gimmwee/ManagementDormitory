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

        btn_Active = findViewById(R.id.btn_RActive);

                                Droom.add(room).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {

                                        Toast.makeText(getApplicationContext(), "Change Successfull", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(getApplicationContext(), AdminListRoomActivity.class);
                                        startActivity(i);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getApplicationContext(), "Change Fail", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }else{
                                Toast.makeText(getApplicationContext(), "Change Fail", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

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