package com.example.administrator.merchants.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.merchants.R;
import com.example.administrator.merchants.activity.CommodityPictureUpdateActivity;
import com.example.administrator.merchants.activity.MerchantAddGoodsActivity;
import com.example.administrator.merchants.dialog.MineAvatarDialog;
import com.example.administrator.merchants.http.show.ImageUriShowBean;

import java.util.List;

/**
 * 作者：韩宇 on 2017/7/20 0020 14:33
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：商品上传图片适配器
 */
public class AddIssueActivityPicAdapter extends BaseAdapter {
    private Context context;
    private List<ImageUriShowBean> list;

    public AddIssueActivityPicAdapter(Context context, List<ImageUriShowBean> list) {
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

    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        ImageUriShowBean activityBean = list.get(position);
        int type = activityBean.getType();//获取类型
        return type;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final Holder holder;
        int type = getItemViewType(position);//获取item类型
        if (convertView == null) {
            holder = new Holder();
            if (type == 0) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_merchant_goods_image, null);
                holder.imageView = (ImageView) convertView.findViewById(R.id.iv);
                holder.button = (Button) convertView.findViewById(R.id.check);
            } else {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_add_iamge, null);
                holder.add = (ImageView) convertView.findViewById(R.id.add);
            }
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        if (type == 0) {
            Glide.with(context.getApplicationContext()).load(list.get(position).getUri().toString()).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.image_loading).into(holder.imageView);
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list.size() == 6 && list.get(list.size() - 1).getType() == 0) {
                        list.remove(position);
                        ImageUriShowBean addressCotentShowBean = new ImageUriShowBean();
                        addressCotentShowBean.setType(1);
                        list.add(addressCotentShowBean);
                        notifyDataSetChanged();
                    } else {
                        list.remove(position);
                        notifyDataSetChanged();
                    }
                }
            });
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("type", "add");
                    intent.putExtra("posstion", String.valueOf(position));
                    intent.putExtra("bigimage", list.get(position).getUri().toString());
                    intent.setClass(context, CommodityPictureUpdateActivity.class);
                    ((MerchantAddGoodsActivity) context).startActivityForResult(intent, 4);
                }
            });
        } else {
            holder.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MineAvatarDialog dialog = new MineAvatarDialog(context, R.style.Dialog);
                    Window window = dialog.getWindow();
                    window.setGravity(Gravity.CENTER);
                    dialog.show();
                    dialog.getChoose((MerchantAddGoodsActivity) context);//GoodsDetailEditActivityAdd
                }
            });
        }
        return convertView;
    }

    public class Holder {
        public ImageView imageView;
        public ImageView add;
        public Button button;
    }
}
