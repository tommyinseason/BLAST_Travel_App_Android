package com.example.guest.gobal.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.guest.gobal.R;
import com.example.guest.gobal.models.Hotel;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotelDetailFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.restaurantImageView) ImageView mImageLabel;
    @Bind(R.id.restaurantNameTextView) TextView mNameLabel;
    @Bind(R.id.cuisineTextView) TextView mCategoriesLabel;
    @Bind(R.id.ratingTextView) TextView mRatingLabel;
    @Bind(R.id.websiteTextView) TextView mWebsiteLabel;
    @Bind(R.id.phoneTextView) TextView mPhoneLabel;
    @Bind(R.id.addressTextView) TextView mAddressLabel;
    @Bind(R.id.saveRestaurantButton) TextView mSaveRestaurantButton;

    private Hotel mHotel;

    public static HotelDetailFragment newInstance(Hotel restaurant) {
        HotelDetailFragment restaurantDetailFragment = new HotelDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("hotel", Parcels.wrap(hotel));
        hotelDetailFragment.setArguments(args);
        return hotelDetailFragment;
    }


    public HotelDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHotel = Parcels.unwrap(getArguments().getParcelable("hotel"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant_detail, container, false);
        ButterKnife.bind(this, view);
        Picasso.with(view.getContext()).load(mHotel.getImageUrl()).into(mImageLabel);

        mNameLabel.setText(mHotel.getName());
        mCategoriesLabel.setText(android.text.TextUtils.join(", ", mHotel.getCategories()));
        mRatingLabel.setText(Double.toString(mHotel.getRating()) + "/5");
        mPhoneLabel.setText(mHotel.getPhone());
        mAddressLabel.setText(android.text.TextUtils.join(", ", mHotel.getAddress()));
        mWebsiteLabel.setOnClickListener(this);
        mPhoneLabel.setOnClickListener(this);
        mAddressLabel.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mWebsiteLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(mHotel.getWebsite()));
            startActivity(webIntent);
        }
        if (v == mPhoneLabel) {
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:" + mHotel.getPhone()));
            startActivity(phoneIntent);
        }
        if (v == mAddressLabel) {
            Intent mapIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("geo:" + mHotel.getLatitude()
                            + "," + mHotel.getLongitude()
                            + "?q=(" + mHotel.getName() + ")"));
            startActivity(mapIntent);
        }
    }
}