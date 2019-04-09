package com.example.administrator.merchants.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.diy.widget.CircularImage;
import com.example.administrator.merchants.R;
import com.example.administrator.merchants.base.BaseActivity;

/**
 * 作者：韩宇 on 2017/6/23 0023 10:23
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：贝币记录详情
 */
public class BeiBiRecordDetailsActivity extends BaseActivity {
    private TextView title;//互实会  用户昵称
    private LinearLayout addLine;
    private TextView addText;
    private TextView reduceText;
    private LinearLayout reduceLine;
    private TextView phoneText;
    private TextView phone;
    private TextView explain;//说明
    private TextView time1;//创建时间
    private TextView time2;
    private String getColor;
    private String type;
    private CircularImage circularImage;
    private String time1text, time2text, titletext, content, image, money, inoutobjdescr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bei_bi_record_details);
        setTitles("贝币往来明细");
        getColor = getIntent().getStringExtra("color");
        circularImage = (CircularImage) findViewById(R.id.activity_bei_bi_record_details_image);
        title = (TextView) findViewById(R.id.bei_bi_record_details_title);
        addText = (TextView) findViewById(R.id.item_bei_record_add_number);
        reduceText = (TextView) findViewById(R.id.item_bei_record_jian_number);
        addLine = (LinearLayout) findViewById(R.id.activity_bei_bi_record_details_add_line);
        reduceLine = (LinearLayout) findViewById(R.id.activity_bei_bi_record_details_jian_line);
        phoneText = (TextView) findViewById(R.id.activity_bei_bi_record_details_phone_text);
        phone = (TextView) findViewById(R.id.activity_bei_bi_record_details_phone);
        explain = (TextView) findViewById(R.id.activity_bei_bi_record_details_text);//说明
        time1 = (TextView) findViewById(R.id.activity_bei_bi_record_details_time1);//创建时间
        time2 = (TextView) findViewById(R.id.activity_bei_bi_record_details_time2);//创建时间
        time1text = getIntent().getStringExtra("time1");
        time2text = getIntent().getStringExtra("time2");
        titletext = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        image = getIntent().getStringExtra("image");
        money = getIntent().getStringExtra("money");
        inoutobjdescr = getIntent().getStringExtra("inoutobjdescr");
        time1.setText(time1text);
        time2.setText(time2text);
        title.setText(titletext);
        explain.setText(content);
        type = getIntent().getStringExtra("inouttype");//类型
//        if (image.equals("")) {
            circularImage.setBackgroundResource(R.drawable.default_avatar);
//        } else {
//            Glide.with(getApplicationContext()).load(image).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.image_loading).into(circularImage);
//        }
        if (getColor.equals("1")) {
            //加  绿色
            addLine.setVisibility(View.VISIBLE);
            reduceLine.setVisibility(View.GONE);
            addText.setText(money);
        } else if (getColor.equals("0")) {
            //减 红色
            addLine.setVisibility(View.GONE);
            reduceLine.setVisibility(View.VISIBLE);
            reduceText.setText(money);
        }
        if (type.equals("out_order")) {//购物消费
            explain.setText("购物消费");
            phoneText.setText("订单号");
        } else if (type.equals("in_ret")) {//购物返利
            phoneText.setText("订单号");
        } else if (type.equals("out_ret")) {
            phoneText.setText("订单号");
        } else if (type.equals("out_gift")) {//贝币转赠
            phoneText.setText("手机号");
        } else if (type.equals("in_gift")) {//贝币获赠
            phoneText.setText("手机号");
        } else if (type.equals("in_recharge")) {//贝币充值
            phoneText.setText("充值方式");
        } else if (type.equals("out_cash")) {//提现
            phoneText.setText("银行卡号");
        } else if (type.equals("in_order")) {//退款
            phoneText.setText("订单号");
        }
        phone.setText(inoutobjdescr);
    }
}
