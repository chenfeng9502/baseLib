package com.makeit.baselib.util;

import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationManagerCompat;


import com.makeit.baselib.BaseApp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

import static android.content.Context.APP_OPS_SERVICE;


/**
 * Created by chenfeng on 2017/6/12.
 * <p>
 * 用途：手机设备信息 需要获取手机权限
 */

public class DeviceUtil {

    private static final String CHECK_OP_NO_THROW    = "checkOpNoThrow";
    private static final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";

    private DeviceUtil() {

    }

    /**
     * 设备ID
     *
     * @param context
     * @return
     */
    public static String getDeviceId(Context context) {
//        TelephonyManager manager = getManager(context);
//        try {
//            if (manager != null) {
//                return manager.getDeviceId();
//            }
//        } catch (Exception ex) {
//            LogUtil.INSTANCE.e("TAG", ex.getMessage());
//        }

        return Settings.System.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);

    }


    public static TelephonyManager getManager(Context context) {
        if (context != null) {
            TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            return manager;
        }

        return null;
    }

    /**
     * 手机型号
     *
     * @return
     */
    public static String getPhoneModel() {
        return Build.MODEL;
    }

    /**
     * 手机品牌
     *
     * @return
     */
    public static String getPhoneBrand() {
        return Build.BRAND;
    }

    /**
     * 系统版本号
     *
     * @return
     */
    public static String getPhoneVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 当前版本名
     *
     * @param context
     * @return
     */
    public static String getCurrentVersionName(Context context) {

        if (context == null) {
            context = BaseApp.Companion.getContext();
        }

        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo pi = manager.getPackageInfo(context.getPackageName(), 0);
            return pi.versionName;
        } catch (Exception ex) {

        }

        return "";
    }


    /**
     * 当前版本号
     *
     * @param context
     * @return
     */
    public static int getCurrentVersionCode(Context context) {

        if (context == null) {
            context = BaseApp.Companion.getContext();
        }

        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo pi = manager.getPackageInfo(context.getPackageName(), 0);
            return pi.versionCode;
        } catch (Exception ex) {

        }

        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static boolean isNotificationEnabled(Context context) {

        if (context == null) {
            context = BaseApp.Companion.getContext();
        }

        AppOpsManager mAppOps = (AppOpsManager)
                context.getSystemService(APP_OPS_SERVICE);
        ApplicationInfo appInfo = context.getApplicationInfo();
        String pkg = context.getPackageName();
        int uid = appInfo.uid;
        Class appOpsClass; /* Context.APP_OPS_MANAGER */
        try {
            appOpsClass = Class.forName(AppOpsManager.class.getName());
            Method checkOpNoThrowMethod = appOpsClass.getMethod(CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE, String.class);
            Field opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION);
            int value = (int) opPostNotificationValue.get(Integer.class);
            return ((int) checkOpNoThrowMethod.invoke(mAppOps, value, uid, pkg) == AppOpsManager.MODE_ALLOWED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isNotificationEnabled26(Context context) {
        try {
            NotificationManager notificationManager = (NotificationManager)
                    context.getSystemService(Context.NOTIFICATION_SERVICE);

            ApplicationInfo appInfo = context.getApplicationInfo();
            String pkg = context.getPackageName();
            int uid = appInfo.uid;
            Method service = notificationManager.getClass().getDeclaredMethod("getService");
            service.setAccessible(true);
            Object sService = service.invoke(notificationManager);

            Method method = sService.getClass().getDeclaredMethod("areNotificationsEnabledForPackage", String.class, Integer.TYPE);
            method.setAccessible(true);
            return (boolean) method.invoke(sService, pkg, uid);
        } catch (Exception e) {
            return true;
        }
    }


    public static String getDeviceMac() {
        String mac = "";
        FileReader fstream = null;
        try {
            fstream = new FileReader("/sys/class/net/wlan0/address");
        } catch (FileNotFoundException e) {
            try {
                fstream = new FileReader("/sys/class/net/eth0/address");

            } catch (Exception ex) {
                mac = getAndroid7MAC();
            }
        }
        BufferedReader in = null;
        if (fstream != null) {
            try {
                in = new BufferedReader(fstream, 1024);
                mac = in.readLine();
            } catch (IOException e) {

            } finally {
                if (fstream != null) {
                    try {
                        fstream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return mac;
    }

    private static String getAndroid7MAC() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!"wlan0".equalsIgnoreCase(nif.getName())) {
                    continue;
                }
                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }
                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:", b).toLowerCase());
                }
                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
        }
        return "02:00:00:00:00:00";
    }

    /**
     * 获取app当前的渠道号或application中指定的meta-data
     *
     * @return 如果没有获取成功(没有对应值 ， 或者异常)，则返回值为空
     */
    public static String getAppChannelData(Context context, String key) {
        if (context == null || TextUtils.isEmpty(key)) {
            return "";
        }
        String channelNumber = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        channelNumber = applicationInfo.metaData.getString(key);
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {

        }
        return channelNumber;
    }


    /**
     * 判断应用是否已经启动
     *
     * @param context     一个context
     * @param packageName 要判断应用的包名
     * @return boolean
     */
    public static boolean isAppAlive(Context context, String packageName) {
        ActivityManager activityManager =
                (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processInfos
                = activityManager.getRunningAppProcesses();
        for (int i = 0; i < processInfos.size(); i++) {
            if (processInfos.get(i).processName.equals(packageName)) {
                Log.i("NotificationLaunch", String.format("the %s is running, isAppAlive return true", packageName));
                return true;
            }
        }
        Log.i("NotificationLaunch",
                String.format("the %s is not running, isAppAlive return false", packageName));
        return false;
    }


    public static boolean areNotificationsEnabled(Context context) {
        return NotificationManagerCompat.from(context).areNotificationsEnabled();
    }


    public static boolean isAndroidQ() {
        return Build.VERSION.SDK_INT >= 29;
    }

}
