package com.example.administrator.mymusic.views;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.mymusic.R;
import com.example.administrator.mymusic.helps.MediaPlayerHelp;

public class PlayMusicView extends FrameLayout {

    private Context mContext;
    private MediaPlayerHelp mMediaPlayerHelp;
    private boolean isPlaying;
    private String mPath;
    private View mView;
    private ImageView mIvIcon, mINeedle, mIvplay;
    private Animation mPlayMusicAnim, mPlayNeedleAnim, mStopNeedleAnim;
    private FrameLayout mFlPlayMusic;


    public PlayMusicView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init (Context context) {
        mContext = context;

        mView = LayoutInflater.from(mContext).inflate(R.layout.play_music, this, false);

        mFlPlayMusic = mView.findViewById(R.id.fl_play_music);
        mFlPlayMusic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                trigger();
            }
        });
        mIvIcon = mView.findViewById(R.id.iv_icon);
        mINeedle = mView.findViewById(R.id.iv_needle);
        mIvplay = mView.findViewById(R.id.iv_play);

        /**
         * 1、定义所需要执行的动画
         *      1.光盘转动的动画
         *      2.指针指向光盘的动画
         *      3.指针离开光盘的动画
         * 2、startAnimation
         */
        mPlayMusicAnim = AnimationUtils.loadAnimation(mContext, R.anim.play_musicanim);
        mPlayNeedleAnim = AnimationUtils.loadAnimation(mContext, R.anim.play_needleanim);
        mStopNeedleAnim = AnimationUtils.loadAnimation(mContext, R.anim.stop_needle_anim);

        addView(mView);

        mMediaPlayerHelp = MediaPlayerHelp.getInstance(mContext);
    }

    /**
     * 切换播放状态
     */
    private void trigger () {
        if (isPlaying) {
            stopMusic();
        }else {
            playMusic(mPath);
        }
    }

    /**
     * 播放音乐
     */
    public void playMusic (String path) {
        mPath = path;
        isPlaying = true;
        mIvplay.setVisibility(View.GONE);
        mFlPlayMusic.startAnimation(mPlayMusicAnim);
        mINeedle.startAnimation(mPlayNeedleAnim);
        /**
         * 1、判断当前音乐是否是已经播放的音乐
         * 2、如果当前的音乐是已经在播放的音乐播放，那么直接执行start方法
         * 3、如果当前的音乐不是需要播放的音乐播放，那么就调用setPath的方法
         */
        if (mMediaPlayerHelp.getPath() != null && mMediaPlayerHelp.getPath().equals(path)) {
            mMediaPlayerHelp.start();
        }else {
            mMediaPlayerHelp.setPath(path);
            mMediaPlayerHelp.setOnMeidaPlayerHelperListener(new MediaPlayerHelp.OnMeidaPlayerHelperListener() {
                @Override
                public void onPerpared(MediaPlayer mp) {
                    mMediaPlayerHelp.start();
                }
            });
        }


    }

    /**
     * 停止音乐
     */
    public void stopMusic () {
        isPlaying = false;
        mIvplay.setVisibility(View.VISIBLE);
        mFlPlayMusic.clearAnimation();
        mINeedle.startAnimation(mStopNeedleAnim);
        mMediaPlayerHelp.pause();
    }

    /**
     * 设置光盘中显示的音乐封面图片
     */
    public void setMusicIcon (String icon) {
        Glide.with(mContext)
                .load(icon)
                .into(mIvIcon);
    }
}
