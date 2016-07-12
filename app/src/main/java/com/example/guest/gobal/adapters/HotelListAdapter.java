package com.example.guest.gobal.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.gobal.R;
import com.example.guest.gobal.models.Hotel;
import com.example.guest.gobal.ui.HotelDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 7/8/16.
 */
public class HotelListAdapter extends RecyclerView.Adapter<HotelListAdapter.HotelViewHolder> {
    private ArrayList<Hotel> mHotels = new ArrayList<>();
    private Context mContext;

    public HotelListAdapter(Context context, ArrayList<Hotel> hotels) {
        mContext = context;
        mHotels = hotels;
    }

    @Override
    public HotelListAdapter.HotelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_list_item, parent, false);
        HotelViewHolder viewHolder = new HotelViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HotelListAdapter.HotelViewHolder holder, int position) {
        holder.bindHotel(mHotels.get(position));
    }

    @Override
    public int getItemCount() {
        return mHotels.size();
    }


    public class HotelViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
        @Bind(R.id.hotelImageView) ImageView mHotelImageView;
        @Bind(R.id.hotelNameTextView) TextView mNameTextView;
        @Bind(R.id.ratingTextView) TextView mRatingTextView;
//        @Bind(R.id.addressTextView) TextView mAddressTextView;
        private Context mContext;


        public HotelViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindHotel(Hotel hotel) {
            Picasso.with(mContext).load(hotel.getImageUrl()).into(mHotelImageView);
            mNameTextView.setText(hotel.getName());
            mRatingTextView.setText("Rating: " + hotel.getRating() + "/5");


        }

        @Override
        public void onClick(View v) {
            Log.d("hotel in adapter", mHotels.size() + "");
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, HotelDetailActivity.class);
            intent.putExtra("position", itemPosition + "");
            intent.putExtra("hotels", Parcels.wrap(mHotels));
            mContext.startActivity(intent);
        }
    }
}
