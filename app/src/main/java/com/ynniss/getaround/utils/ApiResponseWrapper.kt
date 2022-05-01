package com.ynniss.getaround.utils

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

sealed class ApiResponseWrapper<T>(val apiResponse: T?, val errorMessage: String?) : Parcelable {
    @Parcelize
    class Success<T>(private val data: @RawValue T) : ApiResponseWrapper<T>(data, null)

    @Parcelize
    class Error<T>(private val message: String) : ApiResponseWrapper<T>(null, message)
}