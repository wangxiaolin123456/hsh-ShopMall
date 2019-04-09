package com.example.administrator.merchants.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.administrator.merchants.R;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseActivity;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.HttpUrl;
import com.example.administrator.merchants.http.post.StoreIdPostBean;
import com.example.administrator.merchants.http.post.PhoneTypePostBean;
import com.example.administrator.merchants.common.DateUtils;
import com.example.administrator.merchants.common.toast.CustomToast;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.utils.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者：韩宇 on 2017/6/22 0022 10:09
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：贝币赠与
 */
public class BeiBiGiveActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    private EditText phone;
    private EditText beibi;
    private Button next;
    private RadioGroup radioGroup;
    private RadioGroup radioGroupBeiBi;
    private int check = 0;
    private RadioButton rb1;
    private RadioButton rb3;
    private RadioButton jBeiBiRadio;
    private RadioButton yBeiBiRadio;
    private int phoneRight = 0;
    private String type;//好友类型
    private TextView yueTv;
    private double jbeibiYue = 0;
    private double ybeibiYue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_bei_bi);
        setTitles("贝币赠予");
        yueTv = (TextView) findViewById(R.id.zy_beibei_yue_tv);
        phone = (EditText) findViewById(R.id.phone);
        beibi = (EditText) findViewById(R.id.beibi);
        next = (Button) findViewById(R.id.next);
        radioGroupBeiBi = (RadioGroup) findViewById(R.id.radioGroup_two);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(this);
        radioGroupBeiBi.setOnCheckedChangeListener(this);
        rb1 = (RadioButton) findViewById(R.id.rb1);
        rb3 = (RadioButton) findViewById(R.id.rb3);
        jBeiBiRadio = (RadioButton) findViewById(R.id.zy_j_beibei_radiobtn);
        yBeiBiRadio = (RadioButton) findViewById(R.id.zy_y_beibei_radiobtn);
        //贝币数量的监听
        beibi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if ("".equals(s + "") || ".".equals((s + "").substring((s + "").length() - 1))
                        || Double.parseDouble(s + "") == 0) {
                    //灰色
                    radioGroupBeiBi.clearCheck();
                    radioGroupBeiBi.setVisibility(View.GONE);
                    yueTv.setVisibility(View.GONE);
                    next.setBackgroundResource(R.drawable.dialog_gray_white);
                    next.setEnabled(false);
                } else {
                    //红色
                    radioGroupBeiBi.check(R.id.zy_j_beibei_radiobtn);
                    yueTv.setVisibility(View.VISIBLE);
                    getPersonInfo("1");
                    radioGroupBeiBi.setVisibility(View.VISIBLE);
                    next.setBackgroundResource(R.drawable.dialog_theme);
                    next.setEnabled(true);
                }
            }
        });


