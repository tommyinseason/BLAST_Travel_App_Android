package com.example.guest.gobal.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.gobal.R;
import com.example.guest.gobal.models.Hotel;
import com.example.guest.gobal.ui.HotelDetailActivity;

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


    public class RestaurantViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.hotelImageView) ImageView mHotelImageView;
        @Bind(R.id.hotelNameTextView) TextView mNameTextView;
        @Bind(R.id.categoryTextView) TextView mCategoryTextView;
        @Bind(R.id.ratingTextView) TextView mRatingTextView;
        private Context mContext;


        public RestaurantViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindHotel(Hotel hotel) {
            mNameTextView.setText(hotel.getName());
            mCategoryTextView.setText(hotel.getCategories().get(0));
            mRatingTextView.setText("Rating: " + hotel.getRating() + "/5");
        }
    }

    public class HotelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public HotelViewHolder(View itemView) {

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, HotelDetailActivity.class);
            intent.putExtra("position", itemPosition + "");
            intent.putExtra("hotel", Parcels.wrap(mHotel));
            mContext.startActivity(intent);
        }
    }
}
