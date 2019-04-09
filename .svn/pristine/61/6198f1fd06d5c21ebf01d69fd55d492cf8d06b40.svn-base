package com.example.administrator.merchants.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.http.show.MessageShowBean;

import java.util.List;

/**
 * 作者：韩宇 on 2017/7/20 0020 14:33
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：消息列表适配器
 */
public class MyMessageAdapter extends BaseAdapter {
    private Context context;
    private List<MessageShowBean> list;

    public MyMessageAdapter(Context context, List<MessageShowBean> list) {
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
        MessageShowBean messageShowBean = list.get(position);
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_message, null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.item_message_image);
            holder.titleText = (TextView) convertView.findViewById(R.id.item_message_title);
            holder.message = (TextView) convertView.findViewById(R.id.item_message_message);
            holder.date = (TextView) convertView.findViewById(R.id.item_message_date);
            holder.read = (ImageView) convertView.findViewById(R.id.read);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.imageView.setImageResource(messageShowBean.getImage());
        if ("1".equals(messageShowBean.getIsused())) {
            holder.read.setImageResource(R.drawable.read_no);
        } else {
            holder.read.setImageResource(R.drawable.read_already);
        }
        if (messageShowBean.getType()==1){
            holder.imageView.setImageResource(R.drawable.message_notice);
        }else if (messageShowBean.getType()==2){
            holder.imageView.setImageResource(R.drawable.message_rebate);
        }else {
            holder.imageView.setImageResource(R.drawable.message_order);
        }
        holder.titleText.setText(messageShowBean.getTitle());
        holder.date.setText(messageShowBean.getTime());
        holder.message.setText(messageShowBean.getMessage());
        return convertView;
    }

    public class Holder {
        public ImageView imageView;//图片
        public TextView titleText;//标题名TextView
        public TextView date;//日期
        public TextView message;//消息
        public ImageView read;
    }
}
