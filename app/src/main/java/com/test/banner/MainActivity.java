package com.test.banner;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.test.banner.demo.BannerAnimationActivity;
import com.test.banner.demo.BannerLocalActivity;
import com.test.banner.demo.BannerStyleActivity;
import com.test.banner.demo.CustomBannerActivity;
import com.test.banner.demo.CustomViewPagerActivity;
import com.test.banner.demo.IndicatorPositionActivity;
import com.test.banner.loader.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener, OnBannerListener {
    static final int REFRESH_COMPLETE = 0X1112;
    //SuperSwipeRefreshLayout mSwipeLayout;
    //ListView listView;
    Banner banner;

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    String[] urls = getResources().getStringArray(R.array.url4);
                    List list = Arrays.asList(urls);
                    List arrayList = new ArrayList(list);
                    banner.update(arrayList);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        banner = (Banner) findViewById(R.id.banner);
        String[] data = getResources().getStringArray(R.array.demo_list);
        banner.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 400));


        List<String> list = new ArrayList<>();
        list.add("");

        banner.setImages(list)
                .setImageLoader(new GlideImageLoader())
                .setOnBannerListener(this)
                .start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        banner.setImages(App.images.subList(0,1))
                                .setImageLoader(new GlideImageLoader())
                                .start();
                    }
                });
            }
        }).start();

    }

    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(getApplicationContext(),"你点击了："+position,Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onStart() {
        super.onStart();
        banner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }

    @Override
    public void onRefresh() {
        mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 1:
                startActivity(new Intent(this, BannerAnimationActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, BannerStyleActivity.class));
                break;
            case 3:
                startActivity(new Intent(this, IndicatorPositionActivity.class));
                break;
            case 4:
                startActivity(new Intent(this, CustomBannerActivity.class));
                break;
            case 5:
                startActivity(new Intent(this, BannerLocalActivity.class));
                break;
            case 6:
                startActivity(new Intent(this, CustomViewPagerActivity.class));
                break;
        }
    }


}
