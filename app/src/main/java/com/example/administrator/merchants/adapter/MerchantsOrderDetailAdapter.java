package com.example.administrator.merchants.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.merchants.R;
import com.example.administrator.merchants.http.show.RememberStatusShowBean;
import com.example.administrator.merchants.http.show.MerchantsOrderShowBean;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;



/**
 * 作者：韩宇 on 2017/7/20 0020 14:33
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：商家订单详情适配器
 */
public class MerchantsOrderDetailAdapter extends BaseAdapter {
    private Context context;
    private List<MerchantsOrderShowBean> list;
    private CheckBox checkAll;//全选
    private List<RememberStatusShowBean> rememberStatusShowBeans = new ArrayList<>();
    private LinearLayout linearLayoutAll;
    private LinearLayout linearLayoutBottom;
    private LinearLayout linearLayoutPayType;
    private int p = 0;
    private int s = 0;

    public MerchantsOrderDetailAdapter(Context context, List<MerchantsOrderShowBean> list, CheckBox checkAll, LinearLayout linearLayoutAll, LinearLayout linearLayoutBottom, LinearLayout linearLayoutPayType) {
        this.context = context;
        this.list = list;
        this.checkAll = checkAll;
        this.linearLayoutAll = linearLayoutAll;
        this.linearLayoutBottom = linearLayoutBottom;
        this.linearLayoutPayType = linearLayoutPayType;
        initRememberStatusBeans();
        initMap(false);
        initCheck(false);
        listP();
        listS();
    }

    void initRememberStatusBeans() { //toDo  初始化临时集合,String s
        for (int i = 0; i < list.size(); i++) {
            RememberStatusShowBean rememberStatusShowBean = new RememberStatusShowBean();
            rememberStatusShowBean.setChoose(false);
            rememberStatusShowBean.setColor(0);
            rememberStatusShowBean.setVisibility(0);
            rememberStatusShowBeans.add(rememberStatusShowBean);
        }
    }

