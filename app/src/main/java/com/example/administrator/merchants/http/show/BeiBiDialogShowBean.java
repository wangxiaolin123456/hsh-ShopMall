package com.example.administrator.merchants.http.show;

import android.app.Dialog;
import android.widget.Button;
import android.widget.EditText;

/**
 * 作者：韩宇 on 2017/6/22 0022 10:37
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：贝币支付show
 */
public class BeiBiDialogShowBean {
    private Button confirm;//确认按钮
    private Dialog dialog;
    private EditText password;

    public Button getConfirm() {
        return confirm;
    }

    public void setConfirm(Button confirm) {
        this.confirm = confirm;
    }

    public Dialog getDialog() {
        return dialog;
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public void setPassword(EditText password) {
        this.password = password;
    }

    public EditText getPassword() {
        return password;
    }
}
