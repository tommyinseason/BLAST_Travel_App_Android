package com.example.guest.gobal.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.FirebaseDatabase;

public class SavedHotelListActivity extends AppCompatActivity {
    private DatabaseReference mHotelReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_hotels);
        ButterKnife.bind(this);

        mHotelReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_HOTELS);
        setUpFirebaseAdapter();
    }

    private
}
