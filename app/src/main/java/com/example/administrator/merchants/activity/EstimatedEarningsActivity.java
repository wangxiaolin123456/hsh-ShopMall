package com.example.administrator.merchants.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseActivity;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.post.StoreIdPostBean;

/**
 * 作者：韩宇 on 2017/6/23 0023 14:39
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：预估收益
 */
public class EstimatedEarningsActivity extends BaseActivity {
    private TextView estimatedEarnings;//预估收益
    private TextView estimatedEarningsMoney;//预估收益金额
    private TextView accumulatedIncome;//累计收益文字
    private TextView accumulatedIncomeMoney;//累计收益金额
    private TextView peopleNumber;//推荐人数
    private LinearLayout gotoEstimatedEarnings;//预估收益详情
    private TextView EstimatedEarningsDeails;//预估收益详情
    private TextView Monthly_income_statistics;//月收益统计

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimated_earnings);
        setTitles("预估收益");
        estimatedEarnings = (TextView) findViewById(R.id.Being_displayed);
        estimatedEarningsMoney = (TextView) findViewById(R.id.Being_displayed_money);//网路请求
        accumulatedIncome = (TextView) findViewById(R.id.Will_show);
        accumulatedIncomeMoney = (TextView) findViewById(R.id.Will_show_money);//网络请求
        peopleNumber = (TextView) findViewById(R.id.people_number);//网络请求
        gotoEstimatedEarnings = (LinearLayout) findViewById(R.id.goto_Estimated_earnings);//跳转预估收益
        EstimatedEarningsDeails = (TextView) findViewById(R.id.EstimatedEarningsDeails);
        Monthly_income_statistics = (TextView) findViewById(R.id.Monthly_income_statistics);
        EstimatedEarningsDeails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转预估收益详情
                startActivity(new Intent(EstimatedEarningsActivity.this, EstimatedEarningsDetailsActivity.class));
            }
        });
        Monthly_income_statistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转月收益统计
                startActivity(new Intent(EstimatedEarningsActivity.this, MonthIncomeActivity.class));
            }
        });

        estimatedEarnings.setText("预估收益(元)");
        accumulatedIncome.setText("累计收益(元)");
        revenue_head();
        gotoEstimatedEarnings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EstimatedEarningsActivity.this, AccumulatedIncomeActivity.class));
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        MutualApplication.getRequestQueue().cancelAll("revenueManagement27");
    }

    /**
     * 收益管理数据显示
     */
    public void revenue_head() {//头
        StoreIdPostBean storeIdPostBean = new StoreIdPostBean();
        storeIdPostBean.setStoreid(UserInfo.getInstance().getUser(this).getStoreid());
        Http.revenueManagement(EstimatedEarningsActivity.this, storeIdPostBean, accumulatedIncomeMoney,
                estimatedEarningsMoney, peopleNumber);
    }

}
