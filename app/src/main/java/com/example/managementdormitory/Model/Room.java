package com.example.managementdormitory.Model;

import java.io.Serializable;

public class Room implements Serializable {
    private String room_id;
    private String room_name;
    private String room_area;
    private String max_slot;
    private String current_slot;
    private String room_price;
    private String room_floor;
    private String user_id;
    private Status status;
    private String note;
    public enum Status{
        ACTIVE,INACTIVE
    }
    public Room() {
    }

    public Room(String room_id, String room_name, String room_area, String max_slot, String current_slot, String room_price, String room_floor, String user_id, Status status, String note) {
        this.room_id = room_id;
        this.room_name = room_name;
        this.room_area = room_area;
        this.max_slot = max_slot;
        this.current_slot = current_slot;
        this.room_price = room_price;
        this.room_floor = room_floor;
        this.user_id = user_id;
        this.status = status;
        this.note = note;
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

    public String getRoom_area() {
        return room_area;
    }

    public void setRoom_area(String room_area) {
        this.room_area = room_area;
    }

    public String getMax_slot() {
        return max_slot;
    }

    public void setMax_slot(String max_slot) {
        this.max_slot = max_slot;
    }

    public String getCurrent_slot() {
        return current_slot;
    }

    public void setCurrent_slot(String current_slot) {
        this.current_slot = current_slot;
    }

    public String getRoom_price() {
        return room_price;
    }

    public void setRoom_price(String room_price) {
        this.room_price = room_price;
    }

    public String getRoom_floor() {
        return room_floor;
    }

    public void setRoom_floor(String room_floor) {
        this.room_floor = room_floor;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
