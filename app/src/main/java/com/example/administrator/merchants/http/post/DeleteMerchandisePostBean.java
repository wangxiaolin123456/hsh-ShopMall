package com.example.administrator.merchants.http.post;

/**
 * 作者：韩宇 on 2017/8/7 0007 14:18
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：删除商品
 */
public class DeleteMerchandisePostBean {
    private String storeid;
    private String merid;
    private String isused;

    public String getStoreid() {
        return storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    public String getMerid() {
        return merid;
    }

    public void setMerid(String merid) {
        this.merid = merid;
    }

    public void setIsused(String isused) {
        this.isused = isused;
    }

    public String getIsused() {
        return isused;
    }
}
