package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.common.TimerCount;
import com.example.administrator.merchants.common.toast.CustomToast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者：韩宇 on 2017/7/3 0003 10:30
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：忘记密码和忘记贝币密码
 */
public class ForgetCodeListener implements Response.Listener<JSONObject> {
    private Context context;
    private TextView getVerificationCode;
    private ImageView imageView;
    private EditText inputVerificationCode;

    public ForgetCodeListener(Context context, TextView getVerificationCode, ImageView imageView, EditText inputVerificationCode) {
        this.context = context;
        this.getVerificationCode = getVerificationCode;
        this.imageView = imageView;
        this.inputVerificationCode = inputVerificationCode;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //返回成功
            CustomToast.getInstance(context).setMessage("发送成功！");
            TimerCount timerCount = new TimerCount(60000, 1000, getVerificationCode);
            timerCount.start();
            try {
                final String httpCode = jsonObject.getString("aucode");
                inputVerificationCode.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (httpCode.equals(s + "")) {
                            imageView.setVisibility(View.VISIBLE);
                        } else {
                            imageView.setVisibility(View.GONE);
                        }
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            //返回失败
            Status.fail(context, jsonObject);
        }
    }
}
