package com.example.dashboard.requests;

import android.util.Log;

import com.example.dashboard.model.User;
import com.example.dashboard.model.UserResponseObject;
import com.example.dashboard.network.DataService;
import com.example.dashboard.network.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserGetter {
    private static final String TAG = "UserGetter.java";

    private DataService service;

    public UserGetter() {
        service = RetrofitClientInstance.getRetrofitInstance()
                .create(DataService.class);
    }

    public User getUserWithEmail(String email) {
        User foundUser = null;

        Call<UserResponseObject> call = service.getUserWithEmail(email);

        call.enqueue(new Callback<UserResponseObject>() {
            @Override
            public void onResponse(Call<UserResponseObject> call, Response<UserResponseObject> response) {
                if(response.body().error) {
                    Log.d(TAG, "onResponse: " + response.body().message);
                }
            }

            @Override
            public void onFailure(Call<UserResponseObject> call, Throwable t) {

            }
        });

        return foundUser;
    }
}
