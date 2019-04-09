package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.Response;
import com.example.administrator.merchants.R;
import com.example.administrator.merchants.activity.EnterpriseDemandActivity;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.common.TimerCount;
import com.example.administrator.merchants.common.toast.CustomToast;
import com.example.administrator.merchants.common.TimerHandler;

import org.json.JSONObject;

/**
 * 作者：韩宇 on 2017/6/26 0026 17:15
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：企业需求提交
 */
public class EnterNeedListener implements Response.Listener<JSONObject> {
    public Context context;
    private TimerCount timerCount;
    private TimerHandler timerHandler;
    private EditText phone;//电话输入框
    private EditText code;//验证码输入框
    private EditText content;//企业联系人
    private EditText moreContent;//需求说明
    private Button getCode;//获取验证码按钮
    private ImageView checkImage;
    private Button commit;

    public EnterNeedListener(Context context, TimerCount timerCount, TimerHandler timerHandler, EditText phone, EditText code, EditText content, EditText moreContent, Button getCode, ImageView checkImage, Button commit) {
        this.context = context;
        this.timerCount = timerCount;
        this.timerHandler = timerHandler;
        this.phone = phone;
        this.code = code;
        this.content = content;
        this.moreContent = moreContent;
        this.getCode = getCode;
        this.checkImage = checkImage;
        this.commit = commit;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //返回成功
            CustomToast.getInstance(context).setMessage("已提交成功!");
            timerCount.cancel();
            timerHandler.cancel();
            phone.setText("");
            code.setText("");
            content.setText("");
            moreContent.setText("");
            getCode.setText("获得验证码");
            getCode.setBackgroundResource(R.drawable.dialog_yellow);
            getCode.setClickable(true);
            checkImage.setVisibility(View.GONE);
            commit.setClickable(true);
            EnterpriseDemandActivity.st = 2;
        } else {
            //返回失败
            commit.setClickable(true);
            Status.fail(context, jsonObject);
        }
    }
}
