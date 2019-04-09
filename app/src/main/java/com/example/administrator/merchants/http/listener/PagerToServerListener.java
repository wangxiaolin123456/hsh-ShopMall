package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Response;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.merchants.R;
import com.example.administrator.merchants.activity.EnlargePictureActivity;
import com.example.administrator.merchants.activity.GoodsDetailsActivity;
import com.example.administrator.merchants.common.views.CustomerViewPage;
import com.example.administrator.merchants.http.HttpUrl;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.http.show.CarouselShowBean;
import com.example.administrator.merchants.utils.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/8/18 0018 08:51
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：首页轮播
 */
public class PagerToServerListener implements Response.Listener<JSONObject> {
    private Context context;
    private List<View> views;
    private CustomerViewPage viewPage;

    public PagerToServerListener(Context context, CustomerViewPage viewPage) {
        this.context = context;
        this.viewPage = viewPage;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        LogUtil.i("轮播图"+jsonObject.toString());
        if (Status.status(jsonObject)) {
            //返回成功
            try {
                views = new ArrayList<>();
                JSONArray jsonArray = jsonObject.getJSONArray("list");
                List<JSONObject> objectArrayList = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    objectArrayList.add((JSONObject) jsonArray.get(i));
                }
                final List<CarouselShowBean> listCarouselShowBean = new ArrayList<>();
                for (int j = 0; j < objectArrayList.size(); j++) {
                    CarouselShowBean carouselShowBean = new CarouselShowBean();
                    carouselShowBean.setAdvname(objectArrayList.get(j).getString("advname"));
                    carouselShowBean.setImgpfile(objectArrayList.get(j).getString("imgpfile"));
                    carouselShowBean.setLinkpfile(objectArrayList.get(j).getString("linkpfile"));
                    carouselShowBean.setLinktype(objectArrayList.get(j).getString("linktype"));
                    carouselShowBean.setMerid(objectArrayList.get(j).getString("merid"));
                    carouselShowBean.setStoreid(objectArrayList.get(j).getString("storeid"));
                    carouselShowBean.setStorename(objectArrayList.get(j).getString("storename"));
                    listCarouselShowBean.add(carouselShowBean);
                }
                for (int i = 0; i < objectArrayList.size(); i++) {
                    ImageView imageView = new ImageView(context);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    Glide.with(context.getApplicationContext()).load(HttpUrl.BaseImageUrl + listCarouselShowBean.get(i).getImgpfile()).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.image_loading).into(imageView);
                    final int finalI = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //轮播图点击放大的监听
                            if ("".equals(listCarouselShowBean.get(finalI).getLinkpfile()) &&
                                    "".equals(listCarouselShowBean.get(finalI).getMerid()) &&
                                    "".equals(listCarouselShowBean.get(finalI).getStoreid())) {
                            } else if ("img".equals(listCarouselShowBean.get(finalI).getLinktype())) {
                                Intent i = new Intent();
                                i.putExtra("bigimage", HttpUrl.BaseImageUrl + listCarouselShowBean.get(finalI).getLinkpfile());
                                i.setClass(context, EnlargePictureActivity.class);
                                context.startActivity(i);
                            } else if ("placemer".equals(listCarouselShowBean.get(finalI).getLinktype())) {
                                Intent i = new Intent();
                                i.putExtra("merid", listCarouselShowBean.get(finalI).getMerid());
                                i.setClass(context, GoodsDetailsActivity.class);//CommodityDetailsActivity
                                context.startActivity(i);
                            }
                        }
                    });
                    views.add(imageView);
                }
                viewPage.setViewPageViews(views);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            //返回失败
            Status.fail(context, jsonObject);
        }
    }
}
