package com.example.administrator.merchants.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseFragment;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.post.VerificationPostBean;
import com.example.administrator.merchants.scan.CaptureActivity;
import com.example.administrator.merchants.common.toast.CustomToast;

/**
 * 作者：韩宇 on 2017/8/19 0019 16:46
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：验证
 */
public class VerificationFragment extends BaseFragment implements View.OnClickListener {
    private Button button_prove;
    private EditText editText;
    private TextView store_name;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prove, null);
        editText = (EditText) view.findViewById(R.id.et_proving_num);
        button_prove = (Button) view.findViewById(R.id.btn_make_sure);
        store_name = (TextView) view.findViewById(R.id.store_name);
        if (getContext() != null) {
            store_name.setText(UserInfo.getInstance().getUser(getContext()).getStorename());
        }
        button_prove.setOnClickListener(this);
        TextView button = (TextView) view.findViewById(R.id.erweima);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), CaptureActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_make_sure:
                if ("".equals(editText.getText().toString())) {
                    if (getContext() != null) {
                        CustomToast.getInstance(getContext()).setMessage("验证码不能为空!");
                    }
                } else {
                    toProve();
                }
                break;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        MutualApplication.getRequestQueue().cancelAll("toProve114");
    }

    /**
     * 验证
     */
    private void toProve() {
        VerificationPostBean verificationPostBean = new VerificationPostBean();
        verificationPostBean.setStoreid(UserInfo.getInstance().getUser(getContext()).getStoreid());
        verificationPostBean.setConverificode(editText.getText().toString());
        Http.toProve(getContext(), verificationPostBean);
    }
}
