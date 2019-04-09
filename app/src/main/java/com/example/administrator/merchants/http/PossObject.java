package com.example.administrator.merchants.http;

import android.content.Context;
import android.util.Log;

import com.example.administrator.merchants.activity.ConfirmOrderActivity;
import com.example.administrator.merchants.application.MutualApplication;
import com.example.administrator.merchants.common.CommonUtil;
import com.example.administrator.merchants.common.UserInfo;
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
import com.example.administrator.merchants.http.show.MerchantsOrderShowBean;
import com.example.administrator.merchants.http.show.ModeListShowBean;
import com.example.administrator.merchants.http.show.ModeShowBean;
import com.example.administrator.merchants.utils.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：PSOT转换JSONObject
 */
public class PossObject {
    /**
     * 登录
     *
     * @param loginPostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject login(LoginPostBean loginPostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("storephone", loginPostBean.getStorephone());
        jsonObject.put("storepassword", loginPostBean.getStorepassword());
        if (("".equals(loginPostBean.getDevicenumber())) || (loginPostBean.getDevicenumber() == null)) {
        } else {
            jsonObject.put("devicetype", loginPostBean.getDevicetype());
            jsonObject.put("devicenumber", loginPostBean.getDevicenumber());
        }

        return jsonObject;
    }

    /**
     * 地址添加
     *
     * @param addressPossBean
     * @return
     * @throws JSONException
     */
    public static JSONObject addressAdd(AddressPossBean addressPossBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("storeid", addressPossBean.getStoreid());
        jsonObject.put("receiver", addressPossBean.getReceiver());
        jsonObject.put("gender", addressPossBean.isGender());
        jsonObject.put("contact", addressPossBean.getContact());
        jsonObject.put("areaname", addressPossBean.getAreaname());
        jsonObject.put("streetaddr", addressPossBean.getStreetaddr());
        LogUtil.i("新加地址入参" + jsonObject.toString());
        return jsonObject;
    }

    /**
     * 地址修改
     *
     * @param addressPossBean
     * @return
     * @throws JSONException
     */
    public static JSONObject addressUpdate(AddressPossBean addressPossBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("addressid", addressPossBean.getAddressid());
        jsonObject.put("receiver", addressPossBean.getReceiver());
        jsonObject.put("gender", addressPossBean.isGender());
        jsonObject.put("contact", addressPossBean.getContact());
        jsonObject.put("areaname", addressPossBean.getAreaname());
        jsonObject.put("streetaddr", addressPossBean.getStreetaddr());
        LogUtil.i("修改地址入参" + jsonObject.toString());
        return jsonObject;
    }

