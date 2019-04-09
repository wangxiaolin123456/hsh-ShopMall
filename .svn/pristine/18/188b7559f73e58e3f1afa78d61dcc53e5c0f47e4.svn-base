package com.example.administrator.merchants.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.base.BaseActivity;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.post.RecruitmentPostBean;
import com.example.administrator.merchants.common.toast.CustomToast;
import com.example.administrator.merchants.common.UserInfo;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：新建招聘信息
 */
public class AddRecruitmentActivity extends BaseActivity {
    private EditText name, number, year, money;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruitment_add);
        setTitles("新建招聘信息");
        name = (EditText) findViewById(R.id.activity_recruitment_add_name);
        number = (EditText) findViewById(R.id.activity_recruitment_add_number);
        year = (EditText) findViewById(R.id.activity_recruitment_add_jingyan);
        money = (EditText) findViewById(R.id.activity_recruitment_add_money);
        button = (Button) findViewById(R.id.activity_recruitment_add_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(name.getText().toString()) || "".equals(number.getText().toString()) || "".equals(year.getText().toString()) || "".equals(money.getText().toString())) {
                    CustomToast.getInstance(AddRecruitmentActivity.this).setMessage("请填全信息！");
                } else {
                    add();
                }
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        MutualApplication.getRequestQueue().cancelAll("recruitmentAdd6");
    }

    /***
     * 新建招聘信息接口
     */
    public void add() {
        RecruitmentPostBean recruitmentPostBean = new RecruitmentPostBean();
        recruitmentPostBean.setStoreid(UserInfo.getInstance().getUser(AddRecruitmentActivity.this).getStoreid());
        recruitmentPostBean.setStation(name.getText().toString());
        recruitmentPostBean.setNumber(number.getText().toString());
        recruitmentPostBean.setExperience(year.getText().toString());
        recruitmentPostBean.setSalary(money.getText().toString());
        Http.recruitmentAdd(AddRecruitmentActivity.this, recruitmentPostBean);
    }
}
