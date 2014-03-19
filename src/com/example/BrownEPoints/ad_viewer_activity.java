package com.example.BrownEPoints;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class ad_viewer_activity extends Activity implements View.OnClickListener {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ad_viewer);

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http").authority("www.cache.g4tv.com").appendPath("ImageDb3").appendPath("315444_S").appendPath("star-trek-into-darkness-teaser-poster.jp");
        Uri myUrl = builder.build();


        ImageView ad_image = (ImageView)findViewById(R.id.ad_image_view);

        ad_image.setImageURI(myUrl);






    }

    @Override
    public void onClick(View v) {}


//    public static Drawable LoadImageFromWebOperations(String theUrl) {
//        try {
//            InputStream is = (InputStream) new URL(theUrl).getContent();
//            Drawable d = Drawable.createFromStream(is, "http://cache.g4tv.com/ImageDb3/315444_S/star-trek-into-darkness-teaser-poster.jpg");
//            return d;
//        } catch (Exception e) {
//            return null;
//        }



}