package com.example.administrator.merchants.http;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.diy.widget.CircularImage;
import com.example.administrator.merchants.adapter.AccumulatedIncomeAdapter;
import com.example.administrator.merchants.adapter.AddressListAdapter;
import com.example.administrator.merchants.adapter.BankAdapter;
import com.example.administrator.merchants.adapter.BeiBiGrantRecordAdapter;
import com.example.administrator.merchants.adapter.BeiRecordAdapter;
import com.example.administrator.merchants.adapter.CommodityManagementListAdapter;
import com.example.administrator.merchants.adapter.CommonAdapter;
import com.example.administrator.merchants.adapter.EstimatedEarningsDetailsAdapter;
import com.example.administrator.merchants.adapter.GoodsManagerClassificationAdapter;
import com.example.administrator.merchants.adapter.MenuAdapter;
import com.example.administrator.merchants.adapter.MonthIncomeBaseAdapter;
import com.example.administrator.merchants.adapter.MyMessageAdapter;
import com.example.administrator.merchants.adapter.OriginOrderAdapter;
import com.example.administrator.merchants.adapter.OriginalHomeYouLikeListAdapter;
import com.example.administrator.merchants.adapter.OriginalSearchGridAdapter;
import com.example.administrator.merchants.adapter.RecruitmentManagementAdapter;
import com.example.administrator.merchants.adapter.SellOrderAdapter;
import com.example.administrator.merchants.adapter.ServiceBaseAdapter;
import com.example.administrator.merchants.adapter.ShoppingCarAdapter;
import com.example.administrator.merchants.adapter.StatementAccountBaseAdapter;
import com.example.administrator.merchants.adapter.UpdateImageAdapter;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.common.TimerCount;
import com.example.administrator.merchants.common.TimerHandler;
import com.example.administrator.merchants.common.UpRequest;
import com.example.administrator.merchants.common.toast.CustomToast;
import com.example.administrator.merchants.common.views.CustomerViewPage;
import com.example.administrator.merchants.common.views.GroupSix;
import com.example.administrator.merchants.common.views.MyGridView;
import com.example.administrator.merchants.common.views.RefreshLayout;
import com.example.administrator.merchants.dialog.AddClassifyDialog;
import com.example.administrator.merchants.http.listener.AccountStatementDetailListener;
import com.example.administrator.merchants.http.listener.AccumulateIncomeListListener;
import com.example.administrator.merchants.http.listener.AddGoodsListener;
import com.example.administrator.merchants.http.listener.AddShopCarListener;
import com.example.administrator.merchants.http.listener.AddressListListener;
import com.example.administrator.merchants.http.listener.BalanceListener;
import com.example.administrator.merchants.http.listener.BankListListener;
import com.example.administrator.merchants.http.listener.BeiBiGrantRecordListener;
import com.example.administrator.merchants.http.listener.BeiBiRecordListener;
import com.example.administrator.merchants.http.listener.ChangeCarCountListener;
import com.example.administrator.merchants.http.listener.ClassifyListener;
import com.example.administrator.merchants.http.listener.ClassifyManagementListener;
import com.example.administrator.merchants.http.listener.CodeListener;
import com.example.administrator.merchants.http.listener.CommodityManagementListListener;
import com.example.administrator.merchants.http.listener.CommonListener;
import com.example.administrator.merchants.http.listener.CommonNumberListener;
import com.example.administrator.merchants.http.listener.ConfirmBeiBiPasswordListener;
import com.example.administrator.merchants.http.listener.CustomerTelephoneNumberListener;
import com.example.administrator.merchants.http.listener.DefaultAddressListener;
import com.example.administrator.merchants.http.listener.DefaultBankCardListener;
import com.example.administrator.merchants.http.listener.DeleteAddressListener;
import com.example.administrator.merchants.http.listener.DeleteBankCardListener;
import com.example.administrator.merchants.http.listener.DeleteClassifyListener;
import com.example.administrator.merchants.http.listener.DeleteMerchandiseListener;
import com.example.administrator.merchants.http.listener.DeleteMessageListener;
import com.example.administrator.merchants.http.listener.DeleteRecruitmentListener;
import com.example.administrator.merchants.http.listener.DiscountListener;
import com.example.administrator.merchants.http.listener.EnterDetailsListener;
import com.example.administrator.merchants.http.listener.EnterNeedListener;
import com.example.administrator.merchants.http.listener.EstimatedEarningsDetailsListener;
import com.example.administrator.merchants.http.listener.EvaluaeListener;
import com.example.administrator.merchants.http.listener.ForgetCodeListener;
import com.example.administrator.merchants.http.listener.GetDefaultAddressListener;
import com.example.administrator.merchants.http.listener.GetDefaultBankListener;
import com.example.administrator.merchants.http.listener.GetGoodsDetailListener;
import com.example.administrator.merchants.http.listener.GetMessageNumberListener;
import com.example.administrator.merchants.http.listener.GetTextListener;
import com.example.administrator.merchants.http.listener.GetTnListener;
import com.example.administrator.merchants.http.listener.GoodsDetailListener;
import com.example.administrator.merchants.http.listener.GuessYouLikeListener;
import com.example.administrator.merchants.http.listener.LogOutListener;
import com.example.administrator.merchants.http.listener.LoginListener;
import com.example.administrator.merchants.http.listener.MerchantOrderListListener;
import com.example.administrator.merchants.http.listener.MerchantsOrderDetailListener;
import com.example.administrator.merchants.http.listener.MessageDetailListener;
import com.example.administrator.merchants.http.listener.MonthIncomeListener;
import com.example.administrator.merchants.http.listener.MyMessageListListener;
import com.example.administrator.merchants.http.listener.NoticeDetailsListener;
import com.example.administrator.merchants.http.listener.NoticeJoinListener;
import com.example.administrator.merchants.http.listener.OrderDeleteListener;
import com.example.administrator.merchants.http.listener.OriginOrderListListener;
import com.example.administrator.merchants.http.listener.OriginalClassifyListener;
import com.example.administrator.merchants.http.listener.OriginalOrderDetailListener;
import com.example.administrator.merchants.http.listener.OriginalSearchResultListener;
import com.example.administrator.merchants.http.listener.PagerToServerListener;
import com.example.administrator.merchants.http.listener.PayOrderListener;
import com.example.administrator.merchants.http.listener.PayToBeiBiListener;
import com.example.administrator.merchants.http.listener.PhoneTypeListener;
import com.example.administrator.merchants.http.listener.RecruitmentListListener;
import com.example.administrator.merchants.http.listener.RevenueHeadListener;
import com.example.administrator.merchants.http.listener.RevenueManagementListener;
import com.example.administrator.merchants.http.listener.SearchMenuListener;
import com.example.administrator.merchants.http.listener.SendListener;
import com.example.administrator.merchants.http.listener.ServiceCommitListener;
import com.example.administrator.merchants.http.listener.ServiceDetailCodeListener;
import com.example.administrator.merchants.http.listener.ServiceDetailListener;
import com.example.administrator.merchants.http.listener.ServiceListener;
import com.example.administrator.merchants.http.listener.ShopModeListener;
import com.example.administrator.merchants.http.listener.ShoppingCarCountListener;
import com.example.administrator.merchants.http.listener.ShoppingCarListener;
import com.example.administrator.merchants.http.listener.StatementAccountListener;
import com.example.administrator.merchants.http.listener.StoreAddressUpdateListener;
import com.example.administrator.merchants.http.listener.StoreGetMessageListener;
import com.example.administrator.merchants.http.listener.StrategyListener;
import com.example.administrator.merchants.http.listener.ToCommodityDetailsListener;
import com.example.administrator.merchants.http.listener.ToGetHotSearchListener;
import com.example.administrator.merchants.http.listener.ToSubOrderListener;
import com.example.administrator.merchants.http.listener.ToastListener;
import com.example.administrator.merchants.http.listener.TwoDimensionCodeListener;
import com.example.administrator.merchants.http.listener.UpdateInfoListener;
import com.example.administrator.merchants.http.listener.WXPayEntryListener;
import com.example.administrator.merchants.http.post.AccountStatementDetailPostBean;
import com.example.administrator.merchants.http.post.AddGoodsPossBean;
import com.example.administrator.merchants.http.post.AddressPossBean;
import com.example.administrator.merchants.http.post.ApplyForRefundPostBean;
import com.example.administrator.merchants.http.post.BalanceExtractionPostBean;
import com.example.administrator.merchants.http.post.BankPostBean;
import com.example.administrator.merchants.http.post.BeiBiGivePostBean;
import com.example.administrator.merchants.http.post.ChangeCarCountPostBean;
import com.example.administrator.merchants.http.post.ClassifyPostBean;
import com.example.administrator.merchants.http.post.CodePostBean;
import com.example.administrator.merchants.http.post.CommodityManagementListPostBean;
import com.example.administrator.merchants.http.post.CommonPostBean;
import com.example.administrator.merchants.http.post.ConfirmBeiBiPasswordPostBean;
import com.example.administrator.merchants.http.post.DefaultAddressPostBean;
import com.example.administrator.merchants.http.post.DefaultBankCardPostBean;
import com.example.administrator.merchants.http.post.DeleteAddressPostBean;
import com.example.administrator.merchants.http.post.DeleteBankCardPostBean;
import com.example.administrator.merchants.http.post.DeleteMerchandisePostBean;
import com.example.administrator.merchants.http.post.DeleteRecruitmentPostBean;
import com.example.administrator.merchants.http.post.EnterNeedPostBean;
import com.example.administrator.merchants.http.post.EvaluatePostBean;
import com.example.administrator.merchants.http.post.ForgetPostBean;
import com.example.administrator.merchants.http.post.GetTnPostBean;
import com.example.administrator.merchants.http.post.ListPostBean;
import com.example.administrator.merchants.http.post.LoginPostBean;
import com.example.administrator.merchants.http.post.MenuPostBean;
import com.example.administrator.merchants.http.post.MerIdPostBean;
import com.example.administrator.merchants.http.post.MessageDetailPostBean;
import com.example.administrator.merchants.http.post.NoticeDetailsPostBean;
import com.example.administrator.merchants.http.post.OrderFinishPostBean;
import com.example.administrator.merchants.http.post.OriginalSearchResultPostBean;
import com.example.administrator.merchants.http.post.OriginalSecondPagePostBean;
import com.example.administrator.merchants.http.post.PayOrderPostBean;
import com.example.administrator.merchants.http.post.PhoneTypePostBean;
import com.example.administrator.merchants.http.post.RecruitmentPostBean;
import com.example.administrator.merchants.http.post.SendPostBean;
import com.example.administrator.merchants.http.post.ServeIdPostBean;
import com.example.administrator.merchants.http.post.ServiceCommitPostBean;
import com.example.administrator.merchants.http.post.ServicePostBean;
import com.example.administrator.merchants.http.post.StoreAddressUpdatePostBean;
import com.example.administrator.merchants.http.post.StoreIdPostBean;
import com.example.administrator.merchants.http.post.StoreSetMessagePostBean;
import com.example.administrator.merchants.http.post.ToSubOrderPostBean;
import com.example.administrator.merchants.http.post.UpdatePasswordPostBean;
import com.example.administrator.merchants.http.post.VerificationPostBean;
import com.example.administrator.merchants.http.show.AccumulatedIncomeShowBean;
import com.example.administrator.merchants.http.show.AddressContentShowBean;
import com.example.administrator.merchants.http.show.BankShowBean;
import com.example.administrator.merchants.http.show.BeiRecordShowBean;
import com.example.administrator.merchants.http.show.BitmapShowBean;
import com.example.administrator.merchants.http.show.CarouselShowBean;
import com.example.administrator.merchants.http.show.ClassManagementShowBean;
import com.example.administrator.merchants.http.show.CommodityShowBean;
import com.example.administrator.merchants.http.show.EstimatedEarningsDetailsShowBean;
import com.example.administrator.merchants.http.show.GoodsDetailShowBean;
import com.example.administrator.merchants.http.show.ImageShowBean;
import com.example.administrator.merchants.http.show.MerchantsOrderShowBean;
import com.example.administrator.merchants.http.show.MessageShowBean;
import com.example.administrator.merchants.http.show.ModeListShowBean;
import com.example.administrator.merchants.http.show.ModeShowBean;
import com.example.administrator.merchants.http.show.MonthIncomeShowBean;
import com.example.administrator.merchants.http.show.OrderShowBean;
import com.example.administrator.merchants.http.show.OriginOrderShowBean;
import com.example.administrator.merchants.http.show.PopupMenuShowBean;
import com.example.administrator.merchants.http.show.RecruitmentManagementShowBean;
import com.example.administrator.merchants.http.show.ServiceShowBean;
import com.example.administrator.merchants.http.show.ShoppingCarShowBean;
import com.example.administrator.merchants.http.show.StatementAccountShowBean;
import com.example.administrator.merchants.http.show.TempShoppingCarShowBean;
import com.joanzapata.android.QuickAdapter;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.youth.banner.Banner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：网络接口请求
 */
