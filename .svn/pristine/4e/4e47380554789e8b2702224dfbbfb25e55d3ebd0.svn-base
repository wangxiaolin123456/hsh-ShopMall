package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.widget.ListView;

import com.android.volley.Response;
import com.example.administrator.merchants.adapter.MenuAdapter;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.http.show.PopupMenuShowBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/7/20 0020 14:00
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：原产地商品搜索菜单
 */
public class SearchMenuListener implements Response.Listener<JSONObject>{
    private Context context;
    private int type;
    private List<PopupMenuShowBean> listOne,listTwo;
    private ListView list_menu2;
    private MenuAdapter secondMenuAdapter;
    public SearchMenuListener(Context context,int type,List<PopupMenuShowBean> listOne){
        this.context=context;
        this.type=type;
        this.listOne=listOne;
    }
    public SearchMenuListener(Context context,int type,List<PopupMenuShowBean> listTwo,ListView list_menu2,MenuAdapter secondMenuAdapter){
        this.context=context;
        this.type=type;
        this.listTwo=listTwo;
        this.list_menu2=list_menu2;
        this.secondMenuAdapter=secondMenuAdapter;
    }
    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)){
            //返回成功
            try {
                if (type==0){
                    listOne.clear();
                    JSONArray menulist = jsonObject.getJSONArray("list");
                    List<JSONObject> objectList = new ArrayList<>();
                    for (int i = 0; i < menulist.length(); i++) {
                        objectList.add((JSONObject) menulist.get(i));
                    }
                    for (int i = 0; i < objectList.size(); i++) {
                        PopupMenuShowBean originalGridBean = new PopupMenuShowBean();
                        originalGridBean.setColor(0);
                        originalGridBean.setImgpfile(objectList.get(i).getString("imgpfile"));
                        originalGridBean.setMenuid(objectList.get(i).getString("menuid"));
                        originalGridBean.setMenuname(objectList.get(i).getString("menuname"));
                        listOne.add(originalGridBean);
                    }
                }else if (type==1){
                    listTwo.clear();
                    JSONArray menulist = jsonObject.getJSONArray("list");
                    List<JSONObject> objectList = new ArrayList<>();
                    for (int i = 0; i < menulist.length(); i++) {
                        objectList.add((JSONObject) menulist.get(i));
                    }
                    for (int i = 0; i < objectList.size(); i++) {
                        PopupMenuShowBean originalGridBean = new PopupMenuShowBean();
                        originalGridBean.setColor(0);
                        originalGridBean.setImgpfile(objectList.get(i).getString("imgpfile"));
                        originalGridBean.setMenuid(objectList.get(i).getString("menuid"));
                        originalGridBean.setMenuname(objectList.get(i).getString("menuname"));
                        listTwo.add(originalGridBean);
                    }
                    list_menu2.setAdapter(secondMenuAdapter);
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
