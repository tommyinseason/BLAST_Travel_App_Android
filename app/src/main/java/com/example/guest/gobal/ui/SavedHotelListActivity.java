package com.example.guest.gobal.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.guest.gobal.R;
import com.example.guest.gobal.adapters.FirebaseHotelViewHolder;
import com.example.guest.gobal.models.Hotel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

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

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Hotel, FirebaseHotelViewHolder>
                (Hotel.class, R.layout.hotel_list_item, FirebaseHotelViewHolder.class, mHotelReference) {

            @Override
            protected void populateViewHolder(FirebaseHotelViewHolder viewHolder, Hotel model, int position) {
                viewHolder.bindHotel(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
