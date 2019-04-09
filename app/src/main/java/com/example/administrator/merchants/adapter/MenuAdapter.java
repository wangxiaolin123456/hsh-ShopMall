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
 * 功能：商品菜单适配器
 */
public class MenuAdapter extends BaseAdapter {
    private Context context;
    private List<PopupMenuShowBean> popupMenuShowBeanList;

    public MenuAdapter(Context context, List<PopupMenuShowBean> popupMenuShowBeanList) {
        this.context = context;
        this.popupMenuShowBeanList = popupMenuShowBeanList;
    }

    @Override
    public int getCount() {
        return popupMenuShowBeanList == null ? 0 : popupMenuShowBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return popupMenuShowBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_popup_text, null);
        }
        TextView typename = (TextView) convertView.findViewById(R.id.typename);
        typename.setText(popupMenuShowBeanList.get(position).getMenuname());
        if (popupMenuShowBeanList.get(position).getColor() == 1) {
            typename.setTextColor(Color.parseColor("#fc0404"));
        } else {
            typename.setTextColor(Color.parseColor("#000000"));
        }
        return convertView;
    }
}
