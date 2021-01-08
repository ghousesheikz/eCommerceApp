package com.example.ecommerceapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.models.LstProduct;

import static com.example.ecommerceapp.utils.Constants.ISLOGIN;


public class SplashActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sharedPreferences.getString(ISLOGIN, "").equalsIgnoreCase("1")) {
                    Intent mIntent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(mIntent);
                    finish();
                } else {
                    Intent mIntent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(mIntent);
                    finish();
                }
            }
        }, 3000);
    }


}