package com.example.administrator.merchants.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.adapter.SellOrderAdapter;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseActivity;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.post.AccountStatementDetailPostBean;
import com.example.administrator.merchants.http.show.OrderShowBean;
import com.example.administrator.merchants.common.views.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：对账单明细
 */
public class AccountStatementDetailsActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,
        RefreshLayout.OnLoadListener {
    private ListView listView;
    private List<OrderShowBean> list;
    private SellOrderAdapter orderAdapter;
    private View header;
    private RefreshLayout swipeLayout;
    private View footView;
    public static int s = 0;//是否加载

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);
        setTitles("对账单明细");
        list = new ArrayList<>();
        listView = (ListView) findViewById(R.id.activity_address_list_listView);
        orderAdapter = new SellOrderAdapter(AccountStatementDetailsActivity.this, list);
        footView = getLayoutInflater().inflate(R.layout.no_more_date_footview, null);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position <= list.size()) {
                    Intent intent = new Intent();
                    intent.putExtra("ordno", list.get(position - 1).getOrdno());
                    intent.setClass(AccountStatementDetailsActivity.this, MerchantsOrderDetailActivity.class);
                    startActivity(intent);
                }

            }
        });
        initView();//初始化布局
        setListener();//设置监听
        swipeLayout.post(new Thread(new Runnable() {
            @Override
            public void run() {
                swipeLayout.setRefreshing(true);
            }
        }));

    }

    /**
     * 初始化布局
     */
    @SuppressLint({"InlinedApi", "InflateParams"})
    private void initView() {
        header = getLayoutInflater().inflate(R.layout.loding_header, null);
        swipeLayout = (RefreshLayout) findViewById(R.id.swipe_container);
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
     * 对账单明细
     *
     * @param offset
     * @param limit
     * @param type
     */
    private void accountStatementDetail(String offset, String limit, int type) {
        AccountStatementDetailPostBean accountStatementDetailPostBean = new AccountStatementDetailPostBean();
        accountStatementDetailPostBean.setStoreid(UserInfo.getInstance().getUser(AccountStatementDetailsActivity.this).getStoreid());
        accountStatementDetailPostBean.setOffset(offset);
        accountStatementDetailPostBean.setSettleno(getIntent().getStringExtra("settleno"));
        accountStatementDetailPostBean.setLimit(limit);
        Http.accountStatementDetail(AccountStatementDetailsActivity.this, accountStatementDetailPostBean, list, type, listView, orderAdapter, swipeLayout, footView);

    }

    @Override
    protected void onResume() {
        super.onResume();
        onRefresh();//刷新
    }

    @Override
    protected void onStop() {
        super.onStop();
        MutualApplication.getRequestQueue().cancelAll("accountStatementDetail0");
    }

    /**
     * 加载
     */
    @Override
    public void onLoad() {
        swipeLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (s == 0 || s == 1) {
                    s = 2;
                    accountStatementDetail(list.size() + "", "15", 1);
                }
            }
        }, 1000);
    }

    /**
     * 刷新
     */
    @Override
    public void onRefresh() {
        swipeLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                accountStatementDetail("0", "15", 0);
            }
        }, 1000);
    }
}
