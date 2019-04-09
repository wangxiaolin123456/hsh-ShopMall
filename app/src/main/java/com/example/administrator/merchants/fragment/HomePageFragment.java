package com.example.administrator.merchants.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.administrator.merchants.R;
import com.example.administrator.merchants.activity.MyMessageListActivity;
import com.example.administrator.merchants.activity.NoticeDetailsActivity;
import com.example.administrator.merchants.activity.OriginalSearchActivity;
import com.example.administrator.merchants.adapter.HomePageAdapter;
import com.example.administrator.merchants.adapter.OriginalHomeMenuAdapter;
import com.example.administrator.merchants.adapter.PrefectureImageAdapter;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseFragment;
import com.example.administrator.merchants.common.GlideTest;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.common.toast.CustomToast;
import com.example.administrator.merchants.common.views.CustomerViewPage;
import com.example.administrator.merchants.common.views.MyGridView;
import com.example.administrator.merchants.common.views.RefreshLayout;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.HttpUrl;
import com.example.administrator.merchants.http.post.ListPostBean;
import com.example.administrator.merchants.http.post.StoreIdPostBean;
import com.example.administrator.merchants.http.show.CommodityShowBean;
import com.example.administrator.merchants.http.show.PopupMenuShowBean;
import com.example.administrator.merchants.http.show.PrefectureImageBean;
import com.example.administrator.merchants.utils.LogUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 作者：韩宇 on 2017/8/18 0018 09:05
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：首页
 */
