package com.example.talkpeople;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;

public class signup extends AppCompatActivity  {



    private CheckBox checkBoxVisibility;
    CircleImageView circleImageView;
    EditText name, number, email,password;
    Button changedp , verify;
    StorageReference storageReference;
    FirebaseAuth fAuth;
    String userId;
    View Verify;
//    Button getstart;
    DatabaseReference db ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        checkBoxVisibility = findViewById(R.id.checkmale);
        checkBoxVisibility = findViewById(R.id.checkfemale);

        boolean isChecked = checkBoxVisibility.isChecked();
        if (isChecked) {
        }

//        getstart = (Button)findViewById(R.id.getstart);
        circleImageView = findViewById(R.id.circleimage);
        changedp = findViewById(R.id.uploadpic);


        storageReference = FirebaseStorage.getInstance().getReference();
        changedp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openGaleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGaleryIntent, 1000);
            }
        });
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        number = findViewById(R.id.number);
        fAuth = FirebaseAuth.getInstance();
        verify = findViewById(R.id.Verify);
        db = FirebaseDatabase.getInstance().getReference().child("Students");

        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vemail = email.getText().toString().trim();
                String vpassword = password.getText().toString().trim();
                String vname = name.getText().toString().trim();
                String vnumber = number.getText().toString().trim();

                if (TextUtils.isEmpty(vemail)) {
                    email.setError("Email is Required");
                    email.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(vemail).matches())
                {
                    email.setError("Enter a Valid Email");
                    email.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(password.getText().toString())) {
                    password.setError("Password is Required");
                    password.requestFocus();
                    return;
                }
                if (vpassword.length() < 6) {
                    password.setError("Password must be less than 6 characters");
                    password.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(vname)) {
                    name.setError("Name is Required.");
                    name.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(vnumber)) {
                    number.setError("Number is Required.");
                    number.requestFocus();
                    return;
                }
                fAuth.createUserWithEmailAndPassword(vemail, vpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            insertStudentData();
                            Toast.makeText(signup.this, "User Created.", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(signup.this,verifyy2.class);
                            startActivity(i);
                     //       startActivity(new Intent(signup.this, verifyy2.class));
                        } else {

                            Toast.makeText(signup.this, "Some Error has been Occured.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });


    }

    private void insertStudentData() {
        String dname= name.getText().toString();
        String demail = email.getText().toString();
        String dpassword = password.getText().toString();
        String dnumber = number.getText().toString();

        Students students= new Students(dname,demail,dpassword,dnumber);

        db.push().setValue(students);
        Toast.makeText(signup.this, "Data Inserted", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1000){
            if(resultCode== Activity.RESULT_OK){
                Uri imageUri = data.getData();
                circleImageView.setImageURI(imageUri);

            }
        }
    }
    private void uploadImageToFirebase(Uri imageUri) {
        //upload the image to firebase
        StorageReference fileRef = storageReference.child("profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(signup.this, "Image Uploaded.", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(signup.this, "Failed.", Toast.LENGTH_SHORT).show();
            }
        });
    }


//    public void Veri(View view) {
//        Intent intent = new Intent(signup.this,verifyy2.class);
//        startActivity(intent);
//    }
}
