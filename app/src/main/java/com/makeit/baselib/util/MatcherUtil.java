package com.makeit.baselib.util;

import android.text.TextUtils;

/**
 *
 * @author chenfeng
 * @date 2016/3/8
 */
public class MatcherUtil {
    /**
     * 判断手机号
     */
    public static boolean isPhone(String phone) {
        String telRegex = "^(1[0-9][0-9])\\d{8}$";
        return !TextUtils.isEmpty(phone) && phone.matches(telRegex);
    }

    /**
     * 判断身份证
     */
    public static boolean isIdCard(String idNum) {
        String idRegex = "\\d{17}[0-9xX]";
        return !TextUtils.isEmpty(idNum) && idNum.matches(idRegex);
    }

    /**
     * 判断是否是英文或者字母
     */
    public static boolean isPaper(String num) {
        String numRegex = "[A-Za-z0-9]+$";
        return TextUtils.isEmpty(num) && num.matches(numRegex);
    }

}
