package com.makeit.baselib.ui.base.mvp

import com.makeit.baselib.ui.base.BaseActivity
import com.makeit.csart.ui.base.mvp.IView

interface IPresenter<V : IView> {

    fun attachView(view: V)

    fun detachView()

    fun isViewAttached(): Boolean

    fun getView(): V?

    fun attachActivity(baseActivity: BaseActivity)

    fun getActivity(): BaseActivity?

    fun detachActivity()
}