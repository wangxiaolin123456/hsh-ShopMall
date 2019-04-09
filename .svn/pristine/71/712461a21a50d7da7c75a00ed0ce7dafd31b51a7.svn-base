package com.example.administrator.merchants.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.administrator.merchants.R;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：拍照
 */
public class MineAvatarDialog extends Dialog implements View.OnClickListener {
    private Context context;
    private TextView photograph;//拍照
    private TextView photoAlbum;//相册
    private TextView collOff;//相册
    private BackChoose mBackChoose;
    private TextView cancle_too;

    public MineAvatarDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(
                R.layout.photo_select_dialog, null);
        setContentView(view);
        getWindow().getAttributes().width = WindowManager.LayoutParams.MATCH_PARENT;
        photograph = (TextView) findViewById(R.id.dialog_avatar_photograph);
        photoAlbum = (TextView) findViewById(R.id.dialog_avatar_photoAlbum);
        collOff = (TextView) findViewById(R.id.dialog_avatar_cancel);
        cancle_too= (TextView) findViewById(R.id.cancel_too);
        cancle_too.setOnClickListener(this);
        collOff.setOnClickListener(this);
        photograph.setOnClickListener(this);
        photoAlbum.setOnClickListener(this);
    }

    public void getChoose(BackChoose c) {
        this.mBackChoose = c;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_avatar_photograph:
                mBackChoose.isChoose(false);
                dismiss();
                break;
            case R.id.dialog_avatar_photoAlbum:
                mBackChoose.isChoose(true);
                dismiss();
                break;
            case R.id.cancel_too:
                dismiss();
                break;
            default:
                dismiss();
        }
    }

    public interface BackChoose {
        public void isChoose(boolean choose);
    }
}
