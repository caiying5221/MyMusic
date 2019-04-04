package com.example.administrator.mymusic.activitys;

import android.os.Bundle;

import com.example.administrator.mymusic.R;

public class RegisterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    /**
     * 初始化View
     */
    private void initView(){
        initNavBar(true, "注册", false);
    }
}
