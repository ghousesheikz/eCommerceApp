package com.example.ecommerceapp.ui;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.database.DBManager;
import com.example.ecommerceapp.utils.DaggerMyComponent;
import com.example.ecommerceapp.utils.MyComponent;
import com.example.ecommerceapp.utils.SharedPrefModule;

import java.util.Objects;

import javax.inject.Inject;

public class BaseActivity extends AppCompatActivity {
    private MyComponent myComponent;
    Activity activity;
    DBManager dbManager;
    @Inject
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myComponent = DaggerMyComponent.builder().sharedPrefModule(new SharedPrefModule(this)).build();
        myComponent.inject(this);
        activity = this;
        try {
            dbManager = new DBManager(this);
            dbManager.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    protected void setToolbar(String title) {
        try {
            Toolbar myToolbar = findViewById(R.id.my_toolbar);
            setSupportActionBar(myToolbar);
            setTitle(title);
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected Activity getActivity() {
        return activity;
    }
}
