package com.example.administrator.merchants.http.post;

/**
 * 作者：韩宇 on 2017/6/21 0021 14:15
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：设置默认银行卡post参数
 */
public class DefaultBankCardPostBean {
    private String cardid;//银行卡编码
    private String storeid;//商家编码

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public String getStoreid() {
        return storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }
}
