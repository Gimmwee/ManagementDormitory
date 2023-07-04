package com.example.managementdormitory;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.managementdormitory.Model.User;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private EditText email,pass;
    private Button btnLogin ,btnRegister;

    private ImageView imageTitle,facebook_login,google_login;
    private GoogleSignInClient client;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        Booking booking = new Booking("1","1","P102","5.000.000đ","50m2","1",Booking.getCurrentDateTime(),Booking.getCurrentDateTime(),"2","1",Booking.Type.REGISTER,"");
//        DaoBooking daoBooking = new DaoBooking();
//        daoBooking.add(booking).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void unused) {
//                Toast.makeText(getApplicationContext(), "Record is inserted", Toast.LENGTH_SHORT).show();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        Room room = new Room("1","P102","50m2","5","1","5.000.000đ","1","2",Room.Status.ACTIVE,"");
//        DaoRoom daoRoom = new DaoRoom();
//        daoRoom.add(room).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void unused) {
//                Toast.makeText(getApplicationContext(), "Record is inserted", Toast.LENGTH_SHORT).show();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        User user = new User("2","admin","123456","Lê Hoàng Minh","Trọ Minh An","098789878","admin@gmail.com","99 Cầu Giấy - Hà Nội","088276889767", User.Role.ADMIN);
//        DaoUser userDao = new DaoUser();
//        userDao.add(user).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void unused) {
//                Toast.makeText(getApplicationContext(), "Record is inserted", Toast.LENGTH_SHORT).show();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
        email = findViewById(R.id.username_input);
        pass = findViewById(R.id.password_input);
        btnLogin = findViewById(R.id.login_button);
        btnRegister = findViewById(R.id.register_button);
        imageTitle = findViewById(R.id.image_login);
//        facebook_login = findViewById(R.id.facebook_login);
//        google_login = findViewById(R.id.google_login);
//        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
//        client = GoogleSignIn.getClient(this,options);
//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//        google_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),GoogleSignInActivity.class);
//                startActivity(intent);
//            }
//        });
//        facebook_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LoginActivity.this,FacebookAuthActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                startActivity(intent);
//            }
//        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void Login(){
        String username, password;
        username = email.getText().toString();
        password = pass.getText().toString();
        if(TextUtils.isEmpty(username)){
            Toast.makeText(this,"Please enter username!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password!",Toast.LENGTH_SHORT).show();
            return;
        }
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("User");
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {

                    String pass = userSnapshot.child("password").getValue(String.class);
                    String usern = userSnapshot.child("email").getValue(String.class);
                    User u = userSnapshot.getValue(User.class);
                    if (u.getPassword().equals(password.toString()) && u.getUsername().equals(username.toString())) {
                        SessionManagement session = new SessionManagement(getApplicationContext());
                        // Initialize session management object
                        long expireTimeMillis = System.currentTimeMillis() + (30 * 60 * 1000); // 30 minutes in milliseconds
                        session.createLoginSession(expireTimeMillis, u);
                        // Check if user is already logged in
                        if (session.isLoggedIn()) {
                            // Set the expire time for the session to be 30 minutes from now

                            // Redirect to main activity
                            if (u.getRole().equals(User.Role.ADMIN)) {
                                Toast.makeText(getApplicationContext(), "Login successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, AdminMainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "Login successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                            finish();
                        }
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Login Failed!",Toast.LENGTH_SHORT).show();
                return;
            }
        });




    }
    private void Register(){
        Intent i = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(i);
    }
    private void conformUser(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user.getEmail();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("User");

        List<User> u1 = new ArrayList<>();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    User u = dataSnapshot.getValue(User.class);
                    u1.add(u);
                }
                for (int i =0 ;i< u1.size();i++){
                    if(u1.get(i).getEmail().equals(email)){
                        if( u1.get(i).getRole().equals(User.Role.USER)){
                            setContentView(R.layout.activity_main);

                        }

                    }
                }
//

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

}