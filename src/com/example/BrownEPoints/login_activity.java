package com.example.BrownEPoints;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class login_activity extends Activity implements View.OnClickListener{

    DBAdapter dbAdapter;

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
        dbAdapter = new DBAdapter(this);
        dbAdapter.open();
    }
    private void closeDB() {
        dbAdapter.close();
    }



    public void onClick(View v){}

    public void new_user_button(View v){

        startActivity(new Intent(login_activity.this, new_user_activity.class));
    }

    public void user_homepage_button(View v){


        EditText username_input = (EditText)findViewById(R.id.username_input);
        EditText password_input = (EditText)findViewById(R.id.password_input);

        if(!username_input.getText().toString().trim().equals("") && !password_input.getText().toString().trim().equals(""))
        {
            String password = password_input.getText().toString();
            String username = username_input.getText().toString();

            DBAdapter dbAdapter = new DBAdapter(this);


            if(dbAdapter.isUser(username))
                startActivity(new Intent(login_activity.this, user_homepage_activity.class));
        }
    }


    public void add_user_button(View v){

        dbAdapter.insertUser("holsten2", "pass", "grindlemire", "0", "0", "white", "10", "USA", "CO", "male");

    }

}
