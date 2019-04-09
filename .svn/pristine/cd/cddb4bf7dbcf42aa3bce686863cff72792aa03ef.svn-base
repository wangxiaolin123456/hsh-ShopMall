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

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：输入
 */
public class EditDialog extends Dialog {
    private Context context;
    private TextView textView;



    public EditDialog(Context context, TextView textView) {
        super(context);
        this.context=context;
        this.textView =textView;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.dialog_edit);
        TextView cancel= (TextView) findViewById(R.id.dialog_view_cancel);
        TextView sure= (TextView) findViewById(R.id.dialog_view_sure);
        final EditText text= (EditText) findViewById(R.id.dialog_view_edit);
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
                textView.setText(text.getText().toString());
                dismiss();
            }
        });


    }
}
