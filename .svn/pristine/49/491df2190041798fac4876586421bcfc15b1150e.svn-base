package com.example.administrator.merchants.common;

import java.math.BigDecimal;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：BigDecimal运算
 */
public class MyMath {
    /**
     * 加法
     *
     * @param d1
     * @param d2
     * @return
     */
    public static double add(String d1, String d2) {
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.add(b2).doubleValue();
    }

    /**
     * 减法
     *
     * @param d1
     * @param d2
     * @return
     */
    public static double sub(String d1, String d2) {
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 乘法
     *
     * @param d1
     * @param d2
     * @return
     */
    public static double mul(String d1, String d2) {
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 除法
     *
     * @param d1
     * @param d2
     * @param len
     * @return
     */
    public static double div(String d1, String d2, int len) {
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.divide(b2, len, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
