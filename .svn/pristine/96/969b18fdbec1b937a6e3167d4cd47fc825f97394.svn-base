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
import com.example.administrator.merchants.adapter.AddIssueActivityPicAdapter;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseActivity;
import com.example.administrator.merchants.common.CommonUtil;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.dialog.EditDialog;
import com.example.administrator.merchants.dialog.MineAvatarDialog;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.post.AddGoodsPossBean;
import com.example.administrator.merchants.http.post.StoreIdPostBean;
import com.example.administrator.merchants.http.show.ImageUriShowBean;
import com.example.administrator.merchants.http.show.PopupMenuShowBean;
import com.example.administrator.merchants.common.DateUtils;
import com.example.administrator.merchants.common.toast.CustomToast;
import com.example.administrator.merchants.common.PicUtils;
import com.example.administrator.merchants.common.views.MyGridView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
/**
 * 作者：韩宇 on 2017/6/23 0023 14:39
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：商家新增商品
 */
public class MerchantAddGoodsActivity extends BaseActivity implements MineAvatarDialog.BackChoose, View.OnClickListener {
    private TextView finish;//完成
    private ProgressDialog progressDialog;//提交对话框
    private TextView merNameTextView;//商品名显示文字
    private LinearLayout merDetailLinearLayout;//商品详情点击布局
    private LinearLayout classifyLinearLayout;//分类
    private TextView merDetailTextView;//商品详情显示文字
    private TextView classifyTextView;//分类显示文字
    private LinearLayout merNameLinearLayout;//商品名布局
    private ImageView imageView;//添加图片按钮
    private String merMenuId;//菜单编码
    private static final int PHOTO_REQUEST_CAMERA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private File tempFile;//拍照存储的文件
    private PopupWindow popupWindow;//分类popupWindow
    private View popupWindowView;//分类popupWindow的布局
    private List<PopupMenuShowBean> list;//分类集合
    private ListView list_menu1;//分类listView
    private View popupWindowLine;//popupWindowLine是分类下面的线用来显示popupWindow
    private View  beiBiShowLine;//beiBiShowLine是贝币下面的线用来显示库存隐藏的
    private MyGridView gridView;//商品图片
    private AddIssueActivityPicAdapter addIssueActivityPicAdapter;//拍照adapter
    private List<ImageUriShowBean> imageUriShowBeanList;//拍照图片集合
    private List<File> toFileList;//上传图片文件集合
    private ToggleButton stockSwitch;//库存开关
    private EditText price;//价格
    private EditText number;//数量
    private EditText beiBi;//返贝币数

