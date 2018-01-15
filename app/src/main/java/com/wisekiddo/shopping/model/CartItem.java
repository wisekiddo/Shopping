package com.wisekiddo.shopping.model;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Random;

public class CartItem {

    private Integer albumId;
    private Integer id;
    private String title;
    private String itemId;

    private String name;
    private String url;
    private String thumbnailUrl;
    private Double price;
    private Integer quantity;
    private Double discount;


    public String getItemId() {return itemId;}
    public void setItemId(String itemId) {this.itemId = itemId;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public Integer getAlbumId() {
        return albumId;
    }
    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {this.id = id;}

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {this.price = price;}

    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getDiscount() {
        return discount;
    }
    public void setDiscount(Double discount) {this.discount = discount;}

}