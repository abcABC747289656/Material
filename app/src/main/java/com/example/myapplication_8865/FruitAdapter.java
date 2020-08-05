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

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private Context mContext;
    private List<Fruit> mFruitList;
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
    public FruitAdapter(List<Fruit> fruitList){
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
               Fruit fruit=mFruitList.get(position);
               if(fruit.getName().equals("java环境变量win10")){
                   Intent intent=new Intent(mContext,FuritActivity1.class);
                   intent.putExtra(FuritActivity1.FRUIT_NAME,fruit.getName());
                   intent.putExtra(FuritActivity1.FRUIT_IMAGE_ID,fruit.getImageId());
                   intent.putExtra("Uri","https://top1-video-public.cdn.bcebos.com/97bfefaef0024885681ac693c58b0e901e881bf6.mp4");
                   intent.putExtra("name", fruit.getName());
                   mContext.startActivity(intent);
               }else if(fruit.getName().equals("eclipse安装教程")){
                   Intent intent=new Intent(mContext,FuritActivity1.class);
                   intent.putExtra(FruitActivity.FRUIT_NAME,fruit.getName());
                   intent.putExtra(FruitActivity.FRUIT_IMAGE_ID,fruit.getImageId());
                   intent.putExtra("Uri","https://top1-video-public.cdn.bcebos.com/d2d0c13849f70fd626249c3ce8c4f465c7acb739.mp4");
                   intent.putExtra("name", fruit.getName());
                   mContext.startActivity(intent);
               }else if(fruit.getName().equals("MySQL下载与安装教程视频")){
                   Intent intent=new Intent(mContext,FuritActivity1.class);
                   intent.putExtra(FruitActivity.FRUIT_NAME,fruit.getName());
                   intent.putExtra(FruitActivity.FRUIT_IMAGE_ID,fruit.getImageId());
                   intent.putExtra("Uri","https://top1-video-public.cdn.bcebos.com/cd6f31ff79622d4eb09609100a4b9960f26832b6.mp4");
                   intent.putExtra("name", fruit.getName());
                   mContext.startActivity(intent);
               }
           }
       });
       return holder;
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        Fruit fruit=mFruitList.get(position);
        holder.fruitName.setText(fruit.getName());
        Glide.with(mContext).load(fruit.getImageId()).into(holder.fruitImage);
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }
}
