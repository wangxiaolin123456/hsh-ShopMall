package com.example.administrator.merchants.http.listener;

import android.content.Context;

import com.android.volley.Response;
import com.example.administrator.merchants.http.show.RecruitmentManagementShowBean;
import com.example.administrator.merchants.adapter.RecruitmentManagementAdapter;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.common.toast.CustomToast;

import org.json.JSONObject;

import java.util.List;

/**
 * 作者：韩宇 on 2017/7/30 0030 13:24
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：删除招聘信息
 */
public class DeleteRecruitmentListener implements Response.Listener<JSONObject> {
    private Context context;
    private List<RecruitmentManagementShowBean> list;
    private int position;
    private RecruitmentManagementAdapter adapter;

    public DeleteRecruitmentListener(Context context, List<RecruitmentManagementShowBean> list, int position, RecruitmentManagementAdapter adapter) {
        this.context = context;
        this.list = list;
        this.position = position;
        this.adapter = adapter;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //成功
            list.remove(position - 1);
            adapter.notifyDataSetChanged();
            CustomToast.getInstance(context).setMessage("删除成功！");
        } else {
            //失败
            Status.fail(context, jsonObject);
        }
    }
}
