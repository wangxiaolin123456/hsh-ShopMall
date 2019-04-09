package com.example.administrator.merchants.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.adapter.GoodsManagerClassificationAdapter;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseActivity;
import com.example.administrator.merchants.dialog.AddClassifyDialog;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.post.StoreIdPostBean;
import com.example.administrator.merchants.http.show.ClassManagementShowBean;
import com.example.administrator.merchants.common.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/6/23 0023 14:39
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：商家分类管理
 */
public class CommodityClassificationManagementActivity extends BaseActivity {
    private ListView listView;
    private LinearLayout linear_top;
    private List<ClassManagementShowBean> listClassify;
    private GoodsManagerClassificationAdapter goodsManagerClassificationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classification_manage);
        setTitles("商家分类");
        listClassify = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listView);
        linear_top = (LinearLayout) findViewById(R.id.linear_top);
        goodsManagerClassificationAdapter = new GoodsManagerClassificationAdapter(this, listClassify, listView);
        getClassifyList();
        linear_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddClassifyDialog addOneClassifyDialog = new AddClassifyDialog(CommodityClassificationManagementActivity.this, listClassify, goodsManagerClassificationAdapter, 0);
                addOneClassifyDialog.show();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listClassify.size() != 0) {
                    Intent intent = new Intent();
                    intent.putExtra("menuname", listClassify.get(position).getMenuname());
                    intent.putExtra("menuid", listClassify.get(position).getMenuid());
                    intent.setClass(CommodityClassificationManagementActivity.this, CommodityManagementListActivity.class);
                    startActivity(intent);
                }

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        MutualApplication.getRequestQueue().cancelAll("classManagement24");
        MutualApplication.getRequestQueue().cancelAll("deleteClassify88");
    }

    /**
     * 商家商品分类列表接口
     */
    public void getClassifyList() {
        StoreIdPostBean storeIdPostBean = new StoreIdPostBean();
        storeIdPostBean.setStoreid(UserInfo.getInstance().getUser(CommodityClassificationManagementActivity.this).getStoreid());
        Http.classManagement(CommodityClassificationManagementActivity.this, storeIdPostBean, listView, goodsManagerClassificationAdapter, listClassify);
    }
}
