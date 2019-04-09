package com.example.administrator.merchants.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseActivity;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.post.BankPostBean;
import com.example.administrator.merchants.common.DateUtils;
import com.example.administrator.merchants.common.toast.CustomToast;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：添加和修改银行卡
 */
public class BankCardActivity extends BaseActivity {
    private EditText bankName, bankNumber, userName, bankPhone;
    private Button commit;
    private String type, bindbank, bindaccount, bindname, bindphone, cardid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_card_add);
        bankName = (EditText) findViewById(R.id.activity_bank_card_add_bank_name);
        bankNumber = (EditText) findViewById(R.id.activity_bank_card_add_bank_card_number);
        userName = (EditText) findViewById(R.id.activity_bank_card_add_user_name);
        bankPhone = (EditText) findViewById(R.id.activity_bank_card_add_bank_phone);
        commit = (Button) findViewById(R.id.activity_bank_card_add_bank_commit);
        type = getIntent().getStringExtra("type");
        bindbank = getIntent().getStringExtra("bindbank");
        bindaccount = getIntent().getStringExtra("bindaccount");
        bindname = getIntent().getStringExtra("bindname");
        bindphone = getIntent().getStringExtra("bindphone");
        cardid = getIntent().getStringExtra("cardid");
        if ("add".equals(type)) {
            setTitles("添加银行卡");
        } else {
            setTitles("修改银行卡");
            bankName.setText(bindbank);
            bankNumber.setText(bindaccount);
            userName.setText(bindname);
            bankPhone.setText(bindphone);
        }
        bankName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int lines = bankName.getLineCount();
                // 限制最大输入行数
                if (lines > 2) {
                    String str = s.toString();
                    int cursorStart = bankName.getSelectionStart();
                    int cursorEnd = bankName.getSelectionEnd();
                    if (cursorStart == cursorEnd && cursorStart < str.length() && cursorStart >= 1) {
                        str = str.substring(0, cursorStart - 1) + str.substring(cursorStart);
                    } else {
                        str = str.substring(0, s.length() - 1);
                    }
                    // setText会触发afterTextChanged的递归
                    bankName.setText(str);
                    // setSelection用的索引不能使用str.length()否则会越界
                    bankName.setSelection(bankName.getText().length());
                }

            }
        });
        DateUtils.getAddCard(bankNumber);//银行卡号4位以空格
        DateUtils.getaddphone(bankPhone);//手机号3位空格4位空格
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(bankName.getText().toString()) || "".equals(bankNumber.getText().toString()) || "".equals(bankPhone.getText().toString()) || "".equals(userName.getText().toString())) {
                    CustomToast.getInstance(BankCardActivity.this).setMessage("基本信息不能为空！");
                } else if (!DateUtils.gephonetzhenze(bankPhone.getText().toString().replaceAll(" ", ""))) {
                    CustomToast.getInstance(BankCardActivity.this).setMessage("电话格式不对！");
                } else if (!DateUtils.getbankzhenze(bankNumber.getText().toString().replaceAll(" ", ""))) {
                    CustomToast.getInstance(BankCardActivity.this).setMessage("银行卡格式不对！");

                } else {
                    if ("add".equals(type)) {
                        getadd();//添加
                    } else {
                        getupdate();//修改
                    }

                }
            }
        });
    }

    /**
     * 添加银行卡
     */
    public void getadd() {
        BankPostBean bankPostBean = new BankPostBean();
        bankPostBean.setStoreid(UserInfo.getInstance().getUser(this).getStoreid());
        bankPostBean.setStorename(UserInfo.getInstance().getUser(this).getStorename());
        bankPostBean.setBindaccount(bankNumber.getText().toString().replaceAll(" ", ""));
        bankPostBean.setBindbank(bankName.getText().toString());
        bankPostBean.setBindname(userName.getText().toString());
        bankPostBean.setBindphone(bankPhone.getText().toString().replaceAll(" ", ""));
        Http.bankAdd(BankCardActivity.this, bankPostBean);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MutualApplication.getRequestQueue().cancelAll("bankAdd4");
        MutualApplication.getRequestQueue().cancelAll("bankUpdate5");
    }

    /**
     * 修改银行卡
     */
    public void getupdate() {
        BankPostBean bankPostBean = new BankPostBean();
        bankPostBean.setCardid(cardid);
        bankPostBean.setStoreid(UserInfo.getInstance().getUser(this).getStoreid());
        bankPostBean.setStorename(UserInfo.getInstance().getUser(this).getStorename());
        bankPostBean.setBindaccount(bankNumber.getText().toString().replaceAll(" ", ""));
        bankPostBean.setBindbank(bankName.getText().toString());
        bankPostBean.setBindname(userName.getText().toString());
        bankPostBean.setBindphone(bankPhone.getText().toString().replaceAll(" ", ""));
        Http.bankUpdate(BankCardActivity.this, bankPostBean);
    }

}
