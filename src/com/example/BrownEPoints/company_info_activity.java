package com.example.BrownEPoints;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Ahindes5 on 4/24/14.
 */
public class company_info_activity extends Activity {

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_info);

        Intent currIntent = getIntent();
        int companyId = currIntent.getIntExtra("Advert", -5);
        Log.d("Company ID was:", new Integer(companyId).toString());

        //We will most likely use multiple SQL statements here
        String genderRequest = "http://web.engr.illinois.edu/~null_ptrs/bpoints/company_table/get_gender_ratio.php";
        String genderRatio = Process_request.runProcess(genderRequest);

        String allAdsRequest = "SQL STATEMENT THAT RETURNS ALL THE SHIT WE NEED TO KNOW FOR THIS INFO";
        String outputAllAds = Process_request.runProcess(allAdsRequest);

        TextView AverageAgeTV = (TextView)findViewById(R.id.AverageAge);
        TextView AverageCompanyRatingTV = (TextView)findViewById(R.id.AverageCompanyRating);
        TextView GenderRatioTV = (TextView)findViewById(R.id.GenderRatio);
        TextView AverageAdRatingTV = (TextView)findViewById(R.id.AverageAdRating);

        AverageAgeTV.setText("GET FROM QUERY");
        AverageCompanyRatingTV.setText("GET FROM QUERY");
        AverageAdRatingTV.setText("GET FROM QUERY");
        GenderRatioTV.setText(genderRatio);

        //does this work
    }

    public void view_ads_button(View v){

        Intent currIntent = getIntent();
        int companyId = currIntent.getIntExtra("Advert", -5);

        Intent companyOnly = new Intent(company_info_activity.this, ad_list_activity.class);
        companyOnly.putExtra("CompanyID", companyId);

        startActivity(companyOnly);

    }

}
