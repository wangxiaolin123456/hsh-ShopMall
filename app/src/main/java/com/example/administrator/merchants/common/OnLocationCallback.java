package com.example.administrator.merchants.common;

import com.baidu.location.BDLocation;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：百度定位回调
 */
public interface OnLocationCallback {
    void onLocationSuccess(BDLocation location);

    void onLocationFailed();
}
