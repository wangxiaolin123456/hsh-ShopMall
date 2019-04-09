package com.example.administrator.merchants.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.merchants.R;
import com.example.administrator.merchants.activity.CommodityClassificationManagementActivity;
import com.example.administrator.merchants.activity.EnterpriseDemandActivity;
import com.example.administrator.merchants.activity.OriginalHomePageActivity;
import com.example.administrator.merchants.activity.OriginalSecondPageActivity;
import com.example.administrator.merchants.activity.RecruitmentManagementActivity;
import com.example.administrator.merchants.activity.ServiceActivity;
import com.example.administrator.merchants.activity.StatementAccountActivity;
import com.example.administrator.merchants.activity.StoreInformationManagementActivity;
import com.example.administrator.merchants.http.HttpUrl;
import com.example.administrator.merchants.http.show.PopupMenuShowBean;
import com.example.administrator.merchants.utils.LogUtil;

import java.util.List;

/**
 * 作者：韩宇 on 2017/7/20 0020 14:33
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：原产地首页分类适配器
 */
public class OriginalHomeMenuAdapter extends BaseAdapter {
    private Context context;
    private List<PopupMenuShowBean> list;
    private String stu;

    public OriginalHomeMenuAdapter(Context context, List<PopupMenuShowBean> list,String stu) {
        this.context = context;
        this.list = list;
        this.stu=stu;
    }

    @Override
    public int getCount() {
        return list.size();
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
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_home_page_menu, null);
            holderTest = new HolderTest();
            holderTest.ImageViewEightCircleOne = (ImageView) convertView.findViewById(R.id.grid_image);
            holderTest.TextViewEightCircleOne = (TextView) convertView.findViewById(R.id.grid_text);
            holderTest.linearLayout=convertView.findViewById(R.id.home_grid_line);
            convertView.setTag(holderTest);
        } else {
            holderTest = (HolderTest) convertView.getTag();
        }
        if (list.get(position).getType() == 0) {
            Glide.with(context.getApplicationContext()).load(HttpUrl.BaseImageUrl + list.get(position).getImgpfile()).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.image_loading).into(holderTest.ImageViewEightCircleOne);
        } else if (list.get(position).getType() == 1) {
            holderTest.ImageViewEightCircleOne.setImageResource(list.get(position).getPath());
        }
        holderTest.TextViewEightCircleOne.setText(list.get(position).getMenuname());
        holderTest.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stu.equals("home")){
                    LogUtil.i("首页功能模块"+list.get(position).getMenuid());
                    if ("message".equals(list.get(position).getMenuid())) {
                        context.startActivity(new Intent( context, StoreInformationManagementActivity.class));
                    } else if ("zhaopin".equals(list.get(position).getMenuid())) {
                        context.startActivity(new Intent(context, RecruitmentManagementActivity.class));
                    } else if ("jinrong".equals(list.get(position).getMenuid())) {
                        //贷款
//                    Intent intent1 = new Intent();
//                    intent1.setClass(getActivity(), LoanMainActivity.class);
//                    startActivity(intent1);
                        //原来金融服务
                        Intent intent1 = new Intent();
                        intent1.setClass(context, ServiceActivity.class);
                        intent1.putExtra("servetype", "20");
                        context.startActivity(intent1);
                    } else if ("yuan".equals(list.get(position).getMenuid())) {
                        context.startActivity(new Intent(context, OriginalHomePageActivity.class));
                    } else if ("bill".equals(list.get(position).getMenuid())) {
                        context.startActivity(new Intent(context, StatementAccountActivity.class));
                    } else if ("shangpin".equals(list.get(position).getMenuid())) {
                        context.startActivity(new Intent(context, CommodityClassificationManagementActivity.class));
                    } else if ("xuqiu".equals(list.get(position).getMenuid())) {
                        context.startActivity(new Intent(context, EnterpriseDemandActivity.class));
                    } else if ("fuwu".equals(list.get(position).getMenuid())) {
                        Intent intent1 = new Intent();
                        intent1.setClass(context, ServiceActivity.class);
                        intent1.putExtra("servetype", "10");
                        context.startActivity(intent1);
                    }
                }else if (stu.equals("ycd")){
                    Intent intents = new Intent(context, OriginalSecondPageActivity.class);
                    intents.putExtra("menuid", list.get(position).getMenuid());
                    intents.putExtra("menuname", list.get(position).getMenuname());
                    context.startActivity(intents);
                }
            }
        });

        return convertView;
    }

    class HolderTest {
        public ImageView ImageViewEightCircleOne;
        public TextView TextViewEightCircleOne;
        public LinearLayout linearLayout;
    }
}