    //监听滑动
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
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
        setTitles("新增商品");
        finish = (TextView) findViewById(R.id.head_button);
        finish.setText("完成");
        stockSwitch = (ToggleButton) findViewById(R.id.activity_merchant_add_goods_stockSwitch);
        gridView = (MyGridView) findViewById(R.id.activity_merchant_add_goods_gridView);
        merNameTextView = (TextView) findViewById(R.id.activity_merchant_add_goods_merName);
        merDetailLinearLayout = (LinearLayout) findViewById(R.id.activity_merchant_add_goods_detail);
        classifyLinearLayout = (LinearLayout) findViewById(R.id.activity_merchant_add_goods_classify);
        merDetailTextView = (TextView) findViewById(R.id.activity_merchant_add_goods_detail_text);
        classifyTextView = (TextView) findViewById(R.id.activity_merchant_add_goods_classify_text);
        merNameLinearLayout = (LinearLayout) findViewById(R.id.activity_merchant_add_goods_mer_name);
        imageView = (ImageView) findViewById(R.id.activity_merchant_add_goods_top);
        popupWindowView = LayoutInflater.from(this).inflate(R.layout.popup_add_goods_menu_list, null);
        popupWindowLine = findViewById(R.id.activity_merchant_add_goods_popupWindowLine);
        price = (EditText) findViewById(R.id.activity_merchant_add_goods_price);
        number = (EditText) findViewById(R.id.activity_merchant_add_goods_Stock);
        beiBi = (EditText) findViewById(R.id.activity_merchant_add_goods_beiBi);
        beiBiShowLine = findViewById(R.id.activity_merchant_add_goods_beiBiShowLine);
        DateUtils.setPricePoint(price);
        DateUtils.setPricePoint(beiBi);
        if (stockSwitch.isChecked()) {
            beiBiShowLine.setVisibility(View.VISIBLE);
            number.setVisibility(View.VISIBLE);
        } else {
            number.setVisibility(View.GONE);
            beiBiShowLine.setVisibility(View.GONE);
        }
        imageUriShowBeanList = new ArrayList<>();
        CommonUtil.clear();
        toFileList = new ArrayList<>();
        ImageUriShowBean uuu = new ImageUriShowBean();
        uuu.setType(1);
        imageUriShowBeanList.add(uuu);
        addIssueActivityPicAdapter = new AddIssueActivityPicAdapter(MerchantAddGoodsActivity.this, imageUriShowBeanList);
        gridView.setAdapter(addIssueActivityPicAdapter);
       click();
    }

    /**
     * 监听
     */
    private void click(){
        merDetailLinearLayout.setOnClickListener(this);
        classifyLinearLayout.setOnClickListener(this);
        merNameLinearLayout.setOnClickListener(this);
        finish.setOnClickListener(this);
        imageView.setOnClickListener(this);
        stockSwitch.setOnClickListener(this);
    }
    /***
     * 拍照回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
                CustomToast.getInstance(MerchantAddGoodsActivity.this).setMessage("未找到存储卡，无法存储照片！");
            }
        } else if (requestCode == 4 && resultCode == 9) {
            Uri uri = Uri.parse(data.getStringExtra("photo"));
            getBitmapFromUri(uri);
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onStop() {
        super.onStop();
        MutualApplication.getRequestQueue().cancelAll("classManagement42");
        MutualApplication.getRequestQueue().cancelAll("addGoods43");
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_merchant_add_goods_stockSwitch:
                if (stockSwitch.isChecked()) {
                    beiBiShowLine.setVisibility(View.VISIBLE);
                    number.setVisibility(View.VISIBLE);
                } else {
                    number.setVisibility(View.GONE);
                    beiBiShowLine.setVisibility(View.GONE);
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
                EditDialog editDialog = new EditDialog(MerchantAddGoodsActivity.this, merNameTextView);//商品名称
                editDialog.show();
                break;
            case R.id.activity_merchant_add_goods_detail:
                EditDialog editDialog1 = new EditDialog(MerchantAddGoodsActivity.this, merDetailTextView);//商品详情
                editDialog1.show();
                break;
            case R.id.activity_merchant_add_goods_classify:
                initPop();//分类
                popupWindow.showAsDropDown(popupWindowLine);
                break;
            case R.id.head_button:
                if ("".equals(merNameTextView.getText().toString())) {
                    CustomToast.getInstance(MerchantAddGoodsActivity.this).setMessage("商品名称不能为空！");
                } else if ("".equals(classifyTextView.getText().toString())) {
                    CustomToast.getInstance(MerchantAddGoodsActivity.this).setMessage("分类不能为空！");
                }else  if (imageUriShowBeanList.size() == 1){
                    CustomToast.getInstance(MerchantAddGoodsActivity.this).setMessage("商品图片不能为空！");
                } else if ("".equals(price.getText().toString())) {
                    CustomToast.getInstance(MerchantAddGoodsActivity.this).setMessage("商品价格不能为空！");
                }else if ("".equals(beiBi.getText().toString())) {
                    CustomToast.getInstance(MerchantAddGoodsActivity.this).setMessage("返贝币不能为空！");
                }else  if (stockSwitch.isChecked()){
                   if ("".equals(number.getText().toString())) {
                        CustomToast.getInstance(MerchantAddGoodsActivity.this).setMessage("库存不能为空！");
                    } else {
                       progressDialog = ProgressDialog
                           .show(MerchantAddGoodsActivity.this,
                                   "",
                                   "上传中请稍后...",
                                   true);
                        addShop();

                    }
                }else {
                    progressDialog = ProgressDialog
                        .show(MerchantAddGoodsActivity.this,
                                "",
                                "上传中请稍后...",
                                true);
                        addShop();

                    }
                break;
        }
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
        // 判断存储卡是否可以用，可用进行存储
        if (hasSdcard()) {
            // 从文件中创建uri
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

    /***
     * url转bitmap
     *
     * @param uri
     * @return
     */
    private void getBitmapFromUri(Uri uri) {
        String path = getRealFilePath(uri);
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMdd_hhmmss");
        final String date = sDateFormat.format(new java.util.Date());
        PicUtils.compress(path, getFilesDir() + date + "_" + ".png");
        File file = new File(getFilesDir() + date + "_" + ".png");
        while (!file.exists()) ;
        toFileList.add(file);
        if (CommonUtil.add_update) {
            imageUriShowBeanList.get(CommonUtil.add_posstion).setFile(file);
            imageUriShowBeanList.get(CommonUtil.add_posstion).setUri(uri);
            imageUriShowBeanList.get(CommonUtil.add_posstion).setType(0);
        } else {
            if (imageUriShowBeanList.size() == 6) {
                ImageUriShowBean imageUriShowBean = new ImageUriShowBean();
                imageUriShowBean.setFile(file);
                imageUriShowBean.setType(0);
                imageUriShowBean.setUri(uri);
                imageUriShowBeanList.add(imageUriShowBeanList.size() - 1, imageUriShowBean);
                imageUriShowBeanList.remove(imageUriShowBeanList.size() - 1);
            } else {
                ImageUriShowBean imageUriShowBean = new ImageUriShowBean();
                imageUriShowBean.setType(0);
                imageUriShowBean.setFile(file);
                imageUriShowBean.setUri(uri);
                imageUriShowBeanList.add(imageUriShowBeanList.size() - 1, imageUriShowBean);
            }
        }
        addIssueActivityPicAdapter.notifyDataSetChanged();
        CommonUtil.add_update = false;
    }

    private void initPop() { //初始化popup
        popupWindow = new PopupWindow(popupWindowView,
                ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        list = new ArrayList<>();
        list_menu1 = (ListView) popupWindowView
                .findViewById(R.id.list_menu1);
        getClassifyList();
        list_menu1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (list.size() != 0) {
                    classifyTextView.setText(list.get(position).getMenuname());
                    merMenuId = list.get(position).getMenuid();
                    popupWindow.dismiss();
                }


            }
        });
    }

    /**
     * 分类接口
     */
    public void getClassifyList() {//拿分类
        StoreIdPostBean storeIdPostBean=new StoreIdPostBean();
        storeIdPostBean.setStoreid(UserInfo.getInstance().getUser(MerchantAddGoodsActivity.this).getStoreid());
        Http.classManagement(MerchantAddGoodsActivity.this,storeIdPostBean,list_menu1,list);
    }
    /**
     * 新增商品信息接口
     */
    public void addShop() {    //给服务器
        AddGoodsPossBean addGoodsPossBean=new AddGoodsPossBean();
        addGoodsPossBean.setWstoreid(UserInfo.getInstance().getUser(MerchantAddGoodsActivity.this).getStoreid());
        addGoodsPossBean.setWmername(merNameTextView.getText().toString());
        addGoodsPossBean.setWmerdescr(merDetailTextView.getText().toString());
        addGoodsPossBean.setWmermenuid(merMenuId);
        addGoodsPossBean.setWisstorenum(stockSwitch.isChecked());
        addGoodsPossBean.setSpecsname("默认");
        addGoodsPossBean.setSaleprice(price.getText().toString());
        addGoodsPossBean.setStorenum(number.getText().toString());
        addGoodsPossBean.setRetbeibiamt(beiBi.getText().toString());
        List<File> fileList = new ArrayList<>();
        for (int i = 0; i < imageUriShowBeanList.size(); i++) {
            if (imageUriShowBeanList.get(i).getType() == 0) {
                fileList.add(imageUriShowBeanList.get(i).getFile());
            }
        }
       Http.addGoods(MerchantAddGoodsActivity.this,addGoodsPossBean,progressDialog,toFileList,fileList);
    }


}

