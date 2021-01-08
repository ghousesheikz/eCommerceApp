package com.example.ecommerceapp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.adapter.DashboardAdapter;
import com.example.ecommerceapp.models.LstProduct;
import com.example.ecommerceapp.models.TopBar;
import com.example.ecommerceapp.onclick.DashboardOnClick;
import com.example.ecommerceapp.utils.MyMenuItemStuffListener;
import com.example.ecommerceapp.viewmodel.MainViewModel;

import java.util.ArrayList;

import static com.example.ecommerceapp.utils.Constants.ISLOGIN;
import static com.example.ecommerceapp.utils.Constants.MOBILENUMBER;
import static com.example.ecommerceapp.utils.Constants.PASSWORD;

public class MainActivity extends BaseActivity implements DashboardOnClick {

    private DashboardAdapter dashboardListAdapter;
    private RecyclerView item_list;
    private ArrayList<LstProduct> listProducts;
    private ArrayList<TopBar> topBar;
    MainViewModel mainViewModel;

    Observer<? super ArrayList<LstProduct>> productObserver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
        insertData();


        mainViewModel = new MainViewModel(dbManager);
        item_list = findViewById(R.id.item_list);
        listProducts = new ArrayList<>();
        topBar = new ArrayList<>();
        viewData();

        initrecyclerview();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainViewModel.fetchProductDetails();
        listProducts = mainViewModel.productDetailsList;
        dashboardListAdapter.UpdateDashboardAdapter(listProducts);
    }

    private void insertData() {
        dbManager.deleteproductData();
        dbManager.insertProductData(new LstProduct("1", "HELMET", 900, 1, 950, 1, "https://www.advantagetvs.com/Pgm_Prod_Img/Apache.png"));
        dbManager.insertProductData(new LstProduct("2", "CLEANER", 250, 1, 300, 1, "https://www.advantagetvs.com/Pgm_Prod_Img/P6300460.png"));
        dbManager.insertProductData(new LstProduct("3", "CLEANERPRO", 350, 1, 400, 1, "https://www.advantagetvs.com/Pgm_Prod_Img/P6300460.png"));
        dbManager.insertProductData(new LstProduct("1", "HELMET", 900, 1, 950, 1, "https://www.advantagetvs.com/Pgm_Prod_Img/Apache.png"));
        dbManager.insertProductData(new LstProduct("2", "CLEANER", 250, 1, 300, 1, "https://www.advantagetvs.com/Pgm_Prod_Img/P6300460.png"));
        dbManager.insertProductData(new LstProduct("3", "CLEANERPRO", 350, 1, 400, 1, "https://www.advantagetvs.com/Pgm_Prod_Img/P6300460.png"));
        dbManager.insertProductData(new LstProduct("1", "HELMET", 900, 1, 950, 1, "https://www.advantagetvs.com/Pgm_Prod_Img/Apache.png"));
        dbManager.insertProductData(new LstProduct("2", "CLEANER", 250, 1, 300, 1, "https://www.advantagetvs.com/Pgm_Prod_Img/P6300460.png"));
        dbManager.insertProductData(new LstProduct("3", "CLEANERPRO", 350, 1, 400, 1, "https://www.advantagetvs.com/Pgm_Prod_Img/P6300460.png"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem item = menu.findItem(R.id.action_cart);
        View menu_list = item.getActionView();

        new MyMenuItemStuffListener(menu_list, "Show Cart") {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity, CartActivity.class));
            }
        };


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(MOBILENUMBER, "");
            editor.putString(PASSWORD, "");
            editor.putString(ISLOGIN, "0");
            editor.apply();
            Intent mIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(mIntent);
            finish();
        }
        return super.onOptionsItemSelected(item);
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
        Intent mIntent = new Intent(this, ProductDetailActivity.class);
        mIntent.putExtra("productData", productData);
        startActivity(mIntent);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}