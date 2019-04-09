package com.example.administrator.merchants.http.show;

import android.net.Uri;

import java.io.File;

/**
 * 作者：韩宇 on 2017/6/23 0023 14:39
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：图片url
 */
public class ImageUriShowBean {
    private int type;
    private Uri uri;
    private File file;

    public void setFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}
