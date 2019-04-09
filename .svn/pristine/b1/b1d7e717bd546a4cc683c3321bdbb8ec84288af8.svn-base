package com.example.administrator.merchants.http.listener;

import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.Response;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.common.toast.CustomToastFinish;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.List;

/**
 * 作者：韩宇 on 2017/7/2 0002 10:37
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：评论
 */
public class EvaluaeListener implements Response.Listener<String> {
    private Context context;
    private ProgressDialog progressDialog;
    private List<File>files,fileList;
    public EvaluaeListener(Context context,ProgressDialog progressDialog,List<File>files,List<File>fileList){
        this.context=context;
        this.progressDialog=progressDialog;
        this.files=files;
        this.fileList=fileList;
    }
    @Override
    public void onResponse(String jsonObject) {
        try {
            JSONObject jsonObject1=new JSONObject(jsonObject);
            if (Status.status(jsonObject1)){
                //返回成功
                progressDialog.dismiss();
                CustomToastFinish customToastFinish = new CustomToastFinish(context);
                customToastFinish.setMessage("上传成功!");
                for (int i = 0; i < files.size(); i++) {
                    files.get(i).delete();
                }
                files.clear();
                for (int i = 0; i < fileList.size(); i++) {
                    fileList.get(i).delete();
                }
                fileList.clear();
                if (files.size() != 0) {
                    files.clear();
                }
            }else {
                //返回失败
                Status.fail(context,jsonObject1,progressDialog);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
