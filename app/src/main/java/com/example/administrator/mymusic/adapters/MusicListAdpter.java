package com.example.administrator.mymusic.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.mymusic.R;
import com.example.administrator.mymusic.activitys.PlayMusicActivity;

public class MusicListAdpter extends RecyclerView.Adapter<MusicListAdpter.ViewHolder> {

    private Context mContext;
    private View mItemView;
    private RecyclerView mRv;
    private boolean isCalcaulationRyVHeight;

    public MusicListAdpter (Context context, RecyclerView recyclerView) {
        mContext = context;
        mRv = recyclerView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        mItemView = LayoutInflater.from(mContext).inflate(R.layout.item_list_music, viewGroup, false);
        return new ViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        setRecyclerViewHeight();

//        Glide.with(mContext)
//                .load("http://res.lgdsunday.clud/poster-1.png")
//                .into(viewHolder.ivIcon);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PlayMusicActivity.class);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 8;
    }

    /**
     * 1、获取ItemView的高度
     * 2、ItemView的数量
     * 3、使用ItemViewHeight * ItemViewNum = RecyclerViewHeight
     */
    private void setRecyclerViewHeight () {
        if (isCalcaulationRyVHeight || mRv == null) return;
        isCalcaulationRyVHeight = true;
//        获取ItemView的高度
        RecyclerView.LayoutParams itemViewLp = (RecyclerView.LayoutParams) mItemView.getLayoutParams();
//        获取itemView的数量
        int itemCount = getItemCount();
//        使用ItemViewHeight * ItemViewNum = RecyclerViewHeight
        int recyclerViewHeight = itemViewLp.height * itemCount;
//        设置RecyclerView高度
        LinearLayout.LayoutParams rvLp = (LinearLayout.LayoutParams) mRv.getLayoutParams();
        rvLp.height = recyclerViewHeight;
        mRv.setLayoutParams(rvLp);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        View itemView;
        ImageView ivIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            ivIcon = itemView.findViewById(R.id.iv_icon);
        }
    }
}
