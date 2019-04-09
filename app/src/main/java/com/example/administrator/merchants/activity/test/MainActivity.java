package com.example.administrator.merchants.activity.test;

import android.Manifest;
import android.app.ActionBar;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.administrator.merchants.R;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseFragmentActivity;
import com.example.administrator.merchants.common.GlideTest;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.fragment.HomePageFragment;
import com.example.administrator.merchants.fragment.MyFragment;
import com.example.administrator.merchants.fragment.OrderFragment;
import com.example.administrator.merchants.fragment.VerificationFragment;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.post.LoginPostBean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Timer;
import java.util.TimerTask;

import cn.jpush.android.api.JPushInterface;

/**
 * 作者：韩宇 on 2017/6/23 0023 14:39
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：程序入口
 */
public class MainActivity extends BaseFragmentActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup radioGroup;
    private RadioButton radioButtonOne;//首页按钮
    private RadioButton radioButtonTwo;//验证按钮
    public static TextView notice;//公告
    public static RelativeLayout relativeLayout;//公告布局
    private String fragment1Tag = "fragment1Tag";//首页
    private String fragment2Tag = "fragment2Tag";//验证
    private String fragment3Tag = "fragment3Tag";//订单
    private String fragment4Tag = "fragment4Tag";//我的
    private View PopupWindowLine;//展示我的专属二维码时用到这条线
    private static boolean mBackKeyPressed = false;//记录是否有首次按键
    private HomePageFragment homeFragment;
    private FragmentTransaction ft;
    private FragmentManager fm;
    private static final int BAIDU_READ_PHONE_STATE =100;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PopupWindowLine = findViewById(R.id.PopupWindowLine);
        notice = (TextView) findViewById(R.id.myTextView);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        radioGroup = (RadioGroup) findViewById(R.id.main_activity_radioGroup);
        radioGroup.setOnCheckedChangeListener(this);
        radioButtonOne = (RadioButton) findViewById(R.id.main_radioOne);
        radioButtonTwo = (RadioButton) findViewById(R.id.main_radioTwo);
        checkNotificationAuthority();//检查通知权限
        radioButtonOne.setChecked(true);
        Http.getUpdateInfo(MainActivity.this, getVersionName(),0);//检测更新app
    }

    /**
     * 检查通知权限
     */
    private void checkNotificationAuthority() {
        if (checkPermission(MainActivity.this) == false) {
            new android.app.AlertDialog.Builder(MainActivity.this)
                    .setMessage("请打开允许通知！")
                    .setCancelable(false)
                    .setPositiveButton("现在设置", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            intent.setData(Uri.parse("package:" + MainActivity.this.getPackageName()));
                            startActivity(intent);
                        }
                    })
                    .show();
        }
    }

    /**
     * 隐性登录
     */
    private void invisibleLogin() {
        LoginPostBean loginPostBean = new LoginPostBean();
        loginPostBean.setStorephone(UserInfo.getInstance().getUser(this).getStorephone());
        loginPostBean.setStorepassword(UserInfo.getInstance().getUser(this).getStorepassword());
        loginPostBean.setDevicetype("Android");
        loginPostBean.setDevicenumber(JPushInterface.getRegistrationID(MainActivity.this));
        Http.invisibleLogin(MainActivity.this, loginPostBean, radioButtonTwo);

    }

    /**
     * 获取当前版本号
     *
     * @return
     */
    private String getVersionName() {
        PackageManager packageManager = getPackageManager();//getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packInfo.versionName;
    }

    @Override
    protected void onResume() {
        super.onResume();
        invisibleLogin();//隐形登录
    }

    @Override
    protected void onStop() {
        super.onStop();
        MutualApplication.getRequestQueue().cancelAll("getUpdateInfo40");
        MutualApplication.getRequestQueue().cancelAll("invisibleLogin41");
    }


    // 用来计算返回键的点击间隔时间
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                //弹出提示，可以有多种方式
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
    /**
     * 我的二维码展示
     *
     * @param twoDimensionCode
     * @param describeText
     */
    public void myTwoDimensionalCode(String twoDimensionCode, String describeText) {
        View popupWindowView = LayoutInflater.from(this).inflate(R.layout.view_popup_two_dimension_code, null);
        final PopupWindow popupWindow = new PopupWindow(popupWindowView, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(PopupWindowLine);
        TextView delete = (TextView) popupWindowView.findViewById(R.id.view_popup_two_dimension_code_delete);//取消PopupWindow
        TextView describe = (TextView) popupWindowView.findViewById(R.id.view_popup_two_dimension_code_describe);//二维码功能描述
        ImageView imageView = (ImageView) popupWindowView.findViewById(R.id.view_popup_two_dimension_code);//二维码显示
        GlideTest.image(this, twoDimensionCode, imageView,R.drawable.image_loading);//二维码加载
        describe.setText(describeText);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

    }

    //首页底部监听
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
         fm = getSupportFragmentManager();
         ft = fm.beginTransaction();
        HomePageFragment homePageFragment = (HomePageFragment) fm.findFragmentByTag(fragment1Tag);//首页
        VerificationFragment verificationFragment = (VerificationFragment) fm.findFragmentByTag(fragment2Tag);//验证
        OrderFragment orderFragment = (OrderFragment) fm.findFragmentByTag(fragment3Tag);//订单
        MyFragment myFragment = (MyFragment) fm.findFragmentByTag(fragment4Tag);//我的
        if (homePageFragment != null) {
            ft.hide(homePageFragment);
        }
        if (verificationFragment != null) {
            ft.hide(verificationFragment);
        }
        if (orderFragment != null) {
            ft.hide(orderFragment);
        }
        if (myFragment != null) {
            ft.hide(myFragment);
        }
        switch (checkedId) {
            case R.id.main_radioOne:
                //显示消息 现设置隐藏
                notice.setVisibility(View.GONE);
                relativeLayout.setVisibility(View.GONE);
                if (homePageFragment == null) {
                    homePageFragment = new HomePageFragment();
                    ft.add(R.id.main_activity_frameLayout, homePageFragment, fragment1Tag);
                } else {
                    ft.show(homePageFragment);
                }
                break;
            case R.id.main_radioTwo:
                notice.setVisibility(View.GONE);
                relativeLayout.setVisibility(View.GONE);
                if (verificationFragment == null) {
                    verificationFragment = new VerificationFragment();
                    ft.add(R.id.main_activity_frameLayout, verificationFragment, fragment2Tag);
                } else {
                    ft.show(verificationFragment);
                }
                break;
            case R.id.main_radioThree:
                notice.setVisibility(View.GONE);
                relativeLayout.setVisibility(View.GONE);
                if (orderFragment == null) {
                    orderFragment = new OrderFragment();
                    ft.add(R.id.main_activity_frameLayout, orderFragment,
                            fragment3Tag);
                } else {
                    ft.show(orderFragment);
                }
                break;
            case R.id.main_radioFour:
                notice.setVisibility(View.GONE);
                relativeLayout.setVisibility(View.GONE);
                //个人中心fragment
                if (myFragment == null) {
                    myFragment = new MyFragment();
                    ft.add(R.id.main_activity_frameLayout, myFragment, fragment4Tag);
                } else {
                    ft.show(myFragment);
                }
                break;
        }
        ft.commit();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            RadioButton mTab = (RadioButton) radioGroup.getChildAt(i);
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = fm.findFragmentByTag((String) mTab.getTag());
            FragmentTransaction ft = fm.beginTransaction();
            if (fragment != null) {
                if (!mTab.isChecked()) {
                    ft.hide(fragment);
                }
            }
            ft.commit();
        }
    }

    /**
     * @param context
     * @return true 代表有有权限，或者检测失败   返回false代表没有权限
     */
    public static boolean checkPermission(Context context) {
        if (Build.VERSION.SDK_INT < 18 || Build.VERSION.SDK_INT > 22) {
            return true;
        }
        AppOpsManager mAppOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        Class<? extends AppOpsManager> class1 = mAppOps.getClass();
        try {
            Method method = class1.getDeclaredMethod("noteOpNoThrow", int.class, int.class, String.class);
            if (method.invoke(mAppOps, 11, Binder.getCallingUid(), context.getPackageName()).equals(AppOpsManager.MODE_ALLOWED)) {
                return true;
            } else {
                return false;
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return true;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return true;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return true;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return true;
        }
    }



    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        processExtraData();
    }

    private Intent homeIntent;

    private void processExtraData() {

        homeIntent = getIntent();

        if (homeIntent != null) {
            if (homeIntent.getIntExtra("id", 0) == 100) {
                if (homeFragment == null) {
                    homeFragment = new HomePageFragment();
                    ft.add(R.id.main_activity_frameLayout, homeFragment, fragment1Tag);
                    ft.show(homeFragment);
                } else {
                    ft.show(homeFragment);
                }
                radioButtonOne.setChecked(true);
            }

        }

    }

}
