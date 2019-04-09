package com.example.administrator.merchants.http.listener;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Response;
import com.example.administrator.merchants.R;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.http.post.NoticeDetailsPostBean;
import com.example.administrator.merchants.common.toast.CustomToast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者：韩宇 on 2017/7/16 0016 15:15
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：公告详情
 */
public class NoticeDetailsListener implements Response.Listener<JSONObject> {
    private Context context;
    private String htmlString;
    private String noticeId;
    private WebView imageView;
    private TextView title;
    private Button commit;

    public NoticeDetailsListener(Context context, String noticeId, WebView imageView, TextView title, Button commit) {
        this.context = context;
        this.noticeId = noticeId;
        this.imageView = imageView;
        this.title = title;
        this.commit = commit;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //返回成功
            try {
                htmlString = jsonObject.getString("noticecontent");// 1已报名 0未报名
                title.setText(jsonObject.getString("noticetitle"));
                imageView.loadDataWithBaseURL(null, htmlString, "text/html", "UTF-8", null);
                String join = jsonObject.getString("issignup");
                if (join.equals("1")) {//已报名
                    commit.setClickable(false);
                    commit.setText("您已报名");
                    commit.setBackgroundResource(R.drawable.dialog_gray_white);//灰色
                    commit.setTextColor(Color.parseColor("#ffffff"));
                    commit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            CustomToast.getInstance(context).setMessage("不可再次报名！");
                        }
                    });
                } else if (join.equals("0")) {//未报名
                    commit.setClickable(true);
                    commit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //弹出对话框
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setMessage("\n\t\t\t\t\t\t\t\t是否确认报名？" + "\n");
                            builder.setTitle("提示");
                            builder.setCancelable(false);
                            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //报名成功接口
                                    join();
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
                    });
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            //返回失败
            Status.fail(context, jsonObject);
        }
    }

    /**
     * 立即报名
     */
    public void join() {
        NoticeDetailsPostBean noticeDetailsPostBean = new NoticeDetailsPostBean();
        noticeDetailsPostBean.setStoreid(UserInfo.getInstance().getUser(context).getStoreid());
        noticeDetailsPostBean.setNoticeid(noticeId);
        Http.noticeJoin(context, noticeDetailsPostBean, commit);
    }
}
