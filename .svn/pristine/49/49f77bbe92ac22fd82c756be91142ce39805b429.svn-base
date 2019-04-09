package com.example.administrator.merchants.fragment;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.example.administrator.merchants.activity.OriginalOrderDetailActivity;
import com.example.administrator.merchants.adapter.OriginOrderAdapter;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseFragment;
import com.example.administrator.merchants.common.GlideTest;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.post.ListPostBean;
import com.example.administrator.merchants.http.post.SendPostBean;
import com.example.administrator.merchants.http.show.OriginOrderShowBean;
import com.example.administrator.merchants.common.views.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/8/18 0018 09:05
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：原产地订单
 */
public class OriginOrderFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,
        RefreshLayout.OnLoadListener {
    private View view;
    private ListView listView;
    private List<OriginOrderShowBean> list;
    private OriginOrderAdapter originOrderAdapter;
    private ImageView anim;
    private boolean st = false;
    private View header;
    private RefreshLayout swipeLayout;
    private View footView;
    public static int s = 0;//是否加载

    @Override
    public void onStop() {
        super.onStop();
        MutualApplication.getRequestQueue().cancelAll("cancelOrder60");
        MutualApplication.getRequestQueue().cancelAll("deleteOrder115");
        MutualApplication.getRequestQueue().cancelAll("originOrderList116");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_order_list, null);
        footView = getActivity().getLayoutInflater().inflate(R.layout.no_more_date_footview, null);
        listView = (ListView) view.findViewById(R.id.fragment_xfz_listView);
        anim = (ImageView) view.findViewById(R.id.image_loading);
        GlideTest.imageGif(getContext(), anim);
        list = new ArrayList<>();
        originOrderAdapter = new OriginOrderAdapter(getContext(), list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position <= list.size()) {
                    st = true;
                    Intent intent = new Intent();
                    intent.putExtra("ordno", list.get(position - 1).getOrderId());
                    intent.setClass(view.getContext(), OriginalOrderDetailActivity.class);
                    startActivity(intent);
                }
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                if (position <= list.size()) {
                    new AlertDialog.Builder(getActivity())
                            .setMessage("是否删除订单？\n")
                            .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    SendPostBean sendPostBean = new SendPostBean();
                                    sendPostBean.setOrdno(list.get(position - 1).getOrderId());
                                    Http.deleteOrder(getContext(), sendPostBean, list, position, originOrderAdapter);
                                }
                            })
                            .setNegativeButton("否", null)
                            .show();
                }
                return true;
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
                    getOrderList(list.size() + "", "15", 1); //toDO 调用请求
                }
            }
        }, 1000);
    }

    @Override
    public void onRefresh() {
        swipeLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                getOrderList("0", "15", 0);
            }
        }, 1000);
    }

    /**
     * 获取订单列表
     *
     * @param offset
     * @param limit
     * @param type
     */
    public void getOrderList(String offset, String limit, int type) {
        ListPostBean listPostBean = new ListPostBean();
        listPostBean.setStoreid(UserInfo.getInstance().getUser(getContext()).getStoreid());
        listPostBean.setOffset(offset);
        listPostBean.setLimit(limit);
        Http.originOrderList(getContext(), listPostBean, type, list, anim, listView, originOrderAdapter, swipeLayout, footView, this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (st) {
            getOrderList("0", list.size() + "", 2);
        } else {
            onRefresh();
        }
    }
}
