package com.example.administrator.merchants.http.post;

/**
 * 作者：韩宇 on 2017/8/13 0013 16:03
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：分类post的bean
 */
public class ClassifyPostBean {
    private String storeid;
    private String menuname;
    private String menuid;

    public String getStoreid() {
        return storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }
}
