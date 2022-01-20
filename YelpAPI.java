package com.example.myyelp;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface YelpAPI {

    @GET("businesses/search")
    Call<Restaurants> searchRestaurants(
                @Query("term") String term,
                @Query("location") String location

            );


}
