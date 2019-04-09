package com.example.administrator.merchants.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.common.GlideTest;


/**
 * 作者：韩宇 on 2017/6/23 0023 14:39
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：轮播图放大
 */
public class EnlargePictureActivity extends Activity {
    private ImageView imageView;
    private String bigimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enlarge_picture);
        bigimage=getIntent().getStringExtra("bigimage");
        imageView = (ImageView) findViewById(R.id.big_image_image);
        GlideTest.image(getApplicationContext(), bigimage, imageView,R.drawable.image_loading);
        ImageView back = (ImageView) findViewById(R.id.aaaa);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
