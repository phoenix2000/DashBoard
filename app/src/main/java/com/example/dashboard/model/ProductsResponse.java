package com.example.dashboard.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductsResponse {
    @SerializedName("error")
    public Boolean error;

    @SerializedName("message")
    public String message;

    @SerializedName("products")
    public List<Product> productList;
}
