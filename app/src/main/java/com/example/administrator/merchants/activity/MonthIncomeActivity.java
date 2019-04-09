package com.example.administrator.merchants.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.adapter.MonthIncomeBaseAdapter;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseActivity;
import com.example.administrator.merchants.http.show.MonthIncomeShowBean;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.post.ListPostBean;
import com.example.administrator.merchants.http.post.StoreIdPostBean;
import com.example.administrator.merchants.common.views.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/7/16 0016 09:41
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：月收益列表
 */
public class MonthIncomeActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,
        RefreshLayout.OnLoadListener {
    private ListView listView;
    private MonthIncomeBaseAdapter monthIncomeBaseAdapter;
    private List<MonthIncomeShowBean> list;
    private View header;
    private RefreshLayout swipeLayout;
    private View footView;
    private TextView money;
    public static int s = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);
        setTitles("月收益统计");
        listView = (ListView) findViewById(R.id.activity_address_list_listView);
        footView = getLayoutInflater().inflate(R.layout.no_more_date_footview, null);
        list = new ArrayList<>();
        monthIncomeBaseAdapter = new MonthIncomeBaseAdapter(this, list);
        initView();
        setListener();
        swipeLayout.post(new Thread(new Runnable() {

            @Override
            public void run() {
                swipeLayout.setRefreshing(true);
            }
        }));
        onRefresh();
    }

    @Override
    protected void onStop() {
        super.onStop();
        MutualApplication.getRequestQueue().cancelAll("revenueManagement34");
        MutualApplication.getRequestQueue().cancelAll("monthIncome51");
    }

    /**
     * 初始化布局
     */
    @SuppressLint({"InlinedApi", "InflateParams"})
    private void initView() {
        header = getLayoutInflater().inflate(R.layout.header_month_income, null);
        money = (TextView) header.findViewById(R.id.Being_displayed_money);
        revenue_head();
        swipeLayout = (RefreshLayout) findViewById(R.id.swipe_container);
        listView.addHeaderView(header);
    }

    /**
     * 累计收益金额
     */
    public void revenue_head() {//头
        StoreIdPostBean storeIdPostBean = new StoreIdPostBean();
        storeIdPostBean.setStoreid(UserInfo.getInstance().getUser(this).getStoreid());
        Http.revenueManagement(MonthIncomeActivity.this, storeIdPostBean, money, 1);
    }

    /**
     * 设置监听
     */
    private void setListener() {
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setOnLoadListener(this);
    }

    /**
     * 上拉刷新
     */
    @Override
    public void onRefresh() {
        swipeLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                list("0", "15", 0);
            }
        }, 1000);
    }

    /**
     * 加载更多
     */
    @Override
    public void onLoad() {
        swipeLayout.postDelayed(new Runnable() {

            @Override
            public void run() {
                // 更新数据
                // 更新完后调用该方法结束刷新
                if (s == 0 || s == 1) {
                    s = 2;
                    list(list.size() + "", "15", 2);
                }

            }
        }, 1000);
    }

    /**
     * 月收益列表
     */
    public void list(String offset, String limit, int type) {
        ListPostBean listPostBean = new ListPostBean();
        listPostBean.setStoreid(UserInfo.getInstance().getUser(this).getStoreid());
        listPostBean.setOffset(offset);
        listPostBean.setLimit(limit);
        Http.monthIncome(MonthIncomeActivity.this, listPostBean, type, list, listView, monthIncomeBaseAdapter, swipeLayout, footView);
    }
}
