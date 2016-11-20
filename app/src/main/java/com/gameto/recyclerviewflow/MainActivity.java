package com.gameto.recyclerviewflow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    private ArrayList<String> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initView(){
        recyclerView = (RecyclerView)findViewById(R.id.rv_test);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2 , StaggeredGridLayoutManager.VERTICAL);
//        //设置瀑布流布局
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
//        //设置适配器
        recyclerView.setAdapter(new RecyclerAdapter(mData));
        //添加分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this));

    }

    private void initData(){
        mData = new ArrayList<>();
        //初始化数据
        for(int i = 0 ; i<=20 ; i++){
            mData.add("test" + i);
        }
    }

}
