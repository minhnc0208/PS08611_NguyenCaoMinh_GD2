package com.example.ps08611_nguyencaominh_gd1.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.ps08611_nguyencaominh_gd1.LoginActivity;
import com.example.ps08611_nguyencaominh_gd1.R;
import com.example.ps08611_nguyencaominh_gd1.RegisterActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        final Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, LoginActivity.class); // gói thư viện hành động
                startActivity(i);
                finish();
            }
        }, 3000);
    }
}