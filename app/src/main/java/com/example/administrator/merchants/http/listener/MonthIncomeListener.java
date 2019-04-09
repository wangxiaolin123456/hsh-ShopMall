package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Response;
import com.example.administrator.merchants.activity.MonthIncomeActivity;
import com.example.administrator.merchants.adapter.MonthIncomeBaseAdapter;
import com.example.administrator.merchants.http.show.MonthIncomeShowBean;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.common.DateUtils;
import com.example.administrator.merchants.common.views.RefreshLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/7/16 0016 09:41
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：月收益列表
 */
public class MonthIncomeListener implements Response.Listener<JSONObject> {
    private Context context;
    private List<MonthIncomeShowBean> list;
    private int type;
    private ListView listView;
    private MonthIncomeBaseAdapter monthIncomeBaseAdapter;
    private RefreshLayout swipeLayout;
    private View footView;

    public MonthIncomeListener(Context context, int type, List<MonthIncomeShowBean> list, ListView listView, MonthIncomeBaseAdapter monthIncomeBaseAdapter, RefreshLayout swipeLayout, View footView) {
        this.context = context;
        this.type = type;
        this.list = list;
        this.listView = listView;
        this.monthIncomeBaseAdapter = monthIncomeBaseAdapter;
        this.swipeLayout = swipeLayout;
        this.footView = footView;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //返回成功
            if (type == 0) {
                list.clear();
            }
            try {
                JSONArray jsonArray = jsonObject.getJSONArray("ordList");
                List<JSONObject> jsonObjectList = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObjectList.add((JSONObject) jsonArray.get(i));
                }
                for (int j = 0; j < jsonObjectList.size(); j++) {
                    MonthIncomeShowBean monthIncomeShowBean = new MonthIncomeShowBean();
                    monthIncomeShowBean.setBegintime(DateUtils.monthIncomeDateBegin(jsonObjectList.get(j).getString("begindate")));
                    monthIncomeShowBean.setTime(DateUtils.monthIncomeDate(jsonObjectList.get(j).getString("enddate")));
                    monthIncomeShowBean.setDealAmount(jsonObjectList.get(j).getDouble("recretordamt"));
                    monthIncomeShowBean.setGetProfit(jsonObjectList.get(j).getDouble("recretbeibiamt"));
                    list.add(monthIncomeShowBean);
                }
                if (type == 0) {
                    swipeLayout.setRefreshing(false);
                    listView.setAdapter(monthIncomeBaseAdapter);
                } else if (type == 1) {
                    swipeLayout.setLoading(false);
                    monthIncomeBaseAdapter.notifyDataSetChanged();
                    MonthIncomeActivity.s = 1;
                }
                if (jsonObjectList.size() < 15) {
                    swipeLayout.setOnLoadListener(null);
                    listView.removeFooterView(footView);
                    listView.addFooterView(footView);
                } else {
                    swipeLayout.setOnLoadListener((RefreshLayout.OnLoadListener) context);
                    listView.removeFooterView(footView);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            //返回失表
            Status.fail(context, jsonObject);
        }

    }
}
