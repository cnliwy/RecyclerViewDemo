package com.liwy.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by liwy on 16/9/25.
 */

public class RecyclerAdapter extends RecyclerView.Adapter {
    private String TAG = "RecycleViewAdapter";
    private List<Data> list;
    private Context context;
    private OnRecyclerViewClickListener mClickListener;

    public RecyclerAdapter(List<Data> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public RecyclerAdapter(List<Data> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler,parent,false);
        Log.e(TAG, "onCreateViewHolder: 创建一个item");
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Log.e(TAG, "onBindViewHolder: 绑定item数据");
        Data data = list.get(position);
        MyViewHolder myViewHolder = (MyViewHolder)holder;
        myViewHolder.position = position;
        myViewHolder.tv1.setText(data.getName());
        myViewHolder.tv2.setText(data.getContent());
        if (mClickListener != null) {
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mClickListener.onClick(view,position);
                }
            });
            myViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mClickListener.onLongClick(view,position);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemClickListener(OnRecyclerViewClickListener mClickListener) {
        this.mClickListener = mClickListener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView tv1;
        public TextView tv2;
        public int position;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv1 = (TextView)itemView.findViewById(R.id.textView);
            tv2 = (TextView)itemView.findViewById(R.id.textView2);
        }
    }
}
