package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.widget.TextView;

import com.android.volley.Response;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.common.DateUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者：韩宇 on 2017/6/22 0022 09:40
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：获取默认银行卡
 */
public class GetDefaultBankListener implements Response.Listener<JSONObject> {
    private Context context;
    private TextView bankname, username, tel, banknumber;

    public GetDefaultBankListener(Context context, TextView bankname, TextView username, TextView tel, TextView banknumber) {
        this.context = context;
        this.bankname = bankname;
        this.username = username;
        this.tel = tel;
        this.banknumber = banknumber;
    }

    @Override
    public void onResponse(JSONObject response) {
        if (Status.status(response)) {
            //返回成功弄
            JSONObject jsonObject = null;
            try {
                jsonObject = response.getJSONObject("bankcard");
                bankname.setText(jsonObject.getString("bindbank"));
                username.setText(jsonObject.getString("bindname"));
                tel.setText(DateUtils.getTelephone(jsonObject.getString("bindphone")));
                if (jsonObject.getString("bindaccount").length() == 19) {
                    banknumber.setText(DateUtils.getbanknumber19(jsonObject.getString("bindaccount")));
                } else if (jsonObject.getString("bindaccount").length() == 16) {
                    banknumber.setText(DateUtils.getbanknumber16(jsonObject.getString("bindaccount")));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else {
            //返回失败
            bankname.setText("请添加银行卡信息");
            banknumber.setText("");
            username.setText("");
            tel.setText("");
        }

    }
}
