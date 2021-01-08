package com.example.ecommerceapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartPojo {
    @SerializedName("productCode")
    @Expose
    private String productCode;
    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("mrp")
    @Expose
    private Integer mrp;
    @SerializedName("ndp")
    @Expose
    private Integer ndp;
    @SerializedName("sellingPrice")
    @Expose
    private Integer sellingPrice;
    @SerializedName("categoryID")
    @Expose
    private Integer categoryID;
    @SerializedName("imageLink")
    @Expose
    private String imageLink;
    @SerializedName("noOfItem")
    @Expose
    private Integer noOfItem;
    @SerializedName("totalPrice")
    @Expose
    private Integer totalPrice;


    public Integer getNoOfItem() {
        return noOfItem;
    }

    public void setNoOfItem(Integer noOfItem) {
        this.noOfItem = noOfItem;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getMrp() {
        return mrp;
    }

    public void setMrp(Integer mrp) {
        this.mrp = mrp;
    }

    public Integer getNdp() {
        return ndp;
    }

    public void setNdp(Integer ndp) {
        this.ndp = ndp;
    }

    public Integer getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Integer sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public CartPojo() {

    }

    public CartPojo(String productCode, String productName, Integer mrp, Integer ndp, Integer sellingPrice, Integer categoryID, String imageLink, Integer noOfItem, Integer totalPrice) {
        this.productCode = productCode;
        this.productName = productName;
        this.mrp = mrp;
        this.ndp = ndp;
        this.sellingPrice = sellingPrice;
        this.categoryID = categoryID;
        this.imageLink = imageLink;
        this.noOfItem = noOfItem;
        this.totalPrice = totalPrice;
    }
}
