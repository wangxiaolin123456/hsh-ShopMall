package com.example.administrator.merchants.views.v.popupwindow;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * 上头导航栏需要
 */
public class ViewColor {
    private static Boolean isAddStateBar = false;

    /**
     * 生成一个和状态栏大小相同的矩形条 * * @param activity 需要设置的activity * * @param color 状态栏颜色值 * * @return 状态栏矩形条
     */
    private static View createStatusView(Activity activity, int color) {
        // 获得状态栏高度
        int statusBarHeight = getStatusBarHeight(activity);
        // 绘制一个和状态栏一样高的矩形
        View statusView = new View(activity);
        int screenWidth = activity.getResources().getDisplayMetrics().widthPixels;
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(screenWidth, statusBarHeight);
        statusView.setLayoutParams(params);
        statusView.setBackgroundColor(color);
        return statusView;
    }

    public static int getStatusBarHeight(Activity activity) {
        int statusBarHeight = 0;
        if (activity != null) {
            int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
            statusBarHeight = activity.getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }

    /**
     * 设置状态栏颜色 * * @param activity 需要设置的activity * @param color 状态栏颜色值
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public static void setColor(Activity activity, int color, boolean isImage) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { // 设置状态栏透明
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (!isImage) {
                ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
                if (isAddStateBar) {
                    View statusView = decorView.getChildAt(2);
                    if (statusView != null)
                        statusView.setBackgroundColor(color);
                } else {
                    // 生成一个状态栏大小的矩形
                    View mStatusView = createStatusView(activity, color);
                    // 添加 statusView 到布局中
                    decorView.addView(mStatusView);
                }
                isAddStateBar = true;
            } else {
                if (isAddStateBar) {
                    ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
                    if (decorView.getChildAt(2) != null) {
                        decorView.removeViewAt(2);
                    }
                    isAddStateBar = false;
                }
            }
            // 设置根布局的参数
            ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
            rootView.setFitsSystemWindows(!isImage);
            rootView.setClipToPadding(!isImage);
        }
    }


    /**
     * 测量View的宽高
     *
     * @param view View
     */
    public static void measureWidthAndHeight(View view) {
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(widthMeasureSpec, heightMeasureSpec);
    }

    public static int h(Context context){
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        int emailH = width * 4 / 5;
        return emailH;
    }
    public static int H(Context context){
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        int emailH = height * 3 / 4;
        return emailH;
    }
}