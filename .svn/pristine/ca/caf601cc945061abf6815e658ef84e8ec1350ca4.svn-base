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

import com.example.administrator.merchants.R;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：配送距离
 */
public class DeliveryDistanceDialog extends Dialog {
    private Context context;
    private TextView begin;
    private TextView end;

    public DeliveryDistanceDialog(Context context, TextView begin, TextView end) {
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
        setContentView(R.layout.dialog_delivery_distance);
        TextView sure = (TextView) findViewById(R.id.weel_btn_sure);
        final NumberPicker numberPickerbegin = (NumberPicker) findViewById(R.id.begin_time);
        final NumberPicker numberPickerend = (NumberPicker) findViewById(R.id.jieshu_time);
        final String[] city = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        boolean b = false;
        boolean e = true;
        numberPickerbegin.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberPickerend.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberPickerbegin.setDisplayedValues(city);
        numberPickerbegin.setMinValue(0);
        numberPickerbegin.setMaxValue(city.length - 1);
        for (int i = 0; i < city.length; i++) {
            if (city[i].equals(begin.getText().toString())) {
                b = true;
                numberPickerbegin.setValue(i);
            }
        }
        if (!b) {
            numberPickerbegin.setValue(0);
        }
        numberPickerend.setDisplayedValues(city);
        numberPickerend.setMinValue(0);
        numberPickerend.setMaxValue(city.length - 1);
        for (int j = 0; j < city.length; j++) {
            if (city[j].equals(end.getText().toString())) {
                e = true;
                numberPickerend.setValue(j);
            }
        }
        if (!e) {
            numberPickerend.setValue(0);
        }
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                begin.setText(city[numberPickerbegin.getValue()]);
                end.setText(city[numberPickerend.getValue()]);
                dismiss();
            }
        });
    }
}
