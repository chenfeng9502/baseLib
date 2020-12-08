/*
 * Copyright ©  2017-present  美契.  All Rights Reserved.
 *
 * 美契信息公司 版权所有
 */

package com.makeit.baselib.util

import android.text.TextUtils
import android.widget.Toast
import com.makeit.baselib.BaseApp


/**
 * Created by chenfeng on 2017/5/26.
 *
 * 用途：吐司通知
 */

object ToastUtil {

    private var sToast: Toast? = null

    fun show(text: String) {
        showToast(text, false)
    }

    fun show(resId: Int) {
        val text = if (resId < 0) null else BaseApp.getContext().getString(resId)
        showToast(text, false)
    }

    fun showLong(text: String) {
        showToast(text, true)
    }

    fun showLong(resId: Int) {
        val text = if (resId < 0) null else BaseApp.getContext().getString(resId)
        showToast(text, true)
    }

    private fun showToast(text: String?, longTime: Boolean) {
        if (TextUtils.isEmpty(text)) {
            return
        }

        UiTaskExcutor.execute {
            if (sToast == null) {
                val applicationContext = BaseApp.getContext()
                sToast = Toast.makeText(
                    applicationContext,
                    text,
                    if (longTime) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
                )
            } else {
                sToast!!.setText(text)
            }

            sToast!!.show()
        }
    }
}
