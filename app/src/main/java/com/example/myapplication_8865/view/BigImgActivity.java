package com.example.myapplication_8865.view;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


import com.example.myapplication_8865.R;
import com.example.myapplication_8865.adapter.PhotoPagerAdapter;
import com.example.myapplication_8865.view.ViewPagerFixed;

import java.util.ArrayList;

public class BigImgActivity extends AppCompatActivity {
    private ViewPagerFixed viewPager;
    private TextView tvNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_img);
        initView();
    }

    private void initView() {
        viewPager = findViewById(R.id.viewpager);
        tvNum = findViewById(R.id.tv_num);

        //接收图片数据及位置
        final ArrayList<String> imgData = getIntent().getStringArrayListExtra("imgData");
        int clickPosition = getIntent().getIntExtra("clickPosition", 0);

        //添加适配器
        PhotoPagerAdapter viewPagerAdapter = new PhotoPagerAdapter(getSupportFragmentManager(), imgData);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(clickPosition);//设置选中图片位置

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                tvNum.setText(String.valueOf(position + 1) + "/" + imgData.size());
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
