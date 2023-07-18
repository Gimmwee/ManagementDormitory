package com.example.domitory.Model;

import java.io.Serializable;

public class User implements Serializable {
    private String user_id;
    private String username;
    private String password;
    private String name;
    private String house_name;
    private String phone;
    private String email;
    private String address;
    private String identification_number;
    private Role role;
    public enum Role{
        ADMIN,USER
    }


    public User(String user_id, String username, String password, String name, String house_name, String phone, String email, String address, String identification_number, Role role) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.house_name = house_name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.identification_number = identification_number;
        this.role = role;
    }

    public User(String user_id, String username, String password, String name, String phone, String email, String address, String identification_number, Role role) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.identification_number = identification_number;
        this.role = role;
    }

    public User() {
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHouse_name() {
        return house_name;
    }

    public void setHouse_name(String house_name) {
        this.house_name = house_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdentification_number() {
        return identification_number;
    }

    public void setIdentification_number(String identification_number) {
        this.identification_number = identification_number;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
