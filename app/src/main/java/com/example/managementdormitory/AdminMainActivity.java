package com.example.managementdormitory;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.facebook.login.Login;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
@RequiresApi(api = Build.VERSION_CODES.O)
public class AdminMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        initUi();
        Logout2();
    }
    private ImageView btn_ListRoom,signOut_button2,btn_ListTN,btn_user;

    private void initUi() {
        btn_ListRoom = findViewById(R.id.btn_ListRoom);
        btn_ListRoom.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onClickGotoListRoom();
            }
        });
        btn_user = findViewById(R.id.btn_user);
        btn_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickGoToListUser();
            }
        });
        btn_ListTN = findViewById(R.id.btn_listTN);
        btn_ListTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickGoToListBooking();
            }
        });

    }
    private void  OnClickGoToListUser(){
//        Intent intent = new Intent(getApplicationContext(),AdminListUserActivity.class);
//        startActivity(intent);
    }
    public  void OnClickGoToListBooking(){
        Intent intent = new Intent(getApplicationContext(),AdminListTenantActivity.class);
        startActivity(intent);
    }

    public void  onClickGotoListRoom(){
        Intent intent = new Intent(getApplicationContext(),AdminListRoomActivity.class);
        startActivity(intent);
    }

    //    private void conformUser(){
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        String email = user.getEmail();
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("User");
//
//        List<User> u1 = new ArrayList<>();
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
//                    User u = dataSnapshot.getValue(User.class);
//                    u1.add(u);
//                }
//                for (int i =0 ;i< u1.size();i++){
//                    if(u1.get(i).getEmail().equals(email)){
//                        if( u1.get(i).getRole().equals(User.Role.ADMIN)){
//                            setContentView(R.layout.activityadmin_main);
//
//                        }
//
//                    }
//                }
////
//
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
//    }
    private void Logout2(){
        signOut_button2 = findViewById(R.id.logout_a2);
        signOut_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Logout();
                finish();
            }
        });
    }
    private void Logout(){
        Intent i = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(i);
    }
}