package com.example.myapplication_8865;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myapplication_8865.view.BigImgActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class FruitActivity extends AppCompatActivity{
public static final String FRUIT_NAME="fruit_name";
public static final String FRUIT_IMAGE_ID="fruit_image_id";
    JCVideoPlayerStandard jcVideoPlayerStandard;
    private Banner banner;
    public Banner banner1;
    public Banner banner2;
    public Banner banner3;
    public Banner banner4;
    public FloatingActionButton FB;
    private ArrayList<String> list_path;
    private ArrayList<String> list_path1;
    private ArrayList<String> list_path2;
    private ArrayList<String> list_path3;
    private ArrayList<String> list_path4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);
        Intent intent=getIntent();
        String fruitName=intent.getStringExtra(FRUIT_NAME);
        int fruitImageId=intent.getIntExtra(FRUIT_IMAGE_ID,0);
        Toolbar toolbar=findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar=findViewById(R.id.collapsing_toolbar);
        ImageView fruitImageView=findViewById(R.id.fruit_image_view);
        FB = findViewById(R.id.FB);
        FB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNormalDialog();
            }
        });
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle(fruitName);
        //collapsingToolbar.setContentScrimColor(Color.RED);
        collapsingToolbar.setExpandedTitleColor(Color.BLACK);
        Glide.with(this).load(fruitImageId).into(fruitImageView);
        JcVideoPlayerStandard();
        initView();
        initData();
        initListener();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
    private void initView() {
        banner = findViewById(R.id.banner);
        banner1 = findViewById(R.id.banner1);
        banner2 = findViewById(R.id.banner2);
        banner3 = findViewById(R.id.banner3);
        banner4 = findViewById(R.id.banner4);
    }

    private void initData() {
        setBanner();//设置轮播图
    }

    private void initListener() {

    }
    public void JcVideoPlayerStandard(){
        jcVideoPlayerStandard = (JCVideoPlayerStandard) findViewById(R.id.jc_video);
        jcVideoPlayerStandard.setUp("https://top1-video-public.cdn." +
                "bcebos.com/97bfefaef0024885681ac693c58b0e901e881bf6.mp4", JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "我的视频");
        jcVideoPlayerStandard.thumbImageView.setImageResource(R.mipmap.java);
    }

    /**
     * 设置轮播图
     */
    private void setBanner() {
        //放图片地址的集合
        //设置图片数据
        Add();
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        SetBannerStyle();
        //设置图片加载器，图片加载器在下方
        SetImageLoader();
        //设置图片网址或地址的集合
        SetImages();
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        SetBannerAnimation();
        //设置轮播间隔时间
        /*banner.setDelayTime(3000);
        banner1.setDelayTime(3000);
        banner2.setDelayTime(3000);*/
        //设置是否为自动轮播，默认是“是”
        IsAutoPlay();
        //设置指示器的位置，小点点，左中右。
        Start();
    }
    //自定义的图片加载器
    private class ImgLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).into(imageView);
        }
    }
    //设置内置样式，共有六种可以点入方法内逐一体验使用。
    private void SetBannerStyle(){
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        banner1.setBannerStyle(BannerConfig.NUM_INDICATOR);
        banner2.setBannerStyle(BannerConfig.NUM_INDICATOR);
        banner3.setBannerStyle(BannerConfig.NUM_INDICATOR);
        banner4.setBannerStyle(BannerConfig.NUM_INDICATOR);
    }
    //设置图片加载器，图片加载器在下方
    public void SetImageLoader(){
        banner.setImageLoader(new ImgLoader());
        banner1.setImageLoader(new ImgLoader());
        banner2.setImageLoader(new ImgLoader());
        banner3.setImageLoader(new ImgLoader());
        banner4.setImageLoader(new ImgLoader());
    }
    //设置图片网址或地址的集合
    public void SetImages(){
        banner.setImages(list_path);
        banner1.setImages(list_path1);
        banner2.setImages(list_path2);
        banner3.setImages(list_path3);
        banner4.setImages(list_path4);
    }
    //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
    public void SetBannerAnimation(){
        banner.setBannerAnimation(Transformer.Default);
        banner1.setBannerAnimation(Transformer.Default);
        banner2.setBannerAnimation(Transformer.Default);
        banner3.setBannerAnimation(Transformer.Default);
        banner4.setBannerAnimation(Transformer.Default);
    }
    public void Add(){
        //放图片地址的集合
        list_path = new ArrayList<>();
        list_path1 = new ArrayList<>();
        list_path2 = new ArrayList<>();
        list_path3 = new ArrayList<>();
        list_path4 = new ArrayList<>();
        //设置图片数据
        list_path.add("https://images2015.cnblogs.com/blog/805379/201605/805379-20160512222932249-1178980125.png");
        list_path1.add("https://images2015.cnblogs.com/blog/805379/201605/805379-20160512223219655-1251440251.png");
        list_path2.add("https://images2015.cnblogs.com/blog/805379/201605/805379-20160512223357046-1881703129.png");
        list_path3.add("https://images2015.cnblogs.com/blog/805379/201605/805379-20160512223611077-1852208980.png");
        list_path3.add("https://images2015.cnblogs.com/blog/805379/201605/805379-20160512223838546-1858485630.png");
        list_path3.add("https://images2015.cnblogs.com/blog/805379/201605/805379-20160512224107702-1187773252.png");
        list_path3.add("https://images2015.cnblogs.com/blog/805379/201605/805379-20160512224230452-538115428.png");
        list_path3.add("https://images2015.cnblogs.com/blog/805379/201605/805379-20160512224443687-1268631944.png");
        list_path3.add("https://images2015.cnblogs.com/blog/805379/201605/805379-20160512224843484-1356351538.png");
        list_path4.add("https://images2015.cnblogs.com/blog/805379/201605/805379-20160512225105562-1578800234.png");
        list_path4.add("https://images2015.cnblogs.com/blog/805379/201605/805379-20160512225343452-1007903408.png");
        list_path4.add("https://images2015.cnblogs.com/blog/805379/201605/805379-20160512225205765-304825305.png");
        list_path4.add("https://images2015.cnblogs.com/blog/805379/201605/805379-20160512225445859-652093728.png");
    }
    public void Start(){
        banner.setIndicatorGravity(BannerConfig.CENTER)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Intent intent = new Intent(FruitActivity.this, BigImgActivity.class);
                        intent.putStringArrayListExtra("imgData", list_path);
                        //intent.putStringArrayListExtra("imgData1", list_path1);
                        intent.putExtra("clickPosition", position);
                        //intent.putExtra("clickPosition1", position);
                        startActivity(intent);
                    }
                })
                //必须最后调用的方法，启动轮播图。
                .start();
        banner1.setIndicatorGravity(BannerConfig.CENTER)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Intent intent = new Intent(FruitActivity.this, BigImgActivity.class);
                        intent.putStringArrayListExtra("imgData", list_path1);
                        //intent.putStringArrayListExtra("imgData1", list_path1);
                        intent.putExtra("clickPosition", position);
                        //intent.putExtra("clickPosition1", position);
                        startActivity(intent);
                    }
                })
                //必须最后调用的方法，启动轮播图。
                .start();
        banner2.setIndicatorGravity(BannerConfig.CENTER)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Intent intent = new Intent(FruitActivity.this, BigImgActivity.class);
                        intent.putStringArrayListExtra("imgData", list_path2);
                        //intent.putStringArrayListExtra("imgData1", list_path1);
                        intent.putExtra("clickPosition", position);
                        //intent.putExtra("clickPosition1", position);
                        startActivity(intent);
                    }
                }).start();
        banner3.setIndicatorGravity(BannerConfig.CENTER)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Intent intent = new Intent(FruitActivity.this, BigImgActivity.class);
                        intent.putStringArrayListExtra("imgData", list_path3);
                        //intent.putStringArrayListExtra("imgData1", list_path1);
                        intent.putExtra("clickPosition", position);
                        //intent.putExtra("clickPosition1", position);
                        startActivity(intent);
                    }
                }).start();
        banner4.setIndicatorGravity(BannerConfig.CENTER)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Intent intent = new Intent(FruitActivity.this, BigImgActivity.class);
                        intent.putStringArrayListExtra("imgData", list_path4);
                        //intent.putStringArrayListExtra("imgData1", list_path1);
                        intent.putExtra("clickPosition", position);
                        //intent.putExtra("clickPosition1", position);
                        startActivity(intent);
                    }
                }).start();
    }
    public void IsAutoPlay(){
        banner.isAutoPlay(false);
        banner1.isAutoPlay(false);
        banner2.isAutoPlay(false);
        banner3.isAutoPlay(false);
        banner4.isAutoPlay(false);
    }
    private void showNormalDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FruitActivity.this);
        //builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("如果本方法还未能解决您的问题：");
        builder.setMessage("请您及时地给客服留言哦！感谢您的使用！");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        //    显示出该对话框
        builder.show();
    }
}
