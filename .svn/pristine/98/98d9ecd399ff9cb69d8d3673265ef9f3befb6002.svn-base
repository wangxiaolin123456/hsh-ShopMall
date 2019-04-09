package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.content.DialogInterface;

import com.android.volley.Response;
import com.example.administrator.merchants.http.show.BankShowBean;
import com.example.administrator.merchants.adapter.BankAdapter;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.common.toast.CustomToast;

import org.json.JSONObject;

import java.util.List;

/**
 * 作者：韩宇 on 2017/6/21 0021 14:35
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：删除银行卡处理
 */
public class DeleteBankCardListener implements Response.Listener<JSONObject> {
    private Context context;
    private List<BankShowBean> list;
    private int position;
    private BankAdapter bankAdapter;
    private DialogInterface dialogInterface;

    public DeleteBankCardListener(Context context, List<BankShowBean> list, int position, BankAdapter bankAdapter, DialogInterface dialogInterface) {
        this.context = context;
        this.list = list;
        this.position = position;
        this.bankAdapter = bankAdapter;
        this.dialogInterface = dialogInterface;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //请求返回成功
            list.remove(position - 1);
            if (list.size() != 0) {
                list.get(0).setStu("1");
            }
            bankAdapter.notifyDataSetChanged();
            dialogInterface.dismiss();
            CustomToast.getInstance(context).setMessage("删除成功！");

        } else {
            //请求返回失败处理
            Status.fail(context, jsonObject);
        }

    }
}
