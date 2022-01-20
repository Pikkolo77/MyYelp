package com.example.myyelp;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.http.Url;

public class Restaurants implements Serializable {

    public String getPrice(){
        return price;
    }

    public void setPrice (String price){
        this.price = price;
    }

    @SerializedName("businesses")
    public ArrayList<Restaurants> businesses;

    //works
    @SerializedName("name")
    public String name;

    @SerializedName("price")
    public String price;


    @SerializedName("location")
    public Location location;

    public void id(
            String string) {
    }

    public void setName(String string) {
        this.name = string;
    }

    public void setPhone(String string) {
        this.phone = string;
    }

    class Location{
        @SerializedName("address1")
        public String address1;

        @SerializedName("city")
        public String city;

        @SerializedName("state")
        public String state;
    }

    @SerializedName("category")
    public Category category;

    class Category{
        @SerializedName("alias")
        public String alias;

        @SerializedName("title")
        public String title;
    }


    //works
    @SerializedName("id")
    public String id;



    //works
    @SerializedName("rating")
    public String rating;



    //works
    @SerializedName("phone")
    public String phone;




    //gives a http:// result
    @SerializedName("image_url")
    public String image_url;

    @SerializedName("alias")
    public String alias;

}
