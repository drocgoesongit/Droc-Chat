package com.example.drocchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        auth= FirebaseAuth.getInstance();
        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(auth.getCurrentUser() != null){
                    Intent i = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(i);
                }
                else{
                    Intent j = new Intent(SplashScreen.this, SignupActivity.class);
                    startActivity(j);
                }
            }
        },4000);
    }

}