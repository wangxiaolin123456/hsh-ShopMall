package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.Response;
import com.example.administrator.merchants.adapter.ShoppingCarAdapter;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.http.show.TempShoppingCarShowBean;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.http.show.ShoppingCarShowBean;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.List;

/**
 * 作者：韩宇 on 2017/8/13 0013 13:37
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：购物车数量变更
 */
public class ChangeCarCountListener implements Response.Listener<JSONObject> {
    private Context context;
    private int x;
    private List<ShoppingCarShowBean> list;
    private int position;
    private ShoppingCarAdapter shoppingCarAdapter;
    private List<TempShoppingCarShowBean> tempShoppingCarShowBeans;
    private PopupWindow popupWindow;
    private TextView textViewTotal;

    public ChangeCarCountListener(Context context, int x, List<ShoppingCarShowBean> list, int position
            , List<TempShoppingCarShowBean> tempShoppingCarShowBeans, ShoppingCarAdapter shoppingCarAdapter, PopupWindow popupWindow
            , TextView textViewTotal) {
        this.context = context;
        this.x = x;
        this.list = list;
        this.position = position;
        this.tempShoppingCarShowBeans = tempShoppingCarShowBeans;
        this.shoppingCarAdapter = shoppingCarAdapter;
        this.popupWindow = popupWindow;
        this.textViewTotal = textViewTotal;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //返回成功
            list.get(position).setMerqty(x);
            tempShoppingCarShowBeans.get(position).count = String.valueOf(x);//toDO 这里同时给临时数量改变
            shoppingCarAdapter.notifyDataSetChanged();
            if (tempShoppingCarShowBeans.get(position).choosed == true) {//如果按钮被选中了  总价钱需要再算
                textViewTotal.setText(String.valueOf(getTotalPrice()));
            }
            popupWindow.dismiss();
        } else {
            //返回失败
            popupWindow.dismiss();
            Status.fail(context, jsonObject);
        }
    }

    /**
     * 获得购物车被选中的累积总价钱
     *
     * @return
     */
    public String getTotalPrice() {
        double total = 0.0;
        DecimalFormat df = new DecimalFormat("0.00");
        String totalPrice = "0.00";
        for (int i = 0; i < list.size(); i++) {
            if (tempShoppingCarShowBeans.get(i).choosed == true) {
                total = total + (list.get(i).getMerqty()) * (list.get(i).getSaleprice());
                totalPrice = df.format(total);
            }
        }
        MutualApplication.totalConfirm = "0.00";
        MutualApplication.totalConfirm = totalPrice;
        return totalPrice;
    }
}
