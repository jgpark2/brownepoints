package com.example.BrownEPoints;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Joel Holsteen (netID holsten2 on 3/15/14.
 */
public class user_info_activity extends Activity implements View.OnClickListener {

    private String the_username;
    private String the_password;
    private Map<String,String> user_info = new HashMap<String, String>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);

        EditText user_email = (EditText)findViewById(R.id.user_info_email);
        EditText user_username = (EditText)findViewById(R.id.user_info_username);
        EditText user_password = (EditText)findViewById(R.id.user_info_password);
        EditText user_age = (EditText)findViewById(R.id.user_info_age);
        EditText user_country = (EditText)findViewById(R.id.user_info_country);
        EditText user_state = (EditText)findViewById(R.id.user_info_state);
        EditText user_ethnicity = (EditText)findViewById(R.id.user_info_ethnicity);
        RadioButton user_male = (RadioButton)findViewById(R.id.user_info_male);
        RadioButton user_female = (RadioButton)findViewById(R.id.user_info_female);



        String[] identity =  login_activity.get_user_id();
        the_username = identity[0];
        the_password = identity[1];

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

        user_email.setText(user_info.get("Email"));
        user_username.setText(user_info.get("Username"));
        user_password.setText(user_info.get("Password"));
        user_country.setText(user_info.get("Country"));
        user_state.setText(user_info.get("State"));
        user_age.setText(user_info.get("Age"));
        user_ethnicity.setText(user_info.get("Ethnicity"));
        if(user_info.get("Gender").equals("Male"))
        {
            user_male.toggle();
        }
        else
        {
            user_female.toggle();
        }


       // editText.setText("Google is your friend.", TextView.BufferType.EDITABLE);






    }

    @Override
    public void onClick(View v) {

    }


    public void update_account_button(View v)
    {


        EditText user_email = (EditText)findViewById(R.id.user_info_email);
        EditText user_username = (EditText)findViewById(R.id.user_info_username);
        EditText user_password = (EditText)findViewById(R.id.user_info_password);
        EditText user_age = (EditText)findViewById(R.id.user_info_age);
        EditText user_country = (EditText)findViewById(R.id.user_info_country);
        EditText user_state = (EditText)findViewById(R.id.user_info_state);
        EditText user_ethnicity = (EditText)findViewById(R.id.user_info_ethnicity);
        RadioButton user_male = (RadioButton)findViewById(R.id.user_info_male);
        RadioButton user_female = (RadioButton)findViewById(R.id.user_info_female);

        String email = user_email.getText().toString().trim();
        String username = user_username.getText().toString().trim();
        String age = user_age.getText().toString().trim();
        String password = user_password.getText().toString().trim();
        String country = user_country.getText().toString().trim();
        String state = user_state.getText().toString().trim();
        String ethnicity = user_ethnicity.getText().toString().trim();
        String gender;
        if(user_male.isChecked())
            gender = "Male";
        else gender = "Female";

        String sRequest = "http://web.engr.illinois.edu/~null_ptrs/bpoints/user_table/update_user.php?email="+email+"&username="+username+"&password="+password
                +"&age="+age+"&country="+country+"&state="+state+"&ethnicity="+ethnicity+"&gender="+gender;

        String output = Process_request.runProcess(sRequest);

        Log.w("network", output);
        if(output.contains("updated successfully"))
        {
            Toast.makeText(this, "Account Updated", Toast.LENGTH_SHORT).show();
            login_activity.set_user(username, password);
            startActivity(new Intent(user_info_activity.this, user_homepage_activity.class));
        }
        else
        {
            Toast.makeText(this, "Error updating account, please contact admin", Toast.LENGTH_SHORT).show();
        }




    }

    public void delete_account_button(View v)
    {
        String username = user_info.get("Username");
        String password = user_info.get("Password");

        String output = Process_request.runProcess("http://web.engr.illinois.edu/~null_ptrs/bpoints/user_table/delete_user.php?username="+username+"&password="+password);

        if(output.contains("queried successfully"))
        {
            Toast.makeText(this, "Account Deleted", Toast.LENGTH_SHORT).show();
            login_activity.set_user("", "");
            startActivity(new Intent(user_info_activity.this, login_activity.class));
        }
        else
        {
            Toast.makeText(this, "Error deleting account, please contact admin", Toast.LENGTH_SHORT).show();
        }



    }


}