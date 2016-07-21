package com.example.guest.gobal.ui;

import com.example.guest.gobal.BuildConfig;

/**
 * Created by Guest on 7/8/16.
 */
public class Constants {
    public static final String YELP_CONSUMER_KEY = BuildConfig.YELP_CONSUMER_KEY;
    public static final String YELP_CONSUMER_SECRET = BuildConfig.YELP_CONSUMER_SECRET;
    public static final String YELP_TOKEN = BuildConfig.YELP_TOKEN;
    public static final String YELP_TOKEN_SECRET = BuildConfig.YELP_TOKEN_SECRET;
    public static final String YELP_BASE_URL = "https://api.yelp.com/v2/search?term=hotels";
    public static final String YELP_LOCATION_QUERY_PARAMETER = "location";

    public static final String FIREBASE_LOCATION_SEARCHED_LOCATION = "searchedLocation";
    public static final String FIREBASE_CHILD_HOTELS = "hotels";
    public static final String FIREBASE_QUERY_INDEX = "index";
}
