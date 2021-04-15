package com.example.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity {
    TextView name_text, mail_text;
    Button logout_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        logout_btn = findViewById(R.id.logoutbtn);
        name_text = findViewById(R.id.nametext);
        mail_text = findViewById(R.id.mailtext);

        int loginOption = LoginPage.signInOption;
        if(loginOption==2) {
            Intent intent = getIntent();
            if (intent == null) {
                Toast.makeText(this, "intent received == null", Toast.LENGTH_SHORT).show();
            }
            Bundle bundle = intent.getExtras();

            if (bundle != null) {
                String name = bundle.getString("name");
                String mail = bundle.getString("mail");

                name_text.setText("Name: " + name);
                mail_text.setText("Email: " + mail);
            } else {
                name_text.setText("");
                mail_text.setText("");
            }
        }else{
            GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
            if(signInAccount!=null){
                name_text.setText("Name: " + signInAccount.getDisplayName());
                mail_text.setText("Email: " + signInAccount.getEmail());
            } else{
                name_text.setText("2");
                mail_text.setText("2");
            }
        }
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(), LoginPage.class);
            startActivity(intent);
            }
        });
    }
}