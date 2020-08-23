package com.example.myapplication_8865;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mylibrary.ListItem;
import com.example.mylibrary.Save;
import com.example.mylibrary.ShiPin;
import com.example.mylibrary.ShiPinShouYe;
import com.example.mylibrary.ShiPinWeiZhi;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button JinRu;
    String hhh;
    String aaa;
    Httptxt httptxt = new Httptxt();
    ListItem[] listItems = new ListItem[10];
    String[] Name = new String[10];
    String[] Uri = new String[10];
    int a = 1;
    int c = 0;
    int b = 1;
    int d = 0;
    int e = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendRequestWithOkHttp();
        sendRequestWithOkHttp1();
        JinRu = findViewById(R.id.JinRu);
        JinRu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Num();
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                Bundle b = new Bundle();
                b.putStringArray("AAA",Name);
                b.putStringArray("BBB",Uri);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }
    public void Num(){
        while (e<10){
            listItems[e] = new ListItem(Name[e], Uri[e]);
            e++;
        }
        //responseText.setText(""+listItems[1].getName());
    }
    public void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (a<11) {
                        hhh = httptxt.openFile(a, "https://cdn.jsdelivr.net/gh/huanghaozi/Storage4App@master/20200821/20200821053513300515e558fd5b503a7fbbe08e754e5f.txt");
                        //responseText.setText(hhh);
                        //Log.d("PPPP",hhh);
                        //responseText.setText(hhh);
                        a++;
                        Name[c] = hhh;
                        c++;
                        //Log.d("qqq", hhh);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public void sendRequestWithOkHttp1() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (b<11) {
                        aaa = httptxt.openFile(b, "https://cdn.jsdelivr.net/gh/huanghaozi/Storage4App@master/20200823/20200823030457a830da3b0f66f4415af6b2e08074570a.txt");
                        //Log.d("sss",aaa);
                        b++;
                        Uri[d] = aaa;
                        d++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
