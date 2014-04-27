package com.example.BrownEPoints;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;


public class user_search_results_activity extends Activity implements View.OnClickListener{

    private Map<String,String> search_user_info = new HashMap<String, String>();


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_search_results);

        String search_word  = user_list_activity.get_selected_name();
        //user_homepage_activity.get_search_result();

        TextView user_name = (TextView)findViewById(R.id.search_user_info_username);
        TextView user_credibility = (TextView)findViewById(R.id.search_user_info_credibility);
        TextView user_points = (TextView)findViewById(R.id.search_user_info_points);
        TextView user_age = (TextView)findViewById(R.id.search_user_info_age);
        TextView user_country = (TextView)findViewById(R.id.search_user_info_country);
        TextView user_state = (TextView)findViewById(R.id.search_user_info_state);
        TextView user_ethnicity = (TextView)findViewById(R.id.search_user_info_ethnicity);
        TextView user_gender = (TextView)findViewById(R.id.search_user_info_gender);

        String sRequest = "http://web.engr.illinois.edu/~null_ptrs/bpoints/user_table/select_user.php?username="+search_word;
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

                search_user_info.put(input_key, input_value);

            }
        }
        catch (NullPointerException n){
            n.printStackTrace();
        }

        user_name.setText(search_user_info.get("Username"));
        user_credibility.setText(search_user_info.get("Credibility"));
        user_points.setText(search_user_info.get("Points"));
        user_age.setText(search_user_info.get("Age"));
        user_country.setText(search_user_info.get("Country"));
        user_state.setText(search_user_info.get("State"));
        user_ethnicity.setText(search_user_info.get("Ethnicity"));
        user_gender.setText(search_user_info.get("Gender"));



    }


    @Override
    public void onClick(View v) {

    }
}