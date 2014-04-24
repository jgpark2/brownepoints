package com.example.BrownEPoints;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;


public class user_homepage_activity extends Activity implements View.OnClickListener{


    private static String search_word = "";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_homepage);

        SearchView searchView= (SearchView)findViewById(R.id.user_search);
        int id = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        TextView textView = (TextView) searchView.findViewById(id);
        textView.setTextColor(Color.BLACK);

    }

    public void onClick(View v){}

    public void user_info_button(View v){

        startActivity(new Intent(user_homepage_activity.this, user_info_activity.class));
    }

    public void ad_viewer_button(View v){

        startActivity(new Intent(user_homepage_activity.this, ad_list_activity.class));
    }

    public void companiesButton (View v){
        startActivity(new Intent(user_homepage_activity.this, company_list_activity.class));
    }

    public void logout_button(View v){

        login_activity.logout_user();
        startActivity(new Intent(user_homepage_activity.this, login_activity.class));
        Toast.makeText(this, "You have been logged out.", Toast.LENGTH_SHORT).show();

    }

    public static String get_search_result(){
        return search_word;
    }




    public void search_user_database_button(View v){
        SearchView search_result = (SearchView) findViewById(R.id.user_search);
        final Context theContext = this;

        search_result.setSubmitButtonEnabled(true);

        search_result.setOnQueryTextListener( new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextChange(String newText) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                search_word = query;

                if(search_word.equals("")){
                    //Toast.makeText(this, "Please input something to search", Toast.LENGTH_SHORT).show();
                    return false;
                }

                String sRequest = "http://web.engr.illinois.edu/~null_ptrs/bpoints/user_table/select_user.php?username="+search_word;
                String output = Process_request.runProcess(sRequest);

                if (output.equals(""))
                {
                    Toast.makeText(theContext, "No user with that username", Toast.LENGTH_LONG).show();
                    return false;
                }

                else
                {
                    startActivity(new Intent(user_homepage_activity.this, user_search_results_activity.class));
                    return true;
                }
            }
        }
        );

    }

}
