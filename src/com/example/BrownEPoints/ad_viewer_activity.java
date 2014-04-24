package com.example.BrownEPoints;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;

import java.net.URL;


public class ad_viewer_activity extends Activity implements View.OnClickListener {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ad_viewer);


        Intent adIntent = getIntent();
        String adURL = adIntent.getStringExtra("adURL");

        Log.d("adURL is: ", adURL);



        ImageView iv = (ImageView) findViewById(R.id.adView);


        ad_downloader set_image = new ad_downloader(iv);

        set_image.run_image_downloader(adURL);

         //breaking

    }

    @Override
    public void onClick(View v) {}

    public void companySubmit(View v){

        RatingBar companyRatingBar = (RatingBar) findViewById(R.id.c_ratingBar);

        companyRatingBar.getRating();


        //Query to insert the rating from the 5 star thing

    }

    public void adSubmit(View v){

        RatingBar adRatingBar = (RatingBar) findViewById(R.id.ad_ratingBar);

        adRatingBar.getRating();

        //Query to insert the ad's rating

    }




}