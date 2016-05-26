package com.atom.recyeleview.recyeleviewtest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private AbsListView.RecyclerListener listener;
    private RecyclerView recycler;
    // 定义默认recycleView item 布局
    private LinearLayoutManager layoutManager;
    // 创建adapter
    private MyAdapter adapter;
    // 创建数据列表
    private ArrayList<Person> list = new ArrayList<Person>();
    int lastVisibleItem ;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    adapter.notifyDataSetChanged();//提示数据适配器数据发生改变从而更新适配器里的数据
                    swipeRefreshLayout.setRefreshing(false);//隐藏刷新进度条
                    Toast.makeText(MainActivity.this, "刷新加载", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

    // 创建装饰类对象
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.jumpBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, GridViewRecycle.class);
                startActivity(it);
            }
        });

        // 初始化上拉加载下拉刷新
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        recycler = (RecyclerView) findViewById(R.id.recyclerView);

        swipeRefreshLayout.setColorSchemeColors(R.color.color_item_normal,
                R.color.color_item_press,
                R.color.color_item_test,
                R.color.color_item_test2);
        // 进度条设置(TypedValue。applyDimension用来设置标准尺寸)
        swipeRefreshLayout.setProgressViewOffset(false,
                0,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
        // 设置刷新监听
        swipeRefreshLayout.setOnRefreshListener(this);

        // 创建线性布局
        layoutManager = new LinearLayoutManager(this);
        // 给 recyclerViw 添加布局
        recycler.setLayoutManager(layoutManager);
        // 设置固定高度大小（默认知道item高度)以提高性能
        recycler.setHasFixedSize(true);
        // 默认设置item增加，删除动画
        recycler.setItemAnimator(new DefaultItemAnimator());
        // 添加下划线(0,表示横向,1表示纵向)
        recycler.addItemDecoration(new MyDecoration(this, 0));

        // 使用swipeRefleshLayout 自带进度条
        recycler.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == adapter.getItemCount()) { // 获取加载最后一个item 的position
                    // 设置刷新标志为true
                    swipeRefreshLayout.setRefreshing(true);
                    // 请求加载数据处理
                    list.add(new Person("李雷", 20));
                    list.add(new Person("韩梅梅", 18));
                    list.add(new Person("吉姆", 20));
                    list.add(new Person("露西", 20));
                    list.add(new Person("安口王", 20));
                    mHandler.sendEmptyMessageDelayed(0, 3000);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
            }
        });
        // 添加测试数据
        initData();
        adapter = new MyAdapter(this, list);
        recycler.setAdapter(adapter);

        adapter.setListener(new MyAdapter.OnRecyclerViewItemOnClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "你点击的是第" + position + "个item", Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * arrayList添加数据
     */
    private void initData() {
        list.add(new Person("张三", 10));
        list.add(new Person("李四", 12));
        list.add(new Person("王五", 6));
        list.add(new Person("赵六", 11));
        list.add(new Person("钱七", 10));
        list.add(new Person("马云", 50));
        list.add(new Person("乔布斯", 70));
        list.add(new Person("雷军", 40));
        list.add(new Person("李二", 12));
        list.add(new Person("小沈阳", 36));
        list.add(new Person("赵本山", 20));
    }

    @Override
    public void onRefresh() {

    }


}
