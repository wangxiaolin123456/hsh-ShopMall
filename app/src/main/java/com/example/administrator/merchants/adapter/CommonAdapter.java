package com.example.administrator.merchants.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.diy.widget.CircularImage;
import com.example.administrator.merchants.R;
import com.example.administrator.merchants.activity.EnlargePictureActivity;
import com.example.administrator.merchants.http.show.GoodsDetailShowBean;

import java.util.List;


/**
 * 作者：韩宇 on 2017/7/20 0020 14:33
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：评论适配器
 */
public class CommonAdapter extends BaseAdapter {
    private Context context;
    private List<GoodsDetailShowBean> list;

    public CommonAdapter(Context context, List<GoodsDetailShowBean> list) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_comment, null);
            holder = new Holder();
            holder.image = (CircularImage) convertView.findViewById(R.id.iv_ask_head);
            holder.name = (TextView) convertView.findViewById(R.id.tv_ask_name);
            holder.content = (TextView) convertView.findViewById(R.id.tv_ask_question);
            holder.ratingBar = (RatingBar) convertView.findViewById(R.id.item_comment_yuan_rating);
            holder.time = (TextView) convertView.findViewById(R.id.tv_ask_time);
            holder.imageOne = (ImageView) convertView.findViewById(R.id.iv_ask_first);
            holder.imageTwo = (ImageView) convertView.findViewById(R.id.iv_ask_second);
            holder.imageThree = (ImageView) convertView.findViewById(R.id.iv_ask_third);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        if (list.get(position).getIsanonymous().equals("0")) {
            //图片缓存
            if ("".equals(list.get(position).getImgsfile())) {
                holder.image.setImageResource(R.drawable.default_avatar);
            } else {
                Glide.with(context.getApplicationContext()).load(list.get(position).getImgsfile()).
                        diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.image_loading).into(holder.image);
            }
            holder.name.setText(list.get(position).getStorename());
        } else {
            holder.name.setText("匿名评价");
            holder.image.setImageResource(R.drawable.default_avatar);
        }
        if (list.get(position).getList().size() == 0) {
            holder.imageOne.setVisibility(View.GONE);
            holder.imageTwo.setVisibility(View.GONE);
            holder.imageThree.setVisibility(View.GONE);
        } else if (list.get(position).getList().size() == 1) {
            holder.imageOne.setVisibility(View.VISIBLE);
            holder.imageTwo.setVisibility(View.GONE);
            holder.imageThree.setVisibility(View.GONE);
            Glide.with(context.getApplicationContext()).load(list.get(position).getList().get(0).getImages()).
                    diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.image_loading).into(holder.imageOne);
        } else if (list.get(position).getList().size() == 2) {
            holder.imageOne.setVisibility(View.VISIBLE);
            holder.imageTwo.setVisibility(View.VISIBLE);
            holder.imageThree.setVisibility(View.GONE);
            Glide.with(context.getApplicationContext()).load(list.get(position).getList().get(0).getImages()).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.image_loading).into(holder.imageOne);
            Glide.with(context.getApplicationContext()).load(list.get(position).getList().get(1).getImages()).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.image_loading).into(holder.imageTwo);
        } else if (list.get(position).getList().size() == 3) {
            holder.imageOne.setVisibility(View.VISIBLE);
            holder.imageTwo.setVisibility(View.VISIBLE);
            holder.imageThree.setVisibility(View.VISIBLE);
            Glide.with(context.getApplicationContext()).load(list.get(position).getList().get(0).getImages()).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.image_loading).into(holder.imageOne);
            Glide.with(context.getApplicationContext()).load(list.get(position).getList().get(1).getImages()).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.image_loading).into(holder.imageTwo);
            Glide.with(context.getApplicationContext()).load(list.get(position).getList().get(2).getImages()).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.image_loading).into(holder.imageThree);
        }
        holder.imageOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("bigimage", list.get(position).getList().get(0).getImagep());
                intent.setClass(context, EnlargePictureActivity.class);
                context.startActivity(intent);
            }
        });
        holder.imageTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("bigimage", list.get(position).getList().get(1).getImagep());
                intent.setClass(context, EnlargePictureActivity.class);
                context.startActivity(intent);
            }
        });
        holder.imageThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("bigimage", list.get(position).getList().get(2).getImagep());
                intent.setClass(context, EnlargePictureActivity.class);
                context.startActivity(intent);
            }
        });
        holder.time.setText(list.get(position).getEvaluatetimestr());
        holder.ratingBar.setRating(list.get(position).getEvaluatedscore());
        if (list.get(position).getEvaluatedescr().equals("")) {
            holder.content.setText("非常满意！！");
        } else {
            holder.content.setText(list.get(position).getEvaluatedescr());
        }
        return convertView;
    }

    public class Holder {
        public CircularImage image;
        public TextView name;
        public TextView content;
        public RatingBar ratingBar;
        public TextView time;
        public ImageView imageOne, imageTwo, imageThree;
    }
}
