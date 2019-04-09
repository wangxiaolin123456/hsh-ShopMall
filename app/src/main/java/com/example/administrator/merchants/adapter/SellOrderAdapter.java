package com.example.administrator.merchants.adapter;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.merchants.R;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.HttpUrl;
import com.example.administrator.merchants.http.post.SendPostBean;
import com.example.administrator.merchants.http.show.OrderShowBean;

import java.util.List;

/**
 * 作者：韩宇 on 2017/6/23 0023 14:39
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：商家订单列表适配器
 */
public class SellOrderAdapter extends BaseAdapter {
    private Context context;
    private List<OrderShowBean> list;
    private Holder holder;

    public SellOrderAdapter(Context context, List<OrderShowBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
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
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_order_merchant, null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.item_order_image);
            holder.id = (TextView) convertView.findViewById(R.id.item_order_id);
            holder.stu = (TextView) convertView.findViewById(R.id.item_order_stu);
            holder.times = (TextView) convertView.findViewById(R.id.item_order_time);
            holder.news = (TextView) convertView.findViewById(R.id.item_order_news);
            holder.btn = (TextView) convertView.findViewById(R.id.btn);
            holder.hshSend = (TextView) convertView.findViewById(R.id.hushipeisong);
            holder.linearLayout = (LinearLayout) convertView.findViewById(R.id.linearLayout);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        Glide.with(context.getApplicationContext()).load(list.get(position).getOrdimgsfile()).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.image_loading).into(holder.imageView);
        holder.id.setText(list.get(position).getOrdno());
        holder.times.setText(list.get(position).getCreatetimestr());
        holder.news.setText(list.get(position).getOrdmername() + "等" + list.get(position).getOrdmerqty() + "件商品");
        String ordType = list.get(position).getOrdtype();
        if ("due".equals(ordType)) { //非外卖
            String ordStatus = list.get(position).getOrdstatus();//订单完成状态
            switch (ordStatus) {
                case "0"://未完成
                    String payStatus = list.get(position).getPaystatus();
                    switch (payStatus) {
                        case "0"://未付款
                            holder.stu.setBackgroundResource(R.drawable.account_bills_red_white);
                            holder.stu.setTextColor(Color.RED);
                            holder.stu.setText("未付款");
                            holder.btn.setVisibility(View.GONE);
                            holder.hshSend.setVisibility(View.GONE);
                            break;
                        case "2"://申请退款
                            holder.stu.setBackgroundResource(R.drawable.account_bills_red_white);
                            holder.stu.setTextColor(Color.RED);
                            holder.stu.setText("待确认");
                            holder.btn.setVisibility(View.GONE);
                            holder.hshSend.setVisibility(View.GONE);
                            holder.linearLayout.setVisibility(View.VISIBLE);
                            break;
                        case "3"://退款驳回
                            holder.stu.setBackgroundResource(R.drawable.account_bills_red_white);
                            holder.stu.setTextColor(Color.RED);
                            holder.stu.setText("待确认");
                            holder.btn.setVisibility(View.GONE);
                            holder.hshSend.setVisibility(View.GONE);
                            break;
                        case "1"://已付款
                            holder.stu.setBackgroundResource(R.drawable.account_bills_red_white);
                            holder.stu.setTextColor(Color.RED);
                            holder.stu.setText("待确认");
                            holder.btn.setVisibility(View.GONE);
                            holder.hshSend.setVisibility(View.GONE);
                            break;
                    }
                    break;
                case "1"://已完成  不用管退没退款   包含了已退款
                    holder.stu.setBackgroundResource(R.drawable.account_bills_gray_white);
                    holder.stu.setTextColor(Color.GRAY);
                    holder.stu.setText("已完成");
                    holder.btn.setVisibility(View.GONE);
                    holder.hshSend.setVisibility(View.GONE);
                    break;
            }
        } else {  //外卖
            String ordStatus = list.get(position).getOrdstatus();//订单完成状态
            switch (ordStatus) {
                case "0"://未完成
                    String payStatus = list.get(position).getPaystatus();
                    switch (payStatus) {
                        case "0"://未付款
                            holder.stu.setBackgroundResource(R.drawable.account_bills_red_white);
                            holder.stu.setTextColor(Color.RED);
                            holder.stu.setText("待确认");
                            holder.btn.setVisibility(View.GONE);
                            holder.hshSend.setVisibility(View.GONE);
                            break;
                        case "1"://已付款
                            String sendStatus = list.get(position).getSendstatus();
                            if ("1".equals(sendStatus)) {//发货了
                                holder.stu.setBackgroundResource(R.drawable.account_bills_red_white);
                                holder.stu.setTextColor(Color.RED);
                                holder.stu.setText("待确认");
                                holder.btn.setBackgroundResource(R.drawable.account_bills_gray_white);
                                holder.btn.setTextColor(Color.GRAY);
                                holder.hshSend.setVisibility(View.GONE);
                                holder.btn.setText("已发货");
                            } else {//没发货
                                holder.stu.setBackgroundResource(R.drawable.account_bills_red_white);
                                holder.stu.setTextColor(Color.RED);
                                holder.stu.setText("待确认");
                                holder.btn.setBackgroundResource(R.drawable.dialog_theme);
                                holder.btn.setTextColor(Color.parseColor("#ffffff"));
                                holder.btn.setText("确认发货");
                                if ("".equals(list.get(position).getStationid())) {
                                    holder.hshSend.setVisibility(View.GONE);
                                    holder.btn.setVisibility(View.VISIBLE);
                                } else {
                                    holder.hshSend.setVisibility(View.VISIBLE);
                                    holder.btn.setVisibility(View.GONE);
                                }
                            }
                            break;
                        case "2"://退款申请
                            String sendStatus1 = list.get(position).getSendstatus();
                            if ("1".equals(sendStatus1)) {//发货了
                                holder.stu.setBackgroundResource(R.drawable.account_bills_red_white);
                                holder.stu.setTextColor(Color.RED);
                                holder.stu.setText("待确认");
                                holder.btn.setBackgroundResource(R.drawable.account_bills_gray_white);
                                holder.btn.setTextColor(Color.GRAY);
                                holder.btn.setText("已发货");
                                holder.hshSend.setVisibility(View.GONE);
                            } else {//没发货
                                holder.stu.setBackgroundResource(R.drawable.account_bills_red_white);
                                holder.stu.setTextColor(Color.RED);
                                holder.stu.setText("待确认");
                                holder.btn.setBackgroundResource(R.drawable.dialog_theme);
                                holder.btn.setTextColor(Color.parseColor("#ffffff"));
                                holder.btn.setText("确认发货");
                                if ("".equals(list.get(position).getStationid())) {
                                    holder.hshSend.setVisibility(View.GONE);
                                } else {
                                    holder.hshSend.setVisibility(View.VISIBLE);
                                    holder.btn.setVisibility(View.GONE);
                                }
                            }
                            break;
                        case "5"://    货到付款 未完成
                            String sendStatus5 = list.get(position).getSendstatus();
                            if ("1".equals(sendStatus5)) {//发货了
                                holder.stu.setBackgroundResource(R.drawable.account_bills_red_white);
                                holder.stu.setTextColor(Color.RED);
                                holder.stu.setText("货到付款");
                                holder.btn.setBackgroundResource(R.drawable.account_bills_gray_white);
                                holder.btn.setTextColor(Color.GRAY);
                                holder.btn.setText("已发货");
                                holder.hshSend.setVisibility(View.GONE);
                            } else {//没发货
                                holder.stu.setBackgroundResource(R.drawable.account_bills_red_white);
                                holder.stu.setTextColor(Color.RED);
                                holder.stu.setText("货到付款");
                                holder.btn.setBackgroundResource(R.drawable.dialog_theme);
                                holder.btn.setTextColor(Color.parseColor("#ffffff"));
                                holder.btn.setText("确认发货");
                                if ("".equals(list.get(position).getStationid())) {
                                    holder.hshSend.setVisibility(View.GONE);
                                } else {
                                    holder.hshSend.setVisibility(View.VISIBLE);
                                    holder.btn.setVisibility(View.GONE);
                                }
                            }
                            break;
                    }
                    break;
                case "1"://已完成  不用管退没退款   包含了已退款
                    holder.stu.setBackgroundResource(R.drawable.account_bills_gray_white);
                    holder.stu.setTextColor(Color.GRAY);
                    holder.stu.setText("已完成");
                    holder.btn.setBackgroundResource(R.drawable.account_bills_gray_white);
                    holder.btn.setTextColor(Color.GRAY);
                    holder.btn.setText("已发货");
                    holder.hshSend.setVisibility(View.GONE);
                    break;
                case "3"://已完成  不用管退没退款   包含了已退款
                    holder.stu.setBackgroundResource(R.drawable.account_bills_gray_white);
                    holder.stu.setTextColor(Color.GRAY);
                    holder.stu.setText("已完成");
                    holder.btn.setBackgroundResource(R.drawable.account_bills_gray_white);
                    holder.btn.setTextColor(Color.GRAY);
                    holder.btn.setText("已发货");
                    holder.hshSend.setVisibility(View.GONE);
                    break;
            }
            holder.hshSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list.get(position).getSendstatus().equals("0")) { //如果没发货
                        SendPostBean sendPostBean = new SendPostBean();
                        sendPostBean.setOrdno(list.get(position).getOrdno());
                        sendPostBean.setStoreid(UserInfo.getInstance().getUser(context).getStoreid());
                        sendPostBean.setUrl(HttpUrl.hushisend_goods);
                        Http.listSend(context, sendPostBean, list, SellOrderAdapter.this, position);
                    }
                }
            });
            holder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list.get(position).getSendstatus().equals("0")) { //如果没发货
                        SendPostBean sendPostBean = new SendPostBean();
                        sendPostBean.setOrdno(list.get(position).getOrdno());
                        sendPostBean.setStoreid(UserInfo.getInstance().getUser(context).getStoreid());
                        sendPostBean.setUrl(HttpUrl.send_goods);
                        Http.listSend(context, sendPostBean, list, SellOrderAdapter.this, position);
                    }
                }
            });
        }
        return convertView;
    }

    public class Holder {
        public ImageView imageView;
        public TextView id;
        public TextView stu;
        public TextView times;
        public TextView news;
        public TextView btn;
        private TextView hshSend;
        private LinearLayout linearLayout;

    }
}
