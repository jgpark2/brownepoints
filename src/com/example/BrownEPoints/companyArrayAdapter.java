package com.example.BrownEPoints;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Ahindes5 on 4/22/14.
 */
public class companyArrayAdapter extends ArrayAdapter<CompanyWithNumberAds> {

    CompanyWithNumberAds[] values;
    Context context;

    public companyArrayAdapter(Context context, CompanyWithNumberAds[] objects) {
        super(context, R.layout.ad_row_layout, objects);
        this.context = context;
        this.values = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.ad_row_layout, parent, false);
        TextView companyName = (TextView) rowView.findViewById(R.id.companyName);
        TextView pointsWorth = (TextView) rowView.findViewById(R.id.pointsWorth);
        companyName.setText(values[position].getCompanyName());
        pointsWorth.setText(Integer.toString(values[position].getNumberOfAds()) );
        Log.d("getView", "View Created");
        return rowView;
    }

}
