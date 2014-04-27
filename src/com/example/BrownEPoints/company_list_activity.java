package com.example.BrownEPoints;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Ahindes5 on 4/22/14.
 */
public class company_list_activity extends Activity {

    ArrayList<CompanyWithNumberAds> compList = new ArrayList<CompanyWithNumberAds>();


    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_list);
        String allAdsRequest = "http://web.engr.illinois.edu/~null_ptrs/bpoints/company_table/get_companies_info.php";
        String outputAllCompanies = Process_request.runProcess(allAdsRequest);

        //GETTING ALL THE INDIVIDUAL COMPANIES AND THEIR NUM OF ADS
        String[] parts = outputAllCompanies.split("∞");

        for(int i = 0; i < parts.length; i++){
            Log.d("Making into Companies", parts[i]);
            String[] companies = parts[i].split("\\|");

            for(int j = 0; j < companies.length; j++){
                Log.d("second split", companies[j]);
            }
            CompanyWithNumberAds comp = new CompanyWithNumberAds(Integer.parseInt(companies[0]), companies[1], Integer.parseInt(companies[2]));
            compList.add(comp);
            Log.d("adding Company", "Added" + comp.getCompanyID());
        }

        ListView companyView = (ListView) findViewById(R.id.companyListview);
        companyArrayAdapter adapted = new companyArrayAdapter(companyView.getContext(), compList.toArray(new CompanyWithNumberAds[0]));
        companyView.setAdapter(adapted);

        companyView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                Log.d("ListView On Item Click", "Item with Position = "+ position);
                Intent newIntent = new Intent(company_list_activity.this, company_info_activity.class);
                Log.d("ListView On Item Click", "Inent was created");
                CompanyWithNumberAds placehold = compList.get(position);
                newIntent.putExtra("Advert", placehold.getCompanyID()); //SENDING Company ID TO THE NEW ACTIVITY
                startActivity(newIntent);

            }

        });


        /**
         * SO this is written using the company array adapter. It takes us to the company page when a company
         * is selected. It is essentially similar format to what we did with the last one using the infinity symbol
         * and all that for parsing
         */



    }

}
