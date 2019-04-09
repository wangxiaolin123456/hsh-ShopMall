package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.example.administrator.merchants.activity.GoodsDetailsActivity;
import com.example.administrator.merchants.common.views.GroupSix;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.http.show.ModeListShowBean;
import com.example.administrator.merchants.http.show.ModeShowBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/8/17 0017 14:34
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：原产地商品规格
 */
public class ShopModeListener implements Response.Listener<JSONObject>, GroupSix.OnGroupItemClickListenerOne, GroupSix.OnGroupItemClickListenerTwo, GroupSix.OnGroupItemClickListenerThree, GroupSix.OnGroupItemClickListenerFour, GroupSix.OnGroupItemClickListenerFive, GroupSix.OnGroupItemClickListenerSix {
    private Context context;
    private LinearLayout modeLineOne;
    private LinearLayout modeLineTwo;
    private LinearLayout modeLineThree;
    private LinearLayout modeLineFour;
    private LinearLayout modeLineFive;
    private LinearLayout modeLineSix;
    private List<ModeShowBean> modeList;
    private TextView standardOne;//规格A
    private TextView standardTwo;//规格B
    private TextView standardThree;//规格C
    private TextView standardFour;//规格D
    private TextView standardFive;//规格E
    private TextView standardSix;//规格F
    private List<ModeListShowBean> modeListShowBeanList;
    private ArrayList<String> viewTexts;
    private ArrayList<String> viewTextTwo;
    private ArrayList<String> viewTextThree;
    private ArrayList<String> viewTextFour;
    private ArrayList<String> viewTextFive;
    private ArrayList<String> viewTextSix;
    private GroupSix goodsViewGroupOne;
    private GroupSix goodsViewGroupTwo;
    private GroupSix goodsViewGroupThree;
    private GroupSix goodsViewGroupFour;
    private GroupSix goodsViewGroupFive;
    private GroupSix goodsViewGroupSix;

