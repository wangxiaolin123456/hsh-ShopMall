package com.example.administrator.merchants.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseActivity;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.post.PayOrderPostBean;
import com.example.administrator.merchants.http.post.StoreIdPostBean;
import com.example.administrator.merchants.utils.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者：韩宇 on 2017/7/20 0020 14:33
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：支付订单
 */
public class PayOrderActivity extends BaseActivity implements View.OnClickListener {
    private Button confirmButton;
    private ImageView imageViewWeiXim;
    private ImageView imageViewZhiFuBao;
    private ImageView imageViewYinLian;
    private ImageView imageViewBeiBi;
    //    private TextView tv_order_num;
    private TextView tv_order_money;
    private RelativeLayout beibi_duihuan;
    private RelativeLayout weixin_pay;
    private RelativeLayout ali_pay;
    private RelativeLayout yinlian_pay;
    private String type;
    private String merName, merDescribe, money, orderNo;
    private String isSilver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        setTitles("支付订单");
        LogUtil.i("支付isSilver" + getIntent().getStringExtra("isSilver"));
        isSilver = getIntent().getStringExtra("isSilver");
        merName = getIntent().getStringExtra("mername");
        merDescribe = getIntent().getStringExtra("merdescr");
        money = getIntent().getStringExtra("money");
        orderNo = getIntent().getStringExtra("ordno");
        confirmButton = (Button) findViewById(R.id.btn_commit);//确认支付
        imageViewWeiXim = (ImageView) findViewById(R.id.iv_weixin_pay);//微信支付-红色的小圆点
        imageViewZhiFuBao = (ImageView) findViewById(R.id.iv_ali_pay);//支付宝支付—红色的小圆点
        imageViewYinLian = (ImageView) findViewById(R.id.iv_yinlian_pay);//银联-红色的小圆点
        imageViewBeiBi = (ImageView) findViewById(R.id.iv_beibi_duihuan);//贝币支付-红色的小圆点
//        tv_order_num = (TextView) findViewById(R.id.tv_order_num);
        tv_order_money = (TextView) findViewById(R.id.tv_order_money);
        beibi_duihuan = (RelativeLayout) findViewById(R.id.beibi_duihuan);
        weixin_pay = (RelativeLayout) findViewById(R.id.weixin_pay);
        ali_pay = (RelativeLayout) findViewById(R.id.ali_pay);
        yinlian_pay = (RelativeLayout) findViewById(R.id.yinlian_pay);
//        tv_order_num.setText(orderNo);
        tv_order_money.setText(money);
        beibi_duihuan.setOnClickListener(this);
        weixin_pay.setOnClickListener(this);
        ali_pay.setOnClickListener(this);
        yinlian_pay.setOnClickListener(this);
        confirmButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.beibi_duihuan:
                type = "beibi";
                imageViewBeiBi.setImageResource(R.drawable.choice);
                imageViewWeiXim.setImageResource(R.drawable.item_checkbox_car_false);
                imageViewZhiFuBao.setImageResource(R.drawable.item_checkbox_car_false);
                imageViewYinLian.setImageResource(R.drawable.item_checkbox_car_false);
                break;
            case R.id.weixin_pay:
                type = "wechat";
                imageViewWeiXim.setImageResource(R.drawable.choice);
                imageViewZhiFuBao.setImageResource(R.drawable.item_checkbox_car_false);
                imageViewYinLian.setImageResource(R.drawable.item_checkbox_car_false);
                imageViewBeiBi.setImageResource(R.drawable.item_checkbox_car_false);
                break;
            case R.id.ali_pay:
                type = "alipay";
                imageViewWeiXim.setImageResource(R.drawable.item_checkbox_car_false);
                imageViewZhiFuBao.setImageResource(R.drawable.choice);
                imageViewYinLian.setImageResource(R.drawable.item_checkbox_car_false);
                imageViewBeiBi.setImageResource(R.drawable.item_checkbox_car_false);
                break;
            case R.id.yinlian_pay:
                type = "unionpay";
                imageViewWeiXim.setImageResource(R.drawable.item_checkbox_car_false);
                imageViewZhiFuBao.setImageResource(R.drawable.item_checkbox_car_false);
                imageViewYinLian.setImageResource(R.drawable.choice);
                imageViewBeiBi.setImageResource(R.drawable.item_checkbox_car_false);
                break;
            case R.id.btn_commit:
                confirmButton.setClickable(false);
                confirmButton.setBackgroundColor(Color.parseColor("#5f5c5c"));
                confirmButton.setText("5秒后可再次点击！");
                if ("beibi".equals(type)) {
                    tellBeiBi();//判断贝币余额
                } else {
                    getTN(type);
                }
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        confirmButton.setBackgroundColor(Color.parseColor("#ff5252"));
                        confirmButton.setText("确认支付");
                        confirmButton.setClickable(true);
                    }
                }, 5000);
                break;
        }
    }

    /**
     * 查询贝币余额
     */
    public void tellBeiBi() { //贝币余额
        StoreIdPostBean storeIdPostBean = new StoreIdPostBean();
        storeIdPostBean.setStoreid(UserInfo.getInstance().getUser(this).getStoreid());
        Http.payBeiBi(PayOrderActivity.this, storeIdPostBean, money, orderNo,isSilver);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result_data) {
        if (result_data == null) {
            return;
        }
        String msg = "";
        String str = result_data.getExtras().getString("pay_result");//toDO  所以str里存的是 pay_result=success 、false
        if (str.equalsIgnoreCase("success")) {
            if (result_data.hasExtra("result_data")) {
                String result = result_data.getExtras().getString("result_data");  //toDo sign data 的字符串
                try {
                    JSONObject resultJson = new JSONObject(result);   //用gson将字符串转成json格式   sign data 的json
                    String sign = resultJson.getString("sign");
                    String dataOrg = resultJson.getString("data");
                    boolean ret = verify(dataOrg, sign, "00");// 验签证书同后台验签证书    此处的verify，商户需送去商户后台做验签
                    if (ret) {
                        msg = "支付成功！";
                    } else {
                        msg = "支付失败！";
                    }
                } catch (JSONException e) {
                }
            } else {
                msg = "支付成功！";
            }
        } else if (str.equalsIgnoreCase("fail")) {
            msg = "支付失败！";
        } else if (str.equalsIgnoreCase("cancel")) {
            msg = "用户取消了支付";
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("支付结果通知");
        builder.setMessage(msg);
        builder.setInverseBackgroundForced(true);
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });
        builder.create().show();
    }

    @Override
    protected void onStop() {
        MutualApplication.getRequestQueue().cancelAll("payOrder70");
        MutualApplication.getRequestQueue().cancelAll("payBeiBi71");
        MutualApplication.getRequestQueue().cancelAll("confirmPayOrderPassword72");
        MutualApplication.getRequestQueue().cancelAll("payBeiBiFinish73");
        super.onStop();
    }

    /**
     * 订单支付
     *
     * @param type
     */
    public void getTN(String type) {
        PayOrderPostBean payOrderPostBean = new PayOrderPostBean();
        payOrderPostBean.setStoreid(UserInfo.getInstance().getUser(this).getStoreid());
        payOrderPostBean.setStorename(UserInfo.getInstance().getUser(this).getStorename());
        payOrderPostBean.setOrdno(orderNo);
        payOrderPostBean.setPaytype(type);
        payOrderPostBean.setPayamt(money);
        payOrderPostBean.setIsSilver(isSilver);
        Http.payOrder(PayOrderActivity.this, payOrderPostBean, type, merName, merDescribe, money);
    }

    private boolean verify(String msg, String sign64, String mode) {
        // 此处的verify，商户需送去商户后台做验签
        return true;

    }
}