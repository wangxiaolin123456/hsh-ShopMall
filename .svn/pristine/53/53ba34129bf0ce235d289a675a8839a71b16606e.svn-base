package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Response;
import com.example.administrator.merchants.http.show.StatementAccountShowBean;
import com.example.administrator.merchants.activity.StatementAccountActivity;
import com.example.administrator.merchants.adapter.StatementAccountBaseAdapter;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.common.DateUtils;
import com.example.administrator.merchants.common.views.RefreshLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/8/5 0005 09:20
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：对账单
 */
public class StatementAccountListener implements Response.Listener<JSONObject> {
    private Context context;
    private int type;
    private ListView listView;
    private List<StatementAccountShowBean> list;
    private StatementAccountBaseAdapter statementAccountBaseAdapter;
    private RefreshLayout swipeLayout;
    private View footView;

    public StatementAccountListener(Context context, int type, ListView listView, List<StatementAccountShowBean> list
            , StatementAccountBaseAdapter statementAccountBaseAdapter, RefreshLayout swipeLayout, View footView) {
        this.context = context;
        this.type = type;
        this.listView = listView;
        this.list = list;
        this.statementAccountBaseAdapter = statementAccountBaseAdapter;
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
                JSONArray jsonArray = jsonObject.getJSONArray("list");
                List<JSONObject> jsonObjectList = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObjectList.add((JSONObject) jsonArray.get(i));
                }
                for (int j = 0; j < jsonObjectList.size(); j++) {
                    StatementAccountShowBean statementAccountShowBean = new StatementAccountShowBean();
                    statementAccountShowBean.setType(jsonObjectList.get(j).getInt("paystatus"));
                    statementAccountShowBean.setOrderMoney(jsonObjectList.get(j).getDouble("allpayamt"));
                    statementAccountShowBean.setPayDelivery(jsonObjectList.get(j).getDouble("arrivepayamt"));
                    statementAccountShowBean.setTripartitePaysFee(jsonObjectList.get(j).getDouble("threepaysubamt"));
                    statementAccountShowBean.setPlatformLicensing(jsonObjectList.get(j).getDouble("platusefeeamt"));
                    statementAccountShowBean.setShippingRates(jsonObjectList.get(j).getDouble("platdistamt"));
                    statementAccountShowBean.setToIssueDebt(jsonObjectList.get(j).getDouble("arrearsamt"));
                    statementAccountShowBean.setBackBeiBi(jsonObjectList.get(j).getDouble("allretamt"));
                    statementAccountShowBean.setCustomaryDues(jsonObjectList.get(j).getDouble("payableamt"));
                    statementAccountShowBean.setOrderNumber(jsonObjectList.get(j).getInt("ordernum"));
                    statementAccountShowBean.setTime(DateUtils.getDate(Long.parseLong(jsonObjectList.get(j).getJSONObject("settledate").getString("time"))));
                    statementAccountShowBean.setSettleno(jsonObjectList.get(j).getString("settleno"));
                    list.add(statementAccountShowBean);
                }
                if (type == 0) {
                    swipeLayout.setRefreshing(false);
                    listView.setAdapter(statementAccountBaseAdapter);
                } else if (type == 1) {
                    StatementAccountActivity.s = 1;
                    statementAccountBaseAdapter.notifyDataSetChanged();
                    swipeLayout.setLoading(false);
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
            //返回失败
            Status.fail(context, jsonObject);
        }
    }
}
