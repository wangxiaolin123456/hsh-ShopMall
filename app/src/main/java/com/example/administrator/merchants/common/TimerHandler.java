package com.example.administrator.merchants.common;

import android.graphics.Color;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.merchants.common.CountDownTimer;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：获取验证码倒计时
 */
public class TimerHandler extends CountDownTimer {
    private TextView textView;
    private Button button;
    private int type;

    public TimerHandler(long millisInFuture, long countDownInterval, TextView textView, int type) {
        super(millisInFuture, countDownInterval);
        this.textView = textView;
        this.type = type;
    }

    public TimerHandler(long millisInFuture, long countDownInterval, Button button, int type) {
        super(millisInFuture, countDownInterval);
        this.button = button;
        this.type = type;
    }

    @Override
    public void onFinish() {
        if (type == 1) {
            button.setBackgroundColor(Color.parseColor("#E93441"));
            button.setText("保存");
            button.setClickable(true);
        } else if (type == 2) {
            button.setBackgroundColor(Color.parseColor("#ff5252"));
            button.setText("确定支付");
            button.setClickable(true);
        } else if (type == 3) {
            button.setBackgroundColor(Color.parseColor("#ff5252"));
            button.setText("确认");
            button.setClickable(true);
        } else if (type == 4) {
            textView.setClickable(true);
        } else if (type == 5) {
            button.setBackgroundColor(Color.parseColor("#E93441"));
            button.setText("登录");
            button.setClickable(true);
        } else if (type == 6) {
            button.setBackgroundColor(Color.parseColor("#ff5252"));
            button.setText("确定支付");
            button.setClickable(true);
        } else if (type == 7) {
            button.setClickable(true);
        }
    }

    @Override
    public void onTick(long arg0) {
    }
}

