package com.example.administrator.merchants.common.alipay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.alipay.sdk.app.PayTask;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.http.HttpUrl;
import com.example.administrator.merchants.common.toast.CustomToast;
import com.example.administrator.merchants.common.toast.CustomToastFinish;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：支付宝
 */
public class BabyPay {
    private Context context;
    private String subject;
    private String body;
    private String price;
    private String partnerPrivKey;//签名
    private String TN;//订单号
    private Activity activity;

    public BabyPay(Activity activity, Context context, String subject, String body, String price, String TN, String partnerPrivKey) {
        this.context = context;
        this.subject = subject;
        this.body = body;
        this.price = price;
        this.TN = TN;
        this.partnerPrivKey = partnerPrivKey;
        this.activity = activity;
    }


    public static final String PARTNER = "2088121799173543";  // 商户PID

    public static final String SELLER = "SYHSKJ66666@163.com"; // 商户收款账号

//    public  String RSA_PRIVATE ="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALhiQigB1W7if6n3\n" +
//            "xqS6pLX1cgVuDdYIumblfB1aFvVAm4b0ANT38Jjbg7yxNyz/JzOnlbUg4oMRMX4M\n" +
//            "VBGvIIJK5yqkzO95fIyWKqz/z2HzRn8wVqy0rBnZkj7yWLqMZdCB00pXlDJ3W4jp\n" +
//            "IYUXc/dDZhiaWazGLWcrQdIOkPERAgMBAAECgYB/ow020qYj30kOqMXqzIUurJzk\n" +
//            "o12Sl3IpvpxfWTjhR3siPZDB3GzB89tK6MZQMMS7XjwlKYGDx4giQHiF0VxHiFgW\n" +
//            "PkhmuZp9RR3NU7ocRm4rqo3HJH3K8jyiqijfCmdgDH4HIB3TztKLAVONVvr78hUb\n" +
//            "FwB26ttcdMVAMkgJvQJBAOQudWu8qJ7G+H5PzPZLwQHYHHtleINiQG+Gi6ruEref\n" +
//            "RqV4Wa6cZM05jbBFV4O+H7WeOpCzJbETfyke+N9Zf+8CQQDO3OAYZ5gvWeHxxVbm\n" +
//            "tNzZsdzJQnljtqiB4L3gn4RQOlVQq9ag8D4LVbXiHtJ7Lc9PKVpA6HFFW4C7WRZ6\n" +
//            "257/AkEAySx8scPfFj1uEE7i8bLQM4QJeHF50rvo+2qv8L99GQ8ABhsZVmzkIlu/\n" +
//            "o80+3xPceQ9Lqw+HB5uc3PBFkwUhWQJAT01Vi4WLTfvDJpBkdCU51SreXTlil9ta\n" +
//            "Vg/2OrpsWuVB958otKHk5yuAgqMWRaqWIt7TfradVd1ySGAqwgaXuQJBAKjcUfHD\n" +
//            "qSixuMwZEKNmh+Flrb8VRDaK1ncGQfd2E/uMvdwzE+zHIFw7KOtLET4/traqLvyq\n" +
//            "c/LxIAxvDY0S55k=" ; // 商户私钥，pkcs8格式

    public static final String RSA_PUBLIC = "";// 支付宝公钥
    private static final int SDK_PAY_FLAG = 1;


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    BabyPayResult payResult = new BabyPayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
//                        ToastHelper.makeText(context, "支付成功", 1000).show();
//                        CustomToast.getInstance(context).setMessage("支付成功！");
                        CustomToastFinish customToastFinish = new CustomToastFinish(context);
                        customToastFinish.setMessage("支付成功！");
//                        activity.finish();
                        MutualApplication.getRequestQueue().cancelAll("alisuccess");
                        //toDO  告诉后台   支付宝购买或者充值成功了
                        JSONObject jsonParams = new JSONObject();
                        try {
                            jsonParams.put("tn", TN);
                            jsonParams.put("paystatus", "1");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.ali_ok, jsonParams, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                Log.e("@@@@@@@@@@@@@@", "支付成功！");
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
//                                    ToastHelper.makeText(context,"网络请求失败！",1000).show();
                                CustomToast.getInstance(context).setMessage("网络请求失败！");

                            }
                        });
                        jsonObjectRequest.setTag("alisuccess");
                        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(500000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                        MutualApplication.getRequestQueue().add(jsonObjectRequest);
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
//                            ToastHelper.makeText(context, "支付失败", 1000).show();
                        CustomToast.getInstance(context).setMessage("支付失败！");
                        MutualApplication.getRequestQueue().cancelAll("zhifubaofail");
                        //toDO  告诉后台   支付宝购买或者充值成功了
                        JSONObject jsonParams = new JSONObject();
                        try {
                            jsonParams.put("tn", TN);
                            jsonParams.put("paystatus", "9");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.ali_ok, jsonParams, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                Log.e("@@@@@@@@@@@@@@", "支付失败！");
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
//                                    ToastHelper.makeText(context,"网络请求失败！",1000).show();
                                CustomToast.getInstance(context).setMessage("网络请求失败！");

                            }
                        });
                        jsonObjectRequest.setTag("zhifubaofail");
                        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(500000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                        MutualApplication.getRequestQueue().add(jsonObjectRequest);

                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };


    /**
     * call alipay sdk pay. 调用SDK支付
     */
    public void pay() {


//        Log.e("9999999999999999999",""+RSA_PRIVATE);

        if (TextUtils.isEmpty(PARTNER) || TextUtils.isEmpty(partnerPrivKey) || TextUtils.isEmpty(SELLER)) {
            new AlertDialog.Builder(context).setTitle("警告").setMessage("需要配置PARTNER | RSA_PRIVATE| SELLER")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {

                        }
                    }).show();
            return;
        }
        String orderInfo = getOrderInfo(subject, body, price, TN);

        /**
         * 特别注意，这里的签名逻辑需要放在服务端，切勿将私钥泄露在代码中！
         */
        String sign = sign(orderInfo);
        try {
            /**
             * 仅需对sign 做URL编码
             */
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        /**
         * 完整的符合支付宝参数规范的订单信息
         */
        final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();


        Log.e("完整的订单信息", "" + payInfo);

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask((Activity) context);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();

    }

    /**
     * get the sdk version. 获取SDK版本号
     */
    public void getSDKVersion() {
        PayTask payTask = new PayTask((Activity) context);
        String version = payTask.getVersion();
//        ToastHelper.makeText(context, version, 1000).show();
        CustomToast.getInstance(context).setMessage(version);

    }


    /**
     * create the order info. 创建订单信息
     */
    private String getOrderInfo(String subject, String body, String price, String TN) {

        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + TN + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";

        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + HttpUrl.BaseUrl + "/mbalipayback.action" + "\"";

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";

        return orderInfo;
    }

    /**
     * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
     *
     */
//    private String getOutTradeNo() {
//        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
//        Date date = new Date();
//        String key = format.format(date);
//
//        Random r = new Random();
//        key = key + r.nextInt();
//        key = key.substring(0, 15);
//        return ordno;
//    }

    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content 待签名订单信息
     */
    private String sign(String content) {
        return BabySignUtils.sign(content, partnerPrivKey);
    }

    /**
     * get the sign type we use. 获取签名方式
     */
    private String getSignType() {
        return "sign_type=\"RSA\"";
    }

}
