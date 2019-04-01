package com.example.caasexpress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button signupbutton, loginbutton;
    public static final String EXTRA_MESSAGE = "com.example.caasexpress";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signupbutton = (Button) findViewById(R.id.main_signup_bttn);
        loginbutton = (Button) findViewById(R.id.main_login_bttn);
    }

    public void OnClickLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra(EXTRA_MESSAGE, "Login Page!");
        startActivity(intent);
    }

}
