package com.example.dashboard.network;

import android.util.Log;

import com.example.dashboard.data.Preferences;
import com.example.dashboard.model.Product;
import com.example.dashboard.model.ProductsResponse;
import com.example.dashboard.model.User;
import com.example.dashboard.model.UserResponseObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestCodes {
    private void getAllProducts() {

        DataService service = RetrofitClientInstance.getRetrofitInstance()
                .create(DataService.class);

        Call<ProductsResponse> call = service.getAllProducts();

        call.enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {

                if(response.body().error) {

                }
                else {
                    for(Product product: response.body().productList) {

                    }
                }
            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {

            }
        });
    }

    private void postOneProduct(Product product) {


        DataService service = RetrofitClientInstance.getRetrofitInstance()
                .create(DataService.class);

        Call<Product> call = service.postOneProduct(product);

        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {

                if(response.body().error){

                }
                else {
                    Log.d("MainActivity.java", "onResponse: "  + response.body().toString());

                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });
    }

    private void postNewUser(User user) {


        DataService service = RetrofitClientInstance.getRetrofitInstance()
                .create(DataService.class);

        Call<UserResponseObject> call = service.postNewUser(user.userType, user);//(user.userType, user.email, user.name, user.storeName, user.phone, user.address.street, user.address.pincode, user.address.city, user.address.state);

        call.enqueue(new Callback<UserResponseObject>() {
            @Override
            public void onResponse(Call<UserResponseObject> call, Response<UserResponseObject> response) {

                if(response.body().error){

                }
                else {

                }
            }

            @Override
            public void onFailure(Call<UserResponseObject> call, Throwable t) {

            }
        });
    }

    private void getUserWithEmail(String email) {


        DataService service = RetrofitClientInstance.getRetrofitInstance()
                .create(DataService.class);

        Call<UserResponseObject> call = service.getUserWithEmail(email);

        call.enqueue(new Callback<UserResponseObject>() {
            @Override
            public void onResponse(Call<UserResponseObject> call, Response<UserResponseObject> response) {

                if(response.body().error){

                }
                else {
                    if(response.body().user != null){
//                        Preferences preferences = Preferences.getPreferences();
//                        preferences.saveUser(response.body().user);

                    }
                    else {

                    }

                }
            }

            @Override
            public void onFailure(Call<UserResponseObject> call, Throwable t) {

            }
        });
    }
}
