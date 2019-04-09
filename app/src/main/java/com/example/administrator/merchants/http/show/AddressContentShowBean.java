package com.example.administrator.merchants.http.show;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：收货地址列表显示bean
 */
public class AddressContentShowBean {
    private String addressid;
    private String areaname;//地址
    private String contact;//电话
    private String gender;
    private String isdefault;//是否缺省
    private String receiver;//收货人姓名
    private String streetaddr;//街道
    private String storeid;

    public String getStoreid() {
        return storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    public String getAddressid() {
        return addressid;
    }

    public void setAddressid(String addressid) {
        this.addressid = addressid;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(String isdefault) {
        this.isdefault = isdefault;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getStreetaddr() {
        return streetaddr;
    }

    public void setStreetaddr(String streetaddr) {
        this.streetaddr = streetaddr;
    }
}
