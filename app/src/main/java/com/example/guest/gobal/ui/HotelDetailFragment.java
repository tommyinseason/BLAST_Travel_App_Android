package com.example.guest.gobal.ui;


import android.content.Intent;
import android.graphics.Typeface;
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
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotelDetailFragment extends Fragment implements View.OnClickListener {
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;

    @Bind(R.id.hotelImageView) ImageView mImageLabel;
    @Bind(R.id.hotelNameTextView) TextView mNameLabel;
    @Bind(R.id.ratingTextView) TextView mRatingLabel;
    @Bind(R.id.websiteTextView) TextView mWebsiteLabel;
    @Bind(R.id.phoneTextView) TextView mPhoneLabel;
    @Bind(R.id.addressTextView) TextView mAddressLabel;
    @Bind(R.id.saveHotelButton) TextView mSaveHotelButton;

    private Hotel mHotel;

    public static HotelDetailFragment newInstance(Hotel hotel) {
        HotelDetailFragment hotelDetailFragment = new HotelDetailFragment();
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

//        Typeface caviarDreamsFont = Typeface.createFromAsset(getAssets(), "fonts/CaviarDreams.ttf");
//        mNameLabel.setTypeface(caviarDreamsFont);
//        mRatingLabel.setTypeface(caviarDreamsFont);
//        mWebsiteLabel.setTypeface(caviarDreamsFont);
//        mPhoneLabel.setTypeface(caviarDreamsFont);
//        mAddressLabel.setTypeface(caviarDreamsFont);
//        mSaveHotelButton.setTypeface(caviarDreamsFont);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hotel_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext())
                .load(mHotel.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mImageLabel);



        mNameLabel.setText(mHotel.getName());
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