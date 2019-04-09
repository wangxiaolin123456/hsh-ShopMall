package com.example.administrator.merchants.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.adapter.ShoppingCarAdapter;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseActivity;
import com.example.administrator.merchants.http.show.TempShoppingCarShowBean;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.post.StoreIdPostBean;
import com.example.administrator.merchants.http.show.ShoppingCarShowBean;
import com.example.administrator.merchants.common.toast.CustomToast;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/8/4 0004 09:18
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：购物车列表
 */
public class ShoppingCarActivity extends BaseActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, View.OnClickListener {
    public ListView listView;
    private List<ShoppingCarShowBean> shoppingCarList;
    private TextView goToAccount;
    private ShoppingCarAdapter shoppingCarAdapter;
    private CheckBox checkAll;
    private TextView textViewTotal;
    private LinearLayout linearCheckAll;
    public List<TempShoppingCarShowBean> tempShoppingCarShowBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_car);
        setTitles("购物车");
        listView = (ListView) findViewById(R.id.shoppingcar_list);
        shoppingCarList = new ArrayList<>();
        checkAll = (CheckBox) findViewById(R.id.item_shopping_check_all);
        textViewTotal = (TextView) findViewById(R.id.zongqianshu);
        goToAccount = (TextView) findViewById(R.id.gotoaccount);
        goToAccount.setOnClickListener(this);
        linearCheckAll = (LinearLayout) findViewById(R.id.linear_checkall);
        tempShoppingCarShowBeans = new ArrayList<>();
        shoppingCarAdapter = new ShoppingCarAdapter(ShoppingCarActivity.this, shoppingCarList, checkAll, textViewTotal, tempShoppingCarShowBeans);
        listView.setOnItemLongClickListener(this);
        listView.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        shoppingCarList.clear();
        tempShoppingCarShowBeans.clear();
        toShoppingCarItemData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        MutualApplication.getRequestQueue().cancelAll("shopCarList82");
        MutualApplication.getRequestQueue().cancelAll("changeCarCountToServer91");
        MutualApplication.getRequestQueue().cancelAll("changeCarCountToServer92");
    }

    /**
     * 购物车列表
     */
    public void toShoppingCarItemData() {
        StoreIdPostBean storeIdPostBean = new StoreIdPostBean();
        storeIdPostBean.setStoreid(UserInfo.getInstance().getUser(this).getStoreid());
        Http.shopCarList(this, storeIdPostBean, shoppingCarList, tempShoppingCarShowBeans, linearCheckAll, listView, shoppingCarAdapter, checkAll, textViewTotal);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.putExtra("merid", shoppingCarList.get(position).getMerid());
        intent.setClass(ShoppingCarActivity.this, GoodsDetailsActivity.class);//CommodityDetailsActivity
        this.startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确认删除吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                shoppingCarAdapter.changeCarCountToServer(position, "0");//服务器上直接删除
                shoppingCarList.remove(position);//集合里面也删除了
                tempShoppingCarShowBeans.remove(position);// ShoppingCarAdapter.
                shoppingCarAdapter.notifyDataSetChanged();
                textViewTotal.setText(shoppingCarAdapter.getTotalPrice());//toDo  重算一遍价钱   然后赋值给textview
                dialog.dismiss();
                if (!(shoppingCarList.size() == 0)) {
                    linearCheckAll.setVisibility(View.VISIBLE);
                } else {
                    linearCheckAll.setVisibility(View.INVISIBLE);
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override


            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }

        });
        builder.create().show();
        return true;
    }

    /**
     * 提取选择要的商品
     */
    public void chooseList() {
        MutualApplication.chooseList.clear();
        for (ShoppingCarShowBean s : shoppingCarList) {
            if (s.isJudge()) {
                MutualApplication.chooseList.add(s);
            }
        }

    }

    @Override
    public void onClick(View v) {
        if (!(textViewTotal.getText().toString()).equals("0.00")) {
            chooseList();
            startActivity(new Intent(ShoppingCarActivity.this, ConfirmOrderActivity.class));
            finish();
        } else {
            CustomToast.getInstance(ShoppingCarActivity.this).setMessage("对不起，您还没有选择商品");
        }
    }
}