public class Http {
    /***
     * 登录
     *
     * @param context
     * @param progressDialog
     * @param loginPostBean
     */
    public static void login(Context context, ProgressDialog progressDialog, LoginPostBean loginPostBean) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    HttpUrl.login, PossObject.login(loginPostBean), new LoginListener(context, loginPostBean, progressDialog, 0), new ErrorListener(context, progressDialog, 0));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("login1");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**
     * 添加地址接口
     *
     * @param context
     * @param addressPossBean
     */
    public static void addressAdd(Context context, AddressPossBean addressPossBean) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.addAddress, PossObject.addressAdd(addressPossBean),
                    new ToastListener(context, "添加成功！", 0), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("addressAdd2");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**
     * 修改地址 接口
     *
     * @param context
     * @param addressPossBean
     */
    public static void addressUpdate(Context context, AddressPossBean addressPossBean) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.updateAddress, PossObject.addressUpdate(addressPossBean), new ToastListener(context, "修改成功！", 0), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("addressUpdate3");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**
     * 添加银行卡
     *
     * @param context
     * @param bankPostBean
     */
    public static void bankAdd(Context context, BankPostBean bankPostBean) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.addbank, PossObject.bankAdd(bankPostBean), new ToastListener(context, "添加成功！", 0), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("bankAdd4");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改银行卡
     *
     * @param context
     * @param bankPostBean
     */
    public static void bankUpdate(Context context, BankPostBean bankPostBean) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.updatebank, PossObject.bankUpdate(bankPostBean), new ToastListener(context, "修改成功！", 0), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("bankUpdate5");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**
     * 添加招聘信息
     *
     * @param context
     * @param recruitmentPostBean
     */
    public static void recruitmentAdd(Context context, RecruitmentPostBean recruitmentPostBean) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.addRecruitmentManagement, PossObject.recruitmentAdd(recruitmentPostBean), new ToastListener(context, "添加成功！", 0), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("recruitmentAdd6");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 地址列表
     *
     * @param context
     * @param listPostBean
     */
    public static void addressList(Context context, ListPostBean listPostBean, List<AddressContentShowBean> list, int type, ListView listView, AddressListAdapter addressListAdapter, RefreshLayout swipeLayout, View footView) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.addressList, PossObject.list(listPostBean), new AddressListListener(context, list, type, listView, addressListAdapter, swipeLayout, footView), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("addressList7");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /***
     * 设置默认地址
     *
     * @param context
     * @param defaultAddressPostBean
     * @param list
     * @param position
     * @param addressListAdapter
     */
    public static void defaultAddress(Context context, DefaultAddressPostBean defaultAddressPostBean, List<AddressContentShowBean> list, int position, AddressListAdapter addressListAdapter) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.defaultLocation, PossObject.getDefaultAddress(defaultAddressPostBean), new DefaultAddressListener(context, list, position, addressListAdapter), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("defaultAddress8");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除地址
     *
     * @param context
     * @param deleteAddressPostBean
     * @param list
     * @param position
     * @param addressListAdapter
     * @param dialogInterface
     */
    public static void delectAddress(Context context, DeleteAddressPostBean deleteAddressPostBean, List<AddressContentShowBean> list, int position, AddressListAdapter addressListAdapter, DialogInterface dialogInterface) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.DeleteAddress, PossObject.delectAddress(deleteAddressPostBean), new DeleteAddressListener(context, list, position, addressListAdapter, dialogInterface), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("delectAddress9");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /***
     * 对账单明细
     *
     * @param accountStatementDetailPostBean
     * @return
     * @throws JSONException
     */
    public static void accountStatementDetail(Context context, AccountStatementDetailPostBean accountStatementDetailPostBean, List<OrderShowBean> list, int type, ListView listView, SellOrderAdapter orderAdapter, RefreshLayout swipeLayout, View footView) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.xfz_order_list, PossObject.accountStatementDetail(accountStatementDetailPostBean), new AccountStatementDetailListener(context, type, list, listView, orderAdapter, swipeLayout, footView), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("accountStatementDetail0");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 银行卡列表
     *
     * @param context
     * @param listPostBean
     * @param list
     * @param type
     * @param listView
     * @param bankAdapter
     * @param swipeLayout
     * @param footView
     */
    public static void bankList(Context context, ListPostBean listPostBean, List<BankShowBean> list, int type, ListView listView, BankAdapter bankAdapter, RefreshLayout swipeLayout, View footView) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.bankList, PossObject.list(listPostBean), new BankListListener(context, list, type, listView, bankAdapter, swipeLayout, footView), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("accountStatementDetail1");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置默认银行卡
     *
     * @param context
     * @param defaultBankCardPostBean
     * @param list
     * @param position
     * @param bankAdapter
     */
    public static void defaultBank(Context context, DefaultBankCardPostBean defaultBankCardPostBean, List<BankShowBean> list, int position, BankAdapter bankAdapter) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.defaultbank, PossObject.defaultBank(defaultBankCardPostBean), new DefaultBankCardListener(context, list, position, bankAdapter), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("defaultBank12");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除银行卡
     *
     * @param context
     * @param deleteBankCardPostBean
     * @param list
     * @param position
     * @param bankAdapter
     * @param dialogInterface
     */
    public static void delectBank(Context context, DeleteBankCardPostBean deleteBankCardPostBean, List<BankShowBean> list, int position, BankAdapter bankAdapter, DialogInterface dialogInterface) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.DeleteBank, PossObject.delectBank(deleteBankCardPostBean), new DeleteBankCardListener(context, list, position, bankAdapter, dialogInterface), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("delectBank13");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /***
     * 余额提取中的获取贝币余额
     *
     * @param context
     * @param storeIdPostBean
     * @param balance
     */
    public static void getBalance(Context context, StoreIdPostBean storeIdPostBean, TextView balance) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    HttpUrl.beibi_balance, PossObject.getStoreId(storeIdPostBean), new
                    BalanceListener(context, balance, 0), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("getBalance14");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取默认银行卡信息
     *
     * @param context
     * @param storeIdPostBean
     * @param bankname
     * @param username
     * @param tel
     * @param banknumber
     */
    public static void getDefaultBankCard(Context context, StoreIdPostBean storeIdPostBean, TextView bankname, TextView username, TextView tel, TextView banknumber) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.get_bank_default, PossObject.getStoreId(storeIdPostBean), new GetDefaultBankListener(context, bankname, username, tel, banknumber), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("getDefaultBankCard15");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 确认贝币密码
     *
     * @param context
     * @param confirmBeiBiPasswordPostBean
     * @param bankname
     * @param username
     * @param tel
     * @param banknumber
     * @param editText
     */
    public static void confirmBeiBiPassword(Context context, ConfirmBeiBiPasswordPostBean confirmBeiBiPasswordPostBean, TextView bankname, TextView username, TextView tel, TextView banknumber, EditText editText) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.paypassword, PossObject.confirmBeiBiPassword(confirmBeiBiPasswordPostBean), new ConfirmBeiBiPasswordListener(context, bankname, username, tel, banknumber, editText, 0), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("confirmBeiBiPassword16");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /***
     * 余额提取
     *
     * @param context
     * @param balanceExtractionPostBean
     */
    public static void balanceExtraction(Context context, BalanceExtractionPostBean balanceExtractionPostBean) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.set_balance, PossObject.balanceExtraction(balanceExtractionPostBean), new ToastListener(context, "提取成功！", 0), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("balanceExtraction17");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 贝币赠与电话类型
     *
     * @param context
     * @param phoneTypePostBean
     * @param radioGroup
     * @param rb1
     * @param rb2
     * @param phone
     */
    public static void phoneType(Context context, PhoneTypePostBean phoneTypePostBean,
                                 RadioGroup radioGroup, RadioButton rb1, RadioButton rb2,
                                 EditText phone) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    HttpUrl.phone_type, PossObject.phoneType(phoneTypePostBean),
                    new PhoneTypeListener(context, radioGroup, rb1, rb2, phone), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("phoneType18");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /***
     * 贝币赠与里的获取余额
     *
     * @param context
     * @param storeIdPostBean
     * @param beibi
     * @param phone
     */
    public static void getGiveBalance(Context context, StoreIdPostBean storeIdPostBean,
                                      EditText beibi, EditText phone, String friendType,String beibiType) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    HttpUrl.beibi_balance, PossObject.getStoreId(storeIdPostBean),
                    new BalanceListener(context, beibi, 1, phone, friendType,beibiType,"j"), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("getGiveBalance19");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /***
     * 银贝币赠与里的获取余额
     *
     * @param context
     * @param storeIdPostBean
     * @param beibi
     * @param phone
     */
    public static void getYbeibiBalance(Context context, StoreIdPostBean storeIdPostBean,
                                        EditText beibi, EditText phone, String friendType,String beibiType) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    HttpUrl.usermessage,PossObject.getStoreId(storeIdPostBean),
                    new BalanceListener(context, beibi, 1, phone, friendType,beibiType,"y"),
                    new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("getYbeibiBalance");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    /**
     * 贝币赠与里确认贝币密码
     *
     * @param context
     * @param confirmBeiBiPasswordPostBean
     * @param beibi
     * @param phone
     * @param friendType
     */
    public static void confirmGiveBeiBiPassword(Context context, ConfirmBeiBiPasswordPostBean confirmBeiBiPasswordPostBean,
                                                EditText beibi, EditText phone, String friendType,String beibiType) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    HttpUrl.paypassword, PossObject.confirmBeiBiPassword(confirmBeiBiPasswordPostBean),
                    new ConfirmBeiBiPasswordListener(context, 1, beibi, phone, friendType,beibiType), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("confirmGiveBeiBiPassword20");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 贝币赠与
     *
     * @param context
     * @param beiBiGivePostBean
     */
    public static void beiBiGive(Context context, BeiBiGivePostBean beiBiGivePostBean) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    HttpUrl.beibi_gift, PossObject.beiBiGive(beiBiGivePostBean),
                    new ToastListener(context, "您已成功赠送好友！", 0), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("beiBiGive21");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /***
     * 获取三方支付的TN号
     *
     * @param context
     * @param getTnPostBean
     * @param type
     * @param mMode
     */
    public static void getTN(Context context, GetTnPostBean getTnPostBean, String type, String mMode) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.beibi_pay, PossObject.getTN(getTnPostBean), new GetTnListener(context, type, mMode), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("getTN22");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 金贝币记录列表
     * 使用
     * @param context
     * @param listPostBean
     * @param type
     * @param list
     * @param listView
     * @param swipeLayout
     * @param beiRecordAdapter
     * @param footView
     */
    public static void getBeiBiRecordList(Context context, ListPostBean listPostBean, int type, List<BeiRecordShowBean> list, ListView listView, RefreshLayout swipeLayout, BeiRecordAdapter beiRecordAdapter, View footView) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    HttpUrl.beibi_bill, PossObject.list(listPostBean),
                    new BeiBiRecordListener(context, type, list, listView,
                            swipeLayout, beiRecordAdapter, footView), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("getBeiBiRecordList23");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 贝币记录列表
     * 银贝币 使用记录
     * @param context
     * @param listPostBean
     * @param type
     * @param list
     * @param listView
     * @param swipeLayout
     * @param beiRecordAdapter
     * @param footView
     */
    public static void getYBeiBiUseRecordList(Context context, ListPostBean listPostBean, int type, List<BeiRecordShowBean> list, ListView listView, RefreshLayout swipeLayout, BeiRecordAdapter beiRecordAdapter, View footView) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    HttpUrl.sliverList, PossObject.list(listPostBean),
                    new BeiBiRecordListener(context, type, list, listView,
                            swipeLayout, beiRecordAdapter, footView), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("getBeiBiRecordList23");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 贝币记录列表
     *金贝币发放记录
     * @param context
     * @param listPostBean
     * @param type
     * @param list
     * @param listView
     * @param swipeLayout
     * @param beiBiGrantRecordAdapter
     * @param footView
     */
    public static void getBeiBiGrantRecordList(Context context, ListPostBean listPostBean, int type, List<BeiRecordShowBean> list, ListView listView,
                                               RefreshLayout swipeLayout,
                                               BeiBiGrantRecordAdapter beiBiGrantRecordAdapter, View footView) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.beibi_bill,
                    PossObject.list(listPostBean), new BeiBiGrantRecordListener
                    (context, type, list, listView, swipeLayout, beiBiGrantRecordAdapter, footView), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("getBeiBiRecordList23");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    /**
     * 贝币记录列表
     * 银贝币发放记录
     * @param context
     * @param listPostBean
     * @param type
     * @param list
     * @param listView
     * @param swipeLayout
     * @param beiBiGrantRecordAdapter
     * @param footView
     */
    public static void getYBeiBiGrantRecordList(Context context, ListPostBean listPostBean, int type, List<BeiRecordShowBean> list, ListView listView,
                                               RefreshLayout swipeLayout,
                                               BeiBiGrantRecordAdapter beiBiGrantRecordAdapter, View footView) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.sliverList,
                    PossObject.list(listPostBean), new BeiBiGrantRecordListener
                    (context, type, list, listView, swipeLayout, beiBiGrantRecordAdapter, footView), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("getBeiBiRecordList23");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    /**
     * 商家商品分类管理
     *
     * @param context
     * @param storeIdPostBean
     * @param listView
     */
    public static void classManagement(Context context, StoreIdPostBean storeIdPostBean, ListView listView, GoodsManagerClassificationAdapter goodsManagerClassificationAdapter, List<ClassManagementShowBean> listClassify) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.list_classify, PossObject.getStoreId(storeIdPostBean), new ClassifyManagementListener(context, listView, goodsManagerClassificationAdapter, listClassify, 0), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("classManagement24");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改商家商品信息
     *
     * @param context
     * @param merIdPostBean
     */
    public static void upGoodsDetail(Context context, MerIdPostBean merIdPostBean) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.mer_detail,
                    PossObject.getMerId(merIdPostBean), new GoodsDetailListener(context, merIdPostBean, 0), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("upGoodsDetail25");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取商家商品列表
     *
     * @param context
     * @param commodityManagementListPostBean
     * @param type
     * @param likeBeans
     * @param listView
     * @param commodityManagementListAdapter
     * @param swipeLayout
     * @param footView
     */
    public static void goodsList(Context context, CommodityManagementListPostBean commodityManagementListPostBean, int type, List<CommodityShowBean> likeBeans, ListView listView, CommodityManagementListAdapter commodityManagementListAdapter, RefreshLayout swipeLayout, View footView) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.classGoodsList, PossObject.commodityManagementList(commodityManagementListPostBean), new CommodityManagementListListener(context, type, likeBeans, listView, commodityManagementListAdapter, swipeLayout, footView), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("goodsList26");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /***
     * 收益管理数据显示
     *
     * @param context
     * @param storeIdPostBean
     * @param accumulatedIncomeMoney
     * @param estimatedEarningsMoney
     * @param recommendedNumber
     */
    public static void revenueManagement(Context context, StoreIdPostBean storeIdPostBean, TextView accumulatedIncomeMoney, TextView estimatedEarningsMoney, TextView recommendedNumber) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    HttpUrl.revenue_manage, PossObject.getStoreId(storeIdPostBean), new RevenueManagementListener(context, accumulatedIncomeMoney, estimatedEarningsMoney, recommendedNumber, 0), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("revenueManagement27");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 累计收益列表
     *
     * @param context
     * @param listPostBean
     * @param type
     * @param list
     * @param accumulatedIncomeAdapter
     * @param listView
     * @param swipeLayout
     * @param footView
     */
    public static void accumulatedIncomeList(Context context, ListPostBean listPostBean, int type, List<AccumulatedIncomeShowBean> list, AccumulatedIncomeAdapter accumulatedIncomeAdapter, ListView listView, RefreshLayout swipeLayout, View footView) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.RecommendedEarningsList, PossObject.list(listPostBean), new AccumulateIncomeListListener(context, type, list, accumulatedIncomeAdapter, listView, swipeLayout, footView), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("accumulatedIncomeList28");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /***
     * 确认订单中的默认地址
     *
     * @param context
     * @param storeIdPostBean
     * @param receiverTextView
     * @param genderTextView
     * @param contactTextView
     * @param areanameTextView
     * @param streetaddrTextView
     */
    public static void getDefaultAddress(Context context, StoreIdPostBean storeIdPostBean, TextView receiverTextView, TextView genderTextView, TextView contactTextView, TextView areanameTextView, TextView streetaddrTextView) {

        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    HttpUrl.default_address, PossObject.getStoreId(storeIdPostBean), new GetDefaultAddressListener(context, receiverTextView, genderTextView, contactTextView, areanameTextView, streetaddrTextView), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("getDefaultAddress29");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 提交订单
     *
     * @param context
     * @param toSubOrderPostBean
     * @param textViewTotal
     */
    public static void toSubOrder(Context context, ToSubOrderPostBean toSubOrderPostBean,
                                  TextView textViewTotal, String isSilver) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.add_order,
                    PossObject.toSubOrder(toSubOrderPostBean), new ToSubOrderListener
                    (context, textViewTotal,isSilver), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("toSubOrder30");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 企业需求获取验证码
     *
     * @param context
     * @param codePostBean
     * @param timerCount
     */
    public static void getCode(Context context, CodePostBean codePostBean, TimerCount timerCount) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.serviceGetCode,
                    PossObject.getCode(codePostBean, 0), new CodeListener(context, timerCount), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("getCode31");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 企业需求提交
     *
     * @param context
     * @param enterNeedPostBean
     * @param timerCount
     * @param timerHandler
     * @param phone
     * @param code
     * @param content
     * @param moreContent
     * @param getCode
     * @param checkImage
     * @param commit
     */
    public static void enterNeed(Context context, EnterNeedPostBean enterNeedPostBean, TimerCount timerCount, TimerHandler timerHandler, EditText phone, EditText code, EditText content, EditText moreContent, Button getCode, ImageView checkImage, Button commit) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.enterNeedCommit,
                    PossObject.enterNeed(enterNeedPostBean), new EnterNeedListener(context, timerCount, timerHandler, phone, code, content, moreContent, getCode, checkImage, commit), new ErrorListener(context, commit, 2));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("enterNeed32");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 企业需求详情
     *
     * @param context
     * @param imageView
     */
    public static void enterDetails(Context context, WebView imageView) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.enterDetails,
                    PossObject.Null(), new EnterDetailsListener(context, imageView), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("enterDetails33");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 预估收益详情里的收益管理
     *
     * @param context
     * @param storeIdPostBean
     * @param estimatedEarningsMoney
     */
    public static void revenueManagement(Context context, StoreIdPostBean storeIdPostBean, TextView estimatedEarningsMoney, int type) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.revenue_manage, PossObject.getStoreId(storeIdPostBean), new RevenueManagementListener(context, estimatedEarningsMoney, type), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("revenueManagement34");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 预估详情列表
     *
     * @param context
     * @param listPostBean
     * @param type
     * @param list
     * @param listHead
     * @param listView
     * @param estimatedEarningsDetailsAdapter
     * @param footView
     * @param swipeLayout
     */
    public static void estimatedEarningsDetails(Context context, ListPostBean listPostBean, int type, List<EstimatedEarningsDetailsShowBean> list, List<EstimatedEarningsDetailsShowBean> listHead, ListView listView, EstimatedEarningsDetailsAdapter estimatedEarningsDetailsAdapter, View footView, RefreshLayout swipeLayout) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.pre_revenue, PossObject.list(listPostBean), new EstimatedEarningsDetailsListener(context, type, list, listHead, listView, estimatedEarningsDetailsAdapter, footView, swipeLayout), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("estimatedEarningsDetails35");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传评论
     *
     * @param context
     * @param evaluatePostBean
     * @param progressDialog
     */
    public static void evaluate(Context context, EvaluatePostBean evaluatePostBean, ProgressDialog progressDialog) {
        final List<File> files = new ArrayList<>();
        final List<File> fileList = new ArrayList<>();
        for (int i = 0; i < MutualApplication.chooseOrderList.size(); i++) {
            for (int j = 0; j < MutualApplication.chooseOrderList.get(i).getFiles().size(); j++) {
                files.add(MutualApplication.chooseOrderList.get(i).getFiles().get(j));
            }
            for (int j = 0; j < MutualApplication.chooseOrderList.get(i).getFileList().size(); j++) {
                fileList.add(MutualApplication.chooseOrderList.get(i).getFileList().get(j));
            }
        }
        UpRequest upRequest = new UpRequest(HttpUrl.yes2,
                new EvaluaeListener(context, progressDialog, files, fileList),
                new ErrorListener(context, progressDialog, 1),
                "wimgfiles",
                files, PossObject.evaluate(evaluatePostBean));
        upRequest.setRetryPolicy(new DefaultRetryPolicy(500000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        upRequest.setTag("evaluate36");
        MutualApplication.getRequestQueue().add(upRequest);
    }

    /**
     * 忘记密码和忘记贝币密码获取验证码
     *
     * @param context
     * @param codePostBean
     * @param url
     * @param type
     * @param getVerificationCode
     * @param imageView
     * @param inputVerificationCode
     */
    public static void forgetPass(Context context, CodePostBean codePostBean, String url, int type, TextView getVerificationCode, ImageView imageView, EditText inputVerificationCode) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, PossObject.getCode(codePostBean, type), new ForgetCodeListener(context, getVerificationCode, imageView, inputVerificationCode), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("frogetPass37");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 忘记登录密码和忘记贝币密码
     *
     * @param context
     * @param forgetPostBean
     * @param url
     * @param type
     * @param text
     */
    public static void forget(Context context, ForgetPostBean forgetPostBean, String url, int type, String text) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, PossObject.forgetPass(forgetPostBean, type), new ToastListener(context, text, 0), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("forget38");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 互实攻略
     *
     * @param context
     * @param strategyText
     */
    public static void setStrategyText(Context context, TextView strategyText) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.comTel,
                    PossObject.strategy(), new StrategyListener(context, strategyText), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("setStrategyText39");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 检测当前版本
     *
     * @return
     */
    public static void getUpdateInfo(final Context context, final String getVersionName, int type) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.comTel, PossObject.getUpdateInfo(), new UpdateInfoListener(context, getVersionName, type), new ErrorListener(context, 1));
            jsonObjectRequest.setTag("getUpdateInfo40");
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(500000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**
     * 隐形登录
     *
     * @param context
     * @param loginPostBean
     * @param radioButtonTwo
     */
    public static void invisibleLogin(Context context, LoginPostBean loginPostBean, RadioButton radioButtonTwo) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.login, PossObject.login(loginPostBean), new LoginListener(context, loginPostBean, 1, radioButtonTwo), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("invisibleLogin41");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /***
     * 添加商品中的商品管理
     *
     * @param context
     * @param storeIdPostBean
     * @param listView
     * @param list
     */
    public static void classManagement(Context context, StoreIdPostBean storeIdPostBean, ListView listView, List<PopupMenuShowBean> list) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.list_classify, PossObject.getStoreId(storeIdPostBean), new ClassifyManagementListener(context, 1, listView, list), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("classManagement42");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加商品
     *
     * @param context
     * @param addGoodsPossBean
     * @param progressDialog
     * @param toFileList
     * @param fileList
     */
    public static void addGoods(Context context, AddGoodsPossBean addGoodsPossBean, ProgressDialog progressDialog, List<File> toFileList, List<File> fileList) {
        UpRequest upRequest = new UpRequest(HttpUrl.add_mer, new AddGoodsListener(context, progressDialog, toFileList, "上传成功！"), new ErrorListener(context, progressDialog, 0), "wimgfiles", fileList, PossObject.addGoods(addGoodsPossBean));
        upRequest.setRetryPolicy(new DefaultRetryPolicy(500000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        upRequest.setTag("addGoods43");
        MutualApplication.getRequestQueue().add(upRequest);
    }

    /**
     * 同意退款
     *
     * @param context
     * @param ordno
     * @param list
     */
    public static void agree(Context context, String ordno, List<MerchantsOrderShowBean> list) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("ordno", ordno);
            JSONArray jsonArray = PossObject.agreeList(list);
            obj.put("merList", jsonArray);
            if (jsonArray.length() == 0) {
                CustomToast.getInstance(context).setMessage("请选择要同意的商品!");
            } else {
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.POST, HttpUrl.agree, obj, new ToastListener(context, "已同意", 0), new ErrorListener(context, 1));
                jsonObjectRequest.setTag("agree44");
                jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(500000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                MutualApplication.getRequestQueue().add(jsonObjectRequest);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 拒绝退款
     *
     * @param context
     * @param ordno
     * @param list
     */
    public static void refuse(Context context, String ordno, List<MerchantsOrderShowBean> list) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("ordno", ordno);
            JSONArray jsonArray = PossObject.refuseList(list);
            obj.put("merList", jsonArray);
            if (jsonArray.length() == 0) {
                CustomToast.getInstance(context).setMessage("请选择要拒接的商品!");
            } else {
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.POST, HttpUrl.refuse, obj, new ToastListener(context, "已拒绝", 0), new ErrorListener(context, 1));
                jsonObjectRequest.setTag("refuse45");
                jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(500000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                MutualApplication.getRequestQueue().add(jsonObjectRequest);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发货
     *
     * @param context
     * @param sendPostBean
     */
    public static void send(Context context, SendPostBean sendPostBean) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, sendPostBean.getUrl(), PossObject.send(sendPostBean), new ToastListener(context, "已发货", 0), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("send46");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 商家订单详情
     *
     * @param context
     * @param sendPostBean
     */
    public static void merchantsOrderDetail(Context context, SendPostBean sendPostBean, TextView buyerName, TextView buyerGender, TextView buyerPhone
            , TextView buyerDetailAddress, TextView buyerAddress, TextView orderTime, TextView orderId, LinearLayout peopleLinearLayout, TextView orderMoney, TextView payType, TextView LeavingMessage, TextView sendFee
            , TextView returnBeiBi, LinearLayout coupon_line, LinearLayout amountPaidLine, View amountPaid_line, TextView coupon_money
            , TextView amountPaid, LinearLayout distLinearLayout, TextView deliveryClerkPhone, ImageView status, TextView invoice, LinearLayout linearLayoutBottom
            , TextView Cancel, TextView Confirm, LinearLayout linearLayoutPeople, List<MerchantsOrderShowBean> list, ListView listView, CheckBox checkAll
            , LinearLayout checkAllLinearLayout, LinearLayout linearLayoutPayType) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.merchans_order_detail,
                    PossObject.merchantsOrderDetail(sendPostBean),
                    new MerchantsOrderDetailListener(context, buyerName, buyerGender, buyerPhone, buyerDetailAddress, buyerAddress, orderTime, orderId,
                            peopleLinearLayout, orderMoney, payType, LeavingMessage, sendFee, returnBeiBi, coupon_line, amountPaidLine,
                            amountPaid_line, coupon_money, amountPaid, distLinearLayout, deliveryClerkPhone, status, invoice,
                            linearLayoutBottom, Cancel, Confirm, linearLayoutPeople, list, listView, checkAll, checkAllLinearLayout,
                            linearLayoutPayType), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("merchantsOrderDetail47");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改商家商品信息
     *
     * @param context
     * @param merIdPostBean
     */
    public static void upGoodsDetail(Context context, MerIdPostBean merIdPostBean, ImageLoader imageLoader, DisplayImageOptions options, List<BitmapShowBean> bitmapShowBeanList
            , ImageView imageView, MyGridView gridView, UpdateImageAdapter updateImageAdapter, ToggleButton toggleButton, View beiBiLine, EditText number, TextView commodityNameShow
            , TextView detailShow, TextView classifyShow, EditText price, EditText beiBi) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    HttpUrl.mer_detail, PossObject.getMerId(merIdPostBean), new GoodsDetailListener(context, 1, imageLoader, options, bitmapShowBeanList, imageView, gridView, updateImageAdapter, toggleButton, beiBiLine, number, commodityNameShow, detailShow, classifyShow, price, beiBi), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("upGoodsDetail48");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改商品
     *
     * @param context
     * @param addGoodsPossBean
     * @param progressDialog
     * @param toFileList
     * @param fileList
     */
    public static void updateGoods(Context context, AddGoodsPossBean addGoodsPossBean, ProgressDialog progressDialog, List<File> toFileList, List<File> fileList) {
        UpRequest upRequest = new UpRequest(HttpUrl.mer_detail_img, new AddGoodsListener(context, progressDialog, toFileList, "修改成功！"), new ErrorListener(context, progressDialog, 0), "wimgfiles", fileList, PossObject.updateGoods(addGoodsPossBean));
        upRequest.setRetryPolicy(new DefaultRetryPolicy(500000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        upRequest.setTag("updateGoods49");
        MutualApplication.getRequestQueue().add(upRequest);
    }

    /**
     * 消息详情
     *
     * @param context
     * @param messageDetailPostBean
     * @param textView
     */
    public static void messageDetail(Context context, MessageDetailPostBean messageDetailPostBean, TextView textView) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.myMessageContest, PossObject.messageDetail(messageDetailPostBean), new MessageDetailListener(context, textView), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("messageDetail50");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 月收益列表
     *
     * @param context
     * @param listPostBean
     * @param type
     * @param list
     * @param listView
     * @param monthIncomeBaseAdapter
     * @param swipeLayout
     * @param footView
     */
    public static void monthIncome(Context context, ListPostBean listPostBean, int type, List<MonthIncomeShowBean> list, ListView listView, MonthIncomeBaseAdapter monthIncomeBaseAdapter, RefreshLayout swipeLayout, View footView) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.monthIncomeList, PossObject.list(listPostBean), new MonthIncomeListener(context, type, list, listView, monthIncomeBaseAdapter, swipeLayout, footView), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("monthIncome51");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 消息列表
     *
     * @param context
     * @param listPostBean
     * @param type
     * @param list
     * @param listView
     * @param myMessageAdapter
     * @param swipeLayout
     * @param footView
     */
    public static void myMessageList(Context context, ListPostBean listPostBean, int type, List<MessageShowBean> list, ListView listView, MyMessageAdapter myMessageAdapter, RefreshLayout swipeLayout, View footView) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.myMessage,
                    PossObject.list(listPostBean), new MyMessageListListener(context, type, list, listView, swipeLayout, myMessageAdapter, footView), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("myMessageList52");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 公告详情
     *
     * @param context
     * @param noticeDetailsPostBean
     * @param noticeId
     * @param imageView
     * @param title
     * @param commit
     */
    public static void noticeDetails(Context context, NoticeDetailsPostBean noticeDetailsPostBean, String noticeId, WebView imageView, TextView title, Button commit) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.enterPage, PossObject.noticeDetails(noticeDetailsPostBean), new NoticeDetailsListener(context, noticeId, imageView, title, commit), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("noticeDetails53");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 公告报名
     *
     * @param context
     * @param noticeDetailsPostBean
     * @param commit
     */
    public static void noticeJoin(Context context, NoticeDetailsPostBean noticeDetailsPostBean, Button commit) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.wantJoin, PossObject.noticeDetails(noticeDetailsPostBean), new NoticeJoinListener(context, commit), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("noticeJoin54");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改登录密码
     *
     * @param context
     * @param updatePasswordPostBean
     */
    public static void updateLoginPassword(Context context, UpdatePasswordPostBean updatePasswordPostBean) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.updatePassword, PossObject.updateLoginPasword(updatePasswordPostBean), new ToastListener(context, "修改成功！请重新登录！", 0), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("updateLoginPassword55");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改贝币支付密码
     *
     * @param context
     * @param updatePasswordPostBean
     */
    public static void updateBeiBiPassword(Context context, UpdatePasswordPostBean updatePasswordPostBean) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.updatebeibiPassword, PossObject.updateBeiBiPasword(updatePasswordPostBean), new ToastListener(context, "修改成功！", 0), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("updateBeiBiPassword56");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 原产地猜你喜欢
     *
     * @param context
     * @param commodityShowBeans
     * @param listView
     */
    public static void guessYouLike(Context context, List<CommodityShowBean> commodityShowBeans, ListView listView) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.homepage_guess, PossObject.Null(), new GuessYouLikeListener(context, commodityShowBeans, listView), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("guessYouLike57");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 原产地首页分类图标
     *
     * @param context
     * @param storeIdPostBean
     * @param anim
     * @param gridView
     */
    public static void originalClassify(Context context, StoreIdPostBean storeIdPostBean, ImageView anim, MyGridView gridView) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.original_home, PossObject.getStoreId(storeIdPostBean), new OriginalClassifyListener(context, anim, gridView), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("originalClassify58");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 跳转原产地商品详情
     *
     * @param context
     * @param merIdPostBean
     * @param list
     * @param position
     */
    public static void toCommodityDetails(Context context, MerIdPostBean merIdPostBean, List<MerchantsOrderShowBean> list, int position) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.goodsDetail_up,
                    PossObject.getMerId(merIdPostBean), new ToCommodityDetailsListener(context, list, position), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("toCommodityDetails59");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 原产地取消订单
     *
     * @param context
     * @param sendPostBean
     */
    public static void cancelOrder(Context context, SendPostBean sendPostBean) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.close, PossObject.merchantsOrderDetail(sendPostBean), new ToastListener(context, "取消成功!", 2), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("cancelOrder60");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 原产地订单申请退款
     *
     * @param context
     * @param applyForRefundPostBean
     */
    public static void applyForRefund(Context context, ApplyForRefundPostBean applyForRefundPostBean) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.no, PossObject.applyForRefund(applyForRefundPostBean), new ToastListener(context, "申请退款成功!", 0), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("applyForRefund61");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 原产地订单详情
     *
     * @param context
     * @param sendPostBean
     * @param list
     * @param buyerName
     * @param buyerGender
     * @param buyerPhone
     * @param buyerAddress
     * @param buyerDetailAddress
     * @param orderTime
     * @param orderId
     * @param money
     * @param payName
     * @param linearLayoutAll
     * @param linearLayoutBottom
     * @param linearLayoutPaytype
     * @param checkBox
     * @param listView
     * @param Cancel
     * @param Confirm
     */
    public static void originalOrderDetail(Context context, SendPostBean sendPostBean, List<MerchantsOrderShowBean> list, TextView buyerName, TextView buyerGender, TextView buyerPhone
            , TextView buyerAddress, TextView buyerDetailAddress, TextView orderTime, TextView orderId, TextView money, TextView payName, LinearLayout linearLayoutAll
            , LinearLayout linearLayoutBottom, LinearLayout linearLayoutPaytype, CheckBox checkBox, ListView listView, TextView Cancel, TextView Confirm, LinearLayout discountLinearLayout, TextView orderMoneyTextView
            , TextView discountMoney) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.POST, HttpUrl.getFinishedOriginOrder, PossObject.merchantsOrderDetail(sendPostBean), new OriginalOrderDetailListener(context, list, buyerName, buyerGender, buyerPhone, buyerAddress
                    , buyerDetailAddress, orderTime, orderId, money, payName, linearLayoutAll, linearLayoutBottom, linearLayoutPaytype, checkBox, listView, Cancel, Confirm, discountLinearLayout, orderMoneyTextView, discountMoney), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("OriginalOrderDetail62");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 热搜关键词
     *
     * @param context
     * @param list
     * @param hotGrid
     * @param listView
     * @param adapter
     */
    public static void toGetHotSearch(Context context, List<String> list, MyGridView hotGrid, MyGridView listView, OriginalSearchGridAdapter adapter) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.original_search,
                    PossObject.toGetHotSearch(), new ToGetHotSearchListener(context, list, hotGrid, listView, adapter), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("toGetHotSearch63");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 原产地商品搜索一级菜单
     *
     * @param context
     * @param menuPostBean
     * @param listOne
     */
    public static void searchOneMenu(Context context, MenuPostBean menuPostBean, List<PopupMenuShowBean> listOne) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.original_menu_one, PossObject.searchOneMenu(menuPostBean), new SearchMenuListener(context, 0, listOne), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("searchOneMenu64");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 原产地商品搜索二级菜单
     *
     * @param context
     * @param menuPostBean
     * @param listTwo
     * @param list_menu2
     * @param secondMenuAdapter
     */
    public static void searchTwoMenu(Context context, MenuPostBean menuPostBean, List<PopupMenuShowBean> listTwo, ListView list_menu2, MenuAdapter secondMenuAdapter) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.original_menu_two, PossObject.searchTwoMenu(menuPostBean), new SearchMenuListener(context, 1, listTwo, list_menu2, secondMenuAdapter), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("searchTwoMenu65");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 原厂地商品搜索结果列表
     *
     * @param context
     * @param originalSearchResultPostBean
     * @param type
     * @param commodityShowBeanList
     * @param aftSearchListView
     * @param originalHomeYouLikeListAdapter
     * @param swipeLayout
     * @param footView
     */
    public static void searchResultList(Context context, OriginalSearchResultPostBean originalSearchResultPostBean, int type, List<CommodityShowBean> commodityShowBeanList, ListView aftSearchListView, OriginalHomeYouLikeListAdapter originalHomeYouLikeListAdapter, RefreshLayout swipeLayout, View footView) {

        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    HttpUrl.original_second_page, PossObject.searchResultList(originalSearchResultPostBean),
                    new OriginalSearchResultListener(context, type, commodityShowBeanList, aftSearchListView, originalHomeYouLikeListAdapter, swipeLayout, footView), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("searchResultList66");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 原产地商品一级菜单
     *
     * @param context
     * @param menuPostBean
     * @param listOne
     */
    public static void originalOneMenu(Context context, MenuPostBean menuPostBean, List<PopupMenuShowBean> listOne, String url) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, PossObject.originalSecondPage(menuPostBean), new SearchMenuListener(context, 0, listOne), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("originalOneMenu67");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 原产地商品二级菜单
     *
     * @param context
     * @param menuPostBean
     * @param listTwo
     * @param list_menu2
     * @param secondMenuAdapter
     */
    public static void originalTwoMenu(Context context, MenuPostBean menuPostBean, List<PopupMenuShowBean> listTwo, ListView list_menu2, MenuAdapter secondMenuAdapter, String url) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, PossObject.originalSecondPage(menuPostBean), new SearchMenuListener(context, 1, listTwo, list_menu2, secondMenuAdapter), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("originalTwoMenu68");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 原产地二级分类下的商品列表
     *
     * @param context
     * @param originalSecondPagePostBean
     * @param type
     * @param commodityShowBeanList
     * @param aftSearchListView
     * @param originalHomeYouLikeListAdapter
     * @param swipeLayout
     * @param footView
     */
    public static void originalSecondPage(Context context, OriginalSecondPagePostBean originalSecondPagePostBean, int type, List<CommodityShowBean> commodityShowBeanList, ListView aftSearchListView, OriginalHomeYouLikeListAdapter originalHomeYouLikeListAdapter, RefreshLayout swipeLayout, View footView) {

        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.original_second_page, PossObject.originalSecondPage(originalSecondPagePostBean), new OriginalSearchResultListener(context, type, commodityShowBeanList, aftSearchListView, originalHomeYouLikeListAdapter, swipeLayout, footView), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("originalSecondPage69");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 订单支付
     *
     * @param context
     * @param payOrderPostBean
     * @param type
     * @param merName
     * @param merDescribe
     * @param money
     */
    public static void payOrder(Context context, PayOrderPostBean payOrderPostBean, String type, String merName, String merDescribe, String money) {

        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    HttpUrl.order_pay, PossObject.payOrder(payOrderPostBean),
                    new PayOrderListener(context, type, merName, merDescribe, money), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("payOrder70");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 贝币支付查询余额
     *
     * @param context
     * @param storeIdPostBean
     * @param money
     * @param orderNo
     */
    public static void payBeiBi(Context context, StoreIdPostBean storeIdPostBean,
                                String money, String orderNo,String isSilver) {

        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    HttpUrl.beibi_balance, PossObject.getStoreId(storeIdPostBean),
                    new PayToBeiBiListener(context, money, orderNo,isSilver), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("payBeiBi71");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 确认支付密码
     *
     * @param context
     * @param confirmBeiBiPasswordPostBean
     * @param money
     * @param orderNo
     */
    public static void confirmPayOrderPassword(Context context,
                                               ConfirmBeiBiPasswordPostBean confirmBeiBiPasswordPostBean,
                                               String money, String orderNo
    ,String isSilver) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    HttpUrl.paypassword, PossObject.confirmBeiBiPassword(confirmBeiBiPasswordPostBean),
                    new ConfirmBeiBiPasswordListener(context, money, orderNo, 2,isSilver), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("confirmPayOrderPassword72");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 订单支付完成
     *
     * @param context
     * @param orderFinishPostBean
     */
    public static void payBeiBiFinish(Context context, OrderFinishPostBean orderFinishPostBean) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    HttpUrl.beiBiPayFinish, PossObject.payBeiBiFinish(orderFinishPostBean),
                    new ToastListener(context, "支付成功！", 0), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("payBeiBiFinish73");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 消息清空
     *
     * @param context
     * @param storeIdPostBean
     */
    public static void clearMessage(Context context, StoreIdPostBean storeIdPostBean, List<MessageShowBean> list, MyMessageAdapter myMessageAdapter) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.clearMessage, PossObject.getStoreId(storeIdPostBean), new ToastListener(context, "已清空已读消息！", 1, list, myMessageAdapter), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("clearMessage74");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除消息
     *
     * @param context
     * @param messageDetailPostBean
     * @param list
     * @param myMessageAdapter
     * @param position
     */
    public static void deleteMessage(Context context, MessageDetailPostBean messageDetailPostBean, List<MessageShowBean> list, MyMessageAdapter myMessageAdapter, int position) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.deleteMessage, PossObject.messageDetail(messageDetailPostBean), new DeleteMessageListener(context, position, list, myMessageAdapter), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("deleteMessage75");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除招聘信息
     *
     * @param context
     * @param deleteRecruitmentPostBean
     * @param list
     * @param position
     * @param adapter
     */
    public static void deleteRecruitment(Context context, DeleteRecruitmentPostBean deleteRecruitmentPostBean, List<RecruitmentManagementShowBean> list, int position, RecruitmentManagementAdapter adapter) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.DeleteRecruitment, PossObject.deleteRecruitment(deleteRecruitmentPostBean), new DeleteRecruitmentListener(context, list, position, adapter), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("deleteRecruitment76");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 招聘信息列表
     *
     * @param context
     * @param listPostBean
     * @param type
     * @param list
     * @param listView
     * @param adapter
     * @param swipeLayout
     * @param footView
     */
    public static void recruitmentList(Context context, ListPostBean listPostBean, int type, List<RecruitmentManagementShowBean> list, ListView listView, RecruitmentManagementAdapter adapter, RefreshLayout swipeLayout, View footView) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.RecruitmentManagement, PossObject.list(listPostBean), new RecruitmentListListener(context, type, list, listView, adapter, swipeLayout, footView), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("recruitmentList77");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 服务列表
     *
     * @param context
     * @param servicePostBean
     * @param type
     * @param list
     * @param listView
     * @param adapter
     * @param swipeLayout
     * @param footView
     */
    public static void serviceList(Context context, ServicePostBean servicePostBean, int type, List<ServiceShowBean> list, ListView listView, ServiceBaseAdapter adapter, RefreshLayout swipeLayout, View footView) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.serviceList,
                    PossObject.serviceList(servicePostBean), new ServiceListener(context, type, list, listView, swipeLayout, footView, adapter), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("serviceList78");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 服务获取验证码
     *
     * @param context
     * @param codePostBean
     * @param timerCount
     * @param getCode
     */
    public static void serviceCode(Context context, CodePostBean codePostBean, TimerCount timerCount, Button getCode) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.serviceGetCode,
                    PossObject.serviceCode(codePostBean), new ServiceDetailCodeListener(context, timerCount, getCode), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("serviceCode79");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 服务详情
     *
     * @param context
     * @param serveIdPostBean
     * @param webView
     */
    public static void serviceDetail(Context context, ServeIdPostBean serveIdPostBean, WebView webView) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.serviceDetails,
                    PossObject.serviceId(serveIdPostBean), new ServiceDetailListener(context, webView), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("serviceDetail80");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 服务提交
     *
     * @param context
     * @param serviceCommitPostBean
     * @param timerCount
     * @param timerHandler
     * @param phone
     * @param code
     * @param getCode
     * @param checkImage
     * @param commit
     */
    public static void serviceCommit(Context context, ServiceCommitPostBean serviceCommitPostBean, TimerCount timerCount, TimerHandler timerHandler, EditText phone, EditText code, Button getCode, ImageView checkImage, Button commit) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.enterNeedCommit,
                    PossObject.serviceCommit(serviceCommitPostBean), new ServiceCommitListener(context, timerCount, timerHandler, phone, code, getCode, checkImage, commit), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("serviceCommit81");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 购物车列表
     *
     * @param context
     * @param storeIdPostBean
     * @param shoppingCarList
     * @param tempShoppingCarShowBeans
     * @param linearCheckAll
     * @param listView
     * @param shoppingCarAdapter
     * @param checkAll
     * @param textViewTotal
     */
    public static void shopCarList(Context context, StoreIdPostBean storeIdPostBean, List<ShoppingCarShowBean> shoppingCarList, List<TempShoppingCarShowBean> tempShoppingCarShowBeans
            , LinearLayout linearCheckAll, ListView listView, ShoppingCarAdapter shoppingCarAdapter, CheckBox checkAll, TextView textViewTotal) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    HttpUrl.shoppingCar_item, PossObject.getStoreId(storeIdPostBean), new ShoppingCarListener(context, shoppingCarList, tempShoppingCarShowBeans, linearCheckAll, listView, shoppingCarAdapter, checkAll, textViewTotal), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("shopCarList82");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对账单列表
     *
     * @param context
     * @param listPostBean
     * @param type
     * @param listView
     * @param list
     * @param statementAccountBaseAdapter
     * @param swipeLayout
     * @param footView
     */
    public static void statementAccountList(Context context, ListPostBean listPostBean, int type, ListView listView, List<StatementAccountShowBean> list
            , StatementAccountBaseAdapter statementAccountBaseAdapter, RefreshLayout swipeLayout, View footView) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.activityList, PossObject.list(listPostBean), new StatementAccountListener(context, type, listView, list, statementAccountBaseAdapter, swipeLayout, footView), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("statementAccountList83");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取商家信息
     *
     * @param context
     * @param storeIdPostBean
     * @param deliveryRadiusStart
     * @param radius
     * @param deliveryRadius
     * @param reserve
     * @param button_show_status
     * @param textViewStoreIntroduction
     * @param storePhone
     * @param storeName
     * @param textViewBusinessTimeBegin
     * @param textViewBusinessTimeEnd
     * @param invoice
     * @param payAfter
     * @param out
     * @param cashOnDelivery
     * @param textViewOutSideStartPrice
     * @param textViewOutSidePrice
     * @param textViewMinFree
     * @param reserveLinearLayout
     * @param reserveTime
     * @param textViewReserveTime
     * @param scrollView
     * @param anim
     */
    public static void getStoreMessage(Context context, StoreIdPostBean storeIdPostBean, TextView deliveryRadiusStart, TextView radius, LinearLayout deliveryRadius, ToggleButton reserve
            , ToggleButton button_show_status, TextView textViewStoreIntroduction, EditText storePhone, EditText storeName, TextView textViewBusinessTimeBegin
            , TextView textViewBusinessTimeEnd, ToggleButton invoice, ToggleButton payAfter, LinearLayout out, LinearLayout cashOnDelivery
            , EditText textViewOutSideStartPrice, EditText textViewOutSidePrice, EditText textViewMinFree, LinearLayout reserveLinearLayout
            , LinearLayout reserveTime, TextView textViewReserveTime, ScrollView scrollView, ImageView anim) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    HttpUrl.usermessage, PossObject.getStoreId(storeIdPostBean), new StoreGetMessageListener(context, deliveryRadiusStart, radius, deliveryRadius, reserve
                    , button_show_status, textViewStoreIntroduction, storePhone, storeName, textViewBusinessTimeBegin
                    , textViewBusinessTimeEnd, invoice, payAfter, out, cashOnDelivery, textViewOutSideStartPrice, textViewOutSidePrice, textViewMinFree, reserveLinearLayout
                    , reserveTime, textViewReserveTime, scrollView, anim), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("getStoreMessage84");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改商家信息
     *
     * @param context
     * @param storeSetMessagePostBean
     */
    public static void setStoreMessage(Context context, StoreSetMessagePostBean storeSetMessagePostBean) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.setmessage, PossObject.setStoreMessage(storeSetMessagePostBean), new ToastListener(context, "修改成功！", 0), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("setStoreMessage85");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除商品
     *
     * @param context
     * @param deleteMerchandisePostBean
     * @param size
     */
    public static void deleteMerchandise(Context context, DeleteMerchandisePostBean deleteMerchandisePostBean, String size) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.delete_mer, PossObject.deleteMerchandise(deleteMerchandisePostBean), new DeleteMerchandiseListener(context, size, 0), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("deleteMerchandise86");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上下架
     *
     * @param context
     * @param deleteMerchandisePostBean
     * @param size
     */
    public static void upOrDown(Context context, DeleteMerchandisePostBean deleteMerchandisePostBean, String size) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.up_down_mer, PossObject.upOrDown(deleteMerchandisePostBean), new DeleteMerchandiseListener(context, size, 1), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("upOrDown87");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除分类
     *
     * @param context
     * @param commodityManagementListPostBean
     */
    public static void deleteClassify(Context context, CommodityManagementListPostBean commodityManagementListPostBean) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.delete_classify, PossObject.deleteClassify(commodityManagementListPostBean), new DeleteClassifyListener(context), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("deleteClassify88");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 微信回调
     *
     * @param context
     */
    public static void WXPayEntry(Context context) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.wechat_ok, PossObject.WXPayEntry(), new WXPayEntryListener(context), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("WXPayEntry89");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 商家订单列表中的发货
     *
     * @param context
     * @param sendPostBean
     * @param list
     * @param sellOrderAdapter
     * @param position
     */
    public static void listSend(Context context, SendPostBean sendPostBean, List<OrderShowBean> list, SellOrderAdapter sellOrderAdapter, int position) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, sendPostBean.getUrl(), PossObject.send(sendPostBean), new SendListener(context, list, sellOrderAdapter, position), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("listSend90");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 购物车数量变更
     *
     * @param context
     * @param changeCarCountPostBean
     */
    public static void changeCarCountToServer(Context context, ChangeCarCountPostBean changeCarCountPostBean) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.changeCarCount, PossObject.changeCarCount(changeCarCountPostBean), new WXPayEntryListener(context), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("changeCarCountToServer91");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 购物车数量变更
     *
     * @param context
     * @param changeCarCountPostBean
     */
    public static void changeCarCountToServer(Context context, ChangeCarCountPostBean changeCarCountPostBean, int x, List<ShoppingCarShowBean> list, int position
            , List<TempShoppingCarShowBean> tempShoppingCarShowBeans, ShoppingCarAdapter shoppingCarAdapter, PopupWindow popupWindow
            , TextView textViewTotal) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.changeCarCount, PossObject.changeCarCount(changeCarCountPostBean), new ChangeCarCountListener(context, x, list, position
                    , tempShoppingCarShowBeans, shoppingCarAdapter, popupWindow, textViewTotal), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("changeCarCountToServer92");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加商品分类
     *
     * @param context
     * @param classifyPostBean
     * @param addClassifyDialog
     */
    public static void addClassify(Context context, ClassifyPostBean classifyPostBean, AddClassifyDialog addClassifyDialog) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.add_classify, PossObject.addClassify(classifyPostBean), new ClassifyListener(context, 1, addClassifyDialog), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("addClassify93");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改商品分类
     *
     * @param context
     * @param classifyPostBean
     * @param addClassifyDialog
     */
    public static void updateClassify(Context context, ClassifyPostBean classifyPostBean, AddClassifyDialog addClassifyDialog) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.update_classify, PossObject.updateClassify(classifyPostBean), new ClassifyListener(context, 2, addClassifyDialog), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("updateClassify94");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取商品分类
     *
     * @param context
     * @param classifyPostBean
     * @param list
     * @param goodsManagerClassificationAdapter
     * @param addClassifyDialog
     */
    public static void getClassify(Context context, ClassifyPostBean classifyPostBean, List<ClassManagementShowBean> list
            , GoodsManagerClassificationAdapter goodsManagerClassificationAdapter, AddClassifyDialog addClassifyDialog) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.list_classify, PossObject.getClassify(classifyPostBean), new ClassifyListener(context, 3, list, goodsManagerClassificationAdapter, addClassifyDialog), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("getClassify95");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 评论列表
     *
     * @param context
     * @param commonPostBean
     * @param type
     * @param list
     * @param list1
     * @param listView
     * @param commonAdapter
     * @param swipeLayout
     * @param footView
     */
    public static void getCommonList(Context context, CommonPostBean commonPostBean, int type, List<GoodsDetailShowBean> list, List<ImageShowBean> list1, ListView listView
            , CommonAdapter commonAdapter, RefreshLayout swipeLayout, View footView, RefreshLayout.OnLoadListener onLoadListener) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    HttpUrl.pingjia, PossObject.getCommonList(commonPostBean),
                    new CommonListener(context, type, list, list1, listView, commonAdapter, swipeLayout, footView, onLoadListener), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("getCommonList96");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 评论个数
     *
     * @param context
     * @param merIdPostBean
     * @param goodReputationBtn
     * @param allBtn
     * @param middleEvaluationBtn
     * @param negativeCommentBtn
     * @param makeBlueprintBtn
     */
    public static void toGetGoodsDetailUp(Context context, MerIdPostBean merIdPostBean, Button goodReputationBtn, Button allBtn, Button middleEvaluationBtn, Button negativeCommentBtn
            , Button makeBlueprintBtn) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    HttpUrl.goodsDetail_up, PossObject.getMerId(merIdPostBean), new CommonNumberListener(context, goodReputationBtn, allBtn, middleEvaluationBtn, negativeCommentBtn, makeBlueprintBtn), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("toGetGoodsDetailUp97");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 多规格添加购物车
     *
     * @param context
     * @param changeCarCountPostBean
     * @param modeList
     * @param modeListShowBeanList
     * @param chooseText
     * @param chooseTextTwo
     * @param chooseTextThree
     * @param chooseTextFour
     * @param chooseTextFive
     * @param chooseTextSix
     */
    public static void addShopCar(Context context, ChangeCarCountPostBean changeCarCountPostBean, List<ModeShowBean> modeList, List<ModeListShowBean> modeListShowBeanList, String chooseText,
                                  String chooseTextTwo, String chooseTextThree, String chooseTextFour, String chooseTextFive, String chooseTextSix) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.changeCarCount, PossObject.addShopCar(changeCarCountPostBean, modeList, modeListShowBeanList, chooseText,
                    chooseTextTwo, chooseTextThree, chooseTextFour, chooseTextFive, chooseTextSix), new AddShopCarListener(context), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("addShopCar98");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 普通商品添加购物车
     *
     * @param context
     * @param changeCarCountPostBean
     */
    public static void addShopCar(Context context, ChangeCarCountPostBean changeCarCountPostBean) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.changeCarCount,
                    PossObject.addShopCar(changeCarCountPostBean), new AddShopCarListener(context), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("addShopCar99");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 购物车商品数量
     *
     * @param context
     * @param storeIdPostBean
     * @param textViewDotCar
     */
    public static void getShoppingCarCount(Context context, StoreIdPostBean storeIdPostBean, TextView textViewDotCar) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    HttpUrl.shoppingCarCount, PossObject.getStoreId(storeIdPostBean), new ShoppingCarCountListener(context, textViewDotCar), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("getShoppingCarCount100");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取原产地商品详情
     *
     * @param context
     * @param merIdPostBean
     * @param shopName
     * @param goodsDetail_monNo
     * @param goodsDetail_storeNo
     * @param textView_sale_price
     * @param goodsDetail_info
     * @param listCarouselShowBean
//     * @param viewPage
//     * @param listView
     * @param listImage
     * @param noHave
     */
    public static void getGoodsDetail(Context context, MerIdPostBean merIdPostBean, TextView shopName, TextView goodsDetail_monNo, TextView goodsDetail_storeNo, TextView textView_sale_price, TextView goodsDetail_info, List<CarouselShowBean> listCarouselShowBean
            , Banner banner,
                                      List<String> listImage, TextView noHave,
                                      ImageView anim,QuickAdapter<String> imgAdapter
            ,TextView evaluateAllNumTv,
                                      LinearLayout haveEvaluateLine,CircularImage circularImage
            ,TextView nicknameTv,TextView contentTv,TextView timeTv,RatingBar ratingBar
    ,TextView yBeiBiTv, LinearLayout pjLine,
                                               ImageView pjIconOne,
                                               ImageView pjIconTwo,
                                               ImageView pjIconThree) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.POST, HttpUrl.goodsDetail_up,
                    PossObject.getMerId(merIdPostBean),
                    new GetGoodsDetailListener(context, shopName,
                            goodsDetail_monNo, goodsDetail_storeNo, textView_sale_price, goodsDetail_info, listCarouselShowBean
                    , banner,  listImage, noHave, anim,imgAdapter,evaluateAllNumTv,haveEvaluateLine
            ,circularImage,nicknameTv,contentTv,timeTv,ratingBar,yBeiBiTv,pjLine,pjIconOne,pjIconTwo,pjIconThree), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("getGoodsDetail101");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取规格列表
     *
     * @param context
     * @param merIdPostBean
     * @param modeLineOne
     * @param modeLineTwo
     * @param modeLineThree
     * @param modeLineFour
     * @param modeLineFive
     * @param modeLineSix
     * @param modeList
     * @param standardOne
     * @param standardTwo
     * @param standardThree
     * @param standardFour
     * @param standardFive
     * @param standardSix
     * @param modeListShowBeanList
     * @param viewTexts
     * @param viewTextTwo
     * @param viewTextThree
     * @param viewTextFour
     * @param viewTextFive
     * @param viewTextSix
     * @param goodsViewGroupOne
     * @param goodsViewGroupTwo
     * @param goodsViewGroupThree
     * @param goodsViewGroupFour
     * @param goodsViewGroupFive
     * @param goodsViewGroupSix
     */
    public static void getModeList(Context context, MerIdPostBean merIdPostBean, LinearLayout modeLineOne, LinearLayout modeLineTwo, LinearLayout modeLineThree, LinearLayout modeLineFour, LinearLayout modeLineFive
            , LinearLayout modeLineSix, List<ModeShowBean> modeList, TextView standardOne
            , TextView standardTwo, TextView standardThree, TextView standardFour, TextView standardFive, TextView standardSix
            , List<ModeListShowBean> modeListShowBeanList, ArrayList<String> viewTexts, ArrayList<String> viewTextTwo, ArrayList<String> viewTextThree
            , ArrayList<String> viewTextFour, ArrayList<String> viewTextFive, ArrayList<String> viewTextSix, GroupSix goodsViewGroupOne
            , GroupSix goodsViewGroupTwo, GroupSix goodsViewGroupThree, GroupSix goodsViewGroupFour, GroupSix goodsViewGroupFive, GroupSix goodsViewGroupSix) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.getShopMode, PossObject.getMerId(merIdPostBean), new ShopModeListener(context, modeLineOne
                    , modeLineTwo, modeLineThree, modeLineFour, modeLineFive, modeLineSix, modeList, standardOne, standardTwo, standardThree
                    , standardFour, standardFive, standardSix, modeListShowBeanList, viewTexts, viewTextTwo, viewTextThree, viewTextFour, viewTextFive
                    , viewTextSix, goodsViewGroupOne, goodsViewGroupTwo, goodsViewGroupThree, goodsViewGroupFour, goodsViewGroupFive, goodsViewGroupSix), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("getModeList102");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 首页滚动文字
     *
     * @param context
     */
    public static void toGetText(Context context) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.hot, PossObject.toGetText(), new GetTextListener(context), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("toGetText103");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取首页未读消息数量
     *
     * @param context
     * @param storeIdPostBean
     * @param messageRead
     */
    public static void getMessageNumber(Context context, StoreIdPostBean storeIdPostBean, TextView messageRead) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.noreadmessage, PossObject.getStoreId(storeIdPostBean), new GetMessageNumberListener(context, messageRead), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("toMessageNumber104");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 首页轮播
     *
     * @param context
     * @param viewPage
     */
    public static void pagerToServer(Context context, CustomerViewPage viewPage) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    HttpUrl.homepage_viewpager, PossObject.pagerToServer(), new PagerToServerListener(context, viewPage), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("pagerToServer105");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 公告列表
     *
     * @param context
     * @param listPostBean
     * @param type
     * @param list
     * @param anim
     * @param swipeLayout
     * @param myListView
     * @param footView
     * @param homePageAdapter
     */
//    public static void enterToServer(Context context, ListPostBean listPostBean, int type, List<CommodityShowBean> list, ImageView anim, RefreshLayout swipeLayout, ListView myListView
//            , View footView, HomePageAdapter homePageAdapter, RefreshLayout.OnLoadListener onLoadListener) {
//        try {
//            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.homeEnterList,
//                    PossObject.enterToServer(listPostBean), new EnterToServerListener(context, type, list, anim, swipeLayout, myListView, footView, homePageAdapter, onLoadListener), new ErrorListener(context, 1));
//            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//            jsonObjectRequest.setTag("enterToServer106");
//            MutualApplication.getRequestQueue().add(jsonObjectRequest);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 商家订单列表
     *
     * @param context
     * @param listPostBean
     * @param type
     * @param list
     * @param anim
     * @param listView
     * @param orderAdapter
     * @param swipeLayout
     * @param footView
     */
    public static void merchantOrderList(Context context, ListPostBean listPostBean, int type, List<OrderShowBean> list, ImageView anim, ListView listView, SellOrderAdapter orderAdapter
            , RefreshLayout swipeLayout, View footView, RefreshLayout.OnLoadListener onLoadListener) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.xfz_order_list, PossObject.list(listPostBean), new MerchantOrderListListener(context, type, list, anim, listView, orderAdapter, swipeLayout, footView, onLoadListener), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("merchantOrderList107");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 检测当前版本
     *
     * @return
     */
    public static void getUpdateInfo(final Context context, TextView updateAdd, TextView newMost) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.comTel, PossObject.getUpdateInfo(), new UpdateInfoListener(context, 2, updateAdd, newMost), new ErrorListener(context, 1));
            jsonObjectRequest.setTag("getUpdateInfo108");
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(500000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 推荐二维码
     *
     * @param context
     * @param storeIdPostBean
     */
    public static void twoDimensionCode(Context context, StoreIdPostBean storeIdPostBean) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.twoDimensionCode, PossObject.getStoreId(storeIdPostBean), new TwoDimensionCodeListener(context), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("twoDimensionCode109");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 退出登录
     *
     * @param context
     * @param storeIdPostBean
     */
    public static void logOut(Context context, StoreIdPostBean storeIdPostBean) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    HttpUrl.exit, PossObject.getStoreId(storeIdPostBean), new LogOutListener(context), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("logOut110");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 收益管理数据
     *
     * @param context
     * @param storeIdPostBean
     * @param forecastEarnings
     * @param accumulatedIncome
     */
    public static void revenueManagement(Context context, StoreIdPostBean storeIdPostBean, TextView forecastEarnings, TextView accumulatedIncome) {
        try {
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                            HttpUrl.revenue_manage, PossObject.getStoreId(storeIdPostBean),
                            new RevenueHeadListener(context, forecastEarnings, accumulatedIncome), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("revenueManagement111");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 客服电话
     *
     * @param context
     */
    public static void customerTelephoneNumber(Context context) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.comTel, PossObject.customerTelephoneNumber(), new CustomerTelephoneNumberListener(context), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("customerTelephoneNumber112");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改商铺地址
     *
     * @param context
     * @param storeAddressUpdatePostBean
     */
    public static void storeAddressUpdate(Context context, StoreAddressUpdatePostBean storeAddressUpdatePostBean, TextView qu, EditText strE) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.storeAddressUpdate, PossObject.storeAddressUpdate(storeAddressUpdatePostBean), new StoreAddressUpdateListener(context, qu, strE), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("storeAddressUpdate113");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证
     *
     * @param context
     * @param verificationPostBean
     */
    public static void toProve(Context context, VerificationPostBean verificationPostBean) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    HttpUrl.converificode, PossObject.toProve(verificationPostBean),
                    new ToastListener(context, "验证成功！", 2), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("toProve114");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 原产地订单删除
     *
     * @param context
     * @param sendPostBean
     * @param list
     * @param position
     * @param originOrderAdapter
     */
    public static void deleteOrder(Context context, SendPostBean sendPostBean, List<OriginOrderShowBean> list, int position, OriginOrderAdapter originOrderAdapter) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.deleteOrder, PossObject.merchantsOrderDetail(sendPostBean), new OrderDeleteListener(context, list, position, originOrderAdapter), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("deleteOrder115");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 原产地顶大列表
     *
     * @param context
     * @param listPostBean
     * @param type
     * @param list
     * @param anim
     * @param listView
     * @param originOrderAdapter
     * @param swipeLayout
     * @param footView
     * @param onLoadListener
     */
    public static void originOrderList(Context context, ListPostBean listPostBean, int type, List<OriginOrderShowBean> list, ImageView anim, ListView listView, OriginOrderAdapter originOrderAdapter
            , RefreshLayout swipeLayout, View footView, RefreshLayout.OnLoadListener onLoadListener) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    HttpUrl.getOriginOrder, PossObject.list(listPostBean), new OriginOrderListListener(context, type, list, anim, listView, originOrderAdapter, swipeLayout, footView, onLoadListener), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("originOrderList116");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证
     *
     * @param context
     * @param verificationPostBean
     */
    public static void toProve(Context context, VerificationPostBean verificationPostBean, String orderNo) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    HttpUrl.converificode, PossObject.toProve(verificationPostBean),
                    new ToastListener(context, orderNo, 3), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("toProve117");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 满减折扣
     *
     * @param context
     * @param discount
     */
    public static void discount(Context context, TextView discount) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    HttpUrl.discount, PossObject.Null(), new
                    DiscountListener(context, discount, 0), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("discount118");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 满减显示
     *
     * @param discount
     * @param context
     * @param discountLinear
     * @param textViewTotal
     */
    public static void discount(TextView discount, Context context,
                                LinearLayout discountLinear, TextView textViewTotal,TextView totalMoney) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    HttpUrl.discount, PossObject.Null(),
                    new DiscountListener(context, discount, 1, discountLinear,
                            textViewTotal,totalMoney),
                    new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("discount119");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 列表中显示满减活动信息
     *
     * @param context
     * @param discount
     */
    public static void discountText(Context context, TextView discount) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, HttpUrl.discount,
                    PossObject.Null(), new DiscountListener(context, discount, 2), new ErrorListener(context, 1));
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjectRequest.setTag("discountText120");
            MutualApplication.getRequestQueue().add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
