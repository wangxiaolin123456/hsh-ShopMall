package com.example.administrator.merchants.http.listener;

import android.content.Context;

import com.android.volley.Response;
import com.example.administrator.merchants.adapter.OriginalSearchGridAdapter;
import com.example.administrator.merchants.common.views.MyGridView;
import com.example.administrator.merchants.http.Status;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/7/18 0018 16:34
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：热搜关键词
 */
public class ToGetHotSearchListener implements Response.Listener<JSONObject> {
    private Context context;
    private List<String> list;//热搜的接收集
    private MyGridView hotGrid;
    private MyGridView listView;
    private OriginalSearchGridAdapter adapter;

    public ToGetHotSearchListener(Context context, List<String> list, MyGridView hotGrid, MyGridView listView, OriginalSearchGridAdapter adapter) {
        this.context = context;
        this.list = list;
        this.adapter = adapter;
        this.listView = listView;
        this.hotGrid = hotGrid;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //返回成功
            try {
                JSONArray jsonArray = jsonObject.getJSONArray("list");
                List<JSONObject> jsonObjectList = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObjectList.add((JSONObject) jsonArray.get(i));
                }
                for (int j = 0; j < jsonObjectList.size(); j++) {
                    list.add(jsonObjectList.get(j).getString("wordname"));
                }
                hotGrid.setAdapter(new OriginalSearchGridAdapter(context, list));
                listView.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            //返回失败
            Status.fail(context, jsonObject);
        }

    }
}
