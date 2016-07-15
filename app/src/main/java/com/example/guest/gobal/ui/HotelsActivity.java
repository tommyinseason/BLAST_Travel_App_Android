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
    private DatabaseReference mSearchedLocationReference;

    private ValueEventListener mSearchedLocationReferenceListener;

    @Bind(R.id.buttonSearch) Button mButtonSearch;
    @Bind(R.id.locationEditText) EditText mLocationEditText;
//    @Bind(R.id.savedHotelsButton) EditText mSavedHotelsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mSearchedLocationReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_LOCATION_SEARCHED_LOCATION);

        mSearchedLocationReferenceListener = mSearchedLocationReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot locationSnapshot : dataSnapshot.getChildren()) {
                    String location = locationSnapshot.getValue().toString();
                    Log.d("Locations updated", "location: " + location);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);
        ButterKnife.bind(this);

        Typeface caviarDreamsFont = Typeface.createFromAsset(getAssets(), "fonts/CaviarDreams.ttf");
        mLocationEditText.setTypeface(caviarDreamsFont);
        mButtonSearch.setTypeface(caviarDreamsFont);
//        mSavedHotelsButton.setTypeface(caviarDreamsFont);
        mButtonSearch.setOnClickListener(this);

//        mSavedHotelsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mButtonSearch) {
            String location = mLocationEditText.getText().toString();

            saveLocationToFirebase(location);

            Intent intent = new Intent(HotelsActivity.this, HotelsListActivity.class);
            intent.putExtra("location", location);
            startActivity(intent);
        }

//        if (v == mSavedHotelsButton) {
//            Intent intent = new Intent(HotelsActivity.this, SavedHotelListActivity.class);
//            startActivity(intent);
//        }
    }

    public void saveLocationToFirebase(String location) {
        mSearchedLocationReference.push().setValue(location);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSearchedLocationReference.removeEventListener(mSearchedLocationReferenceListener);
    }
}