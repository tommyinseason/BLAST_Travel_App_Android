package com.example.guest.gobal.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.guest.gobal.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HotelsActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.buttonSearch) Button mButtonSearch;
    @Bind(R.id.locationEditText) EditText mLocationEditText;
    @Bind(R.id.savedHotelsButton) Button mSavedHotelsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);
        ButterKnife.bind(this);

        Typeface caviarDreamsFont = Typeface.createFromAsset(getAssets(), "fonts/CaviarDreams.ttf");
        mLocationEditText.setTypeface(caviarDreamsFont);
        mButtonSearch.setTypeface(caviarDreamsFont);
        mSavedHotelsButton.setTypeface(caviarDreamsFont);
        mButtonSearch.setOnClickListener(this);

        mSavedHotelsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mButtonSearch) {
            Intent intent = new Intent(HotelsActivity.this, HotelsListActivity.class);
            startActivity(intent);
        }

        if (v == mSavedHotelsButton) {
            Intent intent = new Intent(HotelsActivity.this, SavedHotelListActivity.class);
            startActivity(intent);
        }
    }
}