package com.example.administrator.merchants.http.post;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：删除地址post参数
 */
public class DeleteAddressPostBean {
    private String addressid;//地址编码

    public void setAddressid(String addressid) {
        this.addressid = addressid;
    }

    public String getAddressid() {
        return addressid;
    }
}
