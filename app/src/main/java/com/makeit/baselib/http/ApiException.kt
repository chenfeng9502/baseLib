package com.makeit.baselib.http

import java.io.IOException

data class ApiException(var errCode: Int, var errMsg: String) : IOException()