    void initMap(boolean e) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMerpaystatus().equals("2") || list.get(i).getMerpaystatus().equals("6")) { //是申请退款(2 普通退款 6货到付款退款 )才参与遍历  不然都是false
                rememberStatusShowBeans.get(i).setChoose(e);
            } else {
                rememberStatusShowBeans.get(i).setChoose(false);
            }
        }
    }

    void initCheck(boolean f) {
        for (MerchantsOrderShowBean m : list) {
            if (m.getMerpaystatus().equals("2") || m.getMerpaystatus().equals("6")) { //是申请退款(2 普通退款 6货到付款退款 )才参与遍历  不然都是false
                m.setChoosed(f);
            } else {
                m.setChoosed(false);
            }
        }
    }

    void listP() {//遍历集合   判断是否显示下面的两个按钮
        for (MerchantsOrderShowBean m : list) {
            if (m.getMerpaystatus().equals("2") || m.getMerpaystatus().equals("6")) { //是申请退款就自增 不然都是false
                p++;
            }
        }
        if (p == 0) {
            linearLayoutAll.setVisibility(View.GONE);//去掉全选  没有申请退款的
        } else {
            linearLayoutBottom.setVisibility(View.VISIBLE);//显示底下  取消  同意
        }
    }

    void listS() { //遍历集合   根据付款转态 判断是否显示 支付方式
        for (MerchantsOrderShowBean m : list) {
            if (!m.getMerpaystatus().equals("0")) {
                s++;
            }
        }
        if (s == 0) {
            linearLayoutPayType.setVisibility(View.GONE);//去掉付款方式
        } else {
            linearLayoutPayType.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_merchants_order_detail, null);
            holder.image = (ImageView) convertView.findViewById(R.id.item_origin_order_detail_comm_image);
            holder.title = (TextView) convertView.findViewById(R.id.item_origin_order_detail_comm_title);
            holder.money = (TextView) convertView.findViewById(R.id.item_origin_order_detail_comm_price);
            holder.number = (TextView) convertView.findViewById(R.id.item_origin_order_detail_comm_number);
            holder.time = (TextView) convertView.findViewById(R.id.item_origin_order_detail_comm_time);
            holder.staus = (TextView) convertView.findViewById(R.id.order_staus);
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.item_origin_order_detail_comm_checkBox);
            holder.linearLayout = (LinearLayout) convertView.findViewById(R.id.activity_lines);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.title.setText(list.get(position).getMername());
        DecimalFormat df = new DecimalFormat("0.00");
        String qq = df.format(list.get(position).getSaleprice());
        holder.money.setText(qq);
        holder.number.setText(list.get(position).getMerqty() + "");
        Glide.with(context.getApplicationContext()).load(list.get(position).getImgsfile()).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.image_loading).into(holder.image);
        switch (list.get(position).getMerstatus()) {
            case "0"://未完成
                switch (list.get(position).getMerpaystatus()) {
                    case "0"://未付款 ，目前是不会出现没付钱的订单
                        holder.staus.setText("待付款");
                        holder.time.setText("下单时间:" + list.get(position).getCreatetime());
                        rememberStatusShowBeans.get(position).setVisibility(0);
                        rememberStatusShowBeans.get(position).setColor(1);
                        break;
                    case "1"://已付款 卖家关心的是收不收货
                        switch (list.get(position).getReceivestatus()) {//对方收货的状态
                            case "0"://未收货
                                holder.staus.setText("未收货");
                                holder.time.setText("下单时间:" + list.get(position).getCreatetime());
                                rememberStatusShowBeans.get(position).setVisibility(0);
                                rememberStatusShowBeans.get(position).setColor(1);
                                break;
                            case "3"://toDO 已退货 可能是货退了还没给他退钱的情况
                                holder.staus.setText("已退货");
                                holder.time.setText("申请退款时间:" + list.get(position).getRefapplytime());
                                rememberStatusShowBeans.get(position).setVisibility(0);
                                rememberStatusShowBeans.get(position).setColor(1);
                                break;
                        }
                        break;
                    case "2"://退款申请
                        holder.staus.setText("申请退款");
                        holder.time.setText("申请退款时间:" + list.get(position).getRefapplytime());
                        rememberStatusShowBeans.get(position).setVisibility(1);
                        rememberStatusShowBeans.get(position).setColor(1);
                        break;
                    case "3"://退款驳回
                        holder.staus.setText("退款驳回");
                        holder.time.setText("退款驳回时间:" + list.get(position).getReftime());
                        rememberStatusShowBeans.get(position).setVisibility(0);
                        rememberStatusShowBeans.get(position).setColor(1);
                        break;
                    case "4"://同意退款  但是不是已退款
                        holder.staus.setText("同意退款");
                        holder.time.setText("申请退款时间:" + list.get(position).getRefapplytime());
                        rememberStatusShowBeans.get(position).setVisibility(0);
                        rememberStatusShowBeans.get(position).setColor(1);
                        break;
                    case "5"://下订单时选择的货到付款
                        holder.staus.setText("货到付款");
                        holder.time.setText("下单时间:" + list.get(position).getCreatetime());
                        rememberStatusShowBeans.get(position).setVisibility(0);
                        rememberStatusShowBeans.get(position).setColor(1);
                        break;
                    case "6"://货到付款退款申请
                        holder.staus.setText("货到付款退款申请");
                        holder.time.setText("申请退款时间:" + list.get(position).getRefapplytime());
                        rememberStatusShowBeans.get(position).setVisibility(1);
                        rememberStatusShowBeans.get(position).setColor(1);
                        break;
                    case "7"://货到付款退款驳回
                        holder.staus.setText("货到付款退款驳回");
                        holder.time.setText(list.get(position).getRefapplytime());
                        rememberStatusShowBeans.get(position).setVisibility(0);
                        rememberStatusShowBeans.get(position).setColor(1);
                        break;
                }
                break;
            case "1"://完成了
                Log.e("状态：",list.get(position).getMerpaystatus()+list.get(position).getReceivestatus());
                switch (list.get(position).getMerpaystatus()) {
                    case "1"://已付款
                        switch (list.get(position).getReceivestatus()) { //对方收货的状态
                            case "1"://确认收货
                                holder.staus.setText("确认收货");
                                holder.time.setText("确认收货时间:" + list.get(position).getReceivetime());
                                rememberStatusShowBeans.get(position).setVisibility(0);
                                rememberStatusShowBeans.get(position).setColor(0);
                                break;
                            case "2"://自动确认收货
                                holder.staus.setText("自动确认收货");
                                holder.time.setText("自动确认收货时间:" + list.get(position).getReceivetime());
                                rememberStatusShowBeans.get(position).setVisibility(0);
                                rememberStatusShowBeans.get(position).setColor(0);
                                break;
                        }
                        break;
                    case "3":
                        switch (list.get(position).getReceivestatus()) { //对方收货的状态
                            case "1"://确认收货
                                holder.staus.setText("确认收货");
                                holder.time.setText("确认收货时间:" + list.get(position).getReceivetime());
                                rememberStatusShowBeans.get(position).setVisibility(0);
                                rememberStatusShowBeans.get(position).setColor(0);
                                break;
                            case "2"://自动确认收货
                                holder.staus.setText("自动确认收货");
                                holder.time.setText("自动确认收货时间:" + list.get(position).getReceivetime());
                                rememberStatusShowBeans.get(position).setVisibility(0);
                                rememberStatusShowBeans.get(position).setColor(0);
                                break;
                        }
                        break;
                    case "4":
                        switch (list.get(position).getReceivestatus()) { //对方收货的状态
                            case "1"://确认收货
                                holder.staus.setText("确认收货");
                                holder.time.setText("确认收货时间:" + list.get(position).getReceivetime());
                                rememberStatusShowBeans.get(position).setVisibility(0);
                                rememberStatusShowBeans.get(position).setColor(0);
                                break;
                            case "2"://自动确认收货
                                holder.staus.setText("自动确认收货");
                                holder.time.setText("自动确认收货时间:" + list.get(position).getReceivetime());
                                rememberStatusShowBeans.get(position).setVisibility(0);
                                rememberStatusShowBeans.get(position).setColor(0);
                                break;
                        }
                        break;
                    case "9"://非货到付款已退款
                        holder.staus.setText("已退款");
                        holder.time.setText("退款时间:" + list.get(position).getReftime());
                        rememberStatusShowBeans.get(position).setVisibility(0);
                        rememberStatusShowBeans.get(position).setColor(0);
                        break;
                    case "5"://货到付款
                        switch (list.get(position).getReceivestatus()) { //对方收货的状态
                            case "1"://确认收货
                                holder.staus.setText("货到付款确认收货");
                                holder.time.setText("确认收货时间:" + list.get(position).getReceivetime());
                                rememberStatusShowBeans.get(position).setVisibility(0);
                                rememberStatusShowBeans.get(position).setColor(0);
                                break;
                            case "2"://自动确认收货
                                holder.staus.setText("货到付款自动确认收货");
                                holder.time.setText("自动确认收货时间:" + list.get(position).getReceivetime());
                                rememberStatusShowBeans.get(position).setVisibility(0);
                                rememberStatusShowBeans.get(position).setColor(0);
                                break;
                        }
                        break;
                    case "8"://货到付款已退款
                        holder.staus.setText("货到付款已退款");
                        holder.time.setText("退款时间:" + list.get(position).getReftime());
                        rememberStatusShowBeans.get(position).setVisibility(0);
                        rememberStatusShowBeans.get(position).setColor(0);
                        break;
                }
                break;
        }
        checkAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    initMap(true);
                    initCheck(true);
                } else {
                    initMap(false);
                    initCheck(false);
                }
                MerchantsOrderDetailAdapter.this.notifyDataSetChanged();
            }
        });
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == false) {
                    if (checkAll.isChecked()) {
                        checkAll.setChecked(false);
                        initMap(true);
                        initCheck(true);
                    }
                    rememberStatusShowBeans.get(position).setChoose(false);
                    list.get(position).setChoosed(false);
                } else {
                    if (list.get(position).getMerpaystatus().equals("2") || list.get(position).getMerpaystatus().equals("6")) {//是2,6的时候才参与
                        rememberStatusShowBeans.get(position).setChoose(true);
                        list.get(position).setChoosed(true);
                    }
                }
                int s = 0;
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).isChoosed() == false && (list.get(i).getMerpaystatus().equals("2") || list.get(i).getMerpaystatus().equals("6"))) {//如果有一个没选中   结果就大于0
                        s++;
                    }
                }
                if (s > 0) {
                    checkAll.setChecked(false);
                } else {
                    checkAll.setChecked(true);
                }
                notifyDataSetChanged();
            }
        });
        holder.checkBox.setChecked(rememberStatusShowBeans.get(position).isChoose());//必须放监听之后
        if (rememberStatusShowBeans.get(position).getVisibility() == 0) {
            holder.checkBox.setVisibility(View.GONE);
        } else {
            holder.checkBox.setVisibility(View.VISIBLE);
        }
        if (rememberStatusShowBeans.get(position).getColor() == 0) {
            holder.staus.setTextColor(Color.parseColor("#E4E4E4"));
        } else {
            holder.staus.setTextColor(Color.parseColor("#ff0d00"));
        }
        return convertView;
    }

    public class Holder {
        public TextView title;//商品名
        public TextView money;
        public TextView number;
        public ImageView image;
        public TextView time;
        public TextView staus;
        public CheckBox checkBox;
        public LinearLayout linearLayout;
    }
}
