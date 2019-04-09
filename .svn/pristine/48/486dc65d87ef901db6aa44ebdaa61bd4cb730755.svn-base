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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.merchants.R;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.post.DeleteMerchandisePostBean;
import com.example.administrator.merchants.http.show.CommodityShowBean;

import java.util.List;

/**
 * 作者：韩宇 on 2017/7/20 0020 14:33
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：商家商品分类适配器
 */
public class CommodityManagementListAdapter extends BaseAdapter {
    private Context context;
    private List<CommodityShowBean> list;
    private ListView listView;

    public CommodityManagementListAdapter(Context context, List<CommodityShowBean> list, ListView listView) {
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
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final HolderTest holderTest;
        convertView = View.inflate(context, R.layout.item_commodity_management_list, null);
        holderTest = new HolderTest();
        holderTest.ImageViewEightCircleOne = (ImageView) convertView.findViewById(R.id.img_down);//下拉图
        holderTest.ImageViewEightCircleTwo = (ImageView) convertView.findViewById(R.id.iv_yuan);//左边图片
        holderTest.TextViewEightCircleOne = (TextView) convertView.findViewById(R.id.guess_title);//标题
        holderTest.TextViewEightCircleTwo = (TextView) convertView.findViewById(R.id.danjia);//单价
        holderTest.TextViewEightCircleThree = (TextView) convertView.findViewById(R.id.tv_yuan_content);//架
        holderTest.TextViewEightCircleFour = (TextView) convertView.findViewById(R.id.tv_del);//删除商品
        holderTest.TextViewEightCircleFive = (TextView) convertView.findViewById(R.id.tv_change);//上下
        holderTest.LinearLayoutCircleOne = (LinearLayout) convertView.findViewById(R.id.line_down);//下面显隐
        if (list.get(position).getShow() == 1) {
            holderTest.LinearLayoutCircleOne.setVisibility(View.VISIBLE);
        } else {
            holderTest.LinearLayoutCircleOne.setVisibility(View.GONE);
        }
        Glide.with(context.getApplicationContext()).load(list.get(position).getImgsfile()).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.image_loading).into(holderTest.ImageViewEightCircleTwo);
        holderTest.TextViewEightCircleOne.setText(list.get(position).getMername());
        holderTest.TextViewEightCircleTwo.setText(list.get(position).getSaleprice() + "");
        if (list.get(position).getContractno().equals("1")) {
            holderTest.TextViewEightCircleThree.setText("上架中");
            holderTest.TextViewEightCircleFive.setText("下架");
        } else {
            holderTest.TextViewEightCircleThree.setText("已下架");
            holderTest.TextViewEightCircleFive.setText("上架");
        }
        holderTest.ImageViewEightCircleOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(position).getShow() == 0) {
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setShow(0);
                    }
                    list.get(position).setShow(1);
                    notifyDataSetChanged();
                    listView.setSelection(position);
                } else {
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setShow(0);
                    }
                    list.get(position).setShow(0);
                    notifyDataSetChanged();
                    listView.setSelection(position);
                }
            }
        });
        holderTest.TextViewEightCircleFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TOdo  删除
                new android.app.AlertDialog.Builder(context)
                        .setMessage("是否删除商品？")
                        .setTitle("提示")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteMerchandise(position);
                            }
                        })
                        .show();
            }
        });
        holderTest.TextViewEightCircleFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 上下架
                new android.app.AlertDialog.Builder(context)
                        .setMessage("是否更改商品销售状态？")
                        .setTitle("提示")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                upOrDown(position);
                            }
                        })
                        .show();
            }
        });
        return convertView;
    }

    public class HolderTest {
        public ImageView ImageViewEightCircleOne;
        public ImageView ImageViewEightCircleTwo;
        public TextView TextViewEightCircleOne;
        public TextView TextViewEightCircleTwo;
        public TextView TextViewEightCircleThree;
        public TextView TextViewEightCircleFour;
        public TextView TextViewEightCircleFive;
        public LinearLayout LinearLayoutCircleOne;
    }

    /**
     * 删除商品
     *
     * @param position
     */
    public void deleteMerchandise(final int position) {
        DeleteMerchandisePostBean deleteMerchandisePostBean = new DeleteMerchandisePostBean();
        deleteMerchandisePostBean.setStoreid(UserInfo.getInstance().getUser(context).getStoreid());
        deleteMerchandisePostBean.setMerid(list.get(position).getMerid());
        Http.deleteMerchandise(context, deleteMerchandisePostBean, list.size() + "");
    }

    /**
     * 上下架
     *
     * @param position
     */
    public void upOrDown(final int position) {
        DeleteMerchandisePostBean deleteMerchandisePostBean = new DeleteMerchandisePostBean();
        deleteMerchandisePostBean.setStoreid(UserInfo.getInstance().getUser(context).getStoreid());
        deleteMerchandisePostBean.setMerid(list.get(position).getMerid());
        deleteMerchandisePostBean.setIsused(list.get(position).getContractno());
        Http.upOrDown(context, deleteMerchandisePostBean, list.size() + "");
    }
}
