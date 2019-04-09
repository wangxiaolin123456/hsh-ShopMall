package com.example.administrator.merchants.http.listener;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.example.administrator.merchants.R;
import com.example.administrator.merchants.activity.MerchantsOrderDetailActivity;
import com.example.administrator.merchants.adapter.MerchantsOrderDetailAdapter;
import com.example.administrator.merchants.http.HttpUrl;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.http.show.MerchantsOrderShowBean;
import com.example.administrator.merchants.common.DateUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/7/14 0014 09:08
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：商家订单详情
 */
public class MerchantsOrderDetailListener implements Response.Listener<JSONObject> {
    private Context context;
    private MerchantsOrderDetailAdapter merchantsOrderDetailAdapter;
    private TextView buyerName, buyerGender, buyerPhone, coupon_money, amountPaid, Cancel, Confirm;
    private TextView buyerDetailAddress, buyerAddress, deliveryClerkPhone, invoice;
    private TextView orderTime, orderId, orderMoney, payType, LeavingMessage, sendFee, returnBeiBi;
    private LinearLayout peopleLinearLayout, coupon_line, amountPaidLine, distLinearLayout, linearLayoutBottom, linearLayoutPeople, checkAllLinearLayout, linearLayoutPayType;//联系人布局
    private View amountPaid_line;
    private ImageView status;
    private ListView listView;
    private List<MerchantsOrderShowBean> list;
    private CheckBox checkAll;

