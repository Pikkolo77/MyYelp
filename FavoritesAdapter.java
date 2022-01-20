package com.example.myyelp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.RoundedCorner;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import retrofit2.Callback;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.viewHolder> {
    private ArrayList<String> name, phoneArray, priceArray, streetArray, cityArray, stateArray, imageArray;
    private Context currentContext;

    FavoritesAdapter(Context currentContext, ArrayList name, ArrayList phoneArray, ArrayList priceArray, ArrayList streetArray,
    ArrayList cityArray, ArrayList stateArray, ArrayList imageArray ){
        this.currentContext = currentContext;
        this.name = name;
        this.phoneArray = phoneArray;
        this.priceArray = priceArray;
        this.streetArray = streetArray;
        //this.ratingArray = ratingArray;
        this.cityArray = cityArray;
        this.stateArray = stateArray;
        this.imageArray = imageArray;
        //this.ratingArray = ratingArray;
    }






    @NonNull
    @Override
    public FavoritesAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
////        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
////        View cardView = inflater.inflate(R.layout.card_item, parent, false);
////        viewHolder viewHolder = new viewHolder(cardView);
//        View view = LayoutInflater.from(currentContext).inflate(R.layout.card_item,parent,false);
//        return new viewHolder(view);
       LayoutInflater inflater = LayoutInflater.from(currentContext);
       View view = inflater.inflate(R.layout.card_item,parent,false);
       return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesAdapter.viewHolder holder, int position) {

        holder.tv_Data.setText(String.valueOf(name.get(position)));
        holder.phone.setText(String.valueOf(phoneArray.get(position)));
        holder.price.setText(String.valueOf(priceArray.get(position)));
        holder.address.setText(String.valueOf(streetArray.get(position)));
        holder.city.setText(String.valueOf(cityArray.get(position)));
        holder.province.setText(String.valueOf(stateArray.get(position)));
        Glide.with(holder.itemView.getContext()).load(imageArray.get(position)).apply(RequestOptions.centerCropTransform()).into(holder.imageView);
        //holder.ratingBar.setRating(Float.parseFloat(((ratingArray.get(position)))));
//        TextView tv_data = holder.tv_Data;
//        tv_data.setText(data.get(position).name);
//        TextView phone = holder.phone;
//        phone.setText(data.get(position).phone);
////        RatingBar ratingBar = holder.ratingBar;
////        ratingBar.setRating((float) data.get(position).rating);
//        TextView price = holder.price;
//        price.setText(data.get(position).price);
////        TextView address = holder.address;
////        address.setText(data.get(position).location.address1);
////        TextView city = holder.city;
////        city.setText(data.get(position).location.city);
////        TextView province = holder.province;
////        province.setText(data.get(position).location.state);
////        ImageView imageView = holder.imageView;
////        Glide.with(holder.itemView.getContext()).load(data.get(position).image_url).apply(RequestOptions.centerCropTransform()).into(imageView);
//        yelp_db = new Yelp_DB(currentContext.getApplicationContext(),"FAVORITES",null,1);


    }

    @Override
    public int getItemCount(){
        return name.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder  {

        TextView tv_Data;
       // CardView cardView;
        TextView phone;
        //RatingBar ratingBar;
        TextView price;
        TextView address;
        TextView city;
        TextView province;
        ImageView imageView;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            //cardView = itemView.findViewById(R.id.card_item);
            tv_Data = itemView.findViewById(R.id.name);
            //ratingBar = itemView.findViewById(R.id.ratingBar);
            phone = itemView.findViewById(R.id.phone);
            price = itemView.findViewById(R.id.price);
            address = itemView.findViewById(R.id.address);
            city = itemView.findViewById(R.id.city);
            province = itemView.findViewById(R.id.province);
            imageView = itemView.findViewById(R.id.imageView);



        }

    }
}
