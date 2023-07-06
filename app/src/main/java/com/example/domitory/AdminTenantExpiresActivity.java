package com.example.domitory;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class AdminTenantExpiresActivity extends AppCompatActivity {
    private RecyclerView rcv_listTenant;
    private ImageView signOut_button3,btn_user1;
    //private TenantAdapter mTenantAdapter;
    private Button btn_ListTenant1,btn_TenantExpires1,btn_TenantHistory1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_tenant_expires);
//        initUi();
//        Logout1();
//        getListTenat();

    }

//    private void initUi() {
//        rcv_listTenant = findViewById(R.id.admin_listTenant1);
//        btn_ListTenant1 = findViewById(R.id.btn_ListTenant1);
//        btn_ListTenant1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(), AdminListTenantActivity.class);
//                startActivity(i);
//            }
//        });
//        btn_TenantHistory1 = findViewById(R.id.btn_TenantHistory1);
//        btn_TenantHistory1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(), AdminTenantHistoryActivity.class);
//                startActivity(i);
//            }
//        });
//    }

//    private void Logout1(){
//        signOut_button3 = findViewById(R.id.logout_a3);
//        signOut_button3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FirebaseAuth.getInstance().signOut();
//                Intent i = new Intent(getApplicationContext(), Login.class);
//                startActivity(i);
//                finish();
//                getListTenat();
//            }
//        });
//    }

//    private void rcv_listTenant(List<Booking> ListBooking, List<User> ListUser){
//        List<TenantBEAN> tenantBEANList = new ArrayList<>();
//        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
//        rcv_listTenant.setLayoutManager(layoutManager);
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL);
//        rcv_listTenant.addItemDecoration(dividerItemDecoration);
//        SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
//        User u1 = sessionManagement.getUser();
//        //set tenant bean
//        for(int  i=0;i<ListBooking.size();i++){
//            if(ListBooking.get(i).getHost_id().equals(u1.getUser_id())) {
//                TenantBEAN tenantBEAN = new TenantBEAN();
//                for (int j = 0; j < ListUser.size(); j++) {
//                    String type = ListBooking.get(i).getType().toString();
//                    if (ListBooking.get(i).getTenant_id().equals(ListUser.get(j).getUser_id()) && getDayLeft(ListBooking.get(i).getExpire_date()) <= 10 && getDayLeft(ListBooking.get(i).getExpire_date()) > 0 && type.equals("REGISTER")) {
//                        tenantBEAN.setBooking_id(ListBooking.get(i).getBooking_id());
//                        tenantBEAN.setRoom_id(ListBooking.get(i).getRoom_id());
//                        tenantBEAN.setRoom_name(ListBooking.get(i).getRoom_name());
//                        tenantBEAN.setRoom_price(ListBooking.get(i).getRoom_price());
//                        tenantBEAN.setRoom_area(ListBooking.get(i).getRoom_area());
//                        tenantBEAN.setRoom_floor(ListBooking.get(i).getRoom_floor());
//                        tenantBEAN.setCreate_date(ListBooking.get(i).getCreate_dateBean());
//                        tenantBEAN.setExpire_date(ListBooking.get(i).getExpire_date());
//                        tenantBEAN.setHost_id(ListBooking.get(i).getHost_id());
//                        tenantBEAN.setTenant_id(ListBooking.get(i).getTenant_id());
//                        tenantBEAN.setType(ListBooking.get(i).getType().toString());
//                        tenantBEAN.setNote(ListBooking.get(i).getNote());
//                        tenantBEAN.setTenant_name(ListUser.get(j).getName());
//                        tenantBEAN.setTenant_phone(ListUser.get(j).getPhone());
//                        tenantBEAN.setDayLeft("Số ngày còn lại :" + getDayLeft(ListBooking.get(i).getExpire_date()) + "\nChú ý : sắp hết hạn");
//                        tenantBEANList.add(tenantBEAN);
//                    }
//                }
//
//            }
//        }
//
//        mTenantAdapter = new TenantAdapter(this,tenantBEANList);
//        rcv_listTenant.setAdapter(mTenantAdapter);
//    }


    public int getDayLeft(String Expire_date){
        // Định dạng thời gian
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        String curentTime = getCurrentDateTime();
        // Định nghĩa 2 mốc thời gian ban đầu
        Date date1 = Date.valueOf(curentTime);
        Date date2 = Date.valueOf(Expire_date);

        c1.setTime(date1);
        c2.setTime(date2);

        // Công thức tính số ngày giữa 2 mốc thời gian:
        long noDay = (c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000);
        return (int)noDay;

    }

    public static String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formatDateTime = now.format(formatter);
        return formatDateTime;
    }


//    private  void getListTenat(){
//        List<Booking> LBooking = new ArrayList<>();
//        List<User> LUser = new ArrayList<>();
//
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("User");
//        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot ds : snapshot.getChildren()) {
//                    User u = ds.getValue(User.class);
//                    LUser.add(u);
//                }
//                DatabaseReference myRef1 = database.getReference("Booking");
//                myRef1.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        for (DataSnapshot ds : snapshot.getChildren()) {
//                            Booking u1 = ds.getValue(Booking.class);
//                            LBooking.add(u1);
//                        }
//                        rcv_listTenant( LBooking,  LUser);
//                        mTenantAdapter.notifyDataSetChanged();
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//            }
//
//
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
}
