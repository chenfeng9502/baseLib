package com.makeit.baselib.http


import com.tencent.tinker.lib.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext

/**
 * Created by chenfeng on 2019/10/16.
 *
 *用途：
 */
class ApiHelper private constructor() {

    private var okHttpClient: OkHttpClient


    private var appRetrofit: Retrofit


    init {
        val x509 = X509()
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, arrayOf(x509), SecureRandom())

        val okBuilder = OkHttpClient.Builder()
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .sslSocketFactory(sslContext.socketFactory, x509)
            .hostnameVerifier { _, _ -> true }


        if (BuildConfig.DEBUG) {
            okBuilder.addInterceptor(DebugLogInterceptor())
        }

        okHttpClient = okBuilder.build()

        appRetrofit = Retrofit.Builder()
            .baseUrl(BASE_APP_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {

        const val BASE_APP_URL = ""

        private const val TIME_OUT = 15L

        private var instance: ApiHelper? = null
            get() {
                if (field == null) {
                    field = ApiHelper()
                }
                return field
            }

        @Synchronized
        fun get(): ApiHelper {
            return instance!!
        }

        fun <T> createApi(clazz: Class<T>): T {
            return get().appRetrofit.create(clazz)
        }
    }
}