package com.example.guest.gobal.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.guest.gobal.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HotelsActivity extends AppCompatActivity {
    @Bind(R.id.buttonSearch) Button mButtonSearch;
    @Bind(R.id.locationEditText) EditText mLocationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);
        ButterKnife.bind(this);
        mLocationEditText = (EditText) findViewById(R.id.locationEditText);
        mButtonSearch = (Button) findViewById(R.id.buttonSearch);
        mButtonSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String location = mLocationEditText.getText().toString();
                Intent intent = new Intent(HotelsActivity.this, HotelsListActivity.class);
                intent.putExtra("location", location);
                startActivity(intent);
            }
        });

        Typeface caviarDreamsFont = Typeface.createFromAsset(getAssets(), "fonts/CaviarDreams.ttf");
        mLocationEditText.setTypeface(caviarDreamsFont);
        mButtonSearch.setTypeface(caviarDreamsFont);
    }
}
