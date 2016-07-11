package com.example.guest.gobal.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.guest.gobal.models.Hotel;
import com.example.guest.gobal.ui.HotelDetailFragment;

import java.util.ArrayList;


/**
 * Created by tomjones on 7/10/16.
 */
public class HotelPagerAdapter extends FragmentPagerAdapter{
    private ArrayList<Hotel> mHotel;

    public HotelPagerAdapter(FragmentManager fm, ArrayList<Hotel> restaurants) {
        super(fm);
        mHotel = hotels;
    }

    @Override
    public Fragment getItem(int position) {
        return HotelDetailFragment.newInstance(mHotel.get(position));
    }

    @Override
    public int getCount() {
        return mHotel.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mHotel.get(position).getName();
    }
}

