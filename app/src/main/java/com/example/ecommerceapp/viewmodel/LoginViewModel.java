package com.example.ecommerceapp.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.ecommerceapp.database.DBManager;
import com.example.ecommerceapp.models.User;

public class LoginViewModel extends ViewModel {
    DBManager dbManager;
    public User userData = new User();


    public LoginViewModel(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public long insertUser(User user) {
        return dbManager.insertUserData(user);
    }

    public User validateUser(String mobilenumber, String password) {
        return dbManager.validateUser(mobilenumber, password);
    }
}
