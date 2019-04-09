package com.example.administrator.merchants.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseActivity;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.post.NoticeDetailsPostBean;


/**
 * 作者：韩宇 on 2017/7/16 0016 09:41
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：公告详情
 */
public class NoticeDetailsActivity extends BaseActivity {
    private WebView imageView;
    private TextView title;
    private String noticeId;
    private Button commit;//立即报名

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notic_details);
        noticeId = getIntent().getStringExtra("noticeid");
        title = (TextView) findViewById(R.id.head_title);
        imageView = (WebView) findViewById(R.id.activity_service_details_image);
        commit = (Button) findViewById(R.id.sign_up);
        details();
    }

    /**
     * 详情接口
     */
    public void details() {
        NoticeDetailsPostBean noticeDetailsPostBean = new NoticeDetailsPostBean();
        noticeDetailsPostBean.setStoreid(UserInfo.getInstance().getUser(NoticeDetailsActivity.this).getStoreid());
        noticeDetailsPostBean.setNoticeid(noticeId);
        Http.noticeDetails(NoticeDetailsActivity.this, noticeDetailsPostBean, noticeId, imageView, title, commit);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MutualApplication.getRequestQueue().cancelAll("noticeDetails53");
        MutualApplication.getRequestQueue().cancelAll("noticeJoin54");
    }

}
