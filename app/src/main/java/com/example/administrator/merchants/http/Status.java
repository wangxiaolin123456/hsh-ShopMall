package com.example.administrator.merchants.http;

import android.app.ProgressDialog;
import android.content.Context;

import com.example.administrator.merchants.common.toast.CustomToast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：返回请求是否成功
 */
public class Status {
    /**
     * 返回数据是成功还是失败
     * @param jsonObject
     * @return
     */
    public static boolean status(JSONObject jsonObject) {
        try {
            if ("true".equals(jsonObject
                    .getString("success"))) {
                return true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 普通失败后的处理
     * @param context
     * @param jsonObject
     * @return
     */
    public static boolean fail(Context context, JSONObject jsonObject) {
        try {
            CustomToast.getInstance(context).setMessage(jsonObject.getString("message"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 带progressDialog的失败后处理
     * @param context
     * @param jsonObject
     * @param progressDialog
     * @return
     */
    public static boolean fail(Context context, JSONObject jsonObject,ProgressDialog progressDialog) {
        try {
            progressDialog.dismiss();
            CustomToast.getInstance(context).setMessage(jsonObject.getString("message"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
}
