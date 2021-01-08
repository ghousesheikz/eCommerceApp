package com.example.ecommerceapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.adapter.CartAdapter;
import com.example.ecommerceapp.models.CartPojo;
import com.example.ecommerceapp.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.Objects;

public class CartActivity extends BaseActivity implements CartAdapter.changeListener {
    private TextView total_amount;
    private TextView shipping;
    private CartAdapter mAdapter;
    private Button buy_;
    private MainViewModel mainViewModel;
    private CheckBox select_all;
    private RecyclerView product_list;
    private ArrayList<CartPojo> cartArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        setToolbar();

        cartArrayList = new ArrayList<>();

        select_all = findViewById(R.id.select_all);


        total_amount = findViewById(R.id.total_amount);
        shipping = findViewById(R.id.shipping);

        product_list = findViewById(R.id.product_list);
        mainViewModel = new MainViewModel(dbManager);

        mainViewModel.fetchCartDetails();

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

    @Override
    public void delete(int position) {

    }


}