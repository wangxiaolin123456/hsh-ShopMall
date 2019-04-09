package com.example.administrator.merchants.http.listener;

import android.content.Context;

import com.android.volley.Response;
import com.example.administrator.merchants.adapter.GoodsManagerClassificationAdapter;
import com.example.administrator.merchants.dialog.AddClassifyDialog;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.http.show.ClassManagementShowBean;
import com.example.administrator.merchants.common.toast.CustomToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/8/13 0013 15:52
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：分类的添加、修改、和获取
 */
public class ClassifyListener implements Response.Listener<JSONObject> {
    private Context context;
    private int type;
    private List<ClassManagementShowBean> list;
    private GoodsManagerClassificationAdapter goodsManagerClassificationAdapter;
    private AddClassifyDialog addClassifyDialog;

    public ClassifyListener(Context context, int type, AddClassifyDialog addClassifyDialog) {
        this.context = context;
        this.type = type;
        this.addClassifyDialog = addClassifyDialog;
    }

    public ClassifyListener(Context context, int type, List<ClassManagementShowBean> list
            , GoodsManagerClassificationAdapter goodsManagerClassificationAdapter, AddClassifyDialog addClassifyDialog) {
        this.context = context;
        this.type = type;
        this.list = list;
        this.goodsManagerClassificationAdapter = goodsManagerClassificationAdapter;
        this.addClassifyDialog = addClassifyDialog;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //返回成功
            if (type == 1) {
                CustomToast.getInstance(context).setMessage("添加成功!");
                addClassifyDialog.getClassify();
            } else if (type == 2) {
                CustomToast.getInstance(context).setMessage("修改成功!");
                addClassifyDialog.getClassify();
            } else if (type == 3) {
                list.clear();
                JSONArray jsonArray = null;
                try {
                    jsonArray = jsonObject.getJSONArray("list");
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
                            list.add(s);
                        }
                    }
                    goodsManagerClassificationAdapter.notifyDataSetChanged();
                    addClassifyDialog.dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else {
            //返回失败
            if (type == 3) {
                addClassifyDialog.dismiss();
            }
            Status.fail(context, jsonObject);
        }
    }
}
