package com.example.dashboard.model;

import com.google.gson.annotations.SerializedName;

public class ProductDescription {
    @SerializedName("type")
    public String type;

    @SerializedName("subtype")
    public String subtype;

    @SerializedName("text")
    public String text;

    public ProductDescription(String type, String subtype, String text) {
        this.type = type;
        this.subtype = subtype;
        this.text = text;
    }
}
