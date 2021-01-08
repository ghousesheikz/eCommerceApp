package com.example.ecommerceapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ecommerceapp.models.CartPojo;
import com.example.ecommerceapp.models.LstProduct;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private SQLiteDatabase mDatabase;
    private DatabaseHelper mSQLHelper;
    private Context mContext;

    public DBManager(Context context) {
        mContext = context;
        mSQLHelper = DatabaseHelper.getInstance(mContext);
    }

    public void open() throws SQLException {
        mDatabase = mSQLHelper.getWritableDatabase();
    }

    public void close() {
        mDatabase.close();
    }

    public long insertProductData(LstProduct product) {
        long count = 0;
        try {
            ContentValues contentValue = new ContentValues();
            contentValue.put(DBUtils.productName, product.getProductName());
            contentValue.put(DBUtils.productCode, product.getProductCode());
            contentValue.put(DBUtils.mrp, product.getMrp());
            contentValue.put(DBUtils.sellingPrice, product.getSellingPrice());
            contentValue.put(DBUtils.categoryID, product.getCategoryID());
            contentValue.put(DBUtils.imageLink, product.getImageLink());
            count = mDatabase.insert(DBUtils.PRODUCT_TABLE, null, contentValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public long insertCartData(CartPojo cartPojo) {
        long count = 0;
        try {
            ContentValues contentValue = new ContentValues();
            contentValue.put(DBUtils.productName, cartPojo.getProductName());
            contentValue.put(DBUtils.productCode, cartPojo.getProductCode());
            contentValue.put(DBUtils.mrp, cartPojo.getMrp());
            contentValue.put(DBUtils.sellingPrice, cartPojo.getSellingPrice());
            contentValue.put(DBUtils.categoryID, cartPojo.getCategoryID());
            contentValue.put(DBUtils.imageLink, cartPojo.getImageLink());
            contentValue.put(DBUtils.noOfItem, cartPojo.getNoOfItem());
            contentValue.put(DBUtils.totalPrice, cartPojo.getTotalPrice());
            count = mDatabase.insert(DBUtils.CART_TABLE, null, contentValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public ArrayList<LstProduct> getProductData() {
        MutableLiveData<ArrayList<LstProduct>> ProductList = new MutableLiveData<ArrayList<LstProduct>>();
        ArrayList<LstProduct> mDataList = new ArrayList<>();
        LstProduct data;
        mDatabase = mSQLHelper.getReadableDatabase();

        String selectQuery = "SELECT * FROM "
                + DBUtils.PRODUCT_TABLE;

        Cursor cursor = mDatabase.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                data = new LstProduct();
                data.setProductName(cursor.getString(cursor.getColumnIndex(DBUtils.productName)));
                data.setProductCode(cursor.getString(cursor.getColumnIndex(DBUtils.productCode)));
                data.setMrp(cursor.getInt(cursor.getColumnIndex(DBUtils.mrp)));
                data.setSellingPrice(cursor.getInt(cursor.getColumnIndex(DBUtils.sellingPrice)));
                data.setCategoryID(cursor.getInt(cursor.getColumnIndex(DBUtils.categoryID)));
                data.setImageLink(cursor.getString(cursor.getColumnIndex(DBUtils.imageLink)));
                mDataList.add(data);

            } while (cursor.moveToNext());
        }
        cursor.close();
        ProductList.setValue(mDataList);
        return  mDataList;
    }

    public ArrayList<CartPojo> getCartData() {
        ArrayList<CartPojo> CartList = new ArrayList<>();
        CartPojo data;
        mDatabase = mSQLHelper.getReadableDatabase();

        String selectQuery = "SELECT * FROM "
                + DBUtils.CART_TABLE;

        Cursor cursor = mDatabase.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                data = new CartPojo();
                data.setProductName(cursor.getString(cursor.getColumnIndex(DBUtils.productName)));
                data.setProductCode(cursor.getString(cursor.getColumnIndex(DBUtils.productCode)));
                data.setMrp(cursor.getInt(cursor.getColumnIndex(DBUtils.mrp)));
                data.setSellingPrice(cursor.getInt(cursor.getColumnIndex(DBUtils.sellingPrice)));
                data.setCategoryID(cursor.getInt(cursor.getColumnIndex(DBUtils.categoryID)));
                data.setImageLink(cursor.getString(cursor.getColumnIndex(DBUtils.imageLink)));
                data.setNoOfItem(cursor.getInt(cursor.getColumnIndex(DBUtils.noOfItem)));
                data.setTotalPrice(cursor.getInt(cursor.getColumnIndex(DBUtils.totalPrice)));
                CartList.add(data);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return CartList;
    }
}
