package com.example.administrator.merchants.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.merchants.R;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.show.CommodityShowBean;

import java.util.List;

/**
 * 作者：韩宇 on 2017/7/20 0020 14:33
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：原产地商品适配器
 */
public class OriginalHomeYouLikeListAdapter extends BaseAdapter {
    private Context context;
    private List<CommodityShowBean> list;

    public OriginalHomeYouLikeListAdapter(Context context, List<CommodityShowBean> list) {
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
        HolderTest holderTest;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_original_home_you_like, null);
            holderTest = new HolderTest();
            holderTest.ImageViewEightCircleOne = (ImageView) convertView.findViewById(R.id.iv_yuan);//图片
            holderTest.TextViewEightCircleOne = (TextView) convertView.findViewById(R.id.guess_title);//标题
            holderTest.TextViewEightCircleTwo = (TextView) convertView.findViewById(R.id.tv_yuan_content);//简介
            holderTest.TextViewEightCircleThree = (TextView) convertView.findViewById(R.id.danjia);//单价
            holderTest.TextViewEightCircleFour = (TextView) convertView.findViewById(R.id.tv_rating_num);//评分
            holderTest.TextViewEightCircleFive = (TextView) convertView.findViewById(R.id.month_count);//月销量
            holderTest.TextViewEightCircleSix = (TextView) convertView.findViewById(R.id.store_count);//库存
            holderTest.RatingBar = (RatingBar) convertView.findViewById(R.id.yuan_rating);//星星
            holderTest.discount= (TextView) convertView.findViewById(R.id.goodsDetail_discount);
            convertView.setTag(holderTest);
        } else {
            holderTest = (HolderTest) convertView.getTag();
        }
        Glide.with(context.getApplicationContext())
                .load(list.get(position).getImgsfile())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.image_loading)
                .into(holderTest.ImageViewEightCircleOne);
        holderTest.TextViewEightCircleOne.setText(list.get(position).getMername());
        String p = String.valueOf(list.get(position).getSaleprice());
        String qian = p.substring(0, p.lastIndexOf("."));
        String hou = p.substring(p.lastIndexOf("."), p.length());
        double b = Double.parseDouble(hou);
        if (b == 0) {
            holderTest.TextViewEightCircleThree.setText(qian);
        } else {
            holderTest.TextViewEightCircleThree.setText(list.get(position).getSaleprice() + "");
        }
        holderTest.TextViewEightCircleFour.setText(list.get(position).getScoreavg() + "");
        holderTest.RatingBar.setRating((float) list.get(position).getScoreavg());
        holderTest.TextViewEightCircleFive.setText((int) list.get(position).getMonthsalenum() + "");
        holderTest.TextViewEightCircleSix.setText((int) list.get(position).getStorenum() + "");
        if (list.get(position).getMerdescr().equals("")) {
            holderTest.TextViewEightCircleTwo.setText("该产品暂无详情！");
        } else {
            holderTest.TextViewEightCircleTwo.setText(list.get(position).getMerdescr());
        }
        Http.discountText(context,holderTest.discount);
        return convertView;
    }

    class HolderTest {
        public ImageView ImageViewEightCircleOne;
        public TextView TextViewEightCircleOne;
        public TextView TextViewEightCircleTwo;
        public TextView TextViewEightCircleThree;
        public TextView TextViewEightCircleFour;
        public TextView TextViewEightCircleFive;
        public TextView TextViewEightCircleSix;
        public RatingBar RatingBar;
        private TextView discount;
    }
}