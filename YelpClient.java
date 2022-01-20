package com.example.myyelp;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class YelpClient {



    public YelpAPI build(){

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @NonNull
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {
                        return chain.proceed(chain.request().newBuilder()
                                .addHeader("Authorization","Bearer iTzvStpfo-x4ZItqwF05BqEormN9GKs_4Ut8G5IcUPWIP59C-M_ScRIch24XDBiGyyH8SYhWM5VT5SH7JeN6oRPrw0wiOK8hoL-pLIScYTZj6Pm1Eo4mbvznI-CqYXYx")
                                .build());
                    }

                }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.yelp.com/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();

        return retrofit.create(YelpAPI.class);

    }



}
