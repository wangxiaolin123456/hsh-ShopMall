package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.content.DialogInterface;

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
 * 功能：删除地址网络返回处理监听
 */
public class DeleteAddressListener implements Response.Listener<JSONObject> {
    private Context context;
    private List<AddressContentShowBean> list;
    private int position;
    private AddressListAdapter addressListAdapter;
    private DialogInterface dialogInterface;

    public DeleteAddressListener(Context context, List<AddressContentShowBean> list, int position, AddressListAdapter addressListAdapter, DialogInterface dialogInterface) {
        this.context = context;
        this.list = list;
        this.position = position;
        this.addressListAdapter = addressListAdapter;
        this.dialogInterface = dialogInterface;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            list.remove(position - 1);
            boolean staus = false;
            for (int i = 0; i < list.size(); i++) {
                if ("1".equals(list.get(i).getIsdefault())) {
                    staus = true;
                }
            }
            if (list.size() != 0 && !staus) {
                list.get(0).setIsdefault("1");
            }
            addressListAdapter.notifyDataSetChanged();
            dialogInterface.dismiss();
            CustomToast.getInstance(context).setMessage("删除成功！");

        } else {
            Status.fail(context, jsonObject);
        }

    }
}
