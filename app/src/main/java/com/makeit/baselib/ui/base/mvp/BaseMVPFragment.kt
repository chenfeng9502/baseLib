package com.makeit.baselib.ui.base.mvp

import com.makeit.baselib.ui.base.BaseActivity
import com.makeit.baselib.ui.base.BaseFragment
import com.makeit.csart.ui.base.mvp.IView

@Suppress("UNCHECKED_CAST")
abstract class BaseMVPFragment<in V : IView, P : IPresenter<in V>> : BaseFragment(), IView {

    protected lateinit var presenter: P

    override fun initData() {
        presenter = createPresenter()
        presenter.attachView(this as V)
        presenter.attachActivity(activity as BaseActivity)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
        presenter.detachActivity()
    }

    abstract fun createPresenter(): P

    abstract fun createTitle(): String


    override fun dismissLoading() {

    }

    override fun showLoading() {

    }


}