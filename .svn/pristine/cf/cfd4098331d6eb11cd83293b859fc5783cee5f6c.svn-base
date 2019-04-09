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

import com.example.administrator.merchants.http.show.RecruitmentManagementShowBean;
import com.example.administrator.merchants.R;
import com.example.administrator.merchants.adapter.RecruitmentManagementAdapter;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseActivity;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.post.DeleteRecruitmentPostBean;
import com.example.administrator.merchants.http.post.ListPostBean;
import com.example.administrator.merchants.common.views.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/7/30 0030 13:37
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：招聘管理列表
 */
public class RecruitmentManagementActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,
        RefreshLayout.OnLoadListener {
    private ListView listView;
    private List<RecruitmentManagementShowBean> list;
    private RecruitmentManagementAdapter adapter;
    private View header;
    private RefreshLayout swipeLayout;
    private View footView;
    public static int s = 0;//是否加载

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruitment_management);
        setTitles("招聘管理");
        list = new ArrayList<>();
        TextView textView = (TextView) findViewById(R.id.add);
        footView = getLayoutInflater().inflate(R.layout.no_more_date_footview, null);
        listView = (ListView) findViewById(R.id.activity_recruitment_management_listView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(RecruitmentManagementActivity.this, AddRecruitmentActivity.class);
                startActivity(intent);
            }
        });
        adapter = new RecruitmentManagementAdapter(RecruitmentManagementActivity.this, list);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RecruitmentManagementActivity.this);
                builder.setMessage("确认删除吗？");
                builder.setTitle("提示");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        deleteRecruitment(list.get(position - 1).getId(), position);
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

    @Override
    protected void onResume() {
        super.onResume();
        onRefresh();//刷新
    }

    @Override
    protected void onStop() {
        super.onStop();
        MutualApplication.getRequestQueue().cancelAll("deleteRecruitment76");
        MutualApplication.getRequestQueue().cancelAll("recruitmentList77");
    }

    /***
     * 获取招聘信息列表请求
     */
    public void getList(String offset, String limit, int type) {
        ListPostBean listPostBean = new ListPostBean();
        listPostBean.setStoreid(UserInfo.getInstance().getUser(this).getStoreid());
        listPostBean.setOffset(offset);
        listPostBean.setLimit(limit);
        Http.recruitmentList(this, listPostBean, type, list, listView, adapter, swipeLayout, footView);
    }

    /**
     * 删除招聘信息
     *
     * @param recrid
     * @param position
     */
    public void deleteRecruitment(String recrid, int position) {
        DeleteRecruitmentPostBean deleteRecruitmentPostBean = new DeleteRecruitmentPostBean();
        deleteRecruitmentPostBean.setRecrid(recrid);
        Http.deleteRecruitment(RecruitmentManagementActivity.this, deleteRecruitmentPostBean, list, position, adapter);
    }

    @Override
    public void onLoad() {
        swipeLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (s == 0 || s == 1) {
                    s = 2;
                    getList(list.size() + "", "15", 1);
                }
            }
        }, 1000);
    }

    @Override
    public void onRefresh() {
        swipeLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                getList("0", "15", 0);
            }
        }, 1000);
    }
}
