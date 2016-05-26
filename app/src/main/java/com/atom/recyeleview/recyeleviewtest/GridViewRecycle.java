package com.atom.recyeleview.recyeleviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class GridViewRecycle extends AppCompatActivity {

    private RecyclerView grid_recycle_view;
    private GridLayoutManager gridLayoutManager;
    private List<HashMap<String, Object>> myList = new ArrayList<HashMap<String, Object>>();
    private GridAdapter adapter;
    private String testUrl = "http://www.baidu.com/img/bd_logo1.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_recycle);
        // 获取id
        grid_recycle_view = (RecyclerView) findViewById(R.id.grid_recycle_view);
        // 初始化布局管理器
        gridLayoutManager = new GridLayoutManager(this, 4);
        // 添加布局管理器到recycleView中
        grid_recycle_view.setLayoutManager(gridLayoutManager);
        // 第二种方式添加
//        grid_recycle_view.setLayoutManager(new StaggeredGridLayoutManager(4,  StaggeredGridLayoutManager.VERTICAL));
        // 设置默认高度提高性能
        grid_recycle_view.setHasFixedSize(true);
        // 设置默认动画
        grid_recycle_view.setItemAnimator(new DefaultItemAnimator());
        // 设置下划线(recycleView 自身不具备下划线功能)
        grid_recycle_view.addItemDecoration(new DividerGridItemDecoration(this));
        // 初始化数据
        InitData();
        // 设置适配器
        adapter = new GridAdapter(this, (ArrayList<HashMap<String, Object>>) myList);
        grid_recycle_view.setAdapter(adapter);

    }

    private void InitData() {
        HashMap<String, Object> mapData = new HashMap<String, Object>();
        mapData.put("name", "张三");
        mapData.put("testUrl", testUrl);
        myList.add(mapData);

        mapData = new HashMap<String, Object>();
        mapData.put("name", "李四");
        mapData.put("testUrl", testUrl);
        myList.add(mapData);

        mapData = new HashMap<String, Object>();
        mapData.put("name", "王五");
        mapData.put("testUrl", testUrl);
        myList.add(mapData);

        mapData = new HashMap<String, Object>();
        mapData.put("name", "赵六");
        mapData.put("testUrl", testUrl);
        myList.add(mapData);

        mapData = new HashMap<String, Object>();
        mapData.put("name", "钱七");
        mapData.put("testUrl", testUrl);
        myList.add(mapData);

        mapData = new HashMap<String, Object>();
        mapData.put("name", "孙九");
        mapData.put("testUrl", testUrl);
        myList.add(mapData);

        mapData = new HashMap<String, Object>();
        mapData.put("name", "石十");
        mapData.put("testUrl", testUrl);
        myList.add(mapData);

        mapData = new HashMap<String, Object>();
        mapData.put("name", "石十");
        mapData.put("testUrl", testUrl);
        myList.add(mapData);

        mapData = new HashMap<String, Object>();
        mapData.put("name", "石十");
        mapData.put("testUrl", testUrl);
        myList.add(mapData);

        mapData = new HashMap<String, Object>();
        mapData.put("name", "石十");
        mapData.put("testUrl", testUrl);
        myList.add(mapData);

    }
}
