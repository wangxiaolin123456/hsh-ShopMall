package com.example.administrator.merchants.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.adapter.CommonAdapter;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseActivity;
import com.example.administrator.merchants.common.views.RefreshLayout;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.post.CommonPostBean;
import com.example.administrator.merchants.http.post.MerIdPostBean;
import com.example.administrator.merchants.http.show.GoodsDetailShowBean;
import com.example.administrator.merchants.http.show.ImageShowBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品评价
 * Created by User on 2019/2/21.
 */

public class GoodsEvaluateListActivity extends BaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener,
        RefreshLayout.OnLoadListener {
    private Button goodReputationBtn;//好评
    private Button allBtn;//全部
    private Button middleEvaluationBtn;//中评
    private Button negativeCommentBtn;//差评
    private Button makeBlueprintBtn;//晒图
    private ListView listView;
    private List<GoodsDetailShowBean> list = new ArrayList<>();
    private List<ImageShowBean> list1 = new ArrayList<>();
    private CommonAdapter commonAdapter;
    private String type = "all";
    private View header;
    private RefreshLayout swipeLayout;
    private View footView;
    public static int s = 0;//是否加载
    private String merid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_evaluate);
        setTitles("全部评价");
        merid = getIntent().getStringExtra("merid");
        initView();
    }

    private void initView() {
        allBtn = findViewById(R.id.item_goods_details_tm_all_btn);
        goodReputationBtn = findViewById(R.id.item_goods_details_tm_good_reputation_btn);
        middleEvaluationBtn = findViewById(R.id.item_goods_details_tm_middle_reputation_btn);
        negativeCommentBtn = findViewById(R.id.item_goods_details_tm_negative_comment_btn);
        makeBlueprintBtn = findViewById(R.id.item_goods_details_tm_blueprint_btn);
        footView = getLayoutInflater().inflate(R.layout.no_more_date_footview, null);
        listView = findViewById(R.id.item_goods_details_tm_common_listview);
        header = getLayoutInflater().inflate(R.layout.loding_header, null);
        swipeLayout = findViewById(R.id.swipe_container);
        toGetGoodsDetailUp();//商品详情接口
        commonAdapter = new CommonAdapter(mContext, list);
        click();
        listView.addHeaderView(header);
        setListener();//设置监听
        swipeLayout.post(new Thread(new Runnable() {
            @Override
            public void run() {
                swipeLayout.setRefreshing(true);
            }
        }));
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
        Http.toGetGoodsDetailUp(mContext, merIdPostBean, goodReputationBtn, allBtn, middleEvaluationBtn, negativeCommentBtn, makeBlueprintBtn);
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
        Http.getCommonList(mContext, commonPostBean, typete, list, list1, listView, commonAdapter, swipeLayout, footView, this);
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
            allBtn.setBackgroundResource(R.drawable.pj_check_bg);
            negativeCommentBtn.setBackgroundResource(R.drawable.pj_no_check_bg);
            goodReputationBtn.setBackgroundResource(R.drawable.pj_no_check_bg);
            middleEvaluationBtn.setBackgroundResource(R.drawable.pj_no_check_bg);
            negativeCommentBtn.setBackgroundResource(R.drawable.pj_no_check_bg);
            makeBlueprintBtn.setBackgroundResource(R.drawable.pj_no_check_bg);
            allBtn.setTextColor(getResources().getColor(R.color.themeColor));
            negativeCommentBtn.setTextColor(getResources().getColor(R.color.text_gray_color));
            goodReputationBtn.setTextColor(getResources().getColor(R.color.text_gray_color));
            middleEvaluationBtn.setTextColor(getResources().getColor(R.color.text_gray_color));
            makeBlueprintBtn.setTextColor(getResources().getColor(R.color.text_gray_color));
        } else if (type == 2) {
            allBtn.setBackgroundResource(R.drawable.pj_no_check_bg);
            negativeCommentBtn.setBackgroundResource(R.drawable.pj_no_check_bg);
            goodReputationBtn.setBackgroundResource(R.drawable.pj_check_bg);
            middleEvaluationBtn.setBackgroundResource(R.drawable.pj_no_check_bg);
            negativeCommentBtn.setBackgroundResource(R.drawable.pj_no_check_bg);
            makeBlueprintBtn.setBackgroundResource(R.drawable.pj_no_check_bg);
            goodReputationBtn.setTextColor(getResources().getColor(R.color.themeColor));
            negativeCommentBtn.setTextColor(getResources().getColor(R.color.text_gray_color));
            allBtn.setTextColor(getResources().getColor(R.color.text_gray_color));
            middleEvaluationBtn.setTextColor(getResources().getColor(R.color.text_gray_color));
            makeBlueprintBtn.setTextColor(getResources().getColor(R.color.text_gray_color));
        } else if (type == 3) {
            allBtn.setBackgroundResource(R.drawable.pj_no_check_bg);
            negativeCommentBtn.setBackgroundResource(R.drawable.pj_no_check_bg);
            goodReputationBtn.setBackgroundResource(R.drawable.pj_no_check_bg);
            middleEvaluationBtn.setBackgroundResource(R.drawable.pj_check_bg);
            negativeCommentBtn.setBackgroundResource(R.drawable.pj_no_check_bg);
            makeBlueprintBtn.setBackgroundResource(R.drawable.pj_no_check_bg);
            middleEvaluationBtn.setTextColor(getResources().getColor(R.color.themeColor));
            negativeCommentBtn.setTextColor(getResources().getColor(R.color.text_gray_color));
            allBtn.setTextColor(getResources().getColor(R.color.text_gray_color));
            goodReputationBtn.setTextColor(getResources().getColor(R.color.text_gray_color));
            makeBlueprintBtn.setTextColor(getResources().getColor(R.color.text_gray_color));
        } else if (type == 4) {
            allBtn.setBackgroundResource(R.drawable.pj_no_check_bg);
            negativeCommentBtn.setBackgroundResource(R.drawable.pj_no_check_bg);
            goodReputationBtn.setBackgroundResource(R.drawable.pj_no_check_bg);
            middleEvaluationBtn.setBackgroundResource(R.drawable.pj_no_check_bg);
            negativeCommentBtn.setBackgroundResource(R.drawable.pj_check_bg);
            makeBlueprintBtn.setBackgroundResource(R.drawable.pj_no_check_bg);
            negativeCommentBtn.setTextColor(getResources().getColor(R.color.themeColor));
            goodReputationBtn.setTextColor(getResources().getColor(R.color.text_gray_color));
            allBtn.setTextColor(getResources().getColor(R.color.text_gray_color));
            middleEvaluationBtn.setTextColor(getResources().getColor(R.color.text_gray_color));
            makeBlueprintBtn.setTextColor(getResources().getColor(R.color.text_gray_color));
        } else if (type == 5) {
            allBtn.setBackgroundResource(R.drawable.pj_no_check_bg);
            negativeCommentBtn.setBackgroundResource(R.drawable.pj_no_check_bg);
            goodReputationBtn.setBackgroundResource(R.drawable.pj_no_check_bg);
            middleEvaluationBtn.setBackgroundResource(R.drawable.pj_no_check_bg);
            negativeCommentBtn.setBackgroundResource(R.drawable.pj_no_check_bg);
            makeBlueprintBtn.setBackgroundResource(R.drawable.pj_check_bg);
            makeBlueprintBtn.setTextColor(getResources().getColor(R.color.themeColor));
            negativeCommentBtn.setTextColor(getResources().getColor(R.color.text_gray_color));
            allBtn.setTextColor(getResources().getColor(R.color.text_gray_color));
            middleEvaluationBtn.setTextColor(getResources().getColor(R.color.text_gray_color));
            goodReputationBtn.setTextColor(getResources().getColor(R.color.text_gray_color));
        }
    }
}
