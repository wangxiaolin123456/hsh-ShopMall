package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Response;
import com.example.administrator.merchants.http.show.MessageShowBean;
import com.example.administrator.merchants.R;
import com.example.administrator.merchants.activity.MyMessageListActivity;
import com.example.administrator.merchants.adapter.MyMessageAdapter;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.common.views.RefreshLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/7/16 0016 13:59
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：消息列表
 */
public class MyMessageListListener implements Response.Listener<JSONObject> {
    private Context context;
    private int type;
    private List<MessageShowBean> list;
    private ListView listView;
    private RefreshLayout swipeLayout;
    private MyMessageAdapter myMessageAdapter;
    private View footView;
    public MyMessageListListener(Context context,int type,List<MessageShowBean> list,ListView listView,RefreshLayout swipeLayout,MyMessageAdapter myMessageAdapter,View footView){
        this.context=context;
        this.type=type;
        this.list=list;
        this.listView=listView;
        this.swipeLayout=swipeLayout;
        this.myMessageAdapter=myMessageAdapter;
        this.footView=footView;
    }
    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)){
            //返回成功
            try {
                if (type==0||type==2){
                    list.clear();
                }
                JSONArray jsonArray = jsonObject.getJSONArray("list");
                List<JSONObject> jsonObjectList = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObjectList.add((JSONObject) jsonArray.get(i));
                }
                for (int j = 0; j < jsonObjectList.size(); j++) {
                    MessageShowBean messageShowBean = new MessageShowBean();
                    messageShowBean.setImage(R.drawable.message_order);
                    messageShowBean.setTitle(jsonObjectList.get(j).getString("newstitle"));
                    messageShowBean.setMessage(jsonObjectList.get(j).getString("newscontent"));
                    messageShowBean.setMessageId(jsonObjectList.get(j).getString("newsid"));
                    messageShowBean.setType(jsonObjectList.get(j).getInt("newstype"));
                    messageShowBean.setTime(jsonObjectList.get(j).getString("createtimestr"));
                    messageShowBean.setIsused(jsonObjectList.get(j).getString("isused"));
                    list.add(messageShowBean);
                }
                if (type==0){
                    swipeLayout.setRefreshing(false);
                    listView.setAdapter(myMessageAdapter);
                }else if (type==1){
                    myMessageAdapter.notifyDataSetChanged();
                    MyMessageListActivity.s=1;
                    swipeLayout.setLoading(false);
                }else if (type==2){
                    myMessageAdapter.notifyDataSetChanged();
                }
                if (jsonObjectList.size() < 15) {
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
