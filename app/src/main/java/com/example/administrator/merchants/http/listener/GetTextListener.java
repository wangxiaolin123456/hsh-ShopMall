package com.example.administrator.merchants.http.listener;

import android.content.Context;

import com.android.volley.Response;
import com.example.administrator.merchants.activity.test.MainActivity;
import com.example.administrator.merchants.http.Status;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/8/18 0018 08:32
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：首页滚动文字
 */
public class GetTextListener implements Response.Listener<JSONObject> {
    private Context context;

    public GetTextListener(Context context) {
        this.context = context;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //返回成功
            try {
                JSONArray jsonArray = jsonObject.getJSONArray("list");
                List<JSONObject> jsonObjectList = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObjectList.add((JSONObject) jsonArray.get(i));
                }
                String str = "                         ";
                for (int j = 0; j < jsonObjectList.size(); j++) {
                    str += jsonObjectList.get(j).getString("wordname") + "                          ";
                }
                MainActivity.notice.setSelected(true);//这样fragment切换后切换回来才会继续滚动
                MainActivity.notice.requestFocus();
                MainActivity.notice.setText(str);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            //返回失败
            Status.fail(context, jsonObject);
        }
    }
}
