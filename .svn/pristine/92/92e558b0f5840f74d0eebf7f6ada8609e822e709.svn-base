package com.example.administrator.merchants.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.common.GlideTest;
import com.example.administrator.merchants.common.UserInfo;
import com.nineoldandroids.view.ViewPropertyAnimator;

import java.util.ArrayList;

/**
 * 作者：韩宇 on 2017/8/18 0018 09:05
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：订单
 */
public class OrderFragment extends Fragment {
    private View view;
    private RelativeLayout relativeLayout;
    private ImageView anim;
    private ArrayList<Fragment> fragments;
    private ViewPager viewPager;
    private TextView goodOrder;
    private TextView originOrder;
    private int line_width;
    private View line;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_order, null);
        anim = (ImageView) view.findViewById(R.id.image_loading);
        GlideTest.imageGif(getContext(), anim);
        line = view.findViewById(R.id.line);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.rel);//如果是个体商户 则整条隐藏
        //地址链接
        goodOrder = (TextView) view.findViewById(R.id.activity_order_orders_goods);
        originOrder = (TextView) view.findViewById(R.id.activity_order_orders_origin);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        login();
        // 初始化TextView动画
        ViewPropertyAnimator.animate(goodOrder).scaleX(1.2f).setDuration(0);
        ViewPropertyAnimator.animate(goodOrder).scaleY(1.2f).setDuration(0);
        fragments = new ArrayList();
        fragments.add(new OriginOrderFragment());
        fragments.add(new MerchantOrderFragment());
        if (getActivity() != null) {
            line_width = getActivity().getWindowManager().getDefaultDisplay().getWidth()
                    / fragments.size();
        }
        line.getLayoutParams().width = line_width;
        line.requestLayout();
        viewPager.setAdapter(new FragmentStatePagerAdapter(
                getActivity().getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return fragments.get(arg0);
            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
                changeState(arg0);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                float tagerX = arg0 * line_width + arg2 / fragments.size();
                ViewPropertyAnimator.animate(line).translationX(tagerX)
                        .setDuration(0);
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
        goodOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                viewPager.setCurrentItem(0);
            }
        });
        originOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                viewPager.setCurrentItem(1);
            }
        });
        return view;
    }

    /**
     * 商家类型决定订单样式
     */
    public void login() {
        if ("1".equals(UserInfo.getInstance().getUser(getContext()).getLevelno()) || "2".equals(UserInfo.getInstance().getUser(getContext()).getLevelno())) {
            //中小企业
            relativeLayout.setVisibility(View.GONE);
        } else {
            relativeLayout.setVisibility(View.VISIBLE);
        }
        viewPager.setCurrentItem(0);
        GlideTest.imageCancle(anim);
    }

    /**
     * 根据传入的值来改变状态
     *
     * @param arg0
     */
    private void changeState(int arg0) {
        if (arg0 == 0) {
            goodOrder.setTextColor(getResources().getColor(R.color.themeColor));
            originOrder.setTextColor(getResources().getColor(R.color.black));
            ViewPropertyAnimator.animate(goodOrder).scaleX(1.2f).setDuration(200);
            ViewPropertyAnimator.animate(goodOrder).scaleY(1.2f).setDuration(200);
            ViewPropertyAnimator.animate(originOrder).scaleX(1.0f)
                    .setDuration(200);
            ViewPropertyAnimator.animate(originOrder).scaleY(1.0f)
                    .setDuration(200);
        } else if (arg0 == 1) {
            originOrder.setTextColor(getResources().getColor(R.color.themeColor));
            goodOrder.setTextColor(getResources().getColor(R.color.black));
            ViewPropertyAnimator.animate(originOrder).scaleX(1.2f).setDuration(200);
            ViewPropertyAnimator.animate(originOrder).scaleY(1.2f).setDuration(200);
            ViewPropertyAnimator.animate(goodOrder).scaleX(1.0f)
                    .setDuration(200);
            ViewPropertyAnimator.animate(goodOrder).scaleY(1.0f)
                    .setDuration(200);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden == false) {
            login();
        }
    }
}
