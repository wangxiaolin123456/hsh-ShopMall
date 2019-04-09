package com.example.administrator.merchants.activity;


import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseActivity;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.post.CodePostBean;
import com.example.administrator.merchants.http.post.EnterNeedPostBean;
import com.example.administrator.merchants.common.DateUtils;
import com.example.administrator.merchants.common.TimerCount;
import com.example.administrator.merchants.common.toast.CustomToast;
import com.example.administrator.merchants.common.TimerHandler;

/**
 * 作者：韩宇 on 2017/6/23 0023 14:39
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：企业需求
 */
public class EnterpriseDemandActivity extends BaseActivity{
    private WebView imageView;//web背景显示
    private ImageView checkImage;//验证密正确显示的小圆点
    private EditText phone;//电话输入框
    private EditText code;//验证码输入框
    private EditText content;//企业联系人
    private EditText moreContent;//需求说明
    private Button getCode;//获取验证码按钮
    private Button commit;//提交按钮
    public static String httpCode ;//网络请求的验证码
    public static String getServeid;//服务编码
    private TimerHandler timerHandler;//倒计时
    private TimerCount timerCount;
    public static int st = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprise_need);
        setTitles("企业需求");
        httpCode="null";
        checkImage = (ImageView) findViewById(R.id.activity_enter_need_check_image);
        imageView = (WebView) findViewById(R.id.activity_service_details_image);
        phone = (EditText) findViewById(R.id.activity_enter_need_input_phone_edt);
        code = (EditText) findViewById(R.id.activity_enter_need_input_code_edt);
        content = (EditText) findViewById(R.id.activity_enter_need_call_pepole_edt);
        moreContent = (EditText) findViewById(R.id.activity_enter_need_input_content_edt);
        getCode = (Button) findViewById(R.id.activity_enter_need_get_code_btn);
        commit = (Button) findViewById(R.id.activity_enter_need_commit_btn);
        details();
        DateUtils.getCode(code, checkImage);
        //获取验证码
        getCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DateUtils.gephonetzhenze(phone.getText().toString())) {
                    getCode.setClickable(false);
                    timerHandler = new TimerHandler(60000, 1000, getCode, 7);
                    timerHandler.start();
                    timerCount = new TimerCount(60000, 1000, getCode);
                    getCodeRequest();
                } else {
                    CustomToast.getInstance(EnterpriseDemandActivity.this).setMessage("请输入正确的手机号格式！");
                }
            }
        });
        //立即提交
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = phone.getText().toString();
                String codes = code.getText().toString();//获取验证内容
                String contents = content.getText().toString();//内容
                String contentmore = moreContent.getText().toString();//更多内容
                if (phoneNumber.matches("") && codes.matches("")) {
                    CustomToast.getInstance(EnterpriseDemandActivity.this).setMessage("请输入手机号和验证码！");
                } else if (phoneNumber.matches("") && (!codes.matches(""))) {
                    CustomToast.getInstance(EnterpriseDemandActivity.this).setMessage("请输入手机号！");
                } else if (!phoneNumber.matches("") && codes.matches("")) {
                    CustomToast.getInstance(EnterpriseDemandActivity.this).setMessage("请输入验证码！");
                } else if (!phoneNumber.matches("") && !codes.matches("") && contents.matches("") && !contentmore.matches("")) {
                    CustomToast.getInstance(EnterpriseDemandActivity.this).setMessage("请输入企业联系人！");
                } else if (!phoneNumber.matches("") && !codes.matches("") && !contents.matches("") && contentmore.matches("")) {
                    CustomToast.getInstance(EnterpriseDemandActivity.this).setMessage("请输入内容！");
                } else {
                    if (st == 0) {
                        CustomToast.getInstance(EnterpriseDemandActivity.this).setMessage("您还未获取验证码！");
                    } else if (st == 2) {
                        CustomToast.getInstance(EnterpriseDemandActivity.this).setMessage("请重新获取验证码！");
                    } else {
                        if (code.getText().toString().equals(httpCode)) {
                            commit.setClickable(false);
                            commit();
                        } else {
                            CustomToast.getInstance(EnterpriseDemandActivity.this).setMessage("验证码不正确！");
                        }
                    }

                }
            }
        });
    }

    /**
     * 企业需求详情
     */
    public void details() {
        Http.enterDetails(EnterpriseDemandActivity.this, imageView);
    }

    /**
     * 提交信息
     */
    public void commit() {
        EnterNeedPostBean enterNeedPostBean = new EnterNeedPostBean();
        enterNeedPostBean.setStoreid(UserInfo.getInstance().getUser(EnterpriseDemandActivity.this).getStoreid());
        enterNeedPostBean.setGetServeid(getServeid);
        enterNeedPostBean.setNeedsphone(phone.getText().toString());
        enterNeedPostBean.setNeedsperson(content.getText().toString());
        enterNeedPostBean.setNeedsdescr(moreContent.getText().toString());
        enterNeedPostBean.setAucode(code.getText().toString());
        Http.enterNeed(EnterpriseDemandActivity.this, enterNeedPostBean, timerCount, timerHandler, phone, code, content, moreContent, getCode, checkImage, commit);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MutualApplication.getRequestQueue().cancelAll("getCode31");
        MutualApplication.getRequestQueue().cancelAll("enterNeed32");
        MutualApplication.getRequestQueue().cancelAll("enterDetails33");
    }

    /**
     * 获取验证码
     */
    public void getCodeRequest() {
        CodePostBean codePostBean = new CodePostBean();
        codePostBean.setNeedsphone(phone.getText().toString());
        Http.getCode(EnterpriseDemandActivity.this, codePostBean, timerCount);
    }

}
