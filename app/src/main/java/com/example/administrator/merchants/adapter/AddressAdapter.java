package com.example.administrator.merchants.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.http.show.PopupMenuShowBean;

import java.util.List;

/**
 * 作者：韩宇 on 2017/7/20 0020 14:33
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：商品分类菜单
 */
public class AddressAdapter extends BaseAdapter {
    private Context context;
    private List<PopupMenuShowBean> list;

    public AddressAdapter(Context context, List<PopupMenuShowBean> list) {
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
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_address_popup, null);
        }
        TextView typename = (TextView) convertView.findViewById(R.id.typename);
        typename.setText(list.get(position).getMenuname());
        if (list.get(position).getColor() == 1) {
            typename.setTextColor(Color.parseColor("#fc0404"));
        } else {
            typename.setTextColor(Color.parseColor("#000000"));
        }
        return convertView;
    }
}
