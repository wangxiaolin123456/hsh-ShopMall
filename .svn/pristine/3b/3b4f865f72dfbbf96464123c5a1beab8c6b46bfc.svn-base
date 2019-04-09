package com.example.administrator.merchants.common;


import android.widget.TextView;

import com.example.administrator.merchants.R;

/**
 * 作者：韩宇
 * 邮箱：18698802347@163.com
 * 功能：获取验证码倒计时
 */
public class TimerCount extends CountDownTimer {
    private TextView textView;

    public TimerCount(long millisInFuture, long countDownInterval, TextView textView) {
        super(millisInFuture, countDownInterval);
        this.textView = textView;
    }

    @Override
    public void onFinish() {
        textView.setClickable(true);
        textView.setText("获得验证码");
        textView.setBackgroundResource(R.drawable.dialog_yellow);
    }

    @Override
    public void onTick(long arg0) {
        textView.setClickable(false);
        textView.setText(arg0 / 1000 + "秒后重新获取");
        textView.setBackgroundResource(R.drawable.dialog_gray_white);
    }
}
