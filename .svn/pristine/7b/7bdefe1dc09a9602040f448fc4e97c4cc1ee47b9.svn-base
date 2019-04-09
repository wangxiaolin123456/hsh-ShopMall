package com.example.administrator.merchants.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.adapter.OriginalSearchGridAdapter;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseActivity;
import com.example.administrator.merchants.common.CommonUtil;
import com.example.administrator.merchants.common.SharedPreferenceUtil;
import com.example.administrator.merchants.common.toast.CustomToast;
import com.example.administrator.merchants.common.views.MyGridView;
import com.example.administrator.merchants.http.Http;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/6/23 0023 14:39
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：原产地搜索
 */
public class OriginalSearchActivity extends BaseActivity implements View.OnClickListener {
    private MyGridView hotGrid;
    private MyGridView historyListView;
    private List<String> listHot;//热搜的接收集
    private EditText editText;
    private TextView history_info;
    private LinearLayout backLinear;
    private OriginalSearchGridAdapter originalHomeHistoryListAdapter;
    private String key;//获取edittext中的值
    private ImageView clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_original);
        backLinear = (LinearLayout) findViewById(R.id.linfinish);
        backLinear.setOnClickListener(this);
        hotGrid = (MyGridView) findViewById(R.id.gv_hot_search);
        historyListView = (MyGridView) findViewById(R.id.lv_search_history);
        editText = (EditText) findViewById(R.id.editText_search);
        clear = (ImageView) findViewById(R.id.textView_delete);
        clear.setOnClickListener(this);//清除记录
        findViewById(R.id.search).setOnClickListener(this);//右上角搜索二字
        history_info = (TextView) findViewById(R.id.history_info);
        originalHomeHistoryListAdapter = new OriginalSearchGridAdapter(this);
        listHot = new ArrayList<>();
        Http.toGetHotSearch(OriginalSearchActivity.this, listHot, hotGrid, historyListView, originalHomeHistoryListAdapter);
        hotGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                history_info.setVisibility(View.VISIBLE);
                search(listHot.get(position));//toDo
                key = listHot.get(position);
                search(key);
                Intent intent = new Intent();
                intent.putExtra("searchkey", key);
                intent.setClass(OriginalSearchActivity.this, OriginalSearchResultActivity.class);
                startActivity(intent);

            }
        });
        historyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("searchkey", (String) originalHomeHistoryListAdapter.getItem(position));
                intent.setClass(OriginalSearchActivity.this, OriginalSearchResultActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 这个是存下来的方法  跟搜索一点关系没有
     *
     * @param key
     */
    private void search(String key) {
        SharedPreferenceUtil.getInstence().addSearchHistory(key);
        originalHomeHistoryListAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStop() {
        MutualApplication.getRequestQueue().cancelAll("toGetHotSearch63");
        super.onStop();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linfinish:
                finish();
                break;
            case R.id.search:
                history_info.setVisibility(View.VISIBLE);
                key = editText.getText().toString().trim();
                if (TextUtils.isEmpty(key)) {
                    CustomToast.getInstance(OriginalSearchActivity.this).setMessage("输入商品");
                } else {
                    if (CommonUtil.compileExChar(editText.getText().toString())){
                        CustomToast.getInstance(this).setMessage("不能含有特殊字符'和#！");
                    }else {
                        search(key);
                        Intent intent = new Intent();
                        intent.putExtra("searchkey", key);
                        intent.setClass(OriginalSearchActivity.this, OriginalSearchResultActivity.class);
                        startActivity(intent);
                    }

                }
                break;
            case R.id.textView_delete:
                SharedPreferenceUtil.getInstence().deleteSearchHistory();
                history_info.setVisibility(View.GONE);
                originalHomeHistoryListAdapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (SharedPreferenceUtil.getInstence().getSearchHistory().size()>0){
            history_info.setVisibility(View.VISIBLE);
        }else {
            history_info.setVisibility(View.GONE);
        }

    }
}
