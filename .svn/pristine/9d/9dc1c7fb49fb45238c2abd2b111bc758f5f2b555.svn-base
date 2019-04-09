package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Response;
import com.example.administrator.merchants.http.show.RecruitmentManagementShowBean;
import com.example.administrator.merchants.activity.RecruitmentManagementActivity;
import com.example.administrator.merchants.adapter.RecruitmentManagementAdapter;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.common.views.RefreshLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/7/30 0030 13:37
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：招聘管理列表
 */
public class RecruitmentListListener implements Response.Listener<JSONObject> {
    private Context context;
    private int type;
    private ListView listView;
    private RefreshLayout swipeLayout;
    private List<RecruitmentManagementShowBean> list;
    private RecruitmentManagementAdapter adapter;
    private View footView;

    public RecruitmentListListener(Context context, int type, List<RecruitmentManagementShowBean> list, ListView listView, RecruitmentManagementAdapter adapter, RefreshLayout swipeLayout, View footView) {
        this.context = context;
        this.type = type;
        this.list = list;
        this.swipeLayout = swipeLayout;
        this.listView = listView;
        this.adapter = adapter;
        this.footView = footView;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //成功
            JSONArray jsonArray = null;
            try {
                if (type == 0) {
                    list.clear();
                }
                jsonArray = jsonObject.getJSONArray("list");
                List<JSONObject> jsonObjectList = new ArrayList<>();

                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObjectList.add((JSONObject) jsonArray.get(i));
                }
                for (int j = 0; j < jsonObjectList.size(); j++) {
                    RecruitmentManagementShowBean recruitmentManagementShowBean = new RecruitmentManagementShowBean();
                    recruitmentManagementShowBean.setName(jsonObjectList.get(j).getString("station"));
                    recruitmentManagementShowBean.setNumber(jsonObjectList.get(j).getString("number"));
                    recruitmentManagementShowBean.setExperience(jsonObjectList.get(j).getString("experience"));
                    recruitmentManagementShowBean.setCompensation(jsonObjectList.get(j).getString("salary"));
                    recruitmentManagementShowBean.setId(jsonObjectList.get(j).getString("recrid"));
                    list.add(recruitmentManagementShowBean);
                }
                if (type == 0) {
                    listView.setAdapter(adapter);
                    swipeLayout.setRefreshing(false);
                } else if (type == 1) {
                    RecruitmentManagementActivity.s = 1;
                    swipeLayout.setLoading(false);
                    adapter.notifyDataSetChanged();
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
            //失败
            Status.fail(context, jsonObject);
        }
    }
}
