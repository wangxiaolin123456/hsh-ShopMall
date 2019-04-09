package com.example.administrator.merchants.http.listener;

import android.content.Context;

import com.android.volley.Response;
import com.example.administrator.merchants.activity.CommodityClassificationManagementActivity;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.common.toast.CustomToast;

import org.json.JSONObject;

/**
 * 作者：韩宇 on 2017/8/10 0010 10:03
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：删除分类
 */
public class DeleteClassifyListener implements Response.Listener<JSONObject> {
    private Context context;

    public DeleteClassifyListener(Context context) {
        this.context = context;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //返回成功
            CustomToast.getInstance(context).setMessage("删除成功!");
            ((CommodityClassificationManagementActivity) context).getClassifyList();
        } else {
            //返回失败
            Status.fail(context, jsonObject);
        }

    }
}
