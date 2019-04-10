package com.example.administrator.merchants.http.post;

/**
 * 作者：韩宇 on 2017/6/24 0024 09:56
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：商品编码
 */
public class MerIdPostBean {
    private String merid;
    private String memStoreType;//0:消费者  1：商家

    public String getMemStoreType() {
        return memStoreType;
    }

    public void setMemStoreType(String memStoreType) {
        this.memStoreType = memStoreType;
    }

    public void setMerid(String merid) {
        this.merid = merid;
    }

    public String getMerid() {
        return merid;
    }
}
