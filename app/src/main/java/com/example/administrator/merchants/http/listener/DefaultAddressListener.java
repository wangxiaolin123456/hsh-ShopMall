package com.example.administrator.merchants.http.listener;

import android.content.Context;

import com.android.volley.Response;
import com.example.administrator.merchants.http.show.AddressContentShowBean;
import com.example.administrator.merchants.adapter.AddressListAdapter;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.common.toast.CustomToast;

import org.json.JSONObject;

import java.util.List;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：设置默认地址网络返回数据处理
 */
public class DefaultAddressListener implements Response.Listener<JSONObject> {
    private Context context;
    private List<AddressContentShowBean> list;
    private int position;
    private AddressListAdapter addressListAdapter;


    public DefaultAddressListener(Context context, List<AddressContentShowBean> list, int position, AddressListAdapter addressListAdapter) {
        this.context = context;
        this.list = list;
        this.position = position;
        this.addressListAdapter = addressListAdapter;
    }


    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            for (int i = 0; i < list.size(); i++) {
                if (i == position - 1) {
                    list.get(i).setIsdefault("1");
                } else {
                    list.get(i).setIsdefault("0");
                }
            }
            addressListAdapter.notifyDataSetChanged();
            CustomToast.getInstance(context).setMessage("设置默认地址成功！");
        } else {
            //返回失败
            Status.fail(context, jsonObject);
        }
    }
}
