package com.example.administrator.merchants.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.merchants.R;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：营业时间
 */
public class BusinessTimeDialog extends Dialog {
    private Context context;
    private TextView begin;
    private TextView end;

    public BusinessTimeDialog(Context context, TextView begin, TextView end) {
        super(context);
        this.context = context;
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        window.setGravity(Gravity.BOTTOM);
        Rect rect = new Rect();
        View view1 = getWindow().getDecorView();
        view1.getWindowVisibleDisplayFrame(rect);
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(lp);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setAttributes(lp);
        setContentView(R.layout.dialog_business_time);
        TextView sure = (TextView) findViewById(R.id.weel_btn_sure);
        final NumberPicker numberPickerBegin = (NumberPicker) findViewById(R.id.begin_time);
        final NumberPicker numberPickerEnd = (NumberPicker) findViewById(R.id.jieshu_time);
        final String[] city = {"00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"};
        boolean b = false;
        boolean e = true;
        numberPickerBegin.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberPickerEnd.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberPickerBegin.setDisplayedValues(city);
        numberPickerBegin.setMinValue(0);
        numberPickerBegin.setMaxValue(city.length - 1);
        for (int i = 0; i < city.length; i++) {
            if (city[i].equals(begin.getText().toString())) {
                b = true;
                numberPickerBegin.setValue(i);
            }
        }
        if (!b) {
            numberPickerBegin.setValue(0);
        }
        numberPickerEnd.setDisplayedValues(city);
        numberPickerEnd.setMinValue(0);
        numberPickerEnd.setMaxValue(city.length - 1);
        for (int j = 0; j < city.length; j++) {
            if (city[j].equals(end.getText().toString())) {
                e = true;
                numberPickerEnd.setValue(j);
            }
        }
        if (!e) {
            numberPickerEnd.setValue(0);
        }
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberPickerEnd.getValue() == numberPickerBegin.getValue()) {
                    Toast.makeText(getContext(), "不支持24小时营业！", Toast.LENGTH_LONG).show();
                } else {
                    begin.setText(city[numberPickerBegin.getValue()]);
                    end.setText(city[numberPickerEnd.getValue()]);
                    dismiss();
                }
            }
        });
    }
}
