package com.example.administrator.merchants.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.adapter.AccumulatedIncomeAdapter;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseActivity;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.http.show.AccumulatedIncomeShowBean;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.post.ListPostBean;
import com.example.administrator.merchants.http.post.StoreIdPostBean;
import com.example.administrator.merchants.common.views.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：累计收益
 */
public class AccumulatedIncomeActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,
        RefreshLayout.OnLoadListener {
    private ListView listView;
    private List<AccumulatedIncomeShowBean> list;
    private AccumulatedIncomeAdapter accumulatedIncomeAdapter;
    public static int s = 0;
    private View header;
    private RefreshLayout swipeLayout;
    private TextView accumulatedIncome;//累计收益文字
    private TextView estimatedEarnings;//预估收益文字
    private TextView accumulatedIncomeMoney;//累计收益金额
    private TextView estimatedEarningsMoney;//预估收益金额
    private TextView recommendedNumber;//推荐人数
    private LinearLayout gotoEstimatedEarnings;//跳转预估收益布局
    private View footView;//数据全部加载完成

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);
        setTitles("累计收益");
        footView = getLayoutInflater().inflate(R.layout.no_more_date_footview, null);
        list = new ArrayList<>();
        listView = (ListView) findViewById(R.id.activity_address_list_listView);
        accumulatedIncomeAdapter = new AccumulatedIncomeAdapter(AccumulatedIncomeActivity.this, list);
        initView();
        setListener();
        swipeLayout.post(new Thread(new Runnable() {

            @Override
            public void run() {
                swipeLayout.setRefreshing(true);
            }
        }));

    }

    @Override
    protected void onResume() {
        super.onResume();
        onRefresh();
    }

    /**
     * 初始化布局
     */
    @SuppressLint({"InlinedApi", "InflateParams"})
    private void initView() {
        header = getLayoutInflater().inflate(R.layout.header_accumulated_income, null);
        accumulatedIncome = (TextView) header.findViewById(R.id.Being_displayed);
        accumulatedIncomeMoney = (TextView) header.findViewById(R.id.Being_displayed_money);//网路请求
        estimatedEarnings = (TextView) header.findViewById(R.id.Will_show);
        estimatedEarningsMoney = (TextView) header.findViewById(R.id.Will_show_money);//网络请求
        recommendedNumber = (TextView) header.findViewById(R.id.people_number);//网络请求
        gotoEstimatedEarnings = (LinearLayout) header.findViewById(R.id.goto_Estimated_earnings);//跳转预估收益
        accumulatedIncome.setText("累计收益(元)");
        estimatedEarnings.setText("预估收益(元)");
        getRevenueManagementDate();
        gotoEstimatedEarnings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccumulatedIncomeActivity.this, EstimatedEarningsActivity.class));
            }
        });
        swipeLayout = (RefreshLayout) findViewById(R.id.swipe_container);
        listView.addHeaderView(header);
    }

    /**
     * 请求累计收益和预估收益金额接口
     */
    public void getRevenueManagementDate() {//头
        StoreIdPostBean storeIdPostBean = new StoreIdPostBean();
        storeIdPostBean.setStoreid(UserInfo.getInstance().getUser(this).getStoreid());
        Http.revenueManagement(AccumulatedIncomeActivity.this, storeIdPostBean, accumulatedIncomeMoney, estimatedEarningsMoney, recommendedNumber);
    }

    /**
     * 设置监听
     */
    private void setListener() {
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setOnLoadListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MutualApplication.getRequestQueue().cancelAll("revenueManagement27");
        MutualApplication.getRequestQueue().cancelAll("accumulatedIncomeList28");
    }

    /**
     * 上拉刷新
     */
    @Override
    public void onRefresh() {
        swipeLayout.postDelayed(new Runnable() {

            @Override
            public void run() {
                accumulatedIncomeList("0", "15", 0);
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
                if (s == 0 || s == 1) {
                    s = 2;
                    accumulatedIncomeList(list.size() + "", "15", 1);
                }
            }
        }, 1000);
    }

    /**
     * 累计收益列表数据
     *
     * @param offset
     * @param limit
     * @param type
     */
    public void accumulatedIncomeList(String offset, String limit, int type) {
        ListPostBean listPostBean = new ListPostBean();
        listPostBean.setStoreid(UserInfo.getInstance().getUser(AccumulatedIncomeActivity.this).getStoreid());
        listPostBean.setOffset(offset);
        listPostBean.setLimit(limit);
        Http.accumulatedIncomeList(AccumulatedIncomeActivity.this, listPostBean, type, list, accumulatedIncomeAdapter, listView, swipeLayout, footView);
    }
}
