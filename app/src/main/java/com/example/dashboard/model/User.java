package com.example.dashboard.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("_id")
    public String _id;

    @SerializedName("email")
    public String email;

    @SerializedName("name")
    public String name;

    @SerializedName("userType")
    public String userType;

    @SerializedName("storeName")
    public String storeName;

    @SerializedName("phone")
    public String phone;

    @SerializedName("address")
    public Address address;

    public class Address {
        @SerializedName("street")
        public String street;

        @SerializedName("pincode")
        public String pincode;

        @SerializedName("city")
        public String city;

        @SerializedName("state")
        public String state;

        Address(
                String street,
                String pincode,
                String city,
                String state) {
            this.street = street;
            this.pincode = pincode;
            this.city = city;
            this.state = state;
        }
    }

    public User(String email,
                String name,
                String userType,
                String storeName,
                String phone,
                String street,
                String pincode,
                String city,
                String state) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.storeName = storeName;
        this.userType = userType;
        this.address = new Address(street, pincode, city, state);
    }
}
