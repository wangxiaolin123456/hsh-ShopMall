package com.example.administrator.merchants.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.common.CommonUtil;
import com.example.administrator.merchants.common.GlideTest;
import com.example.administrator.merchants.dialog.MineAvatarDialog;
import com.example.administrator.merchants.common.toast.CustomToast;

import java.io.File;
import java.text.SimpleDateFormat;

/**
 * 作者：韩宇 on 2017/6/23 0023 14:39
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：修改商家商品图片
 */
public class CommodityPictureUpdateActivity extends Activity implements MineAvatarDialog.BackChoose {
    private ImageView imageView;
    private TextView textView;
    private File tempFile;
    private static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private String bigimage,posstion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_big_image);
        bigimage=getIntent().getStringExtra("bigimage");
        posstion=getIntent().getStringExtra("posstion");
        imageView = (ImageView) findViewById(R.id.big_image_image);
        textView = (TextView) findViewById(R.id.tihuan);
        GlideTest.image(getApplicationContext(), bigimage, imageView,R.drawable.image_loading);
        ImageView back = (ImageView) findViewById(R.id.aaaa);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("update".equals(getIntent().getStringExtra("type"))) {
                    CommonUtil.update_update = true;
                    CommonUtil.update_posstion = Integer.parseInt(posstion);
                } else {
                    CommonUtil.add_update = true;
                    CommonUtil.add_posstion = Integer.parseInt(posstion);
                }
                MineAvatarDialog dialog = new MineAvatarDialog(CommodityPictureUpdateActivity.this, R.style.Dialog);
                Window window = dialog.getWindow();
                window.setGravity(Gravity.CENTER);
                dialog.show();
                dialog.getChoose(CommodityPictureUpdateActivity.this);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            if (data != null) {
                Uri uri = data.getData();
                Intent intent = new Intent();
                if ("update".equals(getIntent().getStringExtra("type"))) {
                    intent.setClass(CommodityPictureUpdateActivity.this, MerchantUpdateGoodsActivity.class);
                } else {
                    intent.setClass(CommodityPictureUpdateActivity.this, MerchantAddGoodsActivity.class);//GoodsDetailEditActivityAdd
                }
                intent.putExtra("photo", uri.toString());//二级菜单id
                setResult(9, intent);
                finish();
            }
        } else if (requestCode == PHOTO_REQUEST_CAREMA) {
            if (hasSdcard()) {
                if (tempFile.exists()) {
                    Uri uri = Uri.fromFile(tempFile);
                    CommonUtil.fileList.add(tempFile);
                    Intent intent = new Intent();
                    if ("update".equals(getIntent().getStringExtra("type"))) {
                        intent.setClass(CommodityPictureUpdateActivity.this, MerchantUpdateGoodsActivity.class);
                    } else {
                        intent.setClass(CommodityPictureUpdateActivity.this, MerchantAddGoodsActivity.class);
                    }
                    intent.putExtra("photo", uri.toString());//二级菜单id
                    setResult(9, intent);
                    finish();
                }
            } else {
                CustomToast.getInstance(CommodityPictureUpdateActivity.this).setMessage("未找到存储卡，无法存储照片！");
            }
        }
    }

    /**
     * 从相册获取
     */
    public void gallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }

    /**
     * 从相机获取
     */
    public void camera() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        final String date = sDateFormat.format(new java.util.Date());
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (hasSdcard()) {
            tempFile = new File(Environment.getExternalStorageDirectory(),
                    date + ".jpg");
            Uri uri = Uri.fromFile(tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        startActivityForResult(intent, PHOTO_REQUEST_CAREMA);
    }

    /**
     * 判断sdcard是否被挂载
     */
    private boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void isChoose(boolean choose) {
        if (choose) {
            gallery();
        } else {
            camera();
        }
    }
}
