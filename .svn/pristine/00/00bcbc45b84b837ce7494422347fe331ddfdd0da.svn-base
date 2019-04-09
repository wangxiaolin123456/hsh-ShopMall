package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Response;
import com.example.administrator.merchants.fragment.MyFragment;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.http.show.UpdateShowBean;
import com.example.administrator.merchants.common.update.CheckVersionTask;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者：韩宇 on 2017/7/3 0003 15:17
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：
 */
public class UpdateInfoListener implements Response.Listener<JSONObject> {
    private Context context;
    private String versionName;
    private int type;
    private TextView updateAdd, newMost;

    public UpdateInfoListener(Context context, String versionName, int type) {
        this.context = context;
        this.versionName = versionName;
        this.type = type;
    }

    public UpdateInfoListener(Context context, int type, TextView updateAdd, TextView newMost) {
        this.context = context;
        this.type = type;
        this.updateAdd = updateAdd;
        this.newMost = newMost;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //返回成功
            if (type==2){
                try {
                    JSONObject tel = jsonObject.getJSONObject("systeminfo");
                    String tells = tel.getString("infovalue");
                    if (tells.compareTo(MyFragment.nowNew) > 0) {
                        newMost.setVisibility(View.VISIBLE);
                        updateAdd.setText(tells);
                    } else {
                        newMost.setVisibility(View.GONE);
                        updateAdd.setText("已是最新版本");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else {
                UpdateShowBean updateShowBean = new UpdateShowBean();
                JSONObject systemInfo = null;
                try {
                    systemInfo = (JSONObject) jsonObject.get("systeminfo");
                    String version = systemInfo.getString("infovalue");
                    String url = "http://www.hsh55555.com:8090/hshResource/apk/store-app-release.apk";
                    String description = "检测到新版本为" + version + "，" + "您使用的是" + versionName + "版，" + "请立即更新！";
                    updateShowBean.setVersion(version);
                    updateShowBean.setUrl(url);
                    updateShowBean.setDescription(description);
                    CheckVersionTask checkVersionTask = new CheckVersionTask(context, versionName, updateShowBean,type);
                    new Thread(checkVersionTask).start();//启动更新的线程
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else {
            //返回失败
            Status.fail(context, jsonObject);
        }
    }
}
