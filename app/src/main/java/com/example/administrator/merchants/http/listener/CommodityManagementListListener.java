package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Response;
import com.example.administrator.merchants.http.show.CommodityShowBean;
import com.example.administrator.merchants.activity.CommodityManagementListActivity;
import com.example.administrator.merchants.adapter.CommodityManagementListAdapter;
import com.example.administrator.merchants.http.HttpUrl;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.common.views.RefreshLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/6/24 0024 10:17
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：
 */
public class CommodityManagementListListener implements Response.Listener<JSONObject> {
    private Context context;
    private int type;
    private List<CommodityShowBean> likeBeans;
    private ListView listView;
    private RefreshLayout swipeLayout;
    private View footView;
    private CommodityManagementListAdapter commodityManagementListAdapter;

    public CommodityManagementListListener(Context context, int type, List<CommodityShowBean> likeBeans, ListView listView, CommodityManagementListAdapter commodityManagementListAdapter, RefreshLayout swipeLayout, View footView) {
        this.context = context;
        this.type = type;
        this.likeBeans = likeBeans;
        this.listView = listView;
        this.swipeLayout = swipeLayout;
        this.footView = footView;
        this.commodityManagementListAdapter = commodityManagementListAdapter;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //返回成功
            try {
                JSONArray arrayList = jsonObject.getJSONArray("list");
                List<JSONObject> objectList = new ArrayList<>();
                for (int i = 0; i < arrayList.length(); i++) {
                    JSONObject obj = new JSONObject();
                    obj = arrayList.getJSONObject(i);
                    objectList.add(obj);
                }
                if (type == 0 || type == 2) {//正常刷新和返回保存原位置的刷新
                    likeBeans.clear();
                }
                for (int i = 0; i < objectList.size(); i++) {
                    CommodityShowBean bean = new CommodityShowBean();
                    bean.setImgsfile(HttpUrl.BaseImageUrl + objectList.get(i).getString("imgsfile"));
                    bean.setMerid(objectList.get(i).getString("merid"));
                    bean.setShow(0);
                    bean.setMername(objectList.get(i).getString("mername"));
                    bean.setMenuid(objectList.get(i).getString("mermenuid"));
                    bean.setSaleprice(objectList.get(i).getDouble("saleprice"));
                    bean.setContractno(objectList.get(i).getString("isused"));// TODO: 这不是合同编号  是上架下架的
                    likeBeans.add(bean);
                }
                if (type == 0) {
                    listView.setAdapter(commodityManagementListAdapter);
                    swipeLayout.setRefreshing(false);
                } else if (type == 1) {
                    commodityManagementListAdapter.notifyDataSetChanged();
                    CommodityManagementListActivity.s = 1;
                    swipeLayout.setLoading(false);
                } else if (type == 2) {
                    commodityManagementListAdapter.notifyDataSetChanged();
                    swipeLayout.setRefreshing(false);
                }
                if (objectList.size() < 15) {
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
