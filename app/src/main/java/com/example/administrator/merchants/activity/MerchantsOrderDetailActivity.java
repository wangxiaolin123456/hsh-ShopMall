package com.example.administrator.merchants.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseActivity;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.HttpUrl;
import com.example.administrator.merchants.http.post.MerIdPostBean;
import com.example.administrator.merchants.http.post.SendPostBean;
import com.example.administrator.merchants.http.show.MerchantsOrderShowBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/6/23 0023 14:39
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：商家订单详情
 */
public class MerchantsOrderDetailActivity extends BaseActivity {
    private View headView;
    private Intent intent;
    private View footView;
    private ListView listView;
    private List<MerchantsOrderShowBean> list;

    private TextView buyerName;//买家姓名
    private TextView buyerGender;//买家性别
    private TextView buyerPhone;//卖家电话
    private TextView buyerAddress;//买家地址
    private TextView buyerDetailAddress;//买家详细地址
    private TextView orderTime;//下单时间
    private TextView orderId;//订单ID
    private TextView orderMoney;//订单金额
    private TextView payType;//支付方式
    private TextView Cancel;//取消
    private TextView Confirm;//确定
    private CheckBox checkAll;//全选按钮
    private LinearLayout checkAllLinearLayout;//全选布局
    private LinearLayout distLinearLayout;//配送布局
    private LinearLayout linearLayoutBottom;//下面确定取消两个按钮的总布局
    private LinearLayout peopleLinearLayout;//联系人布局
    private LinearLayout linearLayoutPayType;//支付方式布局
    private TextView sendFee;//配送费
    private TextView returnBeiBi;//返贝币布局
    private TextView LeavingMessage;//留言布局
    private TextView invoice;//发票
    private TextView deliveryClerkPhone;//配送员电话
    private ImageView status;//配送员状态（去,送，路）
    public static int s = 0;
    private LinearLayout linearLayoutPeople;//联系人信息布局
    private LinearLayout map;//买家位置
    public static String latitude;//纬度
    public static String longitude;//经度
    private LinearLayout coupon_line, amountPaidLine;//代金券是否隐藏布局
    private TextView coupon_money;//代金券抵用金额
    private TextView amountPaid;//实付金额
    private View amountPaid_line;//市府金额下面的线
    private String orderNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchants_order_detail);
        setTitles("商家订单详情");
        orderNo=getIntent().getStringExtra("ordno");
        headView = LayoutInflater.from(this).inflate(R.layout.view_order_merchants_head, null);
        footView = LayoutInflater.from(this).inflate(R.layout.view_order_merchants_foot, null);
        listView = (ListView) findViewById(R.id.listView);
        list = new ArrayList<>();
        Cancel = (TextView) findViewById(R.id.cancel);//取消
        Confirm = (TextView) findViewById(R.id.confirm);//去干啥
        buyerName = (TextView) headView.findViewById(R.id.item_origin_order_detail_headview_name);
        buyerGender = (TextView) headView.findViewById(R.id.item_origin_order_detail_headview_sex);
        buyerPhone = (TextView) headView.findViewById(R.id.item_origin_order_detail_headview_tel);
        buyerAddress = (TextView) headView.findViewById(R.id.item_origin_order_detail_headview_address);
        buyerDetailAddress = (TextView) headView.findViewById(R.id.item_origin_order_detail_headview_str);
        checkAll = (CheckBox) headView.findViewById(R.id.item_origin_order_detail_headview_checkBox);
        map = (LinearLayout) headView.findViewById(R.id.map);
        peopleLinearLayout = (LinearLayout) headView.findViewById(R.id.phone_people);
        linearLayoutPeople = (LinearLayout) headView.findViewById(R.id.linear_add);
        checkAllLinearLayout = (LinearLayout) headView.findViewById(R.id.ooooo);
        orderTime = (TextView) footView.findViewById(R.id.item_origin_order_detail_headview_createtime);
        orderId = (TextView) footView.findViewById(R.id.item_origin_order_detail_headview_orderid);
        orderMoney = (TextView) footView.findViewById(R.id.item_origin_order_detail_headview_zong_money);
        payType = (TextView) footView.findViewById(R.id.item_origin_order_detail_headview_payname);
        sendFee = (TextView) footView.findViewById(R.id.item_origin_order_detail_headview_sendFee);
        returnBeiBi = (TextView) footView.findViewById(R.id.item_origin_order_detail_headview_refBeibi);
        invoice = (TextView) footView.findViewById(R.id.item_origin_order_detail_headview_invoice);
        distLinearLayout = (LinearLayout) footView.findViewById(R.id.peisong);
        amountPaidLine = (LinearLayout) footView.findViewById(R.id.shifu);
        amountPaid_line = footView.findViewById(R.id.shifu_line);
        coupon_line = (LinearLayout) footView.findViewById(R.id.coupon_line);
        coupon_money = (TextView) footView.findViewById(R.id.coupon_money);
        amountPaid = (TextView) footView.findViewById(R.id.actual_money);
        deliveryClerkPhone = (TextView) footView.findViewById(R.id.peisongphone);
        status = (ImageView) footView.findViewById(R.id.status);
        LeavingMessage = (TextView) footView.findViewById(R.id.liuyan);
        linearLayoutPayType = (LinearLayout) footView.findViewById(R.id.linear_paytype);
        linearLayoutBottom = (LinearLayout) findViewById(R.id.linearLayoutBottom);
        listView.addHeaderView(headView);
        listView.addFooterView(footView);
        getList();

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (s == 1) {
                    dist(HttpUrl.hushisend_goods);
                } else {
                    Http.refuse(MerchantsOrderDetailActivity.this, orderNo, list);
                }
            }
        });
        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (s == 1) {
                    dist(HttpUrl.send_goods);
                } else {
                    Http.agree(MerchantsOrderDetailActivity.this, orderNo, list);
                }
            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("latitude", latitude);
                intent.putExtra("longitude", longitude);
                intent.setClass(MerchantsOrderDetailActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                } else if (position == (list.size() + 1)) {
                } else {
                    intent = new Intent();
                    intent.putExtra("merid", list.get(position - 1).getMerid());//商品编码
                    toGetGoodsDetailUp(position - 1);
                }
            }
        });
    }

    /**
     * 发货
     */
    public void dist(String url) {
        SendPostBean sendPostBean = new SendPostBean();
        sendPostBean.setOrdno(orderNo);
        sendPostBean.setStoreid(UserInfo.getInstance().getUser(MerchantsOrderDetailActivity.this).getStoreid());
        sendPostBean.setUrl(url);
        Http.send(MerchantsOrderDetailActivity.this, sendPostBean);
    }

    /***
     * 商品详情
     *
     * @param position
     */
    public void toGetGoodsDetailUp(int position) {
        MerIdPostBean merIdPostBean = new MerIdPostBean();
        merIdPostBean.setMerid(list.get(position).getMerid());
        Http.upGoodsDetail(MerchantsOrderDetailActivity.this, merIdPostBean);
    }

    /**
     * 商家订单详情接口
     */
    public void getList() {
        SendPostBean sendPostBean = new SendPostBean();
        sendPostBean.setOrdno(orderNo);
        MutualApplication.getRequestQueue().cancelAll("merchantsOrderDetail47");
        Http.merchantsOrderDetail(MerchantsOrderDetailActivity.this, sendPostBean, buyerName, buyerGender, buyerPhone, buyerDetailAddress, buyerAddress, orderTime, orderId, peopleLinearLayout, orderMoney, payType, LeavingMessage, sendFee, returnBeiBi, coupon_line, amountPaidLine, amountPaid_line, coupon_money, amountPaid, distLinearLayout, deliveryClerkPhone, status, invoice, linearLayoutBottom, Cancel, Confirm, linearLayoutPeople, list, listView, checkAll, checkAllLinearLayout, linearLayoutPayType);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MutualApplication.getRequestQueue().cancelAll("upGoodsDetail25");
        MutualApplication.getRequestQueue().cancelAll("agree44");
        MutualApplication.getRequestQueue().cancelAll("refuse45");
        MutualApplication.getRequestQueue().cancelAll("send46");
        MutualApplication.getRequestQueue().cancelAll("merchantsOrderDetail47");
    }
}
