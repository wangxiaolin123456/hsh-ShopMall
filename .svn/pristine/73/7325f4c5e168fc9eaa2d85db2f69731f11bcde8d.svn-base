package com.example.administrator.merchants.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.merchants.R;
import com.example.administrator.merchants.http.show.ServiceShowBean;

import java.util.List;

/**
 * 作者：韩宇 on 2017/6/23 0023 14:39
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：服务列表适配器
 */
public class ServiceBaseAdapter extends BaseAdapter {
    private Context context;
    private List<ServiceShowBean> list;
    private Holder holder;

    public ServiceBaseAdapter(Context context, List<ServiceShowBean> list) {
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
    public View getView(final int position, View convertView, final ViewGroup parent) {
        holder = new Holder();
        convertView = LayoutInflater.from(context).inflate(R.layout.item_service, null);
        holder.linearLayout = (LinearLayout) convertView.findViewById(R.id.item_service_line);
        holder.content = (TextView) convertView.findViewById(R.id.item_service_name);
        holder.more = (ImageView) convertView.findViewById(R.id.item_service_image);
        holder.view = convertView.findViewById(R.id.item_service_view);
        holder.views = convertView.findViewById(R.id.item_service_views);
        convertView.setTag(holder);
        holder.content.setText(list.get(position).getContent());
        Glide.with(context.getApplicationContext()).load(list.get(position).getImage()).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.image_loading).into(holder.more);
        if (position == list.size() - 1) {
            holder.view.setVisibility(View.GONE);
            holder.views.setVisibility(View.VISIBLE);
        } else {
            holder.view.setVisibility(View.VISIBLE);
            holder.views.setVisibility(View.GONE);
        }
        return convertView;
    }

    public class Holder {
        public LinearLayout linearLayout;
        public TextView content;
        private ImageView more;
        private View view, views;
    }
}

