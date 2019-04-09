package com.example.administrator.merchants.http.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.android.volley.Response;
import com.example.administrator.merchants.R;
import com.example.administrator.merchants.activity.CommodityManagementListActivity;
import com.example.administrator.merchants.activity.MerchantUpdateGoodsActivity;
import com.example.administrator.merchants.adapter.UpdateImageAdapter;
import com.example.administrator.merchants.http.HttpUrl;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.http.post.MerIdPostBean;
import com.example.administrator.merchants.http.show.BitmapShowBean;
import com.example.administrator.merchants.common.views.MyGridView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * 作者：韩宇 on 2017/6/24 0024 09:58
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：商家商品修改
 */
public class GoodsDetailListener implements Response.Listener<JSONObject> {
    private Context context;
    private MerIdPostBean merIdPostBean;
    private int type;
    private ImageLoader imageLoader;
    private DisplayImageOptions options;
    private List<BitmapShowBean> bitmapShowBeanList;
    private ImageView imageView;
    private MyGridView gridView;
    private UpdateImageAdapter updateImageAdapter;
    private ToggleButton toggleButton;
    private View beiBiLine;
    private EditText number,price,beiBi;
    private TextView commodityNameShow,detailShow,classifyShow;
    public GoodsDetailListener(Context context, MerIdPostBean merIdPostBean,int type) {
        this.context = context;
        this.merIdPostBean = merIdPostBean;
        this.type=type;

    }
    public GoodsDetailListener(Context context,int type,ImageLoader imageLoader,DisplayImageOptions options,List<BitmapShowBean> bitmapShowBeanList
    ,ImageView imageView,MyGridView gridView,UpdateImageAdapter updateImageAdapter,ToggleButton toggleButton,View beiBiLine,EditText number,TextView commodityNameShow
    ,TextView detailShow,TextView classifyShow,EditText price,EditText beiBi){
        this.context = context;
        this.type=type;
        this.imageLoader=imageLoader;
        this.options=options;
        this.bitmapShowBeanList=bitmapShowBeanList;
        this.imageView=imageView;
        this.gridView=gridView;
        this.updateImageAdapter=updateImageAdapter;
        this.toggleButton=toggleButton;
        this.beiBiLine=beiBiLine;
        this.number=number;
        this.commodityNameShow=commodityNameShow;
        this.detailShow=detailShow;
        this.classifyShow=classifyShow;
        this.price=price;
        this.beiBi=beiBi;
    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (Status.status(jsonObject)) {
            //返回成功
            if (type==0){
                CommodityManagementListActivity.ay = false;
                Intent intent = new Intent();
                intent.putExtra("merid", merIdPostBean.getMerid());//商家编码
                intent.setClass(context, MerchantUpdateGoodsActivity.class);
                context.startActivity(intent);
            }else if (type==1){
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("imglist");
                    List<JSONObject> objectList = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        objectList.add((JSONObject) jsonArray.get(i));
                    }
                    bitmapShowBeanList.clear();
                    for (int j = 0; j < objectList.size(); j++) {
                        BitmapShowBean bitmapShowBean = new BitmapShowBean();
                        bitmapShowBean.setType(1);
                        bitmapShowBean.setImgid(objectList.get(j).getString("imgid"));//图片id
                        bitmapShowBean.setPath(HttpUrl.BaseImageUrl + objectList.get(j).getString("imgpfile"));
                        bitmapShowBeanList.add(bitmapShowBean);
                    }
                    BitmapShowBean bitmapShowBean = new BitmapShowBean();
                    bitmapShowBean.setType(0);
                    bitmapShowBeanList.add(bitmapShowBean);
                    if (bitmapShowBeanList.size() == 7) {
                        bitmapShowBeanList.remove(bitmapShowBeanList.size() - 1);
                    }
                    gridView.setAdapter(updateImageAdapter);
                    JSONObject object = jsonObject.getJSONObject("storemerinfo");
                    if ("".equals(object.getString("imgsfile"))) {
                        imageView.setImageResource(R.drawable.image_loading);
                    } else {
                        imageLoader.displayImage(HttpUrl.BaseImageUrl + object.getString("imgsfile"), imageView, options);
                    }
                    if ("1".equals(object.getString("isstorenum"))) {
                        toggleButton.setChecked(true);
                        beiBiLine.setVisibility(View.VISIBLE);
                        number.setVisibility(View.VISIBLE);
                    } else {
                        toggleButton.setChecked(false);
                        beiBiLine.setVisibility(View.GONE);
                        number.setVisibility(View.GONE);
                    }
                    commodityNameShow.setText(object.getString("mername"));
                    detailShow.setText(object.getString("merdescr"));
                    classifyShow.setText(object.getString("menuname"));
                    MerchantUpdateGoodsActivity.menuId = object.getString("mermenuid");
                    JSONArray jsonArrayMode = jsonObject.getJSONArray("specslist");
                    List<JSONObject> objectListMode = new ArrayList<>();
                    for (int i = 0; i < jsonArrayMode.length(); i++) {
                        objectListMode.add((JSONObject) jsonArrayMode.get(i));
                    }
                    price.setText(objectListMode.get(0).getString("saleprice"));
                    if (toggleButton.isChecked()) {
                        number.setText(objectListMode.get(0).getString("storenum"));
                    }
                    beiBi.setText(objectListMode.get(0).getString("retbeibiamt"));
                    MerchantUpdateGoodsActivity.specsId = objectListMode.get(0).getString("specsid");
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
