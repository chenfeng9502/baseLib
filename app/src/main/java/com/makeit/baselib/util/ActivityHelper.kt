package com.makeit.baselib.util

import android.app.Activity
import java.util.*
import android.content.Intent
import androidx.annotation.NonNull
import androidx.annotation.Nullable


/**
 * Created by chenfeng on 2019/11/12.
 *
 *用途：
 */
object ActivityHelper {
    // Activity栈
    private var activityStack: Stack<Activity> = Stack()


    /**
     * 添加Activity到堆栈
     */
    fun addActivity(activity: Activity) {

        activityStack.add(activity)
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    fun currentActivity(): Activity? {
        return activityStack.lastElement()
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    fun finishActivity() {
        val activity = activityStack.lastElement()
        finishActivity(activity)
    }

    /**
     * 结束指定的Activity
     */
    fun finishActivity(activity: Activity?) {
        if (activity != null) {
            activityStack.remove(activity)
            if (!activity.isFinishing) {
                activity.finish()
            }
        }
    }

    /**
     * 结束指定类名的Activity
     */
    fun finishActivity(cls: Class<*>) {
        for (activity in activityStack) {
            if (activity.javaClass == cls) {
                finishActivity(activity)
                break
            }
        }
    }

    /**
     * 结束所有Activity
     */
    fun finishAllActivity() {
        for (i in activityStack.indices) {
            if (null != activityStack[i]) {
                activityStack[i].finish()
            }
        }
        activityStack.clear()
    }

    /**
     * 退出应用程序
     */
    fun AppExit() {
        try {
            finishAllActivity()
            //退出程序
            android.os.Process.killProcess(android.os.Process.myPid())
            System.exit(1)
        } catch (e: Exception) {
        }

    }

    /**
     * 重启应用
     * @param activity
     */
    fun restartApp(@NonNull activity: Activity) {
        val intent1 = Intent(activity, getRestartActivityClass(activity))
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED)
        if (intent1.component != null) {
            intent1.action = Intent.ACTION_MAIN
            intent1.addCategory(Intent.CATEGORY_LAUNCHER)
        }
        activity.finish()
        activity.startActivity(intent1)
        killCurrentProcess()
    }

    fun reload(anim: Boolean, activity: Activity) {
        if (!anim) {
            activity.overridePendingTransition(0, 0)
            activity.intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        }
        activity.finish()

        if (!anim) {
            activity.overridePendingTransition(0, 0)
        }
        activity.startActivity(activity.intent)
    }

    /**
     * 获取APP初始页面
     * @param context
     * @return
     */
    @Nullable
    private fun getRestartActivityClass(@NonNull context: Activity): Class<out Activity>? {
        val intent = context.packageManager.getLaunchIntentForPackage(context.packageName)
        if (intent != null && intent.component != null) {
            try {
                return Class.forName(intent.component!!.className) as Class<out Activity>
            } catch (e: ClassNotFoundException) {
                //Should not happen, print it to the log!

            }

        }
        return null
    }

    private fun killCurrentProcess() {
        android.os.Process.killProcess(android.os.Process.myPid())
        System.exit(10)
    }

}