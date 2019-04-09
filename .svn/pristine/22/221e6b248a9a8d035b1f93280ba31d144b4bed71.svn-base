package com.example.administrator.merchants.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.activity.MerchantsOrderDetailActivity;
import com.example.administrator.merchants.adapter.SellOrderAdapter;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseFragment;
import com.example.administrator.merchants.common.GlideTest;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.post.ListPostBean;
import com.example.administrator.merchants.http.show.OrderShowBean;
import com.example.administrator.merchants.common.views.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/8/18 0018 09:05
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：商家订单
 */
public class MerchantOrderFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,
        RefreshLayout.OnLoadListener {
    private ListView listView;
    private List<OrderShowBean> list;
    private SellOrderAdapter orderAdapter;
    private ImageView anim;
    private boolean st = false;
    private View header;
    private RefreshLayout swipeLayout;
    private View footView;
    public static int s = 0;//是否加载
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_order_list, null);
        footView = getActivity().getLayoutInflater().inflate(R.layout.no_more_date_footview, null);
        anim = (ImageView) view.findViewById(R.id.image_loading);
        GlideTest.imageGif(getActivity(), anim);
        listView = (ListView) view.findViewById(R.id.fragment_xfz_listView);
        list = new ArrayList<>();
        orderAdapter = new SellOrderAdapter(getContext(), list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position <= list.size())
                    st = true;
                Intent intent = new Intent();
                intent.putExtra("ordno", list.get(position - 1).getOrdno());
                if (getContext() != null) {
                    intent.setClass(getContext(), MerchantsOrderDetailActivity.class);
                }
                startActivity(intent);
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
        return view;
    }

    /**
     * 初始化布局
     */
    @SuppressLint({"InlinedApi", "InflateParams"})
    private void initView() {
        header = getActivity().getLayoutInflater().inflate(R.layout.loding_header, null);
        swipeLayout = (RefreshLayout) view.findViewById(R.id.swipe_container);
        listView.addHeaderView(header);
    }

    /**
     * 设置监听
     */
    private void setListener() {
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setOnLoadListener(this);
    }

    @Override
    public void onLoad() {
        swipeLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (s == 0 || s == 1) {
                    s = 2;
                    merchantOrderList(list.size() + "", "15", 1);
                }
            }
        }, 1000);
    }

    @Override
    public void onRefresh() {
        swipeLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                merchantOrderList("0", "15", 0);
            }
        }, 1000);
    }

    @Override
    public void onStop() {
        super.onStop();
        MutualApplication.getRequestQueue().cancelAll("merchantOrderList107");
    }

    @Override
    public void onResume() {
        super.onResume();
        if (st) {
            merchantOrderList("0", list.size() + "", 2);
        } else {
            onRefresh();
        }
    }

    /**
     * 商家订单列表
     *
     * @param offset
     * @param limit
     * @param type
     */
    private void merchantOrderList(String offset, String limit, int type) {
        ListPostBean listPostBean = new ListPostBean();
        listPostBean.setOffset(offset);
        listPostBean.setLimit(limit);
        listPostBean.setStoreid(UserInfo.getInstance().getUser(getContext()).getStoreid());
        Http.merchantOrderList(getContext(), listPostBean, type, list, anim, listView, orderAdapter, swipeLayout, footView, this);
    }
}
