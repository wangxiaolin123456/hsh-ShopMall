package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.content.Intent;


import com.android.volley.Response;

import com.example.administrator.merchants.activity.MerchantsOrderDetailActivity;
import com.example.administrator.merchants.activity.MyMessageListActivity;
import com.example.administrator.merchants.adapter.MyMessageAdapter;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.http.show.MessageShowBean;
import com.example.administrator.merchants.scan.CaptureActivity;
import com.example.administrator.merchants.common.toast.CustomToast;
import com.example.administrator.merchants.common.toast.CustomToastFinish;
import com.example.administrator.merchants.utils.LogUtil;

import org.json.JSONObject;

import java.util.List;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：toast网络监听
 */
public class ToastListener implements Response.Listener<JSONObject> {
    private Context context;
    private String text;
    private MyMessageAdapter myMessageAdapter;
    private int type;
    private List<MessageShowBean> list;

    public ToastListener(Context context, String text, int type) {
        this.context = context;
        this.text = text;
        this.type = type;

    }

    public ToastListener(Context context, String text, int type, List<MessageShowBean> list, MyMessageAdapter myMessageAdapter) {
        this.context = context;
        this.text = text;
        this.type = type;
        this.list = list;
        this.myMessageAdapter = myMessageAdapter;
    }
    @Override
    public void onResponse(JSONObject jsonObject) {
        LogUtil.i(jsonObject.toString());
        if (Status.status(jsonObject)) {
            if (type == 0) {
                CustomToastFinish customToastFinish = CustomToastFinish.getInstance(context);
                customToastFinish.setMessage(text);
            } else if (type == 1) {
                CustomToast.getInstance(context).setMessage(text);
                ((MyMessageListActivity) context).list("0", list.size() + "", 2);
            }else if (type==2){
                CustomToast.getInstance(context).setMessage(text);
            }else if (type==3){
                Intent intent = new Intent();
                intent.putExtra("ordno", text);
                intent.setClass(context, MerchantsOrderDetailActivity.class);
                context.startActivity(intent);
                ((CaptureActivity)context).finish();
            }
        } else {
            Status.fail(context, jsonObject);
            LogUtil.i("error"+jsonObject.toString());
        }
    }
}
