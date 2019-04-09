package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Response;
import com.example.administrator.merchants.activity.OriginalHomePageActivity;
import com.example.administrator.merchants.activity.OriginalSearchResultActivity;
import com.example.administrator.merchants.activity.OriginalSecondPageActivity;
import com.example.administrator.merchants.adapter.OriginalHomeYouLikeListAdapter;
import com.example.administrator.merchants.http.HttpUrl;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.http.show.CommodityShowBean;
import com.example.administrator.merchants.common.views.RefreshLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/7/20 0020 14:33
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：原产地商品搜索结果列表
 */
public class OriginalSearchResultListener implements Response.Listener<JSONObject> {
    private Context context;
    private int type;
    private ListView aftSearchListView;
    private OriginalHomeYouLikeListAdapter originalHomeYouLikeListAdapter;
    private List<CommodityShowBean> commodityShowBeanList;
    private RefreshLayout swipeLayout;
    private View footView;

    public OriginalSearchResultListener(Context context, int type, List<CommodityShowBean> commodityShowBeanList, ListView aftSearchListView, OriginalHomeYouLikeListAdapter originalHomeYouLikeListAdapter
            , RefreshLayout swipeLayout, View footView) {
        this.context = context;
        this.type = type;
        this.swipeLayout = swipeLayout;
        this.commodityShowBeanList = commodityShowBeanList;
        this.aftSearchListView = aftSearchListView;
        this.footView = footView;
        this.originalHomeYouLikeListAdapter = originalHomeYouLikeListAdapter;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //返回成功
            try {
                if (type == 0) {
                    commodityShowBeanList.clear();
                }
                JSONArray jsonArray = jsonObject.getJSONArray("list");
                List<JSONObject> jsonObjectList = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObjectList.add((JSONObject) jsonArray.get(i));
                }
                for (int j = 0; j < jsonObjectList.size(); j++) {
                    CommodityShowBean commodityShowBean = new CommodityShowBean();
                    commodityShowBean.setContractno(jsonObjectList.get(j).getString("contractno"));
                    commodityShowBean.setImgsfile(HttpUrl.BaseImageUrl + jsonObjectList.get(j).getString("imgsfile"));//图片地址在这个参数里
                    commodityShowBean.setMerdescr(jsonObjectList.get(j).getString("merdescr"));
                    commodityShowBean.setMerid(jsonObjectList.get(j).getString("merid"));
                    commodityShowBean.setMername(jsonObjectList.get(j).getString("mername"));
                    commodityShowBean.setMonthsalenum(jsonObjectList.get(j).getInt("monthsalenum"));
                    commodityShowBean.setSaleprice(jsonObjectList.get(j).getDouble("saleprice"));
                    commodityShowBean.setScoreavg(jsonObjectList.get(j).getInt("scoreavg"));
                    commodityShowBean.setStoreid(jsonObjectList.get(j).getString("storeid"));
                    //库存
                    if (jsonObjectList.get(j).getInt("storenum") <= 0) {
                        commodityShowBean.setStorenum(0);
                    } else {
                        commodityShowBean.setStorenum(jsonObjectList.get(j).getInt("storenum"));
                    }
                    commodityShowBeanList.add(commodityShowBean);
                }
                if (type == 0) {
                    aftSearchListView.setAdapter(originalHomeYouLikeListAdapter);
                    swipeLayout.setRefreshing(false);
                } else if (type == 1) {
                    originalHomeYouLikeListAdapter.notifyDataSetChanged();
                    OriginalSearchResultActivity.s = 1;
                    swipeLayout.setLoading(false);
                }else if (type==2){
                    originalHomeYouLikeListAdapter.notifyDataSetChanged();
                    OriginalSecondPageActivity.s = 1;
                    swipeLayout.setLoading(false);
                }
                if (jsonObjectList.size() < 15) {
                    swipeLayout.setOnLoadListener(null);
                    aftSearchListView.removeFooterView(footView);
                    aftSearchListView.addFooterView(footView);
                } else {
                    swipeLayout.setOnLoadListener((RefreshLayout.OnLoadListener) context);
                    aftSearchListView.removeFooterView(footView);
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