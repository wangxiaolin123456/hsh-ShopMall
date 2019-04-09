package com.example.administrator.merchants.common;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.http.show.BeiBiDialogShowBean;

/**
 * 作者：韩宇 on 2017/6/22 0022 10:25
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：贝币支付和设置密码对话框初始化
 */
public class BeiBiDialog {
    /**
     * 贝币支付和设置密码对话框初始化
     *
     * @param context
     * @return 返回一个确认按钮和dialog还有一个输入密码的输入框
     */
    public static BeiBiDialogShowBean BeiBei(final Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        final Dialog alertDialog = new Dialog(context, R.style.Dialog);
        alertDialog.show();
        Window window = alertDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = width * 3 / 4; // 宽度
        lp.height = height / 4; // 高度
        window.setAttributes(lp);
        window.setContentView(R.layout.dialog_payment_password);
        EditText password = (EditText) window.findViewById(R.id.input_password_editText);
        CommonUtil.editTextLength(password,6);
        Button cancel = (Button) window.findViewById(R.id.cancel_pay);
        Button confirm = (Button) window.findViewById(R.id.confirm_pay);
        password.setFocusable(true);
        password.setFocusableInTouchMode(true);
        password.requestFocus();
        ((Activity) context).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        InputMethodManager imm = (InputMethodManager)
                context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(password, 0); //显示软键盘
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS); //显示软键盘
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });
        BeiBiDialogShowBean beiBiDialogShowBean = new BeiBiDialogShowBean();
        beiBiDialogShowBean.setConfirm(confirm);
        beiBiDialogShowBean.setDialog(alertDialog);
        beiBiDialogShowBean.setPassword(password);
        return beiBiDialogShowBean;
    }
}
