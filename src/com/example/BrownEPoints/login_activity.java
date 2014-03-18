package com.example.BrownEPoints;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class login_activity extends Activity implements View.OnClickListener{

    DBAdapter myDb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        openDB();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeDB();
    }

    private void openDB() {
        myDb = new DBAdapter(this);
        myDb.open();
    }
    private void closeDB() {
        myDb.close();
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


            String sRequest = "http://web.engr.illinois.edu/~holsten2/get_user.php?username="+username+"&password="+password;


            try {

                Process_request check_login = new Process_request();
                check_login.set_path(sRequest);
                Thread t = new Thread(check_login);
                t.start();
                t.join();
                output = check_login.return_reponse();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(!output.equals(""))
            {
                startActivity(new Intent(login_activity.this, user_homepage_activity.class));
            }
            else
            {
                Toast.makeText(this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
            }


        }
        else
            Toast.makeText(this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
    }

}