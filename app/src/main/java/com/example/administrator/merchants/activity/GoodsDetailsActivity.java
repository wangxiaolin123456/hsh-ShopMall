package com.example.administrator.merchants.activity;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.diy.widget.CircularImage;
import com.example.administrator.merchants.R;
import com.example.administrator.merchants.activity.test.MainActivity;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseActivity;
import com.example.administrator.merchants.common.GlideTest;
import com.example.administrator.merchants.common.MyMath;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.common.toast.CustomToast;
import com.example.administrator.merchants.common.views.GroupSix;
import com.example.administrator.merchants.http.ErrorListener;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.HttpUrl;
import com.example.administrator.merchants.http.PossObject;
import com.example.administrator.merchants.http.listener.CustomerTelephoneNumberListener;
import com.example.administrator.merchants.http.post.ChangeCarCountPostBean;
import com.example.administrator.merchants.http.post.MerIdPostBean;
import com.example.administrator.merchants.http.post.StoreIdPostBean;
import com.example.administrator.merchants.http.show.CarouselShowBean;
import com.example.administrator.merchants.http.show.ModeListShowBean;
import com.example.administrator.merchants.http.show.ModeShowBean;
import com.example.administrator.merchants.utils.LogUtil;
import com.example.administrator.merchants.utils.ScreenUtils;
import com.example.administrator.merchants.views.v.popupwindow.CommonPopupWindow;
import com.example.administrator.merchants.views.v.scroll.FadingScrollView;
import com.example.administrator.merchants.views.v.scroll.GlideManager;
import com.example.administrator.merchants.views.v.scroll.ListViewForScrollView;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;
import com.youth.banner.Banner;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 商品详情
 * Created by User on 2019/2/21.
 */

public class GoodsDetailsActivity extends BaseActivity implements CommonPopupWindow.ViewInterface {
    private String merid;
    private ListViewForScrollView listView;
    private Banner banner;
    private TextView addShoppingCar;
    private TextView textViewDotCar;
    private RelativeLayout relativeLayoutShoppingCar;
    private TextView shopName;
    private TextView goodsDetail_monNo;
    private TextView goodsDetail_storeNo;
    private TextView textView_sale_price;
    private TextView goodsDetail_info, discount;
    private List<String> listImage;
    private TextView noHave;
    public static String isModel;
    private View popupWindowView;
    private PopupWindow popupWindow;
    private ImageView shopImage;//商品图片
    private TextView shopNamePop;//商品名称
    private TextView shopPrice;//价钱--单价
    private TextView addShopCarText;//加入购物车
    private RelativeLayout addShopCarRel;//购物车
    private TextView shopCarBuyNumber;//加入购物车中商品的数量
    private TextView buyAllPrice;//购买的总价
    private ImageView addImage;//加号
    private ImageView reduceImage;//减号
    private TextView buyNumber;//数量
    private GroupSix goodsViewGroupOne;
    private GroupSix goodsViewGroupTwo;
    private GroupSix goodsViewGroupThree;
    private GroupSix goodsViewGroupFour;
    private GroupSix goodsViewGroupFive;
    private GroupSix goodsViewGroupSix;
    private TextView standardOne;//规格A
    private TextView standardTwo;//规格B
    private TextView standardThree;//规格C
    private TextView standardFour;//规格D
    private TextView standardFive;//规格E
    private TextView standardSix;//规格F
    private LinearLayout modeLineOne;
    private LinearLayout modeLineTwo;
    private LinearLayout modeLineThree;
    private LinearLayout modeLineFour;
    private LinearLayout modeLineFive;
    private LinearLayout modeLineSix;
    private List<ModeShowBean> modeList;
    private ArrayList<String> viewTexts = new ArrayList<>();
    private ArrayList<String> viewTextTwo = new ArrayList<>();
    private ArrayList<String> viewTextThree = new ArrayList<>();
    private ArrayList<String> viewTextFour = new ArrayList<>();
    private ArrayList<String> viewTextFive = new ArrayList<>();
    private ArrayList<String> viewTextSix = new ArrayList<>();
    private List<CarouselShowBean> listCarouselShowBean = new ArrayList<>();
    public static int chooseID = -1;
    public static int chooseIDs = -1;
    public static int chooseIDss = -1;
    public static int chooseIDFour = -1;
    public static int chooseIDFive = -1;
    public static int chooseIDSix = -1;
    public static int loding = 0;
    public static String chooseText;
    public static String chooseTextTwo;
    public static String chooseTextThree;
    public static String chooseTextFour;
    public static String chooseTextFive;
    public static String chooseTextSix;
    private List<ModeListShowBean> modeListShowBeanList = new ArrayList<>();
    private ImageView anim;
    private FadingScrollView fadingScrollView;
    private TextView topGoodsTv;//回到顶部-商品按钮
    private TextView topDetailsTv;//回到详情-详情按钮;
    private RelativeLayout rlayout_title;
    private LinearLayout llayout_details_top;
    private ImageView backIcon;//返回键
    private ImageView moreIcon;//右上角更多

