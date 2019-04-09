package com.example.administrator.merchants.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 金贝币和银贝币在一起
 * j y 区分
 * 记录入口
 * Created by User on 2019/2/12.
 */

public class JinbeiCoinActivity extends BaseActivity {
    @BindView(R.id.beibi_coin_num_tv)
    TextView beibiCoinNumTv;
    @BindView(R.id.beibi_recharge_btn)
    Button rechargeBtn;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beibi_coin);
        ButterKnife.bind(this);
        if (getIntent().getStringExtra("type").equals("j")) {
            setTitle(R.string.j_beibi);
            beibiCoinNumTv.setText("金贝币: " + getIntent().getStringExtra("yue"));
            rechargeBtn.setVisibility(View.VISIBLE);
        } else if (getIntent().getStringExtra("type").equals("y")) {
            setTitle(R.string.y_beibi);
            beibiCoinNumTv.setText("银贝币: " + getIntent().getStringExtra("yue"));
            rechargeBtn.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.beibi_used_record_line, R.id.beibi_grant_record_line
            , R.id.beibi_recharge_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.beibi_used_record_line:
                //使用记录
                if (getIntent().getStringExtra("type").equals("j")) {
                    intent = new Intent(mContext, BeiBiRecordActivity.class);
                    intent.putExtra("type", "j");
                    startActivity(intent);
                } else if (getIntent().getStringExtra("type").equals("y")) {
                    intent = new Intent(mContext, BeiBiRecordActivity.class);
                    intent.putExtra("type", "y");
                    startActivity(intent);
                }
                break;
            case R.id.beibi_grant_record_line:
                //发放记录
                if (getIntent().getStringExtra("type").equals("j")) {
                    intent = new Intent(mContext, BeiBiGrantRecordActivity.class);
                    intent.putExtra("type", "j");
                    startActivity(intent);
                } else if (getIntent().getStringExtra("type").equals("y")) {
                    intent = new Intent(mContext, BeiBiGrantRecordActivity.class);
                    intent.putExtra("type", "y");
                    startActivity(intent);
                }
                break;
            case R.id.beibi_recharge_btn:
                //金贝币充值
                startActivity(new Intent(mContext, BeiBiRechargeActivity.class));
                break;
        }
    }
}
