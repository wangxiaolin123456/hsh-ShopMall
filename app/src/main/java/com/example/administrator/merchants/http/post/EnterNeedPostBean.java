package com.example.administrator.merchants.http.post;

/**
 * 作者：韩宇 on 2017/6/26 0026 15:31
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：企业需求提交post参数
 */
public class EnterNeedPostBean {
    private String storeid;//商家编码
    private String getServeid;//服务ID
    private String needsphone;//需求电话
    private String needsperson;//企业联系人
    private String needsdescr;//需求描述
    private String aucode;//验证码

    public String getStoreid() {
        return storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    public String getGetServeid() {
        return getServeid;
    }

    public void setGetServeid(String getServeid) {
        this.getServeid = getServeid;
    }

    public String getNeedsphone() {
        return needsphone;
    }

    public void setNeedsphone(String needsphone) {
        this.needsphone = needsphone;
    }

    public String getNeedsperson() {
        return needsperson;
    }

    public void setNeedsperson(String needsperson) {
        this.needsperson = needsperson;
    }

    public String getNeedsdescr() {
        return needsdescr;
    }

    public void setNeedsdescr(String needsdescr) {
        this.needsdescr = needsdescr;
    }

    public String getAucode() {
        return aucode;
    }

    public void setAucode(String aucode) {
        this.aucode = aucode;
    }
}
