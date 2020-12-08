package com.makeit.baselib.ui.base.mvp

import com.makeit.baselib.http.Api
import com.makeit.baselib.ui.base.BaseActivity
import com.makeit.csart.ui.base.mvp.IView
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.ref.SoftReference
import java.lang.ref.WeakReference
import java.util.*


open class BasePresenter<V : IView> : IPresenter<V> {

    private lateinit var viewReference: SoftReference<V>

    protected lateinit var activityReference: WeakReference<out BaseActivity>


    override fun attachView(view: V) {
        viewReference = SoftReference(view)
    }

    override fun detachView() {
        viewReference.clear()
    }

    override fun isViewAttached(): Boolean {
        return viewReference.get() != null
    }

    override fun getView(): V? {
        return viewReference.get()
    }

    override fun attachActivity(baseActivity: BaseActivity) {
        activityReference = WeakReference(baseActivity)
    }

    override fun getActivity(): BaseActivity? {
        return activityReference.get()
    }

    override fun detachActivity() {
        activityReference.clear()
    }

    /**
     * 跟compose()配合使用,比如ObservableUtils.wrap(obj).compose(toMainThread())
     *
     * @param <T>
     * @return
    </T> */
    fun <T> toMainThread(): ObservableTransformer<T, T> {

        return ObservableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    protected inline fun <reified T> that() = T::class.java

    protected val api = Api::class.java

    companion object {


        const val ENCRYPT_SM2 = 1
        const val ENCRYPT_SM4 = 2


    }
}