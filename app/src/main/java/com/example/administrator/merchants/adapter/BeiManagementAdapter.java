package com.example.administrator.merchants.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.http.show.BeiManagementShowBean;

import java.util.List;

/**
 * 作者：韩宇 on 2017/7/20 0020 14:33
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：贝币管理适配器
 */
public class BeiManagementAdapter extends BaseAdapter {
    private Context context;
    private List<BeiManagementShowBean> list;

    public BeiManagementAdapter(Context context, List<BeiManagementShowBean> list) {
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
        Holder holder;
        BeiManagementShowBean beiManagementShowBean = list.get(position);
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_bei_management, null);
            holder.title = (TextView) convertView.findViewById(R.id.item_bei_managent_title);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.title.setText(beiManagementShowBean.getText());
        return convertView;
    }

    public class Holder {
        public TextView title;//功能
    }
}
