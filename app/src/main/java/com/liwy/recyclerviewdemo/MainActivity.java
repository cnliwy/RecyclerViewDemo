package com.liwy.recyclerviewdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class MainActivity extends Activity implements View.OnClickListener{
    private RecyclerView recyclerView;
    private List<Data> list;
    private RecyclerAdapter recyclerAdapter;
    private Context context;

    //按钮
    private Button btnList;
    private Button btnGrid;
    private Button btnStaggered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void initView(){
        context = this;
        btnList = (Button)findViewById(R.id.btn_list);
        btnGrid = (Button)findViewById(R.id.btn_grid);
        btnStaggered = (Button)findViewById(R.id.btn_staggered);
        btnList.setOnClickListener(this);
        btnGrid.setOnClickListener(this);
        btnStaggered.setOnClickListener(this);
        initData();
        recyclerView = (RecyclerView)findViewById(R.id.recycler_main);
        recyclerAdapter = new RecyclerAdapter(list,context);
        /**
         * 设置item的点击事件
         */
        recyclerAdapter.setOnItemClickListener(new OnRecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                showToastShort(list.get(position).getContent());
            }

            @Override
            public void onLongClick(View view, int position) {
                showToastShort("删除了" + list.get(position).getContent());
                // 长点击删除
                recyclerAdapter.notifyItemRemoved(position);
                Log.e(TAG, "删除后的长度=" + recyclerAdapter.getItemCount());
            }
        });
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        /**
         * 设置布局管理器，必须设置,否则数据不显示
         */
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_list:
                // 设置RecyclerView的风格为列表样式,类似于ListView
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.btn_grid:
                // 设置Recycler的风格为网格样式,类似于GridView
                recyclerView.setLayoutManager(new GridLayoutManager(this,4));
                break;
            case R.id.btn_staggered:
                // 设置Recycler的风格为瀑布流样式
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));
                break;
        }
    }

    /**
     * 吐司方法short
     */
    public void showToastShort(String content){
        Toast.makeText(context,content,Toast.LENGTH_SHORT).show();
    }
    /**
     * 吐司方法long
     */
    public void showToastLong(String content){
        Toast.makeText(context,content,Toast.LENGTH_LONG).show();
    }

    public void initData(){
        list = new ArrayList<Data>();
        for (int i = 0; i < 20; i++){
            Data d = new Data();
            d.setName("李大嘴他说:" + i);
            d.setContent("李大嘴" + i);
            list.add(d);
        }
    }
}
