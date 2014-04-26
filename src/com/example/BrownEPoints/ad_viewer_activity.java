package com.example.BrownEPoints;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class ad_viewer_activity extends Activity implements View.OnClickListener {

    int adid;
    int companyid;
    String email;
    String[] identity = login_activity.get_user_id();
    private Map<String,String> user_info = new HashMap<String, String>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ad_viewer);


        Intent adIntent = getIntent();
        String adURL = adIntent.getStringExtra("adURL");
        adid=adIntent.getIntExtra("Advert", -1);
        companyid=adIntent.getIntExtra("companyID", -1);

        Log.d("adURL is: ", adURL);
        Log.d("adID is: ", new Integer(adid).toString());

        ImageView iv = (ImageView) findViewById(R.id.adView);


        ad_downloader set_image = new ad_downloader(iv);

        set_image.run_image_downloader(adURL);

         //breaking

        String the_username = identity[0];
        String the_password = identity[1];

        String sRequest = "http://web.engr.illinois.edu/~null_ptrs/bpoints/user_table/get_user.php?username="+the_username+"&password="+the_password;
        Log.w("sRequest", sRequest);
        String output = Process_request.runProcess(sRequest);
        System.out.println(output);
        int prev = 0;
        try{
            while(prev<output.length())
            {
                int start_type = output.indexOf("|", prev);
                int end_type = output.indexOf("=", prev);

                if(start_type<0 || end_type <0)
                    break;

                String input_key = output.substring(start_type+1, end_type);
                prev = end_type;


                int start_info = output.indexOf("=", prev);
                int end_info = output.indexOf("|", prev);
                String input_value = output.substring(start_info+1, end_info);
                prev++;

                user_info.put(input_key, input_value);

            }
        }
        catch (NullPointerException n){
            n.printStackTrace();
        }

        email = user_info.get("Email");

    }

    @Override
    public void onClick(View v) {}

    public void companySubmit(View v){

        RatingBar companyRatingBar = (RatingBar) findViewById(R.id.c_ratingBar);

        int rating = Math.round(companyRatingBar.getRating());
        Log.d("crating is: ", new Integer(rating).toString());


        //Query to insert the rating from the 5 star thing
        String sRequest = "http://web.engr.illinois.edu/~null_ptrs/bpoints/rates_table/insert_rates.php?companyid="+companyid+"&email="+email+"&rating="+rating;

        String output = Process_request.runProcess(sRequest);

        Log.w("network", output);
        if(output.contains("queried successfully"))
        {
            Toast.makeText(this, "Submitted", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Error submitting", Toast.LENGTH_SHORT).show();
        }

    }

    public void adSubmit(View v){

        RatingBar adRatingBar = (RatingBar) findViewById(R.id.ad_ratingBar);

        int rating = Math.round(adRatingBar.getRating());
        Log.d("arating is: ", new Integer(rating).toString());


        //Query to insert the ad's rating
        String sRequest = "http://web.engr.illinois.edu/~null_ptrs/bpoints/watches_table/insert_watches.php?adid="+adid+"&email="+email+"&rating="+rating;

        String output = Process_request.runProcess(sRequest);

        Log.w("network", output);
        if(output.contains("queried successfully"))
        {
            Toast.makeText(this, "Submitted", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Error submitting", Toast.LENGTH_SHORT).show();
        }
    }




}