package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.example.administrator.merchants.activity.ConfirmOrderActivity;
import com.example.administrator.merchants.activity.MonthIncomeActivity;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.common.DateUtils;
import com.example.administrator.merchants.common.MyMath;
import com.example.administrator.merchants.common.toast.CustomToast;
import com.example.administrator.merchants.common.views.RefreshLayout;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.http.show.MonthIncomeShowBean;
import com.example.administrator.merchants.utils.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/12/1 0001 14:31
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：折扣
 */
public class DiscountListener implements Response.Listener<JSONObject> {
    private Context context;
    private TextView discount,textViewTotal;
    private TextView totalMoney;
    private int type;
    private LinearLayout discountLinear;

    public DiscountListener(Context context,TextView discount,int type){
        this.context=context;
        this.discount=discount;
        this.type=type;
    }
    public DiscountListener(Context context, TextView discount,
                            int type, LinearLayout discountLinear, TextView textViewTotal,
                            TextView totalMoney){
        this.context=context;
        this.discount=discount;
        this.type=type;
        this.discountLinear=discountLinear;
        this.textViewTotal=textViewTotal;
        this.totalMoney=totalMoney;
    }
    @Override
    public void onResponse(JSONObject jsonObject) {
        LogUtil.i("满减什么逼玩意"+jsonObject.toString());
        if (Status.status(jsonObject)){
            //返回成功
            if (type==0){
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("list");
                    List<JSONObject> jsonObjectList = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObjectList.add((JSONObject) jsonArray.get(i));
                    }
                    if (jsonObjectList.size()!=0){
                        String discountText="购物";
                        for (int j = 0; j < jsonObjectList.size(); j++) {
                            discountText+="满"+jsonObjectList.get(j).getString("fullmoney")+"减"+
                                    jsonObjectList.get(j).getString("cutmoney")+";   ";
                        }
                        discount.setText(discountText);
                    }else {
                        discount.setText("");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else if (type==1){
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("list");
                    List<JSONObject> jsonObjectList = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObjectList.add((JSONObject) jsonArray.get(i));
                    }
                    double moneys=0;
                    boolean state=false;
                    for (int j = 0; j < jsonObjectList.size(); j++) {
                        if (MyMath.sub(String.valueOf(MutualApplication.totalConfirm),jsonObjectList.get(j).getString("fullmoney"))>=0){
                            discount.setText("-￥"+jsonObjectList.get(j).getString("cutmoney"));
                            ConfirmOrderActivity.discountId=jsonObjectList.get(j).getString("discountid");
                            textViewTotal.setText(MyMath.sub(String.valueOf(MutualApplication.totalConfirm), jsonObjectList.get(j).getString("cutmoney"))+"");
                            totalMoney.setText(MyMath.sub(String.valueOf(MutualApplication.totalConfirm), jsonObjectList.get(j).getString("cutmoney"))+"");
                            moneys=MyMath.sub(String.valueOf(MutualApplication.totalConfirm), jsonObjectList.get(j).getString("cutmoney"));
                            discountLinear.setVisibility(View.VISIBLE);
                            state=true;
                            return;
                        }
                    }
                    if (!state){
                        textViewTotal.setText(String.valueOf(MutualApplication.totalConfirm));
                        totalMoney.setText(String.valueOf(MutualApplication.totalConfirm));
                        moneys=Double.parseDouble(MutualApplication.totalConfirm);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else  if (type==2){
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("list");
                    List<JSONObject> jsonObjectList = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObjectList.add((JSONObject) jsonArray.get(i));
                    }
                    if (jsonObjectList.size()!=0){
                        discount.setText("购物满"+jsonObjectList.get(0).getString("fullmoney")+"减"+jsonObjectList.get(0).getString("cutmoney")+";");
                    }else{
                        discount.setText("");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }else {
            //返回失败
            Status.fail(context,jsonObject);
        }

    }
}
