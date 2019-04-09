package com.example.administrator.merchants.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.administrator.merchants.R;
import com.example.administrator.merchants.adapter.ConfirmOrderAdapter;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseActivity;
import com.example.administrator.merchants.common.MyMath;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.common.toast.CustomToast;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.HttpUrl;
import com.example.administrator.merchants.http.PossObject;
import com.example.administrator.merchants.http.post.StoreIdPostBean;
import com.example.administrator.merchants.http.post.ToSubOrderPostBean;
import com.example.administrator.merchants.utils.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;

/**
 * 作者：韩宇 on 2017/6/23 0023 14:39
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：提交订单
 */
public class ConfirmOrderActivity extends BaseActivity {
    private ListView listView;
    private TextView textViewTotal;//支付金额显示
    private TextView areaNameTextView;//收货地址
    private TextView contactTextView;//收货人电话
    private TextView genderTextView;//收货人性别
    private TextView receiverTextView;//收货人姓名
    private TextView streetAddTextView;//详细地址
    private TextView textViewSubOrder, discountMoney;//提交按钮
    private LinearLayout plusAddress;//地址布局
    private LinearLayout topLine, discountLinear;//上面额线
    private RelativeLayout blowRel;//提交订单布局
    public static String addressId;//地址编码
    public static String gender;//地址使用者性别
    private View headView, footView;
    public static String discountId = "no";
    private TextView titleTv;//标题
    private TextView totalNumTv;//商品总的数量
    private TextView totalMoneyTv;//商品总价
    //********银贝币逻辑处理************************
    private TextView noYBeiBiTv;//银贝币不可用
    private ImageView noBeiIcon;
    private LinearLayout haveYBeiBiLine;//显示加减号
    private ImageView subIcon;//减号
    private ImageView addIcon;//加号
    private EditText beibiNumEdit;//可输入的银贝币数
    private TextView maxNumTv;//最多抵扣的银贝币数
    private double moneys=0;//总价

