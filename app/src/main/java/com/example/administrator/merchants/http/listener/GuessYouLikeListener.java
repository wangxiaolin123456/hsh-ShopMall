package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.widget.ListView;

import com.android.volley.Response;
import com.example.administrator.merchants.adapter.OriginalHomeYouLikeListAdapter;
import com.example.administrator.merchants.http.HttpUrl;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.http.show.CommodityShowBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/7/18 0018 08:53
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：原产地首页猜你喜欢
 */
public class GuessYouLikeListener implements Response.Listener<JSONObject> {
    private Context context;
    private List<CommodityShowBean> commodityShowBeans;
    private ListView listView;

    public GuessYouLikeListener(Context context, List<CommodityShowBean> commodityShowBeans, ListView listView) {
        this.context = context;
        this.commodityShowBeans = commodityShowBeans;
        this.listView = listView;
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
                    CommodityShowBean commodityShowBean = new CommodityShowBean();
                    commodityShowBean.setContractno(jsonObjectList.get(j).getString("contractno"));
                    commodityShowBean.setImgsfile(HttpUrl.BaseImageUrl + jsonObjectList.get(j).getString("imgsfile"));//图片地址在这个参数里
                    commodityShowBean.setMerdescr(jsonObjectList.get(j).getString("merdescr"));
                    commodityShowBean.setMerid(jsonObjectList.get(j).getString("merid"));
                    commodityShowBean.setMername(jsonObjectList.get(j).getString("mername"));
                    commodityShowBean.setMonthsalenum(jsonObjectList.get(j).getInt("monthsalenum"));
                    commodityShowBean.setSaleprice(jsonObjectList.get(j).getDouble("saleprice"));
                    commodityShowBean.setScoreavg(jsonObjectList.get(j).getDouble("scoreavg"));
                    commodityShowBean.setStoreid(jsonObjectList.get(j).getString("storeid"));
                    //库存
                    if (jsonObjectList.get(j).getInt("storenum") <= 0) {
                        commodityShowBean.setStorenum(0);
                    } else {
                        commodityShowBean.setStorenum(jsonObjectList.get(j).getInt("storenum"));
                    }
                    commodityShowBeans.add(commodityShowBean);
                }
                listView.setAdapter(new OriginalHomeYouLikeListAdapter(context, commodityShowBeans));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            //返回失败
            Status.fail(context, jsonObject);
        }
    }
}
