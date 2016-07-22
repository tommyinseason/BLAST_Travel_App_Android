package com.example.guest.gobal.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.gobal.R;
import com.example.guest.gobal.models.Hotel;
import com.example.guest.gobal.util.ItemTouchViewHolder;
import com.squareup.picasso.Picasso;

/**
 * Created by Guest on 7/14/16.
 */
public class FirebaseHotelViewHolder extends RecyclerView.ViewHolder implements ItemTouchViewHolder {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;


    View mView;
    Context mContext;
    public ImageView mHotelImageView;

    public FirebaseHotelViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindHotel(Hotel hotel) {
        mHotelImageView = (ImageView) mView.findViewById(R.id.hotelImageView);
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
    public void onItemSelected() {
        itemView.animate()
                .alpha(0.7f)
                .scaleX(0.9f)
                .scaleY(0.9f)
                .setDuration(500);
    }

    @Override
    public void onItemClear() {
        itemView.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f);
    }
}