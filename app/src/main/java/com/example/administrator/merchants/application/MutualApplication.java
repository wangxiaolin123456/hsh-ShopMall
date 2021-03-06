package com.example.administrator.merchants.application;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.administrator.merchants.http.show.MerchantsOrderShowBean;
import com.example.administrator.merchants.http.show.ShoppingCarShowBean;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

/**
 * 韩宇  18698802347@163.com
 */
public class MutualApplication extends Application {
    private static MutualApplication application;
    public static RequestQueue requestQueue;
    public static List<ShoppingCarShowBean> chooseList;//购物车里面的被选中的集合
    public static String totalConfirm = "0.00";
    public static String mTN = "";
    public static List<MerchantsOrderShowBean> chooseOrderList;//点单详情里别选中的订单临时保存
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.init(this);            // 初始化 JPush
        application = this;
        requestQueue = Volley.newRequestQueue(this);
        chooseList = new ArrayList<>();//购物车里面的被选中的集合
        chooseOrderList = new ArrayList<>();//点单详情里别选中的订单临时保存
    }

    /**
     * 实例化
     *
     * @return
     */
    public static MutualApplication getIntence() {
        return application;
    }

    /**
     * @return
     */
    public static RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }
}
