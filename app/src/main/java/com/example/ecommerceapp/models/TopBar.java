
package com.example.ecommerceapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopBar {
    public TopBar(Integer id, String name, String imageLink, Integer seriesID) {
        this.id = id;
        this.name = name;
        this.imageLink = imageLink;
        this.seriesID = seriesID;
    }

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("imageLink")
    @Expose
    private String imageLink;
    @SerializedName("seriesID")
    @Expose
    private Integer seriesID;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public Integer getSeriesID() {
        return seriesID;
    }

    public void setSeriesID(Integer seriesID) {
        this.seriesID = seriesID;
    }

}
