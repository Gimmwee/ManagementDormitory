package com.example.domitory.bean;

public class HistoryBEAN {
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
    private String Host_name;
    private String House_name;
    private String Host_phone;
    private String Host_address;
    private String dayLeft;

    public HistoryBEAN() {
    }

    public String getBooking_id() {
        return booking_id;
    }

    public HistoryBEAN(String booking_id, String room_id, String room_name, String room_price, String room_area, String room_floor, String create_date, String expire_date, String host_id, String tenant_id, String type, String note, String host_name, String house_name, String host_phone, String host_address, String dayLeft) {
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
        Host_name = host_name;
        House_name = house_name;
        Host_phone = host_phone;
        Host_address = host_address;
        this.dayLeft = dayLeft;
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

    public String getHost_name() {
        return Host_name;
    }

    public void setHost_name(String host_name) {
        Host_name = host_name;
    }

    public String getHouse_name() {
        return House_name;
    }

    public void setHouse_name(String house_name) {
        House_name = house_name;
    }

    public String getHost_phone() {
        return Host_phone;
    }

    public void setHost_phone(String host_phone) {
        Host_phone = host_phone;
    }

    public String getHost_address() {
        return Host_address;
    }

    public void setHost_address(String host_address) {
        Host_address = host_address;
    }

    public String getDayLeft() {
        return dayLeft;
    }

    public void setDayLeft(String dayLeft) {
        this.dayLeft = dayLeft;
    }
}
