package com.example.BrownEPoints;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jonathan on 4/16/2014.
 *
 * Display selectable list of usernames
 */
public class user_list_activity extends Activity implements View.OnClickListener {

    private static String selected_name = "";
    public final static String NAME_EXTRA="com.example.BrownEPoints._ID";
    final ArrayList<String> list = new ArrayList<String>();

    public void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.user_list);

            String search_word  = user_homepage_activity.get_search_result();

            TextView search_word_field = (TextView)findViewById(R.id.search_word);
            search_word_field.setText(search_word);
            ListView myListView = (ListView)findViewById(R.id.userListView);
            final ArrayAdapter adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, list);
            myListView.setAdapter(adapter);

            //What do we do when an item in list is clicked?
            myListView.setOnItemClickListener(onListClick);

            String sRequest = "http://web.engr.illinois.edu/~null_ptrs/bpoints/user_table/search_user_list.php?username="+search_word;
            Log.w("sRequest", sRequest);
            String output = Process_request.runProcess(sRequest);
            System.out.println(output);
            int index = 0;

            while(index<output.length())
            {
                int start_type = output.indexOf("|", index);
                int end_type = output.indexOf("=", index);

                if(start_type<0 || end_type <0)
                    break;

                String input_key = output.substring(start_type+1, end_type);
                index = end_type;


                int start_info = output.indexOf("=", index);
                int end_info = output.indexOf("|", index);
                String input_value = output.substring(start_info+1, end_info);
                index++;

                list.add(input_value);
            }


            //user_name.setText(list.get());
        }
        catch (Exception e) {
            Log.e("ERROR", "ERROR IN CODE: " + e.toString());
            e.printStackTrace();
        }

    }
    /*Back button for a new search*/
    //Do we need to implement back though?

    private AdapterView.OnItemClickListener onListClick=new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            selected_name = ((TextView)view).getText().toString();

            String sRequest = "http://web.engr.illinois.edu/~null_ptrs/bpoints/user_table/select_user.php?username="+selected_name;
            String output = Process_request.runProcess(sRequest);

            Intent i = new Intent(user_list_activity.this, user_search_results_activity.class);
            i.putExtra("passed_username", selected_name);
            startActivity(i);
        }
    };

    public static String get_selected_name(){
        return selected_name;
    }

    @Override
    public void onClick(View v) {

    }
}