    private double ybeibiPrice = 0;
    private TextView ybeibiNum;//可抵用的钱数  后台获取
    private double ybeibiTotal;//商品总共需要抵扣的银贝币数
    private String isSilver;//判断是否使用银贝币抵扣
    private double personYbeibiYue = 0;//用户银贝币余额
    private TextView shopTotalNum;//商品总贝币数量
    private double endPrice;//减去银贝币最终的价格

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        headView = LayoutInflater.from(this).inflate(R.layout.header_to_sub_order, null);
        titleTv = headView.findViewById(R.id.head_title);
        titleTv.setText("提交订单");
        footView = LayoutInflater.from(this).inflate(R.layout.foot_to_sub_order, null);
        //银贝币
        shopTotalNum=footView.findViewById(R.id.shop_total_unit_num_tv);
        noYBeiBiTv = footView.findViewById(R.id.no_beibi_used_tv);
        noBeiIcon = footView.findViewById(R.id.no_beibi_used_icon);
        haveYBeiBiLine = footView.findViewById(R.id.have_beibi_line);
        subIcon = footView.findViewById(R.id.sub_icon);
        addIcon = footView.findViewById(R.id.add_icon);
        beibiNumEdit = footView.findViewById(R.id.input_beibi_num_edit);
        maxNumTv = footView.findViewById(R.id.max_beibi_num_tv);
        //银贝币结束
        totalNumTv = footView.findViewById(R.id.confirm_order_total_num_tv);
        totalMoneyTv = footView.findViewById(R.id.confirm_order_total_price_tv);
        discountLinear = (LinearLayout) footView.findViewById(R.id.foot);
        discountMoney = (TextView) footView.findViewById(R.id.discountMoney);
        topLine = (LinearLayout) findViewById(R.id.activity_confirm_order_top_line);
        blowRel = (RelativeLayout) findViewById(R.id.activity_blow_rel);
        listView = (ListView) findViewById(R.id.activity_confirm_order_list);
        textViewTotal = (TextView) findViewById(R.id.confirm_zongqianshu);
        textViewSubOrder = (TextView) findViewById(R.id.submit_order);
        plusAddress = (LinearLayout) headView.findViewById(R.id.activity_confirm_order_address);
        plusAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ConfirmOrderActivity.this, AddressListActivity.class));
            }
        });
        receiverTextView = (TextView) headView.findViewById(R.id.per_name);
        genderTextView = (TextView) headView.findViewById(R.id.per_sex);
        contactTextView = (TextView) headView.findViewById(R.id.per_phone);
        areaNameTextView = (TextView) headView.findViewById(R.id.per_address);
        streetAddTextView = (TextView) headView.findViewById(R.id.per_address_more);
        topLine.setVisibility(View.GONE);
        blowRel.setVisibility(View.GONE);
        textViewSubOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    toSubOrder();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        //走接口获取银贝币的余额
        yBeiBiBalance();
        Http.discount(discountMoney, this, discountLinear, textViewTotal,
                totalMoneyTv);

        //计算商品的总数量
        int num = 0;
        double oneYbeibiPrice=0;
        ybeibiTotal = 0;//可抵扣的银贝币总数
        for (int i = 0; i < MutualApplication.chooseList.size(); i++) {
            num += MutualApplication.chooseList.get(i).getMerqty();
            oneYbeibiPrice= MyMath.mul(MutualApplication.chooseList.get(i).getUsedsilver()+"",
                    MutualApplication.chooseList.get(i).getMerqty()+"");
            ybeibiTotal=MyMath.add(oneYbeibiPrice+"",ybeibiTotal+"");
        }
        shopTotalNum.setText("(商品总需"+ybeibiTotal+"银贝币)");
        totalNumTv.setText("共" + num + "件商品");
        listView.setAdapter(new ConfirmOrderAdapter(this, MutualApplication.chooseList));
        listView.addHeaderView(headView);
        listView.addFooterView(footView);
        topLine.setVisibility(View.VISIBLE);
        blowRel.setVisibility(View.VISIBLE);
    }

    /**
     *获取银贝币余额
     */
    private void yBeiBiBalance(){
        try {
            JSONObject obj = new JSONObject();
            obj.put("storeid",UserInfo.getInstance().getUser(mContext).getStoreid());
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST, HttpUrl.usermessage, obj,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            LogUtil.i("确认订单银贝币余额"+response.toString());
                            try {
                                if ("true".equals(response
                                        .getString("success"))) {
                                    JSONObject jsonObject = response.getJSONObject("storeinfo");
                                    personYbeibiYue = jsonObject.getDouble("liftsilver");//银贝币余额
                                    //处理逻辑
                                    if (personYbeibiYue <= 0) {
                                        //（3）若银贝币=0显示无可用贝币。
                                        //无贝币可用
                                        noYBeiBiTv.setVisibility(View.VISIBLE);
                                        noBeiIcon.setVisibility(View.VISIBLE);
                                        haveYBeiBiLine.setVisibility(View.GONE);
                                        maxNumTv.setVisibility(View.GONE);
                                    } else {
                                        noYBeiBiTv.setVisibility(View.GONE);
                                        noBeiIcon.setVisibility(View.GONE);
                                        haveYBeiBiLine.setVisibility(View.VISIBLE);
                                        maxNumTv.setVisibility(View.VISIBLE);
                                        //银贝币余额不是0
                                        maxNumTv.setText("*最多可用" + personYbeibiYue + "银贝币");
                                    }
                                    LogUtil.i("商品总银贝币数"+ybeibiTotal+"银贝币余额"+personYbeibiYue);

                                    beibiNumEdit.addTextChangedListener(new TextWatcher() {
                                        @Override
                                        public void onTextChanged(CharSequence str, int arg1, int arg2,
                                                                  int arg3) {
                                            // TODO Auto-generated method stub
                                            //    （1）若银贝币余额＞商品价格，“最多可使用贝币数量”与商品价格相等，例如商品价格30，银贝币余额50，
//    则显示“最多可使用贝币数量50（显示的是他银贝币的余额量）”，点击“+-”
//            （也可手动键盘输入价格）和编辑数量最多不可超过30，可全额抵扣货款
                                            //（2）若银贝币余额＜=商品价格，“最多可使用贝币数量”与客户自身贝币余额相等，例如商品价格30，
//    银贝币余额20，可抵扣货款20，则显示最多可使用贝币数量20
                                            if (!beibiNumEdit.getText().toString().equals("")) {
                                                if (personYbeibiYue> ybeibiTotal){
                                                    if (Double.parseDouble(str.toString())> ybeibiTotal){
                                                        //计算总价格
                                                        beibiNumEdit.setText(ybeibiTotal+"");
                                                        CustomToast.getInstance(mContext).setMessage("抵扣银贝币数量不能超过商品总价格");
                                                    }
                                                }else if (personYbeibiYue<= ybeibiTotal){
                                                    if (Double.parseDouble(str.toString())>personYbeibiYue){
                                                        CustomToast.getInstance(mContext).setMessage("银贝币余额不足!");
                                                    }

                                                    beibiNumEdit.setText(personYbeibiYue+"");
                                                }

                                                endPrice = new BigDecimal
                                                        (totalMoneyTv.getText().toString()).
                                                        subtract(new BigDecimal(Double.parseDouble
                                                                (beibiNumEdit.getText().toString()))).doubleValue();

                                                textViewTotal.setText(endPrice+"");
                                            }else {
                                                textViewTotal.setText(totalMoneyTv.getText().toString());
                                            }
                                        }
                                        @Override
                                        public void beforeTextChanged(CharSequence arg0, int arg1,
                                                                      int arg2, int arg3) {
                                            // TODO Auto-generated method stub
                                        }
                                        @Override
                                        public void afterTextChanged(Editable arg0) {
                                            // TODO Auto-generated method stub
                                        }
                                    });

                                    subIcon.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            //减号
                                            double y = Double.parseDouble(beibiNumEdit.getText().toString());
                                            if (y <= 0) {
                                            } else {
                                                y--;
                                                if (y <= 0) {
                                                    beibiNumEdit.setText("0");
                                                    CustomToast.getInstance(mContext).setMessage("不能在减少了");
                                                    //减号变灰色

                                                } else {
                                                    beibiNumEdit.setText(String.valueOf(y));
                                                    //减去 总价+
//                                                    总价-输入的
                                                    endPrice = new BigDecimal
                                                            (totalMoneyTv.getText().toString()).
                                                            subtract(new BigDecimal(y)).doubleValue();
                                                    textViewTotal.setText(endPrice+"");
                                                }
                                            }
                                        }
                                    });

                                    addIcon.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            //加号
                                            double x = Double.parseDouble(beibiNumEdit.getText().toString());
                                            x++;
                                            if (ybeibiTotal>personYbeibiYue){
                                                if (Double.parseDouble(beibiNumEdit.getText().toString())<personYbeibiYue){
                                                    //输入数量和银贝币余额的比较
                                                    if (x>personYbeibiYue){
                                                        beibiNumEdit.setText(personYbeibiYue+"");

                                                        CustomToast.getInstance(mContext).setMessage("银贝币余额不足!");
                                                    }else {
                                                        beibiNumEdit.setText(String.valueOf(x));
                                                    }
                                                }else{
                                                    CustomToast.getInstance(mContext).setMessage("银贝币余额不足!");
                                                }
                                            }else if (ybeibiTotal<=personYbeibiYue){
                                                if (Double.parseDouble(beibiNumEdit.getText().toString())<= ybeibiTotal){
                                                    //输入数量和总价的比较
                                                    if (x>ybeibiTotal){
                                                        beibiNumEdit.setText(ybeibiTotal+"");
                                                        CustomToast.getInstance(mContext).setMessage("抵扣银贝币数量不能超过商品总价格");
                                                    }else {
                                                        beibiNumEdit.setText(String.valueOf(x));
                                                    }
                                                }else{
                                                    CustomToast.getInstance(mContext).setMessage("抵扣银贝币数量不能超过商品总价格");
                                                }
                                            }
                                            endPrice = new BigDecimal
                                                    (totalMoneyTv.getText().toString()).
                                                    subtract(new BigDecimal
                                                            (Double.parseDouble(beibiNumEdit.getText().toString()))).
                                                    doubleValue();
                                            textViewTotal.setText(endPrice+"");

                                        }
                                    });
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
    @Override
    protected void onResume() {
        super.onResume();
        getDefaultAddress();
    }


    /**
     * 获取默认地址
     */
    public void getDefaultAddress() {
        StoreIdPostBean storeIdPostBean = new StoreIdPostBean();
        storeIdPostBean.setStoreid(UserInfo.getInstance().getUser(this).getStoreid());
        Http.getDefaultAddress(ConfirmOrderActivity.this, storeIdPostBean, receiverTextView, genderTextView, contactTextView, areaNameTextView, streetAddTextView);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MutualApplication.getRequestQueue().cancelAll("getDefaultAddress29");
        MutualApplication.getRequestQueue().cancelAll("toSubOrder30");
        MutualApplication.getRequestQueue().cancelAll("discount119");
    }

    /**
     * 提交订单到服务器
     * 提交订单加银贝比抵扣字段
     *
     * @param
     */
    public void toSubOrder() throws JSONException {
        ToSubOrderPostBean toSubOrderPostBean = new ToSubOrderPostBean();
        toSubOrderPostBean.setMerList(PossObject.getMerList(ConfirmOrderActivity.this));
        toSubOrderPostBean.setStoreid(UserInfo.getInstance().getUser(this).getStoreid());
        toSubOrderPostBean.setStorename(UserInfo.getInstance().getUser(this).getStorename());
        toSubOrderPostBean.setOrdamt(MutualApplication.totalConfirm);
        toSubOrderPostBean.setAddressid(addressId);
        toSubOrderPostBean.setReceiver(receiverTextView.getText().toString());
        toSubOrderPostBean.setGender(gender);
        toSubOrderPostBean.setContact(contactTextView.getText().toString());
        toSubOrderPostBean.setAreaname(areaNameTextView.getText().toString());
        toSubOrderPostBean.setStreetaddr(streetAddTextView.getText().toString());
        if (beibiNumEdit.getText().toString().equals("")) {
            isSilver = "2";
            toSubOrderPostBean.setUsedSilver("0");
        } else {
            if (Double.parseDouble(beibiNumEdit.getText().toString())>0){
                //使用了银贝币抵扣
                isSilver = "1";
                toSubOrderPostBean.setUsedSilver(beibiNumEdit.getText().toString());
            }else {
                isSilver = "2";
                toSubOrderPostBean.setUsedSilver("0");
            }
        }
        toSubOrderPostBean.setIsSilver(isSilver);
        Http.toSubOrder(ConfirmOrderActivity.this, toSubOrderPostBean, textViewTotal,isSilver);
    }
}
