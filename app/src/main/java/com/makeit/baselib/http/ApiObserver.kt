package com.makeit.baselib.http

import com.makeit.baselib.http.model.BaseResponse
import com.makeit.baselib.util.LogUtil
import com.makeit.csart.ui.base.mvp.IView
import io.reactivex.observers.DisposableObserver

abstract class ApiObserver<T> : DisposableObserver<BaseResponse<T>> {

    private var baseView: IView? = null

    constructor() : super()

    constructor(view: IView?) : super() {
        baseView = view
    }

    override fun onStart() {
        super.onStart()
        baseView?.showLoading()
    }

    override fun onNext(response: BaseResponse<T>) {
        baseView?.dismissLoading()

//        LogUtil.e(msg = "返回信息：${response}")

        val statusCode = response.statusCode
        if (statusCode == 1) {
            onSuccess(true, response.responseResult)
        } else {
//            onSuccess(false, null)
            onError(ApiException(statusCode, response.resMessage))
        }
    }

    override fun onError(e: Throwable) {
        baseView?.dismissLoading()
        LogUtil.e(msg = "错误信息：${e.message}")
        onSuccess(false, null)
        ApiExceptionHandler.handleException(e)
    }

    abstract fun onSuccess(ok: Boolean, data: T?)

    override fun onComplete() {
        baseView?.dismissLoading()
    }

}