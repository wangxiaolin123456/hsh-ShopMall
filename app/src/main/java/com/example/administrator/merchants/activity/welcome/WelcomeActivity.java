package com.example.administrator.merchants.activity.welcome;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.activity.LoginActivity;
import com.example.administrator.merchants.activity.test.MainActivity;
import com.example.administrator.merchants.common.CommonUtil;
import com.example.administrator.merchants.common.UserInfo;

import cn.jpush.android.api.JPushInterface;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：引导页
 */
public class WelcomeActivity extends Activity {

    private SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page4);
        sh = CommonUtil.setSharedPreferences(WelcomeActivity.this);
        if (sh.getBoolean(CommonUtil.userInfoName, false)) {
            if (UserInfo.getInstance().isUserLogin(this)) {//已经登录
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            } else {//未登录
                startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
            }
            finish();
        } else {//第一次进入app开启引导页
            ImageView iv_start = (ImageView) findViewById(R.id.iv_start);
            iv_start.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                    SharedPreferences.Editor editor = sh.edit();
                    editor.putBoolean(CommonUtil.userInfoName, true);
                    editor.putString("id", "null");
                    editor.commit();
                    finish();
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(WelcomeActivity.this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(WelcomeActivity.this);
    }
}

