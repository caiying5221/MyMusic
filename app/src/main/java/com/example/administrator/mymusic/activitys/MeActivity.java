package com.example.administrator.mymusic.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.mymusic.R;
import com.example.administrator.mymusic.utils.UserUtils;

public class MeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        initView();
    }

    private void initView(){
        initNavBar(true, "个人中心", false);
    }

    /**
     * 修改密码点击事件
     */
    public void onChangeClick(View view){
        startActivity(new Intent(this, ChangePasswordActivity.class));
    }

    /**
     * 退出登录点击事件
     */
    public void onLogoutClick(View view){
        UserUtils.logout(this);
    }
}
