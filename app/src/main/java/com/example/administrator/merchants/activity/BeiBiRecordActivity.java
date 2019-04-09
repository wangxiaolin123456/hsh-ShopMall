package com.example.administrator.merchants.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.adapter.BeiRecordAdapter;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseActivity;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.post.ListPostBean;
import com.example.administrator.merchants.http.show.BeiRecordShowBean;
import com.example.administrator.merchants.common.views.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/6/23 0023 10:23
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：贝币使用记录
 * 金贝币 银贝币记录在一起 j y
 */
public class BeiBiRecordActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,
        RefreshLayout.OnLoadListener {
    private ListView listView;
    private List<BeiRecordShowBean> list;
    private BeiRecordAdapter beiRecordAdapter;
    private View header;
    private RefreshLayout swipeLayout;
    private View footView;
    public static int s = 0;//是否加载
    private String stu = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);
        stu = getIntent().getStringExtra("type");
        if (getIntent().getStringExtra("type").equals("j")) {
            setTitles("金贝币使用记录");
        } else if (getIntent().getStringExtra("type").equals("y")) {
            setTitles("银贝币使用记录");
        }
        list = new ArrayList<>();
        footView = getLayoutInflater().inflate(R.layout.no_more_date_footview, null);
        listView = (ListView) findViewById(R.id.activity_address_list_listView);
        beiRecordAdapter = new BeiRecordAdapter(BeiBiRecordActivity.this, list, stu);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position <= list.size()) {
                    //贝币记录详情
                    Intent intent = new Intent();
                    intent.setClass(BeiBiRecordActivity.this, BeiBiRecordDetailsActivity.class);
                    intent.putExtra("title", list.get(position - 1).getInoutobjname());
                    if (list.get(position - 1).getInouttype().equals("in_order") || list.get(position - 1).getInouttype().equals("in_recharge") ||
                            list.get(position - 1).getInouttype().equals("in_gift") || list.get(position - 1).getInouttype().equals("in_ret")) {
                        intent.putExtra("money", list.get(position - 1).getAdd());
                        intent.putExtra("color", "1");//用来判断字的颜色 1 加 绿色；0 减 红色
                    } else {
                        intent.putExtra("money", list.get(position - 1).getJian());
                        intent.putExtra("color", "0");
                    }
                    intent.putExtra("inoutobjdescr", list.get(position - 1).getInoutobjdescr());
                    intent.putExtra("time1", list.get(position - 1).getDate2());
                    intent.putExtra("time2", list.get(position - 1).getDate());
                    intent.putExtra("content", list.get(position - 1).getMiaoshu());
                    intent.putExtra("inouttype", list.get(position - 1).getInouttype());//类型
//                    intent.putExtra("image", list.get(position - 1).getInoutobjimgsfile());
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
     * 金贝币记录
     *
     * @param offset
     * @param limit
     * @param type
     */
    public void getBeiBiRecordList(String offset, String limit, int type) {
        ListPostBean listPostBean = new ListPostBean();
        listPostBean.setStoreid(UserInfo.getInstance().getUser(this).getStoreid());
        listPostBean.setOffset(offset);
        listPostBean.setLimit(limit);
        Http.getBeiBiRecordList(BeiBiRecordActivity.this, listPostBean, type, list, listView, swipeLayout, beiRecordAdapter, footView);
    }

    /**
     * 银贝币记录
     *
     * @param offset
     * @param limit
     * @param type
     */
    public void getYBeiBiRecordList(String offset, String limit, int type) {
        ListPostBean listPostBean = new ListPostBean();
        listPostBean.setStoreid(UserInfo.getInstance().getUser(this).getStoreid());
        listPostBean.setOffset(offset);
        listPostBean.setLimit(limit);
        Http.getYBeiBiUseRecordList(BeiBiRecordActivity.this, listPostBean, type, list, listView, swipeLayout, beiRecordAdapter, footView);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MutualApplication.getRequestQueue().cancelAll("getBeiBiRecordList23");
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
                    if (getIntent().getStringExtra("type").equals("j")) {
                        getBeiBiRecordList(list.size() + "", "15", 1); //toDO 调用请求
                    } else if (getIntent().getStringExtra("type").equals("y")) {
                        getYBeiBiRecordList(list.size() + "", "15", 1); //toDO 调用请求
                    }
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
                if (getIntent().getStringExtra("type").equals("j")) {
                    getBeiBiRecordList("0", "15", 0);
                } else if (getIntent().getStringExtra("type").equals("y")) {
                    getYBeiBiRecordList("0", "15", 0);
                }
            }
        }, 1000);
    }
}