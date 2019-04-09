package com.example.administrator.merchants.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.administrator.merchants.R;
import com.example.administrator.merchants.activity.GoodsDetailsActivity;
import com.example.administrator.merchants.activity.OriginalSecondPageActivity;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.common.toast.CustomToast;
import com.example.administrator.merchants.http.HttpUrl;
import com.example.administrator.merchants.http.show.PrefectureImageBean;
import com.example.administrator.merchants.utils.LogUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;



/**
 * 新   专区图片的适配器
 * Created by Administrator on 2016/11/17.
 */
public class PrefectureImageAdapter extends BaseAdapter {
    private Context context;
    private List<PrefectureImageBean> list;
    private Holder holder;
    private com.nostra13.universalimageloader.core.ImageLoader imageLoader;
    private DisplayImageOptions options;
    private int pos;

    public PrefectureImageAdapter(Context context, List<PrefectureImageBean> list) {
        this.context = context;
        this.list = list;
        options = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .build();
        imageLoader = com.nostra13.universalimageloader.core.ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_home_prefecture, null);
            holder = new Holder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.item_home_pre_image_one);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        imageLoader.displayImage(list.get(position).getImgpfile(),holder.imageView , options);

        //设置点击的区域
        holder.imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (list.get(position).getIstitle().equals("1")) {
                        //说明是标题  不可点击
                    } else {
                        displayXY(event.getX(), event.getY(), position);
                        LogUtil.i(event.getX() + "\n" + event.getY() + "");
                    }
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_CANCEL) {
                    return true;
                }
                return false;
            }
        });

        return convertView;
    }

    /**
     * 获取到坐标，进行判断  鼠标点击的坐标  x y
     */
    private void displayXY(float x, float y, int position) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        int width = display.getWidth();//总宽  list.get(position).getImgwidth()
        int height = list.get(position).getImgheight();//总高
        int a = list.get(position).getRownum();//行
        int b = list.get(position).getColnum();//列
        int aw = width / b;//单元格宽
        int bh = height / a;//单元格高

        if (list.get(position).getIstitle().equals("1")) {
            //是标题
        } else {
            for (int i = 0; i < a; i++) {
                if (y >= 0 && y < (i + 1) * bh) {
                    for (int j = 1; j <= b; j++) {
                        if (x >= 0 && x < j * aw) {
                            Log.e("aa", "点击的行" + i + "\n" + "点击的列" + j + "\n" + "点击的第几个" + String.valueOf(i * b + j));
                            prefectureInfo(position, i * b + j);
                            break;
                        }
                    }
                    break;
                }
                if (i == a - 1) {
                    Log.e("aa", "有问题！");
                }
            }
        }
    }

    public class Holder {
        public ImageView imageView;
    }

    /**
     * 获取专区信息
     */
    public void prefectureInfo(int position, int sequ) {
        try {
            final JSONObject obj = new JSONObject();
            obj.put("groupid", list.get(position).getGroupid());//组编码
            obj.put("sequ", sequ);//序号     从1开始
            System.out.println("专区信息入参"+obj.toString());
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST, HttpUrl.areaInfo, obj,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if ("true".equals(response
                                        .getString("success"))) {
                                    LogUtil.e("专区信息返回"+response);
                                    final JSONObject jsonObject = response.getJSONObject("areainfo");
                                    String areaid = jsonObject.getString("areaid");
                                    String areaName = jsonObject.getString("areaName");
                                    String areatype = jsonObject.getString("areatype");
                                    String merid = jsonObject.getString("merid");
                                    String storeid = jsonObject.getString("storeid");
                                    if (areatype.equals("11")) {
                                        //商品专区
                                        Intent intents = new Intent(context, OriginalSecondPageActivity.class);
                                        intents.putExtra("menuid", areaid);
                                        intents.putExtra("menuname", areaName);
                                        context.startActivity(intents);
                                    } else if (areatype.equals("21")) {
                                        //商家专区
                                    } else if (areatype.equals("12")) {
                                        //商品广告
                                        Intent intent = new Intent();
                                        intent.putExtra("merid", merid);
                                        intent.setClass(context, GoodsDetailsActivity.class);
                                        context.startActivity(intent);
                                    } else if (areatype.equals("22")) {
                                        //商家广告
                                    }
                                } else {
                                    CustomToast.getInstance(context).setMessage(response.getString("message")
                                            + "");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("TAG",
                            "onErrorResponse---->"
                                    + error.getMessage(), error);
                }
            });
            jsonObjectRequest.setTag("ShopMessageDetailsPost");
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy
                    (500000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
