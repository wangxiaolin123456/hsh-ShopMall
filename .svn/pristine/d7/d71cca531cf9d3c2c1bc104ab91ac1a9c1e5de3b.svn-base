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
import com.example.administrator.merchants.activity.MerchantUpdateGoodsActivity;
import com.example.administrator.merchants.common.CommonUtil;
import com.example.administrator.merchants.dialog.MineAvatarDialog;
import com.example.administrator.merchants.http.show.BitmapShowBean;
import com.example.administrator.merchants.common.toast.CustomToast;

import java.util.List;

/**
 * 作者：韩宇 on 2017/6/23 0023 14:39
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：商品图片修改
 */
public class UpdateImageAdapter extends BaseAdapter {
    private Context context;
    private List<BitmapShowBean> list;

    public UpdateImageAdapter(Context context, List<BitmapShowBean> list) {
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
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        BitmapShowBean activityBean = list.get(position);
        int type = activityBean.getType();//获取类型
        return type;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final Holder holder;
        int type = getItemViewType(position);//获取item类型
        if (convertView == null) {
            holder = new Holder();
            if (type == 1) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_merchant_goods_image, null);
                holder.imageView = (ImageView) convertView.findViewById(R.id.iv);
                holder.button = (Button) convertView.findViewById(R.id.check);
            } else if (type == 2) {
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
        if (type == 1) {
            Glide.with(context.getApplicationContext()).load(list.get(position).getPath()).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.image_loading).into(holder.imageView);
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list.size() == 2) {
                        CustomToast.getInstance(context).setMessage("商品图片不能少于1张！");
                    } else {
                        if ("".equals(CommonUtil.deleteFile)) {
                            CommonUtil.deleteFile = list.get(position).getImgid();
                        } else {
                            CommonUtil.deleteFile += "-" + list.get(position).getImgid();
                        }
                        if (list.size() == 6 && list.get(list.size() - 1).getType() == 1) {
                            list.remove(position);
                            BitmapShowBean addressCotentShowBean = new BitmapShowBean();
                            addressCotentShowBean.setType(0);
                            list.add(addressCotentShowBean);
                            notifyDataSetChanged();
                        } else {
                            list.remove(position);
                            notifyDataSetChanged();
                        }
                    }
                }
            });
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("type", "update");
                    intent.putExtra("posstion", String.valueOf(position));
                    intent.putExtra("bigimage", list.get(position).getPath());
                    intent.setClass(context, CommodityPictureUpdateActivity.class);
                    ((MerchantUpdateGoodsActivity) context).startActivityForResult(intent, 4);
                }
            });
        } else if (type == 2) {
            Glide.with(context.getApplicationContext()).load(list.get(position).getUri().toString()).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.image_loading).into(holder.imageView);
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list.size() == 2) {
                        CustomToast.getInstance(context).setMessage("商品图片不能少于1张！");
                    } else {
                        if (list.get(position).getPath() != null && !(("").equals(list.get(position).getPath()))) {
                            if ("".equals(CommonUtil.deleteFile)) {
                                CommonUtil.deleteFile = list.get(position).getImgid();
                            } else {
                                CommonUtil.deleteFile += "-" + list.get(position).getImgid();
                            }
                        }
                        if (list.size() == 6 && list.get(list.size() - 1).getType() == 1) {
                            list.get(position).getFilelow().delete();
                            list.remove(position);
                            BitmapShowBean addressCotentShowBean = new BitmapShowBean();
                            addressCotentShowBean.setType(0);
                            list.add(addressCotentShowBean);
                            notifyDataSetChanged();
                        } else {
                            list.get(position).getFilelow().delete();
                            list.remove(position);
                            notifyDataSetChanged();
                        }
                    }
                }
            });
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("type", "update");
                    intent.putExtra("posstion", String.valueOf(position));
                    intent.putExtra("bigimage", list.get(position).getUri().toString());
                    intent.setClass(context, CommodityPictureUpdateActivity.class);
                    ((MerchantUpdateGoodsActivity) context).startActivityForResult(intent, 4);
                }
            });
        } else if (type == 0) {
            holder.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MineAvatarDialog dialog = new MineAvatarDialog(context, R.style.Dialog);
                    Window window = dialog.getWindow();
                    window.setGravity(Gravity.CENTER);
                    dialog.show();
                    dialog.getChoose((MerchantUpdateGoodsActivity) context);
                }
            });
        }
        return convertView;
    }

    public class Holder {
        public ImageView imageView;
        private ImageView add;
        public Button button;
    }
}
