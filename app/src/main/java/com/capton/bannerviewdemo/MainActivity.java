package com.capton.bannerviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.capton.bannerview.BannerView;
import com.capton.bannerview.Indicator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BannerView bannerView;
    BannerView bannerView2;
    BannerView bannerView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> stringArrayList=new ArrayList<>();
         stringArrayList.add("http://img2.91.com/uploads/allimg/140331/32-1403311A009.jpg");
         stringArrayList.add("http://img1.imgtn.bdimg.com/it/u=3055632223,2826867768&fm=214&gp=0.jpg");
        stringArrayList.add("http://img.article.pchome.net/00/28/41/62/pic_lib/wm/hlfjkpbz_17.jpg");

        bannerView= (BannerView) findViewById(R.id.banner);
        bannerView2= (BannerView) findViewById(R.id.banner2);
        bannerView3= (BannerView) findViewById(R.id.banner3);

         bannerView.loadUrl( stringArrayList);
         bannerView.setAutoPlay(true);
         bannerView.smoothPlay(false);
         bannerView.setInterval(3000);

        bannerView2.loadUrl( stringArrayList);
        bannerView2.setAutoPlay(false);
        bannerView2.setIndicatorLocation(BannerView.INDICATOR_LOCATION_CENTER);
        bannerView2.setInterval(4000);

        bannerView3.loadUrl( stringArrayList);
        bannerView3.setAutoPlay(false);
        bannerView3.setInterval(5000);

    }
}
