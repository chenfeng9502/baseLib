package com.makeit.baselib.util;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * @author chenfeng
 * @date 2020/12/25 18:13
 * <p>
 * 用途：
 */
public class KeyboardUtil {


    public static void hintKeyBoard(Context context, EditText editText) {
        //拿到InputMethodManager
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        //如果window上view获取焦点 && view不为空
        if (editText != null) {
            //拿到view的token 不为空
            if (editText.getWindowToken() != null) {
                //表示软键盘窗口总是隐藏，除非开始时以SHOW_FORCED显示。
                imm.hideSoftInputFromWindow(editText.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }
}
