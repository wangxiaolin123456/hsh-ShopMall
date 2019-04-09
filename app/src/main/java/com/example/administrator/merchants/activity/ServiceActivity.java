package com.example.administrator.merchants.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.adapter.ServiceBaseAdapter;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseActivity;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.post.ServicePostBean;
import com.example.administrator.merchants.http.show.ServiceShowBean;
import com.example.administrator.merchants.common.views.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/8/1 0001 16:39
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：服务列表
 */
public class ServiceActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,
        RefreshLayout.OnLoadListener, AdapterView.OnItemClickListener {
    private ListView listView;
    private List<ServiceShowBean> list;
    private ServiceBaseAdapter serviceBaseAdapter;
    private TextView title;
    private View header;
    private RefreshLayout swipeLayout;
    private View footView;
    public static int s = 0;//是否加载
    private View view;
    private String servetype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);
        footView = getLayoutInflater().inflate(R.layout.loding_header, null);
        view = findViewById(R.id.view);
        view.setVisibility(View.VISIBLE);
        title = (TextView) findViewById(R.id.head_title);
        servetype=getIntent().getStringExtra("servetype");
        if (servetype.equals("10")) {
            title.setText("配套服务");
        } else if (servetype.equals("20")) {
            title.setText("金融助力");
        }
        listView = (ListView) findViewById(R.id.activity_address_list_listView);
        list = new ArrayList<>();
        serviceBaseAdapter = new ServiceBaseAdapter(this, list);
        initView();//初始化布局
        setListener();//设置监听
        swipeLayout.post(new Thread(new Runnable() {
            @Override
            public void run() {
                swipeLayout.setRefreshing(true);
            }
        }));
        listView.setOnItemClickListener(this);
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

    @Override
    protected void onResume() {
        super.onResume();
        onRefresh();//刷新
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
        MutualApplication.getRequestQueue().cancelAll("serviceList78");
    }

    /**
     * 服务列表请求
     *
     * @param offset
     * @param limit
     * @param type
     */
    public void serviceList(String offset, String limit, int type) {
        ServicePostBean servicePostBean = new ServicePostBean();
        servicePostBean.setServetype(servetype);
        servicePostBean.setOffset(offset);
        servicePostBean.setLimit(limit);
        Http.serviceList(this, servicePostBean, type, list, listView, serviceBaseAdapter, swipeLayout, footView);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position <= list.size()) {
            Intent intent = new Intent();
            intent.setClass(ServiceActivity.this, ServiceDetailsActivity.class);
            intent.putExtra("type", "0");
            intent.putExtra("title", list.get(position - 1).getContent());
            intent.putExtra("serveid", list.get(position - 1).getServeid());
            startActivity(intent);
        }

    }

    @Override
    public void onLoad() {
        swipeLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (s == 0 || s == 1) {
                    s = 2;
                    serviceList(list.size() + "", "15", 1);
                }
            }
        }, 1000);
    }

    @Override
    public void onRefresh() {
        swipeLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                serviceList("0", "15", 0);
            }
        }, 1000);
    }
}
