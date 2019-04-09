package com.example.administrator.merchants.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.adapter.AddressAdapter;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseActivity;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.post.AddressPossBean;
import com.example.administrator.merchants.http.show.PopupMenuShowBean;
import com.example.administrator.merchants.common.DateUtils;
import com.example.administrator.merchants.common.toast.CustomToast;
import com.example.administrator.merchants.views.v.popupwindow.CommonPopupWindow;
import com.example.administrator.merchants.views.v.popupwindow.ViewColor;
import com.smarttop.library.bean.AdressBean;
import com.smarttop.library.bean.City;
import com.smarttop.library.bean.County;
import com.smarttop.library.bean.Province;
import com.smarttop.library.bean.Street;
import com.smarttop.library.db.manager.AddressDictManager;
import com.smarttop.library.utils.LogUtil;
import com.smarttop.library.widget.AddressSelector;
import com.smarttop.library.widget.BottomDialog;
import com.smarttop.library.widget.OnAddressSelectedListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：添加和修改地址
 */
public class AddressActivity extends BaseActivity implements CommonPopupWindow.ViewInterface,
        OnAddressSelectedListener, AddressSelector.OnDialogCloseListener, AddressSelector.onSelectorAreaPositionListener {
    private EditText addressName;//地址使用者姓名
    private EditText addressTel;//地址使用者电话
    private EditText addressStr;//详细地址
    private RadioButton man;//男
    private RadioButton woman;//女
    //    private TextView city;//城市
//    private TextView area;//区
//    private PopupWindow cityPopupWindow;//城市选择
//    private PopupWindow areaPopupWindow;//地区选择
//    private View popupWindowView;//popupWindow布局
    private AddressAdapter addressAdapter;
    private List<PopupMenuShowBean> cityList;//城市集合
    private List<PopupMenuShowBean> areaList;//地区集合
    private String addressId;//地址编码
    @BindView(R.id.activity_update_address_tv)
    TextView addressTv;//选择的地址
    private CommonPopupWindow popupWindow;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_address);
        ButterKnife.bind(this);
        //地址链接
        addressName = (EditText) findViewById(R.id.activity_update_address_name);
        man = (RadioButton) findViewById(R.id.activity_update_address_sex_man);
        woman = (RadioButton) findViewById(R.id.activity_update_address_sex_woman);
        addressTel = (EditText) findViewById(R.id.activity_update_address_tel);
        addressStr = (EditText) findViewById(R.id.activity_update_address_str);
//        city = (TextView) findViewById(R.id.activity_update_address_city);
//        area = (TextView) findViewById(R.id.activity_update_address_area);
//        popupWindowView = LayoutInflater.from(this).inflate(R.layout.popup_address_list, null);
        final Intent intent = getIntent();
        type = intent.getStringExtra("type");
        if ("add".equals(type)) {
            setTitles("添加收货地址");
            addressTv.setText("点击选择地址");
        } else {
            setTitles("修改收货地址");
            //传递取值
            addressId = intent.getStringExtra("updateAddressId");
            final String name = intent.getStringExtra("updateName");
            String sex = intent.getStringExtra("updateSex");
            final String tel = intent.getStringExtra("updateTel");
            final String address = intent.getStringExtra("updateAddress");
            String str = intent.getStringExtra("updateStr");
            //赋值
            addressName.setText(name);
            addressTel.setText(tel);
            addressStr.setText(str);
            String[] a = address.split("市");
//            city.setText(a[0]);
//            area.setText(a[1].substring(0, a[1].length() - 1));
            addressTv.setText(address);
            //性别赋值判断
            if ("1".equals(sex)) {
                man.setChecked(true);
                woman.setChecked(false);
            } else {
                man.setChecked(false);
                woman.setChecked(true);
            }
        }

