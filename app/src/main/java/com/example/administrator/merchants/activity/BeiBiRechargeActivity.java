package com.example.administrator.merchants.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseActivity;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.post.GetTnPostBean;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：韩宇 on 2017/6/22 0022 16:36
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：贝币充值
 */
public class BeiBiRechargeActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.beibi_cz_one_tv)
    TextView beibiCzOneTv;
    @BindView(R.id.beibi_cz_ones_tv)
    TextView beibiCzOnesTv;
    @BindView(R.id.beibi_cz_three_tv)
    TextView beibiCzThreeTv;
    @BindView(R.id.beibi_cz_threes_tv)
    TextView beibiCzThreesTv;
    @BindView(R.id.beibi_cz_five_tv)
    TextView beibiCzFiveTv;
    @BindView(R.id.beibi_cz_fives_tv)
    TextView beibiCzFivesTv;
    @BindView(R.id.beibi_cz_two_tv)
    TextView beibiCzTwoTv;
    @BindView(R.id.beibi_cz_twos_tv)
    TextView beibiCzTwosTv;
    @BindView(R.id.beibi_cz_four_tv)
    TextView beibiCzFourTv;
    @BindView(R.id.beibi_cz_fours_tv)
    TextView beibiCzFoursTv;
    @BindView(R.id.beibi_cz_six_tv)
    TextView beibiCzSixTv;
    @BindView(R.id.beibi_cz_sixs_tv)
    TextView beibiCzSixsTv;
    private LinearLayout linearLayoutOne;//100
    private LinearLayout linearLayoutTwo;//200
    private LinearLayout linearLayoutThree;//300
    private LinearLayout linearLayoutFour;//400
    private LinearLayout linearLayoutFive;//500
    private LinearLayout linearLayoutSix;//600
    private LinearLayout linearLayoutweixin;//微信
    private LinearLayout linearLayoutzhifubao;//支付宝
    private LinearLayout linearLayoutyinlian;//银联
    private EditText know;//任意值输入
    private ImageView weixin;//微信小圆点
    private ImageView zhifubao;//支付宝小圆点
    private ImageView yinlian;//银联小圆点
    private Button button;//充值按钮
    private String allMoney;//充值所付金额
    private final String mMode = "00";//"01"银联测试环境
    private String type;//支付类型
    private boolean one;//100是否选中
    private boolean two;//200是否选中
    private boolean three;//300是否选中
    private boolean four;//400是否选中
    private boolean five;//500是否选中
    private boolean six;//600是否选中
    private boolean weixins;//微信是否选中
    private boolean zhifubaos;//支付宝是否选中
    private boolean yinlians;//银联是否选中

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bei_recharge);
        ButterKnife.bind(this);
        setTitles("贝币充值");
        linearLayoutOne = (LinearLayout) findViewById(R.id.activity_bei_recharge_one);
        linearLayoutTwo = (LinearLayout) findViewById(R.id.activity_bei_recharge_two);
        linearLayoutThree = (LinearLayout) findViewById(R.id.activity_bei_recharge_three);
        linearLayoutFour = (LinearLayout) findViewById(R.id.activity_bei_recharge_four);
        linearLayoutFive = (LinearLayout) findViewById(R.id.activity_bei_recharge_five);
        linearLayoutSix = (LinearLayout) findViewById(R.id.activity_bei_recharge_six);
        linearLayoutweixin = (LinearLayout) findViewById(R.id.activity_bei_recharge_weixins);
        linearLayoutzhifubao = (LinearLayout) findViewById(R.id.activity_bei_recharge_zhifubaos);
        linearLayoutyinlian = (LinearLayout) findViewById(R.id.activity_bei_recharge_yinlians);
        know = (EditText) findViewById(R.id.activity_bei_recharge_know);
        weixin = (ImageView) findViewById(R.id.activity_bei_recharge_weixin);
        zhifubao = (ImageView) findViewById(R.id.activity_bei_recharge_zhifubao);
        yinlian = (ImageView) findViewById(R.id.activity_bei_recharge_yinlian);
        button = (Button) findViewById(R.id.activity_bei_recharge_commit);
        linearLayoutOne.setOnClickListener(this);
        linearLayoutTwo.setOnClickListener(this);
        linearLayoutThree.setOnClickListener(this);
        linearLayoutFour.setOnClickListener(this);
        linearLayoutFive.setOnClickListener(this);
        linearLayoutSix.setOnClickListener(this);
        linearLayoutweixin.setOnClickListener(this);
        linearLayoutzhifubao.setOnClickListener(this);
        linearLayoutyinlian.setOnClickListener(this);
        button.setOnClickListener(this);
        one = true;
        two = false;
        three = false;
        four = false;
        five = false;
        six = false;
        linearLayoutOne.setBackgroundResource(R.color.themeColor);
        linearLayoutTwo.setBackgroundResource(R.color.color_e);
        linearLayoutThree.setBackgroundResource(R.color.color_e);
        linearLayoutFour.setBackgroundResource(R.color.color_e);
        linearLayoutFive.setBackgroundResource(R.color.color_e);
        linearLayoutSix.setBackgroundResource(R.color.color_e);
        stu(1);
        know.setText("");
        //默认支付方式银联支付
        weixins = false;
        zhifubaos = false;
        yinlians = true;
        weixin.setImageResource(R.drawable.item_checkbox_car_false);
        zhifubao.setImageResource(R.drawable.item_checkbox_car_false);
        yinlian.setImageResource(R.drawable.choice);
        know.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //任意值输入框的点击事件
                one = false;
                two = false;
                three = false;
                four = false;
                five = false;
                six = false;
                linearLayoutOne.setBackgroundResource(R.color.color_e);
                linearLayoutTwo.setBackgroundResource(R.color.color_e);
                linearLayoutThree.setBackgroundResource(R.color.color_e);
                linearLayoutFour.setBackgroundResource(R.color.color_e);
                linearLayoutFive.setBackgroundResource(R.color.color_e);
                linearLayoutSix.setBackgroundResource(R.color.color_e);
                stu(0);
            }
        });
    }

    /**
     * 处理银联手机支付控件返回的支付结果
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        String msg = "";
        String str = data.getExtras().getString("pay_result");
        if (str.equalsIgnoreCase("success")) {
            if (data.hasExtra("result_data")) {
                String result = data.getExtras().getString("result_data");
                try {
                    JSONObject resultJson = new JSONObject(result);
                    String sign = resultJson.getString("sign");
                    String dataOrg = resultJson.getString("data");
                    boolean ret = verify(dataOrg, sign, mMode);
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
            }
        });
        builder.create().show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        MutualApplication.getRequestQueue().cancelAll("getTN22");
    }

    /**
     * 按钮状态
     */
    @SuppressLint("ResourceAsColor")
    private void stu(int a) {
        beibiCzOneTv.setTextColor(Color.BLACK);
        beibiCzOnesTv.setTextColor(getResources().getColor(R.color.text_black));
        beibiCzTwoTv.setTextColor(Color.BLACK);
        beibiCzTwosTv.setTextColor(getResources().getColor(R.color.text_black));
        beibiCzThreeTv.setTextColor(Color.BLACK);
        beibiCzThreesTv.setTextColor(getResources().getColor(R.color.text_black));
        beibiCzFourTv.setTextColor(Color.BLACK);
        beibiCzFoursTv.setTextColor(getResources().getColor(R.color.text_black));
        beibiCzFiveTv.setTextColor(Color.BLACK);
        beibiCzFivesTv.setTextColor(getResources().getColor(R.color.text_black));
        beibiCzSixTv.setTextColor(Color.BLACK);
        beibiCzSixsTv.setTextColor(getResources().getColor(R.color.text_black));

        switch (a) {
            case 1:
                beibiCzOneTv.setTextColor(Color.WHITE);
                beibiCzOnesTv.setTextColor(Color.WHITE);
                break;
            case 2:
                beibiCzTwoTv.setTextColor(Color.WHITE);
                beibiCzTwosTv.setTextColor(Color.WHITE);
                break;
            case 3:
                beibiCzThreeTv.setTextColor(Color.WHITE);
                beibiCzThreesTv.setTextColor(Color.WHITE);
                break;
            case 4:
                beibiCzFourTv.setTextColor(Color.WHITE);
                beibiCzFoursTv.setTextColor(Color.WHITE);
                break;
            case 5:
                beibiCzFiveTv.setTextColor(Color.WHITE);
                beibiCzFivesTv.setTextColor(Color.WHITE);
                break;
            case 6:
                beibiCzSixTv.setTextColor(Color.WHITE);
                beibiCzSixsTv.setTextColor(Color.WHITE);
                break;
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_bei_recharge_one:
                //100选中
                one = true;
                two = false;
                three = false;
                four = false;
                five = false;
                six = false;
                linearLayoutOne.setBackgroundResource(R.color.themeColor);
                linearLayoutTwo.setBackgroundResource(R.color.color_e);
                linearLayoutThree.setBackgroundResource(R.color.color_e);
                linearLayoutFour.setBackgroundResource(R.color.color_e);
                linearLayoutFive.setBackgroundResource(R.color.color_e);
                linearLayoutSix.setBackgroundResource(R.color.color_e);
                stu(1);
                know.setText("");
                break;
            case R.id.activity_bei_recharge_two:
                //200选中
                one = false;
                two = true;
                three = false;
                four = false;
                five = false;
                six = false;
                linearLayoutOne.setBackgroundResource(R.color.color_e);
                linearLayoutTwo.setBackgroundResource(R.color.themeColor);
                linearLayoutThree.setBackgroundResource(R.color.color_e);
                linearLayoutFour.setBackgroundResource(R.color.color_e);
                linearLayoutFive.setBackgroundResource(R.color.color_e);
                linearLayoutSix.setBackgroundResource(R.color.color_e);
                stu(2);
                know.setText("");
                break;
            case R.id.activity_bei_recharge_three:
                //300选中
                one = false;
                two = false;
                three = true;
                four = false;
                five = false;
                six = false;
                linearLayoutOne.setBackgroundResource(R.color.color_e);
                linearLayoutTwo.setBackgroundResource(R.color.color_e);
                linearLayoutThree.setBackgroundResource(R.color.themeColor);
                linearLayoutFour.setBackgroundResource(R.color.color_e);
                linearLayoutFive.setBackgroundResource(R.color.color_e);
                linearLayoutSix.setBackgroundResource(R.color.color_e);
                stu(3);
                know.setText("");
                break;
            case R.id.activity_bei_recharge_four:
                //400选中
                one = false;
                two = false;
                three = false;
                four = true;
                five = false;
                six = false;
                linearLayoutOne.setBackgroundResource(R.color.color_e);
                linearLayoutTwo.setBackgroundResource(R.color.color_e);
                linearLayoutThree.setBackgroundResource(R.color.color_e);
                linearLayoutFour.setBackgroundResource(R.color.themeColor);
                linearLayoutFive.setBackgroundResource(R.color.color_e);
                linearLayoutSix.setBackgroundResource(R.color.color_e);
                stu(4);
                know.setText("");
                break;
            case R.id.activity_bei_recharge_five:
                //500选中
                one = false;
                two = false;
                three = false;
                four = false;
                five = true;
                six = false;
                linearLayoutOne.setBackgroundResource(R.color.color_e);
                linearLayoutTwo.setBackgroundResource(R.color.color_e);
                linearLayoutThree.setBackgroundResource(R.color.color_e);
                linearLayoutFour.setBackgroundResource(R.color.color_e);
                linearLayoutFive.setBackgroundResource(R.color.themeColor);
                linearLayoutSix.setBackgroundResource(R.color.color_e);
                stu(5);
                know.setText("");
                break;
            case R.id.activity_bei_recharge_six:
                //600选中
                one = false;
                two = false;
                three = false;
                four = false;
                five = false;
                six = true;
                linearLayoutOne.setBackgroundResource(R.color.color_e);
                linearLayoutTwo.setBackgroundResource(R.color.color_e);
                linearLayoutThree.setBackgroundResource(R.color.color_e);
                linearLayoutFour.setBackgroundResource(R.color.color_e);
                linearLayoutFive.setBackgroundResource(R.color.color_e);
                linearLayoutSix.setBackgroundResource(R.color.themeColor);
                stu(6);
                know.setText("");
                break;
            case R.id.activity_bei_recharge_weixins:
                //微信选中
                weixins = true;
                zhifubaos = false;
                yinlians = false;
                weixin.setImageResource(R.drawable.choice);
                zhifubao.setImageResource(R.drawable.item_checkbox_car_false);
                yinlian.setImageResource(R.drawable.item_checkbox_car_false);
                break;
            case R.id.activity_bei_recharge_zhifubaos:
                //支付宝选中
                weixins = false;
                zhifubaos = true;
                yinlians = false;
                weixin.setImageResource(R.drawable.item_checkbox_car_false);
                zhifubao.setImageResource(R.drawable.choice);
                yinlian.setImageResource(R.drawable.item_checkbox_car_false);
                break;
            case R.id.activity_bei_recharge_yinlians:
                //银联选中
                weixins = false;
                zhifubaos = false;
                yinlians = true;
                weixin.setImageResource(R.drawable.item_checkbox_car_false);
                zhifubao.setImageResource(R.drawable.item_checkbox_car_false);
                yinlian.setImageResource(R.drawable.choice);
                break;
            case R.id.activity_bei_recharge_commit:
                //提交监听
                button.setClickable(false);
                button.setBackgroundColor(Color.parseColor("#5f5c5c"));
                button.setText("5秒后可再次点击！");
                if (weixins) {
                    type = "wechat";
                    if (one) {
                        allMoney = "100";
                    } else if (two) {
                        allMoney = "200";
                    } else if (three) {
                        allMoney = "300";
                    } else if (four) {
                        allMoney = "400";
                    } else if (five) {
                        allMoney = "500";
                    } else if (six) {
                        allMoney = "600";
                    } else {
                        allMoney = know.getText().toString();
                    }
                    getTN("wechat");//toDo  微信支付
                } else if (zhifubaos) {//toDo  支付宝支付
                    type = "alipay";
                    if (one) {
                        allMoney = "100";
                    } else if (two) {
                        allMoney = "200";
                    } else if (three) {
                        allMoney = "300";
                    } else if (four) {
                        allMoney = "400";
                    } else if (five) {
                        allMoney = "500";
                    } else if (six) {
                        allMoney = "600";
                    } else {
                        allMoney = know.getText().toString();
                    }
                    getTN(type);
                } else if (yinlians) {
                    type = "unionpay";
                    if (one) {
                        allMoney = "100";
                    } else if (two) {
                        allMoney = "200";
                    } else if (three) {
                        allMoney = "300";
                    } else if (four) {
                        allMoney = "400";
                    } else if (five) {
                        allMoney = "500";
                    } else if (six) {
                        allMoney = "600";
                    } else {
                        allMoney = know.getText().toString();
                    }
                    getTN("unionpay");//toDo  银联支付
                }
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        button.setBackgroundColor(Color.parseColor("#ff5252"));
                        button.setText("确认支付");
                        button.setClickable(true);
                    }
                }, 5000);
                break;

        }
    }

    /**
     * 获取TN号
     *
     * @param type
     */
    public void getTN(String type) {
        GetTnPostBean getTnPostBean = new GetTnPostBean();
        getTnPostBean.setStoreid(UserInfo.getInstance().getUser(this).getStoreid());
        getTnPostBean.setStorename(UserInfo.getInstance().getUser(this).getStorename());
        getTnPostBean.setRechamt(allMoney);
        getTnPostBean.setPaytype(type);
        if ("unionpay".equals(type)) {
            Http.getTN(BeiBiRechargeActivity.this, getTnPostBean, type, mMode);
        } else {
            Http.getTN(BeiBiRechargeActivity.this, getTnPostBean, type, allMoney);
        }
    }

    /**
     * // 此处的verify，商户需送去商户后台做验签
     *
     * @param msg
     * @param sign64
     * @param mode
     * @return
     */
    private boolean verify(String msg, String sign64, String mode) {
        return true;
    }
}
