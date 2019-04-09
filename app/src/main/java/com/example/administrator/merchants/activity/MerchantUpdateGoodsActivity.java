package com.example.administrator.merchants.activity;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.adapter.UpdateImageAdapter;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseActivity;
import com.example.administrator.merchants.common.CommonUtil;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.dialog.EditDialog;
import com.example.administrator.merchants.dialog.MineAvatarDialog;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.post.AddGoodsPossBean;
import com.example.administrator.merchants.http.post.MerIdPostBean;
import com.example.administrator.merchants.http.post.StoreIdPostBean;
import com.example.administrator.merchants.http.show.BitmapShowBean;
import com.example.administrator.merchants.http.show.PopupMenuShowBean;
import com.example.administrator.merchants.common.DateUtils;
import com.example.administrator.merchants.common.toast.CustomToast;
import com.example.administrator.merchants.common.PicUtils;
import com.example.administrator.merchants.common.views.MyGridView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/6/23 0023 14:39
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：商品信息修改
 */
public class MerchantUpdateGoodsActivity extends BaseActivity implements MineAvatarDialog.BackChoose, View.OnClickListener {
    private TextView finish;
    private ProgressDialog progressDialog;
    private TextView commodityNameShow;
    private LinearLayout detailLinearLayout;
    private LinearLayout classifyLinearLayout;
    private TextView detailShow;
    private TextView classifyShow;
    private LinearLayout commodityNameLinearLayout;
    private ImageView imageView;//添加商品图片
    public static String menuId;
    private PopupWindow popupWindow;
    private View popupWindowView;
    private List<PopupMenuShowBean> popupMenuShowBeanList;
    private ListView listView;
    private View popupLine;//popupWindow展示的线
    private View beiBiLine;//返贝币下面的线
    private ToggleButton toggleButton;//库存开关
    private UpdateImageAdapter updateImageAdapter;
    private List<BitmapShowBean> bitmapShowBeanList;
    private static final int PHOTO_REQUEST_CAMERA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private File tempFile;
    private List<File> toFileList;
    private MyGridView gridView;
    private ImageLoader imageLoader;
    private DisplayImageOptions options;
    private EditText price, number, beiBi;
    public static String specsId;
    private String merid;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (isHideInput(view, ev)) {
                HideSoftInput(view.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    // 判定是否需要隐藏
    private boolean isHideInput(View v, MotionEvent ev) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (ev.getX() > left && ev.getX() < right && ev.getY() > top
                    && ev.getY() < bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    // 隐藏软键盘
    private void HideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(token,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_add_goods);
        setTitles("修改商品");
        merid=getIntent().getStringExtra("merid");
        options = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .build();
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(MerchantUpdateGoodsActivity.this));
        CommonUtil.deleteFile = "";
        finish = (TextView) findViewById(R.id.head_button);
        finish.setText("完成");
        toggleButton = (ToggleButton) findViewById(R.id.activity_merchant_add_goods_stockSwitch);
        gridView = (MyGridView) findViewById(R.id.activity_merchant_add_goods_gridView);
        commodityNameShow = (TextView) findViewById(R.id.activity_merchant_add_goods_merName);
        detailLinearLayout = (LinearLayout) findViewById(R.id.activity_merchant_add_goods_detail);
        classifyLinearLayout = (LinearLayout) findViewById(R.id.activity_merchant_add_goods_classify);
        detailShow = (TextView) findViewById(R.id.activity_merchant_add_goods_detail_text);
        classifyShow = (TextView) findViewById(R.id.activity_merchant_add_goods_classify_text);
        commodityNameLinearLayout = (LinearLayout) findViewById(R.id.activity_merchant_add_goods_mer_name);
        imageView = (ImageView) findViewById(R.id.activity_merchant_add_goods_top);
        popupWindowView = LayoutInflater.from(this).inflate(R.layout.popup_add_goods_menu_list, null);
        popupLine = findViewById(R.id.activity_merchant_add_goods_popupWindowLine);
        price = (EditText) findViewById(R.id.activity_merchant_add_goods_price);
        number = (EditText) findViewById(R.id.activity_merchant_add_goods_Stock);
        beiBi = (EditText) findViewById(R.id.activity_merchant_add_goods_beiBi);
        beiBiLine = findViewById(R.id.activity_merchant_add_goods_beiBiShowLine);
        DateUtils.setPricePoint(price);
        DateUtils.setPricePoint(beiBi);
        bitmapShowBeanList = new ArrayList<>();
        CommonUtil.clear();
        toFileList = new ArrayList<>();
        updateImageAdapter = new UpdateImageAdapter(MerchantUpdateGoodsActivity.this, bitmapShowBeanList);
        gridView.setAdapter(updateImageAdapter);
        updateImageAdapter.notifyDataSetChanged();
        getMer();//商品详情接口
        click();
    }

    /**
     * 点击事件
     */
    public void click() {
        detailLinearLayout.setOnClickListener(this);
        classifyLinearLayout.setOnClickListener(this);
        commodityNameLinearLayout.setOnClickListener(this);
        finish.setOnClickListener(this);
        imageView.setOnClickListener(this);
        toggleButton.setOnClickListener(this);
    }

    /**
     * 从相册获取
     */
    public void gallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }

