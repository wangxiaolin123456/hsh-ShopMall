package com.example.administrator.merchants.adapter;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.merchants.R;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.http.show.TempShoppingCarShowBean;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.post.ChangeCarCountPostBean;
import com.example.administrator.merchants.http.show.ShoppingCarShowBean;

import java.text.DecimalFormat;
import java.util.List;

/**
 * 作者：韩宇 on 2017/6/23 0023 14:39
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：购物车适配器
 */
public class ShoppingCarAdapter extends BaseAdapter {
    private Context context;
    private List<ShoppingCarShowBean> list;
    private TextView textViewTotal;//总价格
    private CheckBox checkAll;//全选
    public List<TempShoppingCarShowBean> tempShoppingCarShowBeans;

    public ShoppingCarAdapter(Context context, List<ShoppingCarShowBean> list, CheckBox checkAll, TextView textViewTotal, List<TempShoppingCarShowBean> tempShoppingCarShowBeans) {
        this.list = list;
        this.context = context;
        this.checkAll = checkAll;
        this.textViewTotal = textViewTotal;
        this.tempShoppingCarShowBeans = tempShoppingCarShowBeans;
        initTempShoppingCarBeans(false);
    }

    void initTempShoppingCarBeans(boolean e) { //toDo  初始化临时集合,String s
        for (int i = 0; i < tempShoppingCarShowBeans.size(); i++) {
            TempShoppingCarShowBean tempShoppingCarShowBean = tempShoppingCarShowBeans.get(i);
            tempShoppingCarShowBean.choosed = false;
        }
    }

    void initCheck(boolean f) {
        for (ShoppingCarShowBean s : list) {
            s.setJudge(f);
        }
    }

