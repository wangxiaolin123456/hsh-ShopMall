package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;

import com.android.volley.Response;
import com.example.administrator.merchants.R;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.common.toast.CustomToast;

import org.json.JSONObject;

/**
 * 作者：韩宇 on 2017/7/16 0016 15:35
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：立即报名
 */
public class NoticeJoinListener implements Response.Listener<JSONObject> {
    private Context context;
    private Button commit;

    public NoticeJoinListener(Context context, Button commit) {
        this.context = context;
        this.commit = commit;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //返回成功
            CustomToast.getInstance(context).setMessage("您已报名成功！");
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
        } else {
            //返回失败
            Status.fail(context, jsonObject);
        }

    }
}
