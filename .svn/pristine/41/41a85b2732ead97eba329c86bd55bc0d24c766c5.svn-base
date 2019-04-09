package com.example.administrator.merchants.common;


import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.merchants.R;
import com.example.administrator.merchants.activity.EnterpriseDemandActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 作者：韩宇 on 2017/6/21 0021 13:50
 * 邮箱：18698802347@163.com
 * QQ：1760010478
 * 功能：时间戳转换
 */
public class DateUtils {
    /**
     * 新版时间戳显示格式：“2016-10-20“
     *
     * @param time
     * @return
     */
    public static String monthIncomeDate(String time) {
        String[] aa = time.split("\\-");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < aa.length; i++) {
            list.add(aa[i]);
        }
        return list.get(0) + "-" + list.get(1) + "-" + list.get(2);
    }

    /**
     * 时间戳显示格式：“15:30“
     *
     * @param time
     * @return
     */
    public static String getBeiRecord(String time) {
        String[] a = time.split("\\ ");
        String[] aa = a[0].split("\\-");
        String[] bb = a[1].split("\\:");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < aa.length; i++) {
            list.add(aa[i]);
        }
        for (int i = 0; i < bb.length; i++) {
            list.add(bb[i]);
        }
        return "\t" + list.get(3) + ":" + list.get(4);
    }

    /**
     * 时间戳显示格式：“2005年5月20日“
     *
     * @param time
     * @return
     */
    public static String getBeiRecord2(String time) {
        String[] a = time.split("\\ ");
        String[] aa = a[0].split("\\-");
        String[] bb = a[1].split("\\:");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < aa.length; i++) {
            list.add(aa[i]);
        }
        for (int i = 0; i < bb.length; i++) {
            list.add(bb[i]);
        }
        Calendar calendar = Calendar.getInstance();
        int date = calendar.get(Calendar.YEAR);//得到年
        if (Integer.parseInt(list.get(0)) == date) {
            return list.get(1) + "月" + list.get(2) + "日";
        }
        return list.get(0) + "年" + list.get(1) + "月" + list.get(2) + "日";
    }

    /**
     * 新版时间戳显示格式：“2016-10“
     *
     * @param time
     * @return
     */
    public static String monthIncomeDateBegin(String time) {
        String[] aa = time.split("\\-");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < aa.length; i++) {
            list.add(aa[i]);
        }
        return list.get(0) + "-" + list.get(1);
    }

    /**
     * 电话输入监听
     *
     * @param phone
     * @param code
     */
    public static void phoneInput(EditText phone, final TextView code) {
        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 11) {
                    code.setEnabled(true);
                } else {
                    code.setEnabled(false);
                }
            }
        });
    }

    /**
     * "yyyy.MM.dd HH:mm:ss"
     *
     * @param time
     * @return
     */
    public static String getDateToString(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        return sf.format(d);
    }

    /**
     * "yyyy-MM-dd"
     *
     * @param time
     * @return
     */
    public static String getDate(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(d);
    }

    /**
     * "yyyy/MM/dd"
     *
     * @param time
     * @return
     */
    public static String getEnterTime(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
        return sf.format(d);
    }

    /**
     * 获取验证码
     *
     * @param code
     * @param checkImage
     */
    public static void getCode(final EditText code, final ImageView checkImage) {
        code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (EnterpriseDemandActivity.httpCode.equals(s + "")) {
                    checkImage.setVisibility(View.VISIBLE);
                } else {
                    checkImage.setVisibility(View.GONE);
                }
            }
        });
    }

    /**
     * 19位银行卡号四位添加一个空格
     *
     * @param bankNumer
     * @return
     */
    public static String getbanknumber19(String bankNumer) {
        StringBuilder sb = new StringBuilder(bankNumer);
        for (int i = bankNumer.length() - 3; i > 0; i = i - 4) {
            sb.insert(i, " ");
        }
        return sb.toString();
    }

    /**
     * 4位添加一个空格中间加*代替
     *
     * @param bankNumer
     * @return
     */
    public static String getBankNumber(String bankNumer) {
        StringBuilder str = new StringBuilder(bankNumer.replace(" ", ""));
        int i = str.length() / 4;
        int j = str.length() % 4;
        for (int x = (j == 0 ? i - 1 : i); x > 0; x--) {
            str = str.insert(x * 4, " ");
        }
        String strtest=str.toString();
        String start=strtest.substring(0,5);
        for (int z=strtest.indexOf(" ")+1;z<strtest.lastIndexOf(" ");z++){
            if ((z-4)%5==0){
                start+=" ";
            }else {
                start+="*";
            }
        }
        return start+strtest.substring(strtest.lastIndexOf(" "));
    }


    /**
     * 16位银行卡号四位添加一个空格
     *
     * @param bankNumer
     * @return
     */
    public static String getbanknumber16(String bankNumer) {
        StringBuilder sb = new StringBuilder(bankNumer);
        for (int i = bankNumer.length() - 4; i > 0; i = i - 4) {
            sb.insert(i, " ");
        }
        return sb.toString();
    }

    /**
     * 电话号4位添加一空格
     *
     * @param tel
     * @return
     */
    public static String getTelephone(String tel) {
        StringBuilder sb = new StringBuilder(tel);
        for (int i = tel.length() - 4; i > 0; i = i - 4) {
            sb.insert(i, " ");
        }
        return sb.toString();
    }

    /**
     * 银行卡号输入4位以空格
     *
     * @param banknumber
     */
    public static void getAddCard(final EditText banknumber) {
        banknumber.addTextChangedListener(new TextWatcher() {
            int beforeTextLength = 0;
            int onTextLength = 0;
            boolean isChanged = false;
            int location = 0;//记录光标的位置
            private char[] tempChar;
            private StringBuffer buffer = new StringBuffer();
            int konggeNumberB = 0;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                beforeTextLength = s.length();
                if (buffer.length() > 0) {
                    buffer.delete(0, buffer.length());
                }
                konggeNumberB = 0;
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == ' ') {
                        konggeNumberB++;
                    }
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                onTextLength = s.length();
                buffer.append(s.toString());
                if (onTextLength == beforeTextLength || onTextLength <= 3 || isChanged) {
                    isChanged = false;
                    return;
                }
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isChanged) {
                    location = banknumber.getSelectionEnd();
                    int index = 0;
                    while (index < buffer.length()) {
                        if (buffer.charAt(index) == ' ') {
                            buffer.deleteCharAt(index);
                        } else {
                            index++;
                        }
                    }
                    index = 0;
                    int konggeNumberC = 0;
                    while (index < buffer.length()) {
                        if ((index == 4 || index == 9 || index == 14 || index == 19)) {
                            buffer.insert(index, ' ');
                            konggeNumberC++;
                        }
                        index++;
                    }
                    if (konggeNumberC > konggeNumberB) {
                        location += (konggeNumberC - konggeNumberB);
                    }
                    tempChar = new char[buffer.length()];
                    buffer.getChars(0, buffer.length(), tempChar, 0);
                    String str = buffer.toString();
                    if (location > str.length()) {
                        location = str.length();
                    } else if (location < 0) {
                        location = 0;
                    }
                    banknumber.setText(str);
                    Editable etable = banknumber.getText();
                    Selection.setSelection(etable, location);
                    isChanged = false;
                }
            }
        });
    }

    /**
     * 手机号输入前3位一空格、后四位一空格
     *
     * @param editText
     */
    public static void getaddphone(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            int beforeTextLength = 0;
            int onTextLength = 0;
            boolean isChanged = false;
            int location = 0;//记录光标的位置
            private char[] tempChar;
            private StringBuffer buffer = new StringBuffer();
            int konggeNumberB = 0;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                beforeTextLength = s.length();
                if (buffer.length() > 0) {
                    buffer.delete(0, buffer.length());
                }
                konggeNumberB = 0;
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == ' ') {
                        konggeNumberB++;
                    }
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                onTextLength = s.length();
                buffer.append(s.toString());
                if (onTextLength == beforeTextLength || onTextLength <= 3 || isChanged) {
                    isChanged = false;
                    return;
                }
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isChanged) {
                    location = editText.getSelectionEnd();
                    int index = 0;
                    while (index < buffer.length()) {
                        if (buffer.charAt(index) == ' ') {
                            buffer.deleteCharAt(index);
                        } else {
                            index++;
                        }
                    }
                    index = 0;
                    int konggeNumberC = 0;
                    while (index < buffer.length()) {
                        if ((index == 3 || index == 8)) {
                            buffer.insert(index, ' ');
                            konggeNumberC++;
                        }
                        index++;
                    }
                    if (konggeNumberC > konggeNumberB) {
                        location += (konggeNumberC - konggeNumberB);
                    }
                    tempChar = new char[buffer.length()];
                    buffer.getChars(0, buffer.length(), tempChar, 0);
                    String str = buffer.toString();
                    if (location > str.length()) {
                        location = str.length();
                    } else if (location < 0) {
                        location = 0;
                    }
                    editText.setText(str);
                    Editable etable = editText.getText();
                    Selection.setSelection(etable, location);
                    isChanged = false;
                }
            }
        });
    }

    /**
     * 电话号正则公式
     *
     * @param zifu
     * @return
     */
    public static boolean gephonetzhenze(String zifu) {
        Pattern pattern = Pattern.compile("^((13[0-9])|(15[0-9])|(18[0-9])|(17[0-9])|(14[0-9]))\\d{8}$");
        Matcher matcher = pattern.matcher(zifu);
        boolean stu = matcher.matches();
        return stu;
    }

    /**
     * 银行卡号正则公式
     *
     * @param zifu
     * @return
     */
    public static boolean getbankzhenze(String zifu) {
        Pattern pattern = Pattern.compile("^(\\d{16}|\\d{19})$");
        Matcher matcher = pattern.matcher(zifu);
        boolean stu = matcher.matches();
        return stu;
    }

    /**
     * money输入格式
     *
     * @param editText
     */
    public static void setPricePoint(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        editText.setText(s);
                        editText.setSelection(s.length());
                    }
                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    editText.setText(s);
                    editText.setSelection(2);
                }
                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        editText.setText(s.subSequence(0, 1));
                        editText.setSelection(1);
                        return;
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    /***
     * 钱数大于0按钮可按
     *
     * @param editText
     * @param button
     */
    public static void setEditText(final EditText editText, final Button button) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if ("".equals(editText.getText().toString()) || editText.getText().toString() == null) {
                    button.setBackgroundResource(R.drawable.dialog_gray_white);
                } else {
                    if (Double.parseDouble(editText.getText().toString()) == 0) {
                        button.setBackgroundResource(R.drawable.dialog_gray_white);
                    } else {
                        button.setBackgroundResource(R.drawable.dialog_theme);
                    }
                }
            }
        });
    }

    /**
     * 赠与贝币数量规则
     *
     * @param beibi
     * @param next
     */
    public static void giveBeiBiNumber(final EditText beibi, final Button next) {
        beibi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if ("".equals(s + "") || ".".equals((s + "").substring((s + "").length() - 1)) || Double.parseDouble(s + "") == 0) {
                    next.setBackgroundResource(R.drawable.dialog_gray_white);
                    next.setEnabled(false);
                } else {
                    next.setBackgroundResource(R.drawable.dialog_theme);
                    next.setEnabled(true);
                }
            }
        });
    }
}