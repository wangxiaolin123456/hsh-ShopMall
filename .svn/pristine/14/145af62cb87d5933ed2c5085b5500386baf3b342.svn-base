package com.example.administrator.merchants.common;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * 作者：韩宇 on 2017/8/1 0001 10:26
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：图片压缩
 */
public class PicUtils {
    /**
     * 图片压缩
     *
     * @param srcPath
     * @param newPath
     */
    public static void compress(String srcPath, String newPath) {
        float hh = 1280;
        float ww = 720;
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, opts);
        opts.inJustDecodeBounds = false;
        int w = opts.outWidth;
        int h = opts.outHeight;
        int size = 0;
        if (w <= ww && h <= hh) {
            size = 1;
        } else {
            double scale = w >= h ? w / ww : h / hh;
            double log = Math.log(scale) / Math.log(2);
            double logCeil = Math.ceil(log);
            size = (int) Math.pow(2, logCeil);
        }
        opts.inSampleSize = size;
        bitmap = BitmapFactory.decodeFile(srcPath, opts);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int quality = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        System.out.println(baos.toByteArray().length);
        while (baos.toByteArray().length > 200 * 1024) {
            baos.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
            if (quality > 20) {
                quality -= 20;
            } else {
                break;
            }
            System.out.println(baos.toByteArray().length);
        }
        try {
            File file = new File(newPath);
            FileOutputStream fos = new FileOutputStream(file);//将压缩后的图片保存的本地上指定路径中
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
