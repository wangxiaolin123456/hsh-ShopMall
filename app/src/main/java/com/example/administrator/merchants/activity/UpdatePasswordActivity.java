package com.example.administrator.merchants.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseActivity;
import com.example.administrator.merchants.common.CommonUtil;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.common.MD5;
import com.example.administrator.merchants.http.post.UpdatePasswordPostBean;
import com.example.administrator.merchants.common.toast.CustomToast;

/**
 * 作者：韩宇 on 2017/7/20 0020 14:33
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：修改登录密码和贝币支付密码
 */
public class UpdatePasswordActivity extends BaseActivity {
    private EditText old, newPassword, newPasswordAgain;
    private Button yes;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        type = getIntent().getStringExtra("type");
        old = (EditText) findViewById(R.id.activity_update_password_old);
        newPassword = (EditText) findViewById(R.id.activity_update_password_new);
        newPasswordAgain = (EditText) findViewById(R.id.activity_update_password_new_again);
        if ("login".equals(type)) {
            setTitles("修改登录密码");
            old.setHint("请输入原来密码");
            newPassword.setHint("请输入新密码");
            newPasswordAgain.setHint("请在次输入新密码");
            CommonUtil.editTextLength(newPassword, 24);
            CommonUtil.editTextLength(newPasswordAgain, 24);
            CommonUtil.editTextLength(old, 24);
        } else {
            setTitles("修改贝币密码");
            old.setHint("请输入6位原贝币密码");
            newPassword.setHint("请输入6位新贝币密码");
            newPasswordAgain.setHint("请在次输入6位新贝币密码");
            CommonUtil.editTextLength(newPassword, 6);
            CommonUtil.editTextLength(newPasswordAgain,6);
            CommonUtil.editTextLength(old, 6);
        }
        yes = (Button) findViewById(R.id.activity_update_password_yes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(old.getText().toString()) || old.getText().toString() == null) {
                    CustomToast.getInstance(UpdatePasswordActivity.this).setMessage("原密码不能为空！");
                } else if ("".equals(newPassword.getText().toString()) || newPassword.getText().toString() == null) {
                    CustomToast.getInstance(UpdatePasswordActivity.this).setMessage("新密码不能为空！");
                } else if ("".equals(newPasswordAgain.getText().toString()) || newPasswordAgain.getText().toString() == null) {
                    CustomToast.getInstance(UpdatePasswordActivity.this).setMessage("确认密码不能为空！");
                } else if (!newPassword.getText().toString().equals(newPasswordAgain.getText().toString())) {
                    CustomToast.getInstance(UpdatePasswordActivity.this).setMessage("两次输入新密码不一致！");
                } else if (old.getText().toString().equals(newPassword.getText().toString())) {
                    CustomToast.getInstance(UpdatePasswordActivity.this).setMessage("新密码不能与原密码相同！");
                } else {
                    if ("login".equals(type)) {
                        getPassword();
                    } else {
                        getBeiBiPassword();
                    }
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        MutualApplication.getRequestQueue().cancelAll("updateLoginPassword55");
        MutualApplication.getRequestQueue().cancelAll("updateBeiBiPassword56");
    }

    /**
     * 网络请求修改商家登录密码
     */
    public void getPassword() {
        UpdatePasswordPostBean updatePasswordPostBean = new UpdatePasswordPostBean();
        updatePasswordPostBean.setStorepasswordnew(MD5.toMD5(newPassword.getText().toString().trim()));
        updatePasswordPostBean.setStoreid(UserInfo.getInstance().getUser(this).getStoreid());
        updatePasswordPostBean.setStorepasswordold(MD5.toMD5(old.getText().toString().trim()));
        Http.updateLoginPassword(UpdatePasswordActivity.this, updatePasswordPostBean);
    }

    /**
     * 网络请求修改商家贝币密码
     */
    public void getBeiBiPassword() {
        UpdatePasswordPostBean updatePasswordPostBean = new UpdatePasswordPostBean();
        updatePasswordPostBean.setPaypasswordnew(MD5.toMD5(newPassword.getText().toString().trim()));
        updatePasswordPostBean.setStoreid(UserInfo.getInstance().getUser(this).getStoreid());
        updatePasswordPostBean.setPaypasswordold(MD5.toMD5(old.getText().toString().trim()));
        Http.updateBeiBiPassword(UpdatePasswordActivity.this, updatePasswordPostBean);
    }
}