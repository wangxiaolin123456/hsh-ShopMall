package com.example.administrator.merchants.common.watcher;

import android.text.Editable;
import android.text.TextWatcher;

import com.example.administrator.merchants.http.show.MerchantsOrderShowBean;

import java.util.List;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：评论里的EditText监听
 */
public class MyOrderConfirmTextWatcher implements TextWatcher {
    private int mPosition;
    private List<MerchantsOrderShowBean> list;

    public MyOrderConfirmTextWatcher(List<MerchantsOrderShowBean> list) {
        this.list = list;
    }

    public void updatePosition(int position) {
        mPosition = position;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        list.get(mPosition).setTexts(s.toString());//这里给他赋值
    }
}