//        DateUtils.giveBeiBiNumber(beibi, next);//赠与贝与按钮之间规则
        DateUtils.setPricePoint(beibi);
        giveBeiBiPhone();
        next();

    }

    /**
     * 贝币余额
     */
    public void tellBeiBi(String beibiType) {
        StoreIdPostBean storeIdPostBean = new StoreIdPostBean();
        storeIdPostBean.setStoreid(UserInfo.getInstance().getUser(BeiBiGiveActivity.this).getStoreid());
        Http.getGiveBalance(BeiBiGiveActivity.this, storeIdPostBean, beibi, phone, type,beibiType);
    }

    /**
     * 判断手机号的类型有几种
     */
    private void beiBiPhoneType() {
        PhoneTypePostBean phoneTypePostBean = new PhoneTypePostBean();
        phoneTypePostBean.setGiftphone(phone.getText().toString());
        Http.phoneType(BeiBiGiveActivity.this, phoneTypePostBean, radioGroup, rb1, rb3, phone);
    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb1:
                check = 1;
                type = "store";
                break;
            case R.id.rb3:
                check = 1;
                type = "mem";
                break;
            case R.id.zy_j_beibei_radiobtn:
                yueTv.setVisibility(View.VISIBLE);
                getPersonInfo("1");
                break;
            case R.id.zy_y_beibei_radiobtn:
                yueTv.setVisibility(View.VISIBLE);
                getPersonInfo("2");
                break;
            default:
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        MutualApplication.getRequestQueue().cancelAll("phoneType18");
        MutualApplication.getRequestQueue().cancelAll("getGiveBalance19");
        MutualApplication.getRequestQueue().cancelAll("confirmGiveBeiBiPassword20");
        MutualApplication.getRequestQueue().cancelAll("beiBiGive21");
        MutualApplication.getRequestQueue().cancelAll("getYbeibiBalance");
    }

    /**
     * 被赠与者电话输入规则
     */
    private void giveBeiBiPhone() {
        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (phone.getText().toString().length() == 11 && DateUtils.gephonetzhenze(phone.getText().toString())) {
                    phoneRight = 1;
                    beiBiPhoneType();
                } else {
                    radioGroup.clearCheck();
                    radioGroup.setVisibility(View.GONE);
                    check = 0;
                    phoneRight = 0;
                }
            }
        });
    }
    /**
     *个人信息获取  获取金贝币和银贝币的余额
     */
    private void getPersonInfo(final String type){
        try {
            JSONObject obj = new JSONObject();
            obj.put("storeid",UserInfo.getInstance().getUser(mContext).getStoreid());
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST, HttpUrl.usermessage, obj,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            LogUtil.i("银贝币余额"+response.toString());
                            try {
                                if ("true".equals(response
                                        .getString("success"))) {
                                    LogUtil.i("贝币赠与个人信息" + response.toString());
                                    JSONObject jsonObject = response.getJSONObject("storeinfo");

                                    jbeibiYue = jsonObject.getDouble("beibiamt");//金贝币余额
                                    ybeibiYue = jsonObject.getDouble("liftsilver");//银贝币余额
                                    if (type.equals("1")) {
                                        yueTv.setText("余额: "+jbeibiYue);
                                    } else if (type.equals("2")) {
                                        yueTv.setText("余额: "+ybeibiYue);
                                    }
                                } else {
                                        CustomToast.getInstance(mContext).setMessage(response.getString("message")
                                                + "");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });
            jsonObjectRequest.setTag("yBeiBi");
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy
                    (500000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 下一步监听点击事件
     */
    public void next() {
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (phoneRight == 1) {
                    Log.e("aa", "check" + check);
                    if (check == 0) {
                        if (radioGroup.getVisibility() == View.GONE) {
                            CustomToast.getInstance(BeiBiGiveActivity.this).setMessage("好友不存在，请重新输入手机号！");//
                        } else {
                            CustomToast.getInstance(BeiBiGiveActivity.this).setMessage("请选中您赠与的是商户还是消费者！");//好友不存在，请重新输入手机号
                        }
                    } else {
                        if (beibi.getText().toString().equals("")) {
                            CustomToast.getInstance(BeiBiGiveActivity.this).setMessage("您还没有输入赠与的金额！");
                        } else {
                            //判断是金贝币 还是银贝币赠与
                            if (jBeiBiRadio.isChecked()){
                                //金贝币赠与 判断余额--传类型
                                tellBeiBi("0");//是1就都填写了   然后判断贝币的余额
                            }else if (yBeiBiRadio.isChecked()){
                                //银贝币赠与  判断余额  ---传类型
                                yBeiBiYue("1");
                            }
                        }
                    }
                } else {
                    CustomToast.getInstance(BeiBiGiveActivity.this).setMessage("请输入正确的手机号！");
                }
            }
        });
    }
    /**
     * 银贝币余额
     */
    public void yBeiBiYue(String beibiType) {
        StoreIdPostBean storeIdPostBean = new StoreIdPostBean();
        storeIdPostBean.setStoreid(UserInfo.getInstance().getUser(BeiBiGiveActivity.this).getStoreid());
        Http.getYbeibiBalance(BeiBiGiveActivity.this, storeIdPostBean, beibi, phone, type,beibiType);
    }
}
