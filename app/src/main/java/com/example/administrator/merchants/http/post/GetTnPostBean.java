package com.example.administrator.merchants.http.post;

/**
 * 作者：韩宇 on 2017/6/22 0022 16:31
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：获取三方支付的TN号
 */
public class GetTnPostBean {
    private String storeid;
    private String storename;
    private String rechamt;//充值金额
    private String paytype;//充值方式

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

    public String getRechamt() {
        return rechamt;
    }

    public void setRechamt(String rechamt) {
        this.rechamt = rechamt;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }
}
