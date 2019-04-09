package com.example.administrator.merchants.http.post;

/**
 * 作者：韩宇 on 2017/7/3 0003 10:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：忘记密码和忘记贝币密码
 */
public class ForgetPostBean {
    private String storeid;
    private String aucode;
    private String storephone;
    private String paypassword;
    private String storepassword;

    public void setStorepassword(String storepassword) {
        this.storepassword = storepassword;
    }

    public String getStorepassword() {
        return storepassword;
    }

    public String getStoreid() {
        return storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    public String getAucode() {
        return aucode;
    }

    public void setAucode(String aucode) {
        this.aucode = aucode;
    }

    public String getStorephone() {
        return storephone;
    }

    public void setStorephone(String storephone) {
        this.storephone = storephone;
    }

    public String getPaypassword() {
        return paypassword;
    }

    public void setPaypassword(String paypassword) {
        this.paypassword = paypassword;
    }
}
