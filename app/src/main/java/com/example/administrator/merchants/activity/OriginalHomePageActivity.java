package com.example.administrator.merchants.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseActivity;
import com.example.administrator.merchants.common.GlideTest;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.post.StoreIdPostBean;
import com.example.administrator.merchants.http.show.CommodityShowBean;
import com.example.administrator.merchants.common.views.MyGridView;
import com.example.administrator.merchants.http.show.PopupMenuShowBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/7/18 0018 08:53
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：原产地首页
 */
public class OriginalHomePageActivity extends BaseActivity implements View.OnClickListener {
    private MyGridView gridView;
    private List<CommodityShowBean> commodityShowBeans;
    private ListView listView;
    private ImageView headSearch;
    private ImageView shopCarIcon;
    private View footView;
    private View headView;
    private ImageView anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_original_home_page);
        headView = LayoutInflater.from(this).inflate(R.layout.view_you_like_head, null);
        commodityShowBeans = new ArrayList<>();
        footView = LayoutInflater.from(this).inflate(R.layout.view_you_like_foot, null);
        anim = (ImageView) findViewById(R.id.image_loading);
        GlideTest.imageGif(OriginalHomePageActivity.this, anim);
        gridView = (MyGridView) headView.findViewById(R.id.grid_original);
        listView = (ListView) findViewById(R.id.original_list);
        listView.addHeaderView(headView);
        listView.addFooterView(footView);
        listView.setAdapter(null);
        headSearch = (ImageView) findViewById(R.id.head_search);
        shopCarIcon= (ImageView)findViewById(R.id.shop_car_icon);
        click();
        getOriginalHomeGridData();
        Http.guessYouLike(OriginalHomePageActivity.this, commodityShowBeans, listView);//原产地猜你喜欢
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if ((position <= commodityShowBeans.size())) {
                    Intent intent = new Intent();
                    intent.putExtra("merid", commodityShowBeans.get(position - 1).getMerid());
                    intent.setClass(OriginalHomePageActivity.this, GoodsDetailsActivity.class);
                    startActivity(intent);
                }
            }
        });//CommodityDetailsActivity



        shopCarIcon.setOnClickListener(new View.OnClickListener() {//进入购物车
            @Override
            public void onClick(View v) {
                if (UserInfo.getInstance().getUser(mContext) == null) {
                    Intent intent = new Intent();
                    intent.setClass(mContext, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    startActivity(new Intent(mContext, ShoppingCarActivity.class));
                }
            }
        });

    }

    @Override
    protected void onStop() {
        MutualApplication.getRequestQueue().cancelAll("guessYouLike57");
        MutualApplication.getRequestQueue().cancelAll("originalClassify58");
        MutualApplication.getRequestQueue().cancelAll("discountText120");
        super.onStop();
    }

    /**
     * 点击监听
     */
    public void click() {
        headSearch.setOnClickListener(this);
    }

    /**
     * 原产地商品分类图标
     */
    public void getOriginalHomeGridData() {
        StoreIdPostBean storeIdPostBean = new StoreIdPostBean();
        storeIdPostBean.setStoreid(UserInfo.getInstance().getUser(this).getStoreid());
        Http.originalClassify(OriginalHomePageActivity.this, storeIdPostBean, anim, gridView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.head_search:
                startActivity(new Intent(this, OriginalSearchActivity.class));
                break;
        }
    }
}
