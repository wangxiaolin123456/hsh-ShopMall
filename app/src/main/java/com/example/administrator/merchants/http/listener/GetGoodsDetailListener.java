package com.example.administrator.merchants.http.listener;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.Response;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.diy.widget.CircularImage;
import com.example.administrator.merchants.R;
import com.example.administrator.merchants.activity.GoodsDetailsActivity;
import com.example.administrator.merchants.common.GlideTest;
import com.example.administrator.merchants.common.MyMath;
import com.example.administrator.merchants.http.HttpUrl;
import com.example.administrator.merchants.http.Status;
import com.example.administrator.merchants.http.show.CarouselShowBean;
import com.example.administrator.merchants.http.show.CommonImageShowBean;
import com.example.administrator.merchants.http.show.GoodsDetailShowBean;
import com.example.administrator.merchants.http.show.ImageShowBean;
import com.example.administrator.merchants.utils.LogUtil;
import com.example.administrator.merchants.views.v.GlideImageLoader;
import com.joanzapata.android.QuickAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/8/17 0017 14:10
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：获取商品详情信息
 */
public class GetGoodsDetailListener implements Response.Listener<JSONObject> {
    private Context context;
    private TextView shopName;
    private TextView goodsDetail_monNo;
    private TextView goodsDetail_storeNo;
    private TextView textView_sale_price;
    private TextView goodsDetail_info;
    private Banner banner;
    private List<CarouselShowBean> listCarouselShowBean;
    private List<String> listImage;
    private TextView noHave;
    private ImageView anim;
    private QuickAdapter<String> imgAdapter;
    private List<String> bannerList = new ArrayList<>();
    private TextView evaluateAllNumTv;//评价总数量
    private LinearLayout haveEvaluateLine;//有评价需要显示
    private CircularImage circularImage;//评价头像
    private TextView nicknameTv;//评价昵称
    private TextView contentTv;//评价内容
    private TextView timeTv;//评价时间
    private RatingBar ratingBar;//小星星
    private TextView yBeiBiTv;
    private List<GoodsDetailShowBean> list = new ArrayList<>();
    private LinearLayout pjLine;
    private ImageView pjIconOne;
    private ImageView pjIconTwo;
    private ImageView pjIconThree;
    public GetGoodsDetailListener(Context context, TextView shopName, TextView goodsDetail_monNo, TextView goodsDetail_storeNo, TextView textView_sale_price, TextView goodsDetail_info, List<CarouselShowBean> listCarouselShowBean
            , Banner banner,
                                  List<String> listImage, TextView noHave, ImageView anim
            , QuickAdapter<String> imgAdapter, TextView evaluateAllNumTv,
                                  LinearLayout haveEvaluateLine, CircularImage circularImage
            , TextView nicknameTv, TextView contentTv, TextView timeTv, RatingBar ratingBar
            , TextView yBeiBiTv, LinearLayout pjLine,
                                  ImageView pjIconOne,
                                  ImageView pjIconTwo,
                                  ImageView pjIconThree) {
        this.context = context;
        this.shopName = shopName;
        this.listCarouselShowBean = listCarouselShowBean;
        this.goodsDetail_monNo = goodsDetail_monNo;
        this.goodsDetail_storeNo = goodsDetail_storeNo;
        this.textView_sale_price = textView_sale_price;
        this.goodsDetail_info = goodsDetail_info;
        this.banner = banner;
        this.listImage = listImage;
        this.noHave = noHave;
        this.anim = anim;
        this.imgAdapter = imgAdapter;
        this.evaluateAllNumTv = evaluateAllNumTv;
        this.haveEvaluateLine = haveEvaluateLine;
        this.circularImage = circularImage;
        this.nicknameTv = nicknameTv;
        this.contentTv = contentTv;
        this.timeTv = timeTv;
        this.ratingBar = ratingBar;
        this.yBeiBiTv = yBeiBiTv;
        this.pjLine=pjLine;
        this.pjIconOne=pjIconOne;
        this.pjIconTwo=pjIconTwo;
        this.pjIconThree=pjIconThree;

    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        LogUtil.i("商品详情" + jsonObject.toString());
        if (Status.status(jsonObject)) {
            //返回成功
            try {
                JSONObject object = (JSONObject) jsonObject.get("merinfo");
                GoodsDetailShowBean goodsDetailBean = new GoodsDetailShowBean();
                goodsDetailBean.setImgpfile(HttpUrl.BaseImageUrl + object.getString("imgpfile"));
                goodsDetailBean.setMerdescr(object.getString("merdescr"));
                goodsDetailBean.setMerid(object.getString("merid"));
                goodsDetailBean.setMername(object.getString("mername"));
                goodsDetailBean.setMonthsalenum(object.getDouble("monthsalenum"));
                goodsDetailBean.setSaleprice(object.getDouble("saleprice"));
                goodsDetailBean.setScoreavg(object.getDouble("scorememavg"));
                GoodsDetailsActivity.isModel = object.getString("ismodel");//GoodsDetailsFragment
                if (object.getDouble("storenum") <= 0) {//库存
                    goodsDetailBean.setStorenum(Double.parseDouble("0"));
                } else {
                    goodsDetailBean.setStorenum(object.getDouble("storenum"));
                }
                shopName.setText(goodsDetailBean.getMername());
                goodsDetail_monNo.setText(Integer.parseInt(new DecimalFormat("0").format(goodsDetailBean.getMonthsalenum())) + "");
                goodsDetail_storeNo.setText(Integer.parseInt(new DecimalFormat("0").format(goodsDetailBean.getStorenum())) + "");
                textView_sale_price.setText(String.valueOf(goodsDetailBean.getSaleprice()));
                if (goodsDetailBean.getMerdescr().equals("")) {
                    goodsDetail_info.setText("该产品暂无详情！");
                } else {
                    goodsDetail_info.setText(goodsDetailBean.getMerdescr());//" \u3000\u3000" +
                }

                //评价部分
                evaluateAllNumTv.setText("商品评价(" + object.getInt("scorenum") + ")");


                JSONArray evaluateArray = (JSONArray) jsonObject.get("evaluateInfo");
                if (evaluateArray.length() != 0) {
                    //有评价
                    haveEvaluateLine.setVisibility(View.VISIBLE);
                    JSONObject evaluateObject = (JSONObject) evaluateArray.get(0);


                    List<JSONObject> pjListJSONObject = new ArrayList<>();
                    for (int i = 0; i < evaluateArray.length(); i++) {
                        JSONObject pjListObject = (JSONObject) evaluateArray.get(i);
                        pjListJSONObject.add(pjListObject);
                    }
                    for (int j = 0; j < pjListJSONObject.size(); j++) {
                        GoodsDetailShowBean pjBean = new GoodsDetailShowBean();
                        pjBean.setEvaluateid(pjListJSONObject.get(j).getString("evaluateid"));
                        pjBean.setEvaluatedescr(pjListJSONObject.get(j).getString("evaluatedescr"));
                        pjBean.setEvaluatedscore(pjListJSONObject.get(j).getInt("evaluatescore"));
                        pjBean.setEvaluatetimestr(pjListJSONObject.get(j).getString("evaluatetimestr"));
                        if ("".equals(pjListJSONObject.get(j).getString("imgsfile"))) {
                            pjBean.setImgsfile("");
                        } else {
                            pjBean.setImgsfile(HttpUrl.BaseImageUrl + pjListJSONObject.get(j).getString("imgsfile"));
                        }
                        pjBean.setStorename(pjListJSONObject.get(j).getString("storename"));
                        pjBean.setIsanonymous(pjListJSONObject.get(j).getString("isanonymous"));
                        pjBean.setMerid(pjListJSONObject.get(j).getString("merid"));
                        pjBean.setOrdno(pjListJSONObject.get(j).getString("ordno"));
                        list.add(pjBean);
                    }

                    if (evaluateObject.getString("isanonymous").equals("0")) {
                        //图片缓存
                        if ("".equals(evaluateObject.getString("imgsfile"))) {
                            circularImage.setImageResource(R.drawable.default_avatar);
                        } else {
                            Glide.with(context).
                                    load(evaluateObject.getString("imgsfile")).
                                    error(R.drawable.default_avatar).
                                    placeholder(R.drawable.default_avatar).into(circularImage);
                        }
                        nicknameTv.setText(evaluateObject.getString("memname"));//不对
                    } else {
                        nicknameTv.setText("匿名评价");
                        circularImage.setImageResource(R.drawable.default_avatar);
                    }
                    ratingBar.setRating(evaluateObject.getInt("evaluatescore"));
                    if (evaluateObject.getString("evaluatedescr").equals("")) {
                        contentTv.setText("非常满意！！");
                    } else {
                        contentTv.setText(evaluateObject.getString("evaluatedescr"));
                    }
                    timeTv.setText(evaluateObject.getString("evaluatetimestr"));
                } else {
                    haveEvaluateLine.setVisibility(View.GONE);
                }

//                    评价图片

                JSONArray imgListArray = (JSONArray) jsonObject.get("evaluateImg");
                List<JSONObject> imgListJsonObject = new ArrayList<>();
                for (int i = 0; i < imgListArray.length(); i++) {
                    JSONObject pjobject = (JSONObject) imgListArray.get(i);
                    imgListJsonObject.add(pjobject);
                }
                List<ImageShowBean> imageBeanList = new ArrayList<>();
                for (int i = 0; i < imgListJsonObject.size(); i++) {
                    ImageShowBean imageBean = new ImageShowBean();
                    imageBean.setEvaluateid(imgListJsonObject.get(i).getString("evaluateid"));
                    imageBean.setImgpfile(HttpUrl.BaseImageUrl + imgListJsonObject.get(i).getString("imgpfile"));
                    imageBean.setImgsfile(HttpUrl.BaseImageUrl + imgListJsonObject.get(i).getString("imgsfile"));
                    imageBeanList.add(imageBean);
                }
                List<CommonImageShowBean> images = new ArrayList<>();
                for (int j = 0; j < imageBeanList.size(); j++) {
                    if (list.get(0).getEvaluateid().
                            equals(imageBeanList.get(j).getEvaluateid())) {
                        CommonImageShowBean er = new CommonImageShowBean();
                        er.setImages(imageBeanList.get(j).getImgsfile());
                        er.setImagep(imageBeanList.get(j).getImgpfile());
                        images.add(er);
                    }
                }
                LogUtil.i("评价图片集合" + images.size());
                if (images.size() == 0) {
                    pjLine.setVisibility(View.GONE);
                } else {
                    pjLine.setVisibility(View.VISIBLE);
                    if (images.size() == 1) {
                        pjIconThree.setVisibility(View.GONE);
                        pjIconTwo.setVisibility(View.GONE);
                        Glide.with(context).load(
                                images.get(0).getImages()).
                                diskCacheStrategy(DiskCacheStrategy.ALL).
                                placeholder(R.drawable.image_loading).into(pjIconOne);

                    } else if (images.size() == 2) {
                        pjIconThree.setVisibility(View.GONE);
                        Glide.with(context).load(
                                images.get(0).getImages()).
                                diskCacheStrategy(DiskCacheStrategy.ALL).
                                placeholder(R.drawable.image_loading).into(pjIconOne);
                        Glide.with(context).load(
                                images.get(0).getImages()).
                                diskCacheStrategy(DiskCacheStrategy.ALL).
                                placeholder(R.drawable.image_loading).into(pjIconTwo);

                    } else if (images.size() == 3) {

                        Glide.with(context).load(
                                images.get(0).getImages()).
                                diskCacheStrategy(DiskCacheStrategy.ALL).
                                placeholder(R.drawable.image_loading).into(pjIconOne);
                        Glide.with(context).load(
                                images.get(0).getImages()).
                                diskCacheStrategy(DiskCacheStrategy.ALL).
                                placeholder(R.drawable.image_loading).into(pjIconTwo);
                        Glide.with(context).load(
                                images.get(0).getImages()).
                                diskCacheStrategy(DiskCacheStrategy.ALL).
                                placeholder(R.drawable.image_loading).into(pjIconThree);
                    }
                }
                //评价结束
                //银贝币取值
                if (object.getString("usedsilver").equals("")) {
                    yBeiBiTv.setText("银贝币可抵用0元");
                } else {
                    yBeiBiTv.setText("银贝币可抵用" + object.getString("usedsilver") + "元");
                }

                //------------
                //轮播图赋值
                JSONArray jsonArray1 = jsonObject.getJSONArray("imglist");
                List<JSONObject> objectArrayList = new ArrayList<>();
                for (int i = 0; i < jsonArray1.length(); i++) {
                    objectArrayList.add((JSONObject) jsonArray1.get(i));
                }
                listCarouselShowBean.clear();
                bannerList.clear();
                for (int j = 0; j < objectArrayList.size(); j++) {
                    CarouselShowBean carouselShowBean = new CarouselShowBean();
                    carouselShowBean.setImgpfile(HttpUrl.BaseImageUrl + objectArrayList.get(j).getString("imgpfile"));
                    bannerList.add(HttpUrl.BaseImageUrl + objectArrayList.get(j).getString("imgpfile"));
                    listCarouselShowBean.add(carouselShowBean);
                }
//                List<View> views = new ArrayList<>();
//                for (int i = 0; i < listCarouselShowBean.size(); i++) {
//                    ImageView imageView = new ImageView(context);
//                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);//等比例放大图片ScaleType.FIT_CENTER
//                    Glide.with(context.getApplicationContext()).load(listCarouselShowBean.get(i).getImgpfile()).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.image_loading)
//                            .into(imageView);
//                    views.add(imageView);
//                }
                banner.setImageLoader(new GlideImageLoader());
                banner.setImages(bannerList);
                banner.setIndicatorGravity(BannerConfig.CENTER);//TODO
                banner.setDelayTime(2000);
                if (!((Activity) context).isFinishing()) {
                    banner.start();
                }
//                viewPage.setViewPageViews(views);
                //不启动轮播的线程
//                viewPage.setIsAlive(false);
//                listView.addHeaderView(headView);
                JSONArray jsonArray = (JSONArray) jsonObject.get("descrimglist");
                if (jsonArray.length() != 0) {
                    List<JSONObject> imgObjectList = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object1 = new JSONObject();
                        object1 = (JSONObject) jsonArray.get(i);
                        imgObjectList.add(object1);
                    }
                    for (int j = 0; j < imgObjectList.size(); j++) {
                        String s = HttpUrl.BaseImageUrl + imgObjectList.get(j).getString("imgpfile");
                        listImage.add(s);
                    }
                    noHave.setVisibility(View.GONE);
                } else {
                    noHave.setVisibility(View.VISIBLE);
                }
//                listView.setAdapter(new GoodsDetailImagesAdapter(context, listImage));
                imgAdapter.addAll(listImage);
                GoodsDetailsActivity.loding = 1;//GoodsDetailsFragment
                GlideTest.imageCancle(anim);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            //返回失败
            Status.fail(context, jsonObject);
        }

    }
}
