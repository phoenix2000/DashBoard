package com.example.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {
    private Button button,button2,button3,button5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.button);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        button5=(Button) findViewById(R.id.button5);
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
}
