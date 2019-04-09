package com.example.administrator.merchants.activity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseActivity;
import com.example.administrator.merchants.common.BeiBiDialog;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.common.MD5;
import com.example.administrator.merchants.http.post.StoreIdPostBean;
import com.example.administrator.merchants.http.post.ConfirmBeiBiPasswordPostBean;
import com.example.administrator.merchants.http.show.BeiBiDialogShowBean;
import com.example.administrator.merchants.common.DateUtils;
import com.example.administrator.merchants.common.toast.CustomToast;

/**
 * 作者：韩宇 on 2017/6/22 0022 10:09
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：余额提取
 */
public class BalanceActivity extends BaseActivity {
    private TextView bankname, username, tel, banknumber, balance;//银行卡类型，用户真实姓名，电话，银行卡号，余额
    private LinearLayout bankLay;//银行卡信息布局
    private EditText editText;//提取金额
    private Button button;//提取按钮
    private BeiBiDialogShowBean beiBiDialogShowBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        setTitles("余额提取");
        bankname = (TextView) findViewById(R.id.activity_balance_bankname);
        username = (TextView) findViewById(R.id.activity_balance_user_name);
        tel = (TextView) findViewById(R.id.activity_balance_tel);
        banknumber = (TextView) findViewById(R.id.activity_balance_bank_number);
        balance = (TextView) findViewById(R.id.activity_balance_balance);
        editText = (EditText) findViewById(R.id.activity_balance_money);
        button = (Button) findViewById(R.id.activity_balance_commit);
        bankLay = (LinearLayout) findViewById(R.id.bankLay);
        bankLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(BalanceActivity.this, BankCardListActivity.class);
                startActivity(i);
            }
        });
        DateUtils.setEditText(editText, button);//输入钱数>0时按钮变红
        DateUtils.setPricePoint(editText);//钱数的格式规则保留小数点后2位
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(banknumber.getText().toString())) {
                    CustomToast.getInstance(BalanceActivity.this).setMessage("对不起您还没有添加银行卡信息！");
                } else if ("".equals(editText.getText().toString())) {
                    CustomToast.getInstance(BalanceActivity.this).setMessage("请输入金额！");
                } else if (Double.parseDouble(editText.getText().toString()) - Double.parseDouble(editText.getText().toString()) < 0) {
                    CustomToast.getInstance(BalanceActivity.this).setMessage("余额不足！");
                } else if (Double.parseDouble(editText.getText().toString()) == 0) {
                } else {
                    payBeiBiDialog();
                }
            }
        });
    }


    /**
     * 获取余额
     */
    public void getBalance() {
        StoreIdPostBean storeIdPostBean = new StoreIdPostBean();
        storeIdPostBean.setStoreid(UserInfo.getInstance().getUser(this).getStoreid());
        Http.getBalance(BalanceActivity.this, storeIdPostBean, balance);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MutualApplication.getRequestQueue().cancelAll("getBalance14");
        MutualApplication.getRequestQueue().cancelAll("getDefaultBankCard15");
        MutualApplication.getRequestQueue().cancelAll("confirmBeiBiPassword16");
        MutualApplication.getRequestQueue().cancelAll("balanceExtraction17");
    }

    /**
     * 获取默认银行卡
     */
    public void getBankCard() {
        StoreIdPostBean storeIdPostBean = new StoreIdPostBean();
        storeIdPostBean.setStoreid(UserInfo.getInstance().getUser(this).getStoreid());
        Http.getDefaultBankCard(BalanceActivity.this, storeIdPostBean, bankname, username, tel, banknumber);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getBankCard();//获取银行卡信息
        getBalance();//获取贝币余额
    }

    /**
     * 贝币支付弹出的dialog
     */
    private void payBeiBiDialog() {
        beiBiDialogShowBean = BeiBiDialog.BeiBei(BalanceActivity.this);
        beiBiDialogShowBean.getConfirm().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmBeiBiPassword();//密码确认 请求网络
                beiBiDialogShowBean.getDialog().dismiss();
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });
    }
    /**
     * 密码确认  请求网络
     */
    private void confirmBeiBiPassword() {
        ConfirmBeiBiPasswordPostBean confirmBeiBiPasswordPostBean = new ConfirmBeiBiPasswordPostBean();
        confirmBeiBiPasswordPostBean.setStoreid(UserInfo.getInstance().getUser(this).getStoreid());
        confirmBeiBiPasswordPostBean.setPaypassword(MD5.toMD5(beiBiDialogShowBean.getPassword().getText().toString()));
        Http.confirmBeiBiPassword(BalanceActivity.this, confirmBeiBiPasswordPostBean, bankname, username, tel, banknumber, editText);
    }
}
