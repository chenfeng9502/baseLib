package com.makeit.baselib

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.tencent.bugly.Bugly
import com.tencent.tinker.lib.BuildConfig

/**
 * Created by chenfeng on 2020/12/2.
 *
 *
 * 用途：
 */
open class BaseApp : Application() {


    override fun onCreate() {
        super.onCreate()
        Bugly.init(this, buglyAppId, BuildConfig.DEBUG);
    }



    companion object {

        private val buglyAppId = "e10611f1a5"

        @SuppressLint("StaticFieldLeak")
        private lateinit var app: BaseApp

        fun getContext(): Context {
            return app.applicationContext
        }

        fun getApp(): BaseApp {
            return app
        }

    }


}