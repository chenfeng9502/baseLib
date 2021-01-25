/*
 * Copyright ©  2017-present  美契.  All Rights Reserved.
 *
 * 美契信息公司 版权所有
 */
package com.makeit.baselib.util

import io.reactivex.internal.schedulers.RxThreadFactory
import org.jetbrains.annotations.NotNull
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
 * @author chenfeng
 * @date 2018/5/2 14:26
 *
 *
 * 用途：手动创建线程池管理线程
 */
object ThreadExecutor {

    private const val CORE_POOL_SIZE = 5
    private const val MAX_NUM_POOL_SIZE = 10
    private const val KEEP_ALIVE_TIME = 1

    private val executor =
        ThreadPoolExecutor(
            CORE_POOL_SIZE, MAX_NUM_POOL_SIZE,
            KEEP_ALIVE_TIME.toLong(), TimeUnit.MINUTES,
            ArrayBlockingQueue(128),
            RxThreadFactory(javaClass.`package`.name),
            ThreadPoolExecutor.AbortPolicy()
        )

    fun execute(@NotNull task: Runnable) {
        executor.execute(task)
    }

    fun remove(@NotNull task: Runnable) {
        executor.remove(task)
    }


}