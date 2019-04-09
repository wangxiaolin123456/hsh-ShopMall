package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Response;
import com.example.administrator.merchants.activity.AccumulatedIncomeActivity;
import com.example.administrator.merchants.adapter.AccumulatedIncomeAdapter;
import com.example.administrator.merchants.http.show.AccumulatedIncomeShowBean;
import com.example.administrator.merchants.http.HttpUrl;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.common.views.RefreshLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/6/25 0025 14:00
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：累计收益列表
 */
public class AccumulateIncomeListListener implements Response.Listener<JSONObject> {
    public Context context;
    private int type;
    private List<AccumulatedIncomeShowBean> list;
    private ListView listView;
    private RefreshLayout swipeLayout;
    private View footView;
    private AccumulatedIncomeAdapter accumulatedIncomeAdapter;

    public AccumulateIncomeListListener(Context context, int type, List<AccumulatedIncomeShowBean> list, AccumulatedIncomeAdapter accumulatedIncomeAdapter, ListView listView, RefreshLayout swipeLayout, View footView) {
        this.context = context;
        this.type = type;
        this.list = list;
        this.listView = listView;
        this.swipeLayout = swipeLayout;
        this.footView = footView;
        this.accumulatedIncomeAdapter = accumulatedIncomeAdapter;

    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //返回成功
            try {
                String storeType = jsonObject.getString("levelno");
                JSONArray jsonArray = jsonObject.getJSONArray("recretlist");
                List<JSONObject> objectList = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    objectList.add((JSONObject) jsonArray.get(i));
                }
                if (type == 0) {
                    list.clear();
                }

                for (JSONObject j : objectList) {
                    AccumulatedIncomeShowBean accumulatedIncomeShowBean = new AccumulatedIncomeShowBean();
                    accumulatedIncomeShowBean.setCircularImage(HttpUrl.BaseImageUrl + j.getString("imgsfile"));
                    if ("2".equals(storeType)) {
                        accumulatedIncomeShowBean.setName(j.getString("storename"));
                    } else {
                        accumulatedIncomeShowBean.setName(j.getString("memname"));
                    }
                    accumulatedIncomeShowBean.setIncomeMoney(j.getString("recretbeibiamt2"));
                    list.add(accumulatedIncomeShowBean);
                }
                if (list.size() < 15) {
                    swipeLayout.setOnLoadListener(null);
                    listView.removeFooterView(footView);
                    listView.addFooterView(footView);

                } else {
                    swipeLayout.setOnLoadListener((RefreshLayout.OnLoadListener) context);
                    listView.removeFooterView(footView);
                }
                if (type == 0) {
                    swipeLayout.setRefreshing(false);
                    listView.setAdapter(accumulatedIncomeAdapter);
                } else {
                    swipeLayout.setLoading(false);
                    accumulatedIncomeAdapter.notifyDataSetChanged();
                    AccumulatedIncomeActivity.s = 1;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


        } else {
            //返回失败
            Status.fail(context, jsonObject);
        }
    }
}
