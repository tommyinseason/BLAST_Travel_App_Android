package com.example.guest.gobal.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.guest.gobal.R;
import com.example.guest.gobal.models.Hotel;
import com.example.guest.gobal.services.YelpService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HotelsShowActivity extends AppCompatActivity {
    public static final String TAG = HotelsShowActivity.class.getSimpleName();

    @Bind(R.id.locationTextView) TextView mLocationTextView;
    @Bind(R.id.listView) ListView mListView;

    public ArrayList<Hotel> mHotels = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels_show);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

        mLocationTextView.setText("Here are all the HOTELS near: " + location);

        getHotels(location);
    }

    private void getHotels(String location) {
        final YelpService yelpService = new YelpService();

        yelpService.findHotels(location, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mHotels = yelpService.processResults(response);

                    HotelsShowActivity.this.runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            String[] hotelNames = new String[mHotels.size()];
                            for (int i = 0; i < hotelNames.length; i++) {
                                hotelNames[i] = mHotels.get(i).getName();
                            }
                            ArrayAdapter adapter = new ArrayAdapter(HotelsShowActivity.this, android.R.layout.simple_list_item_1, hotelNames);
                            mListView.setAdapter(adapter);

                            for (Hotel hotel : mHotels) {
                                Log.d(TAG, "Name: " + hotel.getName());
                                Log.d(TAG, "Phone: " + hotel.getPhone());
                                Log.d(TAG, "Website: " + hotel.getWebsite());
                                Log.d(TAG, "Image url: " + hotel.getImageUrl());
                                Log.d(TAG, "Rating: " + Double.toString(hotel.getRating()));
                                Log.d(TAG, "Address: " + android.text.TextUtils.join(", ", hotel.getAddress()));
                                Log.d(TAG, "Categories: " + hotel.getCategories().toString());
                            }
                        }
                    });
                }
        });
    }
}
