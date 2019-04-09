package com.example.administrator.merchants.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.merchants.R;
import com.example.administrator.merchants.http.show.ShoppingCarShowBean;
import com.example.administrator.merchants.common.watcher.MyTextWatcher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者：韩宇 on 2017/7/20 0020 14:33
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：确认订单适配器
 */
public class ConfirmOrderAdapter extends BaseAdapter {
    private Context context;
    private List<ShoppingCarShowBean> list;
    private HolderTest holderTest = null;
    private Map<Integer, String> checkStatusMap = null;
    private int mTouchItemPosition = -1;//toDO 定义成员变量mTouchItemPosition,用来记录手指触摸的EditText的位置

    public ConfirmOrderAdapter(Context context, List<ShoppingCarShowBean> list) {
        this.context = context;
        this.list = list;
        checkStatusMap = new HashMap<>();
        initMap("");
    }

    void initMap(String e) {
        for (int i = 0; i < list.size(); i++) {
            checkStatusMap.put(i, e);
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

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
//        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_confirm_order, null);
            holderTest = new HolderTest();
            holderTest.ImageViewEightCircleOne = (ImageView) convertView.findViewById(R.id.iv_yuan_confirm);
            holderTest.TextViewEightCircleOne = (TextView) convertView.findViewById(R.id.confirm_title);
            holderTest.EditText = (EditText) convertView.findViewById(R.id.edi_beizhu);
            holderTest.TextViewEightCircleThree = (TextView) convertView.findViewById(R.id.number_confirm);
            holderTest.TextViewEightCircleFour = (TextView) convertView.findViewById(R.id.confirm_danjia);
            holderTest.ModeText = (TextView) convertView.findViewById(R.id.item_confirm_order_mode_content);
            holderTest.EditText.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    //注意，此处必须使用getTag的方式，不能将position定义为final，写成mTouchItemPosition = position
                    mTouchItemPosition = (Integer) v.getTag();
                    return false;
                }
            });
            holderTest.myTextWatcher = new MyTextWatcher(list);
            holderTest.EditText.addTextChangedListener(holderTest.myTextWatcher);
            holderTest.updatePosition(position);
            convertView.setTag(holderTest);
//        } else {
//            holderTest = (HolderTest) convertView.getTag();
//            holderTest.updatePosition(position);
//        }
        Glide.with(context.getApplicationContext()).load(list.get(position).getImgsfile()).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.image_loading).into(holderTest.ImageViewEightCircleOne);
        holderTest.TextViewEightCircleOne.setText(list.get(position).getMername());//商品名
        holderTest.TextViewEightCircleThree.setText(list.get(position).getMerqty() + "");
        holderTest.TextViewEightCircleFour.setText(String.valueOf(list.get(position).getSaleprice()));
        holderTest.EditText.setText(list.get(position).getRemark());
        holderTest.EditText.setTag(position);
        holderTest.ModeText.setText(list.get(position).getModeldescr());
        if (mTouchItemPosition == position) {
            holderTest.EditText.requestFocus();
        } else {
            holderTest.EditText.clearFocus();
        }
        return convertView;
    }

    public class HolderTest {
        public ImageView ImageViewEightCircleOne;
        public TextView TextViewEightCircleOne;
        public EditText EditText;
        public TextView TextViewEightCircleThree;
        public TextView TextViewEightCircleFour;
        public TextView ModeText;
        public MyTextWatcher myTextWatcher;

        public void updatePosition(int position) {
            myTextWatcher.updatePosition(position);
        }
    }
}
