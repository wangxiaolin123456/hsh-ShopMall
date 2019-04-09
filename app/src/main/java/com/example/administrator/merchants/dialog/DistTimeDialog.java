package com.example.administrator.merchants.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.common.toast.CustomToast;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：配送时长
 */
public class DistTimeDialog extends Dialog {
    private Context context;
    private TextView textView;
    private String hit;
    private String dws;

    public DistTimeDialog(Context context, TextView textView, String hit, String dws) {
        super(context);
        this.context = context;
        this.textView = textView;
        this.hit = hit;
        this.dws = dws;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.dialog_dist_time);
        TextView cancel = (TextView) findViewById(R.id.dialog_view_all_cancel);
        TextView sure = (TextView) findViewById(R.id.dialog_view_all_sure);
        final EditText text = (EditText) findViewById(R.id.dialog_view_all_edit);
        TextView dw = (TextView) findViewById(R.id.dialog_view_all_danwei);
        dw.setText(dws);
        text.setHint(hit);
        text.setText(textView.getText().toString());
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!"".equals(text.getText().toString())) {
                    if (Double.parseDouble(text.getText().toString()) > 0) {
                        textView.setText(text.getText().toString());
                        dismiss();
                    } else {
                        CustomToast.getInstance(context).setMessage("配送时长需大于0！");
                    }
                } else {
                    CustomToast.getInstance(context).setMessage("不能为空！");
                }
            }
        });
    }
}
