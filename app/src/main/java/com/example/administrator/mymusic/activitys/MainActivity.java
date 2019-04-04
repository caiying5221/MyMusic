package com.example.administrator.mymusic.activitys;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.mymusic.R;
import com.example.administrator.mymusic.adapters.MusicGridAdapter;
import com.example.administrator.mymusic.adapters.MusicListAdpter;
import com.example.administrator.mymusic.views.GridSpaceItemDecoration;

public class MainActivity extends BaseActivity  {

    private RecyclerView mRvGrid, mRvList;
    private MusicGridAdapter mGridAdapter;
    private MusicListAdpter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView(){
        initNavBar(false, "小蔡音乐", true);

        mRvGrid = fd(R.id.rv_grid);
        mRvGrid.setLayoutManager(new GridLayoutManager(this, 3));
        mRvGrid.addItemDecoration(new GridSpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.albumMarginSize), mRvGrid));
        mRvGrid.setNestedScrollingEnabled(false);
        mGridAdapter = new MusicGridAdapter(this);
        mRvGrid.setAdapter(mGridAdapter);

        /**
         * 1、假如已知高度列表的情况下、可以直接把布局中把RecyclerView的高度定义上
         * 2、不知道列表高度的情况下、需要手动计算RecyclerView的高度
         */
        mRvList = fd(R.id.rv_list);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        mRvList.setNestedScrollingEnabled(false);
        mListAdapter = new MusicListAdpter(this, mRvList);
        mRvList.setAdapter(mListAdapter);
    }
}
