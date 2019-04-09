package com.example.administrator.merchants.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.dialog.AddClassifyDialog;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.post.CommodityManagementListPostBean;
import com.example.administrator.merchants.http.show.ClassManagementShowBean;

import java.util.List;

/**
 * 作者：韩宇 on 2017/7/20 0020 14:33
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：商家分类适配器
 */
public class GoodsManagerClassificationAdapter extends BaseAdapter {
    private Context context;
    private List<ClassManagementShowBean> list;
    private ListView listView;

    public GoodsManagerClassificationAdapter(Context context, List<ClassManagementShowBean> list, ListView listView) {
        this.context = context;
        this.list = list;
        this.listView = listView;
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
        HolderTest holderTest = null;
        convertView = View.inflate(context, R.layout.item_classification_manage, null);
        holderTest = new HolderTest();
        holderTest.ImageViewEightCircleOne = (ImageView) convertView.findViewById(R.id.img_down);
        holderTest.TextViewEightCircleOne = (TextView) convertView.findViewById(R.id.tv_name);
        holderTest.TextViewEightCircleTwo = (TextView) convertView.findViewById(R.id.tv_del);
        holderTest.TextViewEightCircleThree = (TextView) convertView.findViewById(R.id.tv_change);
        holderTest.LinearLayoutCircleOne = (LinearLayout) convertView.findViewById(R.id.line_down);
        holderTest.TextViewEightCircleOne.setText(list.get(position).getMenuname());
        holderTest.ImageViewEightCircleOne.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                //todo 展开 收起
                if (list.get(position).getStu() == 0) {
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setStu(0);
                    }
                    list.get(position).setStu(1);
                    notifyDataSetChanged();
                    listView.setSelection(position);
                } else {
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setStu(0);
                    }
                    list.get(position).setStu(0);
                    notifyDataSetChanged();
                    listView.setSelection(position);
                }
            }
        });
        holderTest.TextViewEightCircleTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TOdo  删除
                new android.app.AlertDialog.Builder(context)
                        .setMessage("删除分类将删除分类下的所有商品\n是否删除？")
                        .setTitle("提示")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteClassify(position);
                            }
                        })
                        .show();
            }
        });

        holderTest.TextViewEightCircleThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddClassifyDialog addClassifyDialog = new AddClassifyDialog(context, list, position, GoodsManagerClassificationAdapter.this, 1);
                addClassifyDialog.show();
            }
        });
        if (list.get(position).getStu() == 1) {
            holderTest.LinearLayoutCircleOne.setVisibility(View.VISIBLE);
        } else {
            holderTest.LinearLayoutCircleOne.setVisibility(View.GONE);
        }
        return convertView;
    }

    public void deleteClassify(int position) {
        CommodityManagementListPostBean commodityManagementListPostBean = new CommodityManagementListPostBean();
        commodityManagementListPostBean.setStoreid(UserInfo.getInstance().getUser(context).getStoreid());
        commodityManagementListPostBean.setMenuid(list.get(position).getMenuid());
        Http.deleteClassify(context, commodityManagementListPostBean);
    }

    class HolderTest {
        public ImageView ImageViewEightCircleOne;
        public TextView TextViewEightCircleOne;
        public TextView TextViewEightCircleTwo;
        public TextView TextViewEightCircleThree;
        public LinearLayout LinearLayoutCircleOne;
    }
}
