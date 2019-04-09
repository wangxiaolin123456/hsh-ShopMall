package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Response;
import com.example.administrator.merchants.activity.EstimatedEarningsDetailsActivity;
import com.example.administrator.merchants.adapter.EstimatedEarningsDetailsAdapter;
import com.example.administrator.merchants.http.show.EstimatedEarningsDetailsShowBean;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.common.views.RefreshLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/6/30 0030 13:09
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：预估详情
 */
public class EstimatedEarningsDetailsListener implements Response.Listener<JSONObject> {
    private Context context;
    private int type;
    private List<EstimatedEarningsDetailsShowBean> list;
    private ListView listView;
    private List<EstimatedEarningsDetailsShowBean> listHead;
    private EstimatedEarningsDetailsAdapter estimatedEarningsDetailsAdapter;
    private View footView;
    private RefreshLayout swipeLayout;

    public EstimatedEarningsDetailsListener(Context context, int type, List<EstimatedEarningsDetailsShowBean> list, List<EstimatedEarningsDetailsShowBean> listHead, ListView listView, EstimatedEarningsDetailsAdapter estimatedEarningsDetailsAdapter, View footView, RefreshLayout swipeLayout) {
        this.context = context;
        this.type = type;
        this.list = list;
        this.listHead = listHead;
        this.listView = listView;
        this.estimatedEarningsDetailsAdapter = estimatedEarningsDetailsAdapter;
        this.footView = footView;
        this.swipeLayout = swipeLayout;
    }

    @Override
    public void onResponse(JSONObject jsonObjects) {
        if (Status.status(jsonObjects)) {
            //返回成功
            if (type == 0) {
                list.clear();
                listHead.clear();
            }

            String storetype = null;
            try {
                storetype = jsonObjects.getString("levelno");
                JSONArray jsonArray1 = jsonObjects.getJSONArray("periodList");
                JSONArray jsonArray2 = jsonObjects.getJSONArray("ordList");
                List<JSONObject> objects1 = new ArrayList<>();
                List<JSONObject> objects2 = new ArrayList<>();
                for (int i = 0; i < jsonArray1.length(); i++) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject = (JSONObject) jsonArray1.get(i);
                    objects1.add(jsonObject);
                }
                for (int i = 0; i < objects1.size(); i++) {
                    EstimatedEarningsDetailsShowBean beanHead = new EstimatedEarningsDetailsShowBean();
                    beanHead.setType(0);
                    beanHead.setTextEightCircleOne(objects1.get(i).getString("begindate").substring(0, 7));
                    beanHead.setTextEightCircleTwo(objects1.get(i).getString("recretordamt"));
                    beanHead.setTextEightCircleThree(objects1.get(i).getString("recretbeibiamt"));
                    beanHead.setTextEightCircleFour(objects1.get(i).getString("enddate"));
                    beanHead.setPriodid(objects1.get(i).getString("periodid"));
                    listHead.add(beanHead);
                }
                for (int j = 0; j < jsonArray2.length(); j++) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject = (JSONObject) jsonArray2.get(j);
                    objects2.add(jsonObject);
                }
                for (int k = 0; k < objects2.size(); k++) {
                    EstimatedEarningsDetailsShowBean bean = new EstimatedEarningsDetailsShowBean();
                    if (objects2.get(k).getString("inouttype").equals("1")) {
                        bean.setType(1);
                    } else {
                        bean.setType(2);
                    }
                    bean.setTextEightCircleOne(objects2.get(k).getString("ordstorename"));
                    bean.setTextEightCircleTwo(objects2.get(k).getString("ordstorephone"));
                    bean.setTextEightCircleThree(objects2.get(k).getString("paybeibiamt"));
                    bean.setTextEightCircleFour(objects2.get(k).getString("payamt"));
                    bean.setTextEightCircleFive(objects2.get(k).getString("createtimestr"));//createtime
                    bean.setTextEightCircleSix(objects2.get(k).getString("refamt"));//下面是退款
                    bean.setTextEightCircleSeven(objects2.get(k).getString("refbeibiamt"));//refbeibiamt
                    bean.setPriodid(objects2.get(k).getString("periodid"));
                    if (type == 0) {
                        if (k == 0) {
                            list.add(listHead.get(0));
                        } else if (!objects2.get(k).getString("periodid").equals(objects2.get(k - 1).getString("periodid"))) {
                            list.add(listHead.get(1));
                        }
                    } else {
                        if (k == 0) {
                            if (!objects2.get(k).getString("periodid").equals(list.get(list.size() - 1).getPriodid())) {
                                list.add(listHead.get(1));
                            }
                        } else if (!objects2.get(k).getString("periodid").equals(objects2.get(k - 1).getString("periodid"))) {
                            list.add(listHead.get(1));
                        }
                    }

                    list.add(bean);
                }
                if (type == 0) {
                    swipeLayout.setRefreshing(false);
                    listView.setAdapter(estimatedEarningsDetailsAdapter);
                } else {
                    swipeLayout.setLoading(false);
                    EstimatedEarningsDetailsActivity.s = 1;
                    estimatedEarningsDetailsAdapter.notifyDataSetChanged();
                }

                if (jsonArray2.length() < 15) {
                    swipeLayout.setOnLoadListener(null);
                    listView.removeFooterView(footView);
                    listView.addFooterView(footView);

                } else {
                    swipeLayout.setOnLoadListener((RefreshLayout.OnLoadListener) context);
                    listView.removeFooterView(footView);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


        } else {
            Status.fail(context, jsonObjects);
        }

    }
}
