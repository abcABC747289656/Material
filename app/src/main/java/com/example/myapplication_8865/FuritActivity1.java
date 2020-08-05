package com.example.myapplication_8865;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class FuritActivity1 extends AppCompatActivity implements OnBannerListener {
    public static final String FRUIT_NAME="fruit_name";
    public static final String FRUIT_IMAGE_ID="fruit_image_id";
    JCVideoPlayerStandard jcVideoPlayerStandard;
    private Banner banner;
    public FloatingActionButton FB;
    private ArrayList<String> list_path;
    private String Uri,Name;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit1);
        Intent intent=getIntent();
        String fruitName=intent.getStringExtra(FRUIT_NAME);
        int fruitImageId=intent.getIntExtra(FRUIT_IMAGE_ID,0);
        Toolbar toolbar=findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar=findViewById(R.id.collapsing_toolbar);
        ImageView fruitImageView=findViewById(R.id.fruit_image_view);
        //TextView fruitContentText=findViewById(R.id.fruit_content_text);
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
        Glide.with(this).load(fruitImageId).into(fruitImageView);
        collapsingToolbar.setExpandedTitleColor(Color.BLACK);
        //String fruitContent=generateFruitContent(fruitName);
        //fruitContentText.setText(fruitContent);
        jcVideoPlayerStandard = (JCVideoPlayerStandard) findViewById(R.id.jc_video);
        Uri = intent.getStringExtra("Uri");
        Name = intent.getStringExtra("name");
        jcVideoPlayerStandard.setUp(Uri, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, Name);
        jcVideoPlayerStandard.thumbImageView.setImageResource(fruitImageId);
        tv = findViewById(R.id.tv);
        tv.setText(Name);
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
    }

    /*private void initData() {
        setBanner();//设置轮播图
    }

    private void initListener() {

    }*/

    /**
     * 设置轮播图
     */
   /* private void setBanner() {
        //放图片地址的集合
        list_path = new ArrayList<>();
        //设置图片数据
        list_path.add("https://imgsa.baidu.com/exp/w=500/sign=8c463e1d0523dd542173a768e108b3df/4610b912c8fcc3ce84a88b4a9e45d688d43f205a.jpg");
        list_path.add("https://imgsa.baidu.com/exp/w=500/sign=dc49a3632434349b74066e85f9eb1521/7dd98d1001e93901d85bcf4777ec54e736d1966a.jpg");
        list_path.add("https://imgsa.baidu.com/exp/w=500/sign=b20d0fdda8c27d1ea5263bc42bd5adaf/f636afc379310a55d52b638dbb4543a9822610bf.jpg");
        list_path.add("https://sami-1256315447.picgz.myqcloud.com/article/201908/fb1721b8c9be4da9949fcdd26fc902a2.jpg");
        list_path.add("https://sami-1256315447.picgz.myqcloud.com/article/201908/08b58dde9b284638b44e2d03c4cb9acf.jpg");
        list_path.add("https://imgsa.baidu.com/exp/w=500/sign=8c463e1d0523dd542173a768e108b3df/4610b912c8fcc3ce84a88b4a9e45d688d43f205a.jpg");
        list_path.add("https://sami-1256315447.picgz.myqcloud.com/article/201908/9fd01c4add07473db31ba850f20a7232.jpg");
        list_path.add("https://imgsa.baidu.com/exp/w=500/sign=dc49a3632434349b74066e85f9eb1521/7dd98d1001e93901d85bcf4777ec54e736d1966a.jpg");

        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new FruitActivity1.ImgLoader());
        //设置图片网址或地址的集合
        banner.setImages(list_path);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        banner.setBannerAnimation(Transformer.Default);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”
        banner.isAutoPlay(false);
        //设置指示器的位置，小点点，左中右。
        banner.setIndicatorGravity(BannerConfig.CENTER)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(this)
                //必须最后调用的方法，启动轮播图。
                .start();
    }*/

    //轮播图的监听方法
    @Override
    public void OnBannerClick(int position) {
        Intent intent = new Intent(this, BigImgActivity.class);
        intent.putStringArrayListExtra("imgData", list_path);
        intent.putExtra("clickPosition", position);
        startActivity(intent);
    }

    //自定义的图片加载器
    private class ImgLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).into(imageView);
        }
    }
    private void showNormalDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FuritActivity1.this);
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
