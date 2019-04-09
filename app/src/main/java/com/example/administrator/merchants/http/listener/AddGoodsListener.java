package com.example.administrator.merchants.http.listener;

import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.Response;
import com.example.administrator.merchants.common.CommonUtil;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.common.toast.CustomToastFinish;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.List;

/**
 * 作者：韩宇 on 2017/7/6 0006 15:14
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：添加商品
 */
public class AddGoodsListener implements Response.Listener<String> {
    private Context context;
    private ProgressDialog progressDialog;
    private List<File> toFileList;
    private String text;

    public AddGoodsListener(Context context, ProgressDialog progressDialog, List<File> toFileList,String text) {
        this.context = context;
        this.progressDialog = progressDialog;
        this.toFileList = toFileList;
        this.text=text;
    }

    @Override
    public void onResponse(String jsonObject) {
        try {
            JSONObject jsonObject1 = new JSONObject(jsonObject);
            if (Status.status(jsonObject1)) {
                //返回成功
                progressDialog.dismiss();
                CustomToastFinish customToastFinish = new CustomToastFinish(context);
                customToastFinish.setMessage(text);
                for (int i = 0; i < toFileList.size(); i++) {
                    toFileList.get(i).delete();
                }
                toFileList.clear();
                CommonUtil.clear();
            } else {
                //返回失败
                Status.fail(context, jsonObject1, progressDialog);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
