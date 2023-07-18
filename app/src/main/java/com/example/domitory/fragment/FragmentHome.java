package com.example.domitory.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.domitory.R;


public class FragmentHome extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle saveInstanceState){
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
       // Intent i = new Intent(getApplicationContext(), UserListAreaRoomActivity.class);
       // startActivity(i);
    }
}