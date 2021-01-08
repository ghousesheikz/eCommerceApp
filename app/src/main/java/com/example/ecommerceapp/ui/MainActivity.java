package com.example.ecommerceapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.adapter.DashboardAdapter;
import com.example.ecommerceapp.database.DBManager;
import com.example.ecommerceapp.models.LstProduct;
import com.example.ecommerceapp.models.TopBar;
import com.example.ecommerceapp.onclick.DashboardOnClick;
import com.example.ecommerceapp.viewmodel.MainViewModel;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements DashboardOnClick {

    private DashboardAdapter dashboardListAdapter;
    private RecyclerView item_list;
    private ArrayList<LstProduct> listProducts;
    private ArrayList<TopBar> topBar;
    MainViewModel mainViewModel;
    DBManager dbManager;
    Observer<? super ArrayList<LstProduct>> productObserver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            dbManager = new DBManager(this);
            dbManager.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
        insertData();

        mainViewModel = new MainViewModel(dbManager);
        item_list = findViewById(R.id.item_list);
        listProducts = new ArrayList<>();
        topBar = new ArrayList<>();
        viewData();

        initrecyclerview();

        mainViewModel.fetchProductDetails();
        initialiseObservers();
    }

    private void initialiseObservers() {
        listProducts = mainViewModel.productDetailsList;
        dashboardListAdapter.UpdateDashboardAdapter(listProducts);
    }

    private void insertData() {
        dbManager.insertProductData(new LstProduct("1", "HELMET", 900, 1, 950, 1, "https://www.advantagetvs.com/Pgm_Prod_Img/Apache.png"));
        dbManager.insertProductData(new LstProduct("2", "CLEANER", 250, 1, 300, 1, "https://www.advantagetvs.com/Pgm_Prod_Img/P6300460.png"));
        dbManager.insertProductData(new LstProduct("3", "CLEANERPRO", 350, 1, 400, 1, "https://www.advantagetvs.com/Pgm_Prod_Img/P6300460.png"));
    }

    private void viewData() {
        topBar.add(new TopBar(1, "Image1", "https://www.advantagetvs.com/Pgm_Prod_Img/top1.png", 1));
        topBar.add(new TopBar(2, "Image2", "https://www.advantagetvs.com/Pgm_Prod_Img/top2.png", 2));
        topBar.add(new TopBar(3, "Image3", "https://www.advantagetvs.com/Pgm_Prod_Img/top3.png", 3));
        topBar.add(new TopBar(4, "Image4", "https://www.advantagetvs.com/Pgm_Prod_Img/top4.png", 4));
        topBar.add(new TopBar(5, "Image5", "https://www.advantagetvs.com/Pgm_Prod_Img/top5.png", 5));
        topBar.add(new TopBar(6, "Image6", "https://www.advantagetvs.com/Pgm_Prod_Img/top7.png", 6));
    }

    private final GridLayoutManager.SpanSizeLookup onSpanSizeLookup = new GridLayoutManager.SpanSizeLookup() {
        @Override
        public int getSpanSize(int position) {
            return dashboardListAdapter.getItemViewType(position) == 5 ? 1 : 2;
        }
    };

    private void initrecyclerview() {
        dashboardListAdapter = new DashboardAdapter(listProducts, topBar, this);
        GridLayoutManager
                mGridLayoutManager = new GridLayoutManager(this, 2);
        mGridLayoutManager.setSpanSizeLookup(onSpanSizeLookup);
        item_list.setHasFixedSize(true);
        item_list.setItemViewCacheSize(20);
        item_list.setLayoutManager(mGridLayoutManager);
        item_list.setItemAnimator(new DefaultItemAnimator());
        item_list.setAdapter(dashboardListAdapter);
    }


    @Override
    public void clickProduct(LstProduct productData) {
        Log.i("productName", productData.getProductName());
        Intent mIntent =new Intent(this,ProductDetailActivity.class);
        mIntent.putExtra("productData",productData);
        startActivity(mIntent);
    }

}