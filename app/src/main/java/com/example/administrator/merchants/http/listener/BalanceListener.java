package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Response;
import com.example.administrator.merchants.common.BeiBiDialog;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.common.MD5;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.http.post.ConfirmBeiBiPasswordPostBean;
import com.example.administrator.merchants.http.show.BeiBiDialogShowBean;
import com.example.administrator.merchants.common.toast.CustomToast;
import com.example.administrator.merchants.utils.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者：韩宇 on 2017/6/22 0022 09:13
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：获取余额网络处理
 */
public class BalanceListener implements Response.Listener<JSONObject> {
    private Context context;
    private TextView balance;
    private int type;
    private EditText beibi;
    private EditText phone;
    private BeiBiDialogShowBean beiBiDialogShowBean;
    private String friendsType;
    private String beibiType;//贝币赠与区分 金贝币还是银贝币
    private String stu;//区分贝币赠与的类型
    /***
     * 余额提取中的获取贝币余额
     *
     * @param context
     * @param balance
     * @param type
     */
    public BalanceListener(Context context, TextView balance, int type) {
        this.context = context;
        this.balance = balance;
        this.type = type;
    }

    /***
     * 贝币赠与里的获取贝余额
     *
     * @param context
     * @param beibi
     * @param type
     * @param phone
     * @param friendsType
     */
    public BalanceListener(Context context, EditText beibi, int type, EditText phone, String friendsType,
                           String beibiType,
                           String stu) {
        this.context = context;
        this.beibi = beibi;
        this.type = type;
        this.phone = phone;
        this.friendsType = friendsType;
        this.beibiType = beibiType;
        this.stu = stu;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        LogUtil.i("贝币余额"+jsonObject.toString());
        if (Status.status(jsonObject)) {
            //返回成功
            if (type == 0) {
                try {
                    String beibiamt = jsonObject.getString("beibiamt");  //贝币余额
                    balance.setText(beibiamt);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (type == 1) {
                if (stu.equals("j")) {
                    LogUtil.i("贝币赠与金贝币判断余额");
                    try {
                        String beibiamt = jsonObject.getString("beibiamt");
                        Double d = Double.parseDouble(beibiamt);
                        if (d < Double.parseDouble(beibi.getText().toString())) {
                            CustomToast.getInstance(context).setMessage("贝币余额不足！");
                        } else {
                            beiBeiBiPayDialog();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else if (stu.equals("y")) {
                    LogUtil.i("贝币赠与银贝币判断余额" + jsonObject.toString());
                    try {
                        JSONObject jsonObject1 = jsonObject.getJSONObject("storeinfo");
                        double ybeibiYue =jsonObject1.getDouble("liftsilver");//银贝币余额
                        if (ybeibiYue < Double.parseDouble(beibi.getText().toString())) {
                            CustomToast.getInstance(context).setMessage("银贝币余额不足！");
                        } else {
                            beiBeiBiPayDialog();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        } else {
            //返回失败
            Status.fail(context, jsonObject);
        }
    }
    /**
     * 贝币支付弹出的dialog
     */
    private void beiBeiBiPayDialog() {
        beiBiDialogShowBean = BeiBiDialog.BeiBei(context);
        beiBiDialogShowBean.getConfirm().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beiBiConfirmPassword(beiBiDialogShowBean.getPassword());//密码确认 请求网络
                beiBiDialogShowBean.getDialog().dismiss();
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });
    }

    /**
     * 贝币密码确认
     */
    private void beiBiConfirmPassword(EditText password) {
        ConfirmBeiBiPasswordPostBean confirmBeiBiPasswordPostBean = new ConfirmBeiBiPasswordPostBean();
        confirmBeiBiPasswordPostBean.setStoreid(UserInfo.getInstance().getUser(context).getStoreid());
        confirmBeiBiPasswordPostBean.setPaypassword(MD5.toMD5(password.getText().toString()));
        Http.confirmGiveBeiBiPassword(context, confirmBeiBiPasswordPostBean, beibi, phone, friendsType
                , beibiType);
    }

}
