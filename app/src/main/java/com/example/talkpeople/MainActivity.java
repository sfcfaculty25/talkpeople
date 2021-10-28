package com.example.talkpeople;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

//   public Button getstart;
//   TextView signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(MainActivity.this,Login.class);
                startActivity(intent);
            }
        },3000);
//    getstart = (Button) findViewById(R.id.getstart);
//Thread thread = new Thread()
//{
//
//    @Override
//    public void run() {
////        super.run();
//        try {
//           // sleep(4000);
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//        finally {
//
//        }
//    }
//};



//        Thread thread=new Thread();
//
//
//        try {
//            sleep(3000);
//            Intent intent= new Intent(MainActivity.this,Login.class);
//            startActivity(intent);
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        thread.start();
//    getstart.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//
//
//
//
//
//
//
//        }
//    });
    }

//    public void click(View view) {
//        Intent intent = new Intent(getApplicationContext(),login.class);
//        startActivity(intent);
//
//    }
}