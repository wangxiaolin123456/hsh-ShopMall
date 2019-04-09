package com.example.administrator.merchants.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.http.show.MonthIncomeShowBean;

import java.util.List;

/**
 * 作者：韩宇 on 2017/7/20 0020 14:33
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：月收益统计适配器
 */
public class MonthIncomeBaseAdapter extends BaseAdapter {
    private Context context;
    private List<MonthIncomeShowBean> list;
    private MonthIncomeHolder holder;

    public MonthIncomeBaseAdapter(Context context, List list) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_month_income, null);
            holder = new MonthIncomeHolder();
            holder.begin = (TextView) convertView.findViewById(R.id.item_begin_time);
            holder.time = ((TextView) convertView.findViewById(R.id.item_month_income_time));
            holder.getProfit = ((TextView) convertView.findViewById(R.id.item_month_income_get_profit));
            holder.dealAmount = ((TextView) convertView.findViewById(R.id.item_month_income_all_money));
            convertView.setTag(holder);
        } else {
            holder = (MonthIncomeHolder) convertView.getTag();
        }
        holder.time.setText(list.get(position).getTime());
        holder.begin.setText(list.get(position).getBegintime());
        holder.dealAmount.setText(String.valueOf(list.get(position).getDealAmount()));
        holder.getProfit.setText(String.valueOf(list.get(position).getGetProfit()));
        return convertView;
    }

    public class MonthIncomeHolder {
        public TextView time, begin;//时间
        public TextView dealAmount;//成交总额
        public TextView getProfit;//获得收益
    }
}
