package com.example.administrator.merchants.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.activity.BankCardActivity;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.post.DefaultBankCardPostBean;
import com.example.administrator.merchants.http.show.BankShowBean;

import java.util.List;

/**
 * 作者：韩宇 on 2017/7/20 0020 14:33
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：银行卡列表适配器
 */
public class BankAdapter extends BaseAdapter {
    private Context context;
    private List<BankShowBean> list;

    public BankAdapter(Context context, List<BankShowBean> list) {
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
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_bank_list, null);
            holder.update = (ImageView) convertView.findViewById(R.id.item_bank_list_update);
            holder.bankName = (TextView) convertView.findViewById(R.id.item_bank_list_bank_name);
            holder.userName = (TextView) convertView.findViewById(R.id.item_bank_list_user_name);
            holder.tel = (TextView) convertView.findViewById(R.id.item_bank_list_tel);
            holder.bankNumber = (TextView) convertView.findViewById(R.id.item_bank_list_bank_number);
            holder.checkbox = (CheckBox) convertView.findViewById(R.id.item_bank_list_checkbox);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.bankName.setText(list.get(position).getBankName());
        holder.userName.setText(list.get(position).getUserName());
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("type", "update");
                intent.putExtra("cardid", list.get(position).getBankid());
                intent.putExtra("bindaccount", list.get(position).getBankNumber());
                intent.putExtra("bindbank", list.get(position).getBankName());
                intent.putExtra("bindname", list.get(position).getUserName());
                intent.putExtra("bindphone", list.get(position).getTelephone());
                intent.setClass(context, BankCardActivity.class);
                context.startActivity(intent);
            }
        });
        holder.tel.setText(list.get(position).getTelephone());
        holder.bankNumber.setText(list.get(position).getBankNumber());
        holder.checkbox.setTag(list.get(position).getBankid());
        holder.checkbox.setOnCheckedChangeListener(null);
        if ("1".equals(list.get(position).getStu())) {
            holder.checkbox.setChecked(true);
        } else {
            holder.checkbox.setChecked(false);
        }
        holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                defaultLocation(position);
            }
        });
        return convertView;
    }

    /**
     * 设置默认银行卡
     *
     * @param position
     */
    public void defaultLocation(final int position) {
        DefaultBankCardPostBean defaultBankCardPostBean = new DefaultBankCardPostBean();
        defaultBankCardPostBean.setCardid(list.get(position).getBankid());
        defaultBankCardPostBean.setStoreid(UserInfo.getInstance().getUser(context).getStoreid());
        Http.defaultBank(context, defaultBankCardPostBean, list, (position + 1), this);
    }

    public class Holder {
        public ImageView update;//修改
        public CheckBox checkbox;//状态
        public TextView bankName;//姓名
        public TextView userName;//性别
        public TextView tel;//电话
        public TextView bankNumber;//地址
    }
}