    void initChoosed(boolean b) {//遍历赋值临时集合的checkbox
        for (TempShoppingCarShowBean t : tempShoppingCarShowBeans) {
            t.choosed = b;
        }
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        HolderTest holderTest = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_shopping_car, null);
            holderTest = new HolderTest();
            holderTest.ImageViewEightCircleOne = (ImageView) convertView.findViewById(R.id.iv_yuan);
            holderTest.TextViewEightCircleOne = (TextView) convertView.findViewById(R.id.guess_title);
            holderTest.TextViewEightCircleTwo = (TextView) convertView.findViewById(R.id.tv_yuan_content);
            holderTest.TextViewEightCircleThree = (TextView) convertView.findViewById(R.id.danjia_shopping); //toDo 单价
            holderTest.TextViewEightCircleFour = (TextView) convertView.findViewById(R.id.tv_rating_num);
            holderTest.TextViewEightCircleFive = (TextView) convertView.findViewById(R.id.month_count);
            holderTest.TextViewEightCircleSix = (TextView) convertView.findViewById(R.id.store_count);
            holderTest.TextViewNine = (TextView) convertView.findViewById(R.id.middle_num);
            holderTest.RatingBar = (RatingBar) convertView.findViewById(R.id.yuan_rating);
            holderTest.ImageViewEightCircleTwo = (ImageView) convertView.findViewById(R.id.img_jian);
            holderTest.ImageViewEightCircleThree = (ImageView) convertView.findViewById(R.id.img_jia);
            holderTest.CheckBox = (CheckBox) convertView.findViewById(R.id.item_shopping_cart_chose);
            holderTest.view = convertView.findViewById(R.id.vvvv);
            convertView.setTag(holderTest);
        } else {
            holderTest = (HolderTest) convertView.getTag();
        }
        Glide.with(context.getApplicationContext()).load(list.get(position).getImgsfile()).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.image_loading).into(holderTest.ImageViewEightCircleOne);
        holderTest.TextViewEightCircleOne.setText(list.get(position).getMername());
        holderTest.TextViewEightCircleTwo.setText(list.get(position).getModeldescr());
        holderTest.TextViewEightCircleThree.setText(String.valueOf(list.get(position).getSaleprice()));
        holderTest.TextViewEightCircleFour.setText(String.valueOf(list.get(position).getScoreavg()));
        holderTest.RatingBar.setRating(Float.valueOf(String.valueOf(list.get(position).getScoreavg())));
        holderTest.TextViewEightCircleFive.setText(Integer.parseInt(new DecimalFormat("0").format(list.get(position).getMonthsalenum())) + "");
        holderTest.TextViewEightCircleSix.setText(Integer.parseInt(new DecimalFormat("0").format(list.get(position).getStorenum())) + "");
        holderTest.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("22view", "a");
            }
        });
        if (Integer.parseInt(new DecimalFormat("0").format(list.get(position).getStorenum())) <= 0) {
            holderTest.TextViewEightCircleSix.setText("0");
        } else {
            holderTest.TextViewEightCircleSix.setText(Integer.parseInt(new DecimalFormat("0").format(list.get(position).getStorenum())) + "");
        }
        holderTest.ImageViewEightCircleTwo.
                setOnClickListener(new View.OnClickListener() {// 减法
                                       @Override
                                       public void onClick(View v) {
                                           int x = list.get(position).getMerqty();
                                           if (x > 1) {
                                               changeCarCountToServer(position, "sub1");//大于零才让服务器减一个
                                               x--;
                                           } else {
                                               Toast.makeText(context, "温馨提示：不能继续减少商品，如有需要，请长按删除该商品！", Toast.LENGTH_LONG).show();
                                           }
                                           list.get(position).setMerqty(x);
                                           tempShoppingCarShowBeans.get(position).count = String.valueOf(x);//toDO 这里同时给临时数量改变
                                           notifyDataSetChanged();
                                           if (tempShoppingCarShowBeans.get(position).choosed == true) {//如果按钮被选中了  总价钱需要再算
                                               textViewTotal.setText(String.valueOf(getTotalPrice()));
                                           }
                                       }
                                   }

                );
        holderTest.ImageViewEightCircleThree.
                setOnClickListener(new View.OnClickListener() {// 加法
                                       @Override
                                       public void onClick(View v) {
                                           changeCarCountToServer(position, "add1");//让服务器加一个
                                           int y = list.get(position).getMerqty();
                                           y++;
                                           list.get(position).setMerqty(y);
                                           tempShoppingCarShowBeans.get(position).count = String.valueOf(y);//toDO 这里同时给临时数量改变
                                           notifyDataSetChanged();
                                           if (tempShoppingCarShowBeans.get(position).choosed == true) {//如果被选中了  总价钱需要再算
                                               textViewTotal.setText(String.valueOf(getTotalPrice()));
                                           }
                                       }
                                   }

                );
        checkAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                @Override
                                                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                    if (isChecked) {
                                                        initChoosed(true);
                                                        initCheck(true);
                                                    } else {
                                                        initChoosed(false);
                                                        initCheck(false);
                                                    }
                                                    ShoppingCarAdapter.this.notifyDataSetChanged();
                                                    textViewTotal.setText(getTotalPrice());
                                                }
                                            }

        );
        //输入数字
        final HolderTest finalHolderTest = holderTest;
        holderTest.TextViewNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View popupWindowView = LayoutInflater.from(context).inflate(R.layout.dialog_edit, null);
                final PopupWindow popupWindow = new PopupWindow(popupWindowView,
                        ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setFocusable(true);
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                popupWindow.showAtLocation(finalHolderTest.TextViewNine, Gravity.CENTER, 0, 0);
                final EditText editText = (EditText) popupWindowView.findViewById(R.id.dialog_view_edit);
                TextView cancle = (TextView) popupWindowView.findViewById(R.id.dialog_view_cancel);
                TextView sure = (TextView) popupWindowView.findViewById(R.id.dialog_view_sure);
                sure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int x = Integer.parseInt(editText.getText().toString());
                        if (x > 0) {
                            changeCarCountToServer(position, editText.getText().toString(), popupWindow, x);//大于零才让服务器减一个
                        } else {
                            Toast.makeText(context, "温馨提示：商品数量不能为0，如有需要，请长按删除该商品！", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
            }
        });
        holderTest.CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                           @Override
                                                           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                               if (isChecked == false) {    //当不选它的时候  但是全选是选中的
                                                                   if (checkAll.isChecked()) {
                                                                       checkAll.setChecked(false);
                                                                       initChoosed(true);
                                                                       initCheck(true);
                                                                       tempShoppingCarShowBeans.get(position).choosed = true;
                                                                       list.get(position).setJudge(true);
                                                                   }
                                                                   tempShoppingCarShowBeans.get(position).choosed = false;
                                                                   list.get(position).setJudge(false);
                                                               } else {
                                                                   tempShoppingCarShowBeans.get(position).choosed = true;
                                                                   list.get(position).setJudge(true);
                                                                   int tol = 0;
                                                                   if (checkAll.isChecked() == false) {
                                                                       for (ShoppingCarShowBean s : list) {
                                                                           if (s.isJudge()) {
                                                                               tol = tol + 0;
                                                                           } else {
                                                                               tol = tol + 1;
                                                                           }
                                                                       }
                                                                       if (tol == 0) {
                                                                           checkAll.setChecked(true);
                                                                       }
                                                                   }
                                                               }
                                                               notifyDataSetChanged();
                                                               textViewTotal.setText(getTotalPrice());
                                                           }
                                                       }
        );
        holderTest.CheckBox.setChecked(tempShoppingCarShowBeans.get(position).choosed);//必须放监听之后
        holderTest.TextViewNine.setText(tempShoppingCarShowBeans.get(position).count);//必须放监听之后
        return convertView;
    }

    /**
     * 加一减一删除
     *
     * @param position
     * @param str
     */
    public void changeCarCountToServer(int position, String str) {
        ChangeCarCountPostBean changeCarCountPostBean = new ChangeCarCountPostBean();
        changeCarCountPostBean.setStoreid(UserInfo.getInstance().getUser(context).getStoreid());
        changeCarCountPostBean.setMerid(list.get(position).getMerid());
        changeCarCountPostBean.setMerqty(str);
        changeCarCountPostBean.setModelids(list.get(position).getModelids());
        changeCarCountPostBean.setModeldescr(list.get(position).getModeldescr());
        Http.changeCarCountToServer(context, changeCarCountPostBean);
    }

    /**
     * 随意修改数值
     *
     * @param position
     * @param str
     * @param popupWindow
     * @param x
     */
    public void changeCarCountToServer(final int position, String str, final PopupWindow popupWindow, final int x) {
        ChangeCarCountPostBean changeCarCountPostBean = new ChangeCarCountPostBean();
        changeCarCountPostBean.setStoreid(UserInfo.getInstance().getUser(context).getStoreid());
        changeCarCountPostBean.setMerid(list.get(position).getMerid());
        changeCarCountPostBean.setMerqty(str);
        changeCarCountPostBean.setModelids(list.get(position).getModelids());
        changeCarCountPostBean.setModeldescr(list.get(position).getModeldescr());
        Http.changeCarCountToServer(context, changeCarCountPostBean, x, list, position, tempShoppingCarShowBeans, this, popupWindow, textViewTotal);
    }

    /**
     * 获得购物车被选中的累积总价钱
     *
     * @return
     */
    public String getTotalPrice() {
        double total = 0.0;
        DecimalFormat df = new DecimalFormat("0.00");
        String totalPrice = "0.00";
        for (int i = 0; i < list.size(); i++) {
            if (tempShoppingCarShowBeans.get(i).choosed == true) {
                total = total + (list.get(i).getMerqty()) * (list.get(i).getSaleprice());
                totalPrice = df.format(total);
            }
        }
        MutualApplication.totalConfirm = "0.00";
        MutualApplication.totalConfirm = totalPrice;
        return totalPrice;
    }

    class HolderTest {
        public ImageView ImageViewEightCircleOne;
        public TextView TextViewEightCircleOne;
        public TextView TextViewEightCircleTwo;
        public TextView TextViewEightCircleThree;
        public TextView TextViewEightCircleFour;
        public TextView TextViewEightCircleFive;
        public TextView TextViewEightCircleSix;
        public TextView TextViewNine;
        public RatingBar RatingBar;
        public ImageView ImageViewEightCircleTwo, ImageViewEightCircleThree;
        public CheckBox CheckBox;
        public View view;
    }
}
