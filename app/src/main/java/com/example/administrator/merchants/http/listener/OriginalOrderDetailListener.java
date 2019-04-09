package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.example.administrator.merchants.activity.OriginalOrderDetailActivity;
import com.example.administrator.merchants.adapter.OriginalOrderDetailAdapter;
import com.example.administrator.merchants.common.DateUtils;
import com.example.administrator.merchants.http.HttpUrl;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.http.show.MerchantsOrderShowBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/7/18 0018 15:30
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：原产地订单详情
 */
public class OriginalOrderDetailListener implements Response.Listener<JSONObject> {
    private Context context;
    private TextView buyerName;//买家姓名
    private TextView buyerGender;//买家性别
    private TextView buyerPhone;//卖家电话
    private List<MerchantsOrderShowBean> list;
    private TextView buyerAddress;//买家地址
    private TextView buyerDetailAddress;//买家详细地址
    private TextView orderTime;//下单时间
    private TextView orderId;//订单编码
    private TextView money;//订单金额
    private TextView payName;//支付方式
    private LinearLayout linearLayoutAll;//全选布局
    private LinearLayout linearLayoutBottom;//确定取消总布局
    private LinearLayout linearLayoutPaytype;//支付方式布局
    private CheckBox checkBox;//全选
    private ListView listView;
    private TextView Cancel, Confirm;
    private LinearLayout discountLinearLayout;
    private TextView orderMoneyTextView;
    private TextView discountMoney;

