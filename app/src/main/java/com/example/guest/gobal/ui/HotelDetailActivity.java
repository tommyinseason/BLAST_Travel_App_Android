package com.example.guest.gobal.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.guest.gobal.R;
import com.example.guest.gobal.adapters.HotelPagerAdapter;
import com.example.guest.gobal.models.Hotel;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HotelDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private HotelPagerAdapter adapterViewPager;
    ArrayList<Hotel> mHotels = new ArrayList<>();
    @Bind(R.id.pagerHeader) PagerTabStrip mTabStrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_detail);
        ButterKnife.bind(this);

        Typeface caviarDreamsFont = Typeface.createFromAsset(getAssets(), "fonts/CaviarDreams.ttf");
        mTabStrip.setTypeface(caviarDreamsFont);

        mHotels = Parcels.unwrap(getIntent().getParcelableExtra("hotels"));
        Log.d("mHotels", mHotels.size() + "?");
        int startingPosition = Integer.parseInt(getIntent().getStringExtra("position"));

        adapterViewPager = new HotelPagerAdapter(getSupportFragmentManager(), mHotels);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}