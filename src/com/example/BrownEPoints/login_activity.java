package com.example.BrownEPoints;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

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

            final String sRequest = "http://web.engr.illinois.edu/~holsten2/get_user.php?username="+username+"&password="+password;
            final HttpClient client = new DefaultHttpClient();

            try {

                check_login check = new check_login();
                check.set_path(sRequest);
                Thread t = new Thread(check);
                t.start();
                t.join();
                output = check.return_reponse();

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


    public void add_user_button(View v){


        String email = "holsten2@illinois.edu";
        String username = "holsten2";
        String password = "pass";
        int age = 20;
        String country = "USA";

        final String sRequest = "http://web.engr.illinois.edu/~holsten2/insert_user.php?email="+email+"&username="+username+"&age="+age+"&country="+country+"&password="+password;
//        Log.w("hi", "GOT HERE");
        try {
            Thread t = (new Thread() {
                public void run() {
                    try {
                        Document d = Jsoup.connect(sRequest).get();
                        Log.w("network", d.outerHtml());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            t.start();
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}

class check_login implements Runnable{
    private volatile String response="";
    private String request="";

    public void run() {
        try {
            Document d = Jsoup.connect(request).get();
            Log.w("network", d.text());
            response = d.text();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void set_path(String path)
    {
        request = path;
    }

    public String return_reponse()
    {
        return response;
    }
}

