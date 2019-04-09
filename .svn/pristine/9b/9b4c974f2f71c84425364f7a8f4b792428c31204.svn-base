package com.example.administrator.merchants.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.adapter.CommodityManagementListAdapter;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseActivity;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.post.CommodityManagementListPostBean;
import com.example.administrator.merchants.http.post.MerIdPostBean;
import com.example.administrator.merchants.http.show.CommodityShowBean;
import com.example.administrator.merchants.common.views.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/6/23 0023 14:39
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：商品管理列表
 */
public class CommodityManagementListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,
        RefreshLayout.OnLoadListener {
    private ListView listView;
    private LinearLayout linear_top;
    private List<CommodityShowBean> likeBeans;
    private TextView head;
    private CommodityManagementListAdapter commodityManagementListAdapter;
    private boolean st = false;//是否进入详情
    public static boolean ay = true;//是否走R刷新
    private View header;
    private RefreshLayout swipeLayout;
    public static int s = 0;
    private View footView;
    private String menuname,menuid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_list);
        footView = getLayoutInflater().inflate(R.layout.no_more_date_footview, null);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork()
                .penaltyLog().build());
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
                .penaltyLog().penaltyDeath().build());
        linear_top = (LinearLayout) findViewById(R.id.linear_top);
        listView = (ListView) findViewById(R.id.listv);
        head = (TextView) findViewById(R.id.head_title);
        menuname=getIntent().getStringExtra("menuname");
        menuid=getIntent().getStringExtra("menuid");
        head.setText(menuname);
        likeBeans = new ArrayList<>();
        commodityManagementListAdapter = new CommodityManagementListAdapter(this, likeBeans, listView);
        linear_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st = false;
                startActivity(new Intent(CommodityManagementListActivity.this, MerchantAddGoodsActivity.class));
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (likeBeans.size() != 0 && position <= likeBeans.size()) {
                    if (ay) {
                        st = true;
                        toGetGoodsDetailUp(position - 1);

                    }
                }
            }
        });
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
     * 上拉刷新
     */
    @Override
    public void onRefresh() {
        swipeLayout.postDelayed(new Runnable() {

            @Override
            public void run() {
                getGoodsList("0", "15", 0);
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
                    getGoodsList(likeBeans.size() + "", "15", 1);
                }

            }
        }, 1000);
    }

    /**
     * 商品详情
     *
     * @param position
     */
    public void toGetGoodsDetailUp(int position) {
        MerIdPostBean merIdPostBean = new MerIdPostBean();
        merIdPostBean.setMerid(likeBeans.get(position).getMerid());
        Http.upGoodsDetail(CommodityManagementListActivity.this, merIdPostBean);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MutualApplication.getRequestQueue().cancelAll("upGoodsDetail25");
        MutualApplication.getRequestQueue().cancelAll("goodsList26");
        MutualApplication.getRequestQueue().cancelAll("deleteMerchandise86");
        MutualApplication.getRequestQueue().cancelAll("upOrDown87");
    }

    /**
     * 商品列表
     */
    public void getGoodsList(String offset, String limit, int type) {
        Log.e("goods:",offset+limit+type);
        CommodityManagementListPostBean commodityManagementListPostBean = new CommodityManagementListPostBean();
        commodityManagementListPostBean.setStoreid(UserInfo.getInstance().getUser(this).getStoreid());
        commodityManagementListPostBean.setMenuid(menuid);
        commodityManagementListPostBean.setOffset(offset);
        commodityManagementListPostBean.setLimit(limit);
        Http.goodsList(CommodityManagementListActivity.this, commodityManagementListPostBean, type, likeBeans, listView, commodityManagementListAdapter, swipeLayout, footView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (st) {
            getGoodsList("0", likeBeans.size() + "", 2);//跳转别的activity返回刷新不回顶部
            ay = true;
        } else {
            getGoodsList("0", "15", 0);
        }

    }
}
