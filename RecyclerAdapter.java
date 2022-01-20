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

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.viewHolder> {
    ArrayList<Restaurants> data;
    Context currentContext;
    public OnRestaurantListener mOnRestaurantListener;



    public RecyclerAdapter(Context context, ArrayList<Restaurants> data, OnRestaurantListener onRestaurantListener){
        this.data = data;
        this.mOnRestaurantListener = onRestaurantListener;
        currentContext = context;
    }




    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View cardView = inflater.inflate(R.layout.card_item, parent, false);
        viewHolder viewHolder = new viewHolder(cardView, mOnRestaurantListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        TextView tv_data = holder.tv_Data;
        tv_data.setText(data.get(position).name);
        TextView phone = holder.phone;
        phone.setText(data.get(position).phone);
        RatingBar ratingBar = holder.ratingBar;
        ratingBar.setRating(Float.parseFloat((data.get(position).rating)));
        TextView price = holder.price;
        price.setText(data.get(position).price);
        TextView address = holder.address;
        address.setText(data.get(position).location.address1);
        TextView city = holder.city;
        city.setText(data.get(position).location.city);
        TextView province = holder.province;
        province.setText(data.get(position).location.state);
        ImageView imageView = holder.imageView;
        Glide.with(holder.itemView.getContext()).load(data.get(position).image_url).apply(RequestOptions.centerCropTransform()).into(imageView);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tv_Data;
        CardView cardView;
        TextView phone;
        RatingBar ratingBar;
        TextView price;
        TextView address;
        TextView city;
        TextView province;
        ImageView imageView;

        OnRestaurantListener onRestaurantListener;

        public viewHolder(@NonNull View itemView, OnRestaurantListener onRestaurantListener) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_item);
            tv_Data = itemView.findViewById(R.id.name);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            phone = itemView.findViewById(R.id.phone);
            price = itemView.findViewById(R.id.price);
            address = itemView.findViewById(R.id.address);
            city = itemView.findViewById(R.id.city);
            province = itemView.findViewById(R.id.province);
            imageView = itemView.findViewById(R.id.imageView);

            this.onRestaurantListener = onRestaurantListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //view.getContext();
            onRestaurantListener.onRestaurantClick(getAdapterPosition());
        }
    }

    public interface OnRestaurantListener{
        void onRestaurantClick(int position);
    }


}
