package com.example.guest.gobal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HotelsShowActivity extends AppCompatActivity {
    public static final String TAG = HotelsShowActivity.class.getSimpleName();

    @Bind(R.id.locationTextView) TextView mLocationTextView;
    @Bind(R.id.listView) ListView mListView;

    private String[] hotels = new String[] {"Hilton", "Bellagio", "Wynn", "Caesars"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels_show);
        ButterKnife.bind(this);

        mListView = (ListView) findViewById(R.id.listView);
        mLocationTextView = (TextView) findViewById(R.id.locationTextView);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, hotels);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String hotel = ((TextView)view).getText().toString();
                Toast.makeText(HotelsShowActivity.this, hotel, Toast.LENGTH_LONG).show();
            }
        });


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
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    Log.v(TAG, jsonData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
