package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.android.volley.Response;
import com.example.administrator.merchants.activity.StoreInformationManagementActivity;
import com.example.administrator.merchants.common.GlideTest;
import com.example.administrator.merchants.dialog.DeliveryDistanceDialog;
import com.example.administrator.merchants.http.Status;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者：韩宇 on 2017/8/5 0005 10:57
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：获取商家信息
 */
public class StoreGetMessageListener implements Response.Listener<JSONObject> {
    private Context context;
    private ToggleButton reserve;//是否支持预定
    private TextView deliveryRadiusStart, radius;
    private LinearLayout deliveryRadius;//配送布局
    private ToggleButton button_show_status;//是否在消费者版中显示
    private TextView textViewStoreIntroduction;//商铺简介
    private TextView textViewBusinessTimeBegin, textViewBusinessTimeEnd;//商铺开始营业时间
    private EditText storePhone, storeName;//商铺联系电话
    private ToggleButton invoice;//是否支持发票
    private ToggleButton payAfter;//是否支持货到付款
    private LinearLayout out;//外卖
    private LinearLayout cashOnDelivery;//货到付款 预定没有它
    private EditText textViewOutSideStartPrice;//起送价
    private EditText textViewOutSidePrice;
    private EditText textViewMinFree;//满免配送费达额
    private LinearLayout reserveLinearLayout;//预定两个
    private LinearLayout reserveTime;//预定时间
    private TextView textViewReserveTime;//预定时间
    private ScrollView scrollView;
    private ImageView anim;

    public StoreGetMessageListener(Context context, TextView deliveryRadiusStart, TextView radius, LinearLayout deliveryRadius, ToggleButton reserve
            , ToggleButton button_show_status, TextView textViewStoreIntroduction, EditText storePhone, EditText storeName, TextView textViewBusinessTimeBegin
            , TextView textViewBusinessTimeEnd, ToggleButton invoice, ToggleButton payAfter, LinearLayout out, LinearLayout cashOnDelivery
            , EditText textViewOutSideStartPrice, EditText textViewOutSidePrice, EditText textViewMinFree, LinearLayout reserveLinearLayout
            , LinearLayout reserveTime, TextView textViewReserveTime, ScrollView scrollView, ImageView anim) {
        this.reserveTime = reserveTime;
        this.context = context;
        this.deliveryRadiusStart = deliveryRadiusStart;
        this.radius = radius;
        this.deliveryRadius = deliveryRadius;
        this.reserve = reserve;
        this.button_show_status = button_show_status;
        this.textViewStoreIntroduction = textViewStoreIntroduction;
        this.storePhone = storePhone;
        this.storeName = storeName;
        this.textViewBusinessTimeBegin = textViewBusinessTimeBegin;
        this.textViewBusinessTimeEnd = textViewBusinessTimeEnd;
        this.invoice = invoice;
        this.payAfter = payAfter;
        this.out = out;
        this.cashOnDelivery = cashOnDelivery;
        this.textViewOutSideStartPrice = textViewOutSideStartPrice;
        this.textViewOutSidePrice = textViewOutSidePrice;
        this.textViewMinFree = textViewMinFree;
        this.reserveLinearLayout = reserveLinearLayout;
        this.textViewReserveTime = textViewReserveTime;
        this.scrollView = scrollView;
        this.anim = anim;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //返回成功
            JSONObject message = null;
            try {
                message = jsonObject.getJSONObject("storeinfo");
                String contactPhone = message.getString("contactphone");//商家联系电话
                String isArrivePay = message.getString("isarrivepay");//是否支持货到付款
                String isInvoice = message.getString("isinvoice");//是否支持发票
                StoreInformationManagementActivity.isReserve = message.getString("isreserve");//是否支持预定
                StoreInformationManagementActivity.isDist = message.getString("isdist");//是否外卖还是预定
                String openBeginTime = message.getString("openbegintime");//营业开始时间
                String openEndTime = message.getString("openendtime");//营业结束时间
                String storeDescr = message.getString("storedescr");//商家描述
                String storename = message.getString("storename");//商店名
                String ordminamt = message.getString("ordminamt");//起送价
                String distamt = message.getString("distamt");//配送价
                String distordamt = message.getString("distordamt");//满免配送费达额
                StoreInformationManagementActivity.showStatus = message.getString("showstatus");//是否在商家显示
                String YTime = message.getString("reservehours");//预定时间
                StoreInformationManagementActivity.city = message.getString("areaname");//原来的区
                StoreInformationManagementActivity.addressDetail = message.getString("streetaddr");//原来的详细地址
                if ("1".equals(StoreInformationManagementActivity.isDist) && "".equals(message.getString("stationid"))) {
                    StoreInformationManagementActivity.stu = true;
                    String mi = message.getString("distrange");
                    if (mi.contains(".")) {
                        String[] splitAddress = mi.split("\\.");
                        deliveryRadiusStart.setText(splitAddress[0]);
                        radius.setText(splitAddress[1]);
                    } else {
                        deliveryRadiusStart.setText(mi);
                        radius.setText("0");
                    }
                    deliveryRadius.setVisibility(View.VISIBLE);
                    deliveryRadius.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DeliveryDistanceDialog dateDialog = new DeliveryDistanceDialog(context, deliveryRadiusStart, radius);
                            dateDialog.show();
                        }
                    });
                } else {
                    deliveryRadius.setVisibility(View.GONE);
                }
                if ("1".equals(StoreInformationManagementActivity.isReserve)) {
                    reserve.setChecked(true);
                } else {
                    reserve.setChecked(false);
                }
                if ("1".equals(StoreInformationManagementActivity.showStatus)) {
                    button_show_status.setChecked(true);
                } else {
                    button_show_status.setChecked(false);
                }
                textViewStoreIntroduction.setText(storeDescr);
                storePhone.setText(contactPhone);
                storeName.setText(storename);
                textViewBusinessTimeBegin.setText(openBeginTime);
                textViewBusinessTimeEnd.setText(openEndTime);
                if ("1".equals(isInvoice)) {
                    invoice.setChecked(true);
                } else {
                    invoice.setChecked(false);
                }
                if ("1".equals(isArrivePay)) {
                    payAfter.setChecked(true);
                } else {
                    payAfter.setChecked(false);
                }
                if ("1".equals(StoreInformationManagementActivity.isDist)) {//如果外卖
                    StoreInformationManagementActivity.ifOut = 1;
                    out.setVisibility(View.VISIBLE);
                    cashOnDelivery.setVisibility(View.VISIBLE);
                    textViewOutSideStartPrice.setText(ordminamt);
                    textViewOutSidePrice.setText(distamt);
                    textViewMinFree.setText(distordamt);
                    reserveLinearLayout.setVisibility(View.GONE);
                } else { //非外卖
                    StoreInformationManagementActivity.ifOut = 0;
                    cashOnDelivery.setVisibility(View.GONE);
                    out.setVisibility(View.GONE);
                    reserveLinearLayout.setVisibility(View.VISIBLE);
                    if ("1".equals(StoreInformationManagementActivity.isReserve)) {
                        reserve.setChecked(true);
                        reserveTime.setVisibility(View.VISIBLE);
                        textViewReserveTime.setText(YTime);
                    } else {
                        reserveTime.setVisibility(View.GONE);
                        reserve.setChecked(false);
                    }
                }
                scrollView.setVisibility(View.VISIBLE);
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
