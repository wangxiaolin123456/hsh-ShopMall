package com.example.administrator.merchants.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.merchants.R;
import com.example.administrator.merchants.http.show.CommodityShowBean;

import java.util.List;

/**
 * 作者：韩宇 on 2017/7/20 0020 14:33
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：首页适配器
 */
public class HomePageAdapter extends BaseAdapter {
    private List<CommodityShowBean> list;
    private Context context;

    public HomePageAdapter(List<CommodityShowBean> list, Context context) {
        this.list = list;
        this.context = context;
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

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderTest holderTest = null;
        if (convertView == null) {
            holderTest = new HolderTest();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_homepage_announcement, null);
            holderTest.ImageViewEightCircleOne = (ImageView) convertView.findViewById(R.id.announcement_image);//图片
            holderTest.TextViewEightCircleOne = (TextView) convertView.findViewById(R.id.announcement_title);//标题
            holderTest.TextViewEightCircleTwo = (TextView) convertView.findViewById(R.id.announcement_content);//简介
            holderTest.TextViewEightCircleThree = (TextView) convertView.findViewById(R.id.announcement_time);//时间
            convertView.setTag(holderTest);
        } else {
            holderTest = (HolderTest) convertView.getTag();
        }
        Glide.with(context.getApplicationContext()).load(list.get(position).getImgsfile()).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.image_loading).into(holderTest.ImageViewEightCircleOne);
        holderTest.TextViewEightCircleOne.setText(list.get(position).getMername());
        holderTest.TextViewEightCircleThree.setText(list.get(position).getTime());
        if (!list.get(position).getMerdescr().equals("")) {
            holderTest.TextViewEightCircleTwo.setText(list.get(position).getMerdescr());
        } else {
            holderTest.TextViewEightCircleTwo.setText("该公告暂无详情！");
        }
        return convertView;
    }

    class HolderTest {
        public ImageView ImageViewEightCircleOne;
        public TextView TextViewEightCircleOne;
        public TextView TextViewEightCircleTwo;
        public TextView TextViewEightCircleThree;

    }
}