    public MerchantsOrderDetailListener(Context context, TextView buyerName, TextView buyerGender, TextView buyerPhone
            , TextView buyerDetailAddress, TextView buyerAddress, TextView orderTime, TextView orderId, LinearLayout peopleLinearLayout, TextView orderMoney, TextView payType, TextView LeavingMessage, TextView sendFee
            , TextView returnBeiBi, LinearLayout coupon_line, LinearLayout amountPaidLine, View amountPaid_line, TextView coupon_money
            , TextView amountPaid, LinearLayout distLinearLayout, TextView deliveryClerkPhone, ImageView status, TextView invoice, LinearLayout linearLayoutBottom
            , TextView Cancel, TextView Confirm, LinearLayout linearLayoutPeople, List<MerchantsOrderShowBean> list, ListView listView, CheckBox checkAll
            , LinearLayout checkAllLinearLayout, LinearLayout linearLayoutPayType) {
        this.context = context;
        this.checkAllLinearLayout = checkAllLinearLayout;
        this.Cancel = Cancel;
        this.checkAll = checkAll;
        this.listView = listView;
        this.linearLayoutPayType = linearLayoutPayType;
        this.list = list;
        this.buyerName = buyerName;
        this.Confirm = Confirm;
        this.linearLayoutPeople = linearLayoutPeople;
        this.buyerGender = buyerGender;
        this.linearLayoutBottom = linearLayoutBottom;
        this.buyerPhone = buyerPhone;
        this.invoice = invoice;
        this.buyerDetailAddress = buyerDetailAddress;
        this.buyerAddress = buyerAddress;
        this.orderTime = orderTime;
        this.orderId = orderId;
        this.peopleLinearLayout = peopleLinearLayout;
        this.orderMoney = orderMoney;
        this.payType = payType;
        this.LeavingMessage = LeavingMessage;
        this.sendFee = sendFee;
        this.returnBeiBi = returnBeiBi;
        this.coupon_line = coupon_line;
        this.amountPaidLine = amountPaidLine;
        this.amountPaid_line = amountPaid_line;
        this.coupon_money = coupon_money;
        this.amountPaid = amountPaid;
        this.distLinearLayout = distLinearLayout;
        this.deliveryClerkPhone = deliveryClerkPhone;
        this.status = status;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //返回成功
            JSONArray jsonArray = null;
            try {
                jsonArray = jsonObject.getJSONArray("list");
                List<JSONObject> jsonObjectList = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObjectList.add((JSONObject) jsonArray.get(i));
                }
                buyerName.setText(jsonObject.getJSONObject("order").getString("memname") + "");
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
                if ("out".equals(jsonObject.getJSONObject("order").getString("ordtype"))) {
                    peopleLinearLayout.setVisibility(View.VISIBLE);
                } else if ("outc".equals(jsonObject.getJSONObject("order").getString("ordtype"))) {
                    peopleLinearLayout.setVisibility(View.VISIBLE);
                } else {
                    peopleLinearLayout.setVisibility(View.GONE);
                }
                orderMoney.setText(jsonObject.getJSONObject("order").getString("payamt"));
                payType.setText(jsonObject.getJSONObject("order").getString("paytypename"));
                LeavingMessage.setText(jsonObject.getJSONObject("order").getString("remark"));
                sendFee.setText(jsonObject.getJSONObject("order").getString("distamt"));
                returnBeiBi.setText(jsonObject.getJSONObject("order").getString("retbeibiamt"));
                String stu = jsonObject.getJSONObject("order").getString("diststatus");
                if ("outc".equals(jsonObject.getJSONObject("order").getString("ordtype"))) {
                    coupon_line.setVisibility(View.GONE);
                    amountPaidLine.setVisibility(View.GONE);
                    amountPaid_line.setVisibility(View.GONE);
                    orderMoney.setTextColor(context.getResources().getColor(R.color.text_yellow));
                } else {
                    amountPaidLine.setVisibility(View.VISIBLE);
                    amountPaid_line.setVisibility(View.VISIBLE);
                    orderMoney.setTextColor(context.getResources().getColor(R.color.all_bg_gray_color));
                    if ("1".equals(jsonObject.getJSONObject("order").getString("isusevoucher"))) {
                        coupon_money.setText(jsonObject.getJSONObject("order").getString("vouchermoney"));
                        coupon_line.setVisibility(View.VISIBLE);
                    } else {
                        coupon_line.setVisibility(View.GONE);
                    }
                    amountPaid.setText(jsonObject.getJSONObject("order").getString("payamtmem"));
                }
                if ("0".equals(stu) || "10".equals(stu)) {
                    distLinearLayout.setVisibility(View.GONE);
                } else if ("70".equals(stu)) {
                    distLinearLayout.setVisibility(View.VISIBLE);
                    deliveryClerkPhone.setText(jsonObject.getJSONObject("order").getString("disterphone"));
                    status.setImageResource(R.drawable.dist_get);
                    final String tell = jsonObject.getJSONObject("order").getString("disterphone");
                    deliveryClerkPhone.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            new AlertDialog.Builder(context)
                                    .setTitle("提示")
                                    .setMessage("\n\t\t\t\t\t\t\t\t" + tell + "\n\n" + "\t\t\t\t\t\t\t\t\t\t是否拨打？\n")
                                    .setPositiveButton("拨打", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tell));
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            context.startActivity(intent);
                                        }
                                    })
                                    .setNegativeButton("取消", null)
                                    .show();
                        }
                    });

                } else if ("80".equals(stu)) {
                    //显示送
                    distLinearLayout.setVisibility(View.VISIBLE);
                    deliveryClerkPhone.setText(jsonObject.getJSONObject("order").getString("disterphone"));
                    status.setImageResource(R.drawable.dist_arrive);
                    final String tell = jsonObject.getJSONObject("order").getString("disterphone");
                    deliveryClerkPhone.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            new AlertDialog.Builder(context)
                                    .setTitle("提示")
                                    .setMessage("\n\t\t\t\t\t\t\t\t" + tell + "\n\n" + "\t\t\t\t\t\t\t\t\t\t是否拨打？\n")
                                    .setPositiveButton("拨打", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tell));
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            context.startActivity(intent);
                                        }
                                    })
                                    .setNegativeButton("取消", null)
                                    .show();
                        }
                    });
                } else {
                    //显示路
                    distLinearLayout.setVisibility(View.VISIBLE);
                    deliveryClerkPhone.setText(jsonObject.getJSONObject("order").getString("disterphone"));
                    status.setImageResource(R.drawable.dist_to);
                    final String tell = jsonObject.getJSONObject("order").getString("disterphone");
                    deliveryClerkPhone.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            new AlertDialog.Builder(context)
                                    .setTitle("提示")
                                    .setMessage("\n\t\t\t\t\t\t\t\t" + tell + "\n\n" + "\t\t\t\t\t\t\t\t\t\t是否拨打？\n")
                                    .setPositiveButton("拨打", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tell));
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            context.startActivity(intent);
                                        }
                                    })
                                    .setNegativeButton("取消", null)
                                    .show();
                        }
                    });
                }
                if ("1".equals(jsonObject.getJSONObject("order").getString("isinvoice"))) {
                    invoice.setText(jsonObject.getJSONObject("order").getString("invoicetitle"));
                } else {
                    invoice.setText("不需要发票");
                }
                if ("out".equals(jsonObject.getJSONObject("order").getString("ordtype"))) { //如果是外卖
                    if ("0".equals(jsonObject.getJSONObject("order").getString("ordstatus"))) { //toDo 如果订单没完成（有用验证码直接完成的）
                        if ("0".equals(jsonObject.getJSONObject("order").getString("sendstatus"))) { //未发货
                            linearLayoutBottom.setVisibility(View.VISIBLE);
                            if ("".equals(jsonObject.getJSONObject("order").getString("stationid"))) {
                                Cancel.setVisibility(View.GONE);
                                Confirm.setVisibility(View.VISIBLE);
                            } else {
                                Cancel.setText("互实配送");
                                Cancel.setVisibility(View.VISIBLE);
                                Confirm.setVisibility(View.GONE);
                            }
                            Confirm.setText("确认发货");
                            MerchantsOrderDetailActivity.s = 1;
                        } else { //adapter自己判断是否显示同意还是拒绝退款申请
                            MerchantsOrderDetailActivity.s = 0;
                            Cancel.setText("取消");
                            Confirm.setText("同意");
                        }
                    } else {//订单用验证码直接完成了订单
                        MerchantsOrderDetailActivity.s = 0;
                        Cancel.setText("取消");
                        Confirm.setText("同意");
                    }
                    MerchantsOrderDetailActivity.latitude = jsonObject.getJSONObject("order").getDouble("latitude") + "";
                    MerchantsOrderDetailActivity.longitude = jsonObject.getJSONObject("order").getDouble("longitude") + "";
                } else if ("outc".equals(jsonObject.getJSONObject("order").getString("ordtype"))) {
                    if ("0".equals(jsonObject.getJSONObject("order").getString("ordstatus"))) { //toDo 如果订单没完成（有用验证码直接完成的）
                        if ("0".equals(jsonObject.getJSONObject("order").getString("sendstatus"))) { //未发货
                            linearLayoutBottom.setVisibility(View.VISIBLE);
                            if ("".equals(jsonObject.getJSONObject("order").getString("stationid"))) {
                                Cancel.setVisibility(View.GONE);
                                Confirm.setVisibility(View.VISIBLE);
                            } else {
                                Cancel.setText("互实配送");
                                Cancel.setVisibility(View.VISIBLE);
                                Confirm.setVisibility(View.GONE);
                            }
                            Confirm.setText("确认发货");
                            MerchantsOrderDetailActivity.s = 1;
                        } else { //adapter自己判断是否显示同意还是拒绝退款申请
                            Cancel.setText("取消");
                            Confirm.setText("同意");
                            MerchantsOrderDetailActivity.s = 0;
                        }
                    } else {//订单用验证码直接完成了订单
                        MerchantsOrderDetailActivity.s = 0;
                        Cancel.setText("取消");
                        Confirm.setText("同意");
                    }
                    MerchantsOrderDetailActivity.latitude = jsonObject.getJSONObject("order").getDouble("latitude") + "";
                    MerchantsOrderDetailActivity.longitude = jsonObject.getJSONObject("order").getDouble("longitude") + "";
                } else {//非外卖
                    linearLayoutPeople.setVisibility(View.GONE);
                    linearLayoutBottom.setVisibility(View.GONE);
                }
                for (int j = 0; j < jsonObjectList.size(); j++) {
                    MerchantsOrderShowBean merchantsOrderShowBean = new MerchantsOrderShowBean();
                    merchantsOrderShowBean.setImgsfile(HttpUrl.BaseImageUrl + jsonObjectList.get(j).getString("imgsfile"));
                    merchantsOrderShowBean.setMerqty(jsonObjectList.get(j).getInt("merqty"));
                    merchantsOrderShowBean.setSaleprice(jsonObjectList.get(j).getDouble("saleprice"));
                    merchantsOrderShowBean.setMername(jsonObjectList.get(j).getString("mername"));
                    merchantsOrderShowBean.setMerid(jsonObjectList.get(j).getString("merid"));
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
                                case "5"://下订单时选择的货到付款
                                    merchantsOrderShowBean.setCreatetime(DateUtils.getDateToString(Long.parseLong(jsonObjectList.get(j).getJSONObject("createtime").getString("time"))));//下单时间
                                    break;
                                case "6"://货到付款退款申请
                                    merchantsOrderShowBean.setRefapplytime(DateUtils.getDateToString(Long.parseLong(jsonObjectList.get(j).getJSONObject("refapplytime").getString("time"))));//退款申请时间
                                    break;
                                case "7"://货到付款退款驳回
                                    merchantsOrderShowBean.setReftime(DateUtils.getDateToString(Long.parseLong(jsonObjectList.get(j).getJSONObject("reftime").getString("time"))));//退款时间
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
                                case "5"://货到付款
                                    switch (jsonObjectList.get(j).getString("receivestatus")) { //对方收货的状态
                                        case "1"://确认收货
                                            merchantsOrderShowBean.setReceivetime(DateUtils.getDateToString(Long.parseLong(jsonObjectList.get(j).getJSONObject("receivetime").getString("time"))));//收货时间
                                            break;
                                        case "2"://自动确认收货
                                            merchantsOrderShowBean.setReceivetime(DateUtils.getDateToString(Long.parseLong(jsonObjectList.get(j).getJSONObject("receivetime").getString("time"))));//收货时间
                                            break;
                                    }
                                case "4"://同意退款
                                    switch (jsonObjectList.get(j).getString("receivestatus")) { //对方收货的状态
                                        case "1"://确认收货
                                            merchantsOrderShowBean.setReceivetime(DateUtils.getDateToString(Long.parseLong(jsonObjectList.get(j).getJSONObject("receivetime").getString("time"))));//收货时间
                                            break;
                                        case "2"://自动确认收货
                                            merchantsOrderShowBean.setReceivetime(DateUtils.getDateToString(Long.parseLong(jsonObjectList.get(j).getJSONObject("receivetime").getString("time"))));//收货时间
                                            break;
                                    }
                                    break;

                                case "8"://货到付款已退款
                                    merchantsOrderShowBean.setReftime(DateUtils.getDateToString(Long.parseLong(jsonObjectList.get(j).getJSONObject("reftime").getString("time"))));//退款时间
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
                merchantsOrderDetailAdapter = new MerchantsOrderDetailAdapter(context, list, checkAll, checkAllLinearLayout, linearLayoutBottom, linearLayoutPayType);
                listView.setAdapter(merchantsOrderDetailAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        } else {
            //返回失败
            Status.fail(context, jsonObject);
        }

    }
}
