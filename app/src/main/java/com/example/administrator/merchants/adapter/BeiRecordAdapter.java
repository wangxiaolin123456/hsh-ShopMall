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
 * 功能：贝币记录适配器
 */
public class BeiRecordAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    public static final int VALUE_TWO = 1;
    public static final int VALUE_THREE = 2;
    private Context context;
    private List<BeiRecordShowBean> myList;
    private String stu;

    public BeiRecordAdapter(Context context, List<BeiRecordShowBean> myList, String stu) {
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
        int type = getItemViewType(position);//获取item类型
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            switch (type) {
                case VALUE_TWO:
                    convertView = inflater.inflate(R.layout.item_bei_record_add, null);
                    holder.date = (TextView) convertView.findViewById(R.id.item_bei_record_add_date);
                    holder.add = (TextView) convertView.findViewById(R.id.item_bei_record_add_number);
                    holder.miaoshu = (TextView) convertView.findViewById(R.id.item_bei_record_add_miaoshu);
//                    holder.name= (TextView) convertView.findViewById(R.id.item_bei_record_add_name);
                    break;
                case VALUE_THREE:
                    convertView = inflater.inflate(R.layout.item_bei_record_jian, null);
                    holder.date = (TextView) convertView.findViewById(R.id.item_bei_record_jian_date);
                    holder.jian = (TextView) convertView.findViewById(R.id.item_bei_record_jian_number);
                    holder.miaoshu = (TextView) convertView.findViewById(R.id.item_bei_record_jian_miaoshu);
//                    holder.name= (TextView) convertView.findViewById(R.id.item_bei_record_jian_name);
                    break;
            }
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        switch (type) {
            case VALUE_TWO:
                holder.date.setText(recordBean.getDate2());
//                + recordBean.getDate()
                if (stu.equals("j")) {
                    holder.add.setText(recordBean.getAdd() + "金贝币");
                } else if (stu.equals("y")) {
                    holder.add.setText(recordBean.getAdd() + "银贝币");
                }
                holder.miaoshu.setText(recordBean.getMiaoshu());
//                holder.name.setText(recordBean.getInoutobjname());
                break;
            case VALUE_THREE:
                holder.date.setText(recordBean.getDate2());
//                + recordBean.getDate()
                if (stu.equals("j")) {
                    holder.jian.setText(recordBean.getJian() + "金贝币");
                } else if (stu.equals("y")) {
                    holder.jian.setText(recordBean.getJian() + "银贝币");
                }

                holder.miaoshu.setText(recordBean.getMiaoshu());
//                holder.name.setText(recordBean.getInoutobjname());
                break;
        }
        return convertView;
    }

    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        BeiRecordShowBean activityBean = myList.get(position);
        int type = activityBean.getType();//获取类型
        return type;
    }

    class Holder {
        private TextView date;//日期
        private TextView add;//收入
        private TextView jian;//支出
        private TextView miaoshu;//描述
//        private TextView name;
    }
}
