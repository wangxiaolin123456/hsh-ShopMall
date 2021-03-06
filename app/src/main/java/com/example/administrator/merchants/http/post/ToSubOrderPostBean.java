package com.example.administrator.merchants.http.post;

import org.json.JSONArray;

/**
 * 作者：韩宇 on 2017/6/25 0025 16:09
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：提交订单参数
 */
public class ToSubOrderPostBean {
    private JSONArray merList;//商品集合
    private String storeid;//商家编码
    private String storename;//商家名
    private String ordamt;//订单支付金额
    private String addressid;//地址编码
    private String receiver;//姓名
    private String gender;//性别
    private String contact;//电话
    private String areaname;//地址
    private String streetaddr;//详细地址
    private String usedSilver;//抵扣输入银贝币数
    private String isSilver;//是否使用银贝币

    public String getUsedSilver() {
        return usedSilver;
    }

    public void setUsedSilver(String usedSilver) {
        this.usedSilver = usedSilver;
    }

    public String getIsSilver() {
        return isSilver;
    }

    public void setIsSilver(String isSilver) {
        this.isSilver = isSilver;
    }

    public JSONArray getMerList() {
        return merList;
    }

    public void setMerList(JSONArray merList) {
        this.merList = merList;
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

    public String getOrdamt() {
        return ordamt;
    }

    public void setOrdamt(String ordamt) {
        this.ordamt = ordamt;
    }

    public String getAddressid() {
        return addressid;
    }

    public void setAddressid(String addressid) {
        this.addressid = addressid;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public String getStreetaddr() {
        return streetaddr;
    }

    public void setStreetaddr(String streetaddr) {
        this.streetaddr = streetaddr;
    }
}
