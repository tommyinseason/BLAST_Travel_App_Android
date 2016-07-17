package com.example.guest.gobal.ui;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guest.gobal.R;
import com.example.guest.gobal.models.Hotel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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

    private Context context;

    @Bind(R.id.hotelImageView) ImageView mImageLabel;
    @Bind(R.id.hotelNameTextView) TextView mNameLabel;
    @Bind(R.id.ratingTextView) TextView mRatingLabel;
    @Bind(R.id.websiteTextView) TextView mWebsiteLabel;
    @Bind(R.id.phoneTextView) TextView mPhoneLabel;
    @Bind(R.id.addressTextView) TextView mAddressLabel;
    @Bind(R.id.saveHotelButton) Button mSaveHotelButton;
    @Bind(R.id.savedHotelsButton) Button mSavedHotelsButton;


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

        context = getActivity().getApplicationContext();


        Typeface caviarDreamsFont = Typeface.createFromAsset(context.getAssets(), "fonts/CaviarDreams.ttf");
        mNameLabel.setTypeface(caviarDreamsFont);
        mRatingLabel.setTypeface(caviarDreamsFont);
        mWebsiteLabel.setTypeface(caviarDreamsFont);
        mPhoneLabel.setTypeface(caviarDreamsFont);
        mAddressLabel.setTypeface(caviarDreamsFont);
        mSaveHotelButton.setTypeface(caviarDreamsFont);
        mSavedHotelsButton.setTypeface(caviarDreamsFont);
        mSavedHotelsButton.setTypeface(caviarDreamsFont);



        mNameLabel.setText(mHotel.getName());
        mRatingLabel.setText(Double.toString(mHotel.getRating()) + "/5");
        mPhoneLabel.setText(mHotel.getPhone());
        mAddressLabel.setText(android.text.TextUtils.join(", ", mHotel.getAddress()));

        mWebsiteLabel.setOnClickListener(this);
        mPhoneLabel.setOnClickListener(this);
        mAddressLabel.setOnClickListener(this);

        mSaveHotelButton.setOnClickListener(this);
        mSavedHotelsButton.setOnClickListener(this);

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
        if (v == mSaveHotelButton) {
            DatabaseReference hotelRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_HOTELS);
            hotelRef.push().setValue(mHotel);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
        if (v == mSavedHotelsButton) {
            Intent intent = new Intent(getContext(), SavedHotelListActivity.class);
            startActivity(intent);
        }
        if (v == mSaveHotelButton) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();

            DatabaseReference restaurantRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_HOTELS)
                    .child(uid);

            DatabaseReference pushRef = restaurantRef.push();
            String pushId = pushRef.getKey();
            mHotel.setPushId(pushId);
            pushRef.setValue(mHotel);

            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }

    }
}