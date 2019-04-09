package com.example.administrator.merchants.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseActivity;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.PossObject;
import com.example.administrator.merchants.http.post.ApplyForRefundPostBean;
import com.example.administrator.merchants.http.post.MerIdPostBean;
import com.example.administrator.merchants.http.post.SendPostBean;
import com.example.administrator.merchants.http.show.MerchantsOrderShowBean;
import com.example.administrator.merchants.common.toast.CustomToast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/6/23 0023 14:39
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：原产地订单详情
 */
public class OriginalOrderDetailActivity extends BaseActivity {
    private View headView;
    private View footView;
    private ListView listView;
    private List<MerchantsOrderShowBean> list;
    private TextView buyerName;//买家姓名
    private TextView buyerGender;//买家性别
    private TextView buyerPhone;//卖家电话
    private TextView buyerAddress;//买家地址
    private TextView buyerDetailAddress;//买家详细地址
    private TextView orderTime;//下单时间
    private TextView orderId;//订单编码
    private TextView discountMoney;//满减金额
    private TextView orderMoneyTextView;
    private TextView money;//订单金额
    private TextView payName;//支付方式
    private TextView Cancel, Confirm;
    private CheckBox checkBox;//全选
    private LinearLayout linearLayoutAll;//全选布局
    private LinearLayout linearLayoutBottom;//确定取消总布局
    private LinearLayout linearLayoutPaytype,discountLinearLayout;//支付方式布局
    public static int s = 1;
    public static Double orderMoney = 0.0;//订单金额
    public static String orderName;//订单商品名
    public static String isSilver;//是否使用银贝比
    private String orderNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchants_order_detail);
        setTitles("原产地订单详情");
        orderNo=getIntent().getStringExtra("ordno");
        headView = LayoutInflater.from(this).inflate(R.layout.view_order_original_head, null);
        footView = LayoutInflater.from(this).inflate(R.layout.view_order_original_foot, null);
        listView = (ListView) findViewById(R.id.listView);
        list = new ArrayList<>();
        buyerName = (TextView) headView.findViewById(R.id.item_origin_order_detail_headview_name);
        buyerGender = (TextView) headView.findViewById(R.id.item_origin_order_detail_headview_sex);
        buyerPhone = (TextView) headView.findViewById(R.id.item_origin_order_detail_headview_tel);
        buyerAddress = (TextView) headView.findViewById(R.id.item_origin_order_detail_headview_address);
        buyerDetailAddress = (TextView) headView.findViewById(R.id.item_origin_order_detail_headview_str);
        checkBox = (CheckBox) headView.findViewById(R.id.item_origin_order_detail_headview_checkBox);
        linearLayoutAll = (LinearLayout) headView.findViewById(R.id.ooooo);
        orderTime = (TextView) footView.findViewById(R.id.item_origin_order_detail_headview_createtime);
        orderId = (TextView) footView.findViewById(R.id.item_origin_order_detail_headview_orderid);
        money = (TextView) footView.findViewById(R.id.item_origin_order_detail_headview_zong_money);
        payName = (TextView) footView.findViewById(R.id.item_origin_order_detail_headview_payname);
        linearLayoutPaytype = (LinearLayout) footView.findViewById(R.id.linear_paytype);
        linearLayoutBottom = (LinearLayout) findViewById(R.id.linearLayoutBottom);
        listView.addHeaderView(headView);
        listView.addFooterView(footView);
        discountLinearLayout= (LinearLayout) footView.findViewById(R.id.manjian);
        orderMoneyTextView= (TextView) footView.findViewById(R.id.item_origin_order_detail_headview_dingdan_money);
        discountMoney= (TextView) footView.findViewById(R.id.item_origin_order_detail_headview_full_money);
        Cancel = (TextView) findViewById(R.id.cancel);//取消
        Confirm = (TextView) findViewById(R.id.confirm);//去干啥
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (s == 0) { //s==0就是订单没支付
                    getCancel(orderNo);//取消订单的方法
                } else {
                    final JSONArray jsonArray = new JSONArray();
                    try {
                        for (MerchantsOrderShowBean m : list) { //遍历
                            switch (m.getMerstatus()) {
                                case "0"://未完成
                                    switch (m.getMerpaystatus()) {
                                        case "0"://未付款 ，目前是不会出现没付钱的订单
                                            break;
                                        case "1"://已付款 卖家关心的是收不收货
                                            switch (m.getReceivestatus()) {//对方收货的状态
                                                case "0"://未收货
                                                    if (m.isChoosed() == true) {
                                                        JSONObject j = new JSONObject();
                                                        j.put("orddetailid", m.getOrddetailid());
                                                        jsonArray.put(j);
                                                    }
                                                    break;

                                                case "3"://toDO 已退货 可能是货退了还没给他退钱的情况
                                                    break;
                                            }
                                            break;
                                        case "2"://退款申请
                                            break;
                                        case "3"://退款驳回
                                            if (m.isChoosed() == true) {
                                                JSONObject j = new JSONObject();
                                                j.put("orddetailid", m.getOrddetailid());
                                                jsonArray.put(j);
                                            }
                                            break;
                                        case "4"://同意退款  但是不是已退款
                                            break;
                                    }
                                    break;
                                case "1"://完成了
                                    switch (m.getMerpaystatus()) {
                                        case "1"://已付款
                                            switch (m.getReceivestatus()) {//对方收货的状态
                                                case "1"://确认收货
                                                    if (m.isChoosed() == true) {
                                                        JSONObject j = new JSONObject();
                                                        j.put("orddetailid", m.getOrddetailid());
                                                        jsonArray.put(j);
                                                    }
                                                    break;
                                                case "2"://自动确认收货
                                                    if (m.isChoosed() == true) {
                                                        JSONObject j = new JSONObject();
                                                        j.put("orddetailid", m.getOrddetailid());
                                                        jsonArray.put(j);
                                                    }
                                                    break;
                                            }
                                            break;
                                        case "9"://已退款
                                            break;
                                    }
                                    break;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (jsonArray.length() == 0) {
                        CustomToast.getInstance(OriginalOrderDetailActivity.this).setMessage("对不起,您还没有选择商品!");
                    } else {
                        new AlertDialog.Builder(OriginalOrderDetailActivity.this)
                                .setMessage("是否申请退款？\n")
                                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        applyForRefund(jsonArray);
                                    }
                                })
                                .setNegativeButton("取消", null)
                                .show();
                    }
                }
            }
        });
        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (s == 0) {//没付款  调到去付款
                    Intent intent = new Intent();
                    intent.putExtra("ordno", orderNo);
                    intent.putExtra("money", orderMoney + "");
                    intent.putExtra("mername", orderName);//toDO 商品名
                    intent.putExtra("merdescr", orderName);//toDO 商品名详情跟商品名一样  因为订单拿不到商品详情
                    intent.putExtra("isSilver", isSilver);//toDO 商品名详情跟商品名一样  因为订单拿不到商品详情
                    intent.setClass(OriginalOrderDetailActivity.this, PayOrderActivity.class);
                    startActivity(intent);
                } else {
                    PossObject.chooseList(list);
                    if (MutualApplication.chooseOrderList.size() == 0) {
                        CustomToast.getInstance(OriginalOrderDetailActivity.this).setMessage("对不起，您还没有选择商品");
                    } else {
                        Intent intent = new Intent();
                        intent.putExtra("ordno", orderNo);
                        intent.putExtra("serviceid", getIntent().getStringExtra("serviceid"));
                        intent.setClass(OriginalOrderDetailActivity.this, EvaluateActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    toGetGoodsDetailUp(position);
                }
            }
        });
    }

    /**
     * 原产地订单申请退款
     *
     * @param jsonArray
     */
    public void applyForRefund(JSONArray jsonArray) {
        ApplyForRefundPostBean applyForRefundPostBean = new ApplyForRefundPostBean();
        applyForRefundPostBean.setOrdno(orderNo);
        applyForRefundPostBean.setMerList(jsonArray);
        Http.applyForRefund(OriginalOrderDetailActivity.this, applyForRefundPostBean);
    }

    /**
     * 原产地商品详情
     *
     * @param position
     */
    public void toGetGoodsDetailUp(int position) {//商品详情头部请求
        MerIdPostBean merIdPostBean = new MerIdPostBean();
        merIdPostBean.setMerid(list.get(position - 1).getMerid());
        Http.toCommodityDetails(OriginalOrderDetailActivity.this, merIdPostBean, list, position);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getList();
    }

    /**
     * 订单详情接口
     */
    public void getList() {
        SendPostBean sendPostBean = new SendPostBean();
        sendPostBean.setOrdno(orderNo);
        Http.originalOrderDetail(OriginalOrderDetailActivity.this, sendPostBean, list, buyerName, buyerGender, buyerPhone, buyerAddress, buyerDetailAddress, orderTime, orderId, money, payName, linearLayoutAll, linearLayoutBottom, linearLayoutPaytype, checkBox, listView, Cancel, Confirm,discountLinearLayout,orderMoneyTextView,discountMoney);
    }

    /**
     * 原产地取消订单
     *
     * @param ordno
     */
    public void getCancel(String ordno) {
        SendPostBean sendPostBean = new SendPostBean();
        sendPostBean.setOrdno(ordno);
        Http.cancelOrder(OriginalOrderDetailActivity.this, sendPostBean);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MutualApplication.getRequestQueue().cancelAll("toCommodityDetails59");
        MutualApplication.getRequestQueue().cancelAll("cancelOrder60");
        MutualApplication.getRequestQueue().cancelAll("applyForRefund61");
        MutualApplication.getRequestQueue().cancelAll("OriginalOrderDetail62");
    }


}