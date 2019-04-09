package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Response;
import com.example.administrator.merchants.activity.ServiceActivity;
import com.example.administrator.merchants.adapter.ServiceBaseAdapter;
import com.example.administrator.merchants.http.HttpUrl;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.http.show.ServiceShowBean;
import com.example.administrator.merchants.common.views.RefreshLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/8/1 0001 16:42
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：服务列表
 */
public class ServiceListener implements Response.Listener<JSONObject> {
    private Context context;
    private int type;
    private List<ServiceShowBean> list;
    private ListView listView;
    private RefreshLayout swipeLayout;
    private View footView;
    private ServiceBaseAdapter serviceBaseAdapter;

    public ServiceListener(Context context, int type, List<ServiceShowBean> list, ListView listView, RefreshLayout swipeLayout, View footView, ServiceBaseAdapter serviceBaseAdapter) {
        this.context = context;
        this.type = type;
        this.list = list;
        this.swipeLayout = swipeLayout;
        this.listView = listView;
        this.footView = footView;
        this.serviceBaseAdapter = serviceBaseAdapter;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //返回成功
            if (type == 0) {
                list.clear();
            }
            JSONArray jsonArray = null;
            try {
                jsonArray = jsonObject.getJSONArray("list");
                List<JSONObject> jsonObjectList = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObjectList.add((JSONObject) jsonArray.get(i));
                }
                for (int j = 0; j < jsonObjectList.size(); j++) {
                    ServiceShowBean serviceShowBean = new ServiceShowBean();
                    serviceShowBean.setContent(jsonObjectList.get(j).getString("servename"));
                    serviceShowBean.setImage(HttpUrl.BaseImageUrl + jsonObjectList.get(j).getString("imgsfile"));//图片地址在这个参数里
                    serviceShowBean.setServeid(jsonObjectList.get(j).getString("serveid"));//图片地址在这个参数里
                    list.add(serviceShowBean);
                }
                if (type == 0) {
                    swipeLayout.setRefreshing(false);
                    listView.setAdapter(serviceBaseAdapter);
                } else if (type == 1) {
                    ServiceActivity.s = 1;
                    swipeLayout.setLoading(false);
                    serviceBaseAdapter.notifyDataSetChanged();
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
