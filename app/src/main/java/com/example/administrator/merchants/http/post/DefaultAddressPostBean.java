package com.example.administrator.merchants.http.post;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：设置默认地址post参数
 */
public class DefaultAddressPostBean {
    private String addressid;//地址ID
    private String storeid;//商家编码

    public String getAddressid() {
        return addressid;
    }

    public void setAddressid(String addressid) {
        this.addressid = addressid;
    }

    public String getStoreid() {
        return storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }
}
