package com.example.BrownEPoints;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class ad_viewer_activity extends Activity implements View.OnClickListener {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ad_viewer);




        ImageView iv = (ImageView) findViewById(R.id.adView);
        ad_downloader set_image = new ad_downloader(iv);
        set_image.run_image_downloader();

    }

    @Override
    public void onClick(View v) {}





//    public ad_viewer_button(View v){
//
//    }

}