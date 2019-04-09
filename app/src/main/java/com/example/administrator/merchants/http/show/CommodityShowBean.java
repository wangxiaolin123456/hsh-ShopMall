package com.example.administrator.merchants.http.show;

/**
 * 作者：韩宇 on 2017/6/23 0023 14:39
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：商品
 */
public class CommodityShowBean {
    private String contractno;//是否上架
    private String imgsfile;//也用于公告中的图片/商品图片
    private String merdescr;//也用于公告中的内容/商品描述
    private String merid;
    private String mername;//也用于公告中的名称/商品名称
    private String storeid;
    private double monthsalenum;
    private double saleprice;
    private double scoreavg;
    private double storenum;
    private String menuid;
    private int show;//是否展示popupWindow

    public void setShow(int show) {
        this.show = show;
    }

    public int getShow() {
        return show;
    }

    //用于公告
    private String time;////也用于公告中的时间
    //公告列表中的
    private String noticeid;

    public String getNoticeid() {
        return noticeid;
    }

    public void setNoticeid(String noticeid) {
        this.noticeid = noticeid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }

    public double getStorenum() {
        return storenum;
    }

    public void setStorenum(double storenum) {
        this.storenum = storenum;
    }

    public String getContractno() {
        return contractno;
    }

    public void setContractno(String contractno) {
        this.contractno = contractno;
    }

    public String getImgsfile() {
        return imgsfile;
    }

    public void setImgsfile(String imgsfile) {
        this.imgsfile = imgsfile;
    }

    public String getMerdescr() {
        return merdescr;
    }

    public void setMerdescr(String merdescr) {
        this.merdescr = merdescr;
    }

    public String getMerid() {
        return merid;
    }

    public void setMerid(String merid) {
        this.merid = merid;
    }

    public String getMername() {
        return mername;
    }

    public void setMername(String mername) {
        this.mername = mername;
    }

    public String getStoreid() {
        return storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }
    public double getMonthsalenum() {
        return monthsalenum;
    }
    public void setMonthsalenum(int monthsalenum) {
        this.monthsalenum = monthsalenum;
    }

    public double getSaleprice() {
        return saleprice;
    }

    public void setSaleprice(double saleprice) {
        this.saleprice = saleprice;
    }

    public double getScoreavg() {
        return scoreavg;
    }

    public void setScoreavg(double scoreavg) {
        this.scoreavg = scoreavg;
    }
}
