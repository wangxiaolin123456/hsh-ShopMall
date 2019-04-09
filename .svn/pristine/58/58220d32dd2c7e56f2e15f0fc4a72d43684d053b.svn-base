package com.example.administrator.merchants.http.listener;

import android.content.Context;

import com.android.volley.Response;
import com.example.administrator.merchants.activity.CommodityManagementListActivity;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.common.toast.CustomToast;

import org.json.JSONObject;

/**
 * 作者：韩宇 on 2017/8/7 0007 14:33
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：删除商品
 */
public class DeleteMerchandiseListener implements Response.Listener<JSONObject>{
    private Context context;
    private String size;
    private int type;
    public DeleteMerchandiseListener(Context context,String size,int type){
        this.context=context;
        this.size=size;
        this.type=type;
    }
    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)){
            //返回成功
            if (type==1){
                CustomToast.getInstance(context).setMessage("操作成功!");
            }
            ((CommodityManagementListActivity) context).getGoodsList("0", size, 2);//跳转别的activity返回刷新不回顶部
        }else {
            //返回失败
            Status.fail(context,jsonObject);
        }
    }
}
