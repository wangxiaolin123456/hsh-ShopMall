package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Response;
import com.example.administrator.merchants.activity.StoreAddressUpdateActivity;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.common.toast.CustomToastFinish;

import org.json.JSONObject;

/**
 * 作者：韩宇 on 2017/8/19 0019 11:26
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：修改商铺地址
 */
public class StoreAddressUpdateListener implements Response.Listener<JSONObject> {
    private Context context;
    private TextView qu;
    private EditText strE;

    public StoreAddressUpdateListener(Context context, TextView qu, EditText strE) {
        this.context = context;
        this.qu = qu;
        this.strE = strE;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //返回成功
            Intent intent = new Intent();
            intent.putExtra("areaname", qu.getText().toString());//市区
            intent.putExtra("streetaddr", strE.getText().toString());//详细地址
            ((StoreAddressUpdateActivity) context).setResult(2, intent);
            CustomToastFinish customToastFinish = new CustomToastFinish(context);
            customToastFinish.setMessage("保存成功!");
        } else {
            //返回失败
            Status.fail(context, jsonObject);
        }
    }
}
