package com.example.managementdormitory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.managementdormitory.Model.Booking;
import com.example.managementdormitory.Model.Room;
import com.example.managementdormitory.Model.User;
import com.example.managementdormitory.adapter.UserAndBookingAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminDetailActivityRoom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_detail_room);
        initUi();
        getListUser();
        Logout1();
    }
    private void initUi(){
        rcv_listroomdetail = findViewById(R.id.rcv_listroomdetail);
    }
    private static RecyclerView rcv_listroomdetail;
    private UserAndBookingAdapter userAndBookingAdapter;
    private void rcvListroomdetail(List<Booking> mListBooking, List<User> mListUser) {
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        Room room = (Room) bundle.get("Object_Room");
        TextView domdetail = findViewById(R.id.tv_DomDetail);
        domdetail.setText("  Room Name : "+room.getRoom_name());
        String rid = room.getRoom_id();
        List<User>  ListUserInRoom = new ArrayList<>();
        ListUserInRoom.clear();
        for (int i = 0; i < mListUser.size(); i++) {
            for (int j = 0; j < mListBooking.size(); j++) {
                if(rid.equalsIgnoreCase(mListBooking.get(j).getRoom_id())){
                    if (mListUser.get(i).getUser_id().equalsIgnoreCase(mListBooking.get(j).getTenant_id())
                            && mListBooking.get(j).getType().equals(Booking.Type.REGISTER)){
                        User u = mListUser.get(i);
                        ListUserInRoom.add(u);
                    }
                }
            }
        }
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        rcv_listroomdetail.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL);
        rcv_listroomdetail.addItemDecoration(dividerItemDecoration);
        userAndBookingAdapter = new UserAndBookingAdapter(ListUserInRoom,this);
        rcv_listroomdetail.setAdapter(userAndBookingAdapter);
    }
    private void getListUser(){
        List<User> mListUser= new ArrayList<>();
        List<Booking> mListBooking = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("User");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mListUser.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    User u = ds.getValue(User.class);
                    mListUser.add(u);
                }
                DatabaseReference myRef1 = database.getReference("Booking");
                myRef1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        mListBooking.clear();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            Booking u1 = ds.getValue(Booking.class);
                            mListBooking.add(u1);
                        }
                        rcvListroomdetail( mListBooking,  mListUser);
                        userAndBookingAdapter.notifyDataSetChanged();
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
    }

    private ImageView signOut_button5;
    private void Logout1(){
        signOut_button5 = findViewById(R.id.logout_a5);
        signOut_button5.setOnClickListener(new View.OnClickListener() {
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