package com.example.caasexpress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button signupbutton, loginbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signupbutton = (Button) findViewById(R.id.main_signup_bttn);
        loginbutton = (Button) findViewById(R.id.main_login_bttn);
    }


}
