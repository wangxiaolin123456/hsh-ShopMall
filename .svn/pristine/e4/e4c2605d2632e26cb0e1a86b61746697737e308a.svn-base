package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.Response;
import com.example.administrator.merchants.R;
import com.example.administrator.merchants.activity.ServiceDetailsActivity;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.common.TimerCount;
import com.example.administrator.merchants.common.toast.CustomToast;
import com.example.administrator.merchants.common.TimerHandler;

import org.json.JSONObject;

/**
 * 作者：韩宇 on 2017/8/2 0002 13:46
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：申请服务提交
 */
public class ServiceCommitListener implements Response.Listener<JSONObject> {
    private Context context;
    private TimerCount timerCount;
    private TimerHandler timerHandler;
    private EditText phone, code;
    private Button getCode, commit;
    private ImageView checkImage;

    public ServiceCommitListener(Context context, TimerCount timerCount, TimerHandler timerHandler, EditText phone, EditText code, Button getCode, ImageView checkImage, Button commit) {
        this.context = context;
        this.phone = phone;
        this.code = code;
        this.timerCount = timerCount;
        this.timerHandler = timerHandler;
        this.getCode = getCode;
        this.checkImage = checkImage;
        this.commit = commit;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //返回成功
            ServiceDetailsActivity.st = 2;
            ServiceDetailsActivity.httpCode = "a";
            CustomToast.getInstance(context).setMessage("已提交成功!");
            timerCount.cancel();
            timerHandler.cancel();
            phone.setText("");
            code.setText("");
            getCode.setText("获得验证码");
            getCode.setBackgroundResource(R.drawable.dialog_yellow);
            getCode.setClickable(true);
            checkImage.setVisibility(View.GONE);
            commit.setClickable(true);
        } else {
            //返回失败
            commit.setClickable(true);
            CustomToast.getInstance(context).setMessage("验证码不正确！");
        }
    }
}
