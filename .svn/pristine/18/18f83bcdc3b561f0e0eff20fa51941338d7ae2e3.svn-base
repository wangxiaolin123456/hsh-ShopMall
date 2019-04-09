package com.example.administrator.merchants.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.diy.widget.CircularImage;
import com.example.administrator.merchants.R;
import com.example.administrator.merchants.http.show.AccumulatedIncomeShowBean;

import java.util.List;

/**
 * 作者：韩宇 on 2017/7/20 0020 14:33
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：推荐人适配器
 */
public class AccumulatedIncomeAdapter extends BaseAdapter {
    private Holder holder;
    private List<AccumulatedIncomeShowBean> list;
    private Context context;

    public AccumulatedIncomeAdapter(Context context, List<AccumulatedIncomeShowBean> list) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_accumulated_income, null);
            holder = new Holder();
            holder.circularImage = (CircularImage) convertView.findViewById(R.id.item_accumulated_income_head);
            holder.name = (TextView) convertView.findViewById(R.id.item_accumulated_income_name);
            holder.incomeMoney = (TextView) convertView.findViewById(R.id.item_accumulated_income_money);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        Glide.with(context.getApplicationContext()).load(list.get(position).getCircularImage()).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.default_avatar).into(holder.circularImage);
        holder.name.setText(list.get(position).getName());
        holder.incomeMoney.setText(list.get(position).getIncomeMoney());
        return convertView;
    }

    class Holder {
        private CircularImage circularImage;//头像
        private TextView name;//名字
        private TextView incomeMoney;//收益金额
    }
}
