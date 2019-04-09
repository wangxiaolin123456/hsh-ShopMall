package com.example.administrator.merchants.adapter;

import android.content.Context;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.merchants.R;
import com.example.administrator.merchants.activity.EvaluateActivity;
import com.example.administrator.merchants.dialog.MineAvatarDialog;
import com.example.administrator.merchants.http.show.MerchantsOrderShowBean;
import com.example.administrator.merchants.common.watcher.MyOrderConfirmTextWatcher;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/7/20 0020 14:33
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：评价适配器
 */
public class EvaluateAdapter extends BaseAdapter {
    private Context context;
    private List<MerchantsOrderShowBean> list;
    public static int yy;

    public EvaluateAdapter(Context context, List<MerchantsOrderShowBean> list) {
        this.context = context;
        this.list = list;
        initMap();
    }

    void initMap() {
        for (int i = 0; i < list.size(); i++) {
            List<Uri> listuri = new ArrayList<>();
            List<File> fileList = new ArrayList<>();
            List<File> files = new ArrayList<>();
            list.get(i).setUris(listuri);
            list.get(i).setChoosed(true);
            list.get(i).setGrade("5");
            list.get(i).setType(0);
            list.get(i).setFileList(fileList);
            list.get(i).setFiles(files);
        }
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
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        MerchantsOrderShowBean activityBean = list.get(position);
        int type = activityBean.getType();//获取类型
        return type;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder;
        final MerchantsOrderShowBean merchantsOrderShowBean = list.get(position);
        int type = getItemViewType(position);//获取item类型
        holder = new Holder();
        if (type == 0) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_evaluate_zero, null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.item_evaluate_image);
            holder.editText = (EditText) convertView.findViewById(R.id.item_evaluate_text);
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.item_evaluate_checkBox);
            holder.ratingBar = (RatingBar) convertView.findViewById(R.id.item_evaluate_grade);
            holder.ones = (ImageView) convertView.findViewById(R.id.imageone);
        } else if (type == 1) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_evaluate_one, null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.item_evaluate_image);
            holder.editText = (EditText) convertView.findViewById(R.id.item_evaluate_text);
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.item_evaluate_checkBox);
            holder.ratingBar = (RatingBar) convertView.findViewById(R.id.item_evaluate_grade);
            holder.ones = (ImageView) convertView.findViewById(R.id.imageone);
            holder.twos = (ImageView) convertView.findViewById(R.id.imagetwo);
            holder.check1 = (Button) convertView.findViewById(R.id.check1);
        } else if (type == 2) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_evaluate_two, null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.item_evaluate_image);
            holder.editText = (EditText) convertView.findViewById(R.id.item_evaluate_text);
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.item_evaluate_checkBox);
            holder.ratingBar = (RatingBar) convertView.findViewById(R.id.item_evaluate_grade);
            holder.ones = (ImageView) convertView.findViewById(R.id.imageone);
            holder.twos = (ImageView) convertView.findViewById(R.id.imagetwo);
            holder.threes = (ImageView) convertView.findViewById(R.id.imagethree);
            holder.check1 = (Button) convertView.findViewById(R.id.check1);
            holder.check2 = (Button) convertView.findViewById(R.id.check2);
        } else if (type == 3) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_evaluate_three, null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.item_evaluate_image);
            holder.editText = (EditText) convertView.findViewById(R.id.item_evaluate_text);
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.item_evaluate_checkBox);
            holder.ratingBar = (RatingBar) convertView.findViewById(R.id.item_evaluate_grade);
            holder.ones = (ImageView) convertView.findViewById(R.id.imageone);
            holder.twos = (ImageView) convertView.findViewById(R.id.imagetwo);
            holder.threes = (ImageView) convertView.findViewById(R.id.imagethree);
            holder.check1 = (Button) convertView.findViewById(R.id.check1);
            holder.check2 = (Button) convertView.findViewById(R.id.check2);
            holder.check3 = (Button) convertView.findViewById(R.id.check3);
        }
        holder.myOrderConfirmTextWatcher = new MyOrderConfirmTextWatcher(list);
        holder.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                list.get(position).setTexts(s.toString());
            }
        });
        Glide.with(context.getApplicationContext()).load(merchantsOrderShowBean.getImgsfile()).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.image_loading).into(holder.imageView);
        holder.editText.setText(list.get(position).getTexts());
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    list.get(position).setChoosed(true);
                    list.get(position).setStaumer("1");
                } else {
                    list.get(position).setChoosed(false);
                    list.get(position).setStaumer("0");
                }
            }
        });
        holder.checkBox.setChecked(list.get(position).isChoosed());
        holder.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                list.get(position).setGrade(String.valueOf((int) holder.ratingBar.getRating()));
            }
        });
        if (type == 0) {
            holder.ones.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    yy = position;
                    MineAvatarDialog dialog = new MineAvatarDialog(context, R.style.Dialog);
                    Window window = dialog.getWindow();
                    window.setGravity(Gravity.CENTER);
                    dialog.show();
                    dialog.getChoose((EvaluateActivity) context);
                }
            });
        } else if (type == 1) {
            Glide.with(context.getApplicationContext()).load(list.get(position).getUris().get(0).toString()).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.image_loading).into(holder.ones);
            holder.twos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    yy = position;
                    MineAvatarDialog dialog = new MineAvatarDialog(context, R.style.Dialog);
                    Window window = dialog.getWindow();
                    window.setGravity(Gravity.CENTER);
                    dialog.show();
                    dialog.getChoose((EvaluateActivity) context);
                }
            });
            holder.check1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.get(position).getUris().remove(0);
                    list.get(position).getFiles().remove(0);
                    list.get(position).setType(0);
                    notifyDataSetChanged();
                }
            });
        } else if (type == 2) {
            Glide.with(context.getApplicationContext()).load(list.get(position).getUris().get(0).toString()).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.image_loading).into(holder.ones);
            Glide.with(context.getApplicationContext()).load(list.get(position).getUris().get(1).toString()).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.image_loading).into(holder.twos);
            holder.threes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    yy = position;
                    MineAvatarDialog dialog = new MineAvatarDialog(context, R.style.Dialog);
                    Window window = dialog.getWindow();
                    window.setGravity(Gravity.CENTER);
                    dialog.show();
                    dialog.getChoose((EvaluateActivity) context);
                }
            });
            holder.check1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.get(position).getUris().remove(0);
                    list.get(position).getFiles().remove(0);
                    list.get(position).setType(1);
                    notifyDataSetChanged();
                }
            });
            holder.check2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.get(position).getUris().remove(1);
                    list.get(position).getFiles().remove(1);
                    list.get(position).setType(1);
                    notifyDataSetChanged();
                }
            });
        } else if (type == 3) {
            Glide.with(context.getApplicationContext()).load(list.get(position).getUris().get(0).toString()).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.image_loading).into(holder.ones);
            Glide.with(context.getApplicationContext()).load(list.get(position).getUris().get(1).toString()).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.image_loading).into(holder.twos);
            Glide.with(context.getApplicationContext()).load(list.get(position).getUris().get(2).toString()).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.image_loading).into(holder.threes);
            holder.check1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.get(position).getUris().remove(0);
                    list.get(position).getFiles().remove(0);
                    list.get(position).setType(2);
                    notifyDataSetChanged();
                }
            });
            holder.check2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.get(position).getUris().remove(1);
                    list.get(position).getFiles().remove(1);
                    list.get(position).setType(2);
                    notifyDataSetChanged();
                }
            });
            holder.check3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.get(position).getUris().remove(2);
                    list.get(position).getFiles().remove(2);
                    list.get(position).setType(2);
                    notifyDataSetChanged();
                }
            });
        }
        return convertView;
    }

    public class Holder {
        public ImageView imageView;
        public EditText editText;
        public CheckBox checkBox;
        public RatingBar ratingBar;
        public Button check1, check2, check3;
        public MyOrderConfirmTextWatcher myOrderConfirmTextWatcher;
        public ImageView ones;
        public ImageView twos;
        public ImageView threes;
    }
}