    private RelativeLayout allEvaluateRel;
    private TextView evaluateAllNumTv;//评价总数量
    private LinearLayout haveLine;//有评价需要显示
    private CircularImage circularImage;//评价头像
    private TextView nicknameTv;//评价昵称
    private TextView contentTv;//评价内容
    private TextView timeTv;//评价时间
    private RatingBar ratingBar;//小星星
    private LinearLayout pjLine;
    private ImageView pjIconOne;
    private ImageView pjIconTwo;
    private ImageView pjIconThree;
    private int width;
    private QuickAdapter<String> imgAdapter;
    private TextView yBeiBiTv;//银贝币
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_goods_details);
        unbinder = ButterKnife.bind(this);
        merid = getIntent().getStringExtra("merid");
        initView();
        initImgDatas();//图片列表
        toGetGoodsDetailUp();//商品详情上
    }

    private void initView() {
        yBeiBiTv = findViewById(R.id.goods_details_teXu_ybeibei_tv);//银贝币
        fadingScrollView = findViewById(R.id.sv_container);
        topGoodsTv = findViewById(R.id.detail_top_tv);
        topDetailsTv = findViewById(R.id.detail_top_two_tv);
        rlayout_title = findViewById(R.id.rlayout_title);
        llayout_details_top = findViewById(R.id.llayout_details_top);
        backIcon = findViewById(R.id.iv_good_detai_back);
        moreIcon = findViewById(R.id.details_more_icon);
        allEvaluateRel = findViewById(R.id.look_all_pj_rel);
        //评价部分
        evaluateAllNumTv = findViewById(R.id.details_pj_num_tv);
        haveLine = findViewById(R.id.llyout_evaluate);
        circularImage = findViewById(R.id.civ_user_head);
        nicknameTv = findViewById(R.id.details_pj_name_tv);
        contentTv = findViewById(R.id.details_pj_content_tv);
        timeTv = findViewById(R.id.details_pj_time_tv);
        ratingBar = findViewById(R.id.evaluate_ratingBar_start);
        pjLine = (LinearLayout) findViewById(R.id.comment_img_line);
        pjIconOne = (ImageView) findViewById(R.id.evaluate_image);
        pjIconTwo = (ImageView) findViewById(R.id.evaluate_image_two);
        pjIconThree = (ImageView) findViewById(R.id.evaluate_image_three);
        //评价部分

        anim = findViewById(R.id.image_loading);
        addShoppingCar = findViewById(R.id.goodsDetail_joinCar);
        listView = findViewById(R.id.good_detail_img_listView);
        textViewDotCar = findViewById(R.id.car_dot);
        relativeLayoutShoppingCar = findViewById(R.id.goodsDetail_car_left_down);
        banner = findViewById(R.id.goods_details_banner);
//        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) viewPage.getLayoutParams(); //取控件textView当前的布局参数
//        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
//        int width = wm.getDefaultDisplay().getWidth();
//        linearParams.width = width;// 控件的宽强制设成30
//        linearParams.height = width;// 控件的高强制设成20
//        viewPage.setLayoutParams(linearParams);
        shopName = findViewById(R.id.shopname);
        goodsDetail_monNo = findViewById(R.id.goodsDetail_monNo);
        goodsDetail_storeNo = findViewById(R.id.goodsDetail_storeNo);
        textView_sale_price = findViewById(R.id.textview_sale_price);
        noHave = findViewById(R.id.zanwu);
        goodsDetail_info = findViewById(R.id.goodsDetail_info);
        discount = findViewById(R.id.goodsDetail_discount);
        GlideTest.imageGif(mContext, anim);
        banner.setFocusable(true);
        banner.setFocusableInTouchMode(true);
        banner.requestFocus();
        rlayout_title.setAlpha(0);
        //设置渐变的布局
        fadingScrollView.setFadingView(rlayout_title);
        //设置超过谁会显示
        fadingScrollView.setFadingHeightView(llayout_details_top);
        Http.discount(mContext, discount);
        relativeLayoutShoppingCar.setOnClickListener(new View.OnClickListener() {//进入购物车
            @Override
            public void onClick(View v) {
                if (UserInfo.getInstance().getUser(mContext) == null) {
                    Intent intent = new Intent();
                    intent.setClass(mContext, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    startActivity(new Intent(mContext, ShoppingCarActivity.class));
                    finish();
                }
            }
        });
        addShoppingCar.setOnClickListener(new View.OnClickListener() { //加入购物车的监听
            @Override
            public void onClick(View v) {
                if (UserInfo.getInstance().getUser(mContext) == null) {
                    Intent intent = new Intent();
                    intent.setClass(mContext, LoginActivity.class);
                    startActivity(intent);
                } else {
                    if (loding == 1) {
                        if (Integer.parseInt(goodsDetail_storeNo.getText().toString()) <= 0) {
                            CustomToast.getInstance(mContext).setMessage("不能加入购物车，库存已不足！");
                        } else {
                            if (isModel.equals("1")) {
                                initPop(v, 0);
                            } else if (isModel.equals("0")) {
                                toAddShoppingCars();//加入购物车的接口-不支持多型号的加入购物车
                                int y = Integer.parseInt(textViewDotCar.getText().toString()) + 1;
                                textViewDotCar.setText(String.valueOf(y));
                            }
                        }
                    } else {
                        CustomToast.getInstance(mContext).setMessage("还没加载完，稍等！");
                    }
                }
            }
        });
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        allEvaluateRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查看全部评价
                Intent intent = new Intent();
                intent.putExtra("merid", merid);
                intent.setClass(mContext, GoodsEvaluateListActivity.class);
                startActivity(intent);
            }
        });
        topGoodsTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fadingScrollView.scrollTo(0, 0);
            }
        });
        moreIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPop(moreIcon);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick({R.id.share_icon})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.share_icon:
                LogUtil.i("分享功能暂未开放！");
                CustomToast.getInstance(mContext).setMessage("分享功能暂未开放！");
                break;
        }
    }

    // TODO: 16/8/21 图片
    private void initImgDatas() {
        //详情图片
        width = ScreenUtils.getScreenWidth(getApplicationContext());
        listImage = new ArrayList<>();
        imgAdapter = new QuickAdapter<String>(mContext, R.layout.item_goods_detail_images) {
            @Override
            protected void convert(BaseAdapterHelper helper, String item) {
                ImageView iv = helper.getView(R.id.imgv);
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) iv.getLayoutParams();
                params.width = width;
                params.height = width / 2;
                iv.setLayoutParams(params);
                GlideManager.getsInstance().loadImageViewH(getApplicationContext(), item, iv);
            }
        };
        listView.setAdapter(imgAdapter);
    }

    /**
     * 弹出规格的popupWindow
     * 你说规格能有超过5条
     *
     * @param parent
     * @param i
     */
    private void initPop(View parent, int i) { //初始化popup
        modeList = new ArrayList<>();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        popupWindowView = inflater.inflate(R.layout.popup_view_standard, null);
        popupWindow = new PopupWindow(popupWindowView, ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT, true);//height
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.3f;
        getWindow().setAttributes(params);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        //初始化控件
        shopImage = popupWindowView.findViewById(R.id.choose_goods_standard_image);//商品图片
        shopNamePop = popupWindowView.findViewById(R.id.choose_goods_standard_name);//商品名称
        shopPrice = popupWindowView.findViewById(R.id.choose_goods_standard_money_text);//商品价格
        addShopCarText = popupWindowView.findViewById(R.id.choose_goods_standard_joinCar);//加入购物车的字样
        addShopCarRel = popupWindowView.findViewById(R.id.choose_goods_standard_left_down);//加入购物车的Rel
        shopCarBuyNumber = popupWindowView.findViewById(R.id.choose_goods_standard_car_dot);//购物车中的购买商品的数量
        buyAllPrice = popupWindowView.findViewById(R.id.choose_goods_price_two);//变化的总价格
        addImage = popupWindowView.findViewById(R.id.choose_goods_buy_add);//加号
        reduceImage = popupWindowView.findViewById(R.id.choose_goods_buy_jian);//减号
        buyNumber = popupWindowView.findViewById(R.id.choose_goods_buy_num);//购买商品的数量
        goodsViewGroupOne = popupWindowView.findViewById(R.id.choose_goods_goodsViewGroup_one);
        goodsViewGroupTwo = popupWindowView.findViewById(R.id.choose_goods_goodsViewGroup_two);
        goodsViewGroupThree = popupWindowView.findViewById(R.id.choose_goods_goodsViewGroup_three);
        goodsViewGroupFour = popupWindowView.findViewById(R.id.choose_goods_goodsViewGroup_four);
        goodsViewGroupFive = popupWindowView.findViewById(R.id.choose_goods_goodsViewGroup_five);
        goodsViewGroupSix = popupWindowView.findViewById(R.id.choose_goods_goodsViewGroup_six);
        standardOne = popupWindowView.findViewById(R.id.choose_goods_standard_text_one);
        standardTwo = popupWindowView.findViewById(R.id.choose_goods_standard_text_two);
        standardThree = popupWindowView.findViewById(R.id.choose_goods_standard_text_three);
        standardFour = popupWindowView.findViewById(R.id.choose_goods_standard_text_four);
        standardFive = popupWindowView.findViewById(R.id.choose_goods_standard_text_five);
        standardSix = popupWindowView.findViewById(R.id.choose_goods_standard_text_six);
        modeLineOne = popupWindowView.findViewById(R.id.one_mode_line);
        modeLineTwo = popupWindowView.findViewById(R.id.two_mode_line);
        modeLineThree = popupWindowView.findViewById(R.id.three_mode_line);
        modeLineFour = popupWindowView.findViewById(R.id.four_mode_line);
        modeLineFive = popupWindowView.findViewById(R.id.five_mode_line);
        modeLineSix = popupWindowView.findViewById(R.id.six_mode_line);
        modeList = new ArrayList<>();
        viewTexts.clear();
        viewTextTwo.clear();
        viewTextThree.clear();
        viewTextFour.clear();
        viewTextFive.clear();
        viewTextSix.clear();
        shopDetails();
        shopNamePop.setText(shopName.getText().toString());
        shopPrice.setText(textView_sale_price.getText().toString());
        shopCarBuyNumber.setText(textViewDotCar.getText().toString());
        buyAllPrice.setText(textView_sale_price.getText().toString());
        buyNumber.setText("1");
        reduceImage.setImageResource(R.drawable.sub_goods_gary_image);
        Glide.with(mContext).load(listCarouselShowBean.get(0).getImgpfile()).
                diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.image_loading).into(shopImage);
        addShopCarRel.setOnClickListener(new View.OnClickListener() {//进入购物车
            @Override
            public void onClick(View v) {
                if (UserInfo.getInstance().getUser(mContext) == null) {
                    Intent intent = new Intent();
                    intent.setClass(mContext, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    startActivity(new Intent(mContext, ShoppingCarActivity.class));
                    finish();
                }
            }
        });
        //图片的监听  点击放大
        shopImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("bigimage", listCarouselShowBean.get(0).getImgpfile());
                intent.setClass(mContext, EnlargePictureActivity.class);
                startActivity(intent);

            }
        });
        //加号的监听
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reduceImage.setImageResource(R.drawable.sub_goods_image);
                int x = Integer.parseInt(buyNumber.getText().toString());
                x++;
                buyNumber.setText(String.valueOf(x));
                double result1 = MyMath.mul(textView_sale_price.getText().toString(), String.valueOf(x));
                buyAllPrice.setText(result1 + "");
            }
        });
        //减号的监听
        reduceImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int y = Integer.parseInt(buyNumber.getText().toString());
                if (y <= 0) {
                } else {
                    y--;
                    if (y <= 1) {
                        reduceImage.setImageResource(R.drawable.sub_goods_gary_image);
                        buyNumber.setText("1");
                        buyAllPrice.setText((Double.parseDouble(textView_sale_price.getText().toString())) + "");
                    } else {
                        reduceImage.setImageResource(R.drawable.sub_goods_image);
                        buyNumber.setText(String.valueOf(y));
                        double result1 = MyMath.mul(textView_sale_price.getText().toString(), String.valueOf(y));
                        buyAllPrice.setText(result1 + "");
                    }
                }
            }
        });
        //加入购物车的监听
        addShopCarText.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  if (modeList.size() == 1) {
                                                      if (chooseID >= 0) {
                                                          popupWindow.dismiss();
                                                          toAddShoppingCar();//加入购物车的接口
                                                          int y = Integer.parseInt(textViewDotCar.getText().toString()) + Integer.parseInt(buyNumber.getText().toString());
                                                          textViewDotCar.setText(String.valueOf(y));
                                                      } else {
                                                          CustomToast.getInstance(mContext).setMessage("请选择规格！");

                                                      }
                                                  } else if (modeList.size() == 2) {
                                                      if (chooseID >= 0 && chooseIDs >= 0) {
                                                          popupWindow.dismiss();
                                                          toAddShoppingCar();//加入购物车的接口
                                                          int y = Integer.parseInt(textViewDotCar.getText().toString()) + Integer.parseInt(buyNumber.getText().toString());
                                                          textViewDotCar.setText(String.valueOf(y));
                                                      } else {
                                                          if (chooseID < 0 && chooseIDs < 0) {
                                                              CustomToast.getInstance(mContext).setMessage("请选择规格！");
                                                          } else if (chooseID < 0) {
                                                              CustomToast.getInstance(mContext).setMessage("请选择" + modeList.get(0).getModelname() + "！");
                                                          } else if (chooseIDs < 0) {
                                                              CustomToast.getInstance(mContext).setMessage("请选择" + modeList.get(1).getModelname() + "！");
                                                          }
                                                      }
                                                  } else if (modeList.size() == 3) {
                                                      if (chooseID >= 0 && chooseIDs >= 0 && chooseIDss >= 0) {
                                                          popupWindow.dismiss();
                                                          toAddShoppingCar();//加入购物车的接口
                                                          int y = Integer.parseInt(textViewDotCar.getText().toString()) + Integer.parseInt(buyNumber.getText().toString());
                                                          textViewDotCar.setText(String.valueOf(y));
                                                      } else {
                                                          if (chooseID < 0 && chooseIDs < 0 && chooseIDss < 0) {
                                                              CustomToast.getInstance(mContext).setMessage("请选择规格！");
                                                          } else if (chooseID < 0) {
                                                              CustomToast.getInstance(mContext).setMessage("请选择" + modeList.get(0).getModelname() + "！");
                                                          } else if (chooseIDs < 0) {
                                                              CustomToast.getInstance(mContext).setMessage("请选择" + modeList.get(1).getModelname() + "！");
                                                          } else if (chooseIDss < 0) {
                                                              CustomToast.getInstance(mContext).setMessage("请选择" + modeList.get(2).getModelname() + "！");
                                                          }
                                                      }
                                                  } else if (modeList.size() == 4) {
                                                      if (chooseID >= 0 && chooseIDs >= 0 && chooseIDss >= 0 && chooseIDFour >= 0) {
                                                          popupWindow.dismiss();
                                                          toAddShoppingCar();//加入购物车的接口
                                                          int y = Integer.parseInt(textViewDotCar.getText().toString()) + Integer.parseInt(buyNumber.getText().toString());
                                                          textViewDotCar.setText(String.valueOf(y));
                                                      } else {
                                                          if (chooseID < 0 && chooseIDs < 0 && chooseIDss < 0 && chooseIDFour < 0) {
                                                              CustomToast.getInstance(mContext).setMessage("请选择规格！");
                                                          } else if (chooseID < 0) {
                                                              CustomToast.getInstance(mContext).setMessage("请选择" + modeList.get(0).getModelname() + "！");
                                                          } else if (chooseIDs < 0) {
                                                              CustomToast.getInstance(mContext).setMessage("请选择" + modeList.get(1).getModelname() + "！");
                                                          } else if (chooseIDss < 0) {
                                                              CustomToast.getInstance(mContext).setMessage("请选择" + modeList.get(2).getModelname() + "！");
                                                          } else if (chooseIDFour < 0) {
                                                              CustomToast.getInstance(mContext).setMessage("请选择" + modeList.get(3).getModelname() + "！");
                                                          }
                                                      }
                                                  } else if (modeList.size() == 5) {
                                                      if (chooseID >= 0 && chooseIDs >= 0 && chooseIDss >= 0 && chooseIDFour >= 0 && chooseIDFive >= 0) {
                                                          popupWindow.dismiss();
                                                          toAddShoppingCar();//加入购物车的接口
                                                          int y = Integer.parseInt(textViewDotCar.getText().toString()) + Integer.parseInt(buyNumber.getText().toString());
                                                          textViewDotCar.setText(String.valueOf(y));
                                                      } else {
                                                          if (chooseID < 0 && chooseIDs < 0 && chooseIDss < 0 && chooseIDFour < 0 && chooseIDFive < 0) {
                                                              CustomToast.getInstance(mContext).setMessage("请选择规格！");
                                                          } else if (chooseID < 0) {
                                                              CustomToast.getInstance(mContext).setMessage("请选择" + modeList.get(0).getModelname() + "！");
                                                          } else if (chooseIDs < 0) {
                                                              CustomToast.getInstance(mContext).setMessage("请选择" + modeList.get(1).getModelname() + "！");
                                                          } else if (chooseIDss < 0) {
                                                              CustomToast.getInstance(mContext).setMessage("请选择" + modeList.get(2).getModelname() + "！");
                                                          } else if (chooseIDFour < 0) {
                                                              CustomToast.getInstance(mContext).setMessage("请选择" + modeList.get(3).getModelname() + "！");
                                                          } else if (chooseIDFive < 0) {
                                                              CustomToast.getInstance(mContext).setMessage("请选择" + modeList.get(4).getModelname() + "！");
                                                          }
                                                      }
                                                  } else if (modeList.size() == 6) {
                                                      if (chooseID >= 0 && chooseIDs >= 0 && chooseIDss >= 0 && chooseIDFour >= 0 && chooseIDFive >= 0 && chooseIDSix >= 0) {
                                                          popupWindow.dismiss();
                                                          toAddShoppingCar();//加入购物车的接口
                                                          int y = Integer.parseInt(textViewDotCar.getText().toString()) + Integer.parseInt(buyNumber.getText().toString());
                                                          textViewDotCar.setText(String.valueOf(y));
                                                      } else {
                                                          if (chooseID < 0 && chooseIDs < 0 && chooseIDss < 0 && chooseIDFour < 0 && chooseIDFive < 0 && chooseIDSix < 0) {
                                                              CustomToast.getInstance(mContext).setMessage("请选择规格！");
                                                          } else if (chooseID < 0) {
                                                              CustomToast.getInstance(mContext).setMessage("请选择" + modeList.get(0).getModelname() + "！");
                                                          } else if (chooseIDs < 0) {
                                                              CustomToast.getInstance(mContext).setMessage("请选择" + modeList.get(1).getModelname() + "！");
                                                          } else if (chooseIDss < 0) {
                                                              CustomToast.getInstance(mContext).setMessage("请选择" + modeList.get(2).getModelname() + "！");
                                                          } else if (chooseIDFour < 0) {
                                                              CustomToast.getInstance(mContext).setMessage("请选择" + modeList.get(3).getModelname() + "！");
                                                          } else if (chooseIDFive < 0) {
                                                              CustomToast.getInstance(mContext).setMessage("请选择" + modeList.get(4).getModelname() + "！");
                                                          } else if (chooseIDSix < 0) {
                                                              CustomToast.getInstance(mContext).setMessage("请选择" + modeList.get(5).getModelname() + "！");
                                                          }
                                                      }
                                                  }
                                              }
                                          }
        );
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
                chooseID = -1;
                chooseIDs = -1;
                chooseIDss = -1;
                chooseIDFour = -1;
                chooseIDFive = -1;
                chooseIDSix = -1;
            }
        });
    }

    /**
     * 规格列表请求
     */
    public void shopDetails() {
        MerIdPostBean merIdPostBean = new MerIdPostBean();
        merIdPostBean.setMerid(merid);
        Http.getModeList(mContext, merIdPostBean, modeLineOne, modeLineTwo, modeLineThree, modeLineFour, modeLineFive, modeLineSix
                , modeList, standardOne, standardTwo, standardThree, standardFour, standardFive, standardSix
                , modeListShowBeanList, viewTexts, viewTextTwo, viewTextThree, viewTextFour, viewTextFive, viewTextSix, goodsViewGroupOne
                , goodsViewGroupTwo, goodsViewGroupThree, goodsViewGroupFour, goodsViewGroupFive, goodsViewGroupSix);
    }

    /**
     * 多规格商品添加购物车
     */
    public void toAddShoppingCar() {//添加到购物车的请求
        ChangeCarCountPostBean changeCarCountPostBean = new ChangeCarCountPostBean();
        changeCarCountPostBean.setStoreid(UserInfo.getInstance().getUser(mContext).getStoreid());
        changeCarCountPostBean.setMerid(merid);
        changeCarCountPostBean.setMerqty(buyNumber.getText().toString());
        Http.addShopCar(mContext, changeCarCountPostBean, modeList, modeListShowBeanList, chooseText, chooseTextTwo, chooseTextThree, chooseTextFour, chooseTextFive, chooseTextSix);
    }

    /**
     * 设置添加屏幕的背景透明度  1,：全透明；0.5：半透明  0~1，取自己想到的透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    /**
     * 获取原厂地商品详情信息
     */
    public void toGetGoodsDetailUp() {//头部请求
        MerIdPostBean merIdPostBean = new MerIdPostBean();
        merIdPostBean.setMerid(merid);
        Http.getGoodsDetail(mContext, merIdPostBean, shopName,
                goodsDetail_monNo, goodsDetail_storeNo,
                textView_sale_price, goodsDetail_info,
                listCarouselShowBean, banner,
                listImage, noHave, anim, imgAdapter
                , evaluateAllNumTv, haveLine
                , circularImage, nicknameTv, contentTv, timeTv, ratingBar, yBeiBiTv, pjLine, pjIconOne, pjIconTwo
                , pjIconThree);
    }

    /**
     * 普通商品添加购物车
     */
    public void toAddShoppingCars() {//添加到购物车的请求
        ChangeCarCountPostBean changeCarCountPostBean = new ChangeCarCountPostBean();
        changeCarCountPostBean.setStoreid(UserInfo.getInstance().getUser(mContext).getStoreid());
        changeCarCountPostBean.setMerid(merid);
        Http.addShopCar(mContext, changeCarCountPostBean);
    }

    /**
     * 购物车商品数量
     */
    public void getShoppingCarCount() {
        StoreIdPostBean storeIdPostBean = new StoreIdPostBean();
        storeIdPostBean.setStoreid(UserInfo.getInstance().getUser(mContext).getStoreid());
        Http.getShoppingCarCount(mContext, storeIdPostBean, textViewDotCar);
    }

    @Override
    public void onResume() {
        super.onResume();
        getShoppingCarCount();
    }

    @Override
    public void onStop() {
        super.onStop();
        MutualApplication.getRequestQueue().cancelAll("addShopCar98");
        MutualApplication.getRequestQueue().cancelAll("addShopCar99");
        MutualApplication.getRequestQueue().cancelAll("getShoppingCarCount100");
        MutualApplication.getRequestQueue().cancelAll("getGoodsDetail101");
        MutualApplication.getRequestQueue().cancelAll("getModeList102");
        MutualApplication.getRequestQueue().cancelAll("discount118");
        MutualApplication.getRequestQueue().cancelAll("customerTelephoneNumber112");
    }


    private CommonPopupWindow morePopupWindow;

    public void showPop(View view) {
        if (morePopupWindow != null && morePopupWindow.isShowing()) return;
        morePopupWindow = new CommonPopupWindow.Builder(mContext)
                .setView(R.layout.pop_goods_details_more)
                .setWidthAndHeight(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setAnimationStyle(R.style.AnimDown)
                .setBackGroundLevel(0.5f)//取值范围0.0f-1.0f 值越小越暗
                .setViewOnclickListener(this)
                .setOutsideTouchable(true)
                .create();
        morePopupWindow.showAsDropDown(view);
    }

    /**
     * 右上角弹框
     *
     * @param view
     * @param layoutResId
     * @throws IllegalAccessException
     */
    private LinearLayout messageLine;//消息
    private LinearLayout customerLine;//客服
    private LinearLayout homeLine;//首页

    @Override
    public void getChildView(View view, int layoutResId) throws IllegalAccessException {
        switch (layoutResId) {
            case R.layout.pop_goods_details_more:
                messageLine = view.findViewById(R.id.pop_goods_details_message_line);
                customerLine = view.findViewById(R.id.pop_goods_details_service_line);
                homeLine = view.findViewById(R.id.pop_goods_details_home_line);
                messageLine.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //消息
                        morePopupWindow.dismiss();
                        startActivity(new Intent(mContext, MyMessageListActivity.class));
                        finish();
                    }
                });
                customerLine.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //客服
                        morePopupWindow.dismiss();
                        Http.customerTelephoneNumber(mContext);
                    }
                });
                homeLine.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        morePopupWindow.dismiss();
                        Intent intent = new Intent(mContext, MainActivity.class);
                        intent.putExtra("id", "100");
                        startActivity(intent);
                        finish();
                    }
                });


                break;
        }
    }

    /**
     * 客服电话
     *
     * @param context
     */
    public static void customerTelephoneNumber(Context context) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.comTel, PossObject.customerTelephoneNumber(), new CustomerTelephoneNumberListener(context), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("customerTelephoneNumber112");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
