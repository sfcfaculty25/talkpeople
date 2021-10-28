package com.example.talkpeople;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity  {
    EditText email, password;
    Button loginbtn;
    FirebaseAuth fAuth;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email= findViewById(R.id.email);
        password= findViewById(R.id.password);
        loginbtn = findViewById(R.id.login);
        fAuth = FirebaseAuth.getInstance();
        //signup.setOnClickListener(this);
        signup = findViewById(R.id.signup);


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vemail = email.getText().toString().trim();
                String vpassword = password.getText().toString().trim();

                if(TextUtils.isEmpty(vemail)){
                    email.setError("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty(vpassword)){
                    password.setError("Password is Required");
                    return;
                }
//                if(vpassword.length()<6){
//                    password.setError("Password must be Greater than or Equals to 6 characters");
//                    return;
//                }
                //register the user on the fireBase
//

                fAuth.signInWithEmailAndPassword(vemail,vpassword).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "Logged in Successfully.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Login.this,chat.class));
                        }
                        else{
                            Toast.makeText(Login.this, "Error."+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

//                fAuth.signInWithEmailAndPassword("","").addOnFailureListener(login.this, new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//
//                    }
//                });
                Intent var = new Intent(Login.this, chat.class);
                startActivity(var);
            }
        });



    }

    public void sup(View view)
    {
        Intent intent = new Intent(Login.this, signup.class);
        startActivity(intent);
    }


//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.signup:
//                startActivity(new Intent(this,signup.class));
//                break;
//            case R.id.login:
//                startActivity(new Intent(this,chat.class));
//                break;
//        }
//    }
    
}