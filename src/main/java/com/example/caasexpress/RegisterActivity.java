package com.example.caasexpress;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class RegisterActivity extends AppCompatActivity {

    private Button SignupButton;
    private EditText InputName, InputPhoneNumber, InputPassword;
    private ProgressDialog LoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        SignupButton = (Button) findViewById(R.id.register_bttn);
        InputName = (EditText) findViewById(R.id.register_username_input);
        InputPhoneNumber = (EditText) findViewById(R.id.register_phn_no_input);
        InputPassword = (EditText) findViewById(R.id.register_password_input);
        LoadingBar = new ProgressDialog(this);

        SignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Signup();
            }
        });
    }


    private void Signup() {

        String name = InputName.getText().toString();
        String phone = InputPhoneNumber.getText().toString();
        String password = InputPassword.getText().toString();

        if(TextUtils.isEmpty(name)){
            Toast.makeText(this, "Please write your Name!", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(phone)){
            Toast.makeText(this, "Please write your Phone Number!", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please create a password!", Toast.LENGTH_SHORT).show();
        }
        else {
            LoadingBar.setTitle("Create Account");
            LoadingBar.setMessage("Please Wait...");
            LoadingBar.setCanceledOnTouchOutside(false);
            LoadingBar.show();

            Validation(name, phone, password);
        }
    }


    private void Validation(final String name, final String phone, final String password) {

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (!(dataSnapshot.child("Users").child(phone).exists())){

                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("Phone", phone);
                    userdataMap.put("Username", name);
                    userdataMap.put("Password", password);

                    RootRef.child("Users").child(phone).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()){
                                        Toast.makeText(RegisterActivity.this, "Your account has been Successfully Created!", Toast.LENGTH_SHORT).show();
                                        LoadingBar.dismiss();
                                        Toast.makeText(RegisterActivity.this, "Now, Login to Your Account...", Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                    }

                                    else {
                                        Toast.makeText(RegisterActivity.this, "ERROR: Please Try Again after sometime...", Toast.LENGTH_SHORT).show();
                                        LoadingBar.dismiss();
                                    }
                                }
                            });
                }

                else {

                    Toast.makeText(RegisterActivity.this, "Phone Number already exits!", Toast.LENGTH_SHORT).show();
                    LoadingBar.dismiss();
                    Toast.makeText(RegisterActivity.this, "Try Again with another Phone Number...", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
