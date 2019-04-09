package com.example.administrator.merchants.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.common.CommonUtil;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.common.MD5;
import com.example.administrator.merchants.http.post.LoginPostBean;
import com.example.administrator.merchants.common.toast.CustomToast;

import org.json.JSONException;

import cn.jpush.android.api.JPushInterface;

/**
 * 作者：韩宇 on 2017/6/23 0023 14:39
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：登录
 */
public class LoginActivity extends Activity {
    private EditText et_user_phone, et_user_pwd;
    private TextView tv_forget_pwd;
    private Button btn_login;
    private ProgressDialog p_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_user_phone = (EditText) findViewById(R.id.et_user_phone);
        et_user_pwd = (EditText) findViewById(R.id.et_user_pwd);
        tv_forget_pwd = (TextView) findViewById(R.id.tv_forget_pwd);
        btn_login = (Button) findViewById(R.id.btn_login);
        tv_forget_pwd.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        CommonUtil.editTextLength(et_user_pwd,24);
        //登陆监听
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_user_phone.getText().toString().length() < 11 || "".equals(et_user_phone.getText().toString()) || "".equals(et_user_pwd.getText().toString())) {
                    CustomToast.getInstance(LoginActivity.this).setMessage("手机号和密码不能为空或格式不正确！");
                } else {
                    if (CommonUtil.compileExChar(et_user_pwd.getText().toString())){
                        CustomToast.getInstance(LoginActivity.this).setMessage("不能含有特殊字符'和#！");
                    }else {
                        p_dialog = ProgressDialog
                                .show(LoginActivity.this,
                                        "",
                                        "登录中请稍后...",
                                        true);
                        try {
                            login();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }


                }
            }
        });
        //忘记密码监听---跳转忘记密码activity
        tv_forget_pwd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("type","login");
                intent.setClass(LoginActivity.this, ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 登录接口
     */
    public void login() throws JSONException {
        LoginPostBean loginPostBean = new LoginPostBean();
        loginPostBean.setStorephone(et_user_phone.getText().toString().trim());
        loginPostBean.setStorepassword(MD5.toMD5(et_user_pwd.getText().toString().trim()));
        loginPostBean.setDevicetype("Android");
        loginPostBean.setDevicenumber(JPushInterface.getRegistrationID(LoginActivity.this));
        Http.login(LoginActivity.this, p_dialog, loginPostBean);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MutualApplication.getRequestQueue().cancelAll("login1");
    }
    /***
     * 返回键返回返回到系统主菜单等同于home建
     *
     * @param keyCode
     * @param event
     * @return
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Intent nIntent = new Intent(Intent.ACTION_MAIN);
            nIntent.addCategory(Intent.CATEGORY_HOME);
            startActivity(nIntent);
        }
        return super.onKeyDown(keyCode, event);
    }
}