    public ShopModeListener(Context context, LinearLayout modeLineOne, LinearLayout modeLineTwo, LinearLayout modeLineThree, LinearLayout modeLineFour, LinearLayout modeLineFive
            , LinearLayout modeLineSix, List<ModeShowBean> modeList, TextView standardOne
            , TextView standardTwo, TextView standardThree, TextView standardFour, TextView standardFive, TextView standardSix
            , List<ModeListShowBean> modeListShowBeanList, ArrayList<String> viewTexts, ArrayList<String> viewTextTwo, ArrayList<String> viewTextThree
            , ArrayList<String> viewTextFour, ArrayList<String> viewTextFive, ArrayList<String> viewTextSix, GroupSix goodsViewGroupOne
            , GroupSix goodsViewGroupTwo, GroupSix goodsViewGroupThree, GroupSix goodsViewGroupFour, GroupSix goodsViewGroupFive, GroupSix goodsViewGroupSix) {
        this.goodsViewGroupOne = goodsViewGroupOne;
        this.goodsViewGroupTwo = goodsViewGroupTwo;
        this.goodsViewGroupThree = goodsViewGroupThree;
        this.goodsViewGroupFour = goodsViewGroupFour;
        this.goodsViewGroupFive = goodsViewGroupFive;
        this.goodsViewGroupSix = goodsViewGroupSix;
        this.viewTexts = viewTexts;
        this.viewTextTwo = viewTextTwo;
        this.viewTextThree = viewTextThree;
        this.viewTextFour = viewTextFour;
        this.viewTextFive = viewTextFive;
        this.viewTextSix = viewTextSix;
        this.context = context;
        this.modeLineOne = modeLineOne;
        this.modeLineTwo = modeLineTwo;
        this.modeLineThree = modeLineThree;
        this.modeLineFour = modeLineFour;
        this.modeLineFive = modeLineFive;
        this.modeLineSix = modeLineSix;
        this.modeList = modeList;
        this.standardOne = standardOne;
        this.standardTwo = standardTwo;
        this.standardThree = standardThree;
        this.standardFour = standardFour;
        this.standardFive = standardFive;
        this.standardSix = standardSix;
        this.modeListShowBeanList = modeListShowBeanList;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //返回成功
            try {
                //型号列表
                JSONArray jsonArray1 = jsonObject.getJSONArray("modellist");
                List<JSONObject> objectArrayList = new ArrayList<>();
                for (int i = 0; i < jsonArray1.length(); i++) {
                    objectArrayList.add((JSONObject) jsonArray1.get(i));
                }
                int a = -1;
                if (objectArrayList.size() == 0) {
                    modeLineTwo.setVisibility(View.GONE);
                    modeLineOne.setVisibility(View.GONE);
                    modeLineThree.setVisibility(View.GONE);
                    modeLineFour.setVisibility(View.GONE);
                    modeLineFive.setVisibility(View.GONE);
                    modeLineSix.setVisibility(View.GONE);
                    a = 0;
                }
                modeList.clear();
                ModeShowBean modeShowBean = null;
                for (int j = 0; j < objectArrayList.size(); j++) {
                    modeShowBean = new ModeShowBean();
                    modeShowBean.setModelid(objectArrayList.get(j).getString("modelid"));
                    modeShowBean.setModelname(objectArrayList.get(j).getString("modelname"));
                    modeList.add(modeShowBean);
                }
                if (modeList.size() == 1) {
                    a = 1;
                    modeShowBean.setType(1);
                    modeLineTwo.setVisibility(View.GONE);
                    modeLineOne.setVisibility(View.VISIBLE);
                    modeLineThree.setVisibility(View.GONE);
                    modeLineFour.setVisibility(View.GONE);
                    modeLineFive.setVisibility(View.GONE);
                    modeLineSix.setVisibility(View.GONE);
                    standardOne.setText(objectArrayList.get(0).getString("modelname"));
                } else if (modeList.size() == 2) {
                    a = 2;
                    modeShowBean.setType(2);
                    modeLineOne.setVisibility(View.VISIBLE);
                    modeLineTwo.setVisibility(View.VISIBLE);
                    modeLineThree.setVisibility(View.GONE);
                    modeLineFour.setVisibility(View.GONE);
                    modeLineFive.setVisibility(View.GONE);
                    modeLineSix.setVisibility(View.GONE);
                    standardOne.setText(objectArrayList.get(0).getString("modelname"));
                    standardTwo.setText(objectArrayList.get(1).getString("modelname"));
                } else if (modeList.size() == 3) {
                    a = 3;
                    modeShowBean.setType(3);
                    modeLineOne.setVisibility(View.VISIBLE);
                    modeLineTwo.setVisibility(View.VISIBLE);
                    modeLineThree.setVisibility(View.VISIBLE);
                    modeLineFour.setVisibility(View.GONE);
                    modeLineFive.setVisibility(View.GONE);
                    modeLineSix.setVisibility(View.GONE);
                    standardOne.setText(objectArrayList.get(0).getString("modelname"));
                    standardTwo.setText(objectArrayList.get(1).getString("modelname"));
                    standardThree.setText(objectArrayList.get(2).getString("modelname"));
                } else if (modeList.size() == 4) {
                    a = 4;
                    modeShowBean.setType(4);
                    modeLineOne.setVisibility(View.VISIBLE);
                    modeLineTwo.setVisibility(View.VISIBLE);
                    modeLineThree.setVisibility(View.VISIBLE);
                    modeLineFour.setVisibility(View.VISIBLE);
                    modeLineFive.setVisibility(View.GONE);
                    modeLineSix.setVisibility(View.GONE);
                    standardOne.setText(objectArrayList.get(0).getString("modelname"));
                    standardTwo.setText(objectArrayList.get(1).getString("modelname"));
                    standardThree.setText(objectArrayList.get(2).getString("modelname"));
                    standardFour.setText(objectArrayList.get(3).getString("modelname"));
                } else if (modeList.size() == 5) {
                    a = 5;
                    modeShowBean.setType(5);
                    modeLineOne.setVisibility(View.VISIBLE);
                    modeLineTwo.setVisibility(View.VISIBLE);
                    modeLineThree.setVisibility(View.VISIBLE);
                    modeLineFour.setVisibility(View.VISIBLE);
                    modeLineFive.setVisibility(View.VISIBLE);
                    modeLineSix.setVisibility(View.GONE);
                    standardOne.setText(objectArrayList.get(0).getString("modelname"));
                    standardTwo.setText(objectArrayList.get(1).getString("modelname"));
                    standardThree.setText(objectArrayList.get(2).getString("modelname"));
                    standardFour.setText(objectArrayList.get(3).getString("modelname"));
                    standardFive.setText(objectArrayList.get(4).getString("modelname"));
                } else if (modeList.size() == 6) {
                    a = 6;
                    modeShowBean.setType(6);
                    modeLineOne.setVisibility(View.VISIBLE);
                    modeLineTwo.setVisibility(View.VISIBLE);
                    modeLineThree.setVisibility(View.VISIBLE);
                    modeLineFour.setVisibility(View.VISIBLE);
                    modeLineFive.setVisibility(View.VISIBLE);
                    modeLineSix.setVisibility(View.VISIBLE);
                    standardOne.setText(objectArrayList.get(0).getString("modelname"));
                    standardTwo.setText(objectArrayList.get(1).getString("modelname"));
                    standardThree.setText(objectArrayList.get(2).getString("modelname"));
                    standardFour.setText(objectArrayList.get(3).getString("modelname"));
                    standardFive.setText(objectArrayList.get(4).getString("modelname"));
                    standardSix.setText(objectArrayList.get(5).getString("modelname"));
                }
                JSONArray jsonArray = jsonObject.getJSONArray("modeloptionlist");
                List<JSONObject> imgObjectList = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    imgObjectList.add((JSONObject) jsonArray.get(i));
                }
                modeListShowBeanList.clear();
                for (int j = 0; j < imgObjectList.size(); j++) {
                    ModeListShowBean modeListShowBean = new ModeListShowBean();
                    modeListShowBean.setModelid(imgObjectList.get(j).getString("modelid"));
                    modeListShowBean.setOptionid(imgObjectList.get(j).getString("optionid"));
                    modeListShowBean.setOptionname(imgObjectList.get(j).getString("optionname"));
                    modeListShowBeanList.add(modeListShowBean);
                }
                if (a == 0) {
                } else if (a == 1) {
                    for (int j = 0; j < modeListShowBeanList.size(); j++) {
                        if (modeListShowBeanList.get(j).getModelid().equals(modeList.get(0).getModelid())) {
                            viewTexts.add(modeListShowBeanList.get(j).getOptionname());
                        }
                    }
                } else if (a == 2) {
                    for (int j = 0; j < modeListShowBeanList.size(); j++) {
                        if (modeListShowBeanList.get(j).getModelid().equals(modeList.get(0).getModelid())) {
                            viewTexts.add(modeListShowBeanList.get(j).getOptionname());
                        }
                    }
                    for (int j = 0; j < modeListShowBeanList.size(); j++) {
                        if (modeListShowBeanList.get(j).getModelid().equals(modeList.get(1).getModelid())) {
                            viewTextTwo.add(modeListShowBeanList.get(j).getOptionname());
                        }
                    }
                } else if (a == 3) {
                    for (int j = 0; j < modeListShowBeanList.size(); j++) {
                        if (modeListShowBeanList.get(j).getModelid().equals(modeList.get(0).getModelid())) {
                            viewTexts.add(modeListShowBeanList.get(j).getOptionname());
                        }
                    }
                    for (int j = 0; j < modeListShowBeanList.size(); j++) {
                        if (modeListShowBeanList.get(j).getModelid().equals(modeList.get(1).getModelid())) {
                            viewTextTwo.add(modeListShowBeanList.get(j).getOptionname());
                        }
                    }
                    for (int j = 0; j < modeListShowBeanList.size(); j++) {
                        if (modeListShowBeanList.get(j).getModelid().equals(modeList.get(2).getModelid())) {
                            viewTextThree.add(modeListShowBeanList.get(j).getOptionname());
                        }
                    }
                } else if (a == 4) {
                    for (int j = 0; j < modeListShowBeanList.size(); j++) {
                        if (modeListShowBeanList.get(j).getModelid().equals(modeList.get(0).getModelid())) {
                            viewTexts.add(modeListShowBeanList.get(j).getOptionname());
                        }
                    }
                    for (int j = 0; j < modeListShowBeanList.size(); j++) {
                        if (modeListShowBeanList.get(j).getModelid().equals(modeList.get(1).getModelid())) {
                            viewTextTwo.add(modeListShowBeanList.get(j).getOptionname());
                        }
                    }
                    for (int j = 0; j < modeListShowBeanList.size(); j++) {
                        if (modeListShowBeanList.get(j).getModelid().equals(modeList.get(2).getModelid())) {
                            viewTextThree.add(modeListShowBeanList.get(j).getOptionname());
                        }
                    }
                    for (int j = 0; j < modeListShowBeanList.size(); j++) {
                        if (modeListShowBeanList.get(j).getModelid().equals(modeList.get(3).getModelid())) {
                            viewTextFour.add(modeListShowBeanList.get(j).getOptionname());
                        }
                    }
                } else if (a == 5) {
                    for (int j = 0; j < modeListShowBeanList.size(); j++) {
                        if (modeListShowBeanList.get(j).getModelid().equals(modeList.get(0).getModelid())) {
                            viewTexts.add(modeListShowBeanList.get(j).getOptionname());
                        }
                    }
                    for (int j = 0; j < modeListShowBeanList.size(); j++) {
                        if (modeListShowBeanList.get(j).getModelid().equals(modeList.get(1).getModelid())) {
                            viewTextTwo.add(modeListShowBeanList.get(j).getOptionname());
                        }
                    }
                    for (int j = 0; j < modeListShowBeanList.size(); j++) {
                        if (modeListShowBeanList.get(j).getModelid().equals(modeList.get(2).getModelid())) {
                            viewTextThree.add(modeListShowBeanList.get(j).getOptionname());
                        }
                    }
                    for (int j = 0; j < modeListShowBeanList.size(); j++) {
                        if (modeListShowBeanList.get(j).getModelid().equals(modeList.get(3).getModelid())) {
                            viewTextFour.add(modeListShowBeanList.get(j).getOptionname());
                        }
                    }
                    for (int j = 0; j < modeListShowBeanList.size(); j++) {
                        if (modeListShowBeanList.get(j).getModelid().equals(modeList.get(4).getModelid())) {
                            viewTextFive.add(modeListShowBeanList.get(j).getOptionname());
                        }
                    }
                } else if (a == 6) {
                    for (int j = 0; j < modeListShowBeanList.size(); j++) {
                        if (modeListShowBeanList.get(j).getModelid().equals(modeList.get(0).getModelid())) {
                            viewTexts.add(modeListShowBeanList.get(j).getOptionname());
                        }
                    }
                    for (int j = 0; j < modeListShowBeanList.size(); j++) {
                        if (modeListShowBeanList.get(j).getModelid().equals(modeList.get(1).getModelid())) {
                            viewTextTwo.add(modeListShowBeanList.get(j).getOptionname());
                        }
                    }
                    for (int j = 0; j < modeListShowBeanList.size(); j++) {
                        if (modeListShowBeanList.get(j).getModelid().equals(modeList.get(2).getModelid())) {
                            viewTextThree.add(modeListShowBeanList.get(j).getOptionname());
                        }
                    }
                    for (int j = 0; j < modeListShowBeanList.size(); j++) {
                        if (modeListShowBeanList.get(j).getModelid().equals(modeList.get(3).getModelid())) {
                            viewTextFour.add(modeListShowBeanList.get(j).getOptionname());
                        }
                    }
                    for (int j = 0; j < modeListShowBeanList.size(); j++) {
                        if (modeListShowBeanList.get(j).getModelid().equals(modeList.get(4).getModelid())) {
                            viewTextFive.add(modeListShowBeanList.get(j).getOptionname());
                        }
                    }
                    for (int j = 0; j < modeListShowBeanList.size(); j++) {
                        if (modeListShowBeanList.get(j).getModelid().equals(modeList.get(5).getModelid())) {
                            viewTextSix.add(modeListShowBeanList.get(j).getOptionname());
                        }
                    }
                }
                goodsViewGroupOne.addItemViews(viewTexts, GroupSix.TEV_MODE);
                goodsViewGroupTwo.addItemViews(viewTextTwo, GroupSix.TEV_MODE);
                goodsViewGroupThree.addItemViews(viewTextThree, GroupSix.TEV_MODE);
                goodsViewGroupFour.addItemViews(viewTextFour, GroupSix.TEV_MODE);
                goodsViewGroupFive.addItemViews(viewTextFive, GroupSix.TEV_MODE);
                goodsViewGroupSix.addItemViews(viewTextSix, GroupSix.TEV_MODE);
                goodsViewGroupOne.setGroupClickListenerOne(this);
                goodsViewGroupTwo.setGroupClickListenerTwo(this);
                goodsViewGroupThree.setGroupClickListenerThree(this);
                goodsViewGroupFour.setGroupClickListenerFour(this);
                goodsViewGroupFive.setGroupClickListenerFive(this);
                goodsViewGroupSix.setOnGroupItemClickListenerSix(this);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            //返回失败
            Status.fail(context, jsonObject);
        }
    }

    @Override
    public void onGroupItemClickOne(int item) {
        GoodsDetailsActivity.chooseID = item;//GoodsDetailsFragment
        GoodsDetailsActivity.chooseText = goodsViewGroupOne.getChooseText(item);
    }

    @Override
    public void onGroupItemClickTwo(int item) {
        GoodsDetailsActivity.chooseIDs = item;
        GoodsDetailsActivity.chooseTextTwo = goodsViewGroupTwo.getChooseText(item);
    }

    @Override
    public void onGroupItemClickThree(int item) {
        GoodsDetailsActivity.chooseIDss = item;
        GoodsDetailsActivity.chooseTextThree = goodsViewGroupThree.getChooseText(item);
    }

    @Override
    public void onGroupItemClickFour(int item) {
        GoodsDetailsActivity.chooseIDFour = item;
        GoodsDetailsActivity.chooseTextFour = goodsViewGroupFour.getChooseText(item);
    }

    @Override
    public void onGroupItemClickFive(int item) {
        GoodsDetailsActivity.chooseIDFive = item;
        GoodsDetailsActivity.chooseTextFive = goodsViewGroupFive.getChooseText(item);
    }

    @Override
    public void onGroupItemClickSix(int item) {
        GoodsDetailsActivity.chooseIDSix = item;
        GoodsDetailsActivity.chooseTextSix = goodsViewGroupSix.getChooseText(item);
    }
}