    public OriginalOrderDetailListener(Context context, List<MerchantsOrderShowBean> list, TextView buyerName, TextView buyerGender, TextView buyerPhone
            , TextView buyerAddress, TextView buyerDetailAddress, TextView orderTime, TextView orderId, TextView money, TextView payName, LinearLayout linearLayoutAll
            , LinearLayout linearLayoutBottom, LinearLayout linearLayoutPaytype, CheckBox checkBox, ListView listView, TextView Cancel, TextView Confirm, LinearLayout discountLinearLayout, TextView orderMoneyTextView
            , TextView discountMoney) {
        this.linearLayoutAll = linearLayoutAll;
        this.linearLayoutBottom = linearLayoutBottom;
        this.linearLayoutPaytype = linearLayoutPaytype;
        this.context = context;
        this.listView = listView;
        this.Cancel = Cancel;
        this.Confirm = Confirm;
        this.list = list;
        this.payName = payName;
        this.orderId = orderId;
        this.orderTime = orderTime;
        this.buyerAddress = buyerAddress;
        this.buyerDetailAddress = buyerDetailAddress;
        this.buyerName = buyerName;
        this.money = money;
        this.buyerGender = buyerGender;
        this.buyerPhone = buyerPhone;
        this.checkBox = checkBox;
        this.discountLinearLayout = discountLinearLayout;
        this.orderMoneyTextView = orderMoneyTextView;
        this.discountMoney = discountMoney;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        Log.e("订单详情",jsonObject.toString());
        if (Status.status(jsonObject)) {
            //返回成功
            try {
                list.clear();
                JSONArray jsonArray = jsonObject.getJSONArray("list");
                List<JSONObject> jsonObjectList = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObjectList.add((JSONObject) jsonArray.get(i));
                }
                buyerName.setText(jsonObject.getJSONObject("order").getString("receiver") + "");
                if ("1".equals(jsonObject.getJSONObject("order").getString("gender"))) {
                    buyerGender.setText("先生");
                } else {
                    buyerGender.setText("女士");
                }
                buyerPhone.setText(jsonObject.getJSONObject("order").getString("contact"));
                buyerDetailAddress.setText(jsonObject.getJSONObject("order").getString("streetaddr"));
                buyerAddress.setText(jsonObject.getJSONObject("order").getString("areaname"));
                orderTime.setText(DateUtils.getDateToString(Long.parseLong(jsonObject.getJSONObject("order").getJSONObject("createtime").getString("time"))));
                orderId.setText(jsonObject.getJSONObject("order").getString("ordno"));
                orderMoneyTextView.setText(jsonObject.getJSONObject("order").getString("ordamt"));
                money.setText(jsonObject.getJSONObject("order").getString("payamt"));
                if ("".equals(jsonObject.getJSONObject("order").getString("discountid")) || jsonObject.getJSONObject("order").getString("discountid") == null) {
                    discountLinearLayout.setVisibility(View.GONE);
                } else {
                    discountLinearLayout.setVisibility(View.VISIBLE);
                    discountMoney.setText("-"+jsonObject.getString("cutmoney"));
                }
                payName.setText(jsonObject.getJSONObject("order").getString("paytypename"));
                OriginalOrderDetailActivity.orderMoney = jsonObject.getJSONObject("order").getDouble("payamt");
                OriginalOrderDetailActivity.orderName = jsonObject.getJSONObject("order").getString("ordmername");
                OriginalOrderDetailActivity.isSilver=jsonObject.getJSONObject("order").getString("isSilver");
                if ("0".equals(jsonObject.getJSONObject("order").getString("paystatus"))) {
                    linearLayoutAll.setVisibility(View.GONE);
                    linearLayoutPaytype.setVisibility(View.GONE);
                    linearLayoutBottom.setVisibility(View.VISIBLE);
                    Cancel.setText("取消订单");
                    Confirm.setText("立即付款");
                    OriginalOrderDetailActivity.s = 0;
                } else {
                    linearLayoutAll.setVisibility(View.VISIBLE);
                    linearLayoutPaytype.setVisibility(View.VISIBLE);
                    Cancel.setText("申请退款");
                    Confirm.setText("确认收货");
                    OriginalOrderDetailActivity.s = 1;
                }
                for (int j = 0; j < jsonObjectList.size(); j++) {
                    MerchantsOrderShowBean merchantsOrderShowBean = new MerchantsOrderShowBean();
                    merchantsOrderShowBean.setImgsfile(HttpUrl.BaseImageUrl + jsonObjectList.get(j).getString("imgsfile"));
                    merchantsOrderShowBean.setMerqty(jsonObjectList.get(j).getInt("merqty"));
                    merchantsOrderShowBean.setSaleprice(jsonObjectList.get(j).getDouble("saleprice"));
                    merchantsOrderShowBean.setMername(jsonObjectList.get(j).getString("mername"));
                    merchantsOrderShowBean.setMerid(jsonObjectList.get(j).getString("merid"));
                    merchantsOrderShowBean.setGuige(jsonObjectList.get(j).getString("modeldescr"));
                    merchantsOrderShowBean.setRemark(jsonObjectList.get(j).getString("remark"));
                    switch (jsonObjectList.get(j).getString("merstatus")) {
                        case "0"://未完成
                            switch (jsonObjectList.get(j).getString("merpaystatus")) {
                                case "0"://未付
                                    merchantsOrderShowBean.setCreatetime(DateUtils.getDateToString(Long.parseLong(jsonObjectList.get(j).getJSONObject("createtime").getString("time"))));//下单时间
                                    break;
                                case "1"://已付
                                    switch (jsonObjectList.get(j).getString("receivestatus")) {
                                        case "0"://没收货
                                            merchantsOrderShowBean.setCreatetime(DateUtils.getDateToString(Long.parseLong(jsonObjectList.get(j).getJSONObject("createtime").getString("time"))));//下单时间
                                            break;
                                        case "1"://收货了
                                            merchantsOrderShowBean.setReceivetime(DateUtils.getDateToString(Long.parseLong(jsonObjectList.get(j).getJSONObject("receivetime").getString("time"))));//收货时间
                                            break;
                                        case "2"://自动收货
                                            merchantsOrderShowBean.setReceivetime(DateUtils.getDateToString(Long.parseLong(jsonObjectList.get(j).getJSONObject("receivetime").getString("time"))));//收货时间
                                            break;
                                    }
                                    break;
                                case "2"://申退
                                    merchantsOrderShowBean.setRefapplytime(DateUtils.getDateToString(Long.parseLong(jsonObjectList.get(j).getJSONObject("refapplytime").getString("time"))));//退款申请时间
                                    break;
                                case "3"://驳回
                                    merchantsOrderShowBean.setReftime(DateUtils.getDateToString(Long.parseLong(jsonObjectList.get(j).getJSONObject("reftime").getString("time"))));//退款时间
                                    break;
                                case "4"://同意
                                    merchantsOrderShowBean.setRefapplytime(DateUtils.getDateToString(Long.parseLong(jsonObjectList.get(j).getJSONObject("refapplytime").getString("time"))));//退款申请时间
                                    break;
                            }
                            break;
                        case "1"://已完成
                            switch (jsonObjectList.get(j).getString("merpaystatus")) {
                                case "9"://已退款
                                    merchantsOrderShowBean.setReftime(DateUtils.getDateToString(Long.parseLong(jsonObjectList.get(j).getJSONObject("reftime").getString("time"))));//退款时间
                                    break;
                                case "1"://已完成
                                    merchantsOrderShowBean.setReceivetime(DateUtils.getDateToString(Long.parseLong(jsonObjectList.get(j).getJSONObject("receivetime").getString("time"))));//收货时间
                                    break;
                            }
                            break;

                    }
                    merchantsOrderShowBean.setMerstatus(jsonObjectList.get(j).getString("merstatus"));
                    merchantsOrderShowBean.setMerpaystatus(jsonObjectList.get(j).getString("merpaystatus"));
                    merchantsOrderShowBean.setReceivestatus(jsonObjectList.get(j).getString("receivestatus"));
                    merchantsOrderShowBean.setOrddetailid(jsonObjectList.get(j).getString("orddetailid"));
                    list.add(merchantsOrderShowBean);
                }
                OriginalOrderDetailAdapter originalOrderDetailAdapter = new OriginalOrderDetailAdapter(context, list, checkBox, linearLayoutAll, linearLayoutBottom, linearLayoutPaytype, jsonObject.getJSONObject("order").getString("paystatus"));
                listView.setAdapter(originalOrderDetailAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            //返回失败
            Status.fail(context, jsonObject);
        }

    }
}
