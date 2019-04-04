package com.example.caasexpress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    private Button SignupButton;
    private EditText InputName, InputPhoneNumber, InputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        SignupButton = (Button) findViewById(R.id.register_bttn);
        InputName = (EditText) findViewById(R.id.register_username_input);
        InputPhoneNumber = (EditText) findViewById(R.id.register_phn_no_input);
        InputPassword = (EditText) findViewById(R.id.register_password_input);
    }
}
