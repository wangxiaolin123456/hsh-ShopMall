package com.example.administrator.merchants.views.v.scroll;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.administrator.merchants.R;
import com.example.administrator.merchants.views.v.ImageManger;



public class GlideManager extends ImageManger {

    // Glide 的详细介绍网址 http://blog.csdn.net/kai_1215/article/details/51831511

    // imgUrl 图片请求地址
    // imageView 要绑定的视图组件
    // error 显示图片加载错误的图片
    // placeholder 预加载图片


    private static GlideManager sInstance;

    public static GlideManager getsInstance() {
        // 如果sInstance实例对象为空,则创建,否则直接返回
        if (sInstance == null) {
            sInstance = new GlideManager();
        }
        return sInstance;
    }


    private GlideManager() {
    }

    public void loadImageView(Context c, String imgUrl, ImageView imageView) {
        Glide.with(c)
                .load(imgUrl)
                .error(R.drawable.icon_error)
                .crossFade()
                .into(imageView);
    }

    public void loadImageViewH(Context c, String imgUrl, final ImageView imageView) {
        Glide.with(c)
                .load(imgUrl)
                .error(R.drawable.icon_error)
                .crossFade()
                .into(new GlideDrawableImageViewTarget(imageView) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                        super.onResourceReady(resource, animation);
                        if (null != resource) {
                            float width = resource.getIntrinsicWidth();
                            float height = resource.getIntrinsicHeight();
                            float ivWidth = imageView.getWidth();
                            if (ivWidth == 0) {
                                ivWidth = imageView.getResources().getDisplayMetrics().widthPixels;
                            }
                            int ivHeight = (int) (height / width * ivWidth);
                            ViewGroup.LayoutParams lp = imageView.getLayoutParams();
                            lp.height = ivHeight;
                            imageView.setLayoutParams(lp);
                        }
                    }
                });
    }
}
