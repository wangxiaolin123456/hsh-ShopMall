package com.example.administrator.merchants.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.merchants.R;

import butterknife.ButterKnife;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：BaseActivity
 */
public abstract class BaseActivity extends Activity {
    protected Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
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
    public void setTitle(int id) {
        TextView textView = (TextView) findViewById(R.id.head_title);
        if (textView != null) {
            textView.setText(id);
        }
    }

    /**
     * 设置标题文字设置
     *
     * @param titles
     */
    public void setTitles(String titles) {
        TextView textView = (TextView) findViewById(R.id.head_title);
        if (textView != null) {
            textView.setText(titles);
        }
    }
}
