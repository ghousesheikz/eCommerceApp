package com.example.ecommerceapp.models;

public class User {
    private String mMobileNumber;
    private String mPassword;
    private String mIsLogin;

    public String getmMobileNumber() {
        return mMobileNumber;
    }

    public void setmMobileNumber(String mMobileNumber) {
        this.mMobileNumber = mMobileNumber;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getmIsLogin() {
        return mIsLogin;
    }

    public void setmIsLogin(String mIsLogin) {
        this.mIsLogin = mIsLogin;
    }

    public User() {

    }

    public User(String mMobileNumber, String mPassword, String mIsLogin) {
        this.mMobileNumber = mMobileNumber;
        this.mPassword = mPassword;
        this.mIsLogin = mIsLogin;
    }
}
