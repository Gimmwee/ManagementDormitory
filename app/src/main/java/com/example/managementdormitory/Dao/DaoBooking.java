package com.example.managementdormitory.Dao;
import com.example.managementdormitory.Model.Booking;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DaoBooking {
    private DatabaseReference databaseReference;

    public DaoBooking() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Booking.class.getSimpleName());
    }
    public Task<Void> add (Booking booking){
        return databaseReference.child(String.valueOf(booking.getBooking_id())).setValue(booking);
    }
    public Task<Void> update(String key, HashMap<String,Object> hashMap){
        return databaseReference.child(key).updateChildren(hashMap);
    }
    public Task<Void> remove(String key){
        return databaseReference.child(key).removeValue();
    }
}
