package com.example.myapplication_8865;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mylibrary.ListItem;
import com.example.mylibrary.ShiPin;
import com.example.mylibrary.ShiPinShouYe;
import com.example.mylibrary.TuPian;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private Context mContext;
    private List<ListItem> mFruitList;
    ShiPin shiPin = new ShiPin();
    String[] shipin = shiPin.shipinbofang;
    ShiPinShouYe shiPinShouYe = new ShiPinShouYe();
    String[] shipinshouye = shiPinShouYe.shipinsouye;
    Httptxt httptxt1 = new Httptxt();
    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView fruitImage;
        TextView fruitName;
        public ViewHolder(View view){
            super(view);
            cardView=(CardView) view;
            fruitImage=(ImageView)view.findViewById(R.id.fruit_image);
            fruitName=(TextView)view.findViewById(R.id.fruit_name);

        }
    }
    public FruitAdapter(List<ListItem> fruitList){
        mFruitList=fruitList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        if (mContext==null){
            mContext=parent.getContext();
        }
        View view= LayoutInflater.from(mContext).inflate(R.layout.fruit_item,parent,false);
       final ViewHolder holder=new ViewHolder(view);
       holder.cardView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               int position=holder.getAdapterPosition();
               /*ListItem listItem=mFruitList.get(position);
               //if(fruit.getName().equals("java环境变量win10")){
                   Intent intent=new Intent(mContext,FuritActivity1.class);
                   intent.putExtra(FuritActivity1.FRUIT_NAME,listItem.getName());
                   intent.putExtra(FuritActivity1.FRUIT_IMAGE_ID,listItem.getImageId());
                   intent.putExtra("Uri",shipin[position]);
                   intent.putExtra("name", listItem.getName());
                   intent.putExtra("UriTuPian",shipinshouye[position]);
                   mContext.startActivity(intent);*/
               sendRequestWithOkHttp(position);
               /*}else if(fruit.getName().equals("eclipse安装教程")){
                   Intent intent=new Intent(mContext,FuritActivity1.class);
                   intent.putExtra(FruitActivity.FRUIT_NAME,fruit.getName());
                   intent.putExtra(FruitActivity.FRUIT_IMAGE_ID,fruit.getImageId());
                   intent.putExtra("Uri",shipin[position]);
                   intent.putExtra("name", fruit.getName());
                   mContext.startActivity(intent);
               }else if(fruit.getName().equals("MySQL下载与安装教程视频")){
                   Intent intent=new Intent(mContext,FuritActivity1.class);
                   intent.putExtra(FruitActivity.FRUIT_NAME,fruit.getName());
                   intent.putExtra(FruitActivity.FRUIT_IMAGE_ID,fruit.getImageId());
                   intent.putExtra("Uri",shipin[position]);
                   intent.putExtra("name", fruit.getName());
                   mContext.startActivity(intent);
               }*/
           }
       });
       return holder;
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        ListItem fruit=mFruitList.get(position);
        holder.fruitName.setText(fruit.getName());
        Glide.with(mContext).load(fruit.getImageId()).into(holder.fruitImage);
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }
    public void sendRequestWithOkHttp(final int num) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String Uri = httptxt1.openFile(num+1,"https://cdn.jsdelivr.net/gh/huanghaozi/Storage4App@master/20200820/2020082005193071e67f94107cf72a5f8599fbe745c904.txt");
                    ListItem listItem=mFruitList.get(num);
                    Intent intent=new Intent(mContext,FuritActivity1.class);
                    intent.putExtra(FuritActivity1.FRUIT_NAME,listItem.getName());
                    intent.putExtra(FuritActivity1.FRUIT_IMAGE_ID,listItem.getImageId());
                    intent.putExtra("Uri",Uri);
                    intent.putExtra("name", listItem.getName());
                    intent.putExtra("UriTuPian",shipinshouye[num]);
                    mContext.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
