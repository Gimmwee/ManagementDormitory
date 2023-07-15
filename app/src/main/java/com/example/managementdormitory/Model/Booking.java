package com.example.managementdormitory.Model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Booking implements Serializable {
    private String booking_id;
    private String room_id;
    private String room_name;
    private String room_price;
    private String room_area;
    private String room_floor;
    private String create_date;
    private String expire_date;
    private String host_id;
    private String tenant_id;
    private Type type;
    private String note;
    public enum Type{
        REGISTER,USER_REJECT,ADMIN_REJECT
    }

    public Booking() {
    }

    public Booking(String booking_id, String room_id, String room_name, String room_price, String room_area, String room_floor, String create_date, String expire_date, String host_id, String tenant_id, Type type, String note) {
        this.booking_id = booking_id;
        this.room_id = room_id;
        this.room_name = room_name;
        this.room_price = room_price;
        this.room_area = room_area;
        this.room_floor = room_floor;
        this.create_date = create_date;
        this.expire_date = expire_date;
        this.host_id = host_id;
        this.tenant_id = tenant_id;
        this.type = type;
        this.note = note;
    }

    public String getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public String getRoom_price() {
        return room_price;
    }

    public void setRoom_price(String room_price) {
        this.room_price = room_price;
    }

    public String getRoom_area() {
        return room_area;
    }

    public void setRoom_area(String room_area) {
        this.room_area = room_area;
    }

    public String getRoom_floor() {
        return room_floor;
    }

    public void setRoom_floor(String room_floor) {
        this.room_floor = room_floor;
    }

    public String getCreate_dateBean() {
        return create_date;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }


    public String getExpire_date() {
        return expire_date;
    }

    public void setExpire_date(String expire_date) {
        this.expire_date = expire_date;
    }

    public String getHost_id() {
        return host_id;
    }

    public void setHost_id(String host_id) {
        this.host_id = host_id;
    }

    public String getTenant_id() {
        return tenant_id;
    }

    public void setTenant_id(String tenant_id) {
        this.tenant_id = tenant_id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formatDateTime = now.format(formatter);
        return formatDateTime;
    }
    /*DaoBooking daoBooking = new DaoBooking();
    DaoHistory_Booking daoHistory_booking = new DaoHistory_Booking();
    User user = new User(1,"HE151132","Nguyen Minh Hoang","hoangnmhe151132@fpt.edu.vn","123456",1,User.Role.ADMIN,"software engineering");
    Booking booking = new Booking(1,1,Booking.getCurrentDateTime(),Booking.Status.PROCESSING,1,1,"Luxury");
    History_Booking history_booking = new History_Booking(1,1,"DA","P1",History_Booking.getCurrentDateTime());
                daoHistory_booking.add(history_booking).addOnSuccessListener(new OnSuccessListener<Void>() {
        @Override
        public void onSuccess(Void unused) {
            Toast.makeText(MainActivity.this, "Record is inserted", Toast.LENGTH_SHORT).show();
        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    });

}
        });*/
}
