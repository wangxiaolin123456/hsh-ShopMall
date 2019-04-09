package com.example.administrator.merchants.http.post;

/**
 * 作者：韩宇 on 2017/6/22 0022 15:16
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：贝币赠与请求接口
 */
public class BeiBiGivePostBean {
    private String storeid;
    private String type;//赠与电话类型
    private String giftphone;//赠与电话
    private String giftamt;//赠与金额
    private String beibiType;//贝币类型字段  0: 金贝币  1: 银贝币

    public String getBeibiType() {
        return beibiType;
    }

    public void setBeibiType(String beibiType) {
        this.beibiType = beibiType;
    }

    public String getStoreid() {
        return storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGiftphone() {
        return giftphone;
    }

    public void setGiftphone(String giftphone) {
        this.giftphone = giftphone;
    }

    public String getGiftamt() {
        return giftamt;
    }

    public void setGiftamt(String giftamt) {
        this.giftamt = giftamt;
    }
}
