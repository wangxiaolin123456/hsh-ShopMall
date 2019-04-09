package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.widget.ListView;

import com.android.volley.Response;
import com.example.administrator.merchants.adapter.AddressAdapter;
import com.example.administrator.merchants.http.show.ClassManagementShowBean;
import com.example.administrator.merchants.adapter.GoodsManagerClassificationAdapter;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.http.show.PopupMenuShowBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/6/23 0023 14:39
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：商家分类管理
 */
public class ClassifyManagementListener implements Response.Listener<JSONObject> {
    private Context context;
    private ListView listView;
    private GoodsManagerClassificationAdapter goodsManagerClassificationAdapter;
    private List<ClassManagementShowBean> listClassify;
    private int type;
    private List<PopupMenuShowBean> list;

    /**
     * 分类管理中的分类列表展示
     *
     * @param context
     * @param listView
     * @param goodsManagerClassificationAdapter
     * @param listClassify
     * @param type
     */
    public ClassifyManagementListener(Context context, ListView listView, GoodsManagerClassificationAdapter goodsManagerClassificationAdapter, List<ClassManagementShowBean> listClassify, int type) {
        this.context = context;
        this.listView = listView;
        this.goodsManagerClassificationAdapter = goodsManagerClassificationAdapter;
        this.listClassify = listClassify;
        this.type = type;
    }

    /***
     * 添加商品中的分类列表展示
     *
     * @param context
     * @param type
     * @param listView
     * @param list
     */
    public ClassifyManagementListener(Context context, int type, ListView listView, List<PopupMenuShowBean> list) {
        this.context = context;
        this.type = type;
        this.listView = listView;
        this.list = list;

    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //返回成功
            try {
                if (type == 0) {
                    listClassify.clear();
                    JSONArray jsonArray = jsonObject.getJSONArray("list");
                    List<JSONObject> objectList = new ArrayList<>();
                    if (jsonArray.length() != 0) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = (JSONObject) jsonArray.get(i);
                            objectList.add(object);
                        }
                        for (int j = 0; j < objectList.size(); j++) {
                            ClassManagementShowBean s = new ClassManagementShowBean();
                            s.setMenuname(objectList.get(j).getString("menuname"));
                            s.setMenuid(objectList.get(j).getString("menuid"));
                            s.setStu(0);
                            listClassify.add(s);
                        }
                    }
                    listView.setAdapter(goodsManagerClassificationAdapter);
                } else if (type == 1) {
                    list.clear();
                    JSONArray jsonArray = jsonObject.getJSONArray("list");
                    List<JSONObject> objectList = new ArrayList<>();
                    if (jsonArray.length() != 0) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = (JSONObject) jsonArray.get(i);
                            objectList.add(object);
                        }
                        for (int j = 0; j < objectList.size(); j++) {
                            PopupMenuShowBean s = new PopupMenuShowBean();
                            s.setMenuname(objectList.get(j).getString("menuname"));
                            s.setMenuid(objectList.get(j).getString("menuid"));
                            list.add(s);
                        }
                    }
                    AddressAdapter firstMenuAdapter = new AddressAdapter(context, list);
                    listView.setAdapter(firstMenuAdapter);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            //返回失败
            Status.fail(context, jsonObject);
        }

    }
}
