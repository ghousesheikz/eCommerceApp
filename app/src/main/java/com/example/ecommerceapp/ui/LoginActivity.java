package com.example.ecommerceapp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.models.User;
import com.example.ecommerceapp.viewmodel.LoginViewModel;

import javax.inject.Inject;

import static com.example.ecommerceapp.utils.Constants.ISLOGIN;
import static com.example.ecommerceapp.utils.Constants.MOBILENUMBER;
import static com.example.ecommerceapp.utils.Constants.PASSWORD;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText edtMobileNumber, edtPassword;
    private Button btnLogin;
    private TextView signUp;
    private LoginViewModel loginViewModel;
    private boolean isNewuser = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = new LoginViewModel(dbManager);
        edtMobileNumber = findViewById(R.id.edt_mobilenumber);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);
        signUp = findViewById(R.id.signup);
        btnLogin.setOnClickListener(this);
        signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_login) {
            if (TextUtils.isEmpty(edtMobileNumber.getText().toString().trim()) && edtMobileNumber.getText().toString().length() != 10) {
                Toast.makeText(this, "Please enter mobile number", Toast.LENGTH_LONG).show();
            } else if (TextUtils.isEmpty(edtPassword.getText().toString().trim())) {
                Toast.makeText(this, "Please enter mobile number", Toast.LENGTH_LONG).show();
            } else {
                if (!isNewuser) {
                    User userData = loginViewModel.validateUser(edtMobileNumber.getText().toString(), edtPassword.getText().toString());
                    if (userData != null) {
                        if (userData.getmIsLogin().equalsIgnoreCase("1")) {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(MOBILENUMBER, userData.getmMobileNumber());
                            editor.putString(PASSWORD, userData.getmMobileNumber());
                            editor.putString(ISLOGIN, userData.getmIsLogin());
                            editor.apply();

                            Intent mIntent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(mIntent);
                            finish();
                        }
                    } else {
                        Toast.makeText(this, "Please Signup to login", Toast.LENGTH_LONG).show();
                    }
                } else {
                    User userData = new User();
                    userData.setmMobileNumber(edtMobileNumber.getText().toString());
                    userData.setmPassword(edtPassword.getText().toString());
                    userData.setmIsLogin("1");

                    long mCount = loginViewModel.insertUser(userData);
                    if (mCount > 0) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(MOBILENUMBER, userData.getmMobileNumber());
                        editor.putString(PASSWORD, userData.getmMobileNumber());
                        editor.putString(ISLOGIN, userData.getmIsLogin());
                        editor.apply();
                        Intent mIntent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(mIntent);
                        finish();
                    }
                }
            }
        } else if (view.getId() == R.id.signup) {
            btnLogin.setText("SIGNUP");
            isNewuser = true;

        }
    }

    @Override
    public void onBackPressed() {

    }
}