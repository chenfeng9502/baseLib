package com.makeit.baselib.http.model

data class BaseResponse<T>(
    val statusCode: Int, val resMessage: String, val methodName: String, var responseResult: T
)