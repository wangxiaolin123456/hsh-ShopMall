package com.example.administrator.merchants.http.show;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：对账单
 */
public class StatementAccountShowBean {
    private String settleno;
    private int type=0;
    private String time;//时间
    private String stu;//状态
    private double orderMoney;//订单总额
    private double tripartitePaysFee;//三方支付费用
    private double payDelivery;//货到付款
    private double platformLicensing;//平台使用费
    private double shippingRates;//配送费
    private double toIssueDebt;//往期欠款
    private double backBeiBi;//返贝币
    private int orderNumber;//总共的订单数量
    private double customaryDues;//应付款

    public void setSettleno(String settleno) {
        this.settleno = settleno;
    }

    public String getSettleno() {
        return settleno;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStu() {
        return stu;
    }

    public void setStu(String stu) {
        this.stu = stu;
    }

    public double getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(double orderMoney) {
        this.orderMoney = orderMoney;
    }

    public double getTripartitePaysFee() {
        return tripartitePaysFee;
    }

    public void setTripartitePaysFee(double tripartitePaysFee) {
        this.tripartitePaysFee = tripartitePaysFee;
    }

    public double getPayDelivery() {
        return payDelivery;
    }

    public void setPayDelivery(double payDelivery) {
        this.payDelivery = payDelivery;
    }

    public double getPlatformLicensing() {
        return platformLicensing;
    }

    public void setPlatformLicensing(double platformLicensing) {
        this.platformLicensing = platformLicensing;
    }

    public double getShippingRates() {
        return shippingRates;
    }

    public void setShippingRates(double shippingRates) {
        this.shippingRates = shippingRates;
    }

    public double getToIssueDebt() {
        return toIssueDebt;
    }

    public void setToIssueDebt(double toIssueDebt) {
        this.toIssueDebt = toIssueDebt;
    }

    public double getBackBeiBi() {
        return backBeiBi;
    }

    public void setBackBeiBi(double backBeiBi) {
        this.backBeiBi = backBeiBi;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public double getCustomaryDues() {
        return customaryDues;
    }

    public void setCustomaryDues(double customaryDues) {
        this.customaryDues = customaryDues;
    }
}
