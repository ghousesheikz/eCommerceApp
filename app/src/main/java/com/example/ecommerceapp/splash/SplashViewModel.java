package com.example.ecommerceapp.splash;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.ecommerceapp.auth.User;

public class SplashViewModel extends AndroidViewModel {
    private SplashRepository splashRepository;
    User isUserAuthenticatedLiveData;
    LiveData<User> userLiveData;

    public SplashViewModel(Application application) {
        super(application);
        splashRepository = new SplashRepository();
    }

    void checkIfUserIsAuthenticated() {
        isUserAuthenticatedLiveData = splashRepository.checkIfUserIsAuthenticatedInFirebase();
    }

    void setUid(String uid) {
        userLiveData = splashRepository.addUserToLiveData(uid);
    }
}