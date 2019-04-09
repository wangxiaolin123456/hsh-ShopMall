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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.activity.AddressActivity;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.post.DefaultAddressPostBean;
import com.example.administrator.merchants.http.show.AddressContentShowBean;

import java.util.List;

/**
 * 作者：韩宇 on 2017/7/20 0020 14:33
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：收货地址列表适配器
 */
public class AddressListAdapter extends BaseAdapter {
    private Context context;
    private List<AddressContentShowBean> list;

    public AddressListAdapter(Context context, List<AddressContentShowBean> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_address_list, null);
            holder.update = (LinearLayout) convertView.findViewById(R.id.item_address_list_update);
            holder.name = (TextView) convertView.findViewById(R.id.item_address_list_name);
            holder.sex = (TextView) convertView.findViewById(R.id.item_address_list_sex);
            holder.tel = (TextView) convertView.findViewById(R.id.item_address_list_tel);
            holder.address = (TextView) convertView.findViewById(R.id.item_address_list_address);
            holder.checkbox = (CheckBox) convertView.findViewById(R.id.item_address_list_checkbox);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.name.setText(list.get(position).getReceiver());
        if ("1".equals(list.get(position).getGender())) {
            holder.sex.setText("先生");
        } else {
            holder.sex.setText("女士");
        }
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("updateAddressId", list.get(position).getAddressid());
                intent.putExtra("updateName", list.get(position).getReceiver());
                intent.putExtra("updateSex", list.get(position).getGender());
                intent.putExtra("updateTel", list.get(position).getContact());
                intent.putExtra("updateAddress", list.get(position).getAreaname());
                intent.putExtra("updateStr", list.get(position).getStreetaddr());
                intent.putExtra("type", "update");
                intent.setClass(context, AddressActivity.class);
                context.startActivity(intent);
            }
        });
        holder.tel.setText(list.get(position).getContact());
        holder.address.setText(list.get(position).getAreaname() + " " + list.get(position).getStreetaddr());
        holder.checkbox.setTag(list.get(position).getAddressid());
        holder.checkbox.setOnCheckedChangeListener(null);
        if ("1".equals(list.get(position).getIsdefault())) {
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
     * 设置默认地址
     *
     * @param position
     */
    public void defaultLocation(final int position) {
        DefaultAddressPostBean defaultAddressPostBean = new DefaultAddressPostBean();
        defaultAddressPostBean.setAddressid(list.get(position).getAddressid());
        defaultAddressPostBean.setStoreid(UserInfo.getInstance().getUser(context).getStoreid());
        Http.defaultAddress(context, defaultAddressPostBean, list, (position + 1), this);
    }

    public class Holder {
        public LinearLayout update;//修改
        public CheckBox checkbox;//状态
        public TextView name;//姓名
        public TextView sex;//性别
        public TextView tel;//电话
        public TextView address;//地址
    }
}
