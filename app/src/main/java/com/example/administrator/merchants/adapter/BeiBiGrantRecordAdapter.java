package com.example.administrator.merchants.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.http.show.BeiRecordShowBean;

import java.util.List;

/**
 * 作者：韩宇 on 2017/7/20 0020 14:33
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：贝币发放记录适配器
 */
public class BeiBiGrantRecordAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context;
    private List<BeiRecordShowBean> myList;
    private String stu;

    public BeiBiGrantRecordAdapter(Context context, List<BeiRecordShowBean> myList, String stu) {
        this.context = context;
        this.myList = myList;
        this.stu = stu;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return myList == null ? 0 : myList.size();
    }

    @Override
    public Object getItem(int position) {
        return myList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BeiRecordShowBean recordBean = myList.get(position);//实例化工具类
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = inflater.inflate(R.layout.item_bei_grant_record, null);
            holder.date = (TextView) convertView.findViewById(R.id.item_grant_record_time_tv);
            holder.stuTv = (TextView) convertView.findViewById(R.id.item_grant_record_stu_tv);
            holder.numTv = (TextView) convertView.findViewById(R.id.item_grant_record_number_tv);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.date.setText(recordBean.getDate2());
        if (stu.equals("j")) {
//           区分 待发放还是已发放  后台给出字段
            holder.numTv.setText("+" + recordBean.getAdd() + "金贝币");//字段也不对
        } else if (stu.equals("y")) {
            holder.numTv.setText("-" + recordBean.getAdd() + "银贝币");//字段也不对
        }
        holder.stuTv.setText(recordBean.getMiaoshu());
        return convertView;
    }

    class Holder {
        private TextView date;//日期
        private TextView stuTv;
        private TextView numTv;
    }
}
