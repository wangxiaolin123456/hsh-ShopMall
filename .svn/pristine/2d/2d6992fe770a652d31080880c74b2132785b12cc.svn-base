package com.example.administrator.merchants.http.listener;

import android.content.Context;

import com.android.volley.Response;
import com.example.administrator.merchants.adapter.MyMessageAdapter;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.http.show.MessageShowBean;
import com.example.administrator.merchants.common.toast.CustomToast;

import org.json.JSONObject;

import java.util.List;

/**
 * 作者：韩宇 on 2017/7/27 0027 11:29
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：删除消息
 */
public class DeleteMessageListener implements Response.Listener<JSONObject> {
    private Context context;
    private int position;
    private List<MessageShowBean> list;
    private MyMessageAdapter myMessageAdapter;
    public DeleteMessageListener(Context context, int position, List<MessageShowBean> list, MyMessageAdapter myMessageAdapter) {
        this.context = context;
        this.position = position;
        this.list = list;
        this.myMessageAdapter = myMessageAdapter;
    }
    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //成功
            list.remove(position - 1);
            myMessageAdapter.notifyDataSetChanged();
            CustomToast.getInstance(context).setMessage("删除成功！");
        } else {
            //失败
            Status.fail(context, jsonObject);
        }
    }
}
