package com.example.administrator.merchants.http.post;

import org.json.JSONArray;

/**
 * 作者：韩宇 on 2017/7/2 0002 10:28
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：收货/评价post参数
 */
public class EvaluatePostBean {
    private String wordno;//订单号
    private String wstoreid;//商家编码
    private JSONArray wmerList;//评论信息

    public String getWordno() {
        return wordno;
    }

    public void setWordno(String wordno) {
        this.wordno = wordno;
    }

    public String getWstoreid() {
        return wstoreid;
    }

    public void setWstoreid(String wstoreid) {
        this.wstoreid = wstoreid;
    }

    public JSONArray getWmerList() {
        return wmerList;
    }

    public void setWmerList(JSONArray wmerList) {
        this.wmerList = wmerList;
    }
}