    /**
     * 从相机获取
     */
    public void camera() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        final String date = sDateFormat.format(new java.util.Date());
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (hasSdcard()) {
            tempFile = new File(Environment.getExternalStorageDirectory(),
                    date + ".jpg");
            Uri uri = Uri.fromFile(tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        startActivityForResult(intent, PHOTO_REQUEST_CAMERA);
    }

    /**
     * 判断sdcard是否被挂载
     */
    private boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void isChoose(boolean choose) {
        if (choose) {
            gallery();
        } else {
            camera();
        }
    }

    /***
     * url转bitmap
     *
     * @param uri
     * @return
     */
    private void getBitmapFromUri(Uri uri) {
        if (CommonUtil.update_update) {
            PicUtils.compress(getRealFilePath(uri), getFilesDir() + "/" + bitmapShowBeanList.get(CommonUtil.update_posstion).getImgid() + ".png");
            File file = new File(getFilesDir() + "/" + bitmapShowBeanList.get(CommonUtil.update_posstion).getImgid() + ".png");
            while (!file.exists()) ;
            bitmapShowBeanList.get(CommonUtil.update_posstion).setFilelow(file);
            bitmapShowBeanList.get(CommonUtil.update_posstion).setUri(uri);
            bitmapShowBeanList.get(CommonUtil.update_posstion).setType(2);
            toFileList.add(file);
        } else {
            SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMdd_hhmmss");
            final String date = sDateFormat.format(new java.util.Date());
            PicUtils.compress(getRealFilePath(uri), getFilesDir() + date + "_" + ".png");
            File file = new File(getFilesDir() + date + "_" + ".png");
            while (!file.exists()) ;
            BitmapShowBean bitmapShowBean = new BitmapShowBean();
            bitmapShowBean.setFilelow(file);
            bitmapShowBean.setUri(uri);
            bitmapShowBean.setType(2);
            if (bitmapShowBeanList.size() == 6) {
                bitmapShowBeanList.remove(bitmapShowBeanList.size() - 1);
                bitmapShowBeanList.add(bitmapShowBean);
            } else {
                bitmapShowBeanList.add((bitmapShowBeanList.size() - 1), bitmapShowBean);
            }
            toFileList.add(file);
        }
        updateImageAdapter.notifyDataSetChanged();
        CommonUtil.update_update = false;
    }

    /**
     * 由照片uri获取绝对路径
     *
     * @param uri
     * @return path
     */
    public String getRealFilePath(final Uri uri) {
        if (null == uri)
            return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = getContentResolver().query(uri,
                    new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                getBitmapFromUri(uri);
            }
        } else if (requestCode == PHOTO_REQUEST_CAMERA) {
            // 从相机返回的数据
            if (hasSdcard()) {
                if (tempFile.exists()) {
                    Uri uri = Uri.fromFile(tempFile);
                    getBitmapFromUri(uri);
                    CommonUtil.fileList.add(tempFile);
                }
            } else {
                CustomToast.getInstance(MerchantUpdateGoodsActivity.this).setMessage("未找到存储卡，无法存储照片！");
            }
        } else if (requestCode == 4 && resultCode == 9) {
            Uri uri = Uri.parse(data.getStringExtra("photo"));
            getBitmapFromUri(uri);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        MutualApplication.getRequestQueue().cancelAll("classManagement42");
        MutualApplication.getRequestQueue().cancelAll("upGoodsDetail48");
        MutualApplication.getRequestQueue().cancelAll("updateGoods49");
    }

    /**
     * 商品详情
     */
    public void getMer() {
        MerIdPostBean merIdPostBean = new MerIdPostBean();
        merIdPostBean.setMerid(merid);
        Http.upGoodsDetail(MerchantUpdateGoodsActivity.this, merIdPostBean, imageLoader, options, bitmapShowBeanList, imageView, gridView, updateImageAdapter, toggleButton, beiBiLine, number, commodityNameShow, detailShow, classifyShow, price, beiBi);
    }

    /**
     * 商品修改接口
     */
    private void updateShop() {  //给服务器
        AddGoodsPossBean addGoodsPossBean = new AddGoodsPossBean();
        addGoodsPossBean.setWstoreid(UserInfo.getInstance().getUser(MerchantUpdateGoodsActivity.this).getStoreid());
        addGoodsPossBean.setWmerid(merid);
        addGoodsPossBean.setWmername(commodityNameShow.getText().toString());
        addGoodsPossBean.setWmerdescr(detailShow.getText().toString());
        addGoodsPossBean.setWmermenuid(menuId);
        addGoodsPossBean.setWisstorenum(toggleButton.isChecked());
        addGoodsPossBean.setSpecsname("默认");
        addGoodsPossBean.setSpecsid(specsId);
        addGoodsPossBean.setSaleprice(price.getText().toString());
        addGoodsPossBean.setStorenum(number.getText().toString());
        addGoodsPossBean.setRetbeibiamt(beiBi.getText().toString());
        List<File> fileList = new ArrayList<>();
        for (int i = 0; i < bitmapShowBeanList.size(); i++) {
            if (bitmapShowBeanList.get(i).getType() == 2) {
                fileList.add(bitmapShowBeanList.get(i).getFilelow());
            }
        }
        Http.updateGoods(MerchantUpdateGoodsActivity.this, addGoodsPossBean, progressDialog, toFileList, fileList);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_merchant_add_goods_stockSwitch:
                if (toggleButton.isChecked()) {
                    beiBiLine.setVisibility(View.VISIBLE);
                    number.setVisibility(View.VISIBLE);
                } else {
                    number.setVisibility(View.GONE);
                    beiBiLine.setVisibility(View.GONE);
                }
                break;
            case R.id.activity_merchant_add_goods_top:
                MineAvatarDialog dialog = new MineAvatarDialog(this, R.style.Dialog);
                Window window = dialog.getWindow();
                window.setGravity(Gravity.CENTER);
                dialog.show();
                dialog.getChoose(this);
                break;
            case R.id.activity_merchant_add_goods_mer_name:
                EditDialog editDialog = new EditDialog(MerchantUpdateGoodsActivity.this, commodityNameShow);//商品名称
                editDialog.show();
                break;
            case R.id.activity_merchant_add_goods_detail:
                EditDialog editDialog1 = new EditDialog(MerchantUpdateGoodsActivity.this, detailShow);//商品详情
                editDialog1.show();
                break;
            case R.id.activity_merchant_add_goods_classify:
                initPop();//分类
                popupWindow.showAsDropDown(popupLine);
                break;
            case R.id.head_button:
                if ("".equals(commodityNameShow.getText().toString())) {
                    CustomToast.getInstance(MerchantUpdateGoodsActivity.this).setMessage("商品名称不能为空！");
                } else if ("".equals(classifyShow.getText().toString())) {
                    CustomToast.getInstance(MerchantUpdateGoodsActivity.this).setMessage("分类不能为空！");
                } else if ("".equals(price.getText().toString())) {
                    CustomToast.getInstance(MerchantUpdateGoodsActivity.this).setMessage("商品价格不能为空！");
                } else if ("".equals(beiBi.getText().toString())) {
                    CustomToast.getInstance(MerchantUpdateGoodsActivity.this).setMessage("返贝币不能为空！");
                } else if (toggleButton.isChecked()) {
                    if ("".equals(number.getText().toString())) {
                        CustomToast.getInstance(MerchantUpdateGoodsActivity.this).setMessage("库存不能为空！");
                    } else {
                        progressDialog = ProgressDialog
                                .show(MerchantUpdateGoodsActivity.this,
                                        "",
                                        "修改中请稍后...",
                                        true);
                        updateShop();
                    }
                } else {
                    progressDialog = ProgressDialog
                            .show(MerchantUpdateGoodsActivity.this,
                                    "",
                                    "修改中请稍后...",
                                    true);
                    updateShop();
                }
                break;
        }
    }

    /**
     * 分类popup
     */
    private void initPop() { //初始化popup
        popupWindow = new PopupWindow(popupWindowView,
                ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupMenuShowBeanList = new ArrayList<>();
        listView = (ListView) popupWindowView
                .findViewById(R.id.list_menu1);
        getClassifyList();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (popupMenuShowBeanList.size() != 0) {
                    classifyShow.setText(popupMenuShowBeanList.get(position).getMenuname());
                    menuId = popupMenuShowBeanList.get(position).getMenuid();
                    popupWindow.dismiss();
                }
            }
        });
    }

    /**
     * 商品分类
     */
    public void getClassifyList() {//拿分类
        StoreIdPostBean storeIdPostBean = new StoreIdPostBean();
        storeIdPostBean.setStoreid(UserInfo.getInstance().getUser(MerchantUpdateGoodsActivity.this).getStoreid());
        Http.classManagement(MerchantUpdateGoodsActivity.this, storeIdPostBean, listView, popupMenuShowBeanList);
    }
}