    /***
     * 添加银行卡
     *
     * @param bankPostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject bankAdd(BankPostBean bankPostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("storeid", bankPostBean.getStoreid());
        jsonObject.put("storename", bankPostBean.getStorename());
        jsonObject.put("bindaccount", bankPostBean.getBindaccount());
        jsonObject.put("bindbank", bankPostBean.getBindbank());
        jsonObject.put("bindname", bankPostBean.getBindname());
        jsonObject.put("bindphone", bankPostBean.getBindphone());
        return jsonObject;
    }

    /***
     * 修改银行卡
     *
     * @param bankPostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject bankUpdate(BankPostBean bankPostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cardid", bankPostBean.getCardid());
        jsonObject.put("storeid", bankPostBean.getStoreid());
        jsonObject.put("storename", bankPostBean.getStorename());
        jsonObject.put("bindaccount", bankPostBean.getBindaccount());
        jsonObject.put("bindbank", bankPostBean.getBindbank());
        jsonObject.put("bindname", bankPostBean.getBindname());
        jsonObject.put("bindphone", bankPostBean.getBindphone());
        return jsonObject;
    }

    /**
     * 添加招聘信息
     *
     * @param recruitmentPostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject recruitmentAdd(RecruitmentPostBean recruitmentPostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("storeid", recruitmentPostBean.getStoreid());
        jsonObject.put("station", recruitmentPostBean.getStation());
        jsonObject.put("number", recruitmentPostBean.getNumber());
        jsonObject.put("experience", recruitmentPostBean.getExperience());
        jsonObject.put("salary", recruitmentPostBean.getSalary());
        return jsonObject;
    }

    /**
     * 列表参数转为object
     *
     * @param listPostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject list(ListPostBean listPostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("storeid", listPostBean.getStoreid());
        jsonObject.put("offset", listPostBean.getOffset());
        jsonObject.put("limit", listPostBean.getLimit());
        LogUtil.i("入参"+jsonObject.toString());
        return jsonObject;
    }

    /***
     * 设置默认地址
     *
     * @param defaultAddressPostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject getDefaultAddress(DefaultAddressPostBean defaultAddressPostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("addressid", defaultAddressPostBean.getAddressid());
        jsonObject.put("storeid", defaultAddressPostBean.getStoreid());
        return jsonObject;
    }

    /***
     * 删除地址
     *
     * @param deleteAddressPostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject delectAddress(DeleteAddressPostBean deleteAddressPostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("addressid", deleteAddressPostBean.getAddressid());
        return jsonObject;
    }

    /***
     * 对账单明细
     *
     * @param accountStatementDetailPostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject accountStatementDetail(AccountStatementDetailPostBean accountStatementDetailPostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("storeid", accountStatementDetailPostBean.getStoreid());
        jsonObject.put("offset", accountStatementDetailPostBean.getOffset());
        jsonObject.put("settleno", accountStatementDetailPostBean.getSettleno());
        jsonObject.put("limit", accountStatementDetailPostBean.getLimit());
        return jsonObject;
    }

    /**
     * 设置默认银行卡
     *
     * @param defaultBankCardPostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject defaultBank(DefaultBankCardPostBean defaultBankCardPostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cardid", defaultBankCardPostBean.getCardid());
        jsonObject.put("storeid", defaultBankCardPostBean.getStoreid());
        return jsonObject;
    }

    /**
     * 删除银行卡
     *
     * @param deleteBankCardPostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject delectBank(DeleteBankCardPostBean deleteBankCardPostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cardid", deleteBankCardPostBean.getCardid());
        return jsonObject;
    }

    /**
     * 获取storeId的jsonObject
     *
     * @param storeIdPostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject getStoreId(StoreIdPostBean storeIdPostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("storeid", storeIdPostBean.getStoreid());
        return jsonObject;
    }

    /**
     * 贝币确认密码
     *
     * @param confirmBeiBiPasswordPostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject confirmBeiBiPassword(ConfirmBeiBiPasswordPostBean confirmBeiBiPasswordPostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("storeid", confirmBeiBiPasswordPostBean.getStoreid());//商家编码
        jsonObject.put("paypassword", confirmBeiBiPasswordPostBean.getPaypassword());
        return jsonObject;
    }

    /***
     * 余额提取
     *
     * @param balanceExtractionPostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject balanceExtraction(BalanceExtractionPostBean balanceExtractionPostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("storeid", balanceExtractionPostBean.getStoreid());
        jsonObject.put("storename", balanceExtractionPostBean.getStorename());
        jsonObject.put("cashamt", balanceExtractionPostBean.getCashamt());
        jsonObject.put("receivetype", balanceExtractionPostBean.getReceivetype());
        jsonObject.put("receiveaccount", balanceExtractionPostBean.getReceiveaccount());
        jsonObject.put("receivebank", balanceExtractionPostBean.getReceivebank());
        jsonObject.put("receivename", balanceExtractionPostBean.getReceivename());
        jsonObject.put("receivephone", balanceExtractionPostBean.getReceivephone());
        return jsonObject;
    }

    /***
     * 贝币赠与的电话类型
     *
     * @param phoneTypePostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject phoneType(PhoneTypePostBean phoneTypePostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("giftphone", phoneTypePostBean.getGiftphone());
        return jsonObject;
    }

    /***
     * 贝币赠与
     *
     * @param beiBiGivePostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject beiBiGive(BeiBiGivePostBean beiBiGivePostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("storeid", beiBiGivePostBean.getStoreid());
        jsonObject.put("type", beiBiGivePostBean.getType());
        jsonObject.put("giftphone", beiBiGivePostBean.getGiftphone());
        jsonObject.put("giftamt", beiBiGivePostBean.getGiftamt());
        jsonObject.put("beibiType", beiBiGivePostBean.getBeibiType());
        com.smarttop.library.utils.LogUtil.i("aa","赠予入参"+ jsonObject.toString());
        return jsonObject;
    }

    /**
     * 获取三方支付Tn号
     *
     * @param getTnPostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject getTN(GetTnPostBean getTnPostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("storeid", getTnPostBean.getStoreid());
        jsonObject.put("storename", getTnPostBean.getStorename());
        jsonObject.put("rechamt", getTnPostBean.getRechamt());
        jsonObject.put("paytype", getTnPostBean.getPaytype());// toDO "beibi"
        return jsonObject;
    }

    /**
     * 商品编码
     *
     * @param merIdPostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject getMerId(MerIdPostBean merIdPostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("merid", merIdPostBean.getMerid());
        return jsonObject;
    }

    /**
     * 商品管理列表
     *
     * @param commodityManagementListPostBean
     * @return
     * @throws JSONException
     */

    public static JSONObject commodityManagementList(CommodityManagementListPostBean commodityManagementListPostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("storeid", commodityManagementListPostBean.getStoreid());
        jsonObject.put("menuid", commodityManagementListPostBean.getMenuid());
        jsonObject.put("offset", commodityManagementListPostBean.getOffset());
        jsonObject.put("limit", commodityManagementListPostBean.getLimit());
        return jsonObject;
    }

    /**
     * 把购物车被选中的商品从集合里面一个一个的   拿到放入数组里面
     *
     * @param context
     * @return
     */
    public static JSONArray getMerList(Context context) throws JSONException {
        JSONArray merList = new JSONArray();
        for (int i = 0; i < MutualApplication.chooseList.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("cartid", MutualApplication.chooseList.get(i).getCartid());
            jsonObject.put("merid", MutualApplication.chooseList.get(i).getMerid());
            jsonObject.put("mername", MutualApplication.chooseList.get(i).getMername());
            jsonObject.put("imgsfile", MutualApplication.chooseList.get(i).getImgsfile());
            jsonObject.put("saleprice", MutualApplication.chooseList.get(i).getSaleprice());
            jsonObject.put("merqty", MutualApplication.chooseList.get(i).getMerqty());
            Double dd = Double.parseDouble(String.valueOf(MutualApplication.chooseList.get(i).getMerqty()));
            Double cc = Double.parseDouble(String.valueOf(MutualApplication.chooseList.get(i).getSaleprice()));
            Double tt = dd * cc;
            DecimalFormat df = new DecimalFormat("0.00");
            String qq = df.format(tt);
            jsonObject.put("meramt", qq);
            jsonObject.put("modelids", MutualApplication.chooseList.get(i).getModelids());
            jsonObject.put("modeldescr", MutualApplication.chooseList.get(i).getModeldescr());
            jsonObject.put("storename", UserInfo.getInstance().getUser(context).getStorename());
            jsonObject.put("distdays", MutualApplication.chooseList.get(i).getDistdays());
            if (MutualApplication.chooseList.get(i).getRemark() == null) {
                jsonObject.put("remark", "");
            } else {
                jsonObject.put("remark", MutualApplication.chooseList.get(i).getRemark());
            }
            merList.put(jsonObject);
        }
        return merList;
    }