//        city.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                cityInitPop();
//                cityPopupWindow.showAsDropDown(city);
//            }
//        });
//        area.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                areaInitPop();
//                areaPopupWindow.showAsDropDown(area);
//            }
//        });
    }

    @OnClick({R.id.ll_address_choose, R.id.activity_update_address_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_address_choose:
                //选择地址
                showPop();
                break;
            case R.id.activity_update_address_commit:
                //提交添加监听
                //                else if (man.isChecked() == false && woman.isChecked() == false) {
//                    CustomToast.getInstance(AddressActivity.this).setMessage("请选择性别！");
//                }
                if ("".equals(addressTel.getText().toString()) || "".equals(addressStr.getText().toString())
                        || addressName.getText().toString().equals("") ||
                        addressTv.getText().toString().equals("点击选择地址")) {
                    CustomToast.getInstance(AddressActivity.this).setMessage("基本信息不能为空！");
                } else if (!DateUtils.gephonetzhenze(addressTel.getText().toString())) {
                    CustomToast.getInstance(AddressActivity.this).setMessage("电话格式不对！");
                } else {
                    if ("add".equals(type)) {
                        getAdd();
                    } else {
                        getupdate();
                    }
                }
                break;
        }
    }

    /**
     * 修改地址
     */
    public void getupdate() {
        AddressPossBean addressPossBean = new AddressPossBean();
        addressPossBean.setAddressid(addressId);
        addressPossBean.setReceiver(addressName.getText().toString());
//        if (man.isChecked()) {
            addressPossBean.setGender("");//1
//        } else {
//            addressPossBean.setGender("0");
//        }
        addressPossBean.setContact(addressTel.getText().toString());
//        city.getText().toString() + "市" + area.getText().toString() + "区"
        addressPossBean.setAreaname(addressTv.getText().toString());
        addressPossBean.setStreetaddr(addressStr.getText().toString());
        Http.addressUpdate(AddressActivity.this, addressPossBean);
    }

    /**
     * 添加地址接口
     */
    public void getAdd() {
        AddressPossBean addressPossBean = new AddressPossBean();
        addressPossBean.setStoreid(UserInfo.getInstance().getUser(this).getStoreid());
        addressPossBean.setReceiver(addressName.getText().toString());
        //性别这个地方记得改 现在是必填项
//        if (man.isChecked()) {
//            addressPossBean.setGender("1");
//        } else if (woman.isChecked()) {
//            addressPossBean.setGender("0");
//        } else {
        addressPossBean.setGender("");
//        }
        addressPossBean.setContact(addressTel.getText().toString());
        addressPossBean.setAreaname(addressTv.getText().toString());
        addressPossBean.setStreetaddr(addressStr.getText().toString());
        Http.addressAdd(AddressActivity.this, addressPossBean);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MutualApplication.getRequestQueue().cancelAll("addressAdd2");
        MutualApplication.getRequestQueue().cancelAll("addressUpdate3");
    }

    public void showPop() {
        if (popupWindow != null && popupWindow.isShowing()) return;
        View upView = LayoutInflater.from(mContext).inflate(R.layout.pop_choose_address, null);
        //测量View的宽高
        ViewColor.measureWidthAndHeight(upView);
        int emailH = ViewColor.H(mContext);
        popupWindow = new CommonPopupWindow.Builder(mContext)
                .setView(R.layout.pop_choose_address)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, emailH)
                .setAnimationStyle(R.style.AnimUp)
                .setOutsideTouchable(false)
                .setBackGroundLevel(0.5f)//取值范围0.0f-1.0f 值越小越暗
                .setViewOnclickListener(this)
                .create();
        popupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.BOTTOM, 0, 0);
    }

    private LinearLayout addressLine;
    private AddressDictManager addressDictManager;
    private BottomDialog dialog;
    private String provinceCode;
    private String cityCode;
    private String countyCode;
    private String streetCode;
    private int provincePosition;
    private int cityPosition;
    private int countyPosition;
    private int streetPosition;

    @Override
    public void getChildView(View view, int layoutResId) throws IllegalAccessException {
        switch (layoutResId) {
            case R.layout.pop_choose_address:
                addressLine = view.findViewById(R.id.pop_address_content_line);
                AddressSelector selector = new AddressSelector(mContext);
                //获取地址管理数据库
                addressDictManager = selector.getAddressDictManager();
                selector.setTextSize(14);//设置字体的大小
//        selector.setIndicatorBackgroundColor("#00ff00");
                selector.setIndicatorBackgroundColor(R.color.themeColor);//设置指示器的颜色
//        selector.setBackgroundColor(android.R.color.holo_red_light);//设置字体的背景
                selector.setTextSelectedColor(R.color.text_black);//设置字体获得焦点的颜色
                selector.setTextUnSelectedColor(R.color.themeColor);//设置字体没有获得焦点的颜色
//        //获取数据库管理
                AddressDictManager addressDictManager = selector.getAddressDictManager();
                AdressBean.ChangeRecordsBean changeRecordsBean = new AdressBean.ChangeRecordsBean();
                changeRecordsBean.parentId = 0;
                changeRecordsBean.name = "测试省";
                changeRecordsBean.id = 35;
                addressDictManager.inserddress(changeRecordsBean);//对数据库里增加一个数据
                selector.setOnAddressSelectedListener(new OnAddressSelectedListener() {
                    @Override
                    public void onAddressSelected(Province province, City city, County county, Street street) {
                        if (street == null) {
                            addressTv.setText(province.name + city.name + county.name);
                        } else {
                            addressTv.setText(province.name + city.name + county.name + street.name);
                        }
                        popupWindow.dismiss();
                    }
                });
                View views = selector.getView();
                addressLine.addView(views);
                selector.setOnDialogCloseListener(new AddressSelector.OnDialogCloseListener() {
                    @Override
                    public void dialogclose() {
                        if (popupWindow!=null){
                            popupWindow.dismiss();
                        }
                    }
                });
                break;
        }
    }

    @Override
    public void onAddressSelected(Province province, City city, County county, Street street) {
        provinceCode = (province == null ? "" : province.code);
        cityCode = (city == null ? "" : city.code);
        countyCode = (county == null ? "" : county.code);
        streetCode = (street == null ? "" : street.code);
        LogUtil.d("数据", "省份id=" + provinceCode);
        LogUtil.d("数据", "城市id=" + cityCode);
        LogUtil.d("数据", "乡镇id=" + countyCode);
        LogUtil.d("数据", "街道id=" + streetCode);
        String s = (province == null ? "" : province.name) + (city == null ? "" : city.name) + (county == null ? "" : county.name) +
                (street == null ? "" : street.name);
        addressTv.setText(s);
        if (dialog != null) {
            dialog.dismiss();
        }
//        getSelectedArea();
    }

    @Override
    public void dialogclose() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public void selectorAreaPosition(int provincePosition, int cityPosition, int countyPosition, int streetPosition) {
        this.provincePosition = provincePosition;
        this.cityPosition = cityPosition;
        this.countyPosition = countyPosition;
        this.streetPosition = streetPosition;
        LogUtil.d("数据", "省份位置=" + provincePosition);
        LogUtil.d("数据", "城市位置=" + cityPosition);
        LogUtil.d("数据", "乡镇位置=" + countyPosition);
        LogUtil.d("数据", "街道位置=" + streetPosition);
    }

    /**
     * 根据code 来显示选择过的地区
     */
    private void getSelectedArea() {
        String province = addressDictManager.getProvince(provinceCode);
        String city = addressDictManager.getCity(cityCode);
        String county = addressDictManager.getCounty(countyCode);
        String street = addressDictManager.getStreet(streetCode);
        addressTv.setText(province + city + county + street);
        LogUtil.d("数据", "省份=" + province);
        LogUtil.d("数据", "城市=" + city);
        LogUtil.d("数据", "乡镇=" + county);
        LogUtil.d("数据", "街道=" + street);
    }

    /**
     * 城市popupWindow
     */
