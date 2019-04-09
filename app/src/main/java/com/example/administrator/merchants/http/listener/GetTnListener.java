package com.example.administrator.merchants.http.listener;

import android.app.Activity;
import android.content.Context;

import com.android.volley.Response;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.common.CommonUtil;
import com.example.administrator.merchants.common.alipay.BabyPay;
import com.example.administrator.merchants.http.Status;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.unionpay.UPPayAssistEx;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者：韩宇 on 2017/6/22 0022 16:36
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：获取第三方支付tn号
 */
public class GetTnListener extends Activity implements Response.Listener<JSONObject> {
    private static String TN;
    private Context context;
    private String type;
    private String mMode;
    private BabyPay babyPay;//支付宝 启动对象
    private IWXAPI api;//微信支付启动对象

    /**
     * 银联支付宝
     *
     * @param context
     * @param type
     * @param mMode
     */
    public GetTnListener(Context context, String type, String mMode) {
        this.context = context;
        this.type = type;
        this.mMode = mMode;
    }

    /**
     * 微信
     *
     * @param context
     * @param type
     */
    public GetTnListener(Context context, String type) {
        this.context = context;
        this.type = type;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //返回成功
            switch (type) {
                case "unionpay":
                    TN = new String();
                    try {
                        TN = jsonObject.getString("tn");
                        UPPayAssistEx.startPay(context, null, null, TN, mMode);//TODO   调用银联支付
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case "alipay":
                    try {
                        TN = jsonObject.getString("tn");
                        String partnerPrivKey = jsonObject.getString("partnerPrivKey");
                        babyPay = new BabyPay(this, context,
                                "贝币",
                                "贝币充值",
                                mMode,
                                TN,
                                partnerPrivKey);
                        babyPay.pay();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case "wechat":
                    api = WXAPIFactory.createWXAPI(context, CommonUtil.APP_ID);//toDo  秘钥
                    try {
                        TN = jsonObject.getString("tn");
                        MutualApplication.mTN = TN;
                        PayReq req = new PayReq();
                        try {
                            req.appId = jsonObject.getString("appid");
                            req.partnerId = jsonObject.getString("partnerid");
                            req.prepayId = jsonObject.getString("prepayid");
                            req.nonceStr = jsonObject.getString("noncestr");
                            req.timeStamp = jsonObject.getString("timestamp");
                            req.packageValue = jsonObject.getString("package");
                            req.sign = jsonObject.getString("sign");
                            req.extData = "app data"; // optional
                            api.sendReq(req);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        } else {
            //返回失败
            Status.fail(context, jsonObject);
        }
    }
}
