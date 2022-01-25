package com.makeit.baselib

import android.app.Application
import android.content.Context

/**
 * Created by chenfeng on 2020/12/2.
 *
 *
 * 用途：
 */
open class BaseApp : Application() {


    override fun onCreate() {
        super.onCreate()
        app = this
    }

    companion object {

        private lateinit var app: BaseApp

        fun getContext(): Context {
            return app.applicationContext
        }

        fun getApp(): BaseApp {
            return app
        }

    }


}