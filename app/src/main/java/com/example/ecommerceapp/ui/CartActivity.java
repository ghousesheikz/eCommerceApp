package com.example.ecommerceapp.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.adapter.CartAdapter;
import com.example.ecommerceapp.models.CartPojo;
import com.example.ecommerceapp.viewmodel.MainViewModel;

import java.util.ArrayList;

public class CartActivity extends BaseActivity implements CartAdapter.changeListener {
    private TextView total_amount;
    private TextView shipping;
    private CartAdapter mAdapter;
    private Button buy_;
    private MainViewModel mainViewModel;
    private RecyclerView product_list;
    private ArrayList<CartPojo> cartArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        setToolbar("Cart");

        cartArrayList = new ArrayList<>();
        mainViewModel = new MainViewModel(dbManager);
        mainViewModel.fetchCartDetails();

        initRecyclerView();

    }


    private void initRecyclerView() {
        product_list = findViewById(R.id.product_list);
        mAdapter = new CartAdapter(mainViewModel.cartDetailsList, this, activity);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        product_list.setLayoutManager(layoutManager);
        product_list.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        product_list.setItemAnimator(new DefaultItemAnimator());
        product_list.setAdapter(mAdapter);
    }

    @Override
    public void quantityChange(int position, String quantity) {

    }



}