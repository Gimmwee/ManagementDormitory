package com.example.domitory.bean;

public class TenantBEAN {
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
    private String type;
    private String note;
    private String tenant_name;
    private String Tenant_phone;
    private String dayLeft;

    public TenantBEAN() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTenant_name() {
        return tenant_name;
    }

    public void setTenant_name(String tenant_name) {
        this.tenant_name = tenant_name;
    }

    public String getTenant_phone() {
        return Tenant_phone;
    }

    public void setTenant_phone(String tenant_phone) {
        Tenant_phone = tenant_phone;
    }

    public String getDayLeft() {
        return dayLeft;
    }

    public void setDayLeft(String dayLeft) {
        this.dayLeft = dayLeft;
    }

    public TenantBEAN(String booking_id, String room_id, String room_name, String room_price, String room_area, String room_floor, String create_date, String expire_date, String host_id, String tenant_id, String type, String note, String tenant_name, String tenant_phone, String dayLeft) {
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
        this.tenant_name = tenant_name;
        Tenant_phone = tenant_phone;
        this.dayLeft = dayLeft;
    }
}
