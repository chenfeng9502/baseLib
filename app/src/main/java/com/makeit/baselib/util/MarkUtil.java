/*
 * Copyright ©  2017-present  美契.  All Rights Reserved.
 *
 * 美契信息公司 版权所有
 */

package com.makeit.baselib.util;


import android.text.TextUtils;

/**
 * @author chenfeng
 * @date 2018/10/11 20:19
 */
public class MarkUtil {


    public static String phoneMark(String phone) {
        return idMask(phone, 4, 4);
    }

    /**
     * 用户身份证号码的打码隐藏加星号加*
     * <p>18位和非18位身份证处理均可成功处理</p>
     * <p>参数异常直接返回null</p>
     *
     * @param idCardNum 身份证号码
     * @param front     需要显示前几位
     * @param end       需要显示末几位
     * @return 处理完成的身份证
     */
    public static String idMask(String idCardNum, int front, int end) {
        //身份证不能为空
        if (TextUtils.isEmpty(idCardNum)) {
            return "";
        }
        //需要截取的长度不能大于身份证号长度
        if ((front + end) > idCardNum.length()) {
            return "";
        }
        //需要截取的不能小于0
        if (front < 0 || end < 0) {
            return "";
        }
        //计算*的数量
        int asteriskCount = idCardNum.length() - (front + end);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < asteriskCount; i++) {
            sb.append("*");
        }
        String regex = "(\\w{" + front + "})(\\w+)(\\w{" + end + "})";
        return idCardNum.replaceAll(regex, "$1" + sb + "$3");
    }


//    public static String emailMask(String email) {
//        return emailMask(email, 3, MatchUtil.INSTANCE.indexForAt(email));
//    }


    /**
     * @param email 邮箱
     * @param front 显示前面几位
     * @param end   显示@后几位
     * @return
     */
    public static String emailMask(String email, int front, int end) {

        //邮箱不能为空
        if (TextUtils.isEmpty(email)) {
            return "";
        }
        //需要截取的长度不能大于邮箱长度
        if ((front + end) > email.length()) {
            return "";
        }
        //需要截取的不能小于0
        if (front < 0 || end < 0) {
            return "";
        }
        //计算*的数量
        int asteriskCount = end - front;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < asteriskCount; i++) {
            sb.append("*");
        }

        String regex = "(\\w{" + front + "})(\\w{" + asteriskCount + "})(@\\w+\\.[a-z]+(\\.[a-z]+)?)";

        return email.replaceAll(regex, "$1" + sb + "$3");
    }

}
