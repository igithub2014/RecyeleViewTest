package com.atom.recyeleview.recyeleviewtest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by User on 2016/4/11.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context ctx;
    private ArrayList<Person> list;

    public MyAdapter(Context mCtx, ArrayList<Person> mList){

        this.ctx = mCtx;
        this.list = mList;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView name,age;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            age = (TextView) itemView.findViewById(R.id.age);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ViewHolder holder;
        View v = LayoutInflater.from(ctx).inflate(R.layout.recyler_item,parent,false);
        holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.name.setText(list.get(position).getName());
        holder.age.setText(list.get(position).getAge()+"");

        // 设置点击事件回调
        if(listener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    listener.onItemClick(holder.itemView, position);

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    /**
     *  创建recyclerView 事件点击接口
     */
    public interface OnRecyclerViewItemOnClickListener{

        void onItemClick(View view, int list );
    }
    // 定义点击事件接口对象 listener
    private OnRecyclerViewItemOnClickListener listener;
    //  接口设置set方法
    public void setListener(OnRecyclerViewItemOnClickListener listener) {

        this.listener = listener;
    }
}
