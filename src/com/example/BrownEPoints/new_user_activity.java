package com.example.BrownEPoints;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

/**
 * Created by Joel Holsteen (netID holsten2 on 3/15/14.
 */
public class new_user_activity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user_screen);
    }

    public void onClick(View v){}

    public void create_new_user_button(View v){

        String output="";

        EditText new_user_email = (EditText)findViewById(R.id.new_user_email);
        EditText new_user_username = (EditText)findViewById(R.id.new_user_username);
        EditText new_user_age = (EditText)findViewById(R.id.new_user_age);
        EditText new_user_password = (EditText)findViewById(R.id.new_user_password);
        RadioButton new_user_male = (RadioButton)findViewById(R.id.new_user_male);
        RadioButton new_user_female = (RadioButton)findViewById(R.id.new_user_female);
        EditText new_user_country = (EditText)findViewById(R.id.new_user_country);
        EditText new_user_state = (EditText)findViewById(R.id.new_user_state);
        EditText new_user_ethnicity = (EditText)findViewById(R.id.new_user_ethnicity);


        if(!new_user_email.getText().toString().trim().equals("") &&
                !new_user_username.getText().toString().trim().equals("")&&
                !new_user_age.getText().toString().trim().equals("")&&
                !new_user_password.getText().toString().trim().equals("")&&
                !new_user_country.getText().toString().trim().equals("")&&
                !new_user_state.getText().toString().trim().equals("")&&
                !new_user_ethnicity.getText().toString().trim().equals("")&&
                (new_user_male.isChecked() || new_user_female.isChecked()))

        {

            String email = new_user_email.getText().toString().trim();
            String username = new_user_username.getText().toString().trim();
            String age = new_user_age.getText().toString().trim();
            String password = new_user_password.getText().toString().trim();
            String country = new_user_country.getText().toString().trim();
            String state = new_user_state.getText().toString().trim();
            String ethnicity = new_user_ethnicity.getText().toString().trim();
            String gender;
            if(new_user_male.isChecked())
                gender = "Male";
            else gender = "Female";

            String sRequest = "http://web.engr.illinois.edu/~null_ptrs/bpoints/user_table/insert_user.php?email="+email+"&username="+username+"&password="+password
                   +"&age="+age+"&country="+country+"&state="+state+"&ethnicity="+ethnicity+"&gender="+gender;

            System.out.println(sRequest);
            try {

                Process_request new_user = new Process_request();
                new_user.set_path(sRequest);
                Thread t = new Thread(new_user);
                t.start();
                t.join();
                output = new_user.return_reponse();
                Log.w("network", output);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(output.contains("queried successfully"))
            {
                Toast.makeText(this, "New Account Created!", Toast.LENGTH_SHORT).show();
                login_activity.set_user(username, password);
                startActivity(new Intent(new_user_activity.this, user_homepage_activity.class));
            }
            else if(output.contains("Error inserting into database:"))
            {
                Toast.makeText(this, "Error inserting into database", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Please try again.", Toast.LENGTH_SHORT).show();
            }

        }



//        Toast.makeText(new_user_activity.this, " Please enter all the information", Toast.LENGTH_SHORT).show();
    }

}