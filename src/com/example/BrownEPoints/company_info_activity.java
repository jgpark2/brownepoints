package com.example.BrownEPoints;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

        //We will most likely use multiple SQL statements here
        String genderRequest = "http://web.engr.illinois.edu/~null_ptrs/bpoints/company_table/get_gender_ratio.php?CompanyID="+companyId;
        String genderRatio = Process_request.runProcess(genderRequest);

        String average_age_request = "https://web.engr.illinois.edu/~null_ptrs/bpoints/company_table/get_average_user_age_of_company.php?CompanyID="+companyId;
        String age_request_output = Process_request.runProcess(average_age_request);

        TextView AverageAgeTV = (TextView)findViewById(R.id.average_age_input);
        TextView AverageCompanyRatingTV = (TextView)findViewById(R.id.average_company_rating_input);
        TextView GenderRatioTV = (TextView)findViewById(R.id.gender_ratio_input);
        TextView AverageAdRatingTV = (TextView)findViewById(R.id.average_ad_rating_input);

        AverageAgeTV.setText(age_request_output);//company_table/get_average_user_age_of_company.php
        AverageCompanyRatingTV.setText("GET FROM QUERY");//
        AverageAdRatingTV.setText("GET FROM QUERY");//
        GenderRatioTV.setText(genderRatio);//company_table/get_gender_ratio

        //does this work
        //branch working?
    }

    public void view_ads_button(View v){

        Intent currIntent = getIntent();
        int companyId = currIntent.getIntExtra("Advert", -5);

        Intent companyOnly = new Intent(company_info_activity.this, ad_list_activity.class);
        companyOnly.putExtra("CompanyID", companyId);

        startActivity(companyOnly);

    }

}
