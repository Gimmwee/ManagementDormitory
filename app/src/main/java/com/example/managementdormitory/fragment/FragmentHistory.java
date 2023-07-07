package com.example.managementdormitory.fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.managementdormitory.Model.Booking;
import com.example.managementdormitory.Model.User;
import com.example.managementdormitory.R;
import com.example.managementdormitory.SessionManagement;
import com.example.managementdormitory.adapter.RecycleViewAdapter;
import com.example.managementdormitory.bean.HistoryBEAN;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class FragmentHistory extends Fragment implements RecycleViewAdapter.ItemListener {
    private RecycleViewAdapter adapter;
    private RecyclerView recyclerView;

    //test
    private RecyclerView rcv_ListHistory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle saveInstanceState){
        return inflater.inflate(R.layout.activity_history_booking,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getList();
        rcv_ListHistory = view.findViewById(R.id.recycleViewHis);


    }

    private void getList(){
        List<Booking> LBooking = new ArrayList<>();
        List<User> LUser = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("User");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    User u = ds.getValue(User.class);
                    LUser.add(u);
                }
                DatabaseReference myRef1 = database.getReference("Booking");
                myRef1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            Booking u1 = ds.getValue(Booking.class);
                            LBooking.add(u1);
                        }
                        rcvListHistory( LBooking,  LUser);

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




    //test
    private void rcvListHistory(List<Booking> lBooking,List<User> lUser) {
        SessionManagement sessionManagement = new SessionManagement(getActivity().getApplicationContext());
        User u1 = sessionManagement.getUser();
        List<HistoryBEAN> list = new ArrayList<>();
        for(int i=0;i<lBooking.size();i++){
            if(lBooking.get(i).getTenant_id().equals(u1.getUser_id())){
                for(int j =0;j<lUser.size();j++){
                    String type = lBooking.get(i).getType().toString();
                    if(lUser.get(j).getUser_id().equals(lBooking.get(i).getHost_id())){
                        HistoryBEAN historyBEAN = new HistoryBEAN();

                        historyBEAN.setBooking_id(lBooking.get(i).getBooking_id());
                        historyBEAN.setRoom_id(lBooking.get(i).getRoom_id());
                        historyBEAN.setRoom_name(lBooking.get(i).getRoom_name());
                        historyBEAN.setRoom_price(lBooking.get(i).getRoom_price());
                        historyBEAN.setRoom_area(lBooking.get(i).getRoom_area());
                        historyBEAN.setRoom_floor(lBooking.get(i).getRoom_floor());
                        historyBEAN.setCreate_date(lBooking.get(i).getCreate_dateBean());
                        historyBEAN.setExpire_date(lBooking.get(i).getExpire_date());
                        historyBEAN.setHost_id(lBooking.get(i).getHost_id());
                        historyBEAN.setTenant_id(lBooking.get(i).getTenant_id());
                        historyBEAN.setType(lBooking.get(i).getType().toString());
                        historyBEAN.setNote(lBooking.get(i).getNote());
                        historyBEAN.setHost_name(lUser.get(j).getName());
                        historyBEAN.setHouse_name(lUser.get(j).getHouse_name());
                        historyBEAN.setHost_phone(lUser.get(j).getPhone());
                        historyBEAN.setHost_address(lUser.get(j).getAddress());

                        if (getDayLeft(lBooking.get(i).getExpire_date()) > 10) {
                            historyBEAN.setDayLeft("Số ngày còn lại :" + getDayLeft(lBooking.get(i).getExpire_date()));
                        } else if (getDayLeft(lBooking.get(i).getExpire_date()) > 0) {
                            historyBEAN.setDayLeft("Số ngày còn lại :" + getDayLeft(lBooking.get(i).getExpire_date()) + "\nChú ý : sắp hết hạn");
                        } else if (getDayLeft(lBooking.get(i).getExpire_date()) < 0){
                            if (!type.equals("REGISTER")) {
                                historyBEAN.setDayLeft("Đã bị Reject");
                            }else{
                                historyBEAN.setDayLeft("Đã hết hạn thuê phòng");
                            }
                        }
                        list.add(historyBEAN);
                    }
                }
            }
        }

        adapter = new RecycleViewAdapter();
        adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rcv_ListHistory.setLayoutManager(manager);
        rcv_ListHistory.setAdapter(adapter);


    }


    @Override
    public void onItemClick(View view, int position) {

    }

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
}

