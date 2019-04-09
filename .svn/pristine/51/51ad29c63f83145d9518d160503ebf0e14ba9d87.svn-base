package com.example.administrator.merchants.common;

import android.app.Activity;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.administrator.merchants.application.MutualApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：历史记录保存工具
 */
public class SharedPreferenceUtil {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private static SharedPreferenceUtil instence;
    double curLat = 32.0;
    double curLon = 118.0;

    private SharedPreferenceUtil() {
        preferences = MutualApplication.getIntence().getSharedPreferences("mutual", Activity.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public static SharedPreferenceUtil getInstence() {
        if (instence == null) {
            instence = new SharedPreferenceUtil();
        }
        return instence;
    }

    public void setKey(String value) {
        editor.putString("key", value);
        editor.commit();
    }

    public void getKey() {
        preferences.getString("key", "");
    }

    public void setDayShow(boolean value) {
        editor.putBoolean("key", value);
        editor.commit();
    }

    public boolean getDayShow() {
        return preferences.getBoolean("key", false);
    }

    public void setImg(String value) {
        editor.putString("Img", value);
        editor.commit();
    }

    public String getImg() {
        return preferences.getString("Img", "");
    }

    public void setAddress(String address) {
        editor.putString("address", address);
        editor.commit();
    }

    public String getAddress() {
        return preferences.getString("address", "");
    }

    public void setShow(boolean isshow) {
        editor.putBoolean("isShow", true);
        editor.commit();
    }

    public boolean getShow() {
        return preferences.getBoolean("isShow", false);
    }

    public void setAddIndex(int value) {
        editor.putInt("index", value);
        editor.commit();
    }

    public int getIndex() {
        return preferences.getInt("index", 0);
    }

    public void setMainIndex(int value) {
        editor.putInt("index", value);
        editor.commit();
    }

    public int getMainIndex() {
        if (!preferences.contains("index")) {
            return 0;
        }
        return preferences.getInt("index", 0);
    }

    public void setIsFirstRun() {
        editor.putBoolean("is_first_run", false);
        editor.commit();
    }

    public boolean getIsFirstRun() {
        return preferences.getBoolean("is_first_run", true);
    }

    public List<String> getSearchHistory() {
        List<String> history = new ArrayList<String>();
        String his = preferences.getString("search_history", "");
        if (TextUtils.isEmpty(his)) {
            return history;
        }
        String[] array = his.split(";");
        for (int i = 0; i < array.length; i++) {
            history.add(array[i]);
        }
        return history;
    }

    public void addSearchHistory(String key) {
        if (TextUtils.isEmpty(key)) {
            return;
        }
        List<String> pre = getSearchHistory();
        if (pre.contains(key)) {
            pre.remove(key);
        }
        pre.add(0, key);
        StringBuffer newhis = new StringBuffer();
        for (int i = 0; i < pre.size(); i++) {
            newhis.append(pre.get(i) + ";");
        }
        editor.putString("search_history", newhis.toString());
        editor.commit();
    }

    public void deleteSearchHistory() {
        editor.putString("search_history", "");
        editor.commit();
    }

    public void setLat(String value) {
        editor.putString("lat", value);
        editor.commit();
    }

    public double getLat() {
        return Double.parseDouble(preferences.getString("lat", ""));
    }

    public void setLon(String value) {
        editor.putString("lon", value);
        editor.commit();
    }

    public double getLon() {
        return Double.parseDouble(preferences.getString("lon", ""));
    }
}