    /**
     * 提交订单
     *
     * @param toSubOrderPostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject toSubOrder(ToSubOrderPostBean toSubOrderPostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("merList", toSubOrderPostBean.getMerList());
        jsonObject.put("storeid", toSubOrderPostBean.getStoreid());
        jsonObject.put("storename", toSubOrderPostBean.getStorename());
        jsonObject.put("ordamt", toSubOrderPostBean.getOrdamt());
        jsonObject.put("addressid", toSubOrderPostBean.getAddressid());//
        jsonObject.put("receiver", toSubOrderPostBean.getReceiver());
        jsonObject.put("gender", toSubOrderPostBean.getGender());
        jsonObject.put("contact", toSubOrderPostBean.getContact());
        jsonObject.put("areaname", toSubOrderPostBean.getAreaname());
        jsonObject.put("streetaddr", toSubOrderPostBean.getStreetaddr());
        jsonObject.put("discountid", ConfirmOrderActivity.discountId);
        //新增字段  银贝币
        jsonObject.put("usedSilver", toSubOrderPostBean.getUsedSilver());
        jsonObject.put("isSilver", toSubOrderPostBean.getIsSilver());
        LogUtil.i("提交订单入参" + jsonObject.toString());
        return jsonObject;
    }

    /**
     * 企业需求获取验证码
     *
     * @param codePostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject getCode(CodePostBean codePostBean, int type) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        if (type == 0) {
            jsonObject.put("needsphone", codePostBean.getNeedsphone());
        } else if (type == 1) {
            jsonObject.put("storephone", codePostBean.getStorephone());
        } else if (type == 2) {
            jsonObject.put("storeid", codePostBean.getStoreid());
            jsonObject.put("storephone", codePostBean.getStorephone());
        }
        return jsonObject;
    }

    /***
     * 企业需求提交
     *
     * @param enterNeedPostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject enterNeed(EnterNeedPostBean enterNeedPostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("storeid", enterNeedPostBean.getStoreid());
        jsonObject.put("serveid", enterNeedPostBean.getGetServeid());
        jsonObject.put("needsphone", enterNeedPostBean.getNeedsphone());
        jsonObject.put("needsperson", enterNeedPostBean.getNeedsperson());
        jsonObject.put("needsdescr", enterNeedPostBean.getNeedsdescr());
        jsonObject.put("aucode", enterNeedPostBean.getAucode());
        return jsonObject;
    }

    /**
     * 无需参数
     *
     * @return
     * @throws JSONException
     */
    public static JSONObject Null() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        return jsonObject;
    }

    /**
     * 评论数据
     *
     * @return
     */
    public static JSONArray evaluateDate() {
        JSONArray merList = new JSONArray();
        for (int i = 0; i < MutualApplication.chooseOrderList.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("orddetailid", MutualApplication.chooseOrderList.get(i).getOrddetailid());
                jsonObject.put("merid", MutualApplication.chooseOrderList.get(i).getMerid());
                if ("".equals(MutualApplication.chooseOrderList.get(i).getTexts())) {
                    jsonObject.put("evaluatedescr", "非常满意！");
                } else {
                    jsonObject.put("evaluatedescr", MutualApplication.chooseOrderList.get(i).getTexts());
                }
                if ("".equals(MutualApplication.chooseOrderList.get(i).getGrade())) {
                    jsonObject.put("evaluatescore", "5");

                } else {
                    jsonObject.put("evaluatescore", MutualApplication.chooseOrderList.get(i).getGrade());
                }
                if ("".equals(MutualApplication.chooseOrderList.get(i).getStaumer())) {
                    jsonObject.put("isanonymous", "1");
                } else {
                    jsonObject.put("isanonymous", MutualApplication.chooseOrderList.get(i).getStaumer());
                }
                merList.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return merList;
    }

    /**
     * 评论
     *
     * @param evaluatePostBean
     * @return
     */
    public static HashMap<String, String> evaluate(EvaluatePostBean evaluatePostBean) {
        HashMap<String, String> map = new HashMap<>();
        map.put("wordno", evaluatePostBean.getWordno());
        map.put("wstoreid", evaluatePostBean.getWstoreid());
        map.put("wmerList", evaluatePostBean.getWmerList().toString());
        LogUtil.i("评价入参"+map.toString());
        return map;
    }

    /**
     * 忘记密码和忘记贝币密码
     *
     * @param forgetPostBean
     * @param type
     * @return
     * @throws JSONException
     */
    public static JSONObject forgetPass(ForgetPostBean forgetPostBean, int type) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        if (type == 0) {
            jsonObject.put("aucode", forgetPostBean.getAucode());
            jsonObject.put("storephone", forgetPostBean.getStorephone());
            jsonObject.put("storepassword", forgetPostBean.getStorepassword());
        } else if (type == 1) {
            jsonObject.put("storeid", forgetPostBean.getStoreid());
            jsonObject.put("aucode", forgetPostBean.getAucode());
            jsonObject.put("storephone", forgetPostBean.getStorephone());
            jsonObject.put("paypassword", forgetPostBean.getPaypassword());
        }
        return jsonObject;
    }

    /**
     * 互实攻略
     *
     * @return
     * @throws JSONException
     */
    public static JSONObject strategy() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("infoid", "storejoinLevel");
        return jsonObject;
    }

    /**
     * 检测更新app
     *
     * @return
     * @throws JSONException
     */
    public static JSONObject getUpdateInfo() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("infoid", "storeAndroidVersion");
        return jsonObject;
    }

    /**
     * 添加商品信息
     *
     * @param addGoodsPossBean
     * @return
     */
    public static HashMap<String, String> addGoods(AddGoodsPossBean addGoodsPossBean) {
        HashMap<String, String> map = new HashMap<>();
        map.put("wstoreid", addGoodsPossBean.getWstoreid());//商家编码
        map.put("wmername", addGoodsPossBean.getWmername());//商品名称
        map.put("wmerdescr", addGoodsPossBean.getWmerdescr());//商品描述
        map.put("wmermenuid", addGoodsPossBean.getWmermenuid());//菜单编码
        if (addGoodsPossBean.getWisstorenum()) {
            map.put("wisstorenum", "1");
        } else {
            map.put("wisstorenum", "0");
        }
        JSONArray wspecsList = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("specsname", addGoodsPossBean.getSpecsname());
            jsonObject.put("saleprice", addGoodsPossBean.getSaleprice());
            if (addGoodsPossBean.getWisstorenum()) {
                jsonObject.put("storenum", addGoodsPossBean.getStorenum());
            }
            jsonObject.put("retbeibiamt", addGoodsPossBean.getRetbeibiamt());
            wspecsList.put(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        map.put("wspecsList", wspecsList + "");
        return map;
    }

    /**
     * 修改商品信息
     *
     * @param addGoodsPossBean
     * @return
     */
    public static HashMap<String, String> updateGoods(AddGoodsPossBean addGoodsPossBean) {
        HashMap<String, String> map = new HashMap<>();
        map.put("wstoreid", addGoodsPossBean.getWstoreid());//商家编码
        map.put("wmerid", addGoodsPossBean.getWmerid());
        map.put("wmername", addGoodsPossBean.getWmername());//商品名称
        map.put("wmerdescr", addGoodsPossBean.getWmerdescr());//商品描述
        map.put("wmermenuid", addGoodsPossBean.getWmermenuid());//菜单编码
        map.put("wdeletefiles", CommonUtil.deleteFile);
        if (addGoodsPossBean.getWisstorenum()) {
            map.put("wisstorenum", "1");
        } else {
            map.put("wisstorenum", "0");
        }
        JSONArray wspecsList = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("specsname", addGoodsPossBean.getSpecsname());
            jsonObject.put("specsid", addGoodsPossBean.getSpecsid());
            jsonObject.put("saleprice", addGoodsPossBean.getSaleprice());
            if (addGoodsPossBean.getWisstorenum()) {
                jsonObject.put("storenum", addGoodsPossBean.getStorenum());
            } else {
                jsonObject.put("storenum", "");
            }
            jsonObject.put("retbeibiamt", addGoodsPossBean.getRetbeibiamt());
            wspecsList.put(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        map.put("wspecsList", wspecsList + "");
        return map;
    }

    /**
     * 同意退款商品列表
     *
     * @param list
     * @return
     * @throws JSONException
     */
    public static JSONArray agreeList(List<MerchantsOrderShowBean> list) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (MerchantsOrderShowBean m : list) {//遍历
            if ((m.getMerpaystatus().equals("2") || m.getMerpaystatus().equals("6")) && m.isChoosed() == true) {
                JSONObject j = new JSONObject();
                j.put("orddetailid", m.getOrddetailid());
                jsonArray.put(j);
            }
        }
        return jsonArray;
    }

    /**
     * 拒绝退款列表
     *
     * @param list
     * @return
     * @throws JSONException
     */
    public static JSONArray refuseList(List<MerchantsOrderShowBean> list) {
        JSONArray jsonArray = new JSONArray();
        for (MerchantsOrderShowBean m : list) { //遍历
            if ((m.getMerpaystatus().equals("2") || m.getMerpaystatus().equals("6")) && m.isChoosed() == true) {
                JSONObject j = new JSONObject();
                try {
                    j.put("orddetailid", m.getOrddetailid());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                jsonArray.put(j);
            }
        }
        return jsonArray;
    }

    /**
     * 发货
     *
     * @param sendPostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject send(SendPostBean sendPostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ordno", sendPostBean.getOrdno());
        jsonObject.put("storeid", sendPostBean.getStoreid());
        return jsonObject;
    }

    /**
     * 商家订单详情
     *
     * @param sendPostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject merchantsOrderDetail(SendPostBean sendPostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ordno", sendPostBean.getOrdno());
        return jsonObject;
    }

    /**
     * 消息详情
     *
     * @param messageDetailPostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject messageDetail(MessageDetailPostBean messageDetailPostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("newsid", messageDetailPostBean.getNewsid());
        return jsonObject;
    }

    /**
     * 公告
     *
     * @param noticeDetailsPostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject noticeDetails(NoticeDetailsPostBean noticeDetailsPostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("storeid", noticeDetailsPostBean.getStoreid());
        jsonObject.put("noticeid", noticeDetailsPostBean.getNoticeid());
        return jsonObject;
    }

    /**
     * 修改登录密码
     *
     * @param updatePasswordPostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject updateLoginPasword(UpdatePasswordPostBean updatePasswordPostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("storepasswordnew", updatePasswordPostBean.getStorepasswordnew());
        jsonObject.put("storeid", updatePasswordPostBean.getStoreid());
        jsonObject.put("storepasswordold", updatePasswordPostBean.getStorepasswordold());
        return jsonObject;
    }

    /**
     * 修改贝币密码
     *
     * @param updatePasswordPostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject updateBeiBiPasword(UpdatePasswordPostBean updatePasswordPostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("paypasswordnew", updatePasswordPostBean.getPaypasswordnew());
        jsonObject.put("storeid", updatePasswordPostBean.getStoreid());
        jsonObject.put("paypasswordold", updatePasswordPostBean.getPaypasswordold());
        return jsonObject;
    }

    /**
     * 选中的商品
     */
    public static void chooseList(List<MerchantsOrderShowBean> list) {
        MutualApplication.chooseOrderList.clear();

        for (MerchantsOrderShowBean s : list) {
            if (s.isChoosed()) {
                MutualApplication.chooseOrderList.add(s);
            }
        }
    }

    /**
     * 原产地订单退款
     *
     * @param applyForRefundPostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject applyForRefund(ApplyForRefundPostBean applyForRefundPostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ordno", applyForRefundPostBean.getOrdno());
        jsonObject.put("merList", applyForRefundPostBean.getMerList());
        return jsonObject;
    }

    /**
     * 热搜关键词
     *
     * @return
     * @throws JSONException
     */
    public static JSONObject toGetHotSearch() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("wordtype", "11");
        return jsonObject;
    }

    /**
     * 原产地商品搜索一级菜单
     *
     * @param menuPostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject searchOneMenu(MenuPostBean menuPostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("keyword", menuPostBean.getKeyword());
        jsonObject.put("storeid", "place");
        return jsonObject;
    }

    /**
     * 原产地商品搜索二级菜单
     *
     * @param menuPostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject searchTwoMenu(MenuPostBean menuPostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pmenuid", menuPostBean.getPmenuid());
        jsonObject.put("keyword", menuPostBean.getKeyword());
        return jsonObject;
    }

    /**
     * 原厂地商品搜索结果列表
     *
     * @param originalSearchResultPostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject searchResultList(OriginalSearchResultPostBean originalSearchResultPostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("storeid", "place");
        jsonObject.put("keyword", originalSearchResultPostBean.getKeyword());
        jsonObject.put(originalSearchResultPostBean.getId2(), originalSearchResultPostBean.getParamsId2());
        if ("null".equals(originalSearchResultPostBean.getType()) || "all".equals(originalSearchResultPostBean.getType())) {
        } else {
            jsonObject.put("orderby", originalSearchResultPostBean.getType());
        }
        jsonObject.put("offset", originalSearchResultPostBean.getOffset());
        jsonObject.put("limit", originalSearchResultPostBean.getLimit());
        return jsonObject;
    }

    /**
     * 原产地商品分类
     *
     * @param menuPostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject originalSecondPage(MenuPostBean menuPostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pmenuid", menuPostBean.getPmenuid());
        jsonObject.put("storeid", "place");
        return jsonObject;
    }

    /**
     * 原产地二级分类下的商品列表
     *
     * @param originalSecondPagePostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject originalSecondPage(OriginalSecondPagePostBean originalSecondPagePostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("storeid", "place");
        jsonObject.put(originalSecondPagePostBean.getId0(), originalSecondPagePostBean.getParamsId0());
        if ("null".equals(originalSecondPagePostBean.getType()) || "all".equals(originalSecondPagePostBean.getType())) {
        } else {
            jsonObject.put("orderby", originalSecondPagePostBean.getType());
        }
        jsonObject.put("offset", originalSecondPagePostBean.getOffset());
        jsonObject.put("limit", originalSecondPagePostBean.getLimit());
        return jsonObject;
    }

    /**
     * 订单支付
     *
     * @param payOrderPostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject payOrder(PayOrderPostBean payOrderPostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("storeid", payOrderPostBean.getStoreid());
        jsonObject.put("storename", payOrderPostBean.getStorename());
        jsonObject.put("ordno", payOrderPostBean.getOrdno());
        jsonObject.put("paytype", payOrderPostBean.getPaytype());// toDO "beibi"
        jsonObject.put("payamt", payOrderPostBean.getPayamt());// toDO 付款金额
        jsonObject.put("isSilver", payOrderPostBean.getIsSilver());
        Log.e("aa", "支付" + jsonObject.toString());
        return jsonObject;
    }

    /**
     * 订单支付完成
     *
     * @param orderFinishPostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject payBeiBiFinish(OrderFinishPostBean orderFinishPostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("storeid", orderFinishPostBean.getStoreid());//商家编码
        jsonObject.put("storename", orderFinishPostBean.getStorename());//商家名称
        jsonObject.put("ordno", orderFinishPostBean.getOrdno());//订单编号  yesoriginid nopayoriginid
        jsonObject.put("paytype", orderFinishPostBean.getPaytype());// 付款方式
        jsonObject.put("payamt", orderFinishPostBean.getPayamt());// 付款金额
        jsonObject.put("isSilver", orderFinishPostBean.getIsSilver());
        LogUtil.i("支付" + jsonObject.toString());
        return jsonObject;
    }

    /**
     * 删除招聘信息
     *
     * @param deleteRecruitmentPostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject deleteRecruitment(DeleteRecruitmentPostBean deleteRecruitmentPostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("recrid", deleteRecruitmentPostBean.getRecrid());//商家编码
        return jsonObject;
    }

    /**
     * 服务列表
     *
     * @param servicePostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject serviceList(ServicePostBean servicePostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("servetype", servicePostBean.getServetype());
        jsonObject.put("offset", servicePostBean.getOffset());
        jsonObject.put("limit", servicePostBean.getLimit());
        return jsonObject;
    }

    /**
     * 申请服务获取验证码
     *
     * @param codePostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject serviceCode(CodePostBean codePostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("needsphone", codePostBean.getNeedsphone());
        return jsonObject;
    }

    /**
     * 服务ID
     *
     * @param serveIdPostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject serviceId(ServeIdPostBean serveIdPostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("serveid", serveIdPostBean.getServeid());
        return jsonObject;
    }

    /**
     * 服务申请提交
     *
     * @param serviceCommitPostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject serviceCommit(ServiceCommitPostBean serviceCommitPostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("storeid", serviceCommitPostBean.getStoreid());
        jsonObject.put("serveid", serviceCommitPostBean.getServeid());
        jsonObject.put("needsphone", serviceCommitPostBean.getNeedsphone());
        jsonObject.put("aucode", serviceCommitPostBean.getAucode());
        return jsonObject;
    }

    /**
     * 修改商家信息
     *
     * @param storeSetMessagePostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject setStoreMessage(StoreSetMessagePostBean storeSetMessagePostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("storeid", storeSetMessagePostBean.getStoreid());
        jsonObject.put("storename", storeSetMessagePostBean.getStorename());
        jsonObject.put("storedescr", storeSetMessagePostBean.getStoredescr());
        jsonObject.put("areaname", storeSetMessagePostBean.getAreaname());
        jsonObject.put("streetaddr", storeSetMessagePostBean.getStreetaddr());
        jsonObject.put("contactphone", storeSetMessagePostBean.getContactphone());
        if (storeSetMessagePostBean.isStu()) {
            jsonObject.put("distrange", storeSetMessagePostBean.getDistrange());
        }
        if (storeSetMessagePostBean.isinvoice()) {
            jsonObject.put("isinvoice", "1");
        } else {
            jsonObject.put("isinvoice", "0");
        }
        if (storeSetMessagePostBean.isShowstatus()) {
            jsonObject.put("showstatus", "1");
        } else {
            jsonObject.put("showstatus", "0");
        }
        if (storeSetMessagePostBean.isarrivepay()) {
            jsonObject.put("isarrivepay", "1");
        } else {
            jsonObject.put("isarrivepay", "0");
        }
        jsonObject.put("openbegintime", storeSetMessagePostBean.getOpenbegintime());
        jsonObject.put("openendtime", storeSetMessagePostBean.getOpenendtime());
        jsonObject.put("isdist", storeSetMessagePostBean.getIsDist());
        if ("1".equals(storeSetMessagePostBean.getIsDist())) {
            jsonObject.put("ordminamt", storeSetMessagePostBean.getOrdminamt());
            jsonObject.put("distamt", storeSetMessagePostBean.getDistamt());
            jsonObject.put("distordamt", storeSetMessagePostBean.getDistordamt());
        } else {
            jsonObject.put("isreserve", storeSetMessagePostBean.getIsreserve());
            if ("1".equals(storeSetMessagePostBean.getIsreserve())) {
                jsonObject.put("reservehours", storeSetMessagePostBean.getReservehours());
            }
        }
        return jsonObject;
    }

    /**
     * 删除商品
     *
     * @param deleteMerchandisePostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject deleteMerchandise(DeleteMerchandisePostBean deleteMerchandisePostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("storeid", deleteMerchandisePostBean.getStoreid());
        jsonObject.put("merid", deleteMerchandisePostBean.getMerid());
        return jsonObject;
    }

    /**
     * 上下架
     *
     * @param deleteMerchandisePostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject upOrDown(DeleteMerchandisePostBean deleteMerchandisePostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("storeid", deleteMerchandisePostBean.getStoreid());
        jsonObject.put("merid", deleteMerchandisePostBean.getMerid());
        if ("1".equals(deleteMerchandisePostBean.getIsused())) {
            jsonObject.put("isused", "0");
        } else {
            jsonObject.put("isused", "1");
        }
        return jsonObject;
    }

    /**
     * 删除分类
     *
     * @param commodityManagementListPostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject deleteClassify(CommodityManagementListPostBean commodityManagementListPostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("storeid", commodityManagementListPostBean.getStoreid());
        jsonObject.put("menuid", commodityManagementListPostBean.getMenuid());
        return jsonObject;
    }

    /**
     * 微信回调
     *
     * @return
     * @throws JSONException
     */
    public static JSONObject WXPayEntry() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tn", MutualApplication.mTN);
        jsonObject.put("paystatus", "1");
        return jsonObject;
    }

    /**
     * 购物车数量变更
     *
     * @param changeCarCountPostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject changeCarCount(ChangeCarCountPostBean changeCarCountPostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("storeid", changeCarCountPostBean.getStoreid());
        jsonObject.put("merid", changeCarCountPostBean.getMerid());
        jsonObject.put("merqty", changeCarCountPostBean.getMerqty());
        jsonObject.put("modelids", changeCarCountPostBean.getModelids());
        jsonObject.put("modeldescr", changeCarCountPostBean.getModeldescr());
        return jsonObject;
    }

    /**
     * 修改商品分类名称
     *
     * @param classifyPostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject addClassify(ClassifyPostBean classifyPostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("storeid", classifyPostBean.getStoreid());
        jsonObject.put("menuname", classifyPostBean.getMenuname());
        return jsonObject;
    }

    /**
     * 修改商品分类名称
     *
     * @param classifyPostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject updateClassify(ClassifyPostBean classifyPostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("storeid", classifyPostBean.getStoreid());
        jsonObject.put("menuname", classifyPostBean.getMenuname());
        jsonObject.put("menuid", classifyPostBean.getMenuid());
        return jsonObject;
    }

    /**
     * 修改商品分类名称
     *
     * @param classifyPostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject getClassify(ClassifyPostBean classifyPostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("storeid", classifyPostBean.getStoreid());
        return jsonObject;
    }

    /**
     * 评论列表
     *
     * @param commonPostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject getCommonList(CommonPostBean commonPostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("merid", commonPostBean.getMerid());
        jsonObject.put("evatype", commonPostBean.getEvatype());
        jsonObject.put("offset", commonPostBean.getOffset());
        jsonObject.put("limit", commonPostBean.getLimit());
        return jsonObject;
    }

    /**
     * 多规格添加购物车
     *
     * @param changeCarCountPostBean
     * @param modeList
     * @param modeListShowBeanList
     * @param chooseText
     * @param chooseTextTwo
     * @param chooseTextThree
     * @param chooseTextFour
     * @param chooseTextFive
     * @param chooseTextSix
     * @return
     * @throws JSONException
     */
    public static JSONObject addShopCar(ChangeCarCountPostBean changeCarCountPostBean, List<ModeShowBean> modeList, List<ModeListShowBean> modeListShowBeanList, String chooseText,
                                        String chooseTextTwo, String chooseTextThree, String chooseTextFour, String chooseTextFive, String chooseTextSix) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("storeid", changeCarCountPostBean.getStoreid());
        jsonObject.put("merid", changeCarCountPostBean.getMerid());
        jsonObject.put("merqty", changeCarCountPostBean.getMerqty());//"add1"
        jsonObject.put("changetype", "adds");
        String id = null;
        String ids = null;
        String idss = null;
        String idfour = null;
        String idfive = null;
        String idsix = null;
        if (modeList.size() == 1) {//一个规格的情况
            for (int a = 0; a < modeListShowBeanList.size(); a++) {
                if (chooseText.equals(modeListShowBeanList.get(a).getOptionname())) {
                    id = modeListShowBeanList.get(a).getOptionid();
                }
            }
            jsonObject.put("modelids", modeList.get(0).getModelid() + "-" + id);
            jsonObject.put("modeldescr", modeList.get(0).getModelname() + ":" + chooseText);//chooseText + "_"
        } else if (modeList.size() == 2) { //二个规格的情况
            for (int a = 0; a < modeListShowBeanList.size(); a++) {
                if (chooseText.equals(modeListShowBeanList.get(a).getOptionname())) {
                    id = modeListShowBeanList.get(a).getOptionid();
                }
            }
            for (int a = 0; a < modeListShowBeanList.size(); a++) {
                if (chooseTextTwo.equals(modeListShowBeanList.get(a).getOptionname())) {
                    ids = modeListShowBeanList.get(a).getOptionid();
                }
            }
            jsonObject.put("modelids", modeList.get(0).getModelid() + "-" + id + "|" + modeList.get(1).getModelid() + "-" + ids);
            jsonObject.put("modeldescr", modeList.get(0).getModelname() + ":" + chooseText + "\t\t" + modeList.get(1).getModelname() + ":" + chooseTextTwo);//chooseText + "_" + chooseTextTwo + "_"
        } else if (modeList.size() == 3) { //三个规格的情况
            for (int a = 0; a < modeListShowBeanList.size(); a++) {
                if (chooseText.equals(modeListShowBeanList.get(a).getOptionname())) {
                    id = modeListShowBeanList.get(a).getOptionid();
                }
            }
            for (int a = 0; a < modeListShowBeanList.size(); a++) {
                if (chooseTextTwo.equals(modeListShowBeanList.get(a).getOptionname())) {
                    ids = modeListShowBeanList.get(a).getOptionid();
                }
            }
            for (int a = 0; a < modeListShowBeanList.size(); a++) {
                if (chooseTextThree.equals(modeListShowBeanList.get(a).getOptionname())) {
                    idss = modeListShowBeanList.get(a).getOptionid();
                }
            }
            jsonObject.put("modelids", modeList.get(0).getModelid() + "-" + id + "|" + modeList.get(1).getModelid() + "-" + ids + "|" + modeList.get(2).getModelid() + "-" + idss);
            jsonObject.put("modeldescr", modeList.get(0).getModelname() + ":" + chooseText + "\t\t" + modeList.get(1).getModelname() + ":" + chooseTextTwo + "\t\t" + modeList.get(2).getModelname() + ":" + chooseTextThree);//chooseText + "_" + chooseTextTwo + "_" + chooseTextThree + "_"
        } else if (modeList.size() == 4) { //4个规格的情况
            for (int a = 0; a < modeListShowBeanList.size(); a++) {
                if (jsonObject.equals(modeListShowBeanList.get(a).getOptionname())) {
                    id = modeListShowBeanList.get(a).getOptionid();
                }
            }
            for (int a = 0; a < modeListShowBeanList.size(); a++) {
                if (jsonObject.equals(modeListShowBeanList.get(a).getOptionname())) {
                    ids = modeListShowBeanList.get(a).getOptionid();
                }
            }
            for (int a = 0; a < modeListShowBeanList.size(); a++) {
                if (jsonObject.equals(modeListShowBeanList.get(a).getOptionname())) {
                    idss = modeListShowBeanList.get(a).getOptionid();
                }
            }
            for (int a = 0; a < modeListShowBeanList.size(); a++) {
                if (jsonObject.equals(modeListShowBeanList.get(a).getOptionname())) {
                    idfour = modeListShowBeanList.get(a).getOptionid();
                }
            }
            jsonObject.put("modelids", modeList.get(0).getModelid() + "-" + id + "|" +
                    modeList.get(1).getModelid() + "-" + ids + "|" + modeList.get(2).getModelid() + "-" + idss + "|" + modeList.get(3).getModelid() + "-" + idfour);
            jsonObject.put("modeldescr", modeList.get(0).getModelname() + ":" + chooseText +
                    "\t\t" + modeList.get(1).getModelname() + ":" + chooseTextTwo + "\t\t" + modeList.get(2).getModelname() + ":" +
                    chooseTextThree + "\t\t" + modeList.get(3).getModelname() + ":" +
                    chooseTextFour);
        } else if (modeList.size() == 5) { //5个规格的情况
            for (int a = 0; a < modeListShowBeanList.size(); a++) {
                if (chooseText.equals(modeListShowBeanList.get(a).getOptionname())) {
                    id = modeListShowBeanList.get(a).getOptionid();
                }
            }
            for (int a = 0; a < modeListShowBeanList.size(); a++) {
                if (chooseTextTwo.equals(modeListShowBeanList.get(a).getOptionname())) {
                    ids = modeListShowBeanList.get(a).getOptionid();
                }
            }
            for (int a = 0; a < modeListShowBeanList.size(); a++) {
                if (chooseTextThree.equals(modeListShowBeanList.get(a).getOptionname())) {
                    idss = modeListShowBeanList.get(a).getOptionid();
                }
            }
            for (int a = 0; a < modeListShowBeanList.size(); a++) {
                if (chooseTextFour.equals(modeListShowBeanList.get(a).getOptionname())) {
                    idfour = modeListShowBeanList.get(a).getOptionid();
                }
            }
            for (int a = 0; a < modeListShowBeanList.size(); a++) {
                if (chooseTextFive.equals(modeListShowBeanList.get(a).getOptionname())) {
                    idfive = modeListShowBeanList.get(a).getOptionid();
                }
            }
            jsonObject.put("modelids", modeList.get(0).getModelid() + "-" + id + "|" +
                    modeList.get(1).getModelid() + "-" + ids + "|" + modeList.get(2).getModelid() + "-" + idss + "|" +
                    modeList.get(3).getModelid() + "-" + idfour + "|" +
                    modeList.get(4).getModelid() + "-" + idfive);
            jsonObject.put("modeldescr", modeList.get(0).getModelname() + ":" + chooseText +
                    "\t\t" + modeList.get(1).getModelname() + ":" + chooseTextTwo + "\t\t" + modeList.get(2).getModelname() + ":" +
                    chooseTextThree + "\t\t" + modeList.get(3).getModelname() + ":" +
                    chooseTextFour + "\t\t" + modeList.get(4).getModelname() + ":" +
                    chooseTextFive);
        } else if (modeList.size() == 6) { //6个规格的情况
            for (int a = 0; a < modeListShowBeanList.size(); a++) {
                if (chooseText.equals(modeListShowBeanList.get(a).getOptionname())) {
                    id = modeListShowBeanList.get(a).getOptionid();
                }
            }
            for (int a = 0; a < modeListShowBeanList.size(); a++) {
                if (chooseTextTwo.equals(modeListShowBeanList.get(a).getOptionname())) {
                    ids = modeListShowBeanList.get(a).getOptionid();
                }
            }
            for (int a = 0; a < modeListShowBeanList.size(); a++) {
                if (chooseTextThree.equals(modeListShowBeanList.get(a).getOptionname())) {
                    idss = modeListShowBeanList.get(a).getOptionid();
                }
            }
            for (int a = 0; a < modeListShowBeanList.size(); a++) {
                if (chooseTextFour.equals(modeListShowBeanList.get(a).getOptionname())) {
                    idfour = modeListShowBeanList.get(a).getOptionid();
                }
            }
            for (int a = 0; a < modeListShowBeanList.size(); a++) {
                if (chooseTextFive.equals(modeListShowBeanList.get(a).getOptionname())) {
                    idfive = modeListShowBeanList.get(a).getOptionid();
                }
            }
            for (int a = 0; a < modeListShowBeanList.size(); a++) {
                if (chooseTextSix.equals(modeListShowBeanList.get(a).getOptionname())) {
                    idsix = modeListShowBeanList.get(a).getOptionid();
                }
            }
            jsonObject.put("modelids", modeList.get(0).getModelid() + "-" + id + "|" +
                    modeList.get(1).getModelid() + "-" + ids + "|" + modeList.get(2).getModelid() + "-" + idss + "|" +
                    modeList.get(3).getModelid() + "-" + idfour + "|" +
                    modeList.get(4).getModelid() + "-" + idfive + "|" +
                    modeList.get(5).getModelid() + "-" + idsix);
            jsonObject.put("modeldescr", modeList.get(0).getModelname() + ":" + chooseText +
                    "\t\t" + modeList.get(1).getModelname() + ":" + chooseTextTwo + "\t\t" + modeList.get(2).getModelname() + ":" +
                    chooseTextThree + "\t\t" + modeList.get(3).getModelname() + ":" +
                    chooseTextFour + "\t\t" + modeList.get(4).getModelname() + ":" +
                    chooseTextFive + "\t\t" + modeList.get(5).getModelname() + ":" +
                    chooseTextSix);
        }
        return jsonObject;
    }

    /**
     * 普通商品添加购物车
     *
     * @param changeCarCountPostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject addShopCar(ChangeCarCountPostBean changeCarCountPostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("storeid", changeCarCountPostBean.getStoreid());
        jsonObject.put("merid", changeCarCountPostBean.getMerid());
        jsonObject.put("merqty", "add1");
        return jsonObject;
    }

    /**
     * 首页滚动文字
     *
     * @return
     * @throws JSONException
     */
    public static JSONObject toGetText() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("wordtype", "30");
        return jsonObject;
    }

    /**
     * 首页轮播
     *
     * @return
     * @throws JSONException
     */
    public static JSONObject pagerToServer() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("advtype", "11");
        return jsonObject;
    }

    /**
     * 公告
     *
     * @param listPostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject enterToServer(ListPostBean listPostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("offset", listPostBean.getOffset());
        jsonObject.put("limit", listPostBean.getLimit());
        return jsonObject;
    }

    /**
     * 客服电话
     *
     * @return
     * @throws JSONException
     */
    public static JSONObject customerTelephoneNumber() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("infoid", "storeContactUs");
        return jsonObject;
    }

    /**
     * 商铺地址修改
     *
     * @param storeAddressUpdatePostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject storeAddressUpdate(StoreAddressUpdatePostBean storeAddressUpdatePostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("storeid", storeAddressUpdatePostBean.getStoreid());
        jsonObject.put("areaname", storeAddressUpdatePostBean.getAreaname());
        jsonObject.put("streetaddr", storeAddressUpdatePostBean.getStreetaddr());
        return jsonObject;
    }

    /**
     * 验证接口
     *
     * @param verificationPostBean
     * @return
     * @throws JSONException
     */
    public static JSONObject toProve(VerificationPostBean verificationPostBean) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("converificode", verificationPostBean.getConverificode());
        jsonObject.put("storeid", verificationPostBean.getStoreid());
        return jsonObject;
    }
}