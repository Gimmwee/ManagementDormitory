package com.example.managementdormitory.Fragment;

import static com.facebook.FacebookSdk.getApplicationContext;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.managementdormitory.R;
import com.example.managementdormitory.UserListAreaRoomAcivity;

public class FragmentHome extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){
        return inflater.inflate(R.layout.activity_home,container,false);

    }
    private ImageButton btBooking;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btBooking = view.findViewById(R.id.imgBtBooking);
        btBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickgotoBooking();

            }
        });


    }
    public void clickgotoBooking(){
        Intent i = new Intent(getApplicationContext(), UserListAreaRoomAcivity.class);
        startActivity(i);
    }
}