package com.example.administrator.merchants.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseActivity;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.post.CodePostBean;
import com.example.administrator.merchants.http.post.ServeIdPostBean;
import com.example.administrator.merchants.http.post.ServiceCommitPostBean;
import com.example.administrator.merchants.common.DateUtils;
import com.example.administrator.merchants.common.TimerCount;
import com.example.administrator.merchants.common.toast.CustomToast;
import com.example.administrator.merchants.common.TimerHandler;

/**
 * 作者：韩宇 on 2017/8/2 0002 13:46
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：服务详情
 */
public class ServiceDetailsActivity extends BaseActivity implements TextWatcher {
    private WebView imageView;
    private ImageView checkImage;
    private TextView title;
    private EditText phone;
    private EditText code;
    private Button getCode;
    private Button commit;
    public static String httpCode = "a";//网络请求的验证码
    public static String getServeId;
    private TimerCount timerCount;
    private TimerHandler timerHandler;
    public static int st = 0;
    private String titleText,serveId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_details);
        titleText=getIntent().getStringExtra("title");
        title = (TextView) findViewById(R.id.head_title);
        title.setText(titleText);
        serveId=getIntent().getStringExtra("serveid");
        checkImage = (ImageView) findViewById(R.id.activity_service_details_check_image);
        imageView = (WebView) findViewById(R.id.activity_service_details_image);
        phone = (EditText) findViewById(R.id.activity_service_details_input_phone);
        code = (EditText) findViewById(R.id.activity_service_details_input_code);
        getCode = (Button) findViewById(R.id.activity_service_details_get_code_btn);
        timerCount = new TimerCount(60000, 1000, getCode);
        commit = (Button) findViewById(R.id.activity_service_details_commit_btn);
        details(serveId);
        getCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!DateUtils.gephonetzhenze(phone.getText().toString())) {
                    CustomToast.getInstance(ServiceDetailsActivity.this).setMessage("请输入正确的手机号格式！");
                } else {
                    getCode.setClickable(false);
                    timerHandler = new TimerHandler(60000, 1000, getCode, 7);
                    timerHandler.start();
                    getCodeRequest();
                }
            }
        });
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codes = code.getText().toString();//获取验证内容
                if (codes.matches("")) {
                    CustomToast.getInstance(ServiceDetailsActivity.this).setMessage("请输入验证码！");
                } else if (!DateUtils.gephonetzhenze(phone.getText().toString())) {
                    CustomToast.getInstance(ServiceDetailsActivity.this).setMessage("请输入正确的手机号格式！");
                } else {
                    if (st == 0) {
                        CustomToast.getInstance(ServiceDetailsActivity.this).setMessage("您还未获取验证码！");
                    } else if (st == 2) {
                        CustomToast.getInstance(ServiceDetailsActivity.this).setMessage("请重新获取验证码！");
                    } else {
                        if (code.getText().toString().equals(httpCode)) {
                            commit.setClickable(false);
                            commit();
                        } else {
                            CustomToast.getInstance(ServiceDetailsActivity.this).setMessage("验证码不正确！");
                        }

                    }

                }
            }
        });
        code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (httpCode.equals(s + "")) {
                    checkImage.setVisibility(View.VISIBLE);
                } else {
                    checkImage.setVisibility(View.GONE);
                }
            }
        });
    }

    /**
     * 服务详情
     */
    public void details(String serveId) {
        ServeIdPostBean serveIdPostBean = new ServeIdPostBean();
        serveIdPostBean.setServeid(serveId);
        Http.serviceDetail(this, serveIdPostBean, imageView);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MutualApplication.getRequestQueue().cancelAll("serviceCode79");
        MutualApplication.getRequestQueue().cancelAll("serviceDetail80");
        MutualApplication.getRequestQueue().cancelAll("serviceCommit81");
    }

    /**
     * 提交信息
     */
    public void commit() {
        ServiceCommitPostBean serviceCommitPostBean = new ServiceCommitPostBean();
        serviceCommitPostBean.setStoreid(UserInfo.getInstance().getUser(ServiceDetailsActivity.this).getStoreid());
        serviceCommitPostBean.setServeid(getServeId);
        serviceCommitPostBean.setNeedsphone(phone.getText().toString());
        serviceCommitPostBean.setAucode(code.getText().toString());
        Http.serviceCommit(this, serviceCommitPostBean, timerCount, timerHandler, phone, code, getCode, checkImage, commit);
    }

    /**
     * 获取验证码
     */
    public void getCodeRequest() {
        CodePostBean codePostBean = new CodePostBean();
        codePostBean.setNeedsphone(phone.getText().toString());
        Http.serviceCode(this, codePostBean, timerCount, getCode);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() == 11) {
            getCode.setEnabled(true);
        } else {
            getCode.setEnabled(false);
        }
    }
}
