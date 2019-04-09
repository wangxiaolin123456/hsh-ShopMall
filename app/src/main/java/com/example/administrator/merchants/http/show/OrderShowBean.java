package com.example.administrator.merchants.http.show;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：商家订单显示bean
 */
public class OrderShowBean {

    private String ordstatus;
    private String paystatus;
    private String ordimgsfile;
    private String ordno;
    private String createtimestr;
    private int ordmerqty;
    private String ordmername;
    private String sendstatus;
    private String ordtype;
    private String stationid;

    public void setStationid(String stationid) {
        this.stationid = stationid;
    }

    public String getStationid() {
        return stationid;
    }

    public String getOrdtype() {
        return ordtype;
    }

    public void setOrdtype(String ordtype) {
        this.ordtype = ordtype;
    }

    public String getSendstatus() {
        return sendstatus;
    }

    public void setSendstatus(String sendstatus) {
        this.sendstatus = sendstatus;
    }

    public String getOrdmername() {
        return ordmername;
    }

    public void setOrdmername(String ordmername) {
        this.ordmername = ordmername;
    }

    public String getOrdimgsfile() {
        return ordimgsfile;
    }

    public void setOrdimgsfile(String ordimgsfile) {
        this.ordimgsfile = ordimgsfile;
    }

    public String getOrdno() {
        return ordno;
    }

    public void setOrdno(String ordno) {
        this.ordno = ordno;
    }

    public String getCreatetimestr() {
        return createtimestr;
    }

    public void setCreatetimestr(String createtimestr) {
        this.createtimestr = createtimestr;
    }

    public int getOrdmerqty() {
        return ordmerqty;
    }

    public void setOrdmerqty(int ordmerqty) {
        this.ordmerqty = ordmerqty;
    }

    public String getOrdstatus() {
        return ordstatus;
    }

    public void setOrdstatus(String ordstatus) {
        this.ordstatus = ordstatus;
    }

    public String getPaystatus() {
        return paystatus;
    }

    public void setPaystatus(String paystatus) {
        this.paystatus = paystatus;
    }

}
