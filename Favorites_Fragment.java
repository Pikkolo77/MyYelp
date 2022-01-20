package com.example.myyelp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;


public class Favorites_Fragment extends Fragment {

    ArrayList<String> name, phoneArray, priceArray, streetArray, cityArray,stateArray, imageArray, ratingBar;
    RecyclerView recyclerView;
    Yelp_DB yelp_db;
    View view;
    TextView tv_Data;
    TextView phone;
    TextView price;
    ImageView imageView;
    TextView street;
    TextView city;
    TextView state;
    FavoritesAdapter favoritesAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Context context;

        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_favorites_, container, false);
        yelp_db = new Yelp_DB(this.getContext(),"YELP_DATABASE",null,1);
        name = new ArrayList<>();
        phoneArray = new ArrayList<>();
        priceArray = new ArrayList<>();
        streetArray = new ArrayList<>();
        //ratingBar = new ArrayList<>();
        cityArray = new ArrayList<>();
        stateArray = new ArrayList<>();
        imageArray = new ArrayList<>();

        recyclerView = view.findViewById(R.id.favorite);
        favoritesAdapter = new FavoritesAdapter(this.getContext(),name,phoneArray,priceArray,streetArray,cityArray,stateArray,imageArray);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(favoritesAdapter);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

//        RecyclerView recyclerView = view.findViewById(R.id.favorite);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
//        //FavoritesAdapter favoritesAdapter = new FavoritesAdapter(view.getContext(), data);
//        //recyclerView.setAdapter(favoritesAdapter);
//
////        displayFavs();
//        tv_Data = view.findViewById(R.id.name);
//        phone = view.findViewById(R.id.phone);
//        price = view.findViewById(R.id.price);
//        street = view.findViewById(R.id.address);
//        city = view.findViewById(R.id.city);
//        state = view.findViewById(R.id.province);
//        imageView = view.findViewById(R.id.imageView);
//
//        //add to arraylist
//        yelp_db = new Yelp_DB(view.getContext(),"YELP_DATABASE",null,1);
//        SQLiteDatabase db = yelp_db.getReadableDatabase();
//
//
//        Cursor cursor = db.rawQuery("SELECT * FROM " + "FAVORITES",null);
//        cursor.moveToLast();
//        String name = cursor.getString(1);
//        String price2 = cursor.getString(2);
//        String phone2 = cursor.getString(3);
//        String address = cursor.getString(4);
//        String city2 = cursor.getString(5);
//        String state2 = cursor.getString(6);
//        String image = cursor.getString(7);
//
//
//        tv_Data.setText(name);
//        price.setText(price2);
//        phone.setText(phone2);
//        street.setText(address);
//        city.setText(city2);
//        state.setText(state2);
//        Glide.with(view.getContext()).load(image).apply(RequestOptions.centerCropTransform()).into(imageView);
        displayData();
        return view;
//    }
    }
    void displayData(){
        Cursor cursor = yelp_db.readAllData();
        if (cursor.getCount() == 0){
            Toast.makeText(this.getContext(), "NO DATA",Toast.LENGTH_SHORT);
        }else{
            while(cursor.moveToNext()){
                name.add(cursor.getString(1));
                phoneArray.add(cursor.getString(2));
                priceArray.add(cursor.getString(3));
                streetArray.add(cursor.getString(4));
                cityArray.add(cursor.getString(5));
                stateArray.add(cursor.getString(6));
                imageArray.add(cursor.getString(7));
                //ratingBar.add(cursor.getString(8));

            }
        }
    }
//    public void displayFavs(){
//        data = new ArrayList<>(yelp_db.getRestos());
//        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
//        FavoritesAdapter favoritesAdapter = new FavoritesAdapter(getContext(),data);
//        recyclerView.setAdapter(favoritesAdapter);
//    }
}