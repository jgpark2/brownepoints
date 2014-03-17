package com.example.BrownEPoints;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Joel Holsteen (netID holsten2 on 3/15/14.
 */
public class user_homepage_activity extends Activity implements View.OnClickListener{

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_homepage);
    }

    public void onClick(View v){}

    public void user_info_button(View v){

        startActivity(new Intent(user_homepage_activity.this, user_info_activity.class));
    }

    public void ad_viewer_button(View v){

        startActivity(new Intent(user_homepage_activity.this, ad_viewer_activity.class));
    }


    public void logout_button(View v){

        startActivity(new Intent(user_homepage_activity.this, login_activity.class));
        Toast.makeText(this, "You have been logged out.", Toast.LENGTH_SHORT).show();

    }

}
