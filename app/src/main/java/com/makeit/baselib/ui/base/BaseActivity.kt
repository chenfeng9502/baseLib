package com.makeit.baselib.ui.base

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import butterknife.ButterKnife
import com.makeit.baselib.util.ActivityHelper
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

abstract class BaseActivity : RxAppCompatActivity() {

    protected lateinit var rootView: View
    protected var savedInstanceState: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        if (fullable()) setScreenFull()
        super.onCreate(savedInstanceState)
        this.savedInstanceState = savedInstanceState
        rootView = View.inflate(this, getLayoutId(), null)
        setContentView(rootView)
        ButterKnife.bind(this)
        initPresenter()
        initView()
        initData()
        ActivityHelper.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityHelper.finishActivity(this)
    }

    abstract fun createTitle(): String

    abstract fun getLayoutId(): Int

    abstract fun initView()

    abstract fun initPresenter()

    protected open fun initData() {

    }

    protected open fun fullable(): Boolean = false


    protected fun setScreenFull() {
        supportRequestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY)
        if (Build.VERSION.SDK_INT >= 21) {
            val decorView = window.decorView
            val option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            decorView.systemUiVisibility = option;
            window.statusBarColor = Color.TRANSPARENT;
        }
    }

    private fun gestureEnable() {

    }


    protected fun open(context: Context, clazz: Class<BaseActivity>) {
        context.startActivity(Intent(context, clazz))
    }

    protected fun open(context: Context, bundle: Bundle, clazz: Class<BaseActivity>) {
        val intent = Intent(context, clazz)
        intent.putExtras(bundle)
        context.startActivity(intent)
    }


}