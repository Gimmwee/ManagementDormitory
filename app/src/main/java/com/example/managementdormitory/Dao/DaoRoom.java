package com.example.managementdormitory.Dao;

import com.example.managementdormitory.Model.Room;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DaoRoom {
    private DatabaseReference databaseReference;

    public DaoRoom() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Room.class.getSimpleName());
    }
    public Task<Void> add (Room room){
        return databaseReference.child(String.valueOf(room.getRoom_id())).setValue(room);
    }
    public Task<Void> update(String key, HashMap<String,Object> hashMap){
        return databaseReference.child(key).updateChildren(hashMap);
    }
    public Task<Void> remove(String key){
        return databaseReference.child(key).removeValue();
    }
}
