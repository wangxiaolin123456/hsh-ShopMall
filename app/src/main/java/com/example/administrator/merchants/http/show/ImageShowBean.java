package com.example.administrator.merchants.http.show;

/**
 * 作者：韩宇 on 2017/6/23 0023 14:39
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：评论图片
 */
public class ImageShowBean {
    /*评价编码*/
    private String evaluateid;
    /*评价晒图大图*/
    private String imgpfile;
    /*评价晒图小图*/
    private String imgsfile;

    public String getEvaluateid() {
        return evaluateid;
    }

    public void setEvaluateid(String evaluateid) {
        this.evaluateid = evaluateid;
    }

    public String getImgpfile() {
        return imgpfile;
    }

    public void setImgpfile(String imgpfile) {
        this.imgpfile = imgpfile;
    }

    public String getImgsfile() {
        return imgsfile;
    }

    public void setImgsfile(String imgsfile) {
        this.imgsfile = imgsfile;
    }

}
