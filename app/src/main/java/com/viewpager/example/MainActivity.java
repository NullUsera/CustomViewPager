package com.viewpager.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private int resourceIds[] = {R.mipmap.guide_1, R.mipmap.guide_2, R.mipmap.guide_3,
            R.mipmap.guide_4, R.mipmap.guide_5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CustomViewPager viewPager = (CustomViewPager) findViewById(R.id.viewpager);

        // 添加页面
        for (int resourceId : resourceIds) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(resourceId);
            viewPager.addView(imageView);
        }

    }
}
