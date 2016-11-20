package com.gameto.recyclerviewflow;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Administrator on 2016/11/20.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{

    private ArrayList<String> mData;

    public RecyclerAdapter(ArrayList<String> mData) {
        this.mData = mData;
    }

    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.test_item , parent ,false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.MyViewHolder holder, int position) {
        holder.tv_tests.setText(mData.get(position));
        Random r = new Random();
        holder.tv_tests.getLayoutParams().height = 300;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView tv_tests;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_tests = (TextView) itemView.findViewById(R.id.tv_test);
        }
    }

}
