package com.example.administrator.merchants.fragment;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.diy.widget.CircularImage;
import com.example.administrator.merchants.R;
import com.example.administrator.merchants.activity.AboutUsActivity;
import com.example.administrator.merchants.activity.AccumulatedIncomeActivity;
import com.example.administrator.merchants.activity.AddressListActivity;
import com.example.administrator.merchants.activity.BeiBiManagementActivity;
import com.example.administrator.merchants.activity.EstimatedEarningsActivity;
import com.example.administrator.merchants.activity.HSHStrategyActivity;
import com.example.administrator.merchants.activity.JinbeiCoinActivity;
import com.example.administrator.merchants.activity.LoginActivity;
import com.example.administrator.merchants.activity.UpdatePasswordActivity;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseFragment;
import com.example.administrator.merchants.common.CommonUtil;
import com.example.administrator.merchants.common.GlideTest;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.common.toast.CustomToast;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.HttpUrl;
import com.example.administrator.merchants.http.post.StoreIdPostBean;
import com.example.administrator.merchants.utils.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 作者：韩宇 on 2017/8/18 0018 09:05
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：我的
 */
public class MyFragment extends BaseFragment {
    @BindView(R.id.y_unit_tv)
    TextView yUnitTv;
    @BindView(R.id.j_unit_tv)
    TextView jUnitTv;
    private TextView now;
    private TextView updateAdd, newMost;
    public View popupWindowView;
    private PopupWindow popupWindow;
    public static String nowNew;
    private PackageManager packageManager;
    private TextView forecastEarnings, accumulatedIncome;//预估收益
    @BindView(R.id.mine_head_icon)
    CircularImage circularImage;//头像
    @BindView(R.id.mine_name_tv)
    TextView nameTv;//昵称
    @BindView(R.id.mine_account_tv)
    TextView accountTv;//账号
    private Unbinder unbinder;
    private Intent intent;
    @BindView(R.id.clear_cache_line)
    LinearLayout wipeCache;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, null);
        unbinder = ButterKnife.bind(this, view);
        nowNew = getVersionName();
        newMost = (TextView) view.findViewById(R.id.nnew);
        forecastEarnings = (TextView) view.findViewById(R.id.forecast_earn);//预估收益
        accumulatedIncome = (TextView) view.findViewById(R.id.leiji);
        now = (TextView) view.findViewById(R.id.dangqian);
        updateAdd = (TextView) view.findViewById(R.id.fragment_main_my_update_yes_add);
        now.setText(nowNew);
        Http.getUpdateInfo(getActivity(), updateAdd, newMost);//获取当前版本
        return view;
    }

    @OnClick({R.id.mine_address_line, R.id.share_app_line, R.id.hsh_strategy_line,
            R.id.mine_about_us_line, R.id.mine_custom_service_line, R.id.clear_cache_line,
            R.id.mine_update_pwd_line, R.id.mine_version_check_line, R.id.mine_unit_manage_line,
            R.id.fragment_main_my_logout, R.id.mine_estimated_revenue_line, R.id.mine_accumulated_income_line
            , R.id.y_unit_line, R.id.j_unit_line})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mine_address_line:
                //收获地址
                intent = new Intent();
                intent.setClass(getContext(), AddressListActivity.class);
                startActivity(intent);
                break;
            case R.id.share_app_line:
                //分享app
                StoreIdPostBean storeIdPostBean = new StoreIdPostBean();
                storeIdPostBean.setStoreid(UserInfo.getInstance().getUser(getActivity()).getStoreid());
                Http.twoDimensionCode(getActivity(), storeIdPostBean);
                break;
            case R.id.hsh_strategy_line:
                //互实攻略
                intent = new Intent();
                intent.setClass(getContext(), HSHStrategyActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_about_us_line:
                //关于我们
                intent = new Intent();
                intent.setClass(getContext(), AboutUsActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_custom_service_line:
                //联系客服
                Http.customerTelephoneNumber(getActivity());
                break;
            case R.id.clear_cache_line:
                //清除缓存
                try {
                    PopYzmHint(getActivity(), wipeCache, "是否清空缓存:" + CommonUtil.getTotalCacheSize(getActivity()) + "?", "确定");
                    GlideTest.clearImageAllCache(getActivity());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.mine_update_pwd_line:
                //修改登录密码
                intent = new Intent();
                intent.putExtra("type", "login");
                intent.setClass(getContext(), UpdatePasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_version_check_line:
                //版本检测更新
                Http.getUpdateInfo(getActivity(), getVersionName(), 1);//检测更新app
                break;
            case R.id.mine_unit_manage_line:
                //贝币管理
                intent = new Intent();
                intent.setClass(getContext(), BeiBiManagementActivity.class);
                startActivity(intent);
                break;
            case R.id.fragment_main_my_logout:
                //退出登录
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("是否确认退出登录？");
                builder.setTitle("提示");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StoreIdPostBean storeIdPostBean = new StoreIdPostBean();
                        storeIdPostBean.setStoreid(UserInfo.getInstance().getUser(getActivity()).getStoreid());
                        Http.logOut(getActivity(), storeIdPostBean);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
                break;
            case R.id.mine_estimated_revenue_line:
                //预估收益跳转
                startActivity(new Intent(getActivity(), EstimatedEarningsActivity.class));
                break;
            case R.id.mine_accumulated_income_line:
                //累计收益跳转
                startActivity(new Intent(getActivity(), AccumulatedIncomeActivity.class));
                break;
            case R.id.y_unit_line:
                //银贝币
                intent = new Intent(getContext(), JinbeiCoinActivity.class);
                intent.putExtra("type", "y");
                intent.putExtra("yue",yUnitTv.getText().toString());
                startActivity(intent);
                break;
            case R.id.j_unit_line:
                //金贝币
                intent = new Intent(getContext(), JinbeiCoinActivity.class);
                intent.putExtra("type", "j");
                intent.putExtra("yue",jUnitTv.getText().toString());
                startActivity(intent);
                break;
        }
    }

    /**
     * 收益管理数据显示
     */
    public void revenue_head() {
        StoreIdPostBean storeIdPostBean = new StoreIdPostBean();
        storeIdPostBean.setStoreid(UserInfo.getInstance().getUser(getActivity()).getStoreid());
        Http.revenueManagement(getActivity(), storeIdPostBean, forecastEarnings, accumulatedIncome);
    }

    /**
     * 获取当前版本号
     *
     * @return
     */
    private String getVersionName() {
        packageManager = getContext().getPackageManager();//getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        if (getActivity() != null) {
            try {
                packInfo = packageManager.getPackageInfo(getActivity().getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return packInfo.versionName;
    }

    /**
     * 清除缓存弹出的dialog
     *
     * @param context
     * @param view
     * @param str
     * @param str1
     */
    public void PopYzmHint(Context context, View view, String str, String str1) {
        popupWindowView = View.inflate(context, R.layout.activity_version_update, null);
        popupWindow = new PopupWindow(popupWindowView,
                ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT, true);
        // 必须要有这句否则弹出popupWindow后监听不到Back键
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, 0, 0);
        // 让popupWindow获得焦点
        popupWindow.setFocusable(true);
        popupWindow.update();
        TextView tv_msg = (TextView) popupWindowView.findViewById(R.id.tv_msg);//清除的缓存  1.3kb
        tv_msg.setText(str);
        Button success = (Button) popupWindowView.findViewById(R.id.success);//确定
        success.setText(str1);
        success.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    CommonUtil.clearAllCache(getActivity());//清除缓存
                    if (getContext() != null) {
                        CustomToast.getInstance(getContext()).setMessage("已清除缓存！");
                    }
                    popupWindow.dismiss();
                }
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        MutualApplication.getRequestQueue().cancelAll("toMessageNumber104");
//        MutualApplication.getRequestQueue().cancelAll("getBalance14");
        MutualApplication.getRequestQueue().cancelAll("getUpdateInfo108");
        MutualApplication.getRequestQueue().cancelAll("twoDimensionCode109");
        MutualApplication.getRequestQueue().cancelAll("logOut110");
        MutualApplication.getRequestQueue().cancelAll("revenueManagement111");
        MutualApplication.getRequestQueue().cancelAll("customerTelephoneNumber112");
        MutualApplication.getRequestQueue().cancelAll("yBeiBi");
    }

    /**
     * 未读消息数量
     */
    public void getMessageNumber() {
//        StoreIdPostBean storeIdPostBean = new StoreIdPostBean();
//        storeIdPostBean.setStoreid(UserInfo.getInstance().getUser(getContext()).getStoreid());
//        Http.getMessageNumber(getActivity(), storeIdPostBean, messageRead);
    }

    //金贝币余额
    public void getBalance() {
        StoreIdPostBean storeIdPostBean = new StoreIdPostBean();
        storeIdPostBean.setStoreid(UserInfo.getInstance().getUser(getActivity()).getStoreid());
        Http.getBalance(getActivity(), storeIdPostBean, jUnitTv);
    }

    @Override
    public void onResume() {
        super.onResume();
//        getMessageNumber();
        if (UserInfo.getInstance().getUser(getContext()) == null) {
            Intent intent = new Intent();
            intent.putExtra("fromUpdatePasswordActivity", "changed");
            intent.setClass(getContext(), LoginActivity.class);
            startActivity(intent);
        } else {
            nameTv.setText(UserInfo.getInstance().getUser(getContext()).getStorename());
            accountTv.setText("账号: " + UserInfo.getInstance().getUser(getContext()).getStorephone());
            if (UserInfo.getInstance().getUser(getContext()).getImgfile().equals("")) {
                circularImage.setImageResource(R.drawable.icon);
            } else {
                Glide.with(getActivity().getApplicationContext()).load(UserInfo.getInstance().getUser(getContext()).getImgfile()).
                        diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.icon).into(circularImage);
            }
//            getBalance();
            yBeiBiBalance();
            revenue_head();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden == false) {
//            getMessageNumber();
            if (UserInfo.getInstance().getUser(getContext()) == null) {
                Intent intent = new Intent();
                intent.putExtra("fromUpdatePasswordActivity", "changed");
                intent.setClass(getContext(), LoginActivity.class);
                startActivity(intent);
            } else {
//                getBalance();
                yBeiBiBalance();
                revenue_head();
            }
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    /**
     *获取银贝币余额
     */
    private void yBeiBiBalance(){
        try {
            JSONObject obj = new JSONObject();
            obj.put("storeid",UserInfo.getInstance().getUser(getContext()).getStoreid());
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST, HttpUrl.usermessage, obj,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            LogUtil.i("银贝币余额"+response.toString());
                            try {
                                if ("true".equals(response
                                        .getString("success"))) {
                                    JSONObject jsonObject = response.getJSONObject("storeinfo");
                                    jUnitTv.setText(jsonObject.getString("beibiamt"));
                                    yUnitTv.setText(jsonObject.getDouble("liftsilver")+"");//银贝币余额
                                } else {
                                    if (getActivity() != null) {
                                        CustomToast.getInstance(getActivity()).setMessage(response.getString("message")
                                                + "");
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });
            jsonObjectRequest.setTag("yBeiBi");
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy
                    (500000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
