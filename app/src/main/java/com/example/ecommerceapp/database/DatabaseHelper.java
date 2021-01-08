package com.example.ecommerceapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper sInstance;

    private static final String DATABASE_NAME = "ECOMMERCE";
    private static final String DATABASE_TABLE = "table_name";
    private static final int DATABASE_VERSION = 1;

    public static final String PRODUCTS_TABLE = "CREATE TABLE IF NOT EXISTS " + DBUtils.PRODUCT_TABLE +
            "(" + DBUtils.COLUMN_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DBUtils.productName + " TEXT, " + DBUtils.productCode + " TEXT, " + DBUtils.mrp + " INTEGER,"
            + DBUtils.sellingPrice + " INTEGER, " + DBUtils.categoryID + " INTEGER," + DBUtils.imageLink + " TEXT); ";

    public static final String CART_TALE = "CREATE TABLE IF NOT EXISTS " + DBUtils.CART_TABLE +
            "(" + DBUtils.COLUMN_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DBUtils.productName + " TEXT, " + DBUtils.productCode + " TEXT, " + DBUtils.mrp + " INTEGER,"
            + DBUtils.sellingPrice + " INTEGER, " + DBUtils.categoryID + " INTEGER," + DBUtils.imageLink + " TEXT, "
            + DBUtils.noOfItem + " INTEGER, " + DBUtils.totalPrice + " INTEGER); ";

    public static final String USER_TABLE = "CREATE TABLE IF NOT EXISTS " + DBUtils.USER_TABLE +
            "(" + DBUtils.COLUMN_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DBUtils.MOBILENUMBER + " TEXT, " + DBUtils.PASSWORD + " TEXT, " + DBUtils.ISLOGIN + " TEXT); ";


    public static synchronized DatabaseHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PRODUCTS_TABLE);
        db.execSQL(CART_TALE);
        db.execSQL(USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PRODUCTS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CART_TALE);
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        onCreate(db);
    }
}
