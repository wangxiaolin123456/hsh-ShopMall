package com.example.administrator.merchants.common;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseActivity;
import com.example.administrator.merchants.http.Http;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：微信回调
 */
public class WXPayEntryActivity extends BaseActivity implements IWXAPIEventHandler {
    private ImageView imageView;
    private TextView textView;
    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxpayentry);
        imageView = (ImageView) findViewById(R.id.img);
        textView = (TextView) findViewById(R.id.tv);
        api = WXAPIFactory.createWXAPI(this, CommonUtil.APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
        switch (req.getType()) {
            case ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX:
                break;
            case ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX:
                break;
            default:
                break;
        }
    }

    /**
     * 得到支付结果回调
     */
    @Override
    public void onResp(BaseResp resp) {
        if (resp.errCode == 0) {
            Http.WXPayEntry(this);
        } else if (resp.errCode == -1) {
            imageView.setBackgroundResource(R.drawable.wechat_pay_false);
            textView.setText("支付失败！");
        } else if (resp.errCode == -2) {
            imageView.setBackgroundResource(R.drawable.wechat_pay_false);
            textView.setText("用户取消了支付！");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        MutualApplication.getRequestQueue().cancelAll("WXPayEntry89");
    }
}
