package com.example.administrator.merchants.http.post;

/**
 * 作者：韩宇 on 2017/6/22 0022 10:53
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：余额提取接口post参数
 */
public class BalanceExtractionPostBean {
    private String storeid;//商家必拿吗
    private String storename;//商家名
    private String cashamt;//提取金额
    private String receivetype;//提取类型
    private String receiveaccount;//提取账号
    private String receivebank;//提取银行类型
    private String receivename;//提取人名
    private String receivephone;//提取人电话

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

    public String getCashamt() {
        return cashamt;
    }

    public void setCashamt(String cashamt) {
        this.cashamt = cashamt;
    }

    public String getReceivetype() {
        return receivetype;
    }

    public void setReceivetype(String receivetype) {
        this.receivetype = receivetype;
    }

    public String getReceiveaccount() {
        return receiveaccount;
    }

    public void setReceiveaccount(String receiveaccount) {
        this.receiveaccount = receiveaccount;
    }

    public String getReceivebank() {
        return receivebank;
    }

    public void setReceivebank(String receivebank) {
        this.receivebank = receivebank;
    }

    public String getReceivename() {
        return receivename;
    }

    public void setReceivename(String receivename) {
        this.receivename = receivename;
    }

    public String getReceivephone() {
        return receivephone;
    }

    public void setReceivephone(String receivephone) {
        this.receivephone = receivephone;
    }
}
