package com.example.administrator.merchants.http.post;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：对账单明细post参数
 */
public class AccountStatementDetailPostBean {
    private String storeid;//商家编码
    private String settleno;//对账单编码
    private String offset;//开始行
    private String limit;//每页显示条数

    public String getStoreid() {
        return storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    public String getSettleno() {
        return settleno;
    }

    public void setSettleno(String settleno) {
        this.settleno = settleno;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }
}
