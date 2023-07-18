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
import java.time.LocalDateTime;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class AdminListTenantActivity extends AppCompatActivity {
    private RecyclerView rcv_listTenant;
    private ImageView signOut_button3,btn_user1;
    //private TenantAdapter mTenantAdapter;
    private Button btn_ListTenant,btn_TenantExpires,btn_TenantHistory;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_list_tenant);
        initUi();

    }

    private void initUi() {
        rcv_listTenant = findViewById(R.id.admin_listTenant);
        btn_TenantExpires = findViewById(R.id.btn_TenantExpires);
        btn_TenantExpires.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AdminTenantExpiresActivity.class);
                startActivity(i);
            }
        });
        btn_TenantHistory = findViewById(R.id.btn_TenantHistory);
        btn_TenantHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AdminTenantHistoryActivity.class);
                startActivity(i);
            }
        });
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

