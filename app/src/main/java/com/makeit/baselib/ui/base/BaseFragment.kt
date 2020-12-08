package com.makeit.baselib.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.trello.rxlifecycle2.components.support.RxFragment

abstract class BaseFragment : RxFragment() {

    private lateinit var rootView: View

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(getLayoutId(), container, false)
        return rootView

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
        initView(rootView, savedInstanceState)
        ButterKnife.bind(this, rootView)
    }

    abstract fun initView(rootView: View?, savedInstanceState: Bundle?)

    protected open fun initData() {

    }


    abstract fun getLayoutId(): Int


}