package com.example.administrator.merchants.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.http.show.RecruitmentManagementShowBean;

import java.util.List;

/**
 * 作者：韩宇 on 2017/6/23 0023 14:39
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：招聘信息列表适配器
 */
public class RecruitmentManagementAdapter extends BaseAdapter {
    private Context context;
    private List<RecruitmentManagementShowBean> list;

    public RecruitmentManagementAdapter(Context context, List<RecruitmentManagementShowBean> list) {
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
        Holder holder;
        RecruitmentManagementShowBean recruitmentManagementShowBean = list.get(position);
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_recruitment_management, null);
            holder.name = (TextView) convertView.findViewById(R.id.item_recruitment_management_name);
            holder.number = (TextView) convertView.findViewById(R.id.item_recruitment_management_number);
            holder.experience = (TextView) convertView.findViewById(R.id.item_recruitment_management_experience);
            holder.compensation = (TextView) convertView.findViewById(R.id.item_recruitment_management_compensation);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.name.setText(recruitmentManagementShowBean.getName());
        holder.number.setText(recruitmentManagementShowBean.getNumber());
        holder.experience.setText(recruitmentManagementShowBean.getExperience());
        holder.compensation.setText(recruitmentManagementShowBean.getCompensation());
        return convertView;
    }

    public class Holder {
        public TextView name;//职位
        public TextView number;//招聘人数
        public TextView experience;//工作经验
        public TextView compensation;//薪资
    }
}
