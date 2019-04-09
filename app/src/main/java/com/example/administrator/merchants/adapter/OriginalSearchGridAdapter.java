package com.example.administrator.merchants.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.common.SharedPreferenceUtil;

import java.util.List;

/**
 * 作者：韩宇 on 2017/6/23 0023 14:39
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：热搜适配器
 */
public class OriginalSearchGridAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;

    public OriginalSearchGridAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }
    public OriginalSearchGridAdapter(Context context) {
        this.context = context;
        list = SharedPreferenceUtil.getInstence().getSearchHistory();
    }

    @Override
    public void notifyDataSetChanged() {
        list = SharedPreferenceUtil.getInstence().getSearchHistory();
        super.notifyDataSetChanged();
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
        HolderTest holderTest = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_search_hot, null);
            holderTest = new HolderTest();
            holderTest.TextViewEightCircleOne = (TextView) convertView.findViewById(R.id.cityname);
            convertView.setTag(holderTest);
        } else {
            holderTest = (HolderTest) convertView.getTag();
        }
        holderTest.TextViewEightCircleOne.setText(list.get(position));
        return convertView;
    }

    class HolderTest {
        public TextView TextViewEightCircleOne;
    }
}
