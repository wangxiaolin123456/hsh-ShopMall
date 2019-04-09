package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Response;
import com.example.administrator.merchants.http.show.BankShowBean;
import com.example.administrator.merchants.activity.AddressListActivity;
import com.example.administrator.merchants.adapter.BankAdapter;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.common.DateUtils;
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
 * 功能：银行卡列表返回数据监听操作
 */
public class BankListListener implements Response.Listener<JSONObject> {
    private Context context;
    private List<BankShowBean> list;
    private int type;
    private ListView listView;
    private BankAdapter bankAdapter;
    private RefreshLayout swipeLayout;
    private View footView;

    public BankListListener(Context context, List<BankShowBean> list, int type, ListView listView, BankAdapter bankAdapter, RefreshLayout swipeLayout, View footView) {
        this.context = context;
        this.type = type;
        this.list = list;
        this.listView = listView;
        this.bankAdapter = bankAdapter;
        this.swipeLayout = swipeLayout;
        this.footView = footView;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {//返回成功
            if (type == 0) {//刷新
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
                    BankShowBean addressListBean = new BankShowBean();
                    addressListBean.setBankid(jsonObjectList.get(j).getString("cardid"));//银行卡id
                    addressListBean.setBankName(jsonObjectList.get(j).getString("bindbank"));//银行卡名字
                    addressListBean.setUserName(jsonObjectList.get(j).getString("bindname"));//银行卡持有人
                    if ("1".equals(jsonObjectList.get(j).getString("isdefault"))) {
                        staus = true;
                        addressListBean.setStu(jsonObjectList.get(j).getString("isdefault"));
                    }
                    String tel = jsonObjectList.get(j).getString("bindphone");//银行卡绑定电话
                    String bankNumber = jsonObjectList.get(j).getString("bindaccount");//银行卡号
                    if (bankNumber.length() == 19) {
                        addressListBean.setBankNumber(DateUtils.getbanknumber19(bankNumber));
                    } else if (bankNumber.length() == 16) {
                        addressListBean.setBankNumber(DateUtils.getbanknumber16(bankNumber));
                    }
                    addressListBean.setTelephone(DateUtils.getTelephone(tel));
                    list.add(addressListBean);
                }
                if ((!staus) && (list.size() != 0)) {
                    list.get(0).setStu("1");
                }

                if (type == 0) {//刷新
                    listView.setAdapter(bankAdapter);
                    swipeLayout.setRefreshing(false);
                } else if (type == 1) {//加载
                    AddressListActivity.s = 1;
                    bankAdapter.notifyDataSetChanged();
                    swipeLayout.setLoading(false);
                }
                if (jsonObjectList.size() < 15) {//没有更多数据
                    swipeLayout.setOnLoadListener(null);
                    listView.removeFooterView(footView);
                    listView.addFooterView(footView);
                } else {//还有数据
                    swipeLayout.setOnLoadListener((RefreshLayout.OnLoadListener) context);
                    listView.removeFooterView(footView);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Status.fail(context, jsonObject);//返回失败操作
        }
    }
}
