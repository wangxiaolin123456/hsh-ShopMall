package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Response;
import com.example.administrator.merchants.http.show.BeiRecordShowBean;
import com.example.administrator.merchants.activity.BeiBiRecordActivity;
import com.example.administrator.merchants.adapter.BeiRecordAdapter;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.common.DateUtils;
import com.example.administrator.merchants.common.views.RefreshLayout;
import com.example.administrator.merchants.utils.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/6/23 0023 10:23
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：贝币记录
 */
public class BeiBiRecordListener implements Response.Listener<JSONObject> {
    private Context context;
    private int type;
    private List<BeiRecordShowBean>list;
    private ListView listView;
    private RefreshLayout swipeLayout;
    private BeiRecordAdapter beiRecordAdapter;
    private View footView;
    public BeiBiRecordListener(Context context,int type,List<BeiRecordShowBean>list,ListView listView,RefreshLayout swipeLayout,BeiRecordAdapter beiRecordAdapter,View footView){
        this.context=context;
        this.type=type;
        this.list=list;
        this.listView=listView;
        this.swipeLayout=swipeLayout;
        this.beiRecordAdapter=beiRecordAdapter;
        this.footView=footView;
    }
    @Override
    public void onResponse(JSONObject jsonObject) {
        LogUtil.i("贝币使用记录"+jsonObject.toString());
        if (Status.status(jsonObject)){
            //返回成功
            JSONArray jsonArray = null;
            try {
                jsonArray = jsonObject.getJSONArray("outList");
                List<JSONObject> jsonObjectList = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObjectList.add((JSONObject) jsonArray.get(i));
                }
                if (type==0){
                    //刷新
                    list.clear();
                }
                for (int j = 0; j < jsonObjectList.size(); j++) {
                    BeiRecordShowBean beiRecordShowBean = new BeiRecordShowBean();
                    beiRecordShowBean.setInouttype(jsonObjectList.get(j).getString("inouttype"));//类型
                    if ("in_recharge".equals(jsonObjectList.get(j).getString("inouttype"))) {
                        beiRecordShowBean.setType(BeiRecordAdapter.VALUE_TWO);
                        beiRecordShowBean.setAdd(jsonObjectList.get(j).getString("beibiin"));
                    } else if ("out_cash".equals(jsonObjectList.get(j).getString("inouttype"))) {
                        beiRecordShowBean.setType(BeiRecordAdapter.VALUE_THREE);
                        beiRecordShowBean.setJian(jsonObjectList.get(j).getString("beibiout"));
                    } else if ("in_gift".equals(jsonObjectList.get(j).getString("inouttype"))) {
                        beiRecordShowBean.setType(BeiRecordAdapter.VALUE_TWO);
                        beiRecordShowBean.setAdd(jsonObjectList.get(j).getString("beibiin"));
                    } else if ("out_gift".equals(jsonObjectList.get(j).getString("inouttype"))) {//贝币赠与
                        beiRecordShowBean.setType(BeiRecordAdapter.VALUE_THREE);
                        beiRecordShowBean.setJian(jsonObjectList.get(j).getString("beibiout"));
                    } else if ("in_order".equals(jsonObjectList.get(j).getString("inouttype"))) { //贝币赠与
                        beiRecordShowBean.setType(BeiRecordAdapter.VALUE_TWO);
                        beiRecordShowBean.setAdd(jsonObjectList.get(j).getString("beibiin"));
                    } else if ("out_order".equals(jsonObjectList.get(j).getString("inouttype"))) {//贝币赠与
                        beiRecordShowBean.setType(BeiRecordAdapter.VALUE_THREE);
                        beiRecordShowBean.setJian(jsonObjectList.get(j).getString("beibiout"));
                    }else if ("in_ret".equals(jsonObjectList.get(j).getString("inouttype"))) {
                        beiRecordShowBean.setType(BeiRecordAdapter.VALUE_TWO);
                        beiRecordShowBean.setAdd(jsonObjectList.get(j).getString("beibiin"));
                    } else if ("out_ret".equals(jsonObjectList.get(j).getString("inouttype"))) {
                        beiRecordShowBean.setType(BeiRecordAdapter.VALUE_THREE);
                        beiRecordShowBean.setJian(jsonObjectList.get(j).getString("beibiout"));
                    }
                    beiRecordShowBean.setInoutobjname(jsonObjectList.get(j).getString("storename"));//inoutobjname
                    beiRecordShowBean.setInoutobjdescr(jsonObjectList.get(j).getString("storeid"));//inoutobjdescr
                    beiRecordShowBean.setMiaoshu(jsonObjectList.get(j).getString("inoutdescr"));
                    beiRecordShowBean.setOrdno(jsonObjectList.get(j).getString("ordno"));
                    beiRecordShowBean.setDate(DateUtils.getBeiRecord(jsonObjectList.get(j).getString("createtimestr")));
                    beiRecordShowBean.setDate2(DateUtils.monthIncomeDate(jsonObjectList.get(j).getString("createtimestr")));
//                    if(jsonObjectList.get(j).getString("inoutobjimgsfile").equals("")){
//                        beiRecordShowBean.setInoutobjimgsfile(jsonObjectList.get(j).getString("inoutobjimgsfile"));
//                    }else {
//                        beiRecordShowBean.setInoutobjimgsfile(HttpUrl.BaseImageUrl + jsonObjectList.get(j).getString("inoutobjimgsfile"));
//                    }
                    list.add(beiRecordShowBean);
                }
                if (type==0){
                    //刷新
                    swipeLayout.setRefreshing(false);
                    listView.setAdapter(beiRecordAdapter);
                }else if (type==1){
                    //加载
                    BeiBiRecordActivity.s=1;
                    swipeLayout.setLoading(false);
                    beiRecordAdapter.notifyDataSetChanged();
                }
                if (jsonObjectList.size() < 15) {
                    //没有更多数据
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
        }else {
            //返回失败
            Status.fail(context,jsonObject);
        }
    }
}
