package com.example.administrator.merchants.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.text.InputFilter;
import android.widget.EditText;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 作者：韩宇 on 2017/6/22 0022 10:25
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：常量
 */
public class CommonUtil {
    public final static String userInfoName = "isFirst";//个人信息表名
    public static final String APP_ID = "wx1bac0fa2f655f7f5";//appID

    /**
     * @param context
     * @return
     */
    public static SharedPreferences setSharedPreferences(Context context) {
        return context.getSharedPreferences(userInfoName, Context.MODE_PRIVATE);
    }

    public static boolean update_update = false;
    public static boolean add_update = false;
    public static int update_posstion = 0;
    public static int add_posstion = 0;
    public static String deleteFile = "";
    public static List<File> fileList = new ArrayList<>();

    /**
     * 清空拍照无用图片
     */
    public static void clear() {
        for (int i = 0; i < CommonUtil.fileList.size(); i++) {
            CommonUtil.fileList.get(i).delete();
        }
        CommonUtil.fileList.clear();
    }

    public static void clearAllCache(Context context) {
        deleteDir(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            deleteDir(context.getExternalCacheDir());
        }
    }

    private static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                // 如果下面还有文件
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);
                } else {
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return "0K";
        }
        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }

    public static String getTotalCacheSize(Context context) throws Exception {
        long cacheSize = getFolderSize(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            cacheSize += getFolderSize(context.getExternalCacheDir());
        }
        return getFormatSize(cacheSize);
    }

    /**
     * 控制输入特殊符号
     *
     * @param str
     * @return
     */
    public static boolean compileExChar(String str) {
        String limitEx = "[`'#]";
        Pattern pattern = Pattern.compile(limitEx);
        Matcher m = pattern.matcher(str);
        return m.find();
    }

    /**
     * 输入字符最大长度
     *
     * @param editText
     * @param num
     */
    public static void editTextLength(EditText editText, int num) {
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(num)});
    }
}
