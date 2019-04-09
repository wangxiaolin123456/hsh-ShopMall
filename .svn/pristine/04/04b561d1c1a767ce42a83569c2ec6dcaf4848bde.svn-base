package com.example.administrator.merchants.activity;

import android.app.ActionBar;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.adapter.AddressAdapter;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseActivity;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.post.StoreAddressUpdatePostBean;
import com.example.administrator.merchants.http.show.PopupMenuShowBean;
import com.example.administrator.merchants.common.toast.CustomToast;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/7/20 0020 14:33
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：店铺地址修改
 */
public class StoreAddressUpdateActivity extends BaseActivity {
    private TextView qu;
    private PopupWindow popupWindow2;
    private EditText strE;
    private View popupWindowView;
    private List<PopupMenuShowBean> listTwo;
    private AddressAdapter addressAdapter;
    private Button commit;//保存
    private String yuan_shi,yuan_add_detail;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_address_update);
        setTitles("商家地址修改");
        yuan_shi=getIntent().getStringExtra("yuan_shi");
        yuan_add_detail=getIntent().getStringExtra("yuan_add_detail");
        qu = (TextView) findViewById(R.id.qu);
        popupWindowView = LayoutInflater.from(this).inflate(R.layout.popup_address_list, null);
        strE = (EditText) findViewById(R.id.activity_update_address_str);
        commit = (Button) findViewById(R.id.activity_update_address_commit);
        qu.setText(yuan_shi);
        strE.setText(yuan_add_detail);
        qu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPop2();
                popupWindow2.showAsDropDown(qu);
            }
        });
        commit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if ("".equals(qu.getText().toString())) {
                    CustomToast.getInstance(StoreAddressUpdateActivity.this).setMessage("区地址不能为空!");
                } else if ("".equals(strE.getText().toString())) {
                    CustomToast.getInstance(StoreAddressUpdateActivity.this).setMessage("详细地址不能为空!");
                } else {
                    StoreAddressUpdatePostBean storeAddressUpdatePostBean=new StoreAddressUpdatePostBean();
                    storeAddressUpdatePostBean.setStoreid(UserInfo.getInstance().getUser(StoreAddressUpdateActivity.this).getStoreid());
                    storeAddressUpdatePostBean.setAreaname(qu.getText().toString());
                    storeAddressUpdatePostBean.setStreetaddr(strE.getText().toString());
                    Http.storeAddressUpdate(StoreAddressUpdateActivity.this,storeAddressUpdatePostBean,qu,strE);
                }
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        MutualApplication.getRequestQueue().cancelAll("storeAddressUpdate113");
    }

    private void initPop2() { //初始化popup
        popupWindow2 = new PopupWindow(popupWindowView,
                ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        popupWindow2.setOutsideTouchable(true);
        popupWindow2.setFocusable(true);
        popupWindow2.setBackgroundDrawable(new BitmapDrawable());
        listTwo = new ArrayList<>();
        ListView list_menu1 = (ListView) popupWindowView
                .findViewById(R.id.list_menu1);
        addressAdapter = new AddressAdapter(this, listTwo);
        String[] quu = {"沈河", "大东", "皇姑", "铁西", "和平", "浑南新", "于洪", "沈北新", "苏家屯"};
        for (int i = 0; i < quu.length; i++) {
            PopupMenuShowBean popupMenuShowBean = new PopupMenuShowBean();
            popupMenuShowBean.setMenuname(String.valueOf(quu[i]));
            listTwo.add(popupMenuShowBean);
        }
        list_menu1.setAdapter(addressAdapter);
        list_menu1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                qu.setText(listTwo.get(position).getMenuname());
                popupWindow2.dismiss();
            }
        });
    }
}
