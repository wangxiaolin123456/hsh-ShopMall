package com.example.administrator.merchants.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.adapter.BeiBiGrantRecordAdapter;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseActivity;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.common.views.RefreshLayout;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.post.ListPostBean;
import com.example.administrator.merchants.http.show.BeiRecordShowBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 金贝币 银贝币
 * 发放记录 j y
 * Created by User on 2019/2/13.
 */

public class BeiBiGrantRecordActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,
        RefreshLayout.OnLoadListener {
    @BindView(R.id.activity_address_list_listView)
    ListView listView;
    private List<BeiRecordShowBean> list = new ArrayList<>();
    private BeiBiGrantRecordAdapter beiBiGrantRecordAdapter;
    private View header;
    private RefreshLayout swipeLayout;
    private View footView;
    public static int s = 0;//是否加载
    private String stu = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);
        ButterKnife.bind(this);
        stu = getIntent().getStringExtra("type");
        if (getIntent().getStringExtra("type").equals("j")) {
            setTitles("金贝币发放记录");
        } else if (getIntent().getStringExtra("type").equals("y")) {
            setTitles("银贝币发放记录");
        }
        footView = getLayoutInflater().inflate(R.layout.no_more_date_footview, null);
        listView = (ListView) findViewById(R.id.activity_address_list_listView);
        beiBiGrantRecordAdapter = new BeiBiGrantRecordAdapter(mContext, list, stu);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position <= list.size()) {
                    //贝币记录详情
                    Intent intent = new Intent();
                    intent.setClass(mContext, BeiBiRecordDetailsActivity.class);
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
     * 金贝币发放记录
     *
     * @param offset
     * @param limit
     * @param type
     */
    public void getBeiBiGrantRecordList(String offset, String limit, int type) {//销量排行刷新
        ListPostBean listPostBean = new ListPostBean();
        listPostBean.setStoreid(UserInfo.getInstance().getUser(this).getStoreid());
        listPostBean.setOffset(offset);
        listPostBean.setLimit(limit);
        Http.getBeiBiGrantRecordList(mContext, listPostBean, type, list, listView,
                swipeLayout, beiBiGrantRecordAdapter, footView);
    }
    /**
     * 银贝币发放记录
     *
     * @param offset
     * @param limit
     * @param type
     */
    public void getYBeiBiGrantRecordList(String offset, String limit, int type) {//销量排行刷新
        ListPostBean listPostBean = new ListPostBean();
        listPostBean.setStoreid(UserInfo.getInstance().getUser(this).getStoreid());
        listPostBean.setOffset(offset);
        listPostBean.setLimit(limit);
        Http.getYBeiBiGrantRecordList(mContext, listPostBean, type, list, listView,
                swipeLayout, beiBiGrantRecordAdapter, footView);
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
                        getBeiBiGrantRecordList(list.size() + "", "15", 1); //toDO 调用请求
                    } else if (getIntent().getStringExtra("type").equals("y")) {
                        getYBeiBiGrantRecordList(list.size() + "", "15", 1); //toDO 调用请求
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
                    getBeiBiGrantRecordList("0", "15", 0);
                } else if (getIntent().getStringExtra("type").equals("y")) {
                    getYBeiBiGrantRecordList("0", "15", 0);
                }
            }
        }, 1000);
    }
}
