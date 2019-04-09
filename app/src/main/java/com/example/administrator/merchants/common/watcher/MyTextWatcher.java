package com.example.administrator.merchants.common.watcher;

import android.text.Editable;
import android.text.TextWatcher;

import com.example.administrator.merchants.http.show.ShoppingCarShowBean;

import java.util.List;


/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：备注EditText监听
 */
public class MyTextWatcher implements TextWatcher {
    private int mPosition;
    private List<ShoppingCarShowBean> list;

    public MyTextWatcher(List<ShoppingCarShowBean> list) {
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
        list.get(mPosition).setRemark(s.toString());//这里给他赋值
    }
}
