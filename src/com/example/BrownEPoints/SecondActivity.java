package com.example.BrownEPoints;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Joel Holsteen (netID holsten2 on 3/15/14.
 */
public class SecondActivity extends Activity implements View.OnClickListener{

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user_screen);
    }

    public void onClick(View v){}

    public void new_user_button(View v){

        Toast.makeText(SecondActivity.this, "New Account Created!", Toast.LENGTH_LONG).show();
    }

}
