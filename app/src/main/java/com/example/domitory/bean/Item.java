package com.example.domitory.bean;

import java.io.Serializable;

public class Item implements Serializable {
    private String category,area,room,date;

    public Item() {
    }

    public Item(String category, String area, String room, String date) {
        this.category = category;
        this.area = area;
        this.room = room;
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}