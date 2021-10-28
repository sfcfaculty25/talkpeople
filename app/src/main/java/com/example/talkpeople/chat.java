package com.example.talkpeople;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import meow.bottomnavigation.MeowBottomNavigation;

public class chat extends AppCompatActivity {
MeowBottomNavigation meo;
    RecyclerView chatlist;
    UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        meo=(MeowBottomNavigation)findViewById(R.id.botton_nav);
        meo.add(new MeowBottomNavigation.Model(1,R.drawable.ic_baseline_home_24));
        meo.add(new MeowBottomNavigation.Model(2,R.drawable.ic_baseline_call_24));
        meo.add(new MeowBottomNavigation.Model(3,R.drawable.ic_baseline_notifications_24));
        meo.add(new MeowBottomNavigation.Model(4,R.drawable.ic_baseline_settings_24));


        chatlist=findViewById(R.id.chatlist);
        chatlist.setAdapter(adapter);




    }
}