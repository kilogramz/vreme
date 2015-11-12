package com.example.user.prognoza;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gregor on 20.10.2015.
 */
public class ImageAdapter extends PagerAdapter {

    Context context;
//    private int[] GalImages = new int[] {
//            R.drawable.one,
//            R.drawable.two,
//            R.drawable.three
//    };

//    String[] slike = new String[] {"http://vreme.kilogramz.com/15-10-22/prognoza.hr/aladinHR/web_uv10_HRv8_03_e.gif"};
//            "http://vreme.kilogramz.com/15-10-22/prognoza.hr/aladinHR/web_uv10_HRv8_06_e.gif",
//            "http://vreme.kilogramz.com/15-10-22/prognoza.hr/aladinHR/web_uv10_HRv8_09_e.gif"
//    AsyncTask<String, Void, List<Bitmap>> slikeTask = new DownloadImageTask().execute(slike);


    ImageAdapter(Context context){
        this.context=context;

    }
    @Override
    public int getCount() {
//            if(slikeTask.get().size() != 4)
//                System.out.println("krneki velikost ni 4 ampak: " + slikeTask.get().size());
//            return slike.length;
            return Integer.valueOf(100);

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ImageView imageView = new ImageView(context);
//        int padding = context.getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin);
//        imageView.setPadding(padding, padding, padding, padding);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        try {
            if((position + 1) < 4) {
               Picasso.with(context).load("http://vreme.kilogramz.com/15-10-22/prognoza.hr/aladinHR/web_uv10_HRv8_0" + ((position + 1) * 3) + "_e.gif").into(imageView);
            } else {
                Picasso.with(context).load("http://vreme.kilogramz.com/15-10-22/prognoza.hr/aladinHR/web_uv10_HRv8_" + ((position + 1) * 3) + "_e.gif").into(imageView);
            }
//                System.out.println("size = " + slikeTask.get().size());
//                System.out.println("position je: " + position);
//                System.out.println("http://vreme.kilogramz.com/15-10-22/prognoza.hr/aladinHR/web_uv10_HRv8_" + (position * 3) + "_e.gif");
        } catch(Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        ((ViewPager) container).addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }

    private class DownloadImageTask extends AsyncTask<String, Void, List<Bitmap>> {
        ImageView bmImage;

        public DownloadImageTask() {
        }

        @Override
        protected List<Bitmap> doInBackground(String... urls) {
//            String urldisplay = urls[0];
            List<Bitmap> mIcon11 = new ArrayList<Bitmap>();
            for(String urldisplay : urls)
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11.add(BitmapFactory.decodeStream(in));
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }
        @Override
        protected void onPostExecute(List<Bitmap> result) {
//            bmImage.setImageBitmap(result);
//            System.out.println("število. slik: " + result.size());
        }
    }
}
