package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Response;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.http.post.BalanceExtractionPostBean;
import com.example.administrator.merchants.http.post.BeiBiGivePostBean;
import com.example.administrator.merchants.http.post.OrderFinishPostBean;

import org.json.JSONObject;

/**
 * 作者：韩宇 on 2017/6/22 0022 10:09
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：贝币确认密码
 */
public class ConfirmBeiBiPasswordListener implements Response.Listener<JSONObject> {
    private Context context;
    private TextView bankName, userName, tel, bankNumber;
    private EditText editText;
    private int type;
    private EditText beiBi, phone;
    private String friendsType, money, orderNo;
    private String isSilver;
    private String beibiType;//0金贝别  1银贝币
    /**
     * 余额提取里的确认密码
     *
     * @param context
     * @param bankName
     * @param userName
     * @param tel
     * @param bankNumber
     * @param editText
     * @param type
     */
    public ConfirmBeiBiPasswordListener(Context context, TextView bankName, TextView userName, TextView tel, TextView bankNumber, EditText editText, int type) {
        this.context = context;
        this.bankName = bankName;
        this.userName = userName;
        this.tel = tel;
        this.bankNumber = bankNumber;
        this.editText = editText;
        this.type = type;
    }

    /***
     * 贝币赠与里的确认密码
     *
     * @param context
     * @param type
     * @param beiBi
     * @param phone
     * @param friendsType
     */
    public ConfirmBeiBiPasswordListener(Context context, int type, EditText beiBi, EditText phone,
                                        String friendsType,String beibiType) {
        this.context = context;
        this.type = type;
        this.beiBi = beiBi;
        this.phone = phone;
        this.friendsType = friendsType;
        this.beibiType = beibiType;
    }

    public ConfirmBeiBiPasswordListener(Context context, String money, String orderNo, int type, String isSilver) {
        this.context = context;
        this.money = money;
        this.orderNo = orderNo;
        this.type = type;
        this.isSilver = isSilver;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //返回成功
            if (type == 0) {
                balanceExtraction();//余额提取
            } else if (type == 1) {
                beiBeiGift();//贝币赠与
            } else if (type == 2) {
                beiBiPayConfirm(isSilver);
            }

        } else {
            //返回失败
            Status.fail(context, jsonObject);
        }

    }

    /**
     * 余额提取接口
     */
    public void balanceExtraction() {
        BalanceExtractionPostBean balanceExtractionPostBean = new BalanceExtractionPostBean();
        balanceExtractionPostBean.setStoreid(UserInfo.getInstance().getUser(context).getStoreid());
        balanceExtractionPostBean.setStorename(UserInfo.getInstance().getUser(context).getStorename());
        balanceExtractionPostBean.setCashamt(editText.getText().toString());
        balanceExtractionPostBean.setReceivetype("unionpay");
        balanceExtractionPostBean.setReceiveaccount(bankNumber.getText().toString().replaceAll(" ", ""));
        balanceExtractionPostBean.setReceivebank(bankName.getText().toString());
        balanceExtractionPostBean.setReceivename(userName.getText().toString());
        balanceExtractionPostBean.setReceivephone(tel.getText().toString().replaceAll(" ", ""));
        Http.balanceExtraction(context, balanceExtractionPostBean);
    }

    /**
     * 贝币赠予接口
     */
    private void beiBeiGift() {
        BeiBiGivePostBean beiBiGivePostBean = new BeiBiGivePostBean();
        beiBiGivePostBean.setStoreid(UserInfo.getInstance().getUser(context).getStoreid());
        beiBiGivePostBean.setType(friendsType);
        beiBiGivePostBean.setGiftphone(phone.getText().toString());
        beiBiGivePostBean.setGiftamt(beiBi.getText().toString());
        beiBiGivePostBean.setBeibiType(beibiType);
        Http.beiBiGive(context, beiBiGivePostBean);
    }

    /**
     * 点单支付完成
     */
    private void beiBiPayConfirm(String isSilver) {
        OrderFinishPostBean orderFinishPostBean = new OrderFinishPostBean();
        orderFinishPostBean.setStoreid(UserInfo.getInstance().getUser(context).getStoreid());
        orderFinishPostBean.setStorename(UserInfo.getInstance().getUser(context).getStorename());
        orderFinishPostBean.setOrdno(orderNo);
        orderFinishPostBean.setPaytype("beibi");
        orderFinishPostBean.setPayamt(money);
        orderFinishPostBean.setIsSilver(isSilver);
        Http.payBeiBiFinish(context, orderFinishPostBean);
    }
}
