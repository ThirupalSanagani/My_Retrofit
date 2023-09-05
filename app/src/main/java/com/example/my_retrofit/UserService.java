package com.example.my_retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("authenticate/")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    @POST("User/SignIn")
    Call<RegisterResponse> registerUsers(@Body RegisterRequest registerRequest);
}
