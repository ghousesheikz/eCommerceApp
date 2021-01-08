package com.example.ecommerceapp.ui;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.devs.readmoreoption.ReadMoreOption;
import com.example.ecommerceapp.R;
import com.example.ecommerceapp.adapter.DefaultImageSliderAdapter;
import com.example.ecommerceapp.database.DBManager;
import com.example.ecommerceapp.dialog.DialogNFragment;
import com.example.ecommerceapp.models.CartPojo;
import com.example.ecommerceapp.models.LstProduct;
import com.example.ecommerceapp.utils.MyMenuItemStuffListener;
import com.example.ecommerceapp.viewmodel.MainViewModel;

import java.util.ArrayList;

import static com.example.ecommerceapp.utils.Constants.ISLOGIN;
import static com.example.ecommerceapp.utils.Constants.MOBILENUMBER;
import static com.example.ecommerceapp.utils.Constants.PASSWORD;

public class ProductDetailActivity extends BaseActivity implements View.OnClickListener {
    LstProduct mProductData;
    private ArrayList<String> images;
    ViewPager product_image;
    private TextView discount;

    private TextView product_name;
    private TextView product_price;
    private TextView discount_price;
    private TextView discount_percentage;

    private TextView product_description;
    private TextView save;

    private Button addcart_btn;
    DBManager dbManager;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        setToolbar("ProductDetails");
        try {
            dbManager = new DBManager(this);
            dbManager.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mainViewModel = new MainViewModel(dbManager);

        if (getIntent().getSerializableExtra("productData") != null) {
            mProductData = (LstProduct) getIntent().getSerializableExtra("productData");
        }
        images = new ArrayList<>();
        images.add(mProductData.getImageLink());
        images.add(mProductData.getImageLink());
        images.add("https://bd.gaadicdn.com/processedimages/tvs/tvs-sport/source/m_sport_11539846448.jpg");
        initViews();

    }


    private void initViews() {
        discount = findViewById(R.id.discount);
        product_name = findViewById(R.id.product_name);
        product_price = findViewById(R.id.product_price);
        discount_price = findViewById(R.id.discount_price);
        discount_percentage = findViewById(R.id.discount_percentage);
        product_description = findViewById(R.id.product_description);
        save = findViewById(R.id.save);
        addcart_btn = findViewById(R.id.addcart_btn);
        addcart_btn.setOnClickListener(this);
        product_image = findViewById(R.id.image_1);
        DefaultImageSliderAdapter defaultImageSliderAdapter = new DefaultImageSliderAdapter(this, images);
        product_image.setAdapter(defaultImageSliderAdapter);

        discount.setText(String.format("DISCOUNT %%  %s%% (%s)",
                "10", "50"));
        product_name.setText(String.format("%s (%s)",
                mProductData.getProductName(), mProductData.getProductCode()));
        product_price.setText(String.format("₹ %s/-",
                String.valueOf(mProductData.getSellingPrice())));
        discount_price.setText(String.format("₹ %s/-",
                String.valueOf(mProductData.getMrp())));
        discount_percentage.setText(String.format("%s%%",
                "10"));

        save.setText(String.format(" Save ₹ %s/-",
                "50"));
        ReadMoreOption readMoreOption = new ReadMoreOption.Builder(this)
                .textLength(150)
                .moreLabel("...More")
                .lessLabel("...Less")
                .moreLabelColor(getResources().getColor(R.color.color_one))
                .lessLabelColor(getResources().getColor(R.color.gray))
                .labelUnderLine(true)
                .build();

        readMoreOption.addReadMoreTo(product_description, getResources().getString(R.string.product_desc));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.addcart_btn) {
            mainViewModel.insertCartData(new CartPojo(mProductData.getProductCode(), mProductData.getProductName(), mProductData.getMrp(), mProductData.getMrp(), mProductData.getSellingPrice(), mProductData.getCategoryID(), mProductData.getImageLink(), 1, mProductData.getSellingPrice()));
            DialogNFragment dialog = new DialogNFragment();
            dialog.disableCancel(true);
            dialog.ClickHere(new DialogNFragment.Click() {
                @Override
                public void okClick(Dialog dialogFragment) {
                    dialog.dismiss();
                    Intent mIntent = new Intent(ProductDetailActivity.this, CartActivity.class);
                    startActivity(mIntent);
                    finish();
                }

                @Override
                public void cancelClick(Dialog dialogFragment) {
                    dialog.dismiss();
                }
            });
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            Bundle b = new Bundle();
            b.putString(DialogNFragment.TITLE, "Added to Cart!");
            b.putString(DialogNFragment.MESSAGE, "You Can select quantity in the cart.");
            b.putInt(DialogNFragment.TYPE, DialogNFragment.SUCCESS_TYPE);
            dialog.setArguments(b);
            dialog.setCancelable(false);
            dialog.show(fragmentTransaction, DialogNFragment.TAG);
        }
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
            Intent mIntent = new Intent(ProductDetailActivity.this, LoginActivity.class);
            startActivity(mIntent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}