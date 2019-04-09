package com.example.administrator.merchants.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseActivity;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.post.MessageDetailPostBean;

/**
 * 作者：韩宇 on 2017/6/23 0023 14:39
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：消息详情
 */
public class MessageDetailActivity extends BaseActivity {
    private TextView textView;
    private String messageid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        messageid=getIntent().getStringExtra("messageid");
        setTitles("消息");
        textView = (TextView) findViewById(R.id.activity_message_content);
        getContest();
    }

    /**
     * 消息详情
     */
    public void getContest() {
        MessageDetailPostBean messageDetailPostBean = new MessageDetailPostBean();
        messageDetailPostBean.setNewsid(messageid);
        Http.messageDetail(MessageDetailActivity.this, messageDetailPostBean, textView);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MutualApplication.getRequestQueue().cancelAll("messageDetail50");
    }
}
