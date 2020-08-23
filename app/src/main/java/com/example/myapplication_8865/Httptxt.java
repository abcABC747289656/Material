package com.example.myapplication_8865;

import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Httptxt {
    public  String openFile(int numbers, String filePath) {
        int HttpResult; // 服务器返回的状态
        String ee = new String();
        try
        {
            URL url =new URL(filePath); // 创建URL
            URLConnection urlconn = url.openConnection(); // 试图连接并取得返回状态码
            urlconn.connect();
            HttpURLConnection httpconn =(HttpURLConnection)urlconn;
            HttpResult = httpconn.getResponseCode();
            if(HttpResult != HttpURLConnection.HTTP_OK) {
                //System.out.print("无法连接到");
            } else {
                int number = 0;
                int filesize = urlconn.getContentLength(); // 取数据长度
                InputStreamReader isReader = new InputStreamReader(urlconn.getInputStream(),"UTF-8");
                BufferedReader reader = new BufferedReader(isReader);
                StringBuffer buffer = new StringBuffer();
                String line; // 用来保存每行读取的内容
                //Log.d("N", String.valueOf(filesize));
                line = reader.readLine(); // 读取第一行
                //buffer.append(line);
                //buffer.append(line);
                /*while (line != null) {// 如果 line 为空说明读完了
                    line = reader.readLine();
                    buffer.append(line); // 将读到的内容添加到 buffer 中
                    buffer.append("\n"); // 添加换行符
                    //number++;
                    //line = reader.readLine(); // 读取下一行
                }*/
                while (line != null){
                    number++;
                    if (number==numbers){
                        buffer.append(line);
                    }
                    line = reader.readLine();
                }

                //buffer.append(line);
                ee = buffer.toString();
                //Log.d("N", String.valueOf(number));
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return  ee;
    }
}
