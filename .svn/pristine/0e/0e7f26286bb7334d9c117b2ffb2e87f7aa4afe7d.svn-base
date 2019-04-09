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

import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.http.post.MessageDetailPostBean;
import com.example.administrator.merchants.http.post.StoreIdPostBean;
import com.example.administrator.merchants.http.show.MessageShowBean;
import com.example.administrator.merchants.R;
import com.example.administrator.merchants.adapter.MyMessageAdapter;
import com.example.administrator.merchants.base.BaseActivity;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.post.ListPostBean;
import com.example.administrator.merchants.common.views.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/7/16 0016 09:41
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：消息列表
 */
public class MyMessageListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,
        RefreshLayout.OnLoadListener {
    private List<MessageShowBean> list;
    private ListView listView;
    private MyMessageAdapter myMessageAdapter;
    private View header;
    private RefreshLayout swipeLayout;
    private View footView;
    public static int s = 0;//是否加载
    private boolean staus = false;
    private TextView clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);
        setTitles("消息");
        list = new ArrayList<>();
        clear = (TextView) findViewById(R.id.add);
        clear.setText("清空已读消息");
        clear.setVisibility(View.VISIBLE);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MyMessageListActivity.this)
                        .setMessage("是否清空已读消息？\n")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                clearMessage();
                            }
                        })
                        .setNegativeButton("否", null)
                        .show();
            }
        });
        myMessageAdapter = new MyMessageAdapter(MyMessageListActivity.this, list);
        footView = getLayoutInflater().inflate(R.layout.no_more_date_footview, null);
        listView = (ListView) findViewById(R.id.activity_address_list_listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position != list.size() + 1) {
                    Intent intent = new Intent();
                    intent.putExtra("messageid", list.get(position - 1).getMessageId());
                    intent.setClass(MyMessageListActivity.this, MessageDetailActivity.class);
                    startActivity(intent);
                    staus = true;
                }

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                if (position != list.size() + 1) {
                    new AlertDialog.Builder(MyMessageListActivity.this)
                            .setMessage("是否删除消息？\n")
                            .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    deleteMessage(list.get(position - 1).getMessageId(), position);
                                }
                            })
                            .setNegativeButton("否", null)
                            .show();
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
        onRefresh();
    }

    /**
     * 清空消息
     */
    public void clearMessage() {
        StoreIdPostBean storeIdPostBean = new StoreIdPostBean();
        storeIdPostBean.setStoreid(UserInfo.getInstance().getUser(MyMessageListActivity.this).getStoreid());
        Http.clearMessage(MyMessageListActivity.this, storeIdPostBean, list, myMessageAdapter);
    }

    /**
     * 删除
     *
     * @param newsId
     * @param position
     */
    public void deleteMessage(String newsId, int position) {
        MessageDetailPostBean messageDetailPostBean = new MessageDetailPostBean();
        messageDetailPostBean.setNewsid(newsId);
        Http.deleteMessage(MyMessageListActivity.this, messageDetailPostBean, list, myMessageAdapter, position);
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
     * 消息列表
     *
     * @param offset
     * @param limit
     * @param type
     */
    public void list(String offset, String limit, int type) {
        ListPostBean listPostBean = new ListPostBean();
        listPostBean.setStoreid(UserInfo.getInstance().getUser(this).getStoreid());
        listPostBean.setOffset(offset);
        listPostBean.setLimit(limit);
        Http.myMessageList(MyMessageListActivity.this, listPostBean, type, list, listView, myMessageAdapter, swipeLayout, footView);
    }

    @Override
    public void onLoad() {
        swipeLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (s == 0 || s == 1) {
                    s = 2;
                    list(list.size() + "", "15", 1); //toDO 调用请求
                }
            }
        }, 1000);
    }

    @Override
    public void onRefresh() {
        swipeLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                list("0", "15", 0);
            }
        }, 1000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (staus) {
            list("0", list.size() + "", 2);
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        MutualApplication.getRequestQueue().cancelAll("clearMessage74");
        MutualApplication.getRequestQueue().cancelAll("deleteMessage75");
        MutualApplication.getRequestQueue().cancelAll("myMessageList52");
    }
}
