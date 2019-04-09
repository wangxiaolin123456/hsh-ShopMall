package com.example.administrator.merchants.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.administrator.merchants.R;
import com.example.administrator.merchants.http.show.EstimatedEarningsDetailsShowBean;

import java.util.List;


/**
 * 作者：韩宇 on 2017/7/20 0020 14:33
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：预估详情适配器
 */
public class EstimatedEarningsDetailsAdapter extends BaseAdapter {
    private Holder holder;
    private List<EstimatedEarningsDetailsShowBean> list;
    private Context context;
    public EstimatedEarningsDetailsAdapter(Context context, List<EstimatedEarningsDetailsShowBean> list) {
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
    public int getViewTypeCount() {
        return 3;
    }
    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        switch (type) {
            case 0://期间
                if (convertView == null) {
                    holder = new Holder();
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_revenue_manage_head, null);
                    holder.TextViewEightCircleOne = (TextView) convertView.findViewById(R.id.tt1);
                    holder.TextViewEightCircleTwo = (TextView) convertView.findViewById(R.id.tt4);
                    holder.TextViewEightCircleThree = (TextView) convertView.findViewById(R.id.tt6);
                    holder.TextViewEightCircleFour = (TextView) convertView.findViewById(R.id.tt10);
                    convertView.setTag(holder);
                } else {
                    holder = (Holder) convertView.getTag();
                }
                holder.TextViewEightCircleOne.setText(list.get(position).getTextEightCircleOne());
                holder.TextViewEightCircleTwo.setText(list.get(position).getTextEightCircleTwo());
                holder.TextViewEightCircleThree.setText(list.get(position).getTextEightCircleThree());
                holder.TextViewEightCircleFour.setText(list.get(position).getTextEightCircleFour());
                break;
            case 1://收益
                if (convertView == null) {
                    holder = new Holder();
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_revenue_manage_true, null);
                    holder.TextViewEightCircleOne = (TextView) convertView.findViewById(R.id.t1);
                    holder.TextViewEightCircleTwo = (TextView) convertView.findViewById(R.id.phone1);
                    holder.TextViewEightCircleThree = (TextView) convertView.findViewById(R.id.money1);
                    holder.TextViewEightCircleFour = (TextView) convertView.findViewById(R.id.complete1);
                    holder.TextViewEightCircleFive = (TextView) convertView.findViewById(R.id.time);
                    holder.TextViewEightCircleSix = (TextView) convertView.findViewById(R.id.t2);
                    holder.TextViewEightCircleSeven = (TextView) convertView.findViewById(R.id.y);
                    convertView.setTag(holder);
                } else {
                    holder = (Holder) convertView.getTag();
                }
                if (list.get(position).getTextEightCircleOne().length() > 7) {
                    holder.TextViewEightCircleOne.setText(list.get(position).getTextEightCircleOne().substring(0, 8) + "***");
                } else {
                    holder.TextViewEightCircleOne.setText(list.get(position).getTextEightCircleOne());
                }
                holder.TextViewEightCircleTwo.setText(list.get(position).getTextEightCircleTwo());
                holder.TextViewEightCircleThree.setText(list.get(position).getTextEightCircleThree());
                holder.TextViewEightCircleFour.setText(list.get(position).getTextEightCircleFour());
                holder.TextViewEightCircleFive.setText(list.get(position).getTextEightCircleFive());
                break;
            case 2://退
                if (convertView == null) {
                    holder = new Holder();
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_revenue_manage_false, null);
                    holder.TextViewEightCircleOne = (TextView) convertView.findViewById(R.id.t1);
                    holder.TextViewEightCircleTwo = (TextView) convertView.findViewById(R.id.phone1);
                    holder.TextViewEightCircleThree = (TextView) convertView.findViewById(R.id.money1);
                    holder.TextViewEightCircleFour = (TextView) convertView.findViewById(R.id.complete1);
                    holder.TextViewEightCircleFive = (TextView) convertView.findViewById(R.id.time);
                    holder.TextViewEightCircleSix = (TextView) convertView.findViewById(R.id.t2);
                    holder.TextViewEightCircleSeven = (TextView) convertView.findViewById(R.id.y);
                    convertView.setTag(holder);
                } else {
                    holder = (Holder) convertView.getTag();
                }
                if (list.get(position).getTextEightCircleOne().length() > 7) {
                    holder.TextViewEightCircleOne.setText(list.get(position).getTextEightCircleOne().substring(0, 8) + "***");
                } else {
                    holder.TextViewEightCircleOne.setText(list.get(position).getTextEightCircleOne());
                }
                holder.TextViewEightCircleTwo.setText(list.get(position).getTextEightCircleTwo());
                holder.TextViewEightCircleThree.setText(list.get(position).getTextEightCircleSeven());
                holder.TextViewEightCircleFour.setText(list.get(position).getTextEightCircleSix());
                holder.TextViewEightCircleFive.setText(list.get(position).getTextEightCircleFive());

                break;
        }
        return convertView;
    }
    public class Holder {
        public TextView TextViewEightCircleOne;
        public TextView TextViewEightCircleSeven;
        public TextView TextViewEightCircleTwo;
        public TextView TextViewEightCircleThree;
        public TextView TextViewEightCircleFour;
        public TextView TextViewEightCircleFive;
        public TextView TextViewEightCircleSix;
    }

}
