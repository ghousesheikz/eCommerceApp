
package com.example.ecommerceapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LstProduct implements Serializable {

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


    @SerializedName("sno")
    @Expose
    private Integer sno;

    @SerializedName("disc_amt")
    @Expose
    private String discount;

    @SerializedName("disc_per")
    @Expose
    private String discount_percentage;

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDiscount_percentage() {
        return discount_percentage;
    }

    public void setDiscount_percentage(String discount_percentage) {
        this.discount_percentage = discount_percentage;
    }

    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
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

    public LstProduct() {

    }

    public LstProduct(String productCode, String productName, Integer mrp, Integer ndp, Integer sellingPrice, Integer categoryID, String imageLink) {
        this.productCode = productCode;
        this.productName = productName;
        this.mrp = mrp;
        this.ndp = ndp;
        this.sellingPrice = sellingPrice;
        this.categoryID = categoryID;
        this.imageLink = imageLink;
    }
}
