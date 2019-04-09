package com.example.administrator.merchants.http;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：网络地址
 */
public class HttpUrl {
//        public final static String BaseUrl = "http://www.hsh55555.com:8080/hsh";//外网正式
    public final static String BaseImageUrl = "http://www.hsh55555.com:8090/hshResource";
    public final static String BaseUrl = "http://www.hsh55555.com:38080/hsh";//测试

    //    public final static String BaseUrl = "http://www.hsh55555.com:8081/hshtest";                                            //外网测试环境外网正式
    //外网正式图片
    //    public final static String BaseUrl = "http://www.hsh555.com:8080/hsh";                                                  //外网正式
//    public final static String BaseImageUrl = "http://www.hsh555.com:8090/hshResource";                                     //外网正式图片
//    public final static String BaseUrl = "http://192.168.1.102:8081/hshnew";                                                //内网ZQ测试8081
//    public final static String BaseImageUrl = "http://192.168.1.102:8080/hshResource";                                      //内网ZQ测试图片
    public final static String login = BaseUrl + "/mbstoreinfo_login.action";                                               //登录
    public final static String exit = BaseUrl + "/mbstoreinfo_exit.action";                                                 //退出
    public final static String homepage_viewpager = BaseUrl + "/mbadvertisement_list.action";                               //首页轮播图的地址
    public final static String homepage_guess = BaseUrl + "/mbmerinfo_storeRecommendList.action";                           //首页猜你喜欢的地址
    public final static String goodsDetail_up = BaseUrl + "/mbmerinfo_page.action";                                         //商品详情上面的接口
    public final static String shoppingCar_item = BaseUrl + "/mbplacecart_list.action";                                     //购物车item详情的接口
    public final static String default_address = BaseUrl + "/mbstoreaddress_definfo.action";                                //缺省地址
    public final static String add_order = BaseUrl + "/mbplaceorder_add.action";                                            //原产地订单新增
    public final static String original_home = BaseUrl + "/mbmermenu_placeList.action";                                     //原产地首页
    public final static String original_menu_one = BaseUrl + "/mbmermenu_list.action";                                      //原产地一级菜单
    public final static String original_menu_two = BaseUrl + "/mbmermenu_subList.action";                                   //原产地二级菜单
    public final static String original_search = BaseUrl + "/mbkeyword_list.action";                                        //原产地搜索
    public final static String original_second_page = BaseUrl + "/mbmerinfo_placeList.action";                              //原产地二级页面所有商品
    public final static String order_pay = BaseUrl + "/mbplaceorder_pay.action";                                            //支付订单
    public final static String ali_ok = BaseUrl + "/mbalipaybytn.action";                                                   //支付宝支付成功的tn回调
    public final static String wechat_ok = BaseUrl + "/mbwechatbytn.action";                                                //微信支付成功的tn回调
    public final static String changeCarCount = BaseUrl + "/mbplacecart_merqty.action";                                     //购物车变更
    public final static String shoppingCarCount = BaseUrl + "/mbplacecart_sum.action";                                      //获购物车数量
    public final static String pingjia = BaseUrl + "/mbstoreevaluate_list.action";                                          //评价列表
    public final static String addressList = BaseUrl + "/mbstoreaddress_list.action";                                       //商家地址列表
    public final static String defaultLocation = BaseUrl + "/mbstoreaddress_isdefault.action";                              //设置缺省地址
    public final static String updateAddress = BaseUrl + "/mbstoreaddress_update.action";                                   //修改地址
    public final static String addAddress = BaseUrl + "/mbstoreaddress_add.action";                                         //添加地址
    public final static String DeleteAddress = BaseUrl + "/mbstoreaddress_delete.action";                                   //删除
    public final static String forget_code = BaseUrl + "/mbstoreinfo_aucode.action";                                        //获取验证码
    public final static String forgetPassword = BaseUrl + "/mbstoreinfo_forgetpwd.action";                                  //商家忘记密码
    public final static String getOriginOrder = BaseUrl + "/mbplaceorder_list.action";                                      //获取原产地订单列表
    public final static String getFinishedOriginOrder = BaseUrl + "/mbplaceorder_page.action";                              //原产地订单详情
    public final static String myMessageContest = BaseUrl + "/mbstorenews_page.action";                                     //请求我的消息列表
    public final static String updatePassword = BaseUrl + "/mbstoreinfo_storepassword_update.action";                       //修改商家密码
    public final static String comTel = BaseUrl + "/mbsysteminfo.action";                                                   //请求系统
    public final static String close = BaseUrl + "/mbplaceorder_close.action";                                              //请求原产地订单关闭
    public final static String myMessage = BaseUrl + "/mbstorenews_list.action";                                            //请求我的消息列表
    public final static String no = BaseUrl + "/mbplaceorder_refapply.action";                                              //申请退款+ "/mbplaceorder_refapply.action"
    public final static String RecruitmentManagement = BaseUrl + "/mbstorerecruit_list.action";                             //请求招聘信息列表
    public final static String DeleteRecruitment = BaseUrl + "/mbstorerecruit_delete.action";                               //删除招聘信息
    public final static String addRecruitmentManagement = BaseUrl + "/mbstorerecruit_add.action";                           //添加招聘信息
    public final static String defaultbank = BaseUrl + "/mbstorebankcard_isdefault.action";                                 //设置缺省人行卡
    public final static String bankList = BaseUrl + "/mbstorebankcard_list.action";                                         //银行卡列表
    public final static String DeleteBank = BaseUrl + "/mbstorebankcard_delete.action";                                     //银行卡删除
    public final static String addbank = BaseUrl + "/mbstorebankcard_add.action";                                           //商家银行卡新增
    public final static String updatebank = BaseUrl + "/mbstorebankcard_update.action";                                     //修改银行卡信息
    public final static String usermessage = BaseUrl + "/mbstoreinfo_page.action";                                          //商铺信息详情
    public final static String setmessage = BaseUrl + "/mbstoreinfo_update.action";                                         //商铺信息修改
    public final static String beibi_pay = BaseUrl + "/mbstorebeibi_recharge.action";                                       //贝币充值
    public final static String paypassword = BaseUrl + "/mbstoreinfo_paypassword.action";                                   //贝币兑换密码确认
    public final static String beiBiPayFinish = BaseUrl + "/mbplaceorder_pay.action";                                       //贝币支付完成
    public final static String beibi_balance = BaseUrl + "/mbstoreinfo_beibiamt.action";                                    //贝币余额
    public final static String get_bank_default = BaseUrl + "/mbstorebankcard_definfo.action";                              //默认银行卡
    public final static String set_balance = BaseUrl + "/mbstorebeibi_cashapply.action";                                    //提取余额
    public final static String beibi_bill = BaseUrl + "/mbstorebeibi_list.action";                                          //充值记录
    public final static String updatebeibiPassword = BaseUrl + "/mbstoreinfo_paypassword_update.action";                    //修改贝币支付密码
    public final static String forgetbeibei_code = BaseUrl + "/mbstoreinfo_pay_aucode.action";                              //忘记贝币支付密码验证码
    public final static String forgetbeibiPassword = BaseUrl + "/mbstoreinfo_pay_forgetpwd.action";                         //忘记贝币支付密码
    public final static String phone_type = BaseUrl + "/mbstorebeibi_phone_type.action";                                    //取得赠予手机号商户类型
    public final static String beibi_gift = BaseUrl + "/mbstorebeibi_beibi_gift.action";                                    //赠与贝币
    public final static String list_classify = BaseUrl + "/mbstoremermenu_list.action";                                     //分类列表
    public final static String add_classify = BaseUrl + "/mbstoremermenu_add.action";                                       //新增分类
    public final static String mer_detail = BaseUrl + "/mbstoremerinfo_page.action";                                        //商品详情
    public final static String mer_detail_img = BaseUrl + "/mbstoremerinfo_update_all.action";                              //商品详情修改图片
    public final static String xfz_order_list = BaseUrl + "/mbstoreorder_list.action";                                      //商家订单列表
    public final static String refuse = BaseUrl + "/mbstoreorder_refreject_ordermer.action";                                //外卖订单退款申请驳回(订单商品退款)
    public final static String agree = BaseUrl + "/mbstoreorder_refagree_ordermer.action";                                  //外卖订单同意退款(订单商品退款)
    public final static String merchans_order_detail = BaseUrl + "/mbstoreorder_page.action";                               //商家订单详情
    public final static String hushisend_goods = BaseUrl + "/mbstoreorder_hsdist.action";                                   //72.	外卖订单发货
    public final static String hot = BaseUrl + "/mbkeyword_list.action";                                                    //热搜关键词
    public final static String converificode = BaseUrl + "/mbstoreorder_converificode.action";                              //71.	订单验证码验证
    public final static String yes2 = BaseUrl + "/mbplaceordermer_receive.action";                                          //确认收货+ "/mbplaceorder_receive.action"
    public final static String update_classify = BaseUrl + "/mbstoremermenu_updatename.action";                             //商家改自己分类名
    public final static String delete_classify = BaseUrl + "/mbstoremermenu_delete.action";                                 //商家删除自己分类名
    public final static String classGoodsList = BaseUrl + "/mbstoremerinfo_list.action";                                    //商家菜单商品列表
    public final static String delete_mer = BaseUrl + "/mbstoremerinfo_delete.action";                                      //商家商品删除
    public final static String up_down_mer = BaseUrl + "/mbstoremerinfo_updateisused.action";                               //商家商品上下架
    public final static String add_mer = BaseUrl + "/mbstoremerinfo_add.action";                                            //商家添加商品
    public final static String button = BaseUrl + "/mbservicemenu_button.action";                                           //
    public final static String activityList = BaseUrl + "/mbstoreinfo_settlelist.action";                                   //日对账单列表
    public final static String send_goods = BaseUrl + "/mbstoreorder_send.action";                                          //发货
    public final static String homeEnterList = BaseUrl + "/mbnotice_list.action";                                           //首页公告列表
    public final static String enterPage = BaseUrl + "/mbnotice_page.action";                                               //首页公告单页
    public final static String wantJoin = BaseUrl + "/mbnotice_signup.action";                                              //首页公告单页我要报名
    public final static String serviceList = BaseUrl + "/mbserve_list.action";                                              //服务列表
    public final static String serviceDetails = BaseUrl + "/mbserve_page.action";                                           //服务详情
    public final static String enterDetails = BaseUrl + "/mbserve_page30.action";                                           //企业需求详情接口
    public final static String enterNeedCommit = BaseUrl + "/mbserve_signup.action";                                        //服务商家报名
    public final static String serviceGetCode = BaseUrl + "/mbserve_aucode.action";                                         //服务报名获取验证码
    public final static String getShopMode = BaseUrl + "/mbmerinfo_modellist.action";                                       //获取商品型号
    public final static String twoDimensionCode = BaseUrl + "/mbstoreinfo_storeinfowithqrcode.action";                      //专属二维码
    public final static String monthIncomeList = BaseUrl + "/mbstoreperiod_list.action";                                    //月收益统计
    public final static String revenue_manage = BaseUrl + "/mbstorerecret_home.action";                                     //收益管理数据获取
    public final static String RecommendedEarningsList = BaseUrl + "/mbstorerecret_list.action";                            //推荐收益列表
    public final static String pre_revenue = BaseUrl + "/mbstoreperiod_home.action";                                        //预估收益列表
    public final static String noreadmessage = BaseUrl + "/mbstorenews_messageCount.action";                                //未读消息数量
    public final static String clearMessage = BaseUrl + "/mbstorenews_resetMessage.action";                                 //清空消息
    public final static String deleteMessage = BaseUrl + "/mbstorenews_deleteMessage.action";                               //清空消息
    public final static String deleteOrder = BaseUrl + "/mbplaceorder_delete.action";                                       //删除订单
    public final static String storeAddressUpdate = BaseUrl + "/mbstoreinfo_updateAddr.action";                             //商铺修改地址
    public final static String discount = BaseUrl + "/mbstorediscount_list.action";                                         //折扣
    public final static String sliverList = BaseUrl + "/mbstorebeibi_sliverList.action";//银贝币记录
    public final static String areaImageList = BaseUrl + "/mbarea_groupStorelist.action";
    public final static String areaInfo = BaseUrl + "/mbarea_page.action"; //专区信息
}
