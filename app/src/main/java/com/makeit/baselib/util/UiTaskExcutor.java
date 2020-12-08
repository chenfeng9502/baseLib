/*
 * Copyright ©  2017-present  美契.  All Rights Reserved.
 *
 * 美契信息公司 版权所有
 */

package com.makeit.baselib.util;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by chenfeng on 2017/6/2
 * <p>
 * 用途：
 */

public class UiTaskExcutor {

    private static Handler sMainHandler = new Handler(Looper.getMainLooper());

    public static void execute(Runnable task) {
        if(Looper.myLooper() == Looper.getMainLooper()) {
            task.run();
        } else {
            sMainHandler.post(task);
        }
    }

    public static void execute(Runnable task, long delay) {
        if(delay <= 0) {
            sMainHandler.post(task);
        } else {
            sMainHandler.postDelayed(task, delay);
        }
    }
}
