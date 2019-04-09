package com.example.administrator.merchants.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.adapter.EstimatedEarningsDetailsAdapter;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseActivity;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.http.show.EstimatedEarningsDetailsShowBean;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.post.ListPostBean;
import com.example.administrator.merchants.http.post.StoreIdPostBean;
import com.example.administrator.merchants.common.views.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/6/23 0023 14:39
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：预估收益详情
 */
public class EstimatedEarningsDetailsActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,
        RefreshLayout.OnLoadListener {
    private ListView listView;
    private List<EstimatedEarningsDetailsShowBean> list;
    private List<EstimatedEarningsDetailsShowBean> listHead;
    private EstimatedEarningsDetailsAdapter estimatedEarningsDetailsAdapter;
    public static int s = 0;
    private View header;
    private RefreshLayout swipeLayout;
    private TextView estimatedEarningsMoney;//预估收益
    private View footView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);
        setTitles("预估收益详情");
        footView = getLayoutInflater().inflate(R.layout.no_more_date_footview, null);
        list = new ArrayList<>();//返利订单明细集合
        listHead = new ArrayList<>();//返利期间集合
        listView = (ListView) findViewById(R.id.activity_address_list_listView);
        estimatedEarningsDetailsAdapter = new EstimatedEarningsDetailsAdapter(this, list);
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

    /**
     * 初始化布局
     */
    @SuppressLint({"InlinedApi", "InflateParams"})
    private void initView() {
        header = getLayoutInflater().inflate(R.layout.header_estimated_earnings_deails, null);
        estimatedEarningsMoney = (TextView) header.findViewById(R.id.Being_displayed_money);
        swipeLayout = (RefreshLayout) findViewById(R.id.swipe_container);
        revenue_head();
        listView.addHeaderView(header);
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
                estimatedEarningsDetails("0", "15", 0);

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
                    int offset = 0;
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getType() != 0) {
                            offset += 1;
                        }
                    }
                    estimatedEarningsDetails(offset + "", "15", 1);
                }
            }
        }, 1000);
    }

    /**
     * 预估收益列表
     *
     * @param offset
     * @param limit
     * @param type
     */
    public void estimatedEarningsDetails(String offset, String limit, int type) { //预估
        ListPostBean listPostBean = new ListPostBean();
        listPostBean.setStoreid(UserInfo.getInstance().getUser(this).getStoreid());
        listPostBean.setOffset(offset);
        listPostBean.setLimit(limit);
        Http.estimatedEarningsDetails(EstimatedEarningsDetailsActivity.this, listPostBean, type, list, listHead, listView, estimatedEarningsDetailsAdapter, footView, swipeLayout);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MutualApplication.getRequestQueue().cancelAll("revenueManagement34");
        MutualApplication.getRequestQueue().cancelAll("estimatedEarningsDetails35");
    }

    /**
     * 头部预估收益数据展示
     */
    public void revenue_head() {//头
        StoreIdPostBean storeIdPostBean = new StoreIdPostBean();
        storeIdPostBean.setStoreid(UserInfo.getInstance().getUser(this).getStoreid());
        Http.revenueManagement(EstimatedEarningsDetailsActivity.this, storeIdPostBean, estimatedEarningsMoney,1);
    }


}
