package com.example.administrator.merchants.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.merchants.R;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：fragment的activity继承的base
 */
public abstract class BaseFragmentActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 返回键的监听
     *
     * @param view
     */
    public void onBack(View view) {
        finish();
    }

    /**
     * 设置标题资源设置
     *
     * @param id
     */
    public void setTitle(int id) {//设置标题
        TextView textView = (TextView) findViewById(R.id.head_title);
        if (textView != null) {
            textView.setText(id);
        }
    }
}
