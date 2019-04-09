package com.example.administrator.merchants.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.adapter.BankAdapter;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseActivity;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.post.DefaultBankCardPostBean;
import com.example.administrator.merchants.http.post.DeleteBankCardPostBean;
import com.example.administrator.merchants.http.post.ListPostBean;
import com.example.administrator.merchants.http.show.BankShowBean;
import com.example.administrator.merchants.common.views.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：银行卡列表
 */
public class BankCardListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,
        RefreshLayout.OnLoadListener {
    private List<BankShowBean> list;
    private ListView listView;
    private BankAdapter bankAdapter;
    private View header;
    private RefreshLayout swipeLayout;
    private View footView;
    public static int s = 0;//是否加载

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);
        setTitles("银行卡");
        footView = getLayoutInflater().inflate(R.layout.no_more_date_footview, null);
        list = new ArrayList<>();
        TextView textView = (TextView) findViewById(R.id.add);
        listView = (ListView) findViewById(R.id.activity_address_list_listView);
        textView.setText("新建银行卡");
        textView.setVisibility(View.VISIBLE);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("type", "add");
                intent.setClass(BankCardListActivity.this, BankCardActivity.class);
                startActivity(intent);
            }
        });
        bankAdapter = new BankAdapter(BankCardListActivity.this, list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position <= list.size()) {
                    defaultBank(list.get(position - 1).getBankid(), position);
                }


            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                if (position <= list.size()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(BankCardListActivity.this);
                    builder.setMessage("确认删除吗？");
                    builder.setTitle("提示");
                    builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            deleteBank(list.get(position - 1).getBankid(), position, dialog);

                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.create().show();
                }

                return true;
            }
        });
        initView();//初始化布局
        setListener();//设置监听
        swipeLayout.post(new Thread(new Runnable() {
            @Override
            public void run() {
                swipeLayout.setRefreshing(true);
            }
        }));

    }

    /**
     * 初始化布局
     */
    @SuppressLint({"InlinedApi", "InflateParams"})
    private void initView() {
        header = getLayoutInflater().inflate(R.layout.loding_header, null);
        swipeLayout = (RefreshLayout) findViewById(R.id.swipe_container);
        listView.addHeaderView(header);
    }

    /**
     * 设置监听
     */
    private void setListener() {
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setOnLoadListener(this);
    }

    /**
     * 银行卡列表
     *
     * @param offset
     * @param limit
     * @param type
     */
    public void getBankList(String offset, String limit, int type) {
        ListPostBean listPostBean = new ListPostBean();
        listPostBean.setStoreid(UserInfo.getInstance().getUser(this).getStoreid());
        listPostBean.setOffset(offset);
        listPostBean.setLimit(limit);
        Http.bankList(BankCardListActivity.this, listPostBean, list, type, listView, bankAdapter, swipeLayout, footView);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MutualApplication.getRequestQueue().cancelAll("accountStatementDetail1");
        MutualApplication.getRequestQueue().cancelAll("defaultBank12");
        MutualApplication.getRequestQueue().cancelAll("delectBank13");
    }

    /**
     * 设置缺省银行卡
     *
     * @param cardId
     * @param position
     */
    public void defaultBank(String cardId, int position) {
        DefaultBankCardPostBean defaultBankCardPostBean = new DefaultBankCardPostBean();
        defaultBankCardPostBean.setCardid(cardId);
        defaultBankCardPostBean.setStoreid(UserInfo.getInstance().getUser(this).getStoreid());
        Http.defaultBank(BankCardListActivity.this, defaultBankCardPostBean, list, position, bankAdapter);
    }

    /**
     * 删除银行卡
     *
     * @param cardid
     * @param position
     * @param dialogInterface
     */
    public void deleteBank(String cardid, int position, DialogInterface dialogInterface) {
        DeleteBankCardPostBean deleteBankCardPostBean = new DeleteBankCardPostBean();
        deleteBankCardPostBean.setCardid(cardid);
        Http.delectBank(BankCardListActivity.this, deleteBankCardPostBean, list, position, bankAdapter, dialogInterface);
    }

    @Override
    protected void onResume() {
        super.onResume();
        onRefresh();//刷新
    }

    /**
     * 加载
     */
    @Override
    public void onLoad() {
        swipeLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (s == 0 || s == 1) {
                    s = 2;
                    getBankList(list.size() + "", "15", 1); //toDO 调用请求
                }
            }
        }, 1000);
    }

    /**
     * 刷新
     */
    @Override
    public void onRefresh() {
        swipeLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                getBankList("0", "15", 0);
            }
        }, 1000);
    }
}
