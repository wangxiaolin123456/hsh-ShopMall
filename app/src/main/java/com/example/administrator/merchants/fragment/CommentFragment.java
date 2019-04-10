package com.example.administrator.merchants.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.adapter.CommonAdapter;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseFragment;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.post.CommonPostBean;
import com.example.administrator.merchants.http.post.MerIdPostBean;
import com.example.administrator.merchants.http.show.GoodsDetailShowBean;
import com.example.administrator.merchants.http.show.ImageShowBean;
import com.example.administrator.merchants.common.views.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：评论
 */
public class CommentFragment extends BaseFragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener,
        RefreshLayout.OnLoadListener {
    private View view;
    private Button goodReputationBtn;//好评
    private Button allBtn;//全部
    private Button middleEvaluationBtn;//中评
    private Button negativeCommentBtn;//差评
    private Button makeBlueprintBtn;//晒图
    private ListView listView;
    private List<GoodsDetailShowBean> list;
    private List<ImageShowBean> list1;
    private CommonAdapter commonAdapter;
    private String type = "all";
    private View header;
    private RefreshLayout swipeLayout;
    private View footView;
    public static int s = 0;//是否加载
    private String merid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_goods_comment, null);
        merid=getActivity().getIntent().getStringExtra("merid");
        allBtn = (Button) view.findViewById(R.id.item_goods_details_tm_all_btn);
        goodReputationBtn = (Button) view.findViewById(R.id.item_goods_details_tm_good_reputation_btn);
        middleEvaluationBtn = (Button) view.findViewById(R.id.item_goods_details_tm_middle_reputation_btn);
        negativeCommentBtn = (Button) view.findViewById(R.id.item_goods_details_tm_negative_comment_btn);
        makeBlueprintBtn = (Button) view.findViewById(R.id.item_goods_details_tm_blueprint_btn);
        footView = getActivity().getLayoutInflater().inflate(R.layout.no_more_date_footview, null);
        listView = (ListView) view.findViewById(R.id.item_goods_details_tm_common_listview);
        list = new ArrayList<>();
        list1 = new ArrayList<>();
        toGetGoodsDetailUp();//商品详情接口
        commonAdapter = new CommonAdapter(getActivity(), list);
        click();
        initView();//初始化布局
        setListener();//设置监听
        swipeLayout.post(new Thread(new Runnable() {
            @Override
            public void run() {
                swipeLayout.setRefreshing(true);
            }
        }));
        return view;
    }

    /**
     * 初始化布局
     */
    @SuppressLint({"InlinedApi", "InflateParams"})
    private void initView() {
        header = getActivity().getLayoutInflater().inflate(R.layout.loding_header, null);
        swipeLayout = (RefreshLayout) view.findViewById(R.id.swipe_container);
        listView.addHeaderView(header);
    }

    /**
     * 设置监听
     */
    private void setListener() {
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setOnLoadListener(this);
    }

    @Override
    public void onLoad() {
        swipeLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (s == 0 || s == 1) {
                    s = 2;
                    commonList(list.size() + "", "15", 1);
                }
            }
        }, 1000);
    }

    @Override
    public void onRefresh() {
        swipeLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                commonList("0", "15", 0);
            }
        }, 1000);
    }

    @Override
    public void onResume() {
        super.onResume();
        onRefresh();
    }

    /**
     * 点击事件
     */
    public void click() {
        allBtn.setOnClickListener(this);
        goodReputationBtn.setOnClickListener(this);
        middleEvaluationBtn.setOnClickListener(this);
        negativeCommentBtn.setOnClickListener(this);
        makeBlueprintBtn.setOnClickListener(this);
    }

    /**
     * 评论个数
     */
    public void toGetGoodsDetailUp() {//头部请求
        MerIdPostBean merIdPostBean = new MerIdPostBean();
        merIdPostBean.setMerid(merid);
        merIdPostBean.setMemStoreType("1");
        Http.toGetGoodsDetailUp(getActivity(), merIdPostBean, goodReputationBtn, allBtn, middleEvaluationBtn, negativeCommentBtn, makeBlueprintBtn);
    }

    /**
     * 评价接口
     */
    public void commonList(String offset, String limit, int typete) {//商品详情下部的接口请求
        CommonPostBean commonPostBean = new CommonPostBean();
        commonPostBean.setMerid(merid);
        commonPostBean.setEvatype(type);
        commonPostBean.setOffset(offset);
        commonPostBean.setLimit(limit);
        Http.getCommonList(getActivity(), commonPostBean, typete, list, list1, listView, commonAdapter, swipeLayout, footView,this);
    }

    @Override
    public void onStop() {
        super.onStop();
        MutualApplication.getRequestQueue().cancelAll("getCommonList96");
        MutualApplication.getRequestQueue().cancelAll("toGetGoodsDetailUp97");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_goods_details_tm_all_btn://全部
                type = "all";
                setColor(1);
                commonList("0", "15", 0);
                break;
            case R.id.item_goods_details_tm_good_reputation_btn://好评
                type = "score1";
                setColor(2);
                commonList("0", "15", 0);
                break;
            case R.id.item_goods_details_tm_middle_reputation_btn://中评
                type = "score2";
                setColor(3);
                commonList("0", "15", 0);
                break;
            case R.id.item_goods_details_tm_negative_comment_btn://差评
                type = "score3";
                setColor(4);
                commonList("0", "15", 0);
                break;
            case R.id.item_goods_details_tm_blueprint_btn://晒图
                type = "img";
                setColor(5);
                commonList("0", "15", 0);
                break;
        }
    }

    /**
     * 按钮背景和字体颜色赋值
     *
     * @param type
     */
    public void setColor(int type) {
        if (type == 1) {
            allBtn.setBackgroundResource(R.drawable.dialog_yellow);
            negativeCommentBtn.setBackgroundResource(R.drawable.dialog_gray_white);
            goodReputationBtn.setBackgroundResource(R.drawable.dialog_gray_white);
            middleEvaluationBtn.setBackgroundResource(R.drawable.dialog_gray_white);
            negativeCommentBtn.setBackgroundResource(R.drawable.dialog_gray_white);
            makeBlueprintBtn.setBackgroundResource(R.drawable.dialog_gray_white);
            allBtn.setTextColor(Color.parseColor("#FFFFFF"));
            negativeCommentBtn.setTextColor(Color.parseColor("#000000"));
            goodReputationBtn.setTextColor(Color.parseColor("#000000"));
            middleEvaluationBtn.setTextColor(Color.parseColor("#000000"));
            makeBlueprintBtn.setTextColor(Color.parseColor("#000000"));
        } else if (type == 2) {
            allBtn.setBackgroundResource(R.drawable.dialog_gray_white);
            negativeCommentBtn.setBackgroundResource(R.drawable.dialog_gray_white);
            goodReputationBtn.setBackgroundResource(R.drawable.dialog_yellow);
            middleEvaluationBtn.setBackgroundResource(R.drawable.dialog_gray_white);
            negativeCommentBtn.setBackgroundResource(R.drawable.dialog_gray_white);
            makeBlueprintBtn.setBackgroundResource(R.drawable.dialog_gray_white);
            goodReputationBtn.setTextColor(Color.parseColor("#FFFFFF"));
            negativeCommentBtn.setTextColor(Color.parseColor("#000000"));
            allBtn.setTextColor(Color.parseColor("#000000"));
            middleEvaluationBtn.setTextColor(Color.parseColor("#000000"));
            makeBlueprintBtn.setTextColor(Color.parseColor("#000000"));
        } else if (type == 3) {
            allBtn.setBackgroundResource(R.drawable.dialog_gray_white);
            negativeCommentBtn.setBackgroundResource(R.drawable.dialog_gray_white);
            goodReputationBtn.setBackgroundResource(R.drawable.dialog_gray_white);
            middleEvaluationBtn.setBackgroundResource(R.drawable.dialog_yellow);
            negativeCommentBtn.setBackgroundResource(R.drawable.dialog_gray_white);
            makeBlueprintBtn.setBackgroundResource(R.drawable.dialog_gray_white);
            middleEvaluationBtn.setTextColor(Color.parseColor("#FFFFFF"));
            negativeCommentBtn.setTextColor(Color.parseColor("#000000"));
            allBtn.setTextColor(Color.parseColor("#000000"));
            goodReputationBtn.setTextColor(Color.parseColor("#000000"));
            makeBlueprintBtn.setTextColor(Color.parseColor("#000000"));
        } else if (type == 4) {
            allBtn.setBackgroundResource(R.drawable.dialog_gray_white);
            negativeCommentBtn.setBackgroundResource(R.drawable.dialog_gray_white);
            goodReputationBtn.setBackgroundResource(R.drawable.dialog_gray_white);
            middleEvaluationBtn.setBackgroundResource(R.drawable.dialog_gray_white);
            negativeCommentBtn.setBackgroundResource(R.drawable.dialog_yellow);
            makeBlueprintBtn.setBackgroundResource(R.drawable.dialog_gray_white);
            negativeCommentBtn.setTextColor(Color.parseColor("#FFFFFF"));
            goodReputationBtn.setTextColor(Color.parseColor("#000000"));
            allBtn.setTextColor(Color.parseColor("#000000"));
            middleEvaluationBtn.setTextColor(Color.parseColor("#000000"));
            makeBlueprintBtn.setTextColor(Color.parseColor("#000000"));
        } else if (type == 5) {
            allBtn.setBackgroundResource(R.drawable.dialog_gray_white);
            negativeCommentBtn.setBackgroundResource(R.drawable.dialog_gray_white);
            goodReputationBtn.setBackgroundResource(R.drawable.dialog_gray_white);
            middleEvaluationBtn.setBackgroundResource(R.drawable.dialog_gray_white);
            negativeCommentBtn.setBackgroundResource(R.drawable.dialog_gray_white);
            makeBlueprintBtn.setBackgroundResource(R.drawable.dialog_yellow);
            makeBlueprintBtn.setTextColor(Color.parseColor("#FFFFFF"));
            negativeCommentBtn.setTextColor(Color.parseColor("#000000"));
            allBtn.setTextColor(Color.parseColor("#000000"));
            middleEvaluationBtn.setTextColor(Color.parseColor("#000000"));
            goodReputationBtn.setTextColor(Color.parseColor("#000000"));
        }
    }
}
