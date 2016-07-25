package com.example.guest.gobal.adapters.slidelist;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Guest on 7/25/16.
 */
@SuppressLint("NewApi")
public class FB_Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater
                .inflate(R.layout.fb_fragment, container, false);

        return rootView;
    }

}
