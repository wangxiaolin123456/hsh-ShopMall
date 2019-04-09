package com.example.administrator.merchants.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.merchants.http.show.StatementAccountShowBean;
import com.example.administrator.merchants.R;
import com.example.administrator.merchants.adapter.StatementAccountBaseAdapter;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseActivity;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.post.ListPostBean;
import com.example.administrator.merchants.common.views.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/8/5 0005 09:20
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：对账单
 */
public class StatementAccountActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,
        RefreshLayout.OnLoadListener {
    private ListView listView;
    private List<StatementAccountShowBean> list;
    private StatementAccountBaseAdapter statementAccountBaseAdapter;
    public static int s = 0;
    private View header;
    private RefreshLayout swipeLayout;
    private View footView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);
        setTitles("对账单");
        list = new ArrayList<>();
        listView = (ListView) findViewById(R.id.activity_address_list_listView);
        statementAccountBaseAdapter = new StatementAccountBaseAdapter(this, list);
        footView = getLayoutInflater().inflate(R.layout.no_more_date_footview, null);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position <= list.size()) {
                    Intent intent = new Intent();
                    intent.putExtra("settleno", list.get(position - 1).getSettleno());
                    intent.setClass(StatementAccountActivity.this, AccountStatementDetailsActivity.class);
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
        onRefresh();//刷新
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
     * 对账单列表
     *
     * @param offset
     * @param limit
     * @param type
     */
    public void getList(String offset, String limit, int type) {
        ListPostBean listPostBean = new ListPostBean();
        listPostBean.setStoreid(UserInfo.getInstance().getUser(this).getStoreid());
        listPostBean.setOffset(offset);
        listPostBean.setLimit(limit);
        Http.statementAccountList(this, listPostBean, type, listView, list, statementAccountBaseAdapter, swipeLayout, footView);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MutualApplication.getRequestQueue().cancelAll("statementAccountList83");
    }

    @Override
    public void onLoad() {
        if (s == 0 || s == 1) {
            s = 2;
            getList(list.size() + "", "15", 1);
        }
    }

    @Override
    public void onRefresh() {
        swipeLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                getList("0", "15", 0);
            }
        }, 1000);
    }
}
