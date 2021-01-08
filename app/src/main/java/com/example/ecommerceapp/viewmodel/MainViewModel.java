package com.example.ecommerceapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ecommerceapp.database.DBManager;
import com.example.ecommerceapp.models.CartPojo;
import com.example.ecommerceapp.models.LstProduct;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {
    DBManager dbManager;


    public MainViewModel(DBManager dbManager) {
        this.dbManager = dbManager;
    }

   public ArrayList<LstProduct> productDetailsList = new ArrayList<>();
    public ArrayList<CartPojo> cartDetailsList = new ArrayList<>();


   public void fetchProductDetails() {
        productDetailsList = dbManager.getProductData();
    }

    public void fetchCartDetails() {
        cartDetailsList = dbManager.getCartData();
    }

    public void insertCartData(CartPojo mCartPojo){
       dbManager.insertCartData(mCartPojo);
    }
}
