package com.example.my_retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

public class MainActivity<loginResponse> extends AppCompatActivity {
    LoginResponse loginResponse;
    TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);

        Intent intent = getIntent();
//        if (Intent.getExtras() != null) {
            loginResponse = (LoginResponse) intent.getSerializableExtra("data");
            username.setText(loginResponse.getUsername());
            Log.e("TAG", "=====>" + loginResponse.getEmail());
        }
    }

