package com.example.my_retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.BreakIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button btn_Login;
    EditText Username_Txt,Password_Txt;
    TextView textView2,createAccount;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_Login = findViewById(R.id.btn_Login);
        Username_Txt = findViewById(R.id.Username_Txt);
        Password_Txt = findViewById(R.id.Password_Txt);
        createAccount = findViewById(R.id.createAccount);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(Username_Txt.getText().toString()) || TextUtils.isEmpty(Password_Txt.getText().toString())) {
                    String message = "Enter all inputs";
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                } else {
                        LoginRequest loginRequest = new LoginRequest();
                        loginRequest.setUsername(Username_Txt.getText().toString());
                        loginRequest.setPassword(Password_Txt.getText().toString());
                        loginUser(loginRequest);
                }
            }
        });
    }
    public void loginUser(LoginRequest loginRequest){
        Call<LoginResponse> loginResponseCall = ApiClient.getService().loginUser(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()){
                    LoginResponse loginResponse= response.body();
                    startActivity(new Intent(LoginActivity.this,MainActivity.class).putExtra("data",loginResponse));
                    finish();

                }else{
                    String message ="Registration is Successful";
                    Toast.makeText(LoginActivity.this,message,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(LoginActivity.this,message,Toast.LENGTH_SHORT).show();
            }
        });
    }
}