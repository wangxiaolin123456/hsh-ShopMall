package com.example.administrator.merchants.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseActivity;
import com.example.administrator.merchants.common.CommonUtil;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.HttpUrl;
import com.example.administrator.merchants.common.MD5;
import com.example.administrator.merchants.http.post.CodePostBean;
import com.example.administrator.merchants.http.post.ForgetPostBean;
import com.example.administrator.merchants.common.DateUtils;
import com.example.administrator.merchants.common.toast.CustomToast;
import com.example.administrator.merchants.common.UserInfo;
/**
 * 作者：韩宇 on 2017/6/23 0023 14:39
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：忘记贝币密码和忘记登录密码
 */
public class ForgetPasswordActivity extends BaseActivity {
    private EditText phone, inputVerificationCode, newPassword, newPasswordAgain;
    private Button complete;//完成
    private TextView getVerificationCode;//获取验证码
    private ImageView imageView;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        type = getIntent().getStringExtra("type");
        newPassword = (EditText) findViewById(R.id.activity_forgrt_password_input_new_password);
        newPasswordAgain = (EditText) findViewById(R.id.activity_forgrt_password_input_new_password_again);
        if ("login".equals(type)) {
            setTitles("忘记密码");
            newPassword.setHint("请输入新密码");
            newPasswordAgain.setHint("请再次输入新密码");
            CommonUtil.editTextLength(newPassword, 24);
            CommonUtil.editTextLength(newPasswordAgain, 24);
        } else {
            setTitles("忘记贝币密码");
            newPassword.setHint("请输入6位新贝币密码");
            newPasswordAgain.setHint("请再次输入6位新贝币密码");
            CommonUtil.editTextLength(newPassword,6);
            CommonUtil.editTextLength(newPasswordAgain,6);
        }
        phone = (EditText) findViewById(R.id.activity_forgrt_password_input_phone);
        inputVerificationCode = (EditText) findViewById(R.id.activity_forgrt_password_input_verification_code);
        imageView = (ImageView) findViewById(R.id.activity_forgrt_password_input_verification_code_yes);
        complete = (Button) findViewById(R.id.activity_forgrt_password_complete);
        getVerificationCode = (TextView) findViewById(R.id.activity_forgrt_password_get_verification_code);
        getVerificationCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DateUtils.gephonetzhenze(phone.getText().toString())) {
                    if ("login".equals(type)) { //忘记密码
                        getCode();
                    } else {//忘记贝币密码
                        getCodes();
                    }
                } else {
                    CustomToast.getInstance(ForgetPasswordActivity.this).setMessage("手机号格式不对！");
                }
            }
        });
        DateUtils.phoneInput(phone, getVerificationCode);
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(phone.getText().toString()) || "".equals(inputVerificationCode.getText().toString()) || "".equals(newPassword.getText().toString()) || "".equals(newPasswordAgain.getText().toString())) {
                    CustomToast.getInstance(ForgetPasswordActivity.this).setMessage("电话，验证码，新密码不能为空");
                } else {
                    if (newPassword.getText().toString().equals(newPasswordAgain.getText().toString())) {
                        if ("login".equals(type)) { //忘记密码
                            forgetPassword();
                        } else {//忘记贝币密码
                            if (newPassword.getText().toString().length()!=6){
                                CustomToast.getInstance(ForgetPasswordActivity.this).setMessage("贝币密码只支持6位");
                            }else {
                                forgetPasswords();
                            }

                        }
                    } else {
                        CustomToast.getInstance(ForgetPasswordActivity.this).setMessage("两次密码不一致！");
                    }
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        MutualApplication.getRequestQueue().cancelAll("frogetPass37");
        MutualApplication.getRequestQueue().cancelAll("forget38");
    }

    /**
     * 忘记贝币密码接口
     */
    public void forgetPasswords() {
        ForgetPostBean forgetPostBean = new ForgetPostBean();
        forgetPostBean.setStoreid(UserInfo.getInstance().getUser(this).getStoreid());
        forgetPostBean.setAucode(inputVerificationCode.getText().toString());
        forgetPostBean.setStorephone(phone.getText().toString());
        forgetPostBean.setPaypassword(MD5.toMD5(newPassword.getText().toString().trim()));
        Http.forget(ForgetPasswordActivity.this, forgetPostBean, HttpUrl.forgetbeibiPassword, 1, "密码找回成功！");
    }

    /**
     * 验证码网络请求
     *
     * @return 返回服务端验证码
     */
    public void getCode() {
        CodePostBean codePostBean = new CodePostBean();
        codePostBean.setStorephone(phone.getText().toString());
        Http.forgetPass(ForgetPasswordActivity.this, codePostBean, HttpUrl.forget_code, 1, getVerificationCode, imageView, inputVerificationCode);
    }

    /**
     * 贝币验证码网络请求
     *
     * @return 返回服务端验证码
     */
    public void getCodes() {
        CodePostBean codePostBean = new CodePostBean();
        codePostBean.setStoreid(UserInfo.getInstance().getUser(this).getStoreid());
        codePostBean.setStorephone(phone.getText().toString());
        Http.forgetPass(ForgetPasswordActivity.this, codePostBean, HttpUrl.forgetbeibei_code, 2, getVerificationCode, imageView, inputVerificationCode);
    }

    /**
     * 忘记登录密码接口
     */
    public void forgetPassword() {
        ForgetPostBean forgetPostBean = new ForgetPostBean();
        forgetPostBean.setAucode(inputVerificationCode.getText().toString());
        forgetPostBean.setStorephone(phone.getText().toString());
        forgetPostBean.setStorepassword(MD5.toMD5(newPassword.getText().toString().trim()));
        Http.forget(ForgetPasswordActivity.this, forgetPostBean, HttpUrl.forgetPassword, 0, "密码找回成功！");
    }
}
