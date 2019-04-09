package com.example.administrator.merchants.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseActivity;
import com.example.administrator.merchants.http.Http;

/**
 * 作者：韩宇 on 2017/6/23 0023 14:39
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：互实攻略
 */
public class HSHStrategyActivity extends BaseActivity {
    private TextView strategyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hsh_strategy);
        setTitles("互实攻略");
        strategyText = (TextView) findViewById(R.id.text);
        Http.setStrategyText(HSHStrategyActivity.this, strategyText);

    }
    @Override
    protected void onStop() {
        super.onStop();
        MutualApplication.getRequestQueue().cancelAll("setStrategyText39");
    }
}
