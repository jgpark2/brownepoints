package com.example.BrownEPoints;

/**
 * Created by Ahindes5 on 4/14/14.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ad_list_activity extends Activity{
    /**
     * Called when the activity is first created.
     */

    ArrayList<Advertisements> adList = new ArrayList<Advertisements>();

    @Override
    public void onCreate(Bundle savedInstanceState) {

        String outputAllAds = null;

        //Checks to see if coming from the company page
        Intent currentIntent = getIntent();
        if(currentIntent.getIntExtra("CompanyID",-5) != -5){

            int companyID = currentIntent.getIntExtra("CompanyID", -5);
            super.onCreate(savedInstanceState);
            setContentView(R.layout.ad_list);
            String allAdsRequest = "http://web.engr.illinois.edu/~null_ptrs/bpoints/company_table/get_sorted_ads_list.php";
            outputAllAds = Process_request.runProcess(allAdsRequest);

        }

        else{

            super.onCreate(savedInstanceState);
            setContentView(R.layout.ad_list);
            String allAdsRequest = "http://web.engr.illinois.edu/~null_ptrs/bpoints/ad_table/get_all_ads.php?";
            outputAllAds = Process_request.runProcess(allAdsRequest);
        }
            Log.d("sql test wiener", outputAllAds);
            String[] parts = outputAllAds.split("∞");
            Log.d("after split 1", parts[0]);
            Log.d("after split 2", parts[1]);
            Log.d("after split 3", parts[2]);

            for(int i = 0; i < parts.length; i++){
                Log.d("Making into Advertisements", parts[i]);
                String[] ads = parts[i].split("\\|");

                for(int j = 0; j < ads.length; j++){
                    Log.d("second split", ads[j]);
                }
                Advertisements andy = new Advertisements( Integer.parseInt(ads[0]), Integer.parseInt(ads[1]), ads[2], ads[3]);
                adList.add(andy);
                Log.d("adding Advertisements", "Added" + andy.getAdId());
            }



        ListView adView = (ListView) findViewById(R.id.adListview);
        adArrayAdapter adapted = new adArrayAdapter(adView.getContext(), adList.toArray(new Advertisements[0]));
        adView.setAdapter(adapted);

        adView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                Log.d("ListView On Item Click", "Item with Position = "+ position);
                Intent newIntent = new Intent(ad_list_activity.this, ad_viewer_activity.class);
                Log.d("ListView On Item Click", "Inent was created");
                Advertisements placehold = adList.get(position);
                newIntent.putExtra("Advert", placehold.getAdId()); //SENDING AD ID TO THE NEW ACTIVITY
                newIntent.putExtra("adURL", placehold.getDomain());

                startActivity(newIntent);

            }

        });

    }



}
