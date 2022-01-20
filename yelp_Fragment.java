package com.example.myyelp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.JsonArray;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class yelp_Fragment extends Fragment implements RecyclerAdapter.OnRestaurantListener {
    Context context;
    View view;
    static EditText editText;
    String input = "italian";
    Spinner spinner;
    static ArrayList<Restaurants> restos;
    ImageView imageView;
    static int pos;
    String DB_NAME = "YELP_DATABASE";
    int DB_VERSION = 1;
    Yelp_DB yelp_db =null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Context context;

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        view = inflater.inflate(R.layout.fragment_yelp_, container, false);
        imageView = view.findViewById(R.id.imageView2);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText("");
            }
        });
        spinner = view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        editText = (EditText) view.findViewById(R.id.search1);

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if ((keyEvent != null && (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (i == EditorInfo.IME_ACTION_DONE)) {
                    input = editText.getText().toString();
                    InputMethodManager mgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    mgr.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                    yelpApiCall(input);
                }
                return false;
            }
        });



        yelpApiCall(input);



        return view;
    }



    public void yelpApiCall (String searchText){

        YelpAPI api = new YelpClient().build();
        Call<Restaurants> call = api.searchRestaurants(searchText, "montreal");
        Callback<Restaurants> callback = new Callback<Restaurants>() {

            @Override
            public void onResponse(Call<Restaurants> call, Response<Restaurants> response) {
                Restaurants restaurants = response.body();

                if (restaurants != null) {
                    RecyclerView recyclerView =  view.findViewById(R.id.rvRestaurants);
                    recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                    RecyclerAdapter recyclerAdapter = new RecyclerAdapter(context, restaurants.businesses,yelp_Fragment.this::onRestaurantClick);
                    recyclerView.setAdapter(recyclerAdapter);

                    restos = restaurants.businesses;


                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String choice = adapterView.getItemAtPosition(i).toString();
                if(choice.equalsIgnoreCase("price")){
                    Collections.sort(restos, new Comparator<Restaurants>() {
                        @Override
                        public int compare(Restaurants t1, Restaurants t2) {
                            if(t1.price == null && t2.price == null){
                                return 0;
                            }
                            if(t1.price  == null){
                                return 1;
                            }
                            if(t2.price == null){
                                return -1;
                            }
                            return t1.price.compareTo(t2.price);
                        }
                    });
                    recyclerAdapter.notifyDataSetChanged();
                }
                if(choice.equalsIgnoreCase("rating")){
                    Collections.sort(restos, new Comparator<Restaurants>() {
                        @Override
                        public int compare(Restaurants t1, Restaurants t2) {

                            return Integer.parseInt(String.valueOf(String.valueOf(t2.rating).compareTo(String.valueOf(t1.rating))));
                        }
                    });
                    recyclerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

                }
            }

            @Override
            public void onFailure(Call<Restaurants> call, Throwable t) {
                Log.e("MainActivity", t.toString());
                //add toast message and run api again
            }
        };
        call.enqueue(callback);



   }


    @Override
    public void onRestaurantClick(int position) {
        this.pos = position;
        Log.d("TAG", "WORKS!!"+ restos.get(position).name);
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle("Add to favorites?");
        builder.setMessage("Do you want to add this item to favorites?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String name = restos.get(pos).name;
                String price = restos.get(pos).price;
                String phone = restos.get(pos).phone;
                String street = restos.get(pos).location.address1;
                String city =  restos.get(pos).location.city;
                String state = restos.get(pos).location.state;
                String image = restos.get(pos).image_url;
                //String rating = restos.get(pos).rating;

                yelp_db = new Yelp_DB(view.getContext(), DB_NAME,null,DB_VERSION);
                yelp_db.addData(name,price,phone,street,city,state,image);


                SQLiteDatabase db = yelp_db.getReadableDatabase();
                Cursor cursor = db.query("FAVORITES",new String[]{"NAME","PRICE","PHONE"},null,null,null,null,null);
                cursor.moveToFirst();
                while(cursor.moveToNext()){
                    Log.d("TESTING_DB",cursor.getString(0) +"\n" +cursor.getString(1) +"\n"+ cursor.getString(2));
                }
                //Log.d("TESTING_DB",cursor.getString(0) +"\n" +cursor.getString(1) +"\n"+ cursor.getString(2));
                Log.d("TAG","added to favorites" + name + "\n" + price + "\n" + phone + "\n" + street + "\n" + city + "\n" + state +"\n" + image);
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Log.d("TAG","Not added to favorites");
            }
        });
        builder.create().show();
    }
}