public class HomePageFragment extends BaseFragment {
    private View footView;
    //    private List<CommodityShowBean> list;
    private MyGridView myGridView;
    private View view;
    private CustomerViewPage viewPage;
    private ImageView anim;
    private ListView myListView;//公告列表 --专区图片列表
    //    private HomePageAdapter homePageAdapter;
    private TextView messageRead;
    private List<PopupMenuShowBean> popupMenuShowBeanList;
    private OriginalHomeMenuAdapter originalHomeMenuAdapter;
    private View header;
    private Unbinder unbinder;
    @BindView(R.id.home_location_tv)
    TextView locationTv;//定位
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_page, null);
        initLocation();
        unbinder = ButterKnife.bind(this, view);
        footView = getActivity().getLayoutInflater().inflate(R.layout.no_more_date_footview, null);
        popupMenuShowBeanList = new ArrayList<>();
        anim = (ImageView) view.findViewById(R.id.image_loading);
        messageRead = (TextView) view.findViewById(R.id.car_dot);
        GlideTest.imageGif(getContext(), anim);
        originalHomeMenuAdapter = new OriginalHomeMenuAdapter(getActivity(), popupMenuShowBeanList, "home");
        myListView = (ListView) view.findViewById(R.id.homepage_enter_list);
        //得有判断是中小企业还是商户啊
        initView();//初始化布局
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(com.scwang.smartrefresh.layout.api.RefreshLayout refreshlayout) {
//                Http.toGetText(getActivity());
                StoreIdPostBean storeIdPostBean = new StoreIdPostBean();
                storeIdPostBean.setStoreid(UserInfo.getInstance().getUser(getContext()).getStoreid());
                Http.getMessageNumber(getActivity(), storeIdPostBean, messageRead);
                homeList();
            }
        });
        return view;
    }

    /**
     * 初始化布局
     */
    @SuppressLint({"InlinedApi", "InflateParams"})
    private void initView() {
        header = getActivity().getLayoutInflater().inflate(R.layout.view_home_headview, null);
        myGridView = (MyGridView) header.findViewById(R.id.one);
        myGridView.setAdapter(originalHomeMenuAdapter);
        viewPage = (CustomerViewPage) header.findViewById(R.id.vp);
        Http.pagerToServer(getActivity(), viewPage);
//        homeList();
        myListView.addHeaderView(header);
    }


    /**
     * 商家类型决定模块多少
     */
    public void login() {
        if ("1".equals(UserInfo.getInstance().getUser(getContext()).getLevelno()) || "2".equals(UserInfo.getInstance().getUser(getContext()).getLevelno())) {
            //中小企业
            popupMenuShowBeanList.clear();
            PopupMenuShowBean funShowBean = new PopupMenuShowBean();
            funShowBean.setMenuid("fuwu");
            funShowBean.setPath(R.drawable.ptfw);
            funShowBean.setMenuname("配套服务");
            funShowBean.setType(1);
            popupMenuShowBeanList.add(funShowBean);
            PopupMenuShowBean funShowBean1 = new PopupMenuShowBean();
            funShowBean1.setMenuid("xuqiu");
            funShowBean1.setPath(R.drawable.qyxq);
            funShowBean1.setMenuname("企业需求");
            funShowBean1.setType(1);
            popupMenuShowBeanList.add(funShowBean1);
            PopupMenuShowBean funShowBean2 = new PopupMenuShowBean();
            funShowBean2.setMenuid("jinrong");
            funShowBean2.setPath(R.drawable.jrzl);
            funShowBean2.setMenuname("金融助力");
            funShowBean2.setType(1);
            popupMenuShowBeanList.add(funShowBean2);
            PopupMenuShowBean funShowBean3 = new PopupMenuShowBean();
            funShowBean3.setMenuid("yuan");
            funShowBean3.setPath(R.drawable.ycd);
            funShowBean3.setMenuname("原产地采购");
            funShowBean3.setType(1);
            popupMenuShowBeanList.add(funShowBean3);
            originalHomeMenuAdapter.notifyDataSetChanged();
        } else {
            popupMenuShowBeanList.clear();
            PopupMenuShowBean funShowBean1 = new PopupMenuShowBean();
            funShowBean1.setMenuid("message");
            funShowBean1.setPath(R.drawable.xxgl);
            funShowBean1.setMenuname("信息管理");
            funShowBean1.setType(1);
            popupMenuShowBeanList.add(funShowBean1);
            PopupMenuShowBean funShowBean2 = new PopupMenuShowBean();
            funShowBean2.setMenuid("zhaopin");
            funShowBean2.setPath(R.drawable.zpxx);
            funShowBean2.setMenuname("招聘信息");
            funShowBean2.setType(1);
            popupMenuShowBeanList.add(funShowBean2);
            PopupMenuShowBean funShowBean3 = new PopupMenuShowBean();
            funShowBean3.setMenuid("jinrong");
            funShowBean3.setPath(R.drawable.jrzl);
            funShowBean3.setMenuname("金融助力");
            funShowBean3.setType(1);
            popupMenuShowBeanList.add(funShowBean3);
            PopupMenuShowBean funShowBean4 = new PopupMenuShowBean();
            funShowBean4.setMenuid("yuan");
            funShowBean4.setPath(R.drawable.ycd);
            funShowBean4.setMenuname("原产地采购");
            funShowBean4.setType(1);
            popupMenuShowBeanList.add(funShowBean4);
            PopupMenuShowBean funShowBean5 = new PopupMenuShowBean();
            funShowBean5.setMenuid("bill");
            funShowBean5.setPath(R.drawable.dzd);
            funShowBean5.setMenuname("对账单");
            funShowBean5.setType(1);
            popupMenuShowBeanList.add(funShowBean5);
            PopupMenuShowBean funShowBean6 = new PopupMenuShowBean();
            funShowBean6.setMenuid("shangpin");
            funShowBean6.setPath(R.drawable.spgl);
            funShowBean6.setMenuname("商品管理");
            funShowBean6.setType(1);
            popupMenuShowBeanList.add(funShowBean6);
            PopupMenuShowBean funShowBean7 = new PopupMenuShowBean();
            funShowBean7.setMenuid("xuqiu");
            funShowBean7.setPath(R.drawable.qyxq);
            funShowBean7.setMenuname("企业需求");
            funShowBean7.setType(1);
            popupMenuShowBeanList.add(funShowBean7);
            PopupMenuShowBean funShowBean8 = new PopupMenuShowBean();
            funShowBean8.setMenuid("fuwu");
            funShowBean8.setPath(R.drawable.ptfw);
            funShowBean8.setMenuname("配套服务");
            funShowBean8.setType(1);
            popupMenuShowBeanList.add(funShowBean8);
            originalHomeMenuAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 公告列表
     */
//    public void enterToServer(String offset, String limit, int type) {
//        ListPostBean listPostBean = new ListPostBean();
//        listPostBean.setOffset(offset);
//        listPostBean.setLimit(limit);
//        Http.enterToServer(getActivity(), listPostBean, type, list, anim, swipeLayout, myListView, footView, homePageAdapter, this);
//    }
    @Override
    public void onStop() {
        super.onStop();
        MutualApplication.getRequestQueue().cancelAll("toGetText103");
        MutualApplication.getRequestQueue().cancelAll("toMessageNumber104");
        MutualApplication.getRequestQueue().cancelAll("pagerToServer105");
        MutualApplication.getRequestQueue().cancelAll("homeList");
    }


//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        if (position < (list.size() + 1)) {
//            Intent intent = new Intent();
//            intent.putExtra("noticeid", list.get(position - 1).getNoticeid());
//            intent.putExtra("image", list.get(position - 1).getImgsfile());
//            intent.putExtra("title", list.get(position - 1).getMername());
//            if (getActivity() != null) {
//                intent.setClass(getActivity(), NoticeDetailsActivity.class);
//            }
//            startActivity(intent);
//        }
//    }

    @OnClick({R.id.home_message_line, R.id.home_search_line})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_message_line:
                if (getActivity() != null) {
                    startActivity(new Intent(getActivity(), MyMessageListActivity.class));
                }
                break;
            case R.id.home_search_line:
                if (getActivity() != null) {
                    startActivity(new Intent(getActivity(), OriginalSearchActivity.class));
                }
                break;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        login();
        StoreIdPostBean storeIdPostBean = new StoreIdPostBean();
        storeIdPostBean.setStoreid(UserInfo.getInstance().getUser(getContext()).getStoreid());
        Http.getMessageNumber(getActivity(), storeIdPostBean, messageRead);
        homeList();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden == false) {
            login();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    public LocationClient mLocationClient;
    private MyLocationListener myListener = new MyLocationListener();


    private void initLocation() {


        mLocationClient = new LocationClient(getContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);


        LocationClientOption option = new LocationClientOption();
        //就是这个方法设置为true，才能获取当前的位置信息
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("gcj02");//可选，默认gcj02，设置返回的定位结果坐标系
        //int span = 1000;
        //option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        mLocationClient.setLocOption(option);

        mLocationClient.start();
    }


    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            //Receive Location
            String addr = location.getAddrStr();    //获取详细地址信息
            String country = location.getCountry();    //获取国家
            String province = location.getProvince();    //获取省份
            String city = location.getCity();    //获取城市
            String district = location.getDistrict();    //获取区县
            String street = location.getStreet();    //获取街道信息
            //经纬度
            double lati = location.getLatitude();
            double longa = location.getLongitude();
            //打印出当前位置
            LogUtil.i("TAG" + "location.getAddrStr()=" + location.getAddrStr());
            //打印出当前城市
            LogUtil.i("TAG" + "location.getCity()=" + location.getCity());
            //返回码
            int i = location.getLocType();
            if (city == null) {
                locationTv.setText("沈阳市");
            } else {
                locationTv.setText(city);
            }
        }
    }

    private List<PrefectureImageBean> prefectureList = new ArrayList<>();
    private PrefectureImageBean prefectureImageBean;

    /**
     * 获取首页专区列表
     */
    private void homeList() {
        try {
            JSONObject obj = new JSONObject();
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST, HttpUrl.areaImageList, obj,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            if (refreshLayout != null) {
                                if (refreshLayout.isRefreshing()) {
                                    refreshLayout.finishRefresh();
                                }
                                if (refreshLayout.isLoading()) {
                                    LogUtil.i("===========onSuccessResponse==========setLoadingMore");
                                    refreshLayout.finishLoadMore();
                                }
                            }
                            LogUtil.i("首页专区列表" + response.toString());
                            try {
                                if ("true".equals(response
                                        .getString("success"))) {
                                    try {
                                        JSONArray jsonArray = response.getJSONArray("grouplist");
                                        List<JSONObject> jsonObjectList = new ArrayList<>();
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            jsonObjectList.add((JSONObject) jsonArray.get(i));
                                        }
                                        prefectureList.clear();
                                        for (int j = 0; j < jsonObjectList.size(); j++) {
                                            prefectureImageBean = new PrefectureImageBean();
                                            prefectureImageBean.setGroupid(jsonObjectList.get(j).getString("groupid"));
                                            prefectureImageBean.setIstitle(jsonObjectList.get(j).getString("istitle"));
                                            prefectureImageBean.setImgpfile(HttpUrl.BaseImageUrl + jsonObjectList.get(j).getString("imgpfile"));
                                            prefectureImageBean.setColnum(jsonObjectList.get(j).getInt("colnum"));
                                            prefectureImageBean.setRownum(jsonObjectList.get(j).getInt("rownum"));
                                            prefectureImageBean.setImgheight(jsonObjectList.get(j).getInt("imgheight"));
                                            prefectureImageBean.setImgwidth(jsonObjectList.get(j).getInt("imgwidth"));
                                            prefectureList.add(prefectureImageBean);
                                        }
                                        GlideTest.imageCancle(anim);
                                        myListView.setAdapter(new PrefectureImageAdapter(getActivity(), prefectureList));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                } else {
                                    if (getActivity() != null) {
                                        CustomToast.getInstance(getActivity()).setMessage(response.getString("message")
                                                + "");
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (refreshLayout != null) {
                        if (refreshLayout.isRefreshing()) {
                            refreshLayout.finishRefresh();
                        }
                        if (refreshLayout.isLoading()) {
                            LogUtil.i("===========onSuccessResponse==========setLoadingMore");
                            refreshLayout.finishLoadMore();
                        }
                    }
                }
            });
            jsonObjectRequest.setTag("homeList");
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy
                    (500000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

