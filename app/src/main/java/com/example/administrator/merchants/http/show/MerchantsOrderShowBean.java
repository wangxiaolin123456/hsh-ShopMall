package com.example.administrator.merchants.http.show;

import android.net.Uri;


import java.io.File;
import java.util.List;

/**
 * 作者：韩宇 on 2017/6/23 0023 14:39
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：商家订单
 */
public class MerchantsOrderShowBean {
    private String orddetailid;// 订单明细编码 varchar
    private String guige;
    private String merid; //商品编码 varchar
    private String mername; // 商品名称 varchar
    private String imgsfile; // 商品简图 varchar
    private double saleprice; // 销售价格double
    private int merqty; // 商品数量 int
    private String merstatus; //商品状态（0未完成，1已完成）
    private String merpaystatus; //付款状态（0未付款，1已付款，2退款申请，3退款驳回，9已退款）
    private String createtime;//下单时间
    private String receivestatus; //收货状态（0未收货、1确认收货、2自动确认收货、3已退货）
    private String receivetime; //收货时间
    private String refapplytime; //退款申请时间
    private String reftime ; //退款时间
    private boolean choosed;
    private List<Uri> uris;
    private int type;
    private String remark;

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    private List<File>fileList;
    private List<File>files;
    public List<Uri> getUris() {
        return uris;
    }
    public void setUris(List<Uri> uris) {
        this.uris = uris;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public List<File> getFileList() {
        return fileList;
    }
    public void setFileList(List<File> fileList) {
        this.fileList = fileList;
    }
    public List<File> getFiles() {
        return files;
    }
    public void setFiles(List<File> files) {
        this.files = files;
    }
    //toDo  为了订单评价设置的3个
    public String texts;
    public String staumer;
    public String grade;
    public String getTexts() {
        return texts;
    }
    public void setTexts(String texts) {
        this.texts = texts;
    }
    public String getStaumer() {
        return staumer;
    }
    public void setStaumer(String staumer) {
        this.staumer = staumer;
    }
    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public String getCreatetime() {
        return createtime;
    }
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
    public boolean isChoosed() {
        return choosed;
    }
    public void setChoosed(boolean choosed) {
        this.choosed = choosed;
    }
    public String getOrddetailid() {
        return orddetailid;
    }
    public void setOrddetailid(String orddetailid) {
        this.orddetailid = orddetailid;
    }
    public String getMerid() {
        return merid;
    }
    public void setMerid(String merid) {
        this.merid = merid;
    }

    public void setGuige(String guige) {
        this.guige = guige;
    }

    public String getGuige() {
        return guige;
    }

    public String getMername() {
        return mername;
    }
    public void setMername(String mername) {
        this.mername = mername;
    }
    public String getImgsfile() {
        return imgsfile;
    }
    public void setImgsfile(String imgsfile) {
        this.imgsfile = imgsfile;
    }
    public double getSaleprice() {
        return saleprice;
    }
    public void setSaleprice(double saleprice) {
        this.saleprice = saleprice;
    }
    public int getMerqty() {
        return merqty;
    }
    public void setMerqty(int merqty) {
        this.merqty = merqty;
    }
    public String getMerstatus() {
        return merstatus;
    }
    public void setMerstatus(String merstatus) {
        this.merstatus = merstatus;
    }
    public String getMerpaystatus() {
        return merpaystatus;
    }
    public void setMerpaystatus(String merpaystatus) {
        this.merpaystatus = merpaystatus;
    }
    public String getReceivestatus() {
        return receivestatus;
    }
    public void setReceivestatus(String receivestatus) {
        this.receivestatus = receivestatus;
    }
    public String getReceivetime() {
        return receivetime;
    }
    public void setReceivetime(String receivetime) {
        this.receivetime = receivetime;
    }
    public String getRefapplytime() {
        return refapplytime;
    }
    public void setRefapplytime(String refapplytime) {
        this.refapplytime = refapplytime;
    }
    public String getReftime() {
        return reftime;
    }
    public void setReftime(String reftime) {
        this.reftime = reftime;
    }
}
