/*
 * Copyright ©  2017-present  美契.  All Rights Reserved.
 *
 * 美契信息公司 版权所有
 */

package com.makeit.baselib.util;

import java.security.MessageDigest;

/**
 * Created by chenfeng on 2017/5/27.
 * <p>
 * 用途：MD5校验
 */

public class MD5Util {

    /**
     * md5 加密
     *
     * @param str requestParam+appsecret
     * @return
     */
    public static String getMd5Result32(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        byte[] byteArray = messageDigest.digest();
        StringBuilder md5StrBuff = new StringBuilder();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            } else {
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        }

        return md5StrBuff.toString().toUpperCase();
    }

    /**
     * @param str md5大写 + appsecret
     * @return
     */
    public static String getMd5Result16(String str) {

        try {
            return getMd5Result32(str).substring(8, 24).toLowerCase();
        } catch (Exception ex) {
            return "";
        }
    }
}
