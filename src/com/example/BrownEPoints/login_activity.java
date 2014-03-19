package com.example.BrownEPoints;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class login_activity extends Activity implements View.OnClickListener{

    private static String[] identity = new String[2];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public static String[] get_user_id()
    {
        return identity;
    }

    public static void set_user(String username, String password)
    {
        identity[0]= username;
        identity[1]= password;
    }

    public static void logout_user()
    {
        identity[0]="";
        identity[1]="";
    }


    public void onClick(View v){}

    public void new_user_button(View v){

        startActivity(new Intent(login_activity.this, new_user_activity.class));
    }

    public void login_button(View v){

        EditText username_input = (EditText)findViewById(R.id.username_input);
        EditText password_input = (EditText)findViewById(R.id.password_input);

        if(!username_input.getText().toString().trim().equals("") && !password_input.getText().toString().trim().equals(""))
        {
            String output="";
            String password = password_input.getText().toString();


            String username = username_input.getText().toString();


            String sRequest = "http://web.engr.illinois.edu/~null_ptrs/bpoints/user_table/get_user.php?username="+ username +"&password="+ password;
            Log.w("sRequest", sRequest);
            output = Process_request.runProcess(sRequest);


            if(!output.equals(""))
            {
                set_user(username, password);
                startActivity(new Intent(login_activity.this, user_homepage_activity.class));
            }
            else
            {
                Toast.makeText(this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
            }


        }
        else
            Toast.makeText(this, "Error logging in", Toast.LENGTH_SHORT).show();
    }

}