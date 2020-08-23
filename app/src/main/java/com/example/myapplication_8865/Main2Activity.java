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
import android.widget.Toast;

import com.example.mylibrary.ListItem;
import com.example.mylibrary.Save;
import com.example.mylibrary.ShiPin;
import com.example.mylibrary.ShiPinShouYe;
import com.example.mylibrary.ShiPinWeiZhi;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main2Activity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private List<ListItem> fruitList=new ArrayList<ListItem>();
    private FruitAdapter adapter;
    private MaterialSearchView searchView;
    //CunChu cunChu = new CunChu();
    //Save save = new Save();
    //Fruit[]fruits = cunChu.fruits;
    //ListItem[] listItems = save.listItems;
    ListItem[] listItems = new ListItem[10];
    ShiPinWeiZhi shiPinWeiZhi = new ShiPinWeiZhi();
    ShiPin shiPin = new ShiPin();
    String[] shipin = shiPin.shipinbofang;
    //String[] shipin = new String[10];
    //ShiPinShouYe shiPinShouYe = new ShiPinShouYe();
    //String[] shipinshouye = shiPinShouYe.shipinsouye;
    String[] shipinshouye = new String[10];
    String[] name = new String[10];
    int e = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout=findViewById(R.id.drawer_layout);
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager=new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);
        initFruits();
        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setVoiceSearch(false);
        searchView.setCursorDrawable(R.drawable.color_cursor_white);
        searchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                /*Snackbar.make(findViewById(R.id.container), "Query: " + query, Snackbar.LENGTH_LONG)
                        .show();*/
                Integer  position;
                //shiPinWeiZhi.ChooseWeiZhi = query;
                position = shiPinWeiZhi.WeiZhiXuanZhe(query);
                //if (query.equals("Java环境变量配置")) {
                if (position != null){
                    Toast.makeText(Main2Activity.this, "搜索成功！", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(Main2Activity.this,FuritActivity1.class);
                    intent.putExtra("Uri",shipin[position]);
                    intent.putExtra("name", query);
                    intent.putExtra(FruitActivity.FRUIT_NAME,query);
                    intent.putExtra(FruitActivity.FRUIT_IMAGE_ID,shipinshouye[position]);
                    intent.putExtra("UriTuPian",shipinshouye[position]);
                    startActivity(intent);
                }else{
                    Toast.makeText(Main2Activity.this, "抱歉没有找到相关内容，如有需要可联系客服解决！", Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic

            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });
    }
    private void initFruits(){
        fruitList.clear();
        Num();
        int i;
        for (i=0;i<listItems.length;i++) {
            fruitList.add(listItems[i]);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);


        return true;
    }

    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches != null && matches.size() > 0) {
                String searchWrd = matches.get(0);
                if (!TextUtils.isEmpty(searchWrd)) {
                    searchView.setQuery(searchWrd, false);
                }
            }

            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void JieShou(){
        Intent intent = getIntent();
        Bundle b = this.getIntent().getExtras();
        name = b.getStringArray("AAA");
    }
    public void JieShou1(){
        Intent intent = getIntent();
        Bundle b = this.getIntent().getExtras();
        shipinshouye = b.getStringArray("BBB");
    }
    public void Num(){
        JieShou();
        JieShou1();
        while (e<10){
            listItems[e] = new ListItem(name[e], shipinshouye[e]);
            e++;
        }
    }
}
