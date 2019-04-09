package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.volley.Response;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.http.Status;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者：韩宇 on 2017/6/22 0022 14:04
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：贝币赠与电话类型
 */
public class PhoneTypeListener implements Response.Listener<JSONObject> {
    private Context context;
    private RadioGroup radioGroup;
    private RadioButton rb1, rb2;//商户选项
    private EditText phone;

    public PhoneTypeListener(Context context, RadioGroup radioGroup, RadioButton rb1, RadioButton rb2, EditText phone) {
        this.context = context;
        this.radioGroup = radioGroup;
        this.rb1 = rb1;
        this.rb2 = rb2;
        this.phone = phone;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //返回成功
            try {
                String isstore = jsonObject.getString("isstore");
                String ismem = jsonObject.getString("ismem");
                if (isstore.equals("0") && ismem.equals("0")) {
                    //电话号没注册互实会相关app
                    radioGroup.setVisibility(View.GONE);
                } else {
                    radioGroup.setVisibility(View.VISIBLE);
                    if (isstore.equals("1") && !(phone.getText().toString().
                            equals(UserInfo.getInstance().getUser(context).getStorephone()))) {
                        //电话注册了商家版app（不可以赠送自己）
                        rb1.setVisibility(View.VISIBLE);
                    } else {
                        rb1.setVisibility(View.GONE);
                    }
                    if (ismem.equals("1")) {
                        //注册了消费者版app
                        rb2.setVisibility(View.VISIBLE);
                    } else {
                        rb2.setVisibility(View.GONE);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else {
            //返回失败
            Status.fail(context, jsonObject);
        }

    }
}
