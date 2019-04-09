package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Response;
import com.example.administrator.merchants.http.show.AddressContentShowBean;
import com.example.administrator.merchants.activity.AddressListActivity;
import com.example.administrator.merchants.adapter.AddressListAdapter;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.common.views.RefreshLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：地址列表返回数据监听操作
 */
public class AddressListListener implements Response.Listener<JSONObject> {
    private Context context;
    private List<AddressContentShowBean> list;
    private int type;
    private ListView listView;
    private AddressListAdapter addressListAdapter;
    private RefreshLayout swipeLayout;
    private View footView;

    public AddressListListener(Context context, List<AddressContentShowBean> list, int type, ListView listView, AddressListAdapter addressListAdapter, RefreshLayout swipeLayout, View footView) {
        this.context = context;
        this.type = type;
        this.list = list;
        this.listView = listView;
        this.addressListAdapter = addressListAdapter;
        this.swipeLayout = swipeLayout;
        this.footView = footView;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            if (type == 0) {
                list.clear();
            }
            try {
                JSONArray jsonArray = jsonObject.getJSONArray("list");
                List<JSONObject> jsonObjectList = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObjectList.add((JSONObject) jsonArray.get(i));
                }
                boolean staus = false;
                for (int j = 0; j < jsonObjectList.size(); j++) {
                    AddressContentShowBean addressListBean = new AddressContentShowBean();
                    addressListBean.setAddressid(jsonObjectList.get(j).getString("addressid"));
                    addressListBean.setAreaname(jsonObjectList.get(j).getString("areaname"));
                    addressListBean.setContact(jsonObjectList.get(j).getString("contact"));
                    addressListBean.setGender(jsonObjectList.get(j).getString("gender"));
                    if ("1".equals(jsonObjectList.get(j).getString("isdefault"))) {
                        staus = true;
                        addressListBean.setIsdefault(jsonObjectList.get(j).getString("isdefault"));
                    }
                    addressListBean.setReceiver(jsonObjectList.get(j).getString("receiver"));
                    addressListBean.setStoreid(jsonObjectList.get(j).getString("storeid"));
                    addressListBean.setStreetaddr(jsonObjectList.get(j).getString("streetaddr"));
                    list.add(addressListBean);
                }
                if ((!staus) && (list.size() != 0)) {
                    list.get(0).setIsdefault("1");
                }

                if (type == 0) {
                    listView.setAdapter(addressListAdapter);
                    swipeLayout.setRefreshing(false);
                } else if (type == 1) {
                    AddressListActivity.s = 1;
                    addressListAdapter.notifyDataSetChanged();
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
            Status.fail(context, jsonObject);
        }
    }
}
