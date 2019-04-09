package com.example.administrator.merchants.http.show;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：PopupMenuShowBean
 */
public class PopupMenuShowBean {
    private int color;
   private String  imgpfile;
    private String menuid;
    private String menuname;
    private int path;
    private int type;

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setPath(int path) {
        this.path = path;
    }

    public int getPath() {
        return path;
    }

    public int getColor() {
        return color;
    }
    public void setColor(int color) {
        this.color = color;
    }
    public String getImgpfile() {
        return imgpfile;
    }
    public void setImgpfile(String imgsfile) {
        this.imgpfile = imgsfile;
    }
    public String getMenuid() {
        return menuid;
    }
    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }
    public String getMenuname() {
        return menuname;
    }
    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }
}
