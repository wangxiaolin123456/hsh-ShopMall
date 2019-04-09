package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.widget.Button;

import com.android.volley.Response;
import com.example.administrator.merchants.http.Status;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者：韩宇 on 2017/8/16 0016 11:01
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：评论数量
 */
public class CommonNumberListener implements Response.Listener<JSONObject> {
    private Context context;
    private Button goodReputationBtn;//好评
    private Button allBtn;//全部
    private Button middleEvaluationBtn;//中评
    private Button negativeCommentBtn;//差评
    private Button makeBlueprintBtn;//晒图

    public CommonNumberListener(Context context, Button goodReputationBtn, Button allBtn, Button middleEvaluationBtn, Button negativeCommentBtn
            , Button makeBlueprintBtn) {
        this.context = context;
        this.goodReputationBtn = goodReputationBtn;
        this.allBtn = allBtn;
        this.middleEvaluationBtn = middleEvaluationBtn;
        this.negativeCommentBtn = negativeCommentBtn;
        this.makeBlueprintBtn = makeBlueprintBtn;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //返回成功
            try {
                JSONObject object = (JSONObject) jsonObject.get("merinfo");
                allBtn.setText("全部(" + object.getInt("scorenum") + ")");//全部scorenum
                goodReputationBtn.setText("好评(" + object.getInt("scorenum1") + ")");//好评
                middleEvaluationBtn.setText("中评(" + object.getInt("scorenum2") + ")");//中评
                negativeCommentBtn.setText("差评(" + object.getInt("scorenum3") + ")");//差评
                makeBlueprintBtn.setText("晒图(" + object.getInt("scorenumimg") + ")");//晒图
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            //返回失败
            Status.fail(context, jsonObject);
        }
    }
}
