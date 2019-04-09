package com.example.administrator.merchants.http.post;

/**
 * 作者：韩宇 on 2017/6/21 0021 14:34
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：删除银行卡
 */
public class DeleteBankCardPostBean {
    private String cardid;//银行卡编码

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public String getCardid() {
        return cardid;
    }
}
