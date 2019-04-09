package com.example.administrator.merchants.http.post;

/**
 * 作者：韩宇 on 2017/7/21 0021 15:13
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：支付订单
 */
public class PayOrderPostBean {
    private String storeid;
    private String storename;
    private String ordno;
    private String paytype;
    private String payamt;
    private String isSilver;//1 是  2 不是

    public String getIsSilver() {
        return isSilver;
    }

    public void setIsSilver(String isSilver) {
        this.isSilver = isSilver;
    }

    public String getStoreid() {
        return storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public String getOrdno() {
        return ordno;
    }

    public void setOrdno(String ordno) {
        this.ordno = ordno;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public String getPayamt() {
        return payamt;
    }

    public void setPayamt(String payamt) {
        this.payamt = payamt;
    }
}