//    private void cityInitPop() { //初始化popup
//        cityPopupWindow = new PopupWindow(popupWindowView,
//                ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
//        cityPopupWindow.setOutsideTouchable(true);
//        cityPopupWindow.setFocusable(true);
//        cityPopupWindow.setBackgroundDrawable(new BitmapDrawable());
//
//        cityList = new ArrayList<>();
//        ListView list_menu1 = (ListView) popupWindowView
//                .findViewById(R.id.list_menu1);
//
//        addressAdapter = new AddressAdapter(this, cityList);
//
//        PopupMenuShowBean popupMenuShowBean = new PopupMenuShowBean();
//        popupMenuShowBean.setMenuname("沈阳");
//        cityList.add(popupMenuShowBean);
//        list_menu1.setAdapter(addressAdapter);
//
//        list_menu1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                city.setText(cityList.get(position).getMenuname());
//                cityPopupWindow.dismiss();
//            }
//        });
//    }

    /**
     * 地区popupWindow
     */
//    private void areaInitPop() { //初始化popup
//        areaPopupWindow = new PopupWindow(popupWindowView,
//                ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
//        areaPopupWindow.setOutsideTouchable(true);
//        areaPopupWindow.setFocusable(true);
//        areaPopupWindow.setBackgroundDrawable(new BitmapDrawable());
//
//        areaList = new ArrayList<>();
//        ListView list_menu1 = (ListView) popupWindowView
//                .findViewById(R.id.list_menu1);
//
//        addressAdapter = new AddressAdapter(this, areaList);
//
//        String[] areaArray = {"沈河", "大东", "皇姑", "铁西", "和平", "浑南新", "于洪", "沈北新", "苏家屯"};
//        for (int i = 0; i < areaArray.length; i++) {
//            PopupMenuShowBean popupMenuShowBean = new PopupMenuShowBean();
//            popupMenuShowBean.setMenuname(String.valueOf(areaArray[i]));
//            areaList.add(popupMenuShowBean);
//        }
//
//
//        list_menu1.setAdapter(addressAdapter);
//
//        list_menu1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                area.setText(areaList.get(position).getMenuname());
//                areaPopupWindow.dismiss();
//            }
//        });
//
//    }
}
