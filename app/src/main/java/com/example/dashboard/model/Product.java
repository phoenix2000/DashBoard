package com.example.dashboard.model;

import com.google.gson.annotations.SerializedName;

import retrofit2.http.Headers;

public class Product {
    @SerializedName("error")
    public Boolean error;

    @SerializedName("message")
    public String message;

    @SerializedName("name")
    public String name;

    @SerializedName("price")
    public Float price;

    @SerializedName("quantity")
    public Float quantity;

    @SerializedName("unit")
    public String unit;

    @SerializedName("sellerType")
    public String sellerType;

    @SerializedName("description")
    public ProductDescription description;

    @SerializedName("seller")
    public User seller;

    public Product(String name, Float price, Float quantity, String unit, String sellerType, ProductDescription description, User seller) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.unit = unit;
        this.sellerType = sellerType;
        this.description = description;
        this.seller = seller;
    }

    public String toString() {
        String str = "error: " + error + ", mess: " + message + ", name: " + name;
        if(description != null) {
            str += ", type: " + description.text;
        }
        if(seller != null) {
            str += ", sellerName: " + seller.name;
        }
        return str;
    }
}


