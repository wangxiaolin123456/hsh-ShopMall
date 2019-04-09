package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Response;
import com.example.administrator.merchants.adapter.CommonAdapter;
import com.example.administrator.merchants.fragment.CommentFragment;
import com.example.administrator.merchants.http.HttpUrl;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.http.show.CommonImageShowBean;
import com.example.administrator.merchants.http.show.GoodsDetailShowBean;
import com.example.administrator.merchants.http.show.ImageShowBean;
import com.example.administrator.merchants.common.views.RefreshLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/8/16 0016 09:22
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：评论
 */
public class CommonListener implements Response.Listener<JSONObject> {
    private Context context;
    private int type;
    private List<GoodsDetailShowBean> list;
    private List<ImageShowBean> list1;
    private ListView listView;
    private CommonAdapter commonAdapter;
    private RefreshLayout swipeLayout;
    private View footView;
    private RefreshLayout.OnLoadListener onLoadListener;

    public CommonListener(Context context, int type, List<GoodsDetailShowBean> list, List<ImageShowBean> list1, ListView listView
            , CommonAdapter commonAdapter, RefreshLayout swipeLayout, View footView,RefreshLayout.OnLoadListener onLoadListener) {
        this.context = context;
        this.type = type;
        this.list = list;
        this.list1 = list1;
        this.listView = listView;
        this.commonAdapter = commonAdapter;
        this.swipeLayout = swipeLayout;
        this.footView = footView;
        this.onLoadListener=onLoadListener;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //返回成功
            if (type == 0) {
                list.clear();
                list1.clear();
            }
            try {
                //图片集合
                JSONArray jsonArray1 = jsonObject.getJSONArray("imglist");
                List<JSONObject> listJSONObject1 = new ArrayList<>();
                for (int i = 0; i < jsonArray1.length(); i++) {
                    JSONObject object = (JSONObject) jsonArray1.get(i);
                    listJSONObject1.add(object);
                }
                JSONArray jsonArray = jsonObject.getJSONArray("list");
                List<JSONObject> listJSONObject = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = (JSONObject) jsonArray.get(i);
                    listJSONObject.add(object);
                }
                for (int i = 0; i < listJSONObject1.size(); i++) {
                    ImageShowBean imageShowBean = new ImageShowBean();
                    imageShowBean.setEvaluateid(listJSONObject1.get(i).getString("evaluateid"));
                    imageShowBean.setImgpfile(HttpUrl.BaseImageUrl + listJSONObject1.get(i).getString("imgpfile"));
                    imageShowBean.setImgsfile(HttpUrl.BaseImageUrl + listJSONObject1.get(i).getString("imgsfile"));
                    list1.add(imageShowBean);
                }
                for (int j = 0; j < listJSONObject.size(); j++) {
                    GoodsDetailShowBean goodsDetailBean = new GoodsDetailShowBean();
                    goodsDetailBean.setEvaluateid(listJSONObject.get(j).getString("evaluateid"));
                    goodsDetailBean.setEvaluatedescr(listJSONObject.get(j).getString("evaluatedescr"));
                    goodsDetailBean.setEvaluatedscore(listJSONObject.get(j).getInt("evaluatescore"));
                    goodsDetailBean.setEvaluatetimestr(listJSONObject.get(j).getString("evaluatetimestr"));
                    if ("".equals(listJSONObject.get(j).getString("imgsfile"))) {
                        goodsDetailBean.setImgsfile("");
                    } else {
                        goodsDetailBean.setImgsfile(HttpUrl.BaseImageUrl + listJSONObject.get(j).getString("imgsfile"));
                    }
                    goodsDetailBean.setStorename(listJSONObject.get(j).getString("storename"));
                    goodsDetailBean.setIsanonymous(listJSONObject.get(j).getString("isanonymous"));
                    goodsDetailBean.setMerid(listJSONObject.get(j).getString("merid"));
                    goodsDetailBean.setOrdno(listJSONObject.get(j).getString("ordno"));
                    list.add(goodsDetailBean);
                }
                for (int i = 0; i < list.size(); i++) {
                    List<CommonImageShowBean> images = new ArrayList<>();
                    for (int j = 0; j < list1.size(); j++) {
                        if (list.get(i).getEvaluateid().equals(list1.get(j).getEvaluateid())) {
                            CommonImageShowBean commonImageShowBean = new CommonImageShowBean();
                            commonImageShowBean.setImages(list1.get(j).getImgsfile());
                            commonImageShowBean.setImagep(list1.get(j).getImgpfile());
                            images.add(commonImageShowBean);
                        }
                    }
                    list.get(i).setList(images);
                }
                if (type == 0) {
                    listView.setAdapter(commonAdapter);
                    swipeLayout.setRefreshing(false);
                } else if (type == 1) {
                    commonAdapter.notifyDataSetChanged();
                    CommentFragment.s = 1;
                    swipeLayout.setLoading(false);
                }

                if (listJSONObject.size() < 15) {
                    swipeLayout.setOnLoadListener(null);
                    listView.removeFooterView(footView);
                    listView.addFooterView(footView);
                } else {
                    swipeLayout.setOnLoadListener(onLoadListener);
                    listView.removeFooterView(footView);
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
