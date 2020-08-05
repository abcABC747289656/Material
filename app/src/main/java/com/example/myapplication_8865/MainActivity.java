package com.example.myapplication_8865;

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

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private DrawerLayout mDrawerLayout;
private List<Fruit>fruitList=new ArrayList<>();
private FruitAdapter adapter;
    private MaterialSearchView searchView;
    CunChu cunChu = new CunChu();
    Fruit[]fruits = cunChu.fruits;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout=findViewById(R.id.drawer_layout);
        initFruits();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager=new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);
        /*swipeReffresh =findViewById(R.id.swipr_refresh);
        //swipeReffresh.setColorSchemeResources(R.color.colorPrimary);
        swipeReffresh.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Task().execute();
            }
        });*/
        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setVoiceSearch(false);
        searchView.setCursorDrawable(R.drawable.color_cursor_white);
        searchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                /*Snackbar.make(findViewById(R.id.container), "Query: " + query, Snackbar.LENGTH_LONG)
                        .show();*/
                if (query.equals("Java环境变量配置")) {
                    Toast.makeText(MainActivity.this, "搜索成功！", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(MainActivity.this,FuritActivity1.class);
                    intent.putExtra("Uri","https://top1-video-public.cdn.bcebos.com/d2d0c13849f70fd626249c3ce8c4f465c7acb739.mp4");
                    intent.putExtra("name", query);
                    intent.putExtra(FruitActivity.FRUIT_NAME,query);
                    intent.putExtra(FruitActivity.FRUIT_IMAGE_ID,R.mipmap.java);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "抱歉没有找到相关内容，如有需要可联系客服解决！", Toast.LENGTH_LONG).show();
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
   /* private class Task extends AsyncTask<Void, Void, String[]> {
        @Override
        protected String[] doInBackground(Void... voids) {
            try {
                //执行了2秒的睡眠
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return new String[0];
        }
        @Override protected void onPostExecute(String[] result) {
            //刷新列表后，调用setdreshing ( false )。动画结束
            swipeReffresh.setRefreshing(false);
            super.onPostExecute(result);
            Toast.makeText(MainActivity.this,"HHHH",Toast.LENGTH_LONG).show();
        }
    }*/
    private void initFruits(){
        fruitList.clear();
        int i;
        for (i=0;i<fruits.length;i++) {
            fruitList.add(fruits[i]);
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
}
