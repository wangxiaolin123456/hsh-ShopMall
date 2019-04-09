package com.example.administrator.merchants.common;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.administrator.merchants.http.show.StoreShowBean;


/**
 * 作者：韩宇 on 2017/6/22 0022 10:25
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：用户信息储存
 */

public class UserInfo {
    private StoreShowBean user = null;
    private static volatile UserInfo mInstance = null;
    private SharedPreferences sh;

    public static final UserInfo getInstance() {
        if (mInstance == null) {
            synchronized (UserInfo.class) {
                if (mInstance == null) {
                    mInstance = new UserInfo();
                }
            }
        }
        return mInstance;
    }

    public StoreShowBean getUser(Context context) {

        if (context != null) {
            sh = CommonUtil.setSharedPreferences(context);
            if ("have".equals(sh.getString("id", "0"))) {

                StoreShowBean storeMessageBean = new StoreShowBean();
                storeMessageBean.setStoreid(sh.getString("storeid", "storeid"));
                storeMessageBean.setStorename(sh.getString("storename", "storename"));
                storeMessageBean.setStorepassword(sh.getString("storepassword", "storepassword"));
                storeMessageBean.setImgfile(sh.getString("imgfile", "imgfile"));
                storeMessageBean.setStorephone(sh.getString("storephone", "storephone"));
                storeMessageBean.setBeibi(sh.getString("beibi", "beibi"));
                storeMessageBean.setTime(sh.getString("time", "time"));
                storeMessageBean.setLevelno(sh.getString("levelno", "0"));
                user = storeMessageBean;
                return user;
            }
        }

        return null;
    }

    public void setUser(StoreShowBean user, Context context) {
        this.user = user;
        if (context != null) {
            sh = CommonUtil.setSharedPreferences(context);
            SharedPreferences.Editor editor = sh.edit();
            if (user == null) {
                editor.putString("id", "null");
                editor.commit();
            } else {
                editor.putString("storeid", user.getStoreid());
                editor.putString("storename", user.getStorename());
                editor.putString("storepassword", user.getStorepassword());
                editor.putString("imgfile", user.getImgfile());
                editor.putString("storephone", user.getStorephone());
                editor.putString("beibi", user.getBeibi());
                editor.putString("time", user.getTime());
                editor.putString("levelno", user.getLevelno());
                editor.putString("id", "have");
                editor.commit();
            }
        }
    }

    public boolean isUserLogin(Context context) {
        sh = CommonUtil.setSharedPreferences(context);
        if (user == null) {
            if ("have".equals(sh.getString("id", "0"))) {
                StoreShowBean storeMessageBean = new StoreShowBean();
                storeMessageBean.setStoreid(sh.getString("storeid", "storeid"));
                storeMessageBean.setStorename(sh.getString("storename", "storename"));
                storeMessageBean.setStorepassword(sh.getString("storepassword", "storepassword"));
                storeMessageBean.setImgfile(sh.getString("imgfile", "imgfile"));
                storeMessageBean.setStorephone(sh.getString("storephone", "storephone"));
                storeMessageBean.setStorephone(sh.getString("beibi", "beibi"));
                storeMessageBean.setLevelno(sh.getString("levelno", "0"));
                storeMessageBean.setTime(sh.getString("time", "time"));
                user = storeMessageBean;
            } else {
                user = null;
            }

            //如果文件里user为空此方法返回false，反之返回true
        }
        return user != null;
    }

}
