package com.example.administrator.merchants.common.update;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.merchants.http.show.UpdateShowBean;
import com.example.administrator.merchants.common.toast.CustomToast;

import java.io.File;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：更新
 */
public class CheckVersionTask implements Runnable {
    private Context context;
    private final String TAG = this.getClass().getName();
    private final int UPDATA_CLIENT = 1;
    private final int GET_UNDATAINFO_ERROR = 2;
    private final int DOWN_ERROR = 4;
    private final int NEW_YES = 3;
    private final int NEW_NO = 5;
    private UpdateShowBean info;
    private String localVersion;
    private int type;

    public CheckVersionTask(Context context, String localVersion, UpdateShowBean info, int type) {
        this.context = context;
        this.localVersion = localVersion;
        this.info = info;
        this.type = type;
    }

    public void run() {
        try {
            String aa = info.getVersion();//后台
            String bb = localVersion;//当前使用
            if (aa.compareTo(bb) > 0) {
                Log.i(TAG, "版本号不同 ,提示用户升级 ");
                Message msg = new Message();
                msg.what = UPDATA_CLIENT;
                handler.sendMessage(msg);
            } else {
                Message msg = new Message();
                if (type == 0) {
                    msg.what = NEW_NO;
                } else {
                    msg.what = NEW_YES;
                }
                handler.sendMessage(msg);
            }
        } catch (Exception e) {
            // 待处理
            Message msg = new Message();
            msg.what = GET_UNDATAINFO_ERROR;
            handler.sendMessage(msg);
            e.printStackTrace();
        }
    }

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch (msg.what) {
                case UPDATA_CLIENT:
                    //对话框通知用户升级程序
                    showUpdataDialog();
                    break;
                case GET_UNDATAINFO_ERROR:
                    //服务器超时
                    Toast.makeText(context, "获取服务器更新信息失败", Toast.LENGTH_LONG).show();
                    break;
                case DOWN_ERROR:
                    //下载apk失败
                    Toast.makeText(context, "下载新版本失败", Toast.LENGTH_LONG).show();
                    break;
                case NEW_NO:
                    Log.e(TAG, "已是最新版本！");
                    break;
                case NEW_YES:
                    CustomToast.getInstance(context).setMessage("已是最新版本！");
                    break;
            }
        }
    };


    /*
     *
     * 弹出对话框通知用户更新程序
     *
     * 弹出对话框的步骤：
     *  1.创建alertDialog的builder.
     *  2.要给builder设置属性, 对话框的内容,样式,按钮
     *  3.通过builder 创建一个对话框
     *  4.对话框show()出来
     */
    protected void showUpdataDialog() {
        AlertDialog.Builder builer = new AlertDialog.Builder(context);
        builer.setTitle("版本升级");
        builer.setMessage(info.getDescription());
        //当点确定按钮时从服务器上下载 新的apk 然后安装

        builer.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i(TAG, "下载apk,更新");
                        downLoadApk();
                    }
                }
        );
        //当点取消按钮时进行登录
        builer.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builer.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    /*
     * 从服务器中下载APK
     */
    protected void downLoadApk() {
        final ProgressDialog pd;    //进度条对话框
        pd = new ProgressDialog(context);
        pd.setCanceledOnTouchOutside(false);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMessage("正在下载更新");
        pd.show();
        new Thread() {
            @Override
            public void run() {
                try {
                    CheckVersionDownloadManager checkVersionDownloadManager = new CheckVersionDownloadManager();
                    File file = checkVersionDownloadManager.getFileFromServer(info.getUrl(), pd);//下载文件的方法  getFileFromServer
                    sleep(3000);
                    installApk(file);
                    pd.dismiss(); //结束掉进度条对话框
                } catch (Exception e) {
                    Message msg = new Message();
                    msg.what = DOWN_ERROR;
                    handler.sendMessage(msg);
                    e.printStackTrace();
                }
            }
        }.start();
    }

    //安装apk
    protected void installApk(File file) {
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        //执行的数据类型
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }
}

