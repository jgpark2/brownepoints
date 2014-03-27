package com.example.BrownEPoints;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import javax.net.ssl.HttpsURLConnection;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ad_downloader {

    private ImageView image;

    public ad_downloader(ImageView image) {
        this.image = image;

    }


    public void run_image_downloader()
    {
        ImageDownloader imageLoader = new ImageDownloader(image);
        imageLoader.execute("http://static2.businessinsider.com/image/514b2c81eab8ea6956000002-534-793/star%20trek%20into%20darkness%20poster-1.png");
    }


    public class ImageDownloader extends AsyncTask <String, Void, Bitmap>
    {

        ImageView image;

        public ImageDownloader(ImageView image) {
            this.image = image;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            String url = params[0];
            byte[] data = getImageFromURL(url);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
            return Bitmap.createScaledBitmap(BitmapFactory.decodeStream(inputStream), 200, 200, true);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap)
        {
            image.setImageBitmap(bitmap);
        }
    }



    public static byte[] getImageFromURL(String url)
    {
        InputStream in = null;
        byte[] byteimage = null;
        try
        {
            URL imageURL = new URL(url);
            HttpURLConnection connection = (HttpsURLConnection) imageURL.openConnection();
            connection.setAllowUserInteraction(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("GET");
            connection.connect();

            int response = connection.getResponseCode();

            if(response == HttpURLConnection.HTTP_OK)
            {
                in = connection.getInputStream();
            }


            int inRead;
            byte[] data = new byte[16384];

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();


            if (in != null) {
                while((inRead = in.read(data, 0, data.length)) != -1)
                {
                    buffer.write(data, 0, inRead);
                }
            }
            else
            {
                System.out.println("THE INPUT WAS NULLLLLL");
            }

            buffer.flush();

            byteimage = buffer.toByteArray();


        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

        return byteimage;
    }





}
