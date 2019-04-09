package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.android.volley.Response;
import com.example.administrator.merchants.activity.OriginalSecondPageActivity;
import com.example.administrator.merchants.adapter.OriginalHomeMenuAdapter;
import com.example.administrator.merchants.common.GlideTest;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.http.show.PopupMenuShowBean;
import com.example.administrator.merchants.common.views.MyGridView;
import com.example.administrator.merchants.utils.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/7/18 0018 09:08
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：原产地采购首页图标
 */
public class OriginalClassifyListener implements Response.Listener<JSONObject> {
    private List<PopupMenuShowBean> originalGridBeanList;
    private Context context;
    private ImageView anim;
    private MyGridView gridView;

    public OriginalClassifyListener(Context context, ImageView anim, MyGridView gridView) {
        this.context = context;
        this.anim = anim;
        this.gridView = gridView;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //返回成功
            try {
                JSONArray menulist = jsonObject.getJSONArray("menulist");
                List<JSONObject> objectList = new ArrayList<>();

                for (int i = 0; i < menulist.length(); i++) {
                    objectList.add((JSONObject) menulist.get(i));
                }
                originalGridBeanList = new ArrayList<>();
                for (int i = 0; i < objectList.size(); i++) {
                    PopupMenuShowBean originalGridBean = new PopupMenuShowBean();
                    originalGridBean.setImgpfile(objectList.get(i).getString("imgpfile"));
                    originalGridBean.setMenuid(objectList.get(i).getString("menuid"));
                    originalGridBean.setMenuname(objectList.get(i).getString("menuname"));
                    originalGridBean.setType(0);
                    originalGridBeanList.add(originalGridBean);
                }
                gridView.setAdapter(new OriginalHomeMenuAdapter(context, originalGridBeanList,"ycd"));
                GlideTest.imageCancle(anim);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            //返回失败
            Status.fail(context, jsonObject);
        }

    }
}
