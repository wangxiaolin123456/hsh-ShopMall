package com.example.administrator.merchants.common;

import android.content.Context;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：百度定位控制类
 */
public class OnLocationController implements BDLocationListener {
    private OnLocationCallback callback;
    private LocationClient locationClient;

    public OnLocationController(OnLocationCallback callback) {
        this.callback = callback;
    }

    /**
     * 初始化百度单位以及配置参数
     *
     * @param context
     * @param times   循环定位的毫秒数，必须大于1000
     */
    public void getLocation(final Context context, int times) {
        locationClient = new LocationClient(context);
        locationClient.registerLocationListener(this);
        // 设置定位条件
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置返回值的坐标类型。国内坐标
        option.setScanSpan(times);// 可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        locationClient.setLocOption(option);
        locationClient.start();
    }

    /**
     * 停止定位
     */
    public void stopLocationClient() {
        locationClient.unRegisterLocationListener(this);
        locationClient.stop();
    }

    @Override
    public void onReceiveLocation(BDLocation location) {
        if (null != location) {
            callback.onLocationSuccess(location);
        } else {
            callback.onLocationFailed();
        }
    }
}