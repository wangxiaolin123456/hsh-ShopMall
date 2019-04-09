package com.example.administrator.merchants.http;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Button;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.administrator.merchants.common.toast.CustomToast;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：网络错误
 */
public class ErrorListener implements Response.ErrorListener {
    private Context context;
    private ProgressDialog progressDialog;
    private int type;
    private Button button;

    public ErrorListener(Context context, int type) {
        this.context = context;
        this.type = type;
    }

    public ErrorListener(Context context, ProgressDialog progressDialog, int type) {
        this.context = context;
        this.progressDialog = progressDialog;
        this.type = type;
    }

    public ErrorListener(Context context, Button button, int type) {
        this.context = context;
        this.button = button;
        this.type = type;
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        if (type == 0) {
            progressDialog.dismiss();
            CustomToast.getInstance(context).setMessage("网络不好，请查看网络！");
        } else if (type == 1) {
            CustomToast.getInstance(context).setMessage("网络不好，请查看网络！");
        } else if (type == 2) {
            button.setClickable(true);
            CustomToast.getInstance(context).setMessage("网络不好，请查看网络！");
        }

    }
}
