package com.example.administrator.merchants.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.http.show.StatementAccountShowBean;

import java.util.List;

/**
 * 作者：韩宇 on 2017/6/23 0023 14:39
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：对账单适配器
 */
public class StatementAccountBaseAdapter extends BaseAdapter {
    private Context context;
    private List<StatementAccountShowBean> list;

    public StatementAccountBaseAdapter(Context context, List<StatementAccountShowBean> list) {
        this.context = context;
        this.list = list;
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
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_statement_account, null);
            holder.OrderMoney = (TextView) convertView.findViewById(R.id.item_statement_account_orderMoney);//订单总额
            holder.BackBeiBi = (TextView) convertView.findViewById(R.id.item_statement_account_backBeiBi);//返贝币
            holder.CustomaryDues = (TextView) convertView.findViewById(R.id.item_statement_account_customaryDues);//应付款
            holder.OrderNumber = (TextView) convertView.findViewById(R.id.item_statement_account_orderNumber);//总共的订单数
            holder.PayDelivery = (TextView) convertView.findViewById(R.id.item_statement_account_payDelivery);//货到付款
            holder.PlatformLicensing = (TextView) convertView.findViewById(R.id.item_statement_account_platformLicensing);//平台使用费用
            holder.ShippingRates = (TextView) convertView.findViewById(R.id.item_statement_account_shippingRates);//配送费
            holder.Stu = (Button) convertView.findViewById(R.id.item_statement_account_stu_btn);//状态
            holder.Time = (TextView) convertView.findViewById(R.id.item_statement_account_time);//时间
            holder.ToIssueDebt = (TextView) convertView.findViewById(R.id.item_statement_account_toIssueDebt);//往期欠款
            holder.TripartitePaysFee = (TextView) convertView.findViewById(R.id.item_statement_account_tripartitePaysFee);//三方支付费
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.Time.setText(list.get(position).getTime());
        holder.BackBeiBi.setText("-" + String.valueOf(list.get(position).getBackBeiBi()));
        holder.CustomaryDues.setText(String.valueOf(list.get(position).getCustomaryDues()));
        holder.OrderMoney.setText(String.valueOf(list.get(position).getOrderMoney()));
        holder.OrderNumber.setText("共" + String.valueOf(list.get(position).getOrderNumber()) + "单");
        holder.PayDelivery.setText(String.valueOf(list.get(position).getPayDelivery()));
        holder.PlatformLicensing.setText("-" + String.valueOf(list.get(position).getPlatformLicensing()));
        holder.ShippingRates.setText("-" + String.valueOf(list.get(position).getShippingRates()));
        holder.ToIssueDebt.setText("-" + String.valueOf(list.get(position).getToIssueDebt()));
        holder.TripartitePaysFee.setText("-" + String.valueOf(list.get(position).getTripartitePaysFee()));
        if (list.get(position).getType() == 0) {
            //代打款
            holder.Stu.setBackgroundResource(R.drawable.dialog_yellow_white);
            holder.Stu.setTextColor(Color.parseColor("#ff9f08"));
            holder.Stu.setText("待打款");
        } else if (list.get(position).getType() == 1) {
            //打款成功
            holder.Stu.setBackgroundResource(R.drawable.account_bills_red_white);
            holder.Stu.setTextColor(Color.parseColor("#FF0000"));
            holder.Stu.setText("打款成功");
        } else if (list.get(position).getType() == 2) {
            //帐户欠费
            holder.Stu.setBackgroundResource(R.drawable.account_bills_gray_white);
            holder.Stu.setTextColor(Color.parseColor("#898e8d"));
            holder.Stu.setText("帐户欠费");
        }
        return convertView;
    }

    class Holder {
        public TextView OrderMoney;
        public TextView BackBeiBi;//返贝币
        public TextView CustomaryDues;//应付款
        public TextView OrderNumber;//总共的订单数
        public TextView PayDelivery;//货到付款
        public TextView PlatformLicensing;//平台使用费用
        public TextView ShippingRates;//配送费
        public Button Stu;//状态
        public TextView Time;//时间
        public TextView ToIssueDebt;//往期欠款
        public TextView TripartitePaysFee;
    }
}
