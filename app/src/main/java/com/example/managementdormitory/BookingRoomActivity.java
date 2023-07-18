package com.example.managementdormitory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.managementdormitory.Dao.DaoBooking;
import com.example.managementdormitory.Dao.DaoRoom;
import com.example.managementdormitory.Model.Booking;
import com.example.managementdormitory.Model.Room;
import com.example.managementdormitory.Model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class BookingRoomActivity extends AppCompatActivity {
    private TextView tv_InfoA, tv_InfoB, tv_InfoPriceRoom,
            tv_InfoRoom, tv_StartDate, tv_TotalPrice;
    private EditText edt_time;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_room_ativity);
        bindingView();
        load();
        bindingActive();
    }

    public void bindingView(){
        tv_InfoA = findViewById(R.id.tv_InfoA);
        tv_InfoB = findViewById(R.id.tv_InfoB);
        tv_InfoRoom = findViewById(R.id.tv_InfoRoom);
        tv_InfoPriceRoom = findViewById(R.id.tv_InfoPriceRoom);
        tv_StartDate = findViewById(R.id.tv_StartTime);
        tv_TotalPrice = findViewById(R.id.tv_TotalPrice);
        edt_time = findViewById(R.id.edt_TimeThue);
        button =  (Button) findViewById(R.id.button2);
    }

    private void load(){
        Bundle bundle = getIntent().getExtras();
        Room room = (Room) bundle.get("Object_UserRoom");
        SessionManagement session = new SessionManagement(getApplicationContext());
        User userB = session.getUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("User");
        Date date = Calendar.getInstance().getTime();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 2;
        String sdate;
        if(month < 10){
            sdate =  "1-0" + month + "-" + year;
        }else{
            sdate =  "1-" + month + "-" + year;
        }

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    User userA = ds.getValue(User.class);
                    if(userA.getUser_id().equals(room.getUser_id()) ) {
                        tv_InfoA.setText("Ông(Bà): " + userA.getName() + " chủ " + userA.getHouse_name() + System.getProperty ("line.separator")
                                + "CCCD: " + userA.getIdentification_number() + ". Phone: " + userA.getPhone());
                        tv_InfoB.setText("Ông(Bà): " + userB.getName() + System.getProperty ("line.separator") +
                                "CCCD: " + userB.getIdentification_number()  + ". Phone: " + userB.getPhone());
                        tv_InfoRoom.setText("Phòng: " + room.getRoom_name() + " " + userA.getHouse_name() + System.getProperty ("line.separator")
                                + "Địa chỉ: " + userB.getAddress());
                        tv_InfoPriceRoom.setText(" Giá thuê 1 tháng: " + room.getRoom_price());
                        tv_StartDate.setText(sdate);
                        return;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    public void bindingActive(){
        button.setOnClickListener(this :: btnClickBooking );
    }

    private void btnClickBooking(View view){
        if (edt_time.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Vui lòng nhập thời gian thuê!", Toast.LENGTH_SHORT).show();
        }

        String time = edt_time.getText().toString();
        Pattern pattern = Pattern.compile(".*[^0-9].*");
        if (pattern.matcher(time).matches()) {
            Toast.makeText(getApplicationContext(), "Thời gian thuê phải là 1 số!", Toast.LENGTH_SHORT).show();
        }
        if (Integer.parseInt(time) > 12) {
            Toast.makeText(getApplicationContext(), "Thời gian thuê phải ít hơn 1 năm!", Toast.LENGTH_SHORT).show();
        }

        Date date = Calendar.getInstance().getTime();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 2;
        String sdate;
        if(month < 10){
            sdate =   year + "-0" + month + "-" +"1";
        }else{
            sdate =   year + "-" + month + "-" +"1";
        }
        int text = Integer.parseInt(time);
        int emonth = month + text;
        String edate;
        if(emonth > 12){
            int nYear = year + 1;
            emonth = emonth - 12;
            edate =  nYear + "-0" + emonth + "-" +"1";
        }else{
            edate =  year + "-0" + emonth + "-" +"1";
        }



        Bundle bundle = getIntent().getExtras();
        Room room = (Room) bundle.get("Object_UserRoom");
        SessionManagement session = new SessionManagement(getApplicationContext());
        User userB = session.getUser();
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Booking");
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            long numBooking;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                numBooking = snapshot.getChildrenCount();
                DaoBooking daoBooking = new DaoBooking();
                Booking booking = new Booking();
                booking.setBooking_id(String.valueOf(numBooking + 1));
                booking.setCreate_date(sdate);
                booking.setExpire_date(edate);
                booking.setHost_id(room.getUser_id());
                booking.setNote("");
                booking.setRoom_area(room.getRoom_area());
                booking.setRoom_floor(room.getRoom_floor());
                booking.setRoom_id(room.getRoom_id());
                booking.setRoom_name(room.getRoom_name());
                booking.setRoom_price(room.getRoom_price());
                booking.setTenant_id(userB.getUser_id());
                booking.setType(Booking.Type.REGISTER);
                daoBooking.add(booking).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(), "Booking thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        DatabaseReference roomRef = FirebaseDatabase.getInstance().getReference("Room");
        roomRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DaoRoom daoRoom = new DaoRoom();
                int currentslot = Integer.parseInt(room.getCurrent_slot()) + 1;
                room.setCurrent_slot(currentslot + "");
                daoRoom.add(room).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}