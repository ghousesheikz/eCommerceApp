package com.example.ecommerceapp.utils;


import com.example.ecommerceapp.ui.BaseActivity;
import com.example.ecommerceapp.ui.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {SharedPrefModule.class})
public interface MyComponent {
    void inject(BaseActivity activity);
}
