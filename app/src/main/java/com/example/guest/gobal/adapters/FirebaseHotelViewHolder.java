package com.example.guest.gobal.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.gobal.R;
import com.example.guest.gobal.models.Hotel;
import com.example.guest.gobal.ui.Constants;
import com.example.guest.gobal.ui.HotelDetailActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by Guest on 7/14/16.
 */
public class FirebaseHotelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;
    public ImageView mHotelImageView;


    View mView;
    Context mContext;

    public FirebaseHotelViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindHotel(Hotel hotel) {
        mHotelImageView = (ImageView) mView.findViewById(R.id.hotelImageView);
        ImageView hotelImageView = (ImageView) mView.findViewById(R.id.hotelImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.hotelNameTextView);
        TextView ratingTextView = (TextView) mView.findViewById(R.id.ratingTextView);

        Picasso.with(mContext)
            .load(hotel.getImageUrl())
            .resize(MAX_WIDTH, MAX_HEIGHT)
            .centerCrop()
            .into(mHotelImageView);

        nameTextView.setText(hotel.getName());
        ratingTextView.setText("Rating: " + hotel.getRating() + "/5");
}
    @Override
    public void onClick(View view) {
        final ArrayList<Hotel> hotels = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_HOTELS);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    hotels.add(snapshot.getValue(Hotel.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, HotelDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("hotels", Parcels.wrap(hotels));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}