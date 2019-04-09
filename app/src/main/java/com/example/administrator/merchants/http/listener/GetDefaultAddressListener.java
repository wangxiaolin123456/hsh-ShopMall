package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.widget.TextView;

import com.android.volley.Response;
import com.example.administrator.merchants.activity.ConfirmOrderActivity;
import com.example.administrator.merchants.http.Status;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者：韩宇 on 2017/6/25 0025 15:59
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：
 */
public class GetDefaultAddressListener implements Response.Listener<JSONObject> {
    private TextView receiverTextView;
    private TextView genderTextView;
    private TextView contactTextView;
    private TextView areanameTextView;
    private TextView streetaddrTextView;
    private Context context;
    public GetDefaultAddressListener(Context context,TextView receiverTextView,TextView genderTextView,TextView contactTextView,TextView areanameTextView,TextView streetaddrTextView){
        this.context=context;
        this.receiverTextView=receiverTextView;
        this.genderTextView=genderTextView;
        this.contactTextView=contactTextView;
        this.areanameTextView=areanameTextView;
        this.streetaddrTextView=streetaddrTextView;

    }
    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)){
            //返回成功
            try {

                JSONObject  objectAddress = jsonObject.getJSONObject("address");
                ConfirmOrderActivity.addressId = objectAddress.getString("addressid");
                String receiver = objectAddress.getString("receiver");
                ConfirmOrderActivity.gender = objectAddress.getString("gender");
                String contact = objectAddress.getString("contact");
                String streetaddr = objectAddress.getString("streetaddr");
                String areaname = objectAddress.getString("areaname");
                receiverTextView.setText(receiver);
                if ("".equals(objectAddress.getString("gender"))) {
                    genderTextView.setText("先生");
                } else {
                    genderTextView.setText("女士");
                }
                contactTextView.setText(contact);
                areanameTextView.setText(areaname);
                streetaddrTextView.setText(streetaddr);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else {
            //返回失败
            receiverTextView.setText("您还没有添加收货地址！");
            contactTextView.setText("");
            genderTextView.setText("");
            areanameTextView.setText("");
            streetaddrTextView.setText("");
        }

    }
}
