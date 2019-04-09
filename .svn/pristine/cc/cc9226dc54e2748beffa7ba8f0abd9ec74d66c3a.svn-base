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
import com.example.administrator.merchants.adapter.GoodsManagerClassificationAdapter;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.common.UserInfo;
import com.example.administrator.merchants.http.Http;
import com.example.administrator.merchants.http.post.ClassifyPostBean;
import com.example.administrator.merchants.http.show.ClassManagementShowBean;
import com.example.administrator.merchants.common.toast.CustomToast;

import java.util.List;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：添加个修改分类名对话框
 */
public class AddClassifyDialog extends Dialog {
    private Context context;
    private List<ClassManagementShowBean> list;
    private EditText editText;
    private int type;
    private int position;
    private GoodsManagerClassificationAdapter goodsManagerClassificationAdapter;

    /**
     * 添加分类名构造方法
     *
     * @param context
     * @param list
     * @param goodsManagerClassificationAdapter
     * @param type
     */
    public AddClassifyDialog(Context context, List<ClassManagementShowBean> list, GoodsManagerClassificationAdapter goodsManagerClassificationAdapter, int type) {
        super(context);
        this.context = context;
        this.list = list;
        this.type = type;
        this.goodsManagerClassificationAdapter = goodsManagerClassificationAdapter;
    }

    /**
     * 修改分类名构造方法
     *
     * @param context
     * @param list
     * @param position
     * @param goodsManagerClassificationAdapter
     * @param type
     */
    public AddClassifyDialog(Context context, List<ClassManagementShowBean> list, int position, GoodsManagerClassificationAdapter goodsManagerClassificationAdapter, int type) {
        super(context);
        this.context = context;
        this.list = list;
        this.position = position;
        this.goodsManagerClassificationAdapter = goodsManagerClassificationAdapter;
        this.type = type;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.dialog_edit);
        TextView cancel = (TextView) findViewById(R.id.dialog_view_cancel);
        TextView sure = (TextView) findViewById(R.id.dialog_view_sure);
        editText = (EditText) findViewById(R.id.dialog_view_edit);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        if (type == 1) {
            editText.setText(list.get(position).getMenuname());
        }
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().equals("")) {
                    CustomToast.getInstance(context).setMessage("分类名称不能为空！");
                } else {
                    if (type == 0) {
                        addClassify();
                    } else if (type == 1) {
                        updateClassify();
                    }
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        MutualApplication.getRequestQueue().cancelAll("addClassify93");
        MutualApplication.getRequestQueue().cancelAll("updateClassify94");
        MutualApplication.getRequestQueue().cancelAll("getClassify95");
    }

    /**
     * 添加分类
     */
    public void addClassify() {
        ClassifyPostBean classifyPostBean = new ClassifyPostBean();
        classifyPostBean.setStoreid(UserInfo.getInstance().getUser(context).getStoreid());
        classifyPostBean.setMenuname(editText.getText().toString());
        Http.addClassify(context, classifyPostBean, this);
    }

    /**
     * 修改分类名
     */
    public void updateClassify() {
        ClassifyPostBean classifyPostBean = new ClassifyPostBean();
        classifyPostBean.setStoreid(UserInfo.getInstance().getUser(context).getStoreid());
        classifyPostBean.setMenuname(editText.getText().toString());
        classifyPostBean.setMenuid(list.get(position).getMenuid());
        Http.updateClassify(context, classifyPostBean, this);
    }

    /**
     * 获取分类列表
     */
    public void getClassify() {
        ClassifyPostBean classifyPostBean = new ClassifyPostBean();
        classifyPostBean.setStoreid(UserInfo.getInstance().getUser(context).getStoreid());
        Http.getClassify(context, classifyPostBean, list, goodsManagerClassificationAdapter, this);
    }
}
