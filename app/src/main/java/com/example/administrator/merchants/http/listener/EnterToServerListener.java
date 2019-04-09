//package com.example.administrator.merchants.http.listener;
//
//import android.content.Context;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.ListView;
//
//import com.android.volley.Response;
//import com.example.administrator.merchants.adapter.HomePageAdapter;
//import com.example.administrator.merchants.common.GlideTest;
//import com.example.administrator.merchants.fragment.HomePageFragment;
//import com.example.administrator.merchants.http.HttpUrl;
//import com.example.administrator.merchants.http.Status;
//import com.example.administrator.merchants.http.show.CommodityShowBean;
//import com.example.administrator.merchants.common.DateUtils;
//import com.example.administrator.merchants.common.views.RefreshLayout;
//import com.example.administrator.merchants.utils.LogUtil;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 作者：韩宇 on 2017/8/18 0018 09:05
// * 邮箱：18698802347@163.com
// * QQ：1760010478
// * 功能：公告
// */
//public class EnterToServerListener implements Response.Listener<JSONObject> {
//    private Context context;
//    private int type;
//    private List<CommodityShowBean> list;
//    private ImageView anim;
//    private RefreshLayout swipeLayout;
//    private ListView myListView;
//    private View footView;
//    private HomePageAdapter homePageAdapter;
//    private RefreshLayout.OnLoadListener onLoadListener;
//
//    public EnterToServerListener(Context context, int type, List<CommodityShowBean> list, ImageView anim, RefreshLayout swipeLayout, ListView myListView
//            , View footView, HomePageAdapter homePageAdapter,RefreshLayout.OnLoadListener onLoadListener) {
//        this.context = context;
//        this.type = type;
//        this.list = list;
//        this.anim = anim;
//        this.swipeLayout = swipeLayout;
//        this.myListView = myListView;
//        this.footView = footView;
//        this.homePageAdapter = homePageAdapter;
//        this.onLoadListener=onLoadListener;
//    }
//
//    @Override
//    public void onResponse(JSONObject jsonObject) {
//        LogUtil.i("首页公告列表"+jsonObject.toString());
//        if (Status.status(jsonObject)) {
//            //返回成功
//            if (type == 0) {
//                list.clear();
//            }
//            try {
//                JSONArray jsonArray = jsonObject.getJSONArray("list");
//                List<JSONObject> jsonObjectList = new ArrayList<>();
//                for (int i = 0; i < jsonArray.length(); i++) {
//                    jsonObjectList.add((JSONObject) jsonArray.get(i));
//                }
//                for (int j = 0; j < jsonObjectList.size(); j++) {
//                    CommodityShowBean commodityShowBean = new CommodityShowBean();
//                    commodityShowBean.setNoticeid(jsonObjectList.get(j).getString("noticeid"));
//                    commodityShowBean.setTime(DateUtils.getEnterTime(Long.parseLong(jsonObjectList.get(j).getJSONObject("createtime").
//                            getString("time"))));
//                    commodityShowBean.setImgsfile(HttpUrl.BaseImageUrl + jsonObjectList.get(j).getString("imgsfile"));//图片地址在这个参数里
//                    commodityShowBean.setMerdescr(jsonObjectList.get(j).getString("noticedescr"));
//                    commodityShowBean.setMername(jsonObjectList.get(j).getString("noticetitle"));
//                    list.add(commodityShowBean);
//                }
//                if (type == 0) {
//                    GlideTest.imageCancle(anim);
//                    swipeLayout.setRefreshing(false);
//                    myListView.setAdapter(homePageAdapter);
//                } else if (type == 1) {
//                    homePageAdapter.notifyDataSetChanged();
//                    HomePageFragment.s = 1;
//                    swipeLayout.setLoading(false);
//                }
//                if (jsonObjectList.size() < 15) {
//                    swipeLayout.setOnLoadListener(null);
//                    myListView.removeFooterView(footView);
//                    myListView.addFooterView(footView);
//                } else {
//                    swipeLayout.setOnLoadListener(onLoadListener);
//                    myListView.removeFooterView(footView);
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        } else {
//            //返回失败
//            Status.fail(context, jsonObject);
//        }
//    }
//}