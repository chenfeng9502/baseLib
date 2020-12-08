package com.makeit.baselib.ui.base.mvp

import android.content.Context
import android.content.Intent
import com.makeit.baselib.ui.base.BaseActivity
import com.makeit.csart.ui.base.mvp.IView

@Suppress("UNCHECKED_CAST")
abstract class BaseMVPActivity<in V : IView, P : IPresenter<in V>> : BaseActivity(), IView {

    protected lateinit var presenter: P


    override fun initPresenter() {
        presenter = createPresenter()
        presenter.attachView(this as V)
        presenter.attachActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
        presenter.detachActivity()
    }

    abstract fun createPresenter(): P


    override fun showLoading() {

    }

    override fun dismissLoading() {

    }


    companion object {

        fun open(context: Context, clazz: Class<*>) {
            context.startActivity(Intent(context, clazz))
        }

    }

}