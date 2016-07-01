package com.example.guest.gobal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HotelsActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private Button mFindCitiesButton;
    private EditText mLocationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);
        mLocationEditText = (EditText) findViewById(R.id.locationEditText);
        mFindCitiesButton = (Button) findViewById(R.id.buttonSearch);
        mFindCitiesButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String location = mLocationEditText.getText().toString();
                Log.d(TAG, location);
                Intent intent = new Intent(HotelsActivity.this, HotelsShowActivity.class);
                startActivity(intent);
            }
        });
    }
}
