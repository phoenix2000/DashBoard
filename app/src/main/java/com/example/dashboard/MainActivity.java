package com.example.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.transition.TransitionManager;

import com.example.dashboard.data.Preferences;
import com.example.dashboard.model.Product;
import com.example.dashboard.model.ProductDescription;
import com.example.dashboard.model.ProductsResponse;
import com.example.dashboard.model.User;
import com.example.dashboard.model.UserResponseObject;
import com.example.dashboard.network.DataService;
import com.example.dashboard.network.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Button button,button2,button3,requestButton;
    private TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.button);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
//        button5=(Button) findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openCart1();

            }
        });
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openCart2();

            }
        });
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openCart3();

            }
        });

        requestButton = (Button)findViewById(R.id.request_button);
        requestButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                openProductsActivity();

//                getAllProducts();

//                User user = new User("test5@test.com", "testFromAndroid", "customer", "", "234567", "xyz", "490023", "cwa", "MP");
//                user._id = "6075ccc122e98800049e3794";
//                postOneProduct(new Product("anaar", 31.1f, 100f, "kg", "wholesaler",
//                        new ProductDescription("grocery", "fruit", ""), user));

                //postNewUser(new User("test10@test.com", "testFromAndroid", "customer", "", "234567", "xyz", "490023", "cwa", "MP"));

                getUserWithEmail("test1@gmail.com");

            }
        });
        responseText = (TextView)findViewById(R.id.response_text);
    }
    public void openCart1(){
        Intent intent = new Intent(this, Cart.class);
        startActivity(intent);
    }
    public void openCart2(){
        Intent intent = new Intent(this, Cart2.class);
        startActivity(intent);
    }
    public void openCart3(){
        Intent intent = new Intent(this, cart3.class);
        startActivity(intent);
    }

    private void openProductsActivity() {
        Intent intent = new Intent(this, ProductsActivity.class);
        startActivity(intent);
    }

    private void getAllProducts() {
        requestButton.setEnabled(false);

        DataService service = RetrofitClientInstance.getRetrofitInstance()
                .create(DataService.class);

        Call<ProductsResponse> call = service.getAllProducts();

        call.enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                requestButton.setEnabled(true);
                if(response.body().error) {
                    responseText.setText(response.body().message);
                }
                else {
                    for(Product product: response.body().productList) {
                        responseText.append(product.name + ", " + product.quantity + /*", " + product.description.type +*/ ", " + product.seller.name + "\n");
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {
                requestButton.setEnabled(true);
                responseText.setText(t.getMessage());
            }
        });
    }

    private void postOneProduct(Product product) {
        requestButton.setEnabled(false);

        DataService service = RetrofitClientInstance.getRetrofitInstance()
                .create(DataService.class);

        Call<Product> call = service.postOneProduct(product);

        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                requestButton.setEnabled(true);
                if(response.body().error){
                    responseText.setText(response.body().message);
                }
                else {
                    Log.d("MainActivity.java", "onResponse: "  + response.body().toString());
                    //responseText.setText(response.body().name + ", " + response.body().description.type + response.body().seller.name);
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                requestButton.setEnabled(true);
                responseText.setText(t.getMessage());
            }
        });
    }

    private void postNewUser(User user) {
        requestButton.setEnabled(false);

        DataService service = RetrofitClientInstance.getRetrofitInstance()
                .create(DataService.class);

        Call<UserResponseObject> call = service.postNewUser(user.userType, user);//(user.userType, user.email, user.name, user.storeName, user.phone, user.address.street, user.address.pincode, user.address.city, user.address.state);

        call.enqueue(new Callback<UserResponseObject>() {
            @Override
            public void onResponse(Call<UserResponseObject> call, Response<UserResponseObject> response) {
                requestButton.setEnabled(true);
                if(response.body().error){
                    responseText.setText(response.body().message);
                }
                else {
                    responseText.setText(response.body().user.name + ", " + response.body().user.address.city + ", " + response.body().user._id);
                }
            }

            @Override
            public void onFailure(Call<UserResponseObject> call, Throwable t) {
                requestButton.setEnabled(true);
                responseText.setText(t.getMessage());
            }
        });
    }

    private void getUserWithEmail(String email) {
        requestButton.setEnabled(false);

        DataService service = RetrofitClientInstance.getRetrofitInstance()
                .create(DataService.class);

        Call<UserResponseObject> call = service.getUserWithEmail(email);

        call.enqueue(new Callback<UserResponseObject>() {
            @Override
            public void onResponse(Call<UserResponseObject> call, Response<UserResponseObject> response) {
                requestButton.setEnabled(true);
                if(response.body().error){
                    responseText.setText(response.body().message);
                }
                else {
                    if(response.body().user != null){
                        Preferences preferences = Preferences.getPreferences(getApplicationContext());
                        preferences.saveUser(response.body().user);
                        responseText.setText(preferences.getCurrentUser().name + ", " + preferences.getCurrentUser()._id);
                    }
                    else {
                        responseText.setText(response.body().message);
                    }

//                    responseText.setText(response.body().user.name + ", " + response.body().user.phone + ", " + response.body().user._id);
                }
            }

            @Override
            public void onFailure(Call<UserResponseObject> call, Throwable t) {
                requestButton.setEnabled(true);
                responseText.setText(t.getMessage());
            }
        });
    }
}
