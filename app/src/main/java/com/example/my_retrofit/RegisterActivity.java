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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    Button btn_Signup;
    EditText Username_Txt,Email_Txt,Password_Txt,Confirm_password_Txt;
    TextView textView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn_Signup = findViewById(R.id.btn_Signup);
        Username_Txt=findViewById(R.id.Username_Txt);
        Email_Txt = findViewById(R.id.Email_Txt);
        Password_Txt=findViewById(R.id.Password_Txt);
        Confirm_password_Txt=findViewById(R.id.Confirm_Password_Txt);

        btn_Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(Email_Txt.getText().toString()) || TextUtils.isEmpty(Username_Txt.getText().toString()) || TextUtils.isEmpty(Password_Txt.getText().toString()) || TextUtils.isEmpty(Confirm_password_Txt.getText().toString()) ){

                    String message ="Enter all inputs";
                    Toast.makeText(RegisterActivity.this,message,Toast.LENGTH_SHORT).show();
                } else {
                    RegisterRequest registerRequest = new RegisterRequest();
                    registerRequest.setEmail(Email_Txt.getText().toString());
                    registerRequest.setPassword(Password_Txt.getText().toString());
                    registerRequest.setUsername(Username_Txt.getText().toString());
                    registerUser(registerRequest);
                }
            }
        });


    }
    public void registerUser(RegisterRequest registerRequest){


        Call<RegisterResponse> registerResponseCall = ApiClient.getService().registerUsers(registerRequest);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                if (response.isSuccessful()){

                    String message ="Registration is Successful";
                    Toast.makeText(RegisterActivity.this,message,Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                    finish();

                }else {
                    String message ="An error occurred please try again later";
                    Toast.makeText(RegisterActivity.this,message,Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(RegisterActivity.this,message,Toast.LENGTH_SHORT).show();

            }
        });
    }
}