package com.example.administrator.merchants.http.show;

/**
 * 专区列表
 * Created by Administrator on 2016/11/23.
 */
public class PrefectureImageBean {
    private String groupid;//组编码
    private String istitle;//	是否标题
    private String imgpfile;//组图片
    private int imgwidth;//图片宽度
    private int imgheight;//图片高度
    private int rownum;//	组行数
    private int colnum;//组列数


    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getIstitle() {
        return istitle;
    }

    public void setIstitle(String istitle) {
        this.istitle = istitle;
    }

    public String getImgpfile() {
        return imgpfile;
    }

    public void setImgpfile(String imgpfile) {
        this.imgpfile = imgpfile;
    }

    public int getImgwidth() {
        return imgwidth;
    }

    public void setImgwidth(int imgwidth) {
        this.imgwidth = imgwidth;
    }

    public int getImgheight() {
        return imgheight;
    }

    public void setImgheight(int imgheight) {
        this.imgheight = imgheight;
    }

    public int getRownum() {
        return rownum;
    }

    public void setRownum(int rownum) {
        this.rownum = rownum;
    }

    public int getColnum() {
        return colnum;
    }

    public void setColnum(int colnum) {
        this.colnum = colnum;
    